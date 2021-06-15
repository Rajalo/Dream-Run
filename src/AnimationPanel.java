import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
/**This class is used to render the animation that plays right before the game starts
 * 
 * @author Reilly
 *
 */
public class AnimationPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int[][] horizontalWaypoints = {{100,100,-100},{100,100,140},{100,920,960}}; //these arrays hold turning points for each of the three objects in the animation
	private int[][] verticalWaypoints = {{350,355,500},{350,355,400},{350,250,300}};
	private int[] horizontalPositions = {100,100,-100}; //these are used to store current conditions, currently set to the starts
	private int[] verticalPositions = {350,355,500};
	private int frameNum; //keeps track of which frame it is between waypoints
	private int waypointNum; //keeps track of which waypoint we crossed last
	
	private Background background = new Background();
	private Platform platform = new Platform(400,6,-1,-30); //creates the platform for the background
	private WhipperSnapper whipper = new WhipperSnapper(500,-100 , 0); //creates me
	
	public AnimationPanel(){}
	
	public void paintComponent(Graphics g) //paints all of the objects on screen
	{
		super.paintComponent(g);
		ImageObserver paintingChild = null;
		g.drawImage(background.getImage(), 0, 0, paintingChild);
		for (int i =0; i < 16; i++) //draws platform
		{
			g.drawImage(platform.tiles().get(i).getImage(),platform.tiles().get(i).getX(),platform.tiles().get(i).getY(), paintingChild);
		}
		
		g.drawImage(PlayerImage(), horizontalPositions[0], verticalPositions[0], paintingChild);
		g.drawImage(jacketImage(), horizontalPositions[1], verticalPositions[1], paintingChild);
		g.drawImage(whipper.getImage(), horizontalPositions[2], verticalPositions[2], paintingChild);
		if (waypointNum == 1) //creates the exclamation points surrounding Player at the beginning
		{
			double rand1 = Math.random();
			double rand2 = Math.random();
			double rand3 = Math.random();
			g.setColor(Color.BLACK);
		    g.setFont(new Font("Courier New",Font.BOLD,60));
		    g.drawString("!", 125 +(int)(50*rand1-25), 335+ (int)(30*rand1-10));
		    g.drawString("!", 120 +(int)(50*rand2-25), 335+ (int)(30*rand2-10));
		    g.drawString("!", 135 +(int)(50*rand3-25), 335+ (int)(30*rand3-10));
			g.setColor(Color.RED);
		    g.setFont(new Font("Courier New",Font.BOLD,50));
		    g.drawString("!", 130 +(int)(50*rand1-25), 340+ (int)(30*rand1-10));
		    g.drawString("!", 125 +(int)(50*rand2-25), 340+ (int)(30*rand2-10));
		    g.drawString("!", 140 +(int)(50*rand3-25), 340+ (int)(30*rand3-10));
		}
	}
	public void step()
	{
		frameNum = (frameNum+1)%60;
		waypointNum += (frameNum == 0)?1:0; //goes to next waypoint once frameNum passes 60
		if (waypointNum == 2) //tells Manager Panel to switch to the game
		{
			ManagerPanel.nextState = true;
		}
		else
		{
		for (int i = 0; i < 3; i++)//adjusts the positions for each object
		{
			horizontalPositions[i] += (int)(1/50.0*(horizontalWaypoints[waypointNum+1][i]-horizontalWaypoints[waypointNum][i]));
			verticalPositions[i] += (int)(1/50.0*(verticalWaypoints[waypointNum+1][i]-verticalWaypoints[waypointNum][i]));
		}
		platform.step();
		whipper.step();
		repaint();
		}
	}
	public BufferedImage jacketImage() //returns image to draw for the jacket
	{
		int moment = (int)frameNum%4+84; //imageId for jacket
		return ManagerPanel.images[moment];
	}
	public BufferedImage PlayerImage() //returns image to draw for Player
	{
		int moment;
		if (waypointNum== 0)
		{
			moment = (int)frameNum%4+88;//imageId for Player normal
		}
		else
		{
			moment = 88; //makes him stand still
		}
    	return ManagerPanel.images[moment];
	}
}