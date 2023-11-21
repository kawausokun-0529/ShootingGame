import java.awt.*;

//ゲームに登場するすべての移動オブジェクトに共通の親クラス

abstract class MovingObject extends Frame{ //抽象クラス
    //フィールド変数
    int x; //中心座標
    int y;
    int dx; //速度
    int dy;
    int w; //横幅の半分
    int h; //縦幅の半分
    int hp; //HP(ゼロ以下で死亡扱い)
    int score, point;//得点計算用の変数

    //コンストラクタ1
    MovingObject(){
    }
    //コンストラクタ2
    MovingObject(int width, int height){//描画領域の大きさを引数に、出現の初期値をランダムに指定
        x = (int) (Math.random()*width);//画面内でランダム
        y = (int) (Math.random()*height);
        dx = (int) (Math.random()*6-3);//-3~3の範囲でランダム
        dy = (int) (Math.random()*6-3);
        w = 2;
        h = 2;
        hp = 10;
        score = 0;
        point = 0;
    }

    abstract void move(Graphics buf, int apWidth, int apHeight);
    //オブジェクトを動かし、位置を更新する抽象メソッド

    abstract void revive(int w, int h);
    //オブジェクトを再利用する抽象メソッド

    boolean collisionCheck(MovingObject obj, Fighter ftr, int point){
        //引数は相手のオブジェクト、獲得した得点、相手を倒したときの得点
        if(Math.abs(this.x-obj.x) <= (this.w+obj.w) && Math.abs(this.y-obj.y) <= (this.h+obj.h)){
            this.hp--;//自分のHPを減らす
            obj.hp--;//相手のHPを減らす
            ftr.score = ftr.score + point;//得点計算
            return true;
        }else{
            return false;
        }
    }
}
