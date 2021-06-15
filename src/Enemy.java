import java.awt.image.BufferedImage;
/**This makes the enemies
 * 
 * @author Reilly
 *
 */
public class Enemy 
{
	private int height;//y
	private EnemyType type;//type
	private int lateral;//x
	private double lateralSpeed;
	private double verticalSpeed;
	public Enemy(int h, int l, int ls) 
	{
		height = h-20;
		lateral = l;
		lateralSpeed = ls - 4;
		int rand = (int)(4*Math.random());
		switch (rand) //chooses the enemy to display
		{
		case 0:
			type = EnemyType.WILLOW;
			break;
		case 1:
			type = EnemyType.GOBLIN;
			break;
		case 2:
			type = EnemyType.MEDO;
			break;
		case 3:
			type = EnemyType.THERMO;
			break;
		}
	}
	public BufferedImage getImage() //gives image of enemy
	{
        int imageId = 0;
        switch (type)
        {
        case WILLOW:
        	imageId = 32;
        	break;
        case GOBLIN:
        	imageId = 70;
        	break;
        case MEDO:
        	imageId = 104;
        	break;
        case THERMO: 
        	imageId = 138;
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
	public void die() //throws the enemy off stage in elephant mode
	{
		verticalSpeed = -30;
		lateralSpeed = 2*Math.abs(lateralSpeed);
		ManagerPanel.score++;
	}
	public int getX()
	{
		return lateral;
	}
	public int getY()
	{
		return height;
	}
	enum EnemyType 
	{ 
	    WILLOW, GOBLIN, MEDO, THERMO; 
	} 
}
