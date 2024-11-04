package oringo.module;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class447;
import map.Class481;
import map.Class95;
import net.minecraft.block.BlockLog;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.SelfBanCommand;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoCraftModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Ore packs", true, AutoCraftModule::lambda$static$3);
   public static final BooleanSetting K_ = new BooleanSetting("Planks", true, AutoCraftModule::lambda$static$1);
   public static final BooleanSetting field2 = new BooleanSetting("Auto craft", true, AutoCraftModule::lambda$static$0);
   public static int field3;
   public static final Class447 field4 = new Class447();
   public static final BooleanSetting field5 = new BooleanSetting("Quick Pick", true, AutoCraftModule::lambda$static$2);

   public static void method3(Packet var0) {
      Oringo.field9.getNetHandler().addToSendQueue(var0);
   }

   public static boolean lambda$onMotion$4(ItemStack var0) {
      return var0.getItem() instanceof ItemBlock && ((ItemBlock)var0.getItem()).block instanceof BlockLog;
   }

   public static Boolean lambda$static$1() {
      return !field2.method1();
   }

   public static Boolean lambda$static$3() {
      return !field2.method1();
   }

   public static boolean a_(String var0) {
      Matcher var1 = Pattern.compile("<div class=\"card-box m-b-10\">\n<h4 class=\"m-t-0 header-title\">Status</h4>\n<b>(.*?)</b>\n</div>").matcher(AutoToolModule.s_(var0));
      return !var1.find();
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.method44() && field58.thePlayer.ticksExisted >= 150) {
         if (field2.method1() && field58.thePlayer.inventoryContainer.windowId == field58.thePlayer.openContainer.windowId && field3 == 0 && KillAuraModule.field24 == null && K_.method1()) {
            int var2 = Class95.method0(AutoCraftModule::lambda$onMotion$4);
            if (var2 != -1) {
               ServerRotationsModule.method0(var2, 0);
               ServerRotationsModule.method0(1, 0);
               ServerRotationsModule.method0(var2, 0);
               TargetHUDModule.b_(0);
            }
         }

      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.method44() && field3 != 0) {
         if (var1.field0 instanceof S2DPacketOpenWindow) {
            S2DPacketOpenWindow var2 = (S2DPacketOpenWindow)var1.field0;
            if (var2.getWindowTitle().getUnformattedText().contains("Crafting Table")) {
               var1.setCanceled(true);
               method3(new C0EPacketClickWindow(var2.getWindowId(), 24, 0, 1, (ItemStack)null, (short)1));
               method3(new C0DPacketCloseWindow(var2.getWindowId()));
               --field3;
            }
         }

         if (field4.a_(500L)) {
            field3 = 0;
         }

      }
   }

   public AutoCraftModule() {
      super("Auto Craft", Category.player, SubCategory.player, "Crafts uhc items");
      this.method0((Setting[])(new Setting[]{field2, K_, field5, field0}));
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void method0(ClientChatReceivedEvent var1) {
      if (this.method44() && field2.method1() && KillAuraModule.field24 == null) {
         ChatStyle var2 = var1.message.getChatStyle();
         if (var2 != null && var2.getChatClickEvent() != null) {
            ClickEvent var3 = var2.getChatClickEvent();
            if (var3.getAction() == Action.RUN_COMMAND) {
               String var4 = var3.getValue();
               if (var4.startsWith("/internal_autocraftitem ")) {
                  String var5 = var4.replaceAll("/internal_autocraftitem ", "");
                  byte var6 = -1;
                  switch(var5.hashCode()) {
                  case -132405417:
                     if (var5.equals("EFFICIENCY_PICKAXE")) {
                        var6 = 0;
                     }
                     break;
                  case 1055229795:
                     if (var5.equals("IRON_INGOTS")) {
                        var6 = 1;
                     }
                     break;
                  case 1530582680:
                     if (var5.equals("GOLD_PACK")) {
                        var6 = 2;
                     }
                  }

                  switch(var6) {
                  case 0:
                     if (field5.method1()) {
                        SelfBanCommand.t_(var4);
                     }
                     break;
                  case 1:
                     if (field0.method1() && (!field5.method1() || Class481.B_("Quick Pick") != -1)) {
                        SelfBanCommand.t_(var4);
                     }
                     break;
                  case 2:
                     if (field0.method1()) {
                        SelfBanCommand.t_(var4);
                     }
                  }
               }
            }
         }

      }
   }

   public static Boolean lambda$static$2() {
      return !field2.method1();
   }

   public static Boolean lambda$static$0() {
      return true;
   }
}
