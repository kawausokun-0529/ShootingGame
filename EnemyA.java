import java.awt.*;

class EnemyA extends MovingObject{
    Image img = getToolkit().getImage("enemyA.png");//画像を読み込んでImageクラスのインスタンスに取り込む

    //constructor
    EnemyA(int apWidth, int apHeight){
        super(apWidth, apHeight);//親クラスのコンストラクタの呼び出し
        w = 12;
        h = 12;
        hp = 0;//初期状態
        point = 10;//倒したときに入る得点
    }

    //method
    void move(Graphics buf, int apWidth, int apHeight){//敵画像を使わないときのメソッド
        buf.setColor(Color.red);//色を赤に設定
        if(hp>0){//生きていれば
            //buf.setColor(Color.black);
            buf.drawOval(x-w, y-h, 2*w, 2*h);//〇を描く
            x = x + dx;//x座標の更新
            y = y + dy;//y座標の更新
            if(y>apHeight+h){
                hp = 0;
            }
        }
    }
    void move(Graphics gc, int apHeight){//敵画像を表示するメソッド
        if(hp>0){
            gc.drawImage(img, x-w, y-h, this);
            x = x + dx;
            y = y + dy;
            if(y>apHeight+h){
                hp = 0;
            }
        }
    }
    void revive(int apWidth, int apHeight){//敵を新たに生成する
        x = (int)(Math.random()*(apWidth-2*w)+w);
        y = -h;
        dy = 1;
        if(x<apWidth/2){
            dx = (int)(Math.random()*2);
        }
        else{
            dx = -(int)(Math.random()*2);
        }
        hp = 1;
    }
}
