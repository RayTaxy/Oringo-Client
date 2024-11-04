package map;

import com.google.common.collect.BiMap;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import oringo.module.AutoAlignModule;
import oringo.module.AutoFarmModule;
import oringo.module.WTapModule;

public class Class208 {
   public final Class374 field0;
   public final int field1;
   public boolean bA_;
   public Class507 field3;
   public Class178 field4;
   public final int field5;
   public Class432 field6;
   public boolean field7;
   public final int field2;
   public EnumFacing bB_;
   public boolean field10;
   public Class178 field11;

   public Vec3 method0(Vec3 var1, boolean var2) {
      // $FF: Couldn't be decompiled
   }

   public BlockPos method0(BlockPos var1, boolean var2) {
      // $FF: Couldn't be decompiled
   }

   public int method0() {
      return this.field0.field1;
   }

   public static float method0(float var0) {
      AutoFarmModule var1 = Class229.method1();
      if (var1.method44() && var1.field21.method1() && Class528.method0()) {
         int var2 = AutoFarmModule.field58.gameSettings.thirdPersonView;
         GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(AutoFarmModule.field58.thePlayer.rotationPitch * (float)(var2 * 2 - 3), 1.0F, 0.0F, 0.0F);
         return var0 - 90.0F + (float)(180 * var2);
      } else {
         return var0;
      }
   }

   public String method45() {
      return this.field0.field2;
   }

   public Class374 method2() {
      return this.field0;
   }

   public int hashCode() {
      return this.field2;
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, int var5, int var6) {
      WTapModule.method3();
      Class469.method0(var0 + var4, var1 + var4, var2 - var4, var3 - var4, var5);
      GL11.glPushMatrix();
      Class469.method0(var0 + var4, var1, var2 - var4, var1 + var4, var6);
      Class469.method0(var0, var1, var0 + var4, var3, var6);
      Class469.method0(var2 - var4, var1, var2, var3, var6);
      Class469.method0(var0 + var4, var3 - var4, var2 - var4, var3, var6);
      GL11.glPopMatrix();
      AutoAlignModule.method2();
   }

   public BlockPos method0(BlockPos var1) {
      return this.method1(var1, false);
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class208 var2 = (Class208)var1;
         return this.field2 == var2.field2;
      } else {
         return false;
      }
   }

   public void method0(EnumFacing var1) {
      this.bB_ = var1;
      this.field10 = true;
   }

   public int method3() {
      return this.field2;
   }

   public Class208(Class374 var1, int var2, Class507 var3, int var4, int var5) {
      this.field6 = Class432.field2;
      this.bB_ = EnumFacing.NORTH;
      this.field0 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field1 = var4;
      this.field5 = var5;
   }

   public BlockPos method1(BlockPos var1, boolean var2) {
      // $FF: Couldn't be decompiled
   }

   public static int method0(Class var0, Class465.Class0 var1) {
      Integer var2 = (Integer)((BiMap)Class465.field0.get(var1)).inverse().get(var0);
      if (var2 == null) {
         throw new IllegalStateException("Unknown IRC Packet: " + var0.getSimpleName() + " " + var1.name());
      } else {
         return var2;
      }
   }

   public int method4() {
      return this.field0.field3;
   }

   public Class46 method5() {
      return this.field0.field4;
   }

   public Color method17() {
      return this.field6 == Class432.field2 ? this.method5().method0().darker() : this.method5().method0();
   }
}
