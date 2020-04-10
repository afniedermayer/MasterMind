// FreqL
// January 18, 1997
// by Andras Niedermayer

class FreqL
{
	private int f[];

	public FreqL()
	{
		f = new int[Answer.numHashCodePos];
		Clear();
	}

	public void IncrP(Answer a)
	{
		int index = a.hashCode();
		f[index]++;
	}

	public void Clear()
	{
		for(int i = 0; i<f.length; i++ )
			f[i] = 0;
	}

	public long allBlacks()
	{
		int Blacks = Guess.NumGuesses;   // all Blacks
		Answer a = new Answer(Blacks, 0);
		int index = a.hashCode();
		return f[index];
	}

	public double infoValue()
	{
		double r = 0;
		long ntot = 0;

		for(int i = 0; i<f.length; i++ )
		{
			int j = f[i];
			ntot += j;
			if( j!=0 && j!=1 )
				r -= (double)j * Math.log( (double)j );
		}
		if( ntot > 0 )
			return (r/ntot + Math.log( (double)ntot ))  /  Math.log(2.0);
		else
			return 0;
	}
}
