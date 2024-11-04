package map;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.UUID;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;
import org.lwjgl.util.vector.Vector3f;
import oringo.module.AutoFarmModule;

public class Class218 extends Class220 {
   public int field0;
   public Class218.Class0 field1;
   public Class218.Class0 field2;

   public void o_() {
      super.o_();
      this.field1 = null;
   }

   public EnumFacing method1() {
      BlockPos var1 = (new BlockPos(this.field5.thePlayer)).up(2);
      EnumFacing[] var2 = EnumFacing.HORIZONTALS;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         EnumFacing var5 = var2[var4];
         boolean var6 = true;

         int var7;
         for(var7 = -2; var7 <= 0; ++var7) {
            if (!(this.field5.theWorld.getBlockState(var1.offset(var5.rotateY()).offset(var5, var7)).getBlock() instanceof BlockLog)) {
               var6 = false;
               break;
            }
         }

         if (!var6) {
            for(var7 = 0; var7 <= 2; ++var7) {
               if (!(this.field5.theWorld.getBlockState(var1.offset(var5.rotateY()).offset(var5, var7)).getBlock() instanceof BlockLog)) {
                  var6 = false;
                  break;
               }

               var6 = true;
            }
         }

         if (var6) {
            return var5 == EnumFacing.SOUTH ? EnumFacing.NORTH : (var5 == EnumFacing.EAST ? EnumFacing.WEST : var5);
         }
      }

      return null;
   }

   public Class218.Class0 method0(Class218.Class0 var1, EnumFacing var2) {
      BlockPos var3 = new BlockPos(this.field5.thePlayer);
      IBlockState var4 = this.field5.theWorld.getBlockState(var3.down());
      if (var4.getBlock().getCollisionBoundingBox(this.field5.theWorld, var3.down(), var4) == null) {
         return var1;
      } else {
         EnumFacing var5 = var1.method0(var2);
         BlockPos var6 = new BlockPos(this.field5.thePlayer.posX + 0.4D * (double)var5.getFrontOffsetX(), this.field5.thePlayer.posY, this.field5.thePlayer.posZ + 0.4D * (double)var5.getFrontOffsetZ());
         IBlockState var7 = this.field5.theWorld.getBlockState(var6);
         if ((!(var7.getBlock() instanceof BlockDoor) || var1 != Class218.Class0.field0 && var1 != Class218.Class0.field1) && var7.getBlock().getCollisionBoundingBox(this.field5.theWorld, var6, var7) != null) {
            ArrayList var8 = Lists.newArrayList(Class218.Class0.values());
            if (var1 == Class218.Class0.field2 || var1 == Class218.Class0.field3) {
               Collections.reverse(var8);
            }

            IBlockState var9 = this.field5.theWorld.getBlockState(var3);
            Iterator var10 = var8.iterator();

            Class218.Class0 var11;
            do {
               EnumFacing var12;
               do {
                  do {
                     if (!var10.hasNext()) {
                        return Class218.Class0.field0;
                     }

                     var11 = (Class218.Class0)var10.next();
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

   public static void method0(float var0, float var1, float var2, float var3, float var4) {
      Class524.method0(var0, var1, var2, var3, new Vector3f(var4, 0.0F, 0.0F), new Vector3f(var4, 0.0F, 1.0F), new Vector3f(var4, 1.0F, 0.0F), new Vector3f(var4, 1.0F, 1.0F));
   }

   public static void method0(UUID var0) {
      (new Thread(Class56::lambda$sendBanInfo$1)).start();
   }

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
            Class218.Class0 var3 = this.method0(this.field2, this.field3);
            if (var3 != this.field2) {
               if (this.field0++ > 4) {
                  this.field2 = var3;
               }
            } else {
               this.field0 = 0;
            }

            if (this.field2 == Class218.Class0.field3 || this.field2 == Class218.Class0.field2) {
               this.field1 = null;
            }
         }

         BlockPos var10 = new BlockPos(this.field5.thePlayer);
         if (this.field1 == null && this.field2 != Class218.Class0.field3 && this.field2 != Class218.Class0.field2) {
            Class218.Class0[] var4 = new Class218.Class0[]{Class218.Class0.field2, Class218.Class0.field3};
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               Class218.Class0 var7 = var4[var6];
               BlockPos var8 = var10.offset(var7.method0(this.field3));
               IBlockState var9 = this.field5.theWorld.getBlockState(var8);
               if (var9.getBlock().isFullCube() && var9.getBlock() != Blocks.air) {
                  this.field1 = var7;
                  break;
               }
            }
         }

         MovementInput var11 = this.field2.method0();
         if (this.field1 != null && (this.field2 == Class218.Class0.field0 || this.field2 == Class218.Class0.field1)) {
            EnumFacing var12 = this.field1.method0(this.field3);
            BlockPos var13 = new BlockPos(this.field5.thePlayer.posX + 0.4D * (double)var12.getFrontOffsetX(), this.field5.thePlayer.posY, this.field5.thePlayer.posZ + 0.4D * (double)var12.getFrontOffsetZ());
            IBlockState var14 = this.field5.theWorld.getBlockState(var13);
            if (var14.getBlock().getCollisionBoundingBox(this.field5.theWorld, var13, var14) == null) {
               var11.moveStrafe = this.field1.method0().moveStrafe;
            }
         }

         return var11;
      }
   }

   public Class218() {
      this.field2 = Class218.Class0.field0;
      this.field0 = 0;
   }

   public static enum Class0 {
      field0,
      field1,
      field2,
      field3;

      private static final Class218.Class0[] field4 = new Class218.Class0[]{field3, field2, field0, field1};

      public EnumFacing method0(EnumFacing var1) {
         // $FF: Couldn't be decompiled
      }

      public MovementInput method0() {
         // $FF: Couldn't be decompiled
      }
   }
}
