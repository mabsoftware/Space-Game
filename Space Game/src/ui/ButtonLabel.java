package ui;

import acm.graphics.GLabel;

public class ButtonLabel extends GLabel
{
	public ButtonLabel(Button a)
	{
		super(a.getLabel(), a.getX(), a.getY());
	}
}
