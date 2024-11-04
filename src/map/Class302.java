package map;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.module.Category;
import oringo.module.RenderChunkBoundsModule;
import oringo.module.ServerRotationsModule;
import oringo.module.SubCategory;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class Class302 extends Class408 {
   public static final DoubleSetting field0 = (DoubleSetting)(new DoubleSetting("Start index", 9.0D, 9.0D, 35.0D, 1.0D)).method2("The slot index where the module should start looking for items. The top left corner is 9 and bottom right corner is 35 (excluding the hotbar)");
   public static final DoubleSetting co_ = (DoubleSetting)(new DoubleSetting("Armor count", 1.0D, 1.0D, 4.0D, 1.0D)).method2("The amount of armor pieces that should be swapped");

   public static void method2(Packet var0) {
      ((Class222)Oringo.field9.getNetHandler().getNetworkManager()).method0(var0);
   }

   public Class302() {
      super("Armor Swapper", Category.player, SubCategory.qol, "Swap armor from start index on button press");
      this.method0(new Setting[]{co_, field0});
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.method8() && RenderChunkBoundsModule.method5()) {
         for(int var2 = 0; (double)var2 < co_.method0(); ++var2) {
            if (field58.thePlayer.inventoryContainer.getSlot((int)(field0.method0() + (double)var2)).getHasStack()) {
               ItemStack var3 = field58.thePlayer.inventoryContainer.getSlot((int)(field0.method0() + (double)var2)).getStack();
               int var4 = -1;
               if (var3.getItem() instanceof ItemArmor) {
                  var4 = ((ItemArmor)var3.getItem()).armorType;
               } else if (var3.getItem() instanceof ItemSkull) {
                  var4 = 0;
               }

               if (var4 != -1) {
                  ServerRotationsModule.method0((int)(field0.method0() + (double)var2), 0);
                  ServerRotationsModule.method0(5 + var4, 0);
                  ServerRotationsModule.method0((int)(field0.method0() + (double)var2), 0);
               }
            }
         }

      }
   }
}
