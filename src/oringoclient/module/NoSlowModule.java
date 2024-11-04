package oringo.module;

import map.Class500;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class NoSlowModule extends Module {
   public final EnumSetting an_ = new EnumSetting("Mode", "Vanilla", new String[]{"Hypixel", "NCP", "Vanilla"});
   public final DoubleSetting ao_ = new DoubleSetting("Bow slow", 1.0D, 0.2D, 1.0D, 0.1D);
   public final BooleanSetting field2 = new BooleanSetting("Sprint", true);
   public boolean field0;
   public final DoubleSetting field4 = new DoubleSetting("Sword slow", 1.0D, 0.2D, 1.0D, 0.1D);
   public final DoubleSetting field5 = new DoubleSetting("Eating slow", 1.0D, 0.2D, 1.0D, 0.1D);

   public NoSlowModule() {
      super("No Slow", Category.movement, SubCategory.movement, "Allows you to modify the slowdown when using items");
      this.method0((Setting[])(new Setting[]{this.an_, this.field2, this.field4, this.ao_, this.field5}));
      EnumSetting var10001 = this.an_;
      this.method0((Class500)(var10001::method4));
   }

   public static String method0(StringBuilder var0) {
      return var0.toString();
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class210.Class1 var1) {
      if (field58.thePlayer.isUsingItem()) {
         String var2 = this.an_.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1248403467:
            if (var2.equals("Hypixel")) {
               var3 = 0;
            }
            break;
         case 77115:
            if (var2.equals("NCP")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
            if (field58.thePlayer.isUsingItem()) {
               short var4 = field58.thePlayer.inventoryContainer.getNextTransactionID(field58.thePlayer.inventory);
               method3(new C0DPacketCloseWindow(0));
               method3(new C0EPacketClickWindow(0, 3, field58.thePlayer.inventory.currentItem, 2, (ItemStack)null, var4));
               this.field0 = true;
            }
            break;
         case 1:
            method3(new C07PacketPlayerDigging(Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
         }

      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class75 var1) {
      if (field58.thePlayer.isUsingItem() || this.field0) {
         String var2 = this.an_.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1248403467:
            if (var2.equals("Hypixel")) {
               var3 = 0;
            }
            break;
         case 77115:
            if (var2.equals("NCP")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
            if (this.field0) {
               this.field0 = false;
               short var4 = field58.thePlayer.inventoryContainer.getNextTransactionID(field58.thePlayer.inventory);
               method3(new C0EPacketClickWindow(0, 3, field58.thePlayer.inventory.currentItem, 2, (ItemStack)null, var4));
               method3(new C0DPacketCloseWindow(0));
            }
            break;
         case 1:
            method3(new C08PacketPlayerBlockPlacement(field58.thePlayer.getHeldItem()));
         }

      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field58.thePlayer != null && field58.thePlayer.isUsingItem() && this.an_.method0(0)) {
         if (var1.field0 instanceof S2FPacketSetSlot) {
            var1.method9();
         } else if (var1.field0 instanceof S30PacketWindowItems) {
            var1.method9();
         }

      }
   }
}
