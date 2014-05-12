/*
 工夫した点、評価してもらいたい点：
 fieldを全て見ていき、どこにミサイルを落とせば効率が良いか判定し、重み付けをした点。
 */

import robobattler2.*;
import java.awt.Point;

public class Robo125 extends Robo{
    /*ミサイルの爆撃範囲*/
    private final Point[] LINE = {new Point(-3,0),new Point(-2,0),new Point(-1,0),new Point(0,0),new Point(1,0),new Point(2,0),new Point(3,0)};
    private final Point[] PLUS = {new Point(-1,0),new Point(0,0),new Point(1,0),new Point(0,1),new Point(0,-1)};
    private final Point[] X = {new Point(0,0),new Point(-1,-1),new Point(-1,1),new Point(1,-1),new Point(1,1)};
    private final Point[] BIGPLUS = {new Point(-2,0),new Point(-1,0),new Point(1,0),new Point(2,0),new Point(0,-2),new Point(0,-1),new Point(0,1),new Point(0,2)};
    private final Point[] BIGX = {new Point(-2,-2),new Point(-2,2),new Point(-1,-1),new Point(-1,1),new Point(1,2),new Point(1,1),new Point(2,2),new Point(2,1)};
    
    private final Point[][] missiles = {null,LINE,PLUS,X,BIGPLUS,BIGX};
    private final int[] errorWeight = {1,2,1,2,4,2,1,2,1};//searchTargetでどこに落とされたかを計算するための確率の9マス
    
    private final int SIZE = 9;
    int[][] field = new int[SIZE][SIZE];
    int turn;
    int power;
    
    public Robo125(){
        super();
        init();
    }
    /*初期化*/
    private void init(){
        turn = 0;
        power = 0;
        for(int x=0; x<SIZE; x++){
            for(int y=0; y<SIZE; y++){
                field[x][y] = 0;
            }
        }
    }
    
    protected void action(robobattler2.Missile missile) {
        if(turn == 16){
            init();
        }
        int ownType = missile.getType();
        int enemyType = searchType();
        if(searchTarget() != null){
            Point enemyTarget = searchTarget();
            if(turn < 11){
                field = evalErrorNextField(field,enemyTarget,enemyType);
            }else{
                enemyTarget = powerSearchTarget();
                field = evalNextField(field,enemyTarget,enemyType,false,true);
            }
        }
        int max = -100000;
        int[][] maxField = null;
        Point maxPoint = null;
        /*フィールドを全てみていき、どこに爆撃するのが効率がよいか判定*/
        for(int x=0; x<SIZE; x++){
            for(int y=0; y<SIZE; y++){
                int[][] karifield = evalNextField(field,new Point(x,y),ownType,true,true);
                /*LineMissileが横*/
                if(max < fieldSum(karifield)){
                    max = fieldSum(karifield);
                    maxField = karifield;
                    maxPoint = new Point(x,y);
                    missile.setHorizontal();
                }
                /*LineMissileが縦*/
                karifield = evalNextField(field,new Point(x,y),ownType,true,false);
                if(max < fieldSum(karifield)){
                    max = fieldSum(karifield);
                    maxField = karifield;
                    maxPoint = new Point(x,y);
                    missile.setVertical();
                }
            }
        }
        field = maxField;
        fire(maxPoint.x,maxPoint.y);
        turn++;
    }
    /*Fieldの重み付け*/
    private int fieldSum(int[][] f){
        int sum = 0;
        for(int x=0; x<SIZE; x++){
            for(int y=0; y<SIZE; y++){
                sum += f[x][y];
            }
        }
        return sum;
    }
    
    /*重み付けの計算する*/
    private int[][] evalNextField(int[][] f,Point p,int type,boolean player,boolean muki){
        int[][] nextField = new int[SIZE][SIZE];
        for(int x=0; x<SIZE; x++){
            for(int y=0; y<SIZE; y++){
                nextField[x][y] = f[x][y];
            }
        }
        Point[] m = missiles[type];
        //ミサイルを打ったのが自身の場合
        if(player){
            for(int i = 0; i < m.length; i++){
                Point target;
                if(muki){
                    target = new Point(p.x + m[i].x , p.y + m[i].y);
                }else{
                    target = new Point(p.x + m[i].y, p.y + m[i].x);
                }
                if(target.x >= 0 && target.x < SIZE){
                    if(target.y >= 0 && target.y < SIZE){
                        nextField[target.x][target.y] = 100;
                    }
                }
            }
        }
        //ミサイルを打ったのが敵の場合
        else{
            for(int i = 0; i < m.length; i++){
                Point target;
                /*敵のLineMissileの向きが察知できないため、値は-50で計算。
                 ２回実行するので、計算には支障はない。*/
                target = new Point(p.x + m[i].x, p.y + m[i].y);
                if(target.x >= 0 && target.x < SIZE){
                    if(target.y >= 0 && target.y < SIZE){
                        nextField[target.x][target.y] += -50;
                    }
                }
                target = new Point(p.x + m[i].x, p.y + m[i].y);
                if(target.x >= 0 && target.x < SIZE){
                    if(target.y >= 0 && target.y < SIZE){
                        nextField[target.x][target.y] += -50;
                    }
                }
            }
        }
        return nextField;
    }
    
    /*誤差を計算*/
    private int[][] evalErrorNextField(int[][] f,Point p,int type){
        int[][] nextField = new int[SIZE][SIZE];
        for(int x=0; x<SIZE; x++){
            for(int y=0; y<SIZE; y++){
                nextField[x][y] = f[x][y];
            }
        }
        Point[] m = missiles[type];
        Point target;
        for(int i = 0; i < m.length; i++){
            int eNum = 0;
            /*敵が打ってきたミサイルの爆撃点の揺らぎを計算*/
            for(int x = -1;x <= 1;x++){
                for(int y = -1;y <= 1;y++){
                    target = new Point(p.x + m[i].x + x, p.y + m[i].y + y);
                    if(target.x >= 0 && target.x < SIZE){
                        if(target.y >= 0 && target.y < SIZE){
                            nextField[target.x][target.y] += -3*errorWeight[eNum];
                        }
                    }   
                    target = new Point(p.x + m[i].y + x, p.y + m[i].x + y);
                    if(target.x >= 0 && target.x < SIZE){
                        if(target.y >= 0 && target.y < SIZE){
                            nextField[target.x][target.y] += -3*errorWeight[eNum];
                        }
                    }   
                    eNum++;
                }
            }
        }
        return nextField;
    }
}