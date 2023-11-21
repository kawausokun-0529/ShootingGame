import java.awt.*;
import java.awt.event.*;

//keyboard input
//manage objects in game
//discribe game screen

public class GameMaster extends Canvas implements KeyListener{
    //field variable
    Image buf; //object for buffer (Image class)
    Graphics buf_gc; //object for graffics context of buffer
    Dimension d; //object manage size of aplet
    private  int imgW, imgH; //soze of canvas

    private int enmyAnum = 30; //number of enemyA
    private int enmyBnum = 10;
    private int ftrBltNum = 15; //number of bullet
    private int mode = -1; //-1:title -2:game over 1~:game stage
    private int i, j; 
    //int score = 0;

    Fighter ftr; //fighter
    FighterBullet ftrBlt[] = new FighterBullet[ftrBltNum]; //fighter's bullet
    EnemyA enmyA[] = new EnemyA[enmyAnum]; //enemyA
    EnemyB enmyB[] = new EnemyB[enmyBnum];

    //Image img = getToolkit().getImage("img/rocket.png");

    //constructor
    GameMaster(int imgW, int imgH){
        this.imgW = imgW;
        this.imgH = imgH;
        setSize(imgW, imgH);

        addKeyListener(this);

        ftr = new Fighter(imgW, imgH); //make fighter object
        for(i=0; i<ftrBltNum; i++){ //make bullet objects
            ftrBlt[i] = new FighterBullet();
        }
        for(i=0; i<enmyAnum; i++){ //make enemyA objects
            enmyA[i] = new EnemyA(imgW, imgH);
        }
        for(i=0; i<enmyBnum; i++){
            enmyB[i] = new EnemyB(imgW, imgH);
        }
    }

    //method
    public void addNotify(){
        super.addNotify();
        buf = createImage(imgW, imgH);
        buf_gc = buf.getGraphics();
    }

    //method
    public void paint(Graphics g){
        buf_gc.setColor(Color.black); //gc => white
        buf_gc.fillRect(0, 0, imgW, imgH); //draw white rectangle by gc
        switch(mode){
            case -2: //game over screen (space -> title)
                buf_gc.setColor(Color.white); //characters' color
                buf_gc.drawString("    == Game over ==    ", imgW/2-80, imgH/2-20);
                buf_gc.drawString("    Hit SPACE key    ", imgW/2-80, imgH/2+20);
                break;
            case -1: //title screen (space -> game start)
                buf_gc.setColor(Color.white); //characters' color
                buf_gc.drawString(" == Shooting Game Title ==", imgW/2-80, imgH/2-20);
                buf_gc.drawString("Hit SPACE bar to start game", imgW/2-80, imgH/2+20);
                break;
            default: //during game
                makeEnmyA:if(Math.random() < 0.1){ //make enemy 10% randomly
                    for(i=0; i<enmyAnum; i++){
                        if(enmyA[i].hp == 0){
                            enmyA[i].revive(imgW,imgH);
                            break makeEnmyA;
                        }
                    }
                }
                makeEnmyB:if(Math.random() < 0.05){
                    for(i=0; i<enmyBnum; i++){
                        if(enmyB[i].hp == 0){
                            enmyB[i].revive(imgW, imgH);
                            break makeEnmyB;
                        }
                    }
                }

                //fire bullet
                if(ftr.sflag == true && ftr.delaytime == 0){ // if pushed space && waiting time = 0
                    for(i=0; i<ftrBltNum; i++){
                        if(ftrBlt[i].hp == 0){
                            ftrBlt[i].revive(ftr.x, ftr.y);
                            ftr.delaytime = 5;
                            break;
                        }
                    }
                }else if(ftr.delaytime>0){
                    ftr.delaytime--;
                }

                //check collision between objects
                for(i=0;i<enmyAnum; i++){
                    if(enmyA[i].hp>0){
                        ftr.collisionCheck(enmyA[i], 0);
                        for(j=0; j<ftrBltNum; j++){
                            if(ftrBlt[j].hp>0){
                                ftrBlt[j].collisionCheck(enmyA[i], ftr, enmyA[i].point);
                            }
                        }
                    }
                }
                for(i=0; i<enmyBnum; i++){
                    if(enmyB[i].hp>0){
                        ftr.collisionCheck(enmyB[i], 0);
                        for(j=0; j<ftrBltNum; j++){
                            if(ftrBlt[j].hp>0){
                                ftrBlt[j].collisionCheck(enmyB[i], ftr, enmyB[i].point);
                            }
                        }
                    }
                }

                //check dead or alive
                if(ftr.hp<1){
                    mode = -2; //game over
                }

                //describe & move object
                for(i=0; i<enmyAnum; i++){
                    enmyA[i].move(buf_gc, imgH);
                }
                for(i=0; i<enmyBnum; i++){
                    enmyB[i].move(buf_gc, imgW, imgH);
                }
                for(i=0; i<ftrBltNum; i++){
                    ftrBlt[i].move(buf_gc, imgW, imgH);
                }
                ftr.move(buf_gc);

                //check situation
                /*for(i=0; i<enmyAnum; i++){
                    System.out.print(enmyA[i].hp + " ");
                }*/
                /*for(i=0; i<enmyBnum; i++){
                    System.out.print(enmyB[i].hp + " ");
                }*/
                //System.out.println("");

                buf_gc.setColor(Color.white); //characters' color
                buf_gc.drawString("HP : " + ftr.hp, 20, 20);
                buf_gc.drawString("score : " + ftr.score, 20, 50);
        }

        g.drawImage(buf, 0, 0, this); //paste buffer on canvas
    }

    //method (Canvas)
    public void update(Graphics gc){ //called by repaint()
        paint(gc);
    }

    //method (KeyListener)
    public void keyTyped(KeyEvent ke){
    } //dont

    public void keyPressed(KeyEvent ke){
        int cd = ke.getKeyCode();
        switch(cd){ //flag up
            case KeyEvent.VK_LEFT: // <- key
                ftr.lflag = true;
                break;
            case KeyEvent.VK_RIGHT: // -> key
                ftr.rflag = true;
                break;
            case KeyEvent.VK_UP: // ^ key
                ftr.uflag = true; //|
                break;
            case KeyEvent.VK_DOWN: // | key
                ftr.dflag = true; //  v
                break;
            case KeyEvent.VK_SPACE: // space key
                ftr.sflag = true;
                if(this.mode != 1){
                    this.mode++;
                }
                ftr.hp = 10;
                break;
        }
    }

    //method (KeyListener)
    public void keyReleased(KeyEvent ke){
        int cd = ke.getKeyCode();
        switch(cd){ // flag down
            case KeyEvent.VK_LEFT:
                ftr.lflag = false;
                break;
            case KeyEvent.VK_RIGHT:
                ftr.rflag = false;
                break;
            case KeyEvent.VK_UP:
                ftr.uflag = false;
                break;
            case KeyEvent.VK_DOWN:
                ftr.dflag = false;
                break;
            case KeyEvent.VK_SPACE:
                ftr.sflag = false;
                break;
        }
    }
}
