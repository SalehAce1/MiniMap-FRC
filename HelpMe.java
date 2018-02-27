import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

final public class HelpMe 
{

    JFrame frame;
    DrawPanel drawPanel;
    private double oneX = 479;
    private double oneY = 207;
    private double pos = 0;

    public static void main(String[] args) 
    {
        new HelpMe().go();
    }

    private void go() 
    {

    	
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(958, 414);
        frame.setLocation(0, 0);
        moveIt();
    }

    class DrawPanel extends JPanel 
    {
        public void paintComponent(Graphics g) 
        {
        	BufferedImage arena = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        	BufferedImage robot = new BufferedImage(100,100,BufferedImage.TYPE_INT_ARGB);
        	try 
        	{
        		arena = ImageIO.read(new File ("image.jpg"));
				robot = ImageIO.read(new File ("robot.png"));
			} catch (IOException e) 
        	{
				e.printStackTrace();
			}
        	g.drawImage(arena, 0, 0, 958, 394, this);
        	g.drawImage(robot, (int)oneX, (int)oneY, 30, 30, this);
        }
    }

    private void moveIt() 
    {
    	NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.14.93.2");
		NetworkTable table = NetworkTable.getTable("SmartDashboard");
		
        while(true)
        {
        	
        	oneX = table.getNumber("xpos", 0);
        	oneY = table.getNumber("ypos", 0);
        	
//        	pos = table.getNumber("pos",0);
//        	pos = 0;
//        	if (pos == 0)
//        	{
//        		
//        	}
//        	else if (pos==1)
//        	{
//                oneX = 800;
//                oneY = 180;
//        	}
//        	else if (pos==2)
//        	{
//        		
//        	}
        	
            try
            {
                Thread.sleep(10);
            } catch (Exception exc){}
            frame.repaint();
        }
    }
}

