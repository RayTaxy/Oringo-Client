package map;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;
import oringo.module.BossBarModule;

public class Class515 extends Class220 {
   public Class515.Class0 field0;
   public int Y_;

   public static void method0(float var0, float var1, float var2, float var3) {
      ScaledResolution var4 = new ScaledResolution(Class240.field0);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      Class240.field1.method5();
      Class240.field1.method0("location", var0 * (float)var4.getScaleFactor(), (float)Class240.field0.displayHeight - var3 * (float)var4.getScaleFactor() - var1 * (float)var4.getScaleFactor());
      Class240.field1.method0("size", var2 * (float)var4.getScaleFactor(), var3 * (float)var4.getScaleFactor());
      BossBarModule.method0(var0, var1, var2, var3);
      Class240.field1.method2();
      GlStateManager.disableBlend();
   }

   public MovementInput method0(MovementInput var1) {
      // $FF: Couldn't be decompiled
   }

   public void o_() {
      this.field3 = null;
      this.Y_ = 0;
   }

   public Class515() {
      this.field0 = Class515.Class0.field1;
      this.Y_ = 0;
   }

   public static void method1() {
      (new Thread(Class56::lambda$playOutro$0)).start();
   }

   public EnumFacing method5() {
      BlockPos var1 = new BlockPos(this.field5.thePlayer);
      EnumFacing[] var2 = EnumFacing.HORIZONTALS;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumFacing var5 = var2[var4];
         boolean var6 = true;

         for(int var7 = 1; var7 <= 3; ++var7) {
            if (this.field5.theWorld.getBlockState(var1.offset(var5, var7)).getBlock() != Blocks.reeds) {
               var6 = false;
               break;
            }
         }

         if (var6) {
            return var5 == EnumFacing.SOUTH ? EnumFacing.NORTH : (var5 == EnumFacing.EAST ? EnumFacing.WEST : var5);
         }
      }

      return null;
   }

   private static enum Class0 {
      field0;

      private static final Class515.Class0[] field2 = new Class515.Class0[]{field1, field0};
      field1;
   }
}
