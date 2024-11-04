package map;

import net.minecraft.block.Block;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.module.SprintModule;

public class Class1 {
   public boolean a_ = false;
   public Vec3 field1 = null;
   public String field2 = null;
   public Block field3 = null;
   public int field0 = 0;
   public boolean field5 = false;

   public static void method0(double var0, double var2, double var4, double var6) {
      ScaledResolution var8 = new ScaledResolution(Oringo.field9);
      double var9 = (double)var8.getScaleFactor();
      var2 = (double)var8.getScaledHeight() - var2;
      if (var0 >= 0.0D && var2 >= 0.0D && var4 >= 0.0D && var6 >= 0.0D) {
         var0 *= var9;
         var2 *= var9;
         var4 *= var9;
         var6 *= var9;
         GL11.glScissor((int)var0, (int)(var2 - var6), (int)var4, (int)var6);
      }
   }

   public static void o_() {
      Class198.field1 = new Class208[6][6];
      SprintModule.o_();
      Class198.field4 = false;
   }
}
