import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
public class PlatformGap
{

    public int OffsetX = 0;
    int PipeThickness = 40;
    int type = 0;

    Rectangle p1 = new Rectangle(0, 0, 0, 0);
    Rectangle p2 = new Rectangle(0, 0, 0, 0);

    public PlatformGap(int t)
    {
        type = t;
    }

    public void RenderPipe(Graphics g)
    {
    	g.setColor(Color.GREEN);
        ImageIcon b = new ImageIcon("master.png");
        Image image = b.getImage();
        g.setColor(Color.BLACK);
        g.drawImage(image, p1.x, p1.y, 70,70,null);
        g.drawImage(image, p1.x, p1.y, 70,70,null);
        
        
    }

    public boolean IsPlayerCollides(ZombieRun p)
    {
        if(p.bounds.intersects(p1) || p.bounds.intersects(p2))
        {
            return true;
        }
        return false;
    }
    
    /*public boolean IsScored(scorePoint l)
    {
    	if(l.score.intersects(p1))
        {
            return true;
        }
        return false;
    }*/
    

    public void UpdatePipe(){
        if(type == 0){
            p1 = new Rectangle(OffsetX, 0, PipeThickness, 20);
        }
        else if(type == 1){
            p1 = new Rectangle(OffsetX, 57, PipeThickness, 20);
        }
        else if(type == 2){
            p1 = new Rectangle(OffsetX, 114, PipeThickness, 20);
        }
         else if(type == 3){
            p1 = new Rectangle(OffsetX, 171, PipeThickness, 20);
        }
         else if(type == 4){
            p1 = new Rectangle(OffsetX, 228, PipeThickness, 20);
        }
         else if(type == 5){
            p1 = new Rectangle(OffsetX, 285, PipeThickness, 20);
        }
         else if(type == 6){
            p1 = new Rectangle(OffsetX, 342, PipeThickness, 20);
         }
         else if(type == 7){
            p1 = new Rectangle(OffsetX, 399, PipeThickness, 20);
         }
    }


}
