import robobattler2.*;
import java.awt.Point;
/*
 1144542 小山貴之
 SimpleIsBestの仕様
 相手がMissileを撃たなかった又は自分が選考の時:
 1,取得できる情報の更新(相手情報がないのでMapの更新を行わない)
 2,自分のMissileTypeより爆撃範囲を特定する.
 3,現在のfieldの状態の中から探索をかけて評価関数の最大値がでる場所に爆撃する。
 相手がMissileを撃っ手いる時:
 1,取得できる情報の更新
 2,自分のMissileTypeより爆撃範囲を特定する.
 3,現在のfieldの状態の中から探索をかけて評価関数の最大値がでる場所に爆撃する。
 探索の仕様:
 fieldの予測現状態を保存するこのとき予測に使用する相手の爆撃中心の座標の情報は、
 パワーサーチであろうと、通常サーチであろうと全て正しいと考えて予測を行う。
 ループにより、(0,0) ~ (9,9)の全探索を行う
 一点(x,y)が決定した時に、自分の爆撃範囲は定数で相対座標を使って用意してあるのでこれを使用して、
 (x,y)の地点にもし撃った時の爆撃範囲を求める事が出来、この範囲の中に敵のマス、自分のマス、空白のマス、
 マス外の４つの重みを振りわて、総和が最も大きい物を最適解とする。
 
 評価関数の評価値が同値になる場合が多々出現してしまうのが欠点で、改善をするために評価を厳密にする方法を行おうと
 したが、実装にはいたらなかった。以下実装に至らなかった評価内容を記述する。
  
 評価関数の最大値が複数個出てきた場合に、それぞれのx,y座標を保存しておき
 そのマスに対する爆撃範囲の1,2マス外側の範囲において、より多くの敵のマスと隣接しているかどうかという
 評価を行ってやれば、より隣接している場合敵の次のMissileを打ち込む時に(敵のマス+自分のマス)を取得しなければ
 いけない状態が増えるためより強くなると考える。
 
 
 */

public class SimpleIsBest extends Robo{
	
	private int enemyMissileKind;
	private Point enemyTarget;
	private int myMissileKind;
	private Point[] myMissileArea;
	private int turn = 0;//現在のターン数
	private boolean lineMissileFlag = false;//falseなら　Vertical,trueならHoraizontalArea
	private int[][] field = new int[Field.SIZE][Field.SIZE];
	
