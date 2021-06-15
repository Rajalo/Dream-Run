import java.util.ArrayList;
/**Generates the platforms
 * 
 * @author Reilly
 *
 */
public class PlatformGenerator {
	private ArrayList<Platform> platList = new ArrayList<Platform>(2);
	public static double speed;
	public static double scoreMultiplier;
	private double[] restStops = {0,0};
	public PlatformGenerator()
	{
		speed = -10;
		scoreMultiplier = 1;
		platList.add(new Platform(400,4,(int)speed,0)); //first platform is always the same
	}
	public void step() //steps through platform animations and lets Player know where to land
	{
		if (platList.get(0).getX()<40&&platList.get(0).getX()+platList.get(0).getLength()>100)
		{
			restStops[0] = platList.get(0).getY();
		}
		else
		{
			restStops[0] = 1000;
		}
		if (platList.size()>1&&platList.get(1).getX()<40&&platList.get(1).getX()+platList.get(1).getLength()>100)
		{
			restStops[1] = platList.get(1).getY();
		}
		else
		{
			restStops[1] = 1000;
		}
		
		if (platList.get(0).getX()<-200&&platList.get(0).getFertility())
		{
			int nextHeight = (platList.get(platList.size()-1).getY()+(int)(300*Math.random()-150))%400+150;
			platList.add(new Platform(nextHeight,(int)(3*Math.random()+(Math.sqrt(-1*speed))),(int)speed));
			platList.get(0).setFertility(false);
			ManagerPanel.score += scoreMultiplier;
		}
		if (platList.get(0).getX()+platList.get(0).getLength() <=0)
		{
			platList.set(0,null);
			platList.remove(0);
		}
		speed *= 1.001; //to make it get faster
		for (int i =0; i < platList.size();i++)
		{
		platList.get(i).step();
		}
	}
	public double[] getRestStops() //accessors
	{
		return restStops;
	}
	public ArrayList<Platform> getPlatList()
	{
		return platList;
	}
	public int size()
	{
		return platList.size();
	}
}
