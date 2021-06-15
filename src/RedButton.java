import java.awt.image.BufferedImage;
/**This class is the same as Green Button, it was just for organization.
 * 
 * @author Reilly
 *
 */
public class RedButton {
	private boolean isPressed = false;
	private int imageId;
	public RedButton()
	{
		imageId = 52; //imageId for Exit off.png
	}
	public void press()
	{
		isPressed = true;
	}
	public BufferedImage getImage()
	{
		if (isPressed)
		{
			imageId = 57;
		}
		int moment = (int)ManagerPanel.universalPhase/2%5+imageId;
    	return ManagerPanel.images[moment];
	}
}
