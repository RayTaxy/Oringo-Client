package oringo.event;

import java.io.File;
import map.Class315;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class125 extends Event {
   public final float field0;
   public final float field1;
   public final float field2;
   public final float field3;
   public final EntityLivingBase field4;
   public final float field5;
   public final float field6;
   public final ModelBase field7;

   public Class125(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, ModelBase var8) {
      this.field4 = var1;
      this.field0 = var2;
      this.field5 = var3;
      this.field3 = var4;
      this.field6 = var5;
      this.field2 = var6;
      this.field1 = var7;
      this.field7 = var8;
   }

   public static int method0(Class315.Class3 var0, byte[] var1, int var2) {
      Class315.Class3.access$2802(var0, var1[var2] & 255 | (var1[var2 + 1] & 255) << 8);
      Class315.Class3.access$2902(var0, var1[var2 + 2] & 255 | (var1[var2 + 3] & 255) << 8);
      Class315.Class3.access$3002(var0, Class315.Class3.access$2800(var0) * Class315.Class3.access$2900(var0));
      byte var3 = var1[var2 + 4];
      var0.field9 = (var3 & 128) >>> 7 == 1;
      int var4 = ((var3 & 112) >>> 4) + 1;
      var0.field7 = 1 << var4;
      var0.dp_ = (var3 & 8) >>> 3 == 1;
      int var5 = (var3 & 7) + 1;
      var0.field10 = 1 << var5;
      var0.field0 = var1[var2 + 5] & 255;
      var0.field1 = var1[var2 + 6] & 255;
      return var2 + 7;
   }

   public static File method0(File var0, int var1, int var2, Framebuffer var3) {
      return Class274.method0(var0, (String)null, var1, var2, var3);
   }

   private static class Class0 extends net.minecraftforge.fml.common.eventhandler.Event {
   }
}
