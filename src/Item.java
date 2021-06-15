import java.awt.image.BufferedImage;

/**This makes the enemies
 * 
 * @author Reilly
 *
 */
public class Item 
{
	private int height;//y
	private ItemType type;//type
	private int lateral;//x
	private double lateralSpeed;
	private double verticalSpeed;
	public Item(int h, int l, int ls) 
	{
		height = (int) (h-100-(200*Math.random()));
		lateral = l;
		lateralSpeed = ls;
		int rand = (int)(2*Math.random());
		switch (rand) //chooses the item to display
		{
		case 0:
			type = ItemType.PEPPER;
			break;
		case 1:
			type = ItemType.ICECREAM;
			break;
		}
	}
	public BufferedImage getImage() //gives image of item
	{
        int imageId = 0;
        switch (type)
        {
        case ICECREAM:
        	imageId = 156;
        	break;
        case PEPPER:
        	imageId = 160;
        	break;
        }
        int moment = ManagerPanel.universalPhase/2%(4)+imageId;
    	return ManagerPanel.images[moment];
	}
	public void step() //steps
	{
		lateral += lateralSpeed;
		height += verticalSpeed;
	}
	public void die() //throws the item off stage in elephant mode
	{
		verticalSpeed = -3000;
		switch (type)
		{
		case PEPPER:
			PlatformGenerator.scoreMultiplier++;
			PlatformGenerator.speed *= 1.1;
			RenderPanel.info.addPepper();
			break;
		case ICECREAM:
			ManagerPanel.score++;
			PlatformGenerator.speed /= 1.1;
			RenderPanel.info.addIceCream();
			break;
		}
	}
	public int getX()
	{
		return lateral;
	}
	public int getY()
	{
		return height;
	}
	enum ItemType 
	{ 
	    ICECREAM,PEPPER; 
	} 
}
