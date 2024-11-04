package oringo.module;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Iterator;
import java.util.regex.Pattern;
import map.Class412;
import map.Class84;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoDailyModule extends Module {
   public static final Pattern field0 = Pattern.compile("window\\.appData = '(.*?)';");
   public static final Pattern ci_ = Pattern.compile("window\\.securityToken = \"(.*?)\";");
   public final BooleanSetting field2 = new BooleanSetting("Prioritize EXP", true, "Always chooses exp is possible");

   public AutoDailyModule() {
      super("Auto Daily", Category.other, SubCategory.other, "Automatically claims one of the three rewards");
      this.method0((Setting)this.field2);
   }

   public void lambda$handleDaily$0(String var1) {
      try {
         String var2 = var1.split("/")[4];
         String[] var3 = Class412.method0(var2);
         JsonObject var4 = (new JsonParser()).parse(var3[0]).getAsJsonObject();
         int var5 = var4.getAsJsonObject("dailyStreak").get("score").getAsInt() + 1;
         AutoDailyModule.Class0[] var6 = (AutoDailyModule.Class0[])(new Gson()).fromJson(var4.get("rewards").getAsJsonArray(), AutoDailyModule.Class0[].class);
         int var7 = -1;
         int var8 = 0;

         for(int var9 = 0; var9 < var6.length; ++var9) {
            AutoDailyModule.Class0 var10 = var6[var9];
            if (this.field2.method1() && AutoDailyModule.Class0.field2(var10).equals("experience")) {
               var8 = var9;
               break;
            }

            method3(AutoDailyModule.Class0.field1(var10) + ": " + AutoDailyModule.Class0.field2(var10));
            int var11 = AutoDailyModule.Class0.field0(var10).ordinal();
            if (AutoDailyModule.Class0.field2(var10).equals("adsense_token")) {
               ++var11;
            }

            if (var7 < var11) {
               var7 = var11;
               var8 = var9;
            }
         }

         if (Class84.method0(var8, var2, var3[1], var3[2])) {
            method2("Daily reward claimed! New streak: §r" + var5);
         }
      } catch (Exception var12) {
         var12.printStackTrace();
      }

   }

   public void method1(String var1) {
      (new Thread(this::lambda$handleDaily$0)).start();
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getFormattedText().startsWith("\n§r§6Click the link to visit our website and claim your reward:")) {
         Iterator var2 = var1.message.getSiblings().iterator();

         while(var2.hasNext()) {
            IChatComponent var3 = (IChatComponent)var2.next();
            ChatStyle var4 = var3.getChatStyle();
            if (var4 != null && var4.getChatClickEvent() != null) {
               this.method1(var4.getChatClickEvent().getValue());
            }
         }

      }
   }

   public static void method5() {
      CakeNukerModule.method0(StepModule.method5());
   }

   public static short method0(C0EPacketClickWindow var0) {
      return var0.getActionNumber();
   }

   public static enum Class1 {
      field0,
      field1,
      field2;

      private static final AutoDailyModule.Class1[] field4 = new AutoDailyModule.Class1[]{field0, field1, field3, field2};
      field3;
   }

   public static class Class0 {
      private int field0;
      private String field1;
      private String field2;
      private AutoDailyModule.Class1 field3;

      static AutoDailyModule.Class1 field0(AutoDailyModule.Class0 var0) {
         return var0.field3;
      }

      static String field1(AutoDailyModule.Class0 var0) {
         return var0.field1;
      }

      static String field2(AutoDailyModule.Class0 var0) {
         return var0.field2;
      }

      private Class0() {
      }
   }
}
