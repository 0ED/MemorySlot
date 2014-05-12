abstract class Shape {
    //フィールド
    String type;
    
    //コンストラクタ
    public Shape(String type) {this.type = type;}
    
    //ゲッター
    String getType() {return this.type;}
    abstract double getArea(); //継承したクラスで設定（抽象）
}