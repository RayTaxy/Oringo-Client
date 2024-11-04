package oringo.module;

import map.Class306;
import map.Class462;
import map.Class7;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class437;
import oringo.setting.BooleanSetting;
import oringo.setting.KeyBindSetting;
import oringo.setting.Setting;

public class GuiMoveModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("No Containers", false);
   public final BooleanSetting field1 = new BooleanSetting("Disable in Melody", false);
   public static final KeyBinding[] field2;
   public final BooleanSetting field3 = new BooleanSetting("Only terminals", false);
   public final BooleanSetting field4 = new BooleanSetting("Sneak", true);
   public final BooleanSetting field5 = new BooleanSetting("Hide terminals", false);
   public final KeyBindSetting field6 = new KeyBindSetting("Hide gui", GuiMoveModule::lambda$new$0);

   public void b_() {
      if (field58.currentScreen != null) {
         KeyBinding[] var1 = field2;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            KeyBinding var4 = var1[var3];
            KeyBinding.setKeyBindState(var4.getKeyCode(), false);
         }
      }

      this.field6.method1(false);
   }

   public static Boolean lambda$new$0() {
      return field58.currentScreen instanceof GuiChest;
   }

   public static String method0(C01PacketChatMessage var0) {
      return var0.getMessage();
   }

   public boolean method0(ContainerChest var1) {
      String var2 = var1.getLowerChestInventory().getName();
      return this.method44() && (this.field6.method1() || Class462.method0(var2) && (!this.field1.method1() || !var2.contains("Click the button on time!")) && this.field5.method1());
   }

   @SubscribeEvent
   public void method0(Class437 var1) {
      this.method0(var1.field0);
   }

   public void method0(Gui var1) {
      if (var1 != null) {
         if (!(var1 instanceof GuiChat) && !(var1 instanceof GuiEditSign) && !(var1 instanceof Class7) && !(var1 instanceof Class306)) {
            if (!this.field0.method1() || !(field58.currentScreen instanceof GuiContainer)) {
               if (!this.field3.method1() || DerpModule.method0(var1)) {
                  if (!this.field1.method1() || !FlightModule.method0(var1)) {
                     KeyBinding[] var2 = field2;
                     int var3 = var2.length;

                     for(int var4 = 0; var4 < var3; ++var4) {
                        KeyBinding var5 = var2[var4];
                        KeyBinding.setKeyBindState(var5.getKeyCode(), (var5.getKeyCode() != field58.gameSettings.keyBindSneak.getKeyCode() || this.field4.method1()) && GameSettings.isKeyDown(var5));
                     }

                  }
               }
            }
         }
      }
   }

   static {
      field2 = new KeyBinding[]{field58.gameSettings.keyBindSneak, field58.gameSettings.keyBindJump, field58.gameSettings.keyBindSprint, field58.gameSettings.keyBindForward, field58.gameSettings.keyBindBack, field58.gameSettings.keyBindLeft, field58.gameSettings.keyBindRight};
   }

   public GuiMoveModule() {
      super("Gui Move", Category.movement, SubCategory.qol, "Allows you to move in guis");
      this.method0((Setting[])(new Setting[]{this.field4, this.field5, this.field0, this.field1, this.field3, this.field6}));
   }

   @SubscribeEvent
   public void method0(GuiOpenEvent var1) {
      if (var1.gui == null) {
         this.field6.method1(false);
      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      this.method0((Gui)field58.currentScreen);
   }
}
