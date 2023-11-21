import java.awt.*;

//1.make flame
//2.thread progression

public class ShootingGame extends Frame implements Runnable{
    //field variable
    Thread th; //object(thread class)
    GameMaster gm; //class that progress game

    //main mathod
    public static void main(String[] args){
        new ShootingGame(); //make object of itself
    }

    //constructor
    ShootingGame(){
        super("Shooting Game (Sample)"); //call constructor of parent class
        int cW=800, cH=600; //canvas size
        this.setSize(cW+30, cH+40); //flame size
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); //put canvas of flame

        gm = new GameMaster(cW, cH); //make object (GameMaster class)
        this.add(gm); //put canvas on flame
        this.setVisible(true); //make visible

        th = new Thread(this); //make object(Thread class)
        th.start(); //start thread by start method

        requestFocusInWindow(); //get focus
    }

    //method (Runnable for interface)
    public void run(){
        try{
            while(true){ //infinity roop
                Thread.sleep(20); //break before renew window
                gm.repaint(); //repaint() calls update()
            }
        }
        catch(Exception e){
            System.out.println("Exception: " + e);
        }
    }
}
