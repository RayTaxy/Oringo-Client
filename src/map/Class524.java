package map;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;
import org.lwjgl.util.vector.Vector3f;
import oringo.module.AutoFarmModule;
import oringo.module.BossBarModule;

public class Class524 extends Class220 {
   public Class524.Class0 field0;

   public MovementInput method0(MovementInput var1) {
      AutoFarmModule var2 = Class229.method1();
      if (this.field3 == null) {
         this.field3 = this.method1();
         if (this.field3 != null) {
            var2.method32();
         }

         return new MovementInput();
      } else {
         if (this.field5.thePlayer.onGround) {
            this.field0 = this.method0(this.field0, this.field3);
         }

         return this.field0.method0();
      }
   }

   public Class524.Class0 method0(Class524.Class0 var1, EnumFacing var2) {
      BlockPos var3 = new BlockPos(this.field5.thePlayer);
      IBlockState var4 = this.field5.theWorld.getBlockState(var3.down());
      if (var4.getBlock().getCollisionBoundingBox(this.field5.theWorld, var3.down(), var4) == null) {
         return var1;
      } else {
         EnumFacing var5 = var1.method0(var2);
         BlockPos var6 = new BlockPos(this.field5.thePlayer.posX + 0.4D * (double)var5.getFrontOffsetX(), this.field5.thePlayer.posY, this.field5.thePlayer.posZ + 0.4D * (double)var5.getFrontOffsetZ());
         IBlockState var7 = this.field5.theWorld.getBlockState(var6);
         if ((!(var7.getBlock() instanceof BlockDoor) || !this.field0.method1()) && var7.getBlock().getCollisionBoundingBox(this.field5.theWorld, var6, var7) != null) {
            ArrayList var8 = Lists.newArrayList(Class524.Class0.values());
            if (var1.method1()) {
               Collections.reverse(var8);
            }

            IBlockState var9 = this.field5.theWorld.getBlockState(var3);
            Iterator var10 = var8.iterator();

            Class524.Class0 var11;
            do {
               EnumFacing var12;
               do {
                  do {
                     if (!var10.hasNext()) {
                        return Class524.Class0.field2;
                     }

                     var11 = (Class524.Class0)var10.next();
                  } while(var11 == var1);

                  if (!(var9.getBlock() instanceof BlockDoor)) {
                     break;
                  }

                  var12 = BlockDoor.getFacing(BlockDoor.combineMetadata(this.field5.theWorld, var3)).getOpposite();
               } while(var12 == var11.method0(var2));

               var6 = var3.offset(var11.method0(var2));
               var7 = this.field5.theWorld.getBlockState(var6);
            } while(var7.getBlock().getCollisionBoundingBox(this.field5.theWorld, var6, var7) != null);

            return var11;
         } else {
            return var1;
         }
      }
   }

   public EnumFacing method1() {
      BlockPos var1 = new BlockPos(this.field5.thePlayer);
      EnumFacing[] var2 = EnumFacing.HORIZONTALS;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumFacing var5 = var2[var4];
         if (this.field5.theWorld.getBlockState(var1.offset(var5)).getBlock() == Blocks.cactus) {
            return var5;
         }
      }

      return null;
   }

   public Class524() {
      this.field0 = Class524.Class0.field0;
   }

   public static void method0(float var0, float var1, float var2, float var3, Vector3f var4, Vector3f var5, Vector3f var6, Vector3f var7) {
      ScaledResolution var8 = new ScaledResolution(Class240.field0);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      Class240.field2.method5();
      Class240.field2.method0("location", var0 * (float)var8.getScaleFactor(), (float)Class240.field0.displayHeight - var3 * (float)var8.getScaleFactor() - var1 * (float)var8.getScaleFactor());
      Class240.field2.method0("size", var2 * (float)var8.getScaleFactor(), var3 * (float)var8.getScaleFactor());
      Class240.field2.method0("color1", var4.x, var4.y, var4.z);
      Class240.field2.method0("color2", var5.x, var5.y, var5.z);
      Class240.field2.method0("color3", var6.x, var6.y, var6.z);
      Class240.field2.method0("color4", var7.x, var7.y, var7.z);
      BossBarModule.method0(var0, var1, var2, var3);
      Class240.field2.method2();
      GlStateManager.disableBlend();
   }

   private static enum Class0 {
      field0,
      field1,
      field2;

      private static final Class524.Class0[] field3 = new Class524.Class0[]{field0, field1, field2};

      public MovementInput method0() {
         // $FF: Couldn't be decompiled
      }

      public boolean method1() {
         return this == field0 || this == field1;
      }

      public EnumFacing method0(EnumFacing var1) {
         // $FF: Couldn't be decompiled
      }
   }
}
