// November 19, 1996
// by Andras Niedermayer

class Answer
{
	public static final int numHashCodePos =
		(Guess.NumGuesses+1) * (Guess.NumGuesses+1);

	private int Blacks, Whites;

	public Answer()
	{
		Blacks = 0;
		Whites = 0;
	}

	public Answer(int b, int w)
	{
		Blacks = b;
		Whites = w;
	}

	public void setBlacks(int b)
	{
		Blacks = b;
	}

	public void setWhites(int w)
	{
		Whites = w;
	}

	public int getBlacks()
	{
		return Blacks;
	}

	public int getWhites()
	{
		return Whites;
	}

	public boolean allBlacks()
	{
		return getBlacks() == Guess.NumGuesses;
	}

	public void compare(Guess g1, Guess g2)
	{
		Blacks = 0;
		Whites = 0;

		int helpArray1[] = new int[Guess.NumGuesses];
		int helpArray2[] = new int[Guess.NumGuesses];

		for( int i = 0; i<Guess.NumGuesses; i++ )
		{
			helpArray1[i] = g1.getPeg(i);
			helpArray2[i] = g2.getPeg(i);
		}

		for( int i = 0; i<Guess.NumGuesses; i++ )
			if( helpArray1[i] == helpArray2[i] )
			{
				Blacks++;
				helpArray1[i] = -1;
				helpArray2[i] = -2;
			}

		for( int i = 0; i<Guess.NumGuesses; i++ )
			for( int j = 0; j<Guess.NumGuesses; j++ )
				if( helpArray1[i] == helpArray2[j] )
				{
					Whites++;
					helpArray1[i] = -1;
					helpArray2[j] = -2;
				}
   }

	public String toString()
	{
		return "Blacks: " + Blacks + " Whites: " + Whites;
	}

	public boolean equals(Answer a)
	{
		return Whites==a.Whites && Blacks==a.Blacks;
	}

	public boolean equals(Object o)
	{
		if(o instanceof Answer)
			return equals((Answer)o);
		else
			return false;
	}

	public int hashCode()
	{
		return Blacks*(Guess.NumGuesses+1) + Whites;
	}
}
