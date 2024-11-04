package oringo.event;

import java.io.File;
import map.Class136;
import map.Class315;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class464 extends Event {
   public final TileEntityChest field0;
   public final float field1;
   public final double field2;
   public final int field3;
   public final double field4;
   public final double field5;

   public boolean H_() {
      return this instanceof Class464.Class1;
   }

   public TileEntityChest method1() {
      return this.field0;
   }

   public Class464(TileEntityChest var1, double var2, double var4, double var6, float var8, int var9) {
      this.field0 = var1;
      this.field2 = var2;
      this.field4 = var4;
      this.field5 = var6;
      this.field1 = var8;
      this.field3 = var9;
   }

   public static int method0(Class315.Class0 var0, byte[] var1, int var2) {
      ++var2;
      int var10001 = var1[var2] & 255;
      ++var2;
      Class315.Class0.access$2002(var0, var10001 | (var1[var2] & 255) << 8);
      ++var2;
      var10001 = var1[var2] & 255;
      ++var2;
      Class315.Class0.access$2102(var0, var10001 | (var1[var2] & 255) << 8);
      ++var2;
      var10001 = var1[var2] & 255;
      ++var2;
      Class315.Class0.access$1402(var0, var10001 | (var1[var2] & 255) << 8);
      ++var2;
      var10001 = var1[var2] & 255;
      ++var2;
      Class315.Class0.access$1502(var0, var10001 | (var1[var2] & 255) << 8);
      Class315.Class0.access$1602(var0, Class315.Class0.access$1400(var0) * Class315.Class0.access$1500(var0));
      ++var2;
      byte var3 = var1[var2];
      Class315.Class0.access$1702(var0, (var3 & 128) >>> 7 == 1);
      Class315.Class0.access$1902(var0, (var3 & 64) >>> 6 == 1);
      Class315.Class0.access$2702(var0, (var3 & 32) >>> 5 == 1);
      int var4 = (var3 & 7) + 1;
      Class315.Class0.access$2602(var0, 1 << var4);
      ++var2;
      return var2;
   }

   public double method2() {
      return this.field5;
   }

   public double w_() {
      return this.field2;
   }

   public int method4() {
      return this.field3;
   }

   public float method5() {
      return this.field1;
   }

   public double method6() {
      return this.field4;
   }

   public static File method10() {
      return Class125.method0(Class136.field0.mcDataDir, Class136.field0.displayWidth, Class136.field0.displayHeight, Class136.field0.getFramebuffer());
   }

   @Cancelable
   public static class Class0 extends Class464 {
      public Class0(TileEntityChest var1, double var2, double var4, double var6, float var8, int var9) {
         super(var1, var2, var4, var6, var8, var9);
      }
   }

   @Cancelable
   public static class Class1 extends Class464 {
      public Class1(TileEntityChest var1, double var2, double var4, double var6, float var8, int var9) {
         super(var1, var2, var4, var6, var8, var9);
      }
   }
}
