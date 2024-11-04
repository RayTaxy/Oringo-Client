package oringo.command;

import map.Class514;
import oringo.module.AutoRabbitModule;
import oringo.notification.Notifications;

public class HClipCommand extends Command {
   public void method0(String[] var1) {
      if (var1.length > 0) {
         double var2 = Math.toRadians((double)field9.thePlayer.rotationYaw);
         double var4 = Double.parseDouble(var1[0]);
         field9.thePlayer.setPosition(field9.thePlayer.posX + -Math.sin(var2) * var4, field9.thePlayer.posY - (var1.length == 2 ? Double.parseDouble(var1[1]) : 0.0D), field9.thePlayer.posZ + Math.cos(var2) * var4);
         field9.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
      } else {
         Class514.method0(String.format("%shclip distance y offset", AutoRabbitModule.method1()), 2500, Notifications.Class1.field0);
      }

   }

   public HClipCommand() {
      super("hclip");
   }

   public String method1() {
      return "clips you forward x blocks";
   }
}
