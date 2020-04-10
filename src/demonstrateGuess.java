import java.awt.Dimension;
import java.awt.Frame;

class demonstrateGuess extends Warning {
   boolean showMe = false;

   public demonstrateGuess(Frame var1, String var2, String var3, Guess var4, boolean var5) {
      super(var1, var2, var3, var5);
      Dimension var6 = new Dimension(3 * Warning.w / 4, Warning.h / 4);
      GuessPanel var7 = new GuessPanel(var6);
      var7.setGuess(var4);
      this.add("North", var7);
      var7.disable();
      this.show();
   }
}
