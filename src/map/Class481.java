package map;

import com.google.common.collect.Lists;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class75;
import oringo.module.Category;
import oringo.module.RenderChunkBoundsModule;
import oringo.module.SubCategory;
import oringo.setting.BooleanSetting;

public class Class481 extends Class408 {
   public final List field0 = Lists.newArrayList();

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.method8() && RenderChunkBoundsModule.method5()) {
         int var2 = -1;
         Iterator var3 = this.field0.iterator();

         while(var3.hasNext()) {
            BooleanSetting var4 = (BooleanSetting)var3.next();
            ++var2;
            if (var4.method1()) {
               field58.playerController.windowClick(0, 27 + var2, var2, 2, field58.thePlayer);
            }
         }

      }
   }

   public static int B_(String var0) {
      ArrayList var1 = new ArrayList(Oringo.field9.thePlayer.inventoryContainer.inventorySlots);
      Collections.reverse(var1);
      Iterator var2 = var1.iterator();

      Slot var3;
      do {
         if (!var2.hasNext()) {
            return -1;
         }

         var3 = (Slot)var2.next();
      } while(!var3.getHasStack() || !ChatFormatting.stripFormatting(var3.getStack().getDisplayName()).toLowerCase().contains(var0.toLowerCase()));

      return var3.slotNumber;
   }

   public Class481() {
      super("Hotbar Swapper", Category.skyblock, SubCategory.qol, "Swaps items in your hotbar with the items above");

      for(int var1 = 1; var1 <= 9; ++var1) {
         BooleanSetting var2 = new BooleanSetting("Slot " + var1, false);
         this.method0(var2);
         this.field0.add(var2);
      }

   }
}
