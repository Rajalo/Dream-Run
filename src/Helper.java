import java.awt.image.BufferedImage;


/**This makes the enemies
 * 
 * @author Reilly
 *
 */
public class Helper 
{
	private int height;//y
	private int lateral;//x
	private double lateralSpeed;
	private double verticalSpeed;
	public Helper(int h) 
	{
		height = h-20;
		lateral = -100;
		lateralSpeed = 20* Math.sqrt(PlatformGenerator.scoreMultiplier);
	}
	public BufferedImage getImage() //gives image of helper
	{
        int imageId = 171;
        int moment = ManagerPanel.universalPhase/2%(4)+imageId;
    	return ManagerPanel.images[moment];
	}
	public void step() //steps
	{
		lateral += lateralSpeed;
		height += verticalSpeed;
		
	}
	public int getX()
	{
		return lateral;
	}
	public int getY()
	{
		return height;
	}
}
