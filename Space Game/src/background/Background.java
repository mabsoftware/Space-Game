package background;

public class Background {
	Star[] stars;
	public Background(int numStars, int w, int h)
	{
		stars = new Star[numStars];
		for (int i = 0; i < stars.length; i++)
		{
			//setting the random size and locations of the stars
			stars[i] = new Star(Math.random() * w - w / 2, Math.random() * h - h / 2, Math.random() * 2);
		} // updated star generating function.
	}
	
	public Star[] getBackground()
	{
		return stars;
	}
}
