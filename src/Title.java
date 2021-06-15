import java.awt.image.BufferedImage;
/**Manages the Title animation
 * 
 * @author Reilly
 *
 */
public class Title {
	public Title(){}
	public BufferedImage getImage() //returns image to be drawn for title
	{
		int moment = (int)ManagerPanel.universalPhase/2%4+142;
    	return ManagerPanel.images[moment];
	}
}
