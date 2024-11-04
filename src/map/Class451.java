package map;

import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.event.Class189;
import oringo.event.Class468;

public class Class451 extends Class83 {
   public boolean db_;
   public final Class447 field5 = new Class447();

   public void method0(ClientTickEvent var1) {
      if (this.field3.thePlayer == null || !Class496.field6) {
         this.field1.method23();
      }

   }

   public static int method0(int var0, int var1, int var2) {
      if (var1 < var2) {
         int var3 = var1;
         var1 = var2;
         var2 = var3;
      }

      return Math.max(Math.min(var1, var0), var2);
   }

   public void o_() {
      super.o_();
      this.db_ = false;
   }

   public Class451() {
      super("Finish Loop");
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook) {
         this.db_ = true;
      }

   }

   public boolean method3() {
      return this.field3.thePlayer != null && this.field1.method24().equals(new BlockPos(this.field3.thePlayer));
   }

   public boolean method4() {
      return this.Y_ < 6 && this.field1.method21();
   }

   public void method0(Class468 var1) {
      ++this.Y_;
      if (this.Y_ < 7) {
         var1.method0(this.field1.method25());
      } else {
         if (this.db_) {
            if (this.field3.theWorld.isBlockLoaded(new BlockPos(this.field3.thePlayer))) {
               this.field1.method23();
               this.field1.method16();
            }
         } else if (this.field5.method0(1000L, true)) {
            this.field3.thePlayer.sendChatMessage("/warp garden");
         }

      }
   }
}
