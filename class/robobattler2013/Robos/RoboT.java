import robobattler2.*;
import java.awt.Point;

public class RoboT extends Robo{
	private static int turn=0;
	private final static int LINE = 1;
	private final static int PLUS = 2;
	private final static int X = 3;
	private final static int BIGPLUS = 4;
	private final static int BIGX = 5;
	private final static int MY_AREA=1;
	private final static int ENEMY_AREA=2;
	private int[][] field=new int[9][9];
	
	
	///////////////////////////////////////////////////////////////////
	public Point[] getArea(int missileType,int lineCourse){
		if(missileType==LINE){//LINEのエリア
			if(lineCourse==0){//垂直
				final Point[] AREA = {  // 縦の場合の爆撃範囲の座標列（相対値）
					new Point(0, -3), new Point(0, -2), new Point(0, -1), new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)};
				return AREA;
			}
			else{
				final Point[] AREA = {  // 横の場合の爆撃範囲の座標列（相対値）
					new Point(-3, 0), new Point(-2, 0), new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)};
				return AREA;
			}
		}
		else{//その他のミサイルの場合（念のため）
			return getArea(missileType);
		}
		
		
		
	}
	
	///////////////////////////////////////////////////////////////////
	public Point[] getArea(int missileType){
		if(missileType==LINE){
			return getArea(LINE,0);
		}
		else if(missileType==PLUS){
			final Point[] AREA = {new Point(-1, 0), new Point(0, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1)};
			return AREA;
		}
		else if(missileType==X){
			final Point[] AREA = {new Point(-1, -1), new Point(0, 0), new Point(1, 1), new Point(1, -1), new Point(-1, 1)};
			return AREA;
		}
		else if(missileType==BIGPLUS){
			final Point[] AREA = {new Point(-2, 0), new Point(-1, 0), new Point(1, 0), new Point(2, 0),new Point(0, -2), new Point(0, -1), new Point(0, 1), new Point(0, 2)};
			return AREA;
		}
		else if(missileType==BIGX){	
			final Point[] AREA = {new Point(-2, -2), new Point(-1, -1), new Point(1, 1), new Point(2, 2),new Point(2, -2), new Point(1, -1), new Point(-1, 1), new Point(-2, 2)};
			return AREA;
		}
		return null;
	}
	
	///////////////////////////////////////////////////////////////////
	public void maping(Point[] p,int x,int y,int area){//爆風の場所、撃つ座標、だれのエリアか
		for(int i=0;i<p.length;i++){
			int fireX=p[i].x;
			int fireY=p[i].y;
			int ax,ay;
			ax=x+fireX;
			ay=y+fireY;
			if(ax<9 && ax>=0 ){
				if(ay<9 && ay>=0){
					field[ay][ax]=area;//なぜかこれで正しくマッピングされてる
				}
			}
		}
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(field[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");
		
	}
	///////////////////////////////////////////////////////////////////
	public Point searchArea(Point[] p){
		int[][] newArea=new int[9][9];//そのマスで撃った場合の新規獲得エリア数
		int max=0;
		Point maxPoint=new Point();
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				for(int k=0;k<p.length;k++){
					int x=p[k].x+j;
					int y=p[k].y+i;
					if(x>=0 && x<9){
						if(y>=0 && y<9){
							if(field[x][y]!=MY_AREA){
								newArea[i][j]+=1;
							}
						}
					}
				}
				if(newArea[i][j]>max){
					max=newArea[i][j];
					maxPoint.x=i;
					maxPoint.y=j;
				}
			}
		}
		return maxPoint;
	}
	/////////////////////////////////////////////////////////////////
	public int searchNum(int x,int y,int dir){
		int num=0;
		Point[] area=getArea(LINE,dir);
		for(int i=0;i<area.length;i++){
			if(x+area[i].x>=0 && x+area[i].x<9){
				if(y+area[i].y>=0 && y+area[i].y<9){
					if(field[x+area[i].x][y+area[i].y]!=MY_AREA){
						num++;
					}
				}
			}
		}
		return num;
	}
	///////////////////////////////////////////////////////////////////
	protected void action(robobattler2.Missile missile){
		
		int my_x=0;
		int my_y=0;
		int e_x=0;//enemy
		int e_y=0;
		int get;//自分のとれる陣地の数
		int my_type=1;
		int enemy_type=1;
		int[] num=new int[81];//9*9マス分
		Point search=new Point();
		
		turn++;
		if(turn>16){
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					field[i][j]=0;
				}
			}
			
			turn=1;
		}
		System.out.println(turn);
		
		my_type=missile.getType();
		enemy_type=searchType();
		System.out.println("m: "+my_type);
		System.out.println("e: "+enemy_type);
		
		Point[] my_fireArea=getArea(my_type);
		Point[] e_fireArea=getArea(enemy_type);
		
		if(turn==1){//初手
			my_fireArea=getArea(my_type);
			if(my_type==LINE){//LINEボムは垂直にする
				missile.setVertical();
				my_fireArea=getArea(my_type,0);
			}
			my_x=4;//真ん中
			my_y=4;
			search=searchTarget();
			if(search!=null){
				e_x=search.x;
				e_y=search.y;
			}
		}
		else if(turn==4 ||turn==6 || turn==8 || turn==10 || turn==12 ){
			search=powerSearchTarget();
			System.out.println("Psearch!");
			e_x=search.x;
			e_y=search.y;
			my_fireArea=getArea(my_type);
			maping(e_fireArea,e_x,e_y,ENEMY_AREA);
			
			if(my_type==enemy_type){
				my_x=e_x;
				my_y=e_y;
			}
			
			else{
				if(enemy_type==LINE){
					if(my_type==PLUS || my_type==BIGPLUS){
						my_x=e_x;
						my_y=e_y;
					}
					else{
						Point p=searchArea(my_fireArea);
						my_x=p.x;
						my_y=p.y;
					}
				}
				else if(enemy_type==PLUS){
					if(my_type==LINE || my_type==BIGPLUS){
						my_x=e_x;
						my_y=e_y;
					}
					else{
						Point p=searchArea(my_fireArea);
						my_x=p.x;
						my_y=p.y;						
					}
				}
				else if(enemy_type==X){
					if(my_type==BIGX){
						my_x=e_x;
						my_y=e_y;
					}
					else if(my_type==LINE){
						my_x=e_x+1;
						my_y=e_y+1;
						if(my_x>8 || my_x<0){
							my_x-=2;
						}
						if(my_y>8 || my_y<0){
							my_y-=2;
						}
					}
					else{
						Point p=searchArea(my_fireArea);
						my_x=p.x;
						my_y=p.y;		
					}
				}
				else if(enemy_type==BIGPLUS){
					if(my_type==LINE || my_type==PLUS){
						my_x=e_x;
						my_y=e_y;
					}
					else{
						Point p=searchArea(my_fireArea);
						my_x=p.x;
						my_y=p.y;						
					}
					
				}
				else{
					if(my_type==X){
						my_x=e_x;
						my_y=e_y;
					}
					else if(my_type==LINE){
						my_x=e_x+1;
						my_y=e_y+1;
						if(my_x>8 || my_x<0){
							my_x-=2;
						}
						if(my_y>8 || my_y<0){
							my_y-=2;
						}
					}
					else{
						Point p=searchArea(my_fireArea);
						my_x=p.x;
						my_y=p.y;		
					}
				}
				
			}
		}
		else{
			
			if(my_type==LINE){
				System.out.println("LINE BOM!!!!!!!!!!!!!!!!!!");
				int max1=0;
				int max2=0;
				my_fireArea=getArea(my_type,0);
				Point p1=searchArea(my_fireArea);
				max1=searchNum(p1.x,p1.y,0);
				System.out.println(max1);
				
				my_fireArea=getArea(my_type,1);
				Point p2=searchArea(my_fireArea);
				max2=searchNum(p2.x,p2.y,1);
				System.out.println(max2);
				
				if(max1>max2){
					missile.setVertical();
					my_x=p1.x;
					my_y=p1.y;
					my_fireArea=getArea(my_type,0);
					System.out.println("Vertical");
					
				}
				else{
					missile.setHorizontal();
					my_fireArea=getArea(my_type,1);
					my_x=p2.x;
					my_y=p2.y;
				}
			}
			else{
				Point p=searchArea(my_fireArea);
				my_x=p.x;
				my_y=p.y;
				
				my_fireArea=getArea(my_type);
			}
			
		}
		if(my_type==LINE){//LINEボムのときに爆発範囲が枠外にでないように調整
			if(my_x<2 || my_x>6){
				missile.setVertical();
				my_fireArea=getArea(my_type,0);
			}
			else if(my_y<2 || my_y>6){
				missile.setHorizontal();
				my_fireArea=getArea(my_type,1);
			}
			else{
				my_x=4;
				my_y=4;
				missile.setHorizontal();
				my_fireArea=getArea(my_type,1);
				
			}
		}
		
		maping(my_fireArea,my_x,my_y,MY_AREA);
		System.out.println("type:"+my_type);
		System.out.println("x:"+my_x+"    y:"+my_y);
		fire(my_x,my_y);
		
	}
}



/****
 memo
 初手真ん中
 あとはパワーサーチ使いまくって相手と自分の弾、落下位置を記録
 相手と自分の弾が一緒なら完全にかぶせていく
 それ以外は自分の新規獲得エリアが多いとこに打ち込む
  パワーサーチのターンは勘で決めてみた
 サーチがなんかおかしいけど修正しないほうが強い罠
 
 
 相手がLINEで自分がプラスやったら同じとこ、相手がプラスで自分がLINEでも同じとこ
 相手ちびプラスで自分大きいプラスでもおなじとこ　逆も
 
 ****/