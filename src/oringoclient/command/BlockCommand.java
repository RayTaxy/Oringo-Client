package oringo.command;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.lang.reflect.Field;
import map.Class271;
import map.Class34;
import net.minecraft.util.AxisAlignedBB;
import oringo.Oringo;
import oringo.module.AutoRabbitModule;
import oringo.module.PopupAnimationModule;

public class BlockCommand extends Command {
   public void method0(String[] var1) throws Exception {
      if (var1.length != 1) {
         PopupAnimationModule.method2("Invalid usage! Use: " + AutoRabbitModule.method1() + "block <name>");
      } else {
         Oringo.field4.method0(new Class271(var1[0]));
      }
   }

   public static Object method0(Class var0, String var1, Object var2) {
      try {
         Field var3 = var0.getDeclaredField(var1);
         var3.setAccessible(true);
         return var3.get(var2);
      } catch (Exception var4) {
         var4.printStackTrace();
         return null;
      }
   }

   public BlockCommand() {
      super("block", "ignore");
   }

   public static void method0(Font var0, Graphics2D var1, int var2, int var3, boolean var4, boolean var5) {
      var1.setFont(var0);
      var1.setColor(new Color(0, true));
      var1.fillRect(0, 0, var2, var3);
      var1.setColor(Color.WHITE);
      var1.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      var1.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
      var1.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, var5 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
      var1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, var4 ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
      var1.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, var4 ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
   }

   public String method1() {
      return "Block/unblock someone in IRC";
   }

   public static Class34 method0(AxisAlignedBB var0, float var1) {
      return IRCCommand.method0(var0.expand((double)(-var1), (double)(-var1), (double)(-var1)));
   }
}
