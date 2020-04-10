import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;

class Warning extends Dialog {
   boolean showMe = true;
   static int h = 200;
   static int w = 400;
   String text;

   public Warning(Frame var1, String var2, String var3, boolean var4) {
      super(var1, var2, var4);
      if (var4) {
         var1.disable();
      }

      this.text = var3;
      Panel var5 = new Panel();
      var5.add(new Button("OK"));
      this.add("South", var5);
      this.resize(w, h);
      if (this.showMe) {
         this.show();
      }

   }

   public void paint(Graphics var1) {
      super.paint(var1);
      var1.setFont(new Font("Times New Roman", 1, 16));
      var1.setColor(Color.red);
      var1.drawString(this.text, 80, 90);
   }

   public boolean action(Event var1, Object var2) {
      if (var2.equals("OK")) {
         Container var3 = this.getParent();
         var3.enable();
         this.dispose();
         return true;
      } else {
         return false;
      }
   }
}
