import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.JPanel;

/**This class is made to schedule and render all objects on screen
 * @author Reilly
 *
 */
public class RenderPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Player Player = new Player(); //our protagonist
	private Background background = new Background(); //background
	private PlatformGenerator platformGen = new PlatformGenerator(); //platforms
	private WhipperSnapper whipper = new WhipperSnapper(300, 920, 1); //me
	public static int scoreTrail;
	public static Info info;
    public RenderPanel(){
        setBackground(Color.BLUE);
        setFocusable(true);
        scoreTrail =0;
        info = new Info();
    }
    //paints everything on screen
    public void paintComponent(Graphics g){ //does all the painting for the entire screen
        super.paintComponent(g);
   
		ImageObserver paintingChild = null;
		g.drawImage(background.getImage(), 0, 0, paintingChild);
		ArrayList<Platform> platforms = platformGen.getPlatList();
		for (int j = platforms.size()-1; j >= 0; j--) //platforms
		{
			for (int i =0; i < platforms.get(j).tiles().size(); i++)
			{
				g.drawImage(platforms.get(j).tiles().get(i).getImage(), platforms.get(j).tiles().get(i).getX(),platforms.get(j).tiles().get(i).getY(), paintingChild);
			}
			g.drawImage(platforms.get(j).getEnemy().getImage(), platforms.get(j).getEnemy().getX(), platforms.get(j).getEnemy().getY(), paintingChild);
			g.drawImage(platforms.get(j).getItem().getImage(), platforms.get(j).getItem().getX(), platforms.get(j).getItem().getY(), paintingChild);
			if (platforms.get(j).getHelper()!=null)
			{
				g.drawImage(platforms.get(j).getHelper().getImage(),platforms.get(j).getHelper().getX() , platforms.get(j).getHelper().getY(), null);
			}
		}
		if (ManagerPanel.score <= 1) //instructions
		{
			g.setColor(Color.BLACK);
			g.fillRect(395, 95, 266, 266);
			g.drawImage(spacebarImage(), 400, 100, paintingChild);
		}
		g.drawImage(Player.getImage(), Player.getX(), Player.getY(), paintingChild ); //Player
		g.drawImage(whipper.getImage(), whipper.getX(), whipper.getY(), paintingChild); //Whipper
		if ((ManagerPanel.score%40 >= 37&&ManagerPanel.universalPhase%2==0)||(ManagerPanel.score%40<scoreTrail%40&&ManagerPanel.universalPhase%2==0))//transition between elephant mode and normal
		{
			g.setColor(Color.cyan);
			g.fillOval(Player.getX(),Player.getY(),Player.getImage().getWidth(),Player.getImage().getHeight());
		}
		if (Player.getElephant() != null)
		{
			g.drawImage(Player.getElephant().getImage(), Player.getElephant().getX(), Player.getElephant().getY(), null);
		}
		info.paintInfo(g);
    }
    
    //tells Player to jump
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			Player.jump();
			if (!Player.canJump&&RenderPanel.info.getHelper())
			{
				RenderPanel.info.summon();
				for (int i = 0; i < platformGen.getPlatList().size();i++)
				{
					platformGen.getPlatList().get(i).addHelper();
				}
				Player.canJump = true;
			}
		}
		repaint();
	}
	//tells Player to stop jumping
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			Player.jumpReleased();
		}
		repaint();
	}
	public void PlayerCollision() //deals with all of the resting spots and possible deaths
	{
		double[] restStops = platformGen.getRestStops();
		if (restStops[0]-20>Player.getY())
		{
			Player.setResting((int)restStops[0]-20);
		}
		if (restStops[1]<restStops[0]&&restStops[1]-20>Player.getY())
		{
			Player.setResting((int)restStops[1]-20);
		}
		if (Math.abs(5+platformGen.getPlatList().get(0).getEnemy().getX()-Player.getX())<=50+((Player.ridingElephant)?50:0)&&Math.abs(platformGen.getPlatList().get(0).getEnemy().getY()-Player.getY())<=60+((Player.ridingElephant)?50:0))
		{
			Player.die();
			if (Player.ridingElephant)
			{
				platformGen.getPlatList().get(0).getEnemy().die();
			}
		}
		for (int i =0; i < platformGen.size();i++)
		{
			if (Math.abs(5+platformGen.getPlatList().get(i).getItem().getX()-Player.getX())<=100+((Player.ridingElephant)?100:0)&&Math.abs(platformGen.getPlatList().get(i).getItem().getY()-Player.getY())<=100+((Player.ridingElephant)?100:0))
			{
				platformGen.getPlatList().get(i).getItem().die();
			}
		}
		if (Player.getY()>600)
		{
			Player.die();
			ManagerPanel.nextState = true;
			Player.setY(0);
		}
	}
	//Tells everything to change itself
	public void step() {
		Player.step();
		whipper.step();
		PlayerCollision();
		platformGen.step();	
		repaint();
		scoreTrail += (scoreTrail == ManagerPanel.score)?0:1;
	}
	public BufferedImage spacebarImage() //instructions
	{
		int moment = (int)ManagerPanel.universalPhase/2%(6)+132;//imageId for spacebar.png
    	return ManagerPanel.images[moment];
	}

}