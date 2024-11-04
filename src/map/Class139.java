package map;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.module.AimAssistModule;
import oringo.module.BlockHitModule;
import oringo.module.RenderChunkBoundsModule;

public class Class139 extends Class83 {
   public boolean field4 = false;
   public final Class447 field5 = new Class447();
   public final Class447 field6 = new Class447(0L);
   public boolean field7 = false;
   public Class139.Class0 field8;

   public Class139() {
      super("Fermento");
   }

   public void o_() {
      this.field8 = Class139.Class0.field1;
      this.field5.o_();
      this.field7 = false;
      this.field4 = false;
   }

   public static boolean lambda$onTick$0(ItemStack var0) {
      return "CONDENSED_FERMENTO".equals(BlockHitModule.method0(var0));
   }

   public boolean method3() {
      if (this.field3.thePlayer != null && this.field6.method0(3L, TimeUnit.MINUTES) && AimAssistModule.method3() && RenderChunkBoundsModule.method5() && Class496.field6 && this.field1.method20().method1()) {
         int var1 = 0;
         Iterator var2 = this.field3.thePlayer.inventoryContainer.inventorySlots.iterator();

         while(var2.hasNext()) {
            Slot var3 = (Slot)var2.next();
            if (var3.getHasStack()) {
               ItemStack var4 = var3.getStack();
               if ("CONDENSED_FERMENTO".equals(BlockHitModule.method0(var4))) {
                  ++var1;
               }
            }
         }

         return var1 >= this.field1.method29();
      } else {
         return false;
      }
   }

   public boolean method4() {
      return false;
   }

   public void method0(ClientTickEvent var1) {
      // $FF: Couldn't be decompiled
   }

   public static double method0(double var0, double var2, double var4) {
      return var0 + (var2 - var0) * var4;
   }

   private static enum Class0 {
      field0,
      field1;

      private static final Class139.Class0[] field3 = new Class139.Class0[]{field1, field2, field0};
      field2;
   }
}
