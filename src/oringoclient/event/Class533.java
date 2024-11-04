package oringo.event;

import java.io.File;
import java.util.Date;
import map.Class136;
import map.Class25;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.lwjgl.opengl.GL11;
import oringo.module.CreateNewkeybindModule;
import oringo.module.PinglessHardstoneModule;
import oringo.module.PopupAnimationModule;

@Cancelable
public class Class533 extends Event {
   public final ScaledResolution field0;
   public final ScoreObjective field1;

   public Class533(ScoreObjective var1, ScaledResolution var2) {
      this.field1 = var1;
      this.field0 = var2;
   }

   public static void method0() {
      float var0 = Class255.method0();
      ScaledResolution var1 = new ScaledResolution(PopupAnimationModule.field58);
      GL11.glTranslated((double)var1.getScaledWidth() / 2.0D, (double)var1.getScaledHeight() / 2.0D, 0.0D);
      GL11.glScaled((double)var0, (double)var0, 1.0D);
      GL11.glTranslated((double)(-var1.getScaledWidth()) / 2.0D, (double)(-var1.getScaledHeight()) / 2.0D, 0.0D);
   }

   public static boolean method1() {
      ItemStack var0 = PinglessHardstoneModule.field58.thePlayer.getHeldItem();
      return CreateNewkeybindModule.method0(var0, "Mining Speed:", true);
   }

   public static File method0(File var0) {
      String var1 = Class136.field1.format(new Date());
      int var2 = 1;

      while(true) {
         File var3 = new File(var0, var1 + (var2 == 1 ? "" : "_" + var2) + ".png");
         if (!var3.exists()) {
            return var3;
         }

         ++var2;
      }
   }

   public static boolean method2() {
      return !Class25.field1.isEmpty();
   }
}
