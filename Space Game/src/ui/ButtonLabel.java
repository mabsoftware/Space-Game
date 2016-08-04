// Helper class for Button.java.
package ui;

import acm.graphics.GLabel;
import java.awt.Color;

public class ButtonLabel extends GLabel
{
	public ButtonLabel(Button a)
	{
		super(a.getLabel());
		this.setColor(Color.WHITE);
		this.setFont("fantasy-bold-48");
		this.setLocation(a.getX() + ((a.getWidth() - this.getWidth()) / 2), a.getY() + (a.getHeight() /*- this.getHeight()) / 2*/));

	}
}