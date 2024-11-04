package oringo.module;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import map.Class374;
import map.Class447;
import map.Class528;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class156;
import oringo.event.Class189;
import oringo.event.Class437;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoEnchantingModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Auto close", true);
   public static final List F_ = new ArrayList();
   public static final Class447 field2 = new Class447();
   public boolean field3 = false;
   public static final DoubleSetting field4 = new DoubleSetting("Serum", 0.0D, 0.0D, 3.0D, 1.0D);
   public static final DoubleSetting field5 = new DoubleSetting("Delay", 200.0D, 0.0D, 500.0D, 10.0D);

   public static void lambda$onUpdate$1(Slot var0) {
      F_.add(var0.slotNumber);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      String var2 = Class374.method0();
      ContainerChest var3 = Class528.method1();
      if (var1.field0 instanceof S2FPacketSetSlot && var2 != null && var3 != null) {
         ItemStack var4 = ((S2FPacketSetSlot)var1.field0).func_149174_e();
         int var5 = ((S2FPacketSetSlot)var1.field0).func_149173_d();
         if (var4 != null && var5 / 9 % 2 == 1 && var3.getLowerChestInventory().getSizeInventory() > var5 && var2.startsWith("Chronomatron ")) {
            ItemStack var6 = Class528.method1().getSlot(49).getStack();
            if (var6 != null && var6.getItem() != Items.clock) {
               if (var4.isItemEnchanted() && !this.field3) {
                  F_.add(var5);
                  this.field3 = true;
               } else {
                  this.field3 = false;
               }
            }
         }

      }
   }

   public static double method0(double var0, double var2, float var4) {
      return var0 + (var2 - var0) * (double)var4;
   }

   @SubscribeEvent
   public void field0(Class156 var1) {
      if (field58.thePlayer != null) {
         ContainerChest var2 = var1.field0;
         String var3 = var2.getLowerChestInventory().getName();
         ItemStack var4;
         if (var3.startsWith("Ultrasequencer ")) {
            var4 = var2.getSlot(49).getStack();
            if (var4 != null) {
               if (var4.getItem() == Items.clock) {
                  while(!F_.isEmpty() && field2.method0(field5.method0())) {
                     field58.playerController.windowClick(var2.windowId, (Integer)F_.remove(0), 2, 3, field58.thePlayer);
                     field2.o_();
                  }
               } else {
                  field2.o_();
                  ArrayList var5 = new ArrayList();

                  for(int var6 = 9; var6 < 45; ++var6) {
                     Slot var7 = var2.getSlot(var6);
                     if (var7.getHasStack()) {
                        ItemStack var8 = var7.getStack();
                        if (var8.getItem() instanceof ItemDye) {
                           var5.add(var7);
                        }
                     }
                  }

                  var5.sort(Comparator.comparingInt(AutoEnchantingModule::lambda$onUpdate$0));
                  if (!var5.isEmpty()) {
                     Slot var10 = (Slot)var5.get(var5.size() - 1);
                     if (var10.getHasStack() && var10.getStack().stackSize == var5.size()) {
                        if ((double)var5.size() > 9.0D - field4.method0() && field0.method1()) {
                           field58.thePlayer.closeScreen();
                           return;
                        }

                        F_.clear();
                        var5.forEach(AutoEnchantingModule::lambda$onUpdate$1);
                     }
                  }
               }
            }
         } else if (var3.startsWith("Chronomatron ")) {
            var4 = var2.getSlot(4).getStack();
            if (var4 != null && var4.getItem() == Item.getItemFromBlock(Blocks.bookshelf) && (double)var4.stackSize >= 13.0D - field4.method0() && field0.method1()) {
               field58.thePlayer.closeScreen();
               return;
            }

            ItemStack var9 = var2.getSlot(49).getStack();
            if (var9 != null) {
               if (var9.getItem() == Items.clock) {
                  while(!F_.isEmpty() && field2.method0(field5.method0())) {
                     field58.playerController.windowClick(var2.windowId, (Integer)F_.remove(0), 2, 3, field58.thePlayer);
                     field2.o_();
                  }
               } else {
                  field2.o_();
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class437 var1) {
      F_.clear();
   }

   public AutoEnchantingModule() {
      super("Auto Enchanting", Category.skyblock, SubCategory.skills, "Auto experiment");
      this.method0(new Setting[]{field5, field0, field4});
   }

   public static int lambda$onUpdate$0(Slot var0) {
      return !var0.getHasStack() ? 100 : var0.getStack().stackSize;
   }
}
