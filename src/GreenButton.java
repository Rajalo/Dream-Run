import java.awt.image.BufferedImage;
/**This class manages the variables for all the green buttons
 * 
 * @author Reilly
 *
 */
public class GreenButton {
	private boolean isPressed = false;
	private int imageId;
	public GreenButton()
	{
		imageId = 74; //imageId for Go button off.png
	}
	public void press()//presses the button
	{
		isPressed = true;
	}
	public BufferedImage getImage()
	{
		if (isPressed)
		{
			imageId = 79;//imageId for Go button on.png
		}
		int moment = (int)ManagerPanel.universalPhase/2%(5)+imageId;
		return ManagerPanel.images[moment];
	}
}
