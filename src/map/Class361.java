package map;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.Oringo;

public class Class361 extends Class83 {
   public boolean cN_;
   public final Class447 field5 = new Class447();

   public boolean method3() {
      return (!this.field3.isSingleplayer() || !Oringo.cV_) && (!Class496.field6 || this.field3.thePlayer == null);
   }

   public static double method1(double var0, double var2) {
      return Math.sqrt(var0 * var0 + var2 * var2);
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      String var2 = ChatFormatting.stripFormatting(var1.message.getUnformattedText());
      if (var2.startsWith("You've been sent to limbo") || var2.startsWith("You cannot join SkyBlock from here")) {
         this.cN_ = true;
      }

   }

   public Class361() {
      super("Wrong Island");
   }

   public boolean method4() {
      return false;
   }

   public void o_() {
      super.o_();
      this.field5.method0(6000L);
   }

   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START) {
         if (!this.method3()) {
            this.cN_ = false;
            this.field1.method23();
            this.field1.method16();
         } else {
            if (this.field5.method0(7500L, true)) {
               if (this.field3.thePlayer == null) {
                  this.field3.displayGuiScreen(new GuiConnecting(this.field3.currentScreen, this.field3, new ServerData("Hypixel", "play.hypixel.net", false)));
                  return;
               }

               this.field3.thePlayer.sendChatMessage(Class496.field7 ? "/warp garden" : (this.cN_ ? "/lobby" : "/skyblock"));
               this.cN_ = !this.cN_;
            }

         }
      }
   }

   public static boolean method0(Class94 var0) {
      if (Class263.field0 == null && var0.method2()) {
         Class263.field0 = var0;
         return true;
      } else {
         return false;
      }
   }

   public void method2() {
      this.method0(true);
   }
}
