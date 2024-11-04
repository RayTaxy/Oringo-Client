package map;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;
import net.minecraft.util.Vec3;
import oringo.module.AutoFarmModule;
import oringo.module.ThystHiderModule;

public class Class325 extends Class220 {
   public int field0;
   public Class179 field1;

   public Class179 method0(Class179 var1, EnumFacing var2) {
      BlockPos var3 = new BlockPos(this.field5.thePlayer);
      IBlockState var4 = this.field5.theWorld.getBlockState(var3.down());
      if (var4.getBlock().getCollisionBoundingBox(this.field5.theWorld, var3.down(), var4) == null) {
         return var1;
      } else {
         if (var1 == null) {
            var1 = Class179.field3;
         }

         EnumFacing var5 = var1.method0(var2);
         BlockPos var6 = new BlockPos(this.field5.thePlayer.posX + 0.4D * (double)var5.getFrontOffsetX(), this.field5.thePlayer.posY, this.field5.thePlayer.posZ + 0.4D * (double)var5.getFrontOffsetZ());
         IBlockState var7 = this.field5.theWorld.getBlockState(var6);
         if (var7.getBlock().getCollisionBoundingBox(this.field5.theWorld, var6, var7) == null) {
            return var1;
         } else {
            ArrayList var8 = Lists.newArrayList(Class179.values());
            if (var1 == Class179.field2 || var1 == Class179.field0) {
               Collections.reverse(var8);
            }

            Iterator var9 = var8.iterator();

            while(var9.hasNext()) {
               Class179 var10 = (Class179)var9.next();
               if (var10 != var1 && var10 != Class179.field0) {
                  var6 = var3.offset(var10.method0(var2));
                  var7 = this.field5.theWorld.getBlockState(var6);
                  if (var7.getBlock().getCollisionBoundingBox(this.field5.theWorld, var6, var7) == null) {
                     return var10;
                  }
               }
            }

            return Class179.field2;
         }
      }
   }

   public void o_() {
      this.field3 = null;
   }

   public MovementInput method0(MovementInput var1) {
      AutoFarmModule var2 = Class229.method1();
      if (this.field3 == null) {
         this.field3 = this.method0(var2.method13());
         if (this.field3 != null) {
            var2.method32();
         }

         return new MovementInput();
      } else {
         Class179 var3 = Class179.method0(var1);
         if (var3 != null) {
            this.field1 = var3;
         }

         if (this.field5.thePlayer.onGround) {
            Class179 var4 = this.method0(this.field1, this.field3);
            if (var4 != this.field1) {
               if (this.field0++ > 4) {
                  this.field1 = var4;
               }
            } else {
               this.field0 = 0;
            }
         }

         MovementInput var8 = this.field1.method0();
         if (var8.moveForward == 0.0F) {
            EnumFacing var5 = Class229.method1().method30();
            Class228 var6 = (new Class228(this.field5.thePlayer.getLookVec())).method1((double)var5.getFrontOffsetX(), (double)var5.getFrontOffsetY(), (double)var5.getFrontOffsetZ());
            Class228 var7 = (new Class228(this.field5.thePlayer.getPositionVector().subtract((new Vec3(ThystHiderModule.method3())).addVector(0.5D, 0.0D, 0.5D)))).method1((double)var5.getFrontOffsetX(), (double)var5.getFrontOffsetY(), (double)var5.getFrontOffsetZ());
            if (var7.method0(var6.method6()) >= 1.0D) {
               var8.moveForward = 1.0F;
            }
         }

         return var8;
      }
   }

   public EnumFacing method0(Class258 var1) {
      BlockPos var2 = new BlockPos(this.field5.thePlayer);
      EnumFacing var3 = null;
      int var4 = 0;
      EnumFacing[] var5 = EnumFacing.HORIZONTALS;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         EnumFacing var8 = var5[var7];
         int var9 = 0;

         for(int var10 = 1; var10 < 5; ++var10) {
            BlockPos var11 = var2.offset(var8, var10);
            IBlockState var12 = this.field5.theWorld.getBlockState(var11);
            if (var12.getBlock() == Blocks.farmland || var12.getBlock() == Blocks.soul_sand || var12.getBlock() == Blocks.mycelium || var12.getBlock() == Blocks.dirt && var12.getValue(BlockDirt.VARIANT) == DirtType.PODZOL) {
               ++var9;

               for(int var13 = -2; var13 <= 2; ++var13) {
                  for(int var14 = 0; var14 < 5; var14 += 2) {
                     BlockPos var15 = var11.up().down(var14).offset(var8.rotateY(), var13);
                     var12 = this.field5.theWorld.getBlockState(var15);
                     if (var1.method0(var12.getBlock())) {
                        if (var12.getBlock() instanceof BlockCrops) {
                           if ((Integer)var12.getValue(BlockCrops.AGE) == 7) {
                              var9 += 5;
                           }
                        } else if (var12.getBlock() instanceof BlockNetherWart && (Integer)var12.getValue(BlockNetherWart.AGE) == 3) {
                           var9 += 5;
                        }

                        ++var9;
                     }
                  }
               }
            }
         }

         if (var9 > var4) {
            var3 = var8;
            var4 = var9;
         }
      }

      return var3;
   }

   public Class325() {
      this.field1 = Class179.field3;
      this.field0 = 0;
   }

   public void method16() {
      this.field3 = this.method0(Class229.method1().method13());
   }
}
