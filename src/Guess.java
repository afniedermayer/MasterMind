// November 19, 1996
// Andras Niedermayer

class Guess implements Cloneable
{
	public static final int NumGuesses = 4;
	private int Pegs[];

	public Guess()
	{
		Pegs = new int[NumGuesses];
	}

	public Guess(int newPegs[])
	{
		Pegs = new int[NumGuesses];
		setPegs(newPegs);
	}

	public void setPegs(int newPegs[])
	{
		for( int i = 0; i<NumGuesses; i++ )
			Pegs[i] = newPegs[i];
	}

	public int getPeg(int Place)
	{
		return Pegs[Place];
	}

	public void setPeg(int Place, int pColor)
	{
		Pegs[Place] = pColor;
	}

	public int[] getPegs()
	{
		return Pegs;
	}

	public void copy(Guess g)
	{
		setPegs( g.getPegs() );
	}

	public Object clone()
	{
		Guess r = new Guess();
		r.copy(this);
		return r;
	}

	public void setRandom()
	{
		for( int i = 0; i<NumGuesses; i++ )
			Pegs[i] = (int)(Math.random()*GuessColors.NumColors);
	}

	public String toString()
	{
		String r = "";
		for( int i = 0; i<NumGuesses; i++ )
			r += " " + Pegs[i];
		return r;
	}

	public boolean equals(Guess g)
	{
		for( int i = 0; i<NumGuesses; i++ )
			if( Pegs[i] != g.Pegs[i] )
				return false;
		return true;
	}

	protected void finalize()
	{
		Pegs = null;
	}
}
