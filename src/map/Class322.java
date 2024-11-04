package map;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

public class Class322 {
   public static final WorldRenderer field0;
   public static final Tessellator field1 = Tessellator.getInstance();
   public static final List field2 = new ArrayList();
   public static final Minecraft field3 = Minecraft.getMinecraft();

   static {
      field0 = field1.getWorldRenderer();
   }

   public static ArrayList method0() {
      return Class82.field2;
   }

   private static class Class0 {
      int field2;
      FloatBuffer field1;
      String cy_;
      double i_;
      double field4;
      double j_;

      public Class0(String var1, double var2, double var4, double var6) {
         this.cy_ = var1;
         this.j_ = var2;
         this.i_ = var4;
         this.field4 = var6;
      }
   }
}
