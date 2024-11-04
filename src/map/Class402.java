package map;

import java.util.function.Consumer;
import net.minecraft.item.ItemStack;
import oringo.module.RenderChunkBoundsModule;

public class Class402 implements Class94 {
   public final Consumer field1;
   public final int field2;

   public Class402(int var1, Consumer var2) {
      this.field2 = var1;
      if (var1 >= 9 && var1 <= 45) {
         this.field1 = var2;
      } else {
         throw new IllegalArgumentException("Invalid slot! " + var1);
      }
   }

   public boolean method2() {
      return !field0.playerController.getIsHittingBlock() && !field0.playerController.getIsHittingBlock() && (this.field2 >= 36 || RenderChunkBoundsModule.method5());
   }

   public void method1() {
      if (this.field2 - 36 == field0.thePlayer.inventory.currentItem) {
         Class426.method10();
         this.field1.accept(field0.thePlayer.getHeldItem());
      } else if (this.field2 >= 36) {
         field0.thePlayer.inventory.currentItem = this.field2 - 36;
         Class426.method10();
         this.field1.accept(field0.thePlayer.inventory.getStackInSlot(this.field2 - 36));
      } else {
         ItemStack var1 = field0.thePlayer.inventoryContainer.getSlot(this.field2).getStack();
         field0.playerController.windowClick(field0.thePlayer.inventoryContainer.windowId, this.field2, field0.thePlayer.inventory.currentItem, 2, field0.thePlayer);
         this.field1.accept(var1);
         field0.playerController.windowClick(field0.thePlayer.inventoryContainer.windowId, this.field2, field0.thePlayer.inventory.currentItem, 2, field0.thePlayer);
      }

   }

   public void method0() {
   }

   public boolean method3() {
      return true;
   }

   public void method4() {
   }
}
