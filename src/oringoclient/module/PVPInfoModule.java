package oringo.module;

import java.util.HashMap;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class PVPInfoModule extends Module {
   public final HashMap field0 = new HashMap();
   public boolean ck_ = false;
   public final BooleanSetting field2 = new BooleanSetting("Show WLR", true);
   public final BooleanSetting field3 = new BooleanSetting("Show Levels", true);
   public final BooleanSetting field4 = new BooleanSetting("Show streak", true);
   public final BooleanSetting field5 = new BooleanSetting("Show FKDR", true);
   public final HashMap field6 = new HashMap();
   public String field7 = null;
   public final StringSetting field8 = new StringSetting("Api Key");

   public PVPInfoModule() {
      super("PVP Info", Category.other, SubCategory.other, "Shows stats of enemies");
      this.method0((Setting[])(new Setting[]{this.field3, this.field2, this.field5, this.field4, this.field8}));
   }

   public String p_(String var1) {
      StringBuilder var2 = new StringBuilder();
      char[] var3 = var1.toCharArray();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char var6 = var3[var5];
         if (var6 == '&') {
            var2.append("§");
         } else {
            var2.append(var6);
         }
      }

      return var2.toString();
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.ck_ = false;
      this.field0.clear();
      this.field7 = null;
      this.field6.clear();
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S3EPacketTeams && this.method44() && "§7§k".equals(((S3EPacketTeams)var1.field0).getName()) && ((S3EPacketTeams)var1.field0).getPlayers().size() == 1) {
         String var2 = (String)((S3EPacketTeams)var1.field0).getPlayers().iterator().next();
         if (this.field7 == null) {
            this.field7 = var2;
         } else {
            this.field0.put(this.field7, var2);
            this.field7 = null;
         }
      }

   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getUnformattedText().trim().equals("The Angel of Death has corrupted this game!")) {
         this.ck_ = true;
      }

   }

   public static class Class2 {
      public double field0;
      public double field1;
      public int field2;
      public int field3;
      public double field4;
      public double field5;

      public String A_() {
         return String.format("%.2f", this.field1 / this.field5);
      }

      public String method1() {
         return String.format("%.2f", this.field0 / this.field4);
      }

      public String method2() {
         int var1 = this.field3 / 5000;
         if (var1 >= 1000) {
            return String.format("§a[%s✫]", var1);
         } else if (var1 >= 900) {
            return String.format("§5[%s✫]", var1);
         } else if (var1 >= 800) {
            return String.format("§9[%s✫]", var1);
         } else if (var1 >= 700) {
            return String.format("§d[%s✫]", var1);
         } else if (var1 >= 600) {
            return String.format("§4[%s✫]", var1);
         } else if (var1 >= 500) {
            return String.format("§3[%s✫]", var1);
         } else if (var1 >= 400) {
            return String.format("§1[%s✫]", var1);
         } else if (var1 >= 300) {
            return String.format("§b[%s✫]", var1);
         } else if (var1 >= 200) {
            return String.format("§6[%s✫]", var1);
         } else {
            return var1 >= 100 ? String.format("§f[%s✫]", var1) : String.format("§7[%s✫]", var1);
         }
      }
   }

   public static class Class0 {
      public int cl_;
      public int field0;
      public int field2;

      public int method0() {
         return this.field2;
      }

      public String A_() {
         return String.format(((double)this.cl_ / (double)this.field0 > 3.0D ? "§4" : ((double)this.cl_ / (double)this.field0 > 2.0D ? "§c" : "§f")) + "%.2f", (double)this.cl_ / (double)this.field0);
      }
   }

   public static class Class3 {
      public int field0;
      public String field1;
      public int cl_;
      public int field3;

      public String method0() {
         if (this.field3 > 24999) {
            return "§5Heavenly";
         } else if (this.field3 > 9999) {
            return "§6Divine";
         } else if (this.field3 > 4999) {
            return "§dSucculent";
         } else if (this.field3 > 1999) {
            return "§3Tasty";
         } else if (this.field3 > 999) {
            return "§aSalty";
         } else if (this.field3 > 499) {
            return "§eDecent";
         } else if (this.field3 > 199) {
            return "§fMeh";
         } else {
            return this.field3 > 49 ? "§7Yucky" : "§8Eww";
         }
      }

      public String A_() {
         double var1 = (double)this.cl_ / (double)this.field0;
         return String.format((var1 > 0.4D ? "§4" : (var1 > 0.3D ? "§c" : "§f")) + "%.2f", var1);
      }
   }

   public static class Class1 {
      public final String field0;
      public final String field1;
      public final PVPInfoModule.Class0 field2;
      public final PVPInfoModule.Class2 field3;
      public final PVPInfoModule.Class3 field4;

      public Class1(String var1, String var2, PVPInfoModule.Class3 var3, PVPInfoModule.Class0 var4, PVPInfoModule.Class2 var5) {
         this.field1 = var1;
         this.field0 = var2;
         this.field4 = var3;
         this.field2 = var4;
         this.field3 = var5;
      }
   }
}
