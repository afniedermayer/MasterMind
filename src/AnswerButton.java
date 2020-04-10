// December 13, 1996
// Andras Niedermayer

import java.awt.*;

class AnswerButton extends CircleColorButton
{
	public final static int EMPTY = 0;
	public final static int WHITE = 1;
	public final static int BLACK = 2;

	int Position = 0;

	AnswerButton()
	{
		super();
		setFillColor(Color.lightGray);
//		System.out.println("Being constructed: AnswerButton");
//		System.out.println("Position: " + location() );
	}

	public AnswerButton(Dimension d)
	{
		super();
		setFillColor(Color.lightGray);
//		System.out.println("Yo!");
		resize(d);
	}

	public AnswerButton(int x, int y)
	{
		super();
		setFillColor(Color.lightGray);
//		System.out.println("Yo!");
		resize(new Dimension(x,y));
	}

	public boolean mouseDown(Event e, int x, int y)
	{
		Position++;
		Position %= 3;

		Color c = (Position==EMPTY) ? Color.lightGray :
					 (Position==WHITE) ? Color.white : Color.black;
		setFillColor(c);
		repaint();
		return true;
	}

	public void setBlack()
	{
		Position = BLACK;
		setFillColor(Color.black);
		repaint();
	}

	public void setWhite()
	{
		Position = WHITE;
		setFillColor(Color.white);
		repaint();
	}

	public void setEmpty()
	{
		Position = EMPTY;
		setFillColor(Color.lightGray);
		repaint();
	}

	public int getColor()
	{
		return Position;
	}

	public boolean isBlack()
	{
		return Position == BLACK;
	}

	public boolean isWhite()
	{
		return Position == WHITE;
	}

	public boolean isEmpty()
	{
		return Position == EMPTY;
	}
}
