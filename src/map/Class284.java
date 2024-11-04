package map;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.command.IRCNameCommand;
import oringo.command.ReplyCommand;
import oringo.event.Class468;
import oringo.module.AutoSimonModule;

public class Class284 extends Class83 {
   public Vec3 field4;
   public int field5;
   public boolean field6;
   public int field7;
   public BlockPos field8;

   public Class284() {
      super("Wall");
   }

   public boolean method4() {
      return this.Y_ < 13 && this.field1.method21() || this.field6;
   }

   public boolean method3() {
      if (this.field1.method7().method1() && Class496.field6) {
         if (this.field3.thePlayer != null && this.field3.thePlayer.onGround) {
            EnumFacing[] var1 = EnumFacing.HORIZONTALS;
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
               EnumFacing var4 = var1[var3];
               BlockPos var5 = new BlockPos(this.field3.thePlayer.posX + 0.4D * (double)var4.getFrontOffsetX(), this.field3.thePlayer.posY, this.field3.thePlayer.posZ + 0.4D * (double)var4.getFrontOffsetZ());
               IBlockState var6 = this.field3.theWorld.getBlockState(var5);
               if (var6.getBlock().getCollisionBoundingBox(this.field3.theWorld, var5, var6) != null && var6.getBlock().isFullCube() && Class490.method0(this.field1.method13(), var5.offset(var4))) {
                  this.field8 = var5;
                  return true;
               }

               IBlockState var7 = this.field3.theWorld.getBlockState(var5.up());
               if (var7.getBlock().getCollisionBoundingBox(this.field3.theWorld, var5, var6) != null && var7.getBlock().isFullCube() && Class490.method0(this.field1.method13(), var5.offset(var4))) {
                  this.field8 = var5.up();
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static int method0(int var0, int var1, float var2) {
      return (int)((float)var0 + (float)(var1 - var0) * var2);
   }

   public void method0(Class468 var1) {
      ++this.Y_;
      if (this.Y_ < 8) {
         var1.method0(this.field1.method25());
      } else {
         this.field6 = false;
         if (this.field4 == null) {
            this.field4 = (new Vec3(this.field8)).addVector(Class83.method0(0.35D, 0.65D), Class83.method0(0.45D, 0.65D), Class83.method0(0.35D, 0.65D));
         }

         Class34 var2 = ReplyCommand.method0(this.field4);
         double var3 = IRCNameCommand.method0(Class256.method1(), var2);
         boolean var5 = true;
         if (var3 > 0.5D) {
            Class34 var6 = Class305.method0(Class256.method1(), var2, var3 > 30.0D ? 12.0F : 3.0F);
            this.field3.thePlayer.rotationYaw = var6.method5();
            this.field3.thePlayer.rotationPitch = var6.method2();
            var5 = false;
         }

         if (Class486.method0(new BlockPos(this.field3.thePlayer), this.field8) <= 3) {
            var1.method0(-1.0F);
            var5 = false;
         }

         if (var5 && this.field7++ < 25) {
            this.field6 = true;
         } else if (this.field3.theWorld.getBlockState(this.field8).getBlock() != Blocks.dirt) {
            if (this.field5++ > 20) {
               this.field1.method23();
            }
         } else {
            this.field5 = 0;
         }

      }
   }

   public static boolean method0(BlockPos var0) {
      return AutoSimonModule.field58.thePlayer.getDistance((double)var0.getX(), (double)((float)var0.getY() - AutoSimonModule.field58.thePlayer.getEyeHeight()), (double)var0.getZ()) < 5.0D;
   }

   public void method2() {
      this.method0(true);
      if (this.field1.method22()) {
         this.field1.method1(false);
      }

      if (this.field1.method35()) {
         this.method0();
      }

      if (this.field1.method28()) {
         Class157.method0();
      }

   }

   public void method0(ClientTickEvent var1) {
      if (this.field3.thePlayer == null) {
         this.field1.method23();
      }

   }

   public void o_() {
      super.o_();
      this.field7 = 0;
      this.field5 = 0;
      this.field4 = null;
   }
}
