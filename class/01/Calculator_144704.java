/*
 氏名： 高橋　右
 学籍番号： 144704
 演習番号： 演習01
 提出日： 9/24
 ファイル名： Calculator_144704.java
 プログラムの動作説明：
	実行時、コマンドラインに数値を2つ入力すると
	その2つの数値の加算、減算、乗算、除算、剰余を計算するプログラム
	（見返したときにもわかりやすいので、こういったコメントをつけている）
 感じた事：
	もっとスマートに作ろうとしたが、今の知識ではきついなあと思った。
 
 実行結果：
 ==========================================
 
 Task $ java Calculator_144704 111 222
 Picked up _JAVA_OPTIONS: -Dfile.encoding=utf8
 加算： 111.0 + 222.0 = 333.0
 減算： 111.0 - 222.0 = -111.0
 乗算： 111.0 * 222.0 = 24642.0
 除算： 111.0 / 222.0 = 0.5
 剰余： 111.0 % 222.0 = 111.0
 
 ==========================================
*/


public class Calculator_144704 {
	public static void main(String arg[]) {
		
		double number_0, number_1;
		double plus, minus, kakeru, waru, amari;

		if (arg.length == 2) { //コマンドラインの引数が2つなら数値を変換し、計算し、出力する
			
			//文字列から数値へ変換
			number_0 = Double.parseDouble(arg[0]);
			number_1 = Double.parseDouble(arg[1]);
			
			//計算
			plus = number_0 + number_1;
			minus = number_0 - number_1;
			kakeru = number_0 * number_1;
			waru = number_0 / number_1;
			amari = number_0 % number_1;
			
			//出力
			System.out.println("加算： "+number_0+" + "+number_1+" = "+plus);
			System.out.println("減算： "+number_0+" - "+number_1+" = "+minus);
			System.out.println("乗算： "+number_0+" * "+number_1+" = "+kakeru);
			System.out.println("除算： "+number_0+" / "+number_1+" = "+waru);
			System.out.println("剰余： "+number_0+" % "+number_1+" = "+amari);
		}

		else { //コマンドラインの引数が2つでないなら、入力し直し。
			System.out.println("コマンドラインの引数に2つの数値を入力し直してください");
		}

	}
}