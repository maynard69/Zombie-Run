import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class MainGame extends JPanel implements KeyListener{

    //Variables for the game objects.
    ZombieRun player = new ZombieRun();
    Rectangle floor;
    Rectangle ceil;
    int asd=350;

    //List of active pipes.
    ArrayList<PlatformGap> pipes = new ArrayList<PlatformGap>();

    PlatformGap sample = new PlatformGap(0);

    int GameState = 0;
    //0 = idle
    //1 = play
    //2 = game over

    boolean[] keys = new boolean[256];

    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e){
        keys[e.getKeyCode()] = true;
        System.out.println(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e){
        keys[e.getKeyCode()] = false;
    }

    public MainGame(){
        addKeyListener(this);
        setFocusable(true);
        Thread td = new Thread(new Runnable(){
           public void run(){
               setup();
               while(true){
                   try {
                       update();
                       render();
                       Thread.sleep(16);
                   } catch(Exception ex){

                   }
               }
           }
        });
        td.start();
    }

    void setup(){
        //Position player.
        player.bounds.x = 20;
        player.bounds.y = 290;

    }

    //variables for generating pipe
    int interval = 100;
    int step = 0;

    void update(){

        getInput();

        //prepare floor
        floor = new Rectangle(0,390,getWidth(),10);
        
        

        if(step >= interval){
            AddEnemy();
            step = 0;
        }
        step++;
        
    
        		

        //Game PLAY
        //Move ball.
        if(GameState == 1) {
      //Render pipes
      if(keys[38]){	
        	player.bounds.y-=10;
        		}
        else if(keys[40]){
        player.bounds.y+=10;
        		}
      if(player.bounds.y<0)
 			{
 				player.bounds.y=0;
 			}
		else if (player.bounds.y>320)
			{
 				player.bounds.y=320;
 			}
            for(PlatformGap x : pipes){
                x.OffsetX -= 5;
                x.UpdatePipe();
               
            }
        }
        //Check if game over - hits floor
   			
   			
        //Check if game over - hits pipe
        for(PlatformGap x : pipes) {
            if (x.IsPlayerCollides(player)) {
                player.bounds.y = 290;
                pipes.clear();
                GameState = 2 ;
            }
            
            if (x.IsPlayerCollides(player)) {
                player.bounds.y = 290;
                pipes.clear();
                GameState = 2 ;
            }
        }

        RemoveOffscreenPipe();
    }



    void RemoveOffscreenPipe(){
        int toRemove = -1;
        for(PlatformGap x : pipes){
            if(x.OffsetX < -x.PipeThickness){
                toRemove = pipes.indexOf(x);
            }
        }
        if(toRemove > -1) pipes.remove(toRemove);
    }

    boolean spacedown = false;
    void getInput(){
        //Spacebar.
        if(keys[32]){
            if(GameState == 0) {
                if(spacedown == false) GameState = 1;
            }
            else if(GameState == 2) {
                spacedown = true;
                player.bounds.x = 20;
                GameState = 0;
            }
        } else if(keys[32] == false){
            spacedown = false;
        }
    }

    void render(){
        repaint();
    }

    void AddEnemy(){
        Random rd = new Random();
        int r = rd.nextInt(8);
        System.out.println(r);
        PlatformGap p = new PlatformGap(r);
        //set pipe to rightmost corner
        p.OffsetX = 600;
        pipes.add(p);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //Render player
        g.fillOval(player.bounds.x, player.bounds.y, player.bounds.width, player.bounds.height);
        ImageIcon a = new ImageIcon("marlou.jpg");
        Image imga = a.getImage();
		g.drawImage(imga,player.bounds.x, player.bounds.y, player.bounds.width, player.bounds.height,null);
        
        //Render pipes
        for(PlatformGap x : pipes){
            x.RenderPipe(g);
        }

        //Start screen
        if(GameState == 0){
            Font fnt = new Font("Arial", Font.BOLD, 20);
            g.setFont(fnt);
            g.setColor(Color.BLACK);
            g.drawString("TAP TO START", getWidth() / 2 - 60, getHeight() / 2);
        }
        

        //Dim screen
        if(GameState == 2) {
            //Dim screen
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());

            //ADd game over message
            Font fnt = new Font("Arial", Font.BOLD, 20);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER", getWidth() / 2 - 60, getHeight() / 2);

        }

    }

    public static void main(String[] args){
        JFrame wnd =  new JFrame("Zombie Run");
        wnd.setSize(600, 400);
        wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wnd.setContentPane(new MainGame());
        wnd.setVisible(true);
        wnd.setResizable(false);
    }


}
