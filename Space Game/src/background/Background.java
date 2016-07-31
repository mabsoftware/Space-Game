package background;

public class Background {
	Star[] stars;
	public Background(int numStars, int w, int h)
	{
		stars = new Star[numStars];
		for (int i = 0; i < stars.length; i++)
		{
			stars[i] = new Star(Math.random() * w, Math.random() * h, Math.random() * 2);
		}
	}
	
	public Star[] getBackground()
	{
		return stars;
	}
}
