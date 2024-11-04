package oringo.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import map.Class374;
import map.Class408;
import map.Class434;
import map.Class447;
import map.Class56;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import oringo.event.Class156;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class AutoSalvageModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("- Dungeon Trash", true, this::lambda$new$7);
   public final String[] field1 = new String[]{"ARACK", "ARACHNE_HELMET", "ARACHNE_CHESTPLATE", "ARACHNE_LEGGINGS", "ARACHNE_BOOTS", "ARACHNE_BELT", "ARACHNE_NECKLACE", "ARACHNE_GLOVES", "ARACHNE_BELT"};
   public boolean field2 = false;
   public final BooleanSetting field3 = new BooleanSetting("Auto Sell", false);
   public final Class447 field4 = new Class447();
   public final BooleanSetting field5 = new BooleanSetting("- Weights", true, this::lambda$new$4);
   public final BooleanSetting field6 = new BooleanSetting("- Healing VIII", true, this::lambda$new$1);
   public final String[] field7 = new String[]{"BLADE_OF_THE_VOLCANO", "STAFF_OF_THE_VOLCANO", "TAURUS_HELMET", "FLAMING_CHESTPLATE", "MOOGMA_LEGGINGS", "SLUG_BOOTS"};
   public final BooleanSetting field8 = new BooleanSetting("Salvage 50/50 items", false);
   public final BooleanSetting field9 = new BooleanSetting("- Journals", true, this::lambda$new$2);
   public final BooleanSetting field10 = new BooleanSetting("- Custom", false, this::lambda$new$8);
   public final BooleanSetting field11 = new BooleanSetting("- Crimson Items", false);
   public final BooleanSetting field12 = new BooleanSetting("- Tripwire Hooks", true, this::lambda$new$6);
   public final BooleanSetting field13 = new BooleanSetting("- Superbooms", false, this::lambda$new$3);
   public final BooleanSetting field14 = new BooleanSetting("- Arachne Items", false);
   public final BooleanSetting field15 = new BooleanSetting("Sell in all shops", false, this::lambda$new$0);
   public final BooleanSetting field16 = new BooleanSetting("- Spirit leaps", true, this::lambda$new$5);
   public final StringSetting field17 = (StringSetting)(new StringSetting("IDs", "", this::lambda$new$9)).method2("Separate with ';'");

   public boolean method0(ItemStack var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = BlockHitModule.method0(var1);
         if (var2 == null) {
            return false;
         } else if (var1.getDisplayName().indexOf(10026) != -1) {
            return false;
         } else if (this.field11.method1() && Arrays.asList(this.field7).contains(var2)) {
            return true;
         } else if (this.field14.method1() && Arrays.asList(this.field1).contains(var2)) {
            return true;
         } else {
            int var3 = ReachModule.method0(var1);
            if (var3 == -1) {
               return false;
            } else if (var3 == 50 && !this.field8.method1()) {
               return false;
            } else if ("ICE_SPRAY_WAND".equals(var2)) {
               return false;
            } else {
               return var2.startsWith("BOUNCY_") ? false : Class408.method1(var1).stream().anyMatch(AutoSalvageModule::lambda$isItemValid$10);
            }
         }
      }
   }

   public static boolean lambda$isItemValid$10(String var0) {
      return var0.startsWith("§aPerfect ") && var0.contains(" / ");
   }

   public Boolean lambda$new$5() {
      return !this.field3.method1();
   }

   public AutoSalvageModule() {
      super("Auto Salvage", Category.dungeon, SubCategory.main, "Automatically salvages your dungeon items without stars");
      this.method0(new Setting[]{this.field11, this.field14, this.field8, this.field3, this.field15, this.field6, this.field9, this.field13, this.field5, this.field16, this.field12, this.field0, this.field10, this.field17});
   }

   public static void method6() {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableTexture2D();
      Class434.ap_ = Class56.method0(Class434.ap_, 9729);
      Class434.field8 = Class56.method0(Class434.field8, 9729);
      Class434.field0 = Class434.field6.getFramebuffer().framebufferTexture;

      int var0;
      for(var0 = 0; var0 < 2; ++var0) {
         Class434.field0 = AutoTerminalsModule.field0(var0 % 2 == 0 ? Class434.ap_ : Class434.field8, Class434.field0, Class434.field3);
      }

      for(var0 = 0; var0 < 2; ++var0) {
         Class434.field0 = AutoTerminalsModule.field0(var0 % 2 == 0 ? Class434.ap_ : Class434.field8, Class434.field0, Class434.field7);
      }

      Class434.field6.getFramebuffer().bindFramebuffer(false);
      GlStateManager.disableBlend();
   }

   public Boolean lambda$new$2() {
      return !this.field3.method1();
   }

   @SubscribeEvent
   public void field0(Class156 var1) {
      String var2 = Class374.method0();
      Slot var4;
      if (!"Salvage Item".equals(var2) || this.field4.method0(200L, true)) {
         Iterator var3;
         if ("Salvage Item".equals(var2)) {
            var3 = field58.thePlayer.openContainer.inventorySlots.iterator();

            while(var3.hasNext()) {
               var4 = (Slot)var3.next();
               if (var4.slotNumber == 22 && var4.getHasStack()) {
                  if (this.method0(var4.getStack())) {
                     field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 31, 0, 0, field58.thePlayer);
                  } else {
                     field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 22, 0, 1, field58.thePlayer);
                  }
                  break;
               }

               if (var4.slotNumber >= 54 && this.method0(var4.getStack())) {
                  field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, var4.slotNumber, 0, 1, field58.thePlayer);
                  break;
               }
            }
         }

         if (this.field3.method1() && this.method1(var2)) {
            var3 = field58.thePlayer.openContainer.inventorySlots.iterator();

            while(var3.hasNext()) {
               var4 = (Slot)var3.next();
               if (var4.slotNumber >= 54 && this.method1(var4.getStack())) {
                  field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, var4.slotNumber, 2, 3, field58.thePlayer);
                  break;
               }
            }
         }
      }

      GuiScreen var7 = field58.currentScreen;
      if (this.field10.method1() && Mouse.isButtonDown(2) && Keyboard.isKeyDown(42) && !this.field2 && var7 instanceof GuiContainer) {
         var4 = ((GuiContainer)var7).getSlotUnderMouse();
         if (var4 != null && var4.getHasStack()) {
            String var5 = BlockHitModule.method0(var4.getStack());
            if (var5 == null) {
               return;
            }

            ArrayList var6 = new ArrayList(Arrays.asList(this.field17.method1().split(";")));
            var6.removeIf(String::isEmpty);
            if (!Arrays.asList(this.field17.method1().split(";")).contains(var5)) {
               var6.add(var5);
               this.field17.method1(String.join(";", var6));
               method2(String.format("Added %s to auto sell items!", var5));
            } else {
               var6.remove(var5);
               this.field17.method1(String.join(";", var6));
               method2(String.format("Removed %s from auto sell items!", var5));
            }
         }
      }

      this.field2 = Mouse.isButtonDown(2);
   }

   public Boolean lambda$new$1() {
      return !this.field3.method1();
   }

   public Boolean lambda$new$8() {
      return !this.field3.method1();
   }

   public Boolean lambda$new$7() {
      return !this.field3.method1();
   }

   public Boolean lambda$new$4() {
      return !this.field3.method1();
   }

   public Boolean lambda$new$9() {
      return !this.field3.method1() || !this.field10.method1();
   }

   public Boolean lambda$new$3() {
      return !this.field3.method1();
   }

   public boolean method1(String var1) {
      if (field58.thePlayer == null) {
         return false;
      } else if ("Trades".equals(var1)) {
         return true;
      } else {
         if (this.field15.method1() && field58.thePlayer.openContainer != null && field58.thePlayer.openContainer.inventorySlots.size() > 49) {
            Slot var2 = (Slot)field58.thePlayer.openContainer.inventorySlots.get(49);
            ItemStack var3 = var2.getStack();
            if (var3 != null) {
               if (var3.getDisplayName().equals("§aSell Item")) {
                  return true;
               }

               return Class408.method1(var3).contains("§eClick to buyback!");
            }
         }

         return false;
      }
   }

   public static void method14() {
      GlStateManager.doPolygonOffset(1.0F, 1000000.0F);
      GlStateManager.disablePolygonOffset();
   }

   public boolean method1(ItemStack var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = BlockHitModule.method0(var1);
         if (var2 == null) {
            return false;
         } else {
            String var3 = var1.getDisplayName();
            if (var3.contains("Healing VIII Splash Potion") && this.field6.method1()) {
               return true;
            } else if (var3.contains("Journal Entry") && this.field9.method1()) {
               return true;
            } else if (var3.contains("Superboom TNT") && this.field13.method1()) {
               return true;
            } else if (var3.contains("Spirit Leap") && this.field16.method1()) {
               return true;
            } else if (var3.contains("Weights") && this.field5.method1()) {
               return true;
            } else if (var3.contains("Tripwire Hook") && this.field12.method1()) {
               return true;
            } else if (this.field10.method1() && Arrays.asList(this.field17.method1().split(";")).contains(var2)) {
               return true;
            } else {
               return this.field0.method1() && this.method0(var1);
            }
         }
      }
   }

   public Boolean lambda$new$0() {
      return !this.field3.method1();
   }

   public Boolean lambda$new$6() {
      return !this.field3.method1();
   }
}
