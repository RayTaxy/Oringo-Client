package oringo.module;

import map.Class447;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.Oringo;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoReconnectModule extends Module {
   public static final Class447 field0 = new Class447();
   public static String r_;
   public static final DoubleSetting field2 = new DoubleSetting("Delay", 500.0D, 250.0D, 5000.0D, 50.0D);

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.gui instanceof GuiDisconnected) {
         field0.o_();
         ScaledResolution var2 = new ScaledResolution(field58);
         var1.buttonList.add(new AutoReconnectModule$1(this, -21372137, var2.getScaledWidth() / 2 - 100, ((GuiButton)var1.buttonList.get(0)).yPosition + 32, ""));
      }

   }

   @SubscribeEvent
   public void method0(Pre var1) {
      if (var1.gui instanceof GuiDisconnected && var1.button.id == -21372137) {
         this.method1(false);
      }

   }

   public AutoReconnectModule() {
      super("Auto Reconnect", Category.other, SubCategory.other);
      this.method0((Setting[])(new Setting[]{field2}));
   }

   public static boolean method1() {
      return Oringo.field9.thePlayer.moveForward != 0.0F || Oringo.field9.thePlayer.moveStrafing != 0.0F;
   }

   public static Class447 access$000() {
      return field0;
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase != Phase.END) {
         if (field58.currentScreen instanceof GuiDisconnected && r_ != null) {
            if (field0.method0(field2.method0(), true)) {
               field58.displayGuiScreen(new GuiConnecting(new GuiMainMenu(), Minecraft.getMinecraft(), new ServerData("name", r_, false)));
            }
         } else if (field58.getCurrentServerData() != null) {
            r_ = field58.getCurrentServerData().serverIP;
         }

      }
   }

   public static int method0(C0EPacketClickWindow var0) {
      return var0.getUsedButton();
   }
}
