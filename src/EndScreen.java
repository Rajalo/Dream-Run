import java.awt.image.BufferedImage;
/**This class works just like Background and Title, but for the Endscreen
 * 
 * @author Reilly
 *
 */
public class EndScreen {
	public EndScreen(){}
	public BufferedImage getImage()
	{
		int moment = ManagerPanel.universalPhase/2%4+48;
		return ManagerPanel.images[moment];
	}
}
