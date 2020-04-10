// November 13, 1996
// Andras Niedermayer

import java.awt.*;

class GuessButton extends CircleColorButton
{
	private int actualColor = 0;

	public GuessButton()
	{
		super();
		setFillColor(GuessColors.getColor(actualColor));
	}

	public void setColor(int col)
	{
		actualColor = col;
		actualColor %= GuessColors.NumColors;
		setFillColor(GuessColors.getColor(actualColor));
		repaint();
	}

	public int getColor()
	{
		return actualColor;
	}

	public boolean mouseDown(Event e, int x, int y)
	{
		actualColor++;
		actualColor %= GuessColors.NumColors;
		setFillColor(GuessColors.getColor(actualColor));
		repaint();
		return true;
	}

	public void manipulate()
	{
		setDrawColor(Color.red);
		repaint();
	}
}
