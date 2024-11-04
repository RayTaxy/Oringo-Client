package map;

import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.event.Class189;
import oringo.event.Class468;

public class Class452 extends Class83 {
   public BlockPos field4;
   public Class452.Class0 field5;

   public void method0(ClientTickEvent var1) {
      if (this.field3.thePlayer == null || !Class514.method0(new BlockPos(this.field3.thePlayer))) {
         this.field1.method23();
      }

   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook) {
         S08PacketPlayerPosLook var2 = (S08PacketPlayerPosLook)var1.field0;
         BlockPos var3 = new BlockPos(var2.getX(), var2.getY(), var2.getZ());
         if (Class514.method0(var3)) {
            this.field4 = var3;
         }
      }

   }

   public void method0(Class468 var1) {
      // $FF: Couldn't be decompiled
   }

   public void method2() {
      this.method0(true);
      if (this.field1.method35()) {
         this.method0();
      }

      if (this.field1.method28()) {
         Class157.method0();
      }

      if (this.field1.method22()) {
         this.field1.method1(false);
      }

   }

   public boolean method3() {
      if (!this.field1.method11().method1()) {
         return false;
      } else {
         return this.field3.thePlayer != null && this.field4 != null && this.method5() && Class514.method0(new BlockPos(this.field3.thePlayer));
      }
   }

   public boolean method4() {
      return this.Y_ < 8;
   }

   public boolean method5() {
      BlockPos var1 = new BlockPos(this.field3.thePlayer);

      for(int var2 = (int)this.field3.thePlayer.posY; var2 > 0; --var2) {
         var1 = var1.down();
         if (this.field3.theWorld.getBlockState(var1).getBlock() == Blocks.bedrock) {
            return true;
         }
      }

      return false;
   }

   public void o_() {
      super.o_();
      this.field5 = Class452.Class0.field1;
   }

   public static int method0(BlockPos var0, BlockPos var1, EnumFacing var2) {
      return (int)Class361.method1((double)((var0.getX() - var1.getX()) * var2.getFrontOffsetX()), (double)((var0.getZ() - var1.getZ()) * var2.getFrontOffsetZ()));
   }

   public Class452() {
      super("Bedrock Box");
      this.field5 = Class452.Class0.field1;
   }

   public static enum Class0 {
      field0,
      field1,
      field2;

      private static final Class452.Class0[] field3 = new Class452.Class0[]{field1, field2, field0};
   }
}
