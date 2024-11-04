package map;

import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.event.Class468;
import oringo.module.RenderChunkBoundsModule;

public class Class163 extends Class83 {
   public static float method0(float var0, float var1, float var2) {
      if (var1 < var2) {
         float var3 = var1;
         var1 = var2;
         var2 = var3;
      }

      return Math.max(Math.min(var1, var0), var2);
   }

   public void method0(Class468 var1) {
      if (this.Y_ < 5) {
         var1.method0(this.field1.method25());
      } else {
         var1.method4();
      }

   }

   public boolean method3() {
      return this.field3.thePlayer != null && (this.field3.thePlayer.getHeldItem() == null || !this.field1.method13().method0(this.field3.thePlayer.getHeldItem())) && Class496.field6;
   }

   public void method0(ClientTickEvent var1) {
      ++this.Y_;
      if (this.field3.thePlayer == null) {
         this.field1.method23();
      } else {
         if (this.Y_ > 13) {
            if (this.method3()) {
               if (!RenderChunkBoundsModule.method5()) {
                  this.field3.thePlayer.closeScreen();
               }

               Class258 var10000 = this.field1.method13();
               var10000.getClass();
               int var2 = Class95.method0(var10000::method0);
               if (var2 == -1) {
                  this.field1.method23();
                  return;
               }

               if (this.field3.thePlayer.inventory.currentItem == 8) {
                  this.field3.thePlayer.inventory.currentItem = 0;
               }

               this.field3.playerController.windowClick(this.field3.thePlayer.inventoryContainer.windowId, var2, this.field3.thePlayer.inventory.currentItem, 2, this.field3.thePlayer);
            } else if (this.Y_ > 20) {
               this.field1.method23();
            }
         }

      }
   }

   public Class163() {
      super("Item Swap");
   }

   public boolean method4() {
      return this.Y_ < 5 && this.field1.method21();
   }
}
