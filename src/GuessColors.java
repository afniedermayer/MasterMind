// December 12, 1996
// Andras Niedermayer

import java.awt.*;

class GuessColors
{
	public static final int NumColors = 7;

	private static final Color Colors[] =
	{
		Color.lightGray,
		Color.blue,
		Color.red,
		Color.black,
		Color.white,
		Color.green,
		Color.yellow
	};

	private static final String names[] =
		{ "empty", "blue", "red", "black", "white", "green", "yellow" };

	public static Color getColor(int i)
	{
		return Colors[i];
	}
}
