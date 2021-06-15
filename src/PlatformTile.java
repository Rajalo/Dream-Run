import java.awt.image.BufferedImage;
/**This class manages all the variables in a platform tile
 * 
 * @author Reilly
 *
 */
public class PlatformTile {
	private int x;
	private int y;
	private TileType type;
	private int velocity;
	private int randNum = (int)(3*Math.random()); //for the walls
	public PlatformTile(int y, int x, PlatformTile.TileType type, int speed)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		velocity = speed;
	}
	public BufferedImage getImage() //returns the image for this tile
	{
        int imageId =0;
        switch (type)
        {
        case FRONTTOP:
        	imageId = 116; //imageId for platformfront.png
        	break;
        case TOP:
        	imageId = 124;//imageId for platformmiddle.png
        	break;
        case BACKTOP:
        	imageId = 108;//imageId for platformback.png
        	break;
        case FRONTUNDER: 
        	imageId = 62;//imageId for frontunder.png
        	break;
        case UNDER:
        	imageId =  randNum*8; //imageId for under.png
        	break;
        case BACKUNDER:
        	imageId = 24; //imageId for backunder.png
        	break;
        }
        int moment = ManagerPanel.universalPhase/2%(8)+imageId;
        return ManagerPanel.images[moment];
	}
	public int getX() //accessors
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void step() {
		x += velocity;
	}
	enum TileType 
	{ 
	    FRONTTOP, TOP, BACKTOP, FRONTUNDER, UNDER, BACKUNDER; 
	} 
}
