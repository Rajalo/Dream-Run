import java.awt.image.BufferedImage;
/**This deals with all the variables for me
 * 
 * @author Reilly
 *
 */
public class WhipperSnapper 
{
	private int height;
	private int lateral;
	private int mode;//whether I have the jacket or not
	public WhipperSnapper(int h, int l, int mode) 
	{
		height = h;
		lateral = l;
		this.mode = mode;
	}
	public BufferedImage getImage() //
	{
        int imageId = 0;
        switch (mode)
        {
        case 0:
        	imageId = 146;//imageId for whippersnapper.png
        	break;
        case 1:
        	imageId = 100;//imageId for longwhipper.png
        	break;
        }
        int moment = ManagerPanel.universalPhase/3%4+imageId;
    	return ManagerPanel.images[moment];
	}
	public void step()
	{
		lateral += (int)(Math.random()*8-4);
		height += (int)(Math.random()*8-4);
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
