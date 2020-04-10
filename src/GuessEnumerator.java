import java.util.Enumeration;

class GuessEnumerator implements Enumeration {
   int[] Position = new int[4];
   boolean hasMore = true;
   Guess rGuess = new Guess();

   public GuessEnumerator() {
      for(int var1 = 0; var1 < 4; ++var1) {
         this.Position[var1] = 0;
      }

   }

   public boolean hasMoreElements() {
      return this.hasMore;
   }

   public Object nextElement() {
      this.rGuess = new Guess(this.Position);
      this.Incr(0);
      return this.rGuess;
   }

   void Incr(int var1) {
      if (this.Position[var1] == 6) {
         if (var1 >= 3) {
            this.hasMore = false;
         } else {
            this.Position[var1] = 0;
            this.Incr(var1 + 1);
         }
      } else {
         int var10002 = this.Position[var1]++;
      }
   }

   public static int NumAllPos() {
      return (int)Math.pow(7.0D, 4.0D);
   }
}
