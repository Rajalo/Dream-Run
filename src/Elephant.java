import java.awt.image.BufferedImage;

//Class that deals with if theres an elephant on screen
public class Elephant {
	private int height;
	private int lateral;
	public Elephant(int h, int l)
	{
		height = h;
		lateral = l;
	}
	public int getY()
	{
		return height;
	}
	public int getX()
	{
		return lateral;
	}
	public void step()
	{
		height += 30;
	}
	public BufferedImage getImage()
	{
		return ManagerPanel.images[181+ManagerPanel.universalPhase%3];
	}
}
