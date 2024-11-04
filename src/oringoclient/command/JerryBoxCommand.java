package oringo.command;

import map.Class350;
import map.Class361;
import map.Class447;
import map.Class514;
import map.Class94;
import map.Class95;
import map.Class99;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class468;
import oringo.module.SecretHitboxesModule;
import oringo.module.ServerRotationsModule;
import oringo.notification.Notifications;

public class JerryBoxCommand extends Command {
   public static final Class447 field0 = new Class447();
   public static boolean field1;

   public boolean method2() {
      return field9.thePlayer.getHeldItem() != null && field9.thePlayer.getHeldItem().getDisplayName().contains("Jerry Box");
   }

   public String method1() {
      return "Opens jerry boxes";
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field1) {
         if (var1.field0 instanceof S2DPacketOpenWindow && ((S2DPacketOpenWindow)var1.field0).getWindowTitle().getFormattedText().contains("Jerry Box")) {
            var1.method9();
            field9.playerController.windowClick(((S2DPacketOpenWindow)var1.field0).getWindowId(), 22, 2, 3, field9.thePlayer);
            field9.getNetHandler().addToSendQueue(new C0DPacketCloseWindow(((S2DPacketOpenWindow)var1.field0).getWindowId()));
            if (this.method2()) {
               Class361.method0((Class94)(new Class350()));
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (field1) {
         var1.method1(0.0F).method0(0.0F).method1(false).method0(false);
      }

   }

   public static void method0(float var0, float var1, float var2, float var3, float var4) {
      Class99.field0.method5();
      Class99.field0.method0("multiplier", var0, var1, var2, var3);
      Class99.field0.method0("ratio", var4);
   }

   public void method0(String[] var1) {
      field1 = !field1;
      SecretHitboxesModule.method0(field1 ? "Started opening!" : "Stopped opening!", 4000);
   }

   public static boolean lambda$onUpdate$0(ItemStack var0) {
      return var0.getDisplayName().contains("Jerry Box");
   }

   public JerryBoxCommand() {
      super("jerrybox");
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (field1) {
         if (!this.method2()) {
            int var2 = Class95.method0(JerryBoxCommand::lambda$onUpdate$0);
            if (var2 == -1) {
               Class514.method0("Unable to find a Jerry Box!", 5000, Notifications.Class1.field0);
               field1 = false;
            } else {
               ServerRotationsModule.method0(var2, field9.thePlayer.inventory.currentItem);
               Class361.method0((Class94)(new Class350()));
            }
         }
      }
   }
}
