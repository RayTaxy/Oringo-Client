package map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.event.Class16;

public class Class207 extends Class83 {
   public final Class447 field4 = new Class447();
   public int field5;
   public final Class447 field6 = new Class447();

   public Class207() {
      super("Break throttle");
   }

   public static double method0(double var0, double var2, double var4) {
      if (var2 < var4) {
         double var6 = var2;
         var2 = var4;
         var4 = var6;
      }

      return Math.max(Math.min(var2, var0), var4);
   }

   public void method2() {
      this.method0(true);
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (this.field5 == 0 || this.field6.a_(5000L)) {
         this.field5 = 0;
         this.field6.o_();
      }

      for(int var2 = 1; var2 <= 4 && this.field5 <= var2 * 15 && this.field6.a_((long)(var2 * 1000)); ++var2) {
         this.field6.o_();
         this.field5 = 0;
      }

      if (this.field3.thePlayer.getDistanceSq(var1.cB_) <= 100.0D) {
         if (var1.field0.getBlock() == Blocks.air && this.field1.method13().method0(var1.field2.getBlock())) {
            IBlockState var4 = var1.field2;
            Block var3 = var4.getBlock();
            if (var3 instanceof BlockCrops) {
               if ((Integer)var4.getValue(BlockCrops.AGE) == 7) {
                  ++this.field5;
               }
            } else if (var3 instanceof BlockNetherWart) {
               if ((Integer)var4.getValue(BlockNetherWart.AGE) == 3) {
                  ++this.field5;
               }
            } else if (var3 instanceof BlockCocoa) {
               if ((Integer)var4.getValue(BlockCocoa.AGE) == 2) {
                  ++this.field5;
               }
            } else {
               ++this.field5;
            }

         }
      }
   }

   public boolean method4() {
      return false;
   }

   public boolean method3() {
      if (!this.field1.method9().method1()) {
         return false;
      } else {
         return this.field5 > 50 && this.field6.a_(4000L);
      }
   }

   public void o_() {
      this.field4.o_();
   }

   public void method0(ClientTickEvent var1) {
      if (this.field4.method0(1000L, true)) {
         if (Class496.field6 && this.field3.thePlayer != null) {
            this.field3.thePlayer.sendChatMessage("/warp hub");
         } else {
            this.field1.method0((Class83)Class526.method0());
         }
      }

   }
}