	private final Point[] BigXMissileArea= {
	new Point(-2, -2), new Point(-1, -1), new Point(1, 1), new Point(2, 2),
	new Point(2, -2), new Point(1, -1), new Point(-1, 1), new Point(-2, 2)
	};
	private final Point[] PlusMissileArea = {
	new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)
	};
	private final Point[] BigPlusMissileArea = {
	new Point(-2, 0), new Point(-1, 0), new Point(1, 0), new Point(2, 0),
	new Point(0, -2), new Point(0, -1), new Point(0, 1), new Point(0, 2)
	};
	private final Point[] XMissileArea = {
	new Point(-1, -1), new Point(0, 0), new Point(1, 1), new Point(1, -1), new Point(-1, 1)
	};
	private final Point[] LineMissileVerticalArea = {  // 縦の場合の爆撃範囲の座標列（相対値）
	new Point(0, -3), new Point(0, -2), new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)
	};
	private final Point[] LineMissileHoraizontalArea = {  // 横の場合の爆撃範囲の座標列（相対値）
	new Point(-3, 0), new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)
	};
	
	protected void action(Missile missile){
		Point fireTarget = new Point();
		
		update(missile);//ターン経過の際に自分のもつそれぞれの値をそれぞれの情報より更新する。
		//	printMap();		//予測fieldの出力
		
		if(missile.getType() == 1){
			fireTarget = solve(); //探索
		}else{
			fireTarget = solve(selectArea(missile.getType())); //探索
		}
		createMap(selectArea(missile.getType()),fireTarget,2);//書き換え
		updateTurn();//turnの更新
		
		if(lineMissileFlag){
			missile.setHorizontal();
		}
		else{
			missile.setVertical();
		}
		
//		System.out.println("********************debug message missile.getType()"+missile.getType()+"**********************");
//		System.out.println("********************debug message fireTarget.x="+fireTarget.x+"*******************");
//		System.out.println("********************debug message fireTarget.y="+fireTarget.y+"*******************");
//		System.out.println("********************debug message this.lineMissileFlag="+this.lineMissileFlag+"*******************");
		fire(fireTarget.x,fireTarget.y);//Fire!!!!!!!!!!!!!!!!!!!!!
	}
	/*
	 update(Missile missile)
	 自分の番が回ってきた際に、与えられる情報を全て更新する。
	 更新内容
	 敵のミサイルのタイプ、敵のミサイルの爆撃位置、爆撃によるfieldの変化
	 */
	private void update(Missile missile){
		if(super.searchTarget() == null){return;}//先手の時の初手の動き
		this.enemyMissileKind = super.searchType();
		this.myMissileKind = missile.getType();
		
		if(turn > 10){
			this.enemyTarget = super.powerSearchTarget();
		}
		else{
			this.enemyTarget = super.searchTarget();
			
		}
		createMap(selectArea(this.enemyMissileKind),this.enemyTarget,1);
		
//		System.out.println("********************debug message enemyMissileKind"+this.enemyMissileKind+"**********************");
//		System.out.println("********************debug message enemyTarget.x="+this.enemyTarget.x+"************");
//		System.out.println("********************debug message enemyTarget.y="+this.enemyTarget.y+"************");
	}
	/*
	 updateTurn()
	 ターン数の制御を行う.
	 */
	private void updateTurn(){
		this.turn++;
		if(turn==16){
			for (int i=0; i<Field.SIZE;i++) {
				for(int j=0;j<Field.SIZE;j++){
					field[i][j] =0;
				}
			}
			this.turn=0;
		}
		
	}	
	/*
	 createMap():
	 ミサイルの種類と打ち込んだ座標ノ情報から，推定Mapを制作する。
	 */
	private void createMap(Point[] missileArea,Point target,int zin){		
		for(Point i:missileArea){
			int x = i.x+target.x;
			int y = i.y+target.y; 
			if(x<0 || Field.SIZE<=x){continue;}
			else if(y<0 || Field.SIZE<=y){continue;}
			else{this.field[x][y] = zin;}
		}
	}
	/*
	 solve
	 fieldと自分のミサイルの情報により最適解を探索する
	 最適解：
	 */
	private Point solve(Point[] Area){		
		int max = 0;
		int check = 0;
		Point ans = new Point(4,4);
		if(super.searchTarget() == null){
			return ans;
		}
		
		for(int i = 0;i<Field.SIZE;i++){
			for(int j = 0;j<Field.SIZE;j++){
				check = hantei(Area,i,j);
				if(max<check){
					max = check;
					ans.x = j;
					ans.y = i;
				}
			}
		}
		return ans;
	}
	/*
	 solve();;LineMissile用
	 fieldと自分のミサイルの情報により最適解を探索する
	 最適解：
	 */
	private Point solve(){		
		int max = 0;
		int checkVertical = 0;
		int checkHoraizontal = 0;
		Point ans = new Point(5,0);
		if(super.searchTarget() == null){
			return ans;
		}		
		for(int i = 0;i<Field.SIZE;i++){
			for(int j = 0;j<Field.SIZE;j++){
				checkVertical = hantei(this.LineMissileVerticalArea,i,j);
				checkHoraizontal = hantei(this.LineMissileHoraizontalArea,i,j);
				if(checkVertical<checkHoraizontal){checkVertical = checkHoraizontal;}
				if(max<checkVertical){
					max = checkVertical;
					ans.x = j;
					ans.y = i;
					this.lineMissileFlag =true;
				}				
			}
		}
		return ans;
	}
	/*
	 hantei()
	 この評価関数において
	 0:空き地
	 1:敵の陣地
	 2:自分の陣地
	 のそれぞれの重みを変化させる事によって、精度を変化できるのでは？
	 */
	private int hantei(Point[] Area,int i,int j){
		int ans = 0;
		for(Point k:Area){
			int x = k.x+j;
			int y = k.y+i;
			if(x<0 || Field.SIZE<=x){ans-=5;}
			else if(y<0 || Field.SIZE<=y){ans-=5;}
			else if(field[x][y]==0){ans+=1;}
			else if(field[x][y]==1){ans+=2;}
			else if(field[x][y]==2){ans+=0;}
		}
		return ans; 
	}
	
	/*
	 selectArea()
	 数値からミサイルの爆撃範囲を返す
	 */
	private Point[] selectArea(int type){
		switch(type){
			case 4:
				return this.BigPlusMissileArea;
			case 5:
				return this.BigXMissileArea;
			case 1:
					return this.LineMissileHoraizontalArea;
			case 2:
				return this.PlusMissileArea;
		    case 3:
				return this.XMissileArea;
		}
		return null;
	}
	
	/*debug用関数
	 printMap(void):
	 フィールドをターミナル上に表示する関数
	 */
	private void printMap(){
		for(int i = 0;i<Field.SIZE;i++){
			for(int j = 0;j<Field.SIZE;j++){
				System.out.print(field[j][i]);
			}
			System.out.println();
		}
	}
	
	
}

