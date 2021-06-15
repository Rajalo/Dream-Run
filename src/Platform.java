
import java.util.ArrayList;

/**This class manages the variables for a platform comprised of platform tiles
 * 
 * @author Reilly
 *
 */
public class Platform{
	private int height;
	private Enemy enemy; //the enemy on stage
	private Item item; //the item on stage
	private int length;
	private int lateral = 1000;
	private int lateralSpeed;
	private boolean isFertile = true;
	private boolean restless = true;
	private int visible = 0;
	private int rows;
	private Helper helper = null;
	private ArrayList<PlatformTile> tiles = new ArrayList<PlatformTile>();//sets up an ArrayList for tiles 
	public Platform(int h, int l, int speed) 
	{
		height = h;
		lateralSpeed = speed;
		rows = (600-128-height)/128+1; //number of rows of tiles to render
		tiles.add(new PlatformTile(height,lateral, PlatformTile.TileType.FRONTTOP,lateralSpeed));
		for (int i = 0; i < rows; i++)
		{
			tiles.add(new PlatformTile(height+(i*256)+128,lateral, PlatformTile.TileType.FRONTUNDER,lateralSpeed));
		}
		length = 256*(l+1);
		enemy = new Enemy(height,lateral+length,lateralSpeed);
		item = new Item(height,lateral+length/2,lateralSpeed);
	}
	Platform(int h, int l, int speed,int lat) //this is for the animation
	{		
		lateral = lat; //I tried this(), but it requires the rewrite of lat, which would have to come after.
		height = h;
		length = l;
		lateralSpeed = speed;
		int rows = (600-128-height)/128+1;
		for (int i = -1; i < rows; i++)
		{
			if (i == -1)
			{
				tiles.add(new PlatformTile(height,lateral, PlatformTile.TileType.FRONTTOP,lateralSpeed));
				for (int j = 1; j < length+1; j++)
				{
					tiles.add(new PlatformTile(height, lateral+256*j, PlatformTile.TileType.TOP,lateralSpeed));
				}
				tiles.add(new PlatformTile(height, lateral +256*(length+1),PlatformTile.TileType.BACKTOP,lateralSpeed));
			}
			else 
			{
				tiles.add(new PlatformTile(height+(i*256)+128,lateral, PlatformTile.TileType.FRONTUNDER,lateralSpeed));
				for (int j = 1; j < length+1; j++)
				{
					tiles.add(new PlatformTile(height+(i*256)+128,lateral+256*j, PlatformTile.TileType.UNDER,lateralSpeed));
				}
				tiles.add(new PlatformTile(height+(i*256)+128, lateral +256*(length+1),PlatformTile.TileType.BACKUNDER,lateralSpeed));
			}
		}
		length = 256*(length+1);
		enemy = new Enemy(height,lateral+length,lateralSpeed);
		item = new Item(height,lateral+length/2,lateralSpeed);

	}
	public void addColumn() //sets up a new  column to be rendered
	{
		if (visible == length/256-1) //end bit
		{
			tiles.add(new PlatformTile(height, lateral +256*(visible+1),PlatformTile.TileType.BACKTOP,lateralSpeed));
			for (int i = 0; i < rows; i++)
			{
				tiles.add(new PlatformTile(height+(i*256)+128, lateral +256*(visible+1),PlatformTile.TileType.BACKUNDER,lateralSpeed));
			}
		}
		else
		{
			tiles.add(new PlatformTile(height, lateral+256*(visible+1), PlatformTile.TileType.TOP,lateralSpeed));
			for (int i = 0; i < rows; i++)
			{
				tiles.add(new PlatformTile(height+(i*256)+128,lateral+256*(visible+1), PlatformTile.TileType.UNDER,lateralSpeed));
			}
		}
		visible++;
	}
	public int getLength() //exactly what it says for the accessors
	{
		return length;
	}
	public Enemy getEnemy()
	{
		return enemy;
	}
	public int getX()
	{
		return lateral;
	}
	public int getY()
	{
		return height;
	}
	public void step() { //steps through animation
		lateral += lateralSpeed;
		for (PlatformTile tile : tiles)
		{
			tile.step();
		}
		enemy.step();
		item.step();
		if ((1150-lateral)/256>visible&&visible<length/256)
		{
			addColumn();
		}
		if (helper != null)
		{
			helper.step();
			if (Math.abs(helper.getX()-enemy.getX()+20)<100)
			{
				enemy.die();
			}
		}
	}
	public ArrayList<PlatformTile> tiles()
	{
		return tiles;
	}
	public void setFertility(boolean bool) //to make sure there arent infinity platforms
	{
		isFertile = bool;
	}
	public boolean getFertility()
	{
		return isFertile;
	}
	enum TileType 
	{ 
	    FRONTTOP, TOP, BACKTOP, FRONTUNDER, UNDER, BACKUNDER; 
	}
	public boolean getRestlesnness() {
		return restless;
	}
	public Item getItem() {
		
		return item;
	} 
	public void addHelper()
	{
		helper = new Helper(height);
	}
	public Helper getHelper() {
		// TODO Auto-generated method stub
		return helper;
	}
}
