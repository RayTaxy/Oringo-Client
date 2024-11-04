package oringo.command;

import io.netty.channel.Channel;
import java.awt.Color;
import map.Class262;
import map.Class265;
import map.Class31;
import map.Class39;
import map.Class417;
import map.Class418;
import map.Class480;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.codec.digest.DigestUtils;
import oringo.Oringo;
import oringo.mixin.NetworkManagerAccessor;
import oringo.module.NoRenderModule2;
import oringo.module.PopupAnimationModule;

public class PlusCommand extends Command {
   public static String method0(EnumChatFormatting var0) {
      StringBuilder var1 = new StringBuilder();
      var1.append(var0.name().toLowerCase().replaceAll("_", " "));
      var1.setCharAt(0, Character.toUpperCase(var1.charAt(0)));
      return var1.toString();
   }

   public void method0(String[] var1) {
      if (var1.length != 2) {
         NoRenderModule2.method0("§d.plus redeem [CODE] §7- Redeems the Oringo Plus code");
         NoRenderModule2.method0("§d width (-)0.5-1.5 §7- Changes your model's width");
         NoRenderModule2.method0("§d height 0.5-1.5 §7- Changes your model's height");
         NoRenderModule2.method0("§d color [HEX] §7- Changes your name's color");
         NoRenderModule2.method0("§d cape [NAME] §7- Changes your cape to one of approved capes");
         NoRenderModule2.method0("§d info §7- Check your Oringo plus licence");
      } else {
         Class262 var2 = (Class262)Class265.field1.get(DigestUtils.sha256Hex(field9.getSession().getPlayerID()));
         if (var2 == null) {
            PopupAnimationModule.method2("You don't have an Oringo Plus subscription!");
            return;
         }

         String var3 = var1[0].toLowerCase();
         byte var4 = -1;
         switch(var3.hashCode()) {
         case -1221029593:
            if (var3.equals("height")) {
               var4 = 1;
            }
            break;
         case 3046099:
            if (var3.equals("cape")) {
               var4 = 3;
            }
            break;
         case 94842723:
            if (var3.equals("color")) {
               var4 = 2;
            }
            break;
         case 113126854:
            if (var3.equals("width")) {
               var4 = 0;
            }
         }

         double var5;
         switch(var4) {
         case 0:
            try {
               var5 = Double.parseDouble(var1[1]);
            } catch (NumberFormatException var10) {
               PopupAnimationModule.method2("Not a number!");
               return;
            }

            if (var5 >= 0.5D && var5 <= 1.5D || var5 <= -0.5D && var5 >= -1.5D) {
               var2.method1(var5);
               break;
            }

            PopupAnimationModule.method2("Invalid width!");
            return;
         case 1:
            try {
               var5 = Double.parseDouble(var1[1]);
            } catch (NumberFormatException var9) {
               PopupAnimationModule.method2("Not a number!");
               return;
            }

            if (var5 >= 0.5D && var5 <= 1.5D) {
               var2.method0(var5);
               break;
            }

            PopupAnimationModule.method2("Invalid height!");
            return;
         case 2:
            try {
               var2.method0(Color.decode(var1[1]).getRGB());
               break;
            } catch (Exception var8) {
               PopupAnimationModule.method2("Invalid hex color!");
               return;
            }
         case 3:
            var2.method1(var1[1]);
            PopupAnimationModule.method2("Set the cape");
            break;
         default:
            return;
         }

         ((Class480)field9.thePlayer).method0(var2);
         Oringo.field4.method0(new Class417(var2));
      }

   }

   public PlusCommand() {
      super("plus", "oringoplus");
   }

   public String method1() {
      return "Customization with Oringo Plus";
   }

   public static void method0(NetworkManager var0) {
      ((NetworkManagerAccessor)var0).setEncrypted(true);
      Channel var1 = var0.channel();
      var1.pipeline().addBefore("splitter", "decrypt", new Class31(Class418.field1));
      var1.pipeline().addBefore("prepender", "encrypt", new Class39(Class418.field1));
   }
}
