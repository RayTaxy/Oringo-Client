package oringo.event;

import java.awt.Color;
import map.Class506;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.culling.ICamera;

public class Class290 extends Event {
   public final EntityOtherPlayerMP field0;

   public static Color method0(Color var0, Color var1, float var2) {
      return new Color((int)((float)var0.getRed() + (float)(var1.getRed() - var0.getRed()) * var2), (int)((float)var0.getRed() + (float)(var1.getGreen() - var0.getGreen()) * var2), (int)((float)var0.getBlue() + (float)(var1.getBlue() - var0.getBlue()) * var2));
   }

   public static ICamera method0() {
      return Class506.field0;
   }

   public Class290(EntityOtherPlayerMP var1) {
      this.field0 = var1;
   }
}
