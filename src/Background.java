import java.awt.image.BufferedImage;
/**This class maintains all the information for the background
 * 
 * @author Reilly
 *
 */
public class Background {
	private int[] imageIds = {36,40,41,42,43,44,45,46,47,37,38,39};//since City10 comes before City2 due to alphabetization, the order must be manually set up.
	public Background(){}
	public BufferedImage getImage()
	{
		int moment = ManagerPanel.universalPhase/2%12;//imageId for city.png
		return ManagerPanel.images[imageIds[moment]];
	}
}
