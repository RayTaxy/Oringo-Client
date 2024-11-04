package oringo.module;

import java.util.Arrays;
import java.util.regex.Pattern;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class332;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class ChatBypassModule extends Module {
   public final String[] field0 = new String[]{"nigger", "kurwa"};
   public String field1 = "";
   public static final String field2 = "ｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍ０１２３４５６７８９";
   public final Pattern[] field3 = new Pattern[]{Pattern.compile("\\bez+\\b")};
   public final EnumSetting field4 = new EnumSetting("mode", "font", new String[]{"font", "dots"});
   public static final String field5 = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (this.method44()) {
         String var2 = var1.message.getUnformattedText();
         if (var1.message.getFormattedText().equals("§r§6§m-----------------------------------------§r")) {
            var1.setCanceled(true);
         }

         if (var2.startsWith("We blocked your comment \"")) {
            StringBuilder var3 = new StringBuilder();

            for(int var4 = 1; var4 < var2.split("\"").length - 1; ++var4) {
               var3.append(var2.split("\"")[var4]);
            }

            String var5 = this.field1;
            var1.setCanceled(true);
            (new Thread(this::lambda$onChat$2)).start();
         }

      }
   }

   public void lambda$onChat$2(String var1, StringBuilder var2) {
      try {
         Thread.sleep(550L);
      } catch (InterruptedException var4) {
         var4.printStackTrace();
      }

      this.method1(var1, var2.toString());
   }

   public static boolean lambda$onPacket$1(String var0, Pattern var1) {
      return var1.matcher(var0.toLowerCase()).find();
   }

   public static boolean lambda$onPacket$0(String var0, String var1) {
      return var0.toLowerCase().contains(var1);
   }

   public static void method0(float var0, float var1) {
      if (AutoReconnectModule.method1()) {
         Oringo.field9.thePlayer.motionX = -Math.sin(Math.toRadians((double)var1)) * (double)var0;
         Oringo.field9.thePlayer.motionZ = Math.cos(Math.toRadians((double)var1)) * (double)var0;
      }
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C01PacketChatMessage) {
         this.field1 = "";
         String var2 = ((C01PacketChatMessage)var1.field0).getMessage();
         if (var2.charAt(0) == '/') {
            this.field1 = var2.split(" ")[0];
            if (this.field1.startsWith("/tip")) {
               return;
            }

            if (this.field1.equalsIgnoreCase("/msg") || this.field1.equalsIgnoreCase("/message") || this.field1.equalsIgnoreCase("/t") || this.field1.equalsIgnoreCase("/tell") || this.field1.equalsIgnoreCase("/w")) {
               this.field1 = this.field1 + " ";
               if (var2.split(" ").length > 1) {
                  this.field1 = this.field1 + var2.split(" ")[1];
               }
            }
         }

         if (!this.method44()) {
            return;
         }

         if (Arrays.stream(this.field0).anyMatch(ChatBypassModule::lambda$onPacket$0)) {
            var1.method9();
            this.method1(this.field1, this.field1.isEmpty() ? var2 : var2.replaceFirst(this.field1 + " ", ""));
            return;
         }

         if (Arrays.stream(this.field3).anyMatch(ChatBypassModule::lambda$onPacket$1)) {
            var1.method9();
            this.method1(this.field1, this.field1.isEmpty() ? var2 : var2.replaceFirst(this.field1 + " ", ""));
            return;
         }
      }

   }

   public void method1(String var1, String var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append(var1).append(" ");

      for(int var4 = 0; var4 < var2.length(); ++var4) {
         char var5 = var2.charAt(var4);
         String var6 = this.field4.method4();
         byte var7 = -1;
         switch(var6.hashCode()) {
         case 3089482:
            if (var6.equals("dots")) {
               var7 = 0;
            }
            break;
         case 3148879:
            if (var6.equals("font")) {
               var7 = 1;
            }
         }

         switch(var7) {
         case 0:
            var3.append(var5).append(var5 == ' ' ? "" : "ˌ");
            break;
         case 1:
            var3.append("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789".contains(String.valueOf(var5)) ? "ｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍｑｗｅｒｔｙｕｉｏｐａｓｄｆｇｈｊｋｌｚｘｃｖｂｎｍ０１２３４５６７８９".toCharArray()["qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789".indexOf(var5)] : var5);
         }
      }

      field58.thePlayer.sendChatMessage(var3.toString());
   }

   public ChatBypassModule() {
      super("Chat Bypass", Category.other, SubCategory.other, "Bypass the hypixel chat filter");
      this.method0((Setting[])(new Setting[]{this.field4}));
   }
}
