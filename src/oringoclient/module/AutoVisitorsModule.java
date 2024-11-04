package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import map.Class142;
import map.Class229;
import map.Class361;
import map.Class376;
import map.Class447;
import map.Class94;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoVisitorsModule extends Module {
   public final BooleanSetting c_;
   public final DoubleSetting d_ = new DoubleSetting("Copper price", 5000.0D, 0.0D, 100000.0D, 1.0D, this::lambda$new$1);
   public final Pattern field2;
   public DecimalFormat e_;
   public final BooleanSetting field4 = new BooleanSetting("Sacks only", false);
   public final DoubleSetting field5;
   public static boolean field6 = false;
   public final DoubleSetting field7 = new DoubleSetting("Garden exp price", 0.0D, 0.0D, 100000.0D, 1.0D, this::lambda$new$0);
   public boolean field8;
   public int field9;
   public final DoubleSetting field10;
   public AutoVisitorsModule.Class0 field11;
   public final BooleanSetting field12 = new BooleanSetting("Only accept profitable", false);
   public int field13;
   public final BooleanSetting field14 = new BooleanSetting("Decline non-bazaar items", true);
   public final Class447 field0;
   public AutoVisitorsModule.Class0 field16;
   public final ArrayList field17;
   public final BooleanSetting field18;

   public static boolean lambda$clickVisitor$4(String var0, EntityArmorStand var1) {
      return var1.getDisplayName().getFormattedText().replaceAll("§r", "").trim().equals(var0);
   }

   public Boolean lambda$new$2() {
      return !this.field18.method1() || this.field12.method1();
   }

   public void method1() {
      List var1 = field58.theWorld.getEntities(EntityLivingBase.class, AutoVisitorsModule::lambda$clickVisitor$3);
      if (!var1.isEmpty()) {
         Iterator var2 = field58.getNetHandler().getPlayerInfoMap().iterator();

         while(var2.hasNext()) {
            NetworkPlayerInfo var3 = (NetworkPlayerInfo)var2.next();
            if (var3 != null && var3.getDisplayName() != null && var3.getGameProfile().getName().equals("!C-o")) {
               String var4 = var3.getDisplayName().getFormattedText().replaceAll("§r", "").trim();
               if (this.c_.method1() && ChatFormatting.stripFormatting(var4).contains("Spaceman")) {
                  this.method1(false);
                  return;
               }

               List var5 = field58.theWorld.getEntities(EntityArmorStand.class, AutoVisitorsModule::lambda$clickVisitor$4);
               var5.sort(Comparator.comparingDouble(AutoVisitorsModule::lambda$clickVisitor$5));
               if (!var5.isEmpty()) {
                  Entity var6 = (Entity)var5.get(0);
                  var6.getClass();
                  var1.sort(Comparator.comparingDouble(var6::func_70068_e));
                  EntityLivingBase var7 = (EntityLivingBase)var1.get(0);
                  if (var7.getDistanceSqToEntity(field58.thePlayer) > 32.49D) {
                     return;
                  }

                  Class361.method0((Class94)(new Class142(var7)));
               }
               break;
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getUnformattedText().equals("You don't have enough of the required item!") && !this.field0.method0(300L, true)) {
         this.method1(false);
         method2("Disable personal compactor and make sure you have enough free inventory space!");
      }

      if (var1.message.getUnformattedText().equals("You need the Cookie Buff to use this feature!") && !this.field0.method0(300L, true)) {
         this.method1(false);
         method2("Buy a cookie noob!");
      }

   }

   public static double lambda$clickVisitor$5(EntityArmorStand var0) {
      return var0.getDistanceSqToEntity(field58.thePlayer);
   }

   public static boolean lambda$clickVisitor$3(EntityLivingBase var0) {
      return !(var0 instanceof EntityArmorStand) && field58.thePlayer != var0;
   }

   public Boolean lambda$new$0() {
      return !this.field12.method1();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.thePlayer != null && Class376.method0("The Garden")) {
         if (!Class229.method1().method44()) {
            this.method0(false);
         }
      }
   }

   public String method0(double var1) {
      if (this.e_ == null) {
         DecimalFormat var3 = new DecimalFormat("###,###");
         DecimalFormatSymbols var4 = var3.getDecimalFormatSymbols();
         var4.setGroupingSeparator(',');
         var3.setDecimalFormatSymbols(var4);
         this.e_ = var3;
      }

      return this.e_.format(var1);
   }

   public static boolean method0(float var0) {
      return GardenHelperModule.method0(var0, 0.0D, 0.0D);
   }

   public void method0(boolean var1) {
      // $FF: Couldn't be decompiled
   }

   public AutoVisitorsModule() {
      super("Auto Visitors", Category.skyblock, SubCategory.skills, "Automatically buys items for visitors in the garden");
      BooleanSetting var10005 = this.field12;
      var10005.getClass();
      this.field18 = new BooleanSetting("Limit price", true, var10005::method1);
      this.field5 = new DoubleSetting("Max price", 500000.0D, 50000.0D, 2500000.0D, 1000.0D, this::lambda$new$2);
      this.field10 = new DoubleSetting("Extra delay", 0.0D, 0.0D, 1000.0D, 50.0D);
      this.c_ = (BooleanSetting)(new BooleanSetting("Ignore spaceman", false)).method2("Disables on spaceman");
      this.field2 = Pattern.compile("Price: (.+?) coins");
      this.field0 = new Class447();
      this.field13 = 0;
      this.field17 = new ArrayList();
      this.field8 = false;
      this.field16 = AutoVisitorsModule.Class0.field4;
      this.field11 = this.field16;
      this.field9 = 0;
      this.e_ = null;
      this.method0((Setting[])(new Setting[]{this.field14, this.field4, this.field12, this.field7, this.d_, this.field18, this.field5, this.field10, this.c_}));
   }

   public Boolean lambda$new$1() {
      return !this.field12.method1();
   }

   public void method4() {
      field6 = false;
   }

   static enum Class0 {
      field0,
      field1;

      private static final AutoVisitorsModule.Class0[] field6 = new AutoVisitorsModule.Class0[]{field4, field3, field1, field0, field5, field2};
      field2,
      field3,
      field4,
      field5;
   }

   private static class Class1 {
      private final String cW_;
      private final int field1;

      static String access$100(AutoVisitorsModule.Class1 var0) {
         return var0.cW_;
      }

      static int access$000(AutoVisitorsModule.Class1 var0) {
         return var0.field1;
      }

      public Class1(String var1, int var2) {
         this.cW_ = var1;
         this.field1 = var2;
      }
   }
}
