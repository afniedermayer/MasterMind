import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;

class youVeGotIt extends Dialog {
   static int h = 200;
   static int w = 300;
   String text;

   public youVeGotIt(Frame var1, String var2, String var3, boolean var4) {
      super(var1, var2, var4);
      if (var4) {
         var1.disable();
      }

      this.text = var3;
      Panel var5 = new Panel();
      var5.add(new Button("OK"));
      this.add("South", var5);
      this.resize(w, h);
   }

   public void paint(Graphics var1) {
      var1.setFont(new Font("Times New Roman", 1, 16));
      var1.setColor(Color.red);
      var1.drawString(this.text, 80, 90);
   }

   public boolean action(Event var1, Object var2) {
      if (var2.equals("OK")) {
         Container var3 = this.getParent();
         var3.enable();
         if (var3 instanceof simplePlayer) {
            simplePlayer var4 = (simplePlayer)var3;
            var4.renew();
         }

         this.dispose();
         return true;
      } else {
         return false;
      }
   }
}
