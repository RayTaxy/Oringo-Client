package oringo.module;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessage;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed.EmbedTitle;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class101;
import map.Class220;
import map.Class229;
import map.Class258;
import map.Class285;
import map.Class313;
import map.Class322;
import map.Class34;
import map.Class430;
import map.Class447;
import map.Class501;
import map.Class514;
import map.Class80;
import map.Class83;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLUtil;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class468;
import oringo.event.Class75;
import oringo.notification.Notifications;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class AutoFarmModule extends Module {
   public Class83 field0;
   public long dc_;
   public Class34 field2;
   public final BooleanSetting field3;
   public long field4;
   public final ButtonSetting field5;
   public final DoubleSetting field6;
   public Class258 field7;
   public long field8;
   public final Pattern field9;
   public static final Logger field10 = Oringo.method0("Oringo-AutoFarm");
   public final BooleanSetting field11;
   public Class220 field12;
   public final BooleanSetting field13;
   public final Class447 field14;
   public long field15;
   public static final float field16 = Class430.method0(0.4F, 0.6F);
   public final Class447 field17;
   public double field18;
   public String field19;
   public final BooleanSetting field20;
   public final BooleanSetting field21;
   public final DoubleSetting field22;
   public BlockPos field23;
   public final BooleanSetting field24;
   public final EnumSetting field25;
   public final BooleanSetting field26;
   public UUID field27;
   public final Class447 field28;
   public final BooleanSetting field29;
   public final BooleanSetting field30;
   public final BooleanSetting field31;
   public long field32;
   public long field33;
   public final BooleanSetting field34;
   public final Pattern field35;
   public MovementInput field36;
   public final DoubleSetting field37;
   public final StringSetting field38;
   public static final AutoFarmModule field39 = new AutoFarmModule();
   public long field40;
   public boolean field41;
   public final ArrayList field42;
   public final Pattern field43;
   public final EnumSetting field44;
   public long field45;
   public final DoubleSetting field46;
   public final EnumSetting field47;
   public long field48;
   public Color dh_;
   public final BooleanSetting field50;
   public final Class447 field51;
   public boolean field52;
   public long field53;

   public boolean method5() {
      return this.field52;
   }

   public void method1(JsonObject var1) {
      if (this.field23 != null) {
         JsonObject var2 = new JsonObject();
         var2.addProperty("x", this.field23.getX());
         var2.addProperty("y", this.field23.getY());
         var2.addProperty("z", this.field23.getZ());
         var1.add("warp_pos", var2);
      }
   }

   public void method0(Class83 var1) {
      this.field0 = var1;
      this.field0.o_();
   }

   public void lambda$new$6() {
      this.field23 = new BlockPos(field58.thePlayer);
      Class514.method0("Warp position set successfully!", 7500, Notifications.Class1.field1);
   }

   public BooleanSetting method7() {
      return this.field11;
   }

   public boolean method8() {
      return this.field50.method1() || this.method33() || this.field34.method1() && DoorESPModule.method2();
   }

   public BooleanSetting method9() {
      return this.field20;
   }

   public Boolean lambda$new$0() {
      return !this.field3.method1();
   }

   public AutoFarmModule() {
      super("Auto Farm", Category.skyblock, SubCategory.skills, "Farming macro");
      this.field25 = new EnumSetting("Crop", Class258.field2.method0(), new String[]{Class258.field2.method0(), Class258.field0.method0(), Class258.field7.method0(), Class258.field3.method0(), Class258.field4.method0(), Class258.field5.method0()});
      this.field26 = (BooleanSetting)(new BooleanSetting("Do auto visitors", false)).method2("Does visitors. Requires default barn skin");
      this.field3 = new BooleanSetting("Sell fermento", false);
      this.field37 = new DoubleSetting("Condensed count", 3.0D, 1.0D, 10.0D, 1.0D, this::lambda$new$0);
      this.field47 = new EnumSetting("Failsafe mode", "Act", new String[]{"Act", "Disable macro"});
      this.field29 = new BooleanSetting("Failsafe notification", true);
      this.field31 = new BooleanSetting("Maximize window", true, AutoFarmModule::lambda$new$1);
      this.field13 = new BooleanSetting("Bedrock box failsafe", true);
      this.field20 = new BooleanSetting("Break throttle failsafe", true);
      this.field11 = new BooleanSetting("Dirt wall failsafe", true);
      this.field24 = new BooleanSetting("Outside farm failsafe", true);
      this.field50 = (BooleanSetting)(new BooleanSetting("Limit speed", true)).method2("Sets your speed to the recommended one");
      this.field30 = (BooleanSetting)(new BooleanSetting("Fast break", false)).method2("Allows you to break faster");
      this.field21 = new BooleanSetting("Custom perspective", true);
      this.field6 = new DoubleSetting("Blocks per tick", 2.0D, 1.0D, 5.0D, 1.0D, this::lambda$new$2);
      this.field34 = (BooleanSetting)(new BooleanSetting("No FB during Jacob", false, this::lambda$new$3)).method2("Disables fast break during jacob contest");
      this.field44 = new EnumSetting("Overlay", "Design 1", new String[]{"Off", "Design 1", "Design 2"});
      this.field46 = new DoubleSetting("X123   ", 0.6D, -100000.0D, 100000.0D, 1.0E-5D, AutoFarmModule::lambda$new$4);
      this.field22 = new DoubleSetting("Y123   ", 0.6D, -100000.0D, 100000.0D, 1.0E-5D, AutoFarmModule::lambda$new$5);
      this.field5 = new ButtonSetting("Set end position", this::lambda$new$6);
      this.field38 = new StringSetting("Webhook");
      this.field36 = new MovementInput();
      this.field9 = Pattern.compile("^\\[NPC] Jacob: You earned a (GOLD|SILVER|BRONZE) medal in the (.+?) contest!");
      this.field35 = Pattern.compile("^\\[NPC] Jacob: You scored (.+?) item");
      this.field19 = null;
      this.dh_ = null;
      this.field51 = new Class447(0L);
      this.field27 = null;
      this.field48 = 0L;
      this.field32 = 0L;
      this.dc_ = 0L;
      this.field45 = 0L;
      this.field17 = new Class447();
      this.field33 = 0L;
      this.field4 = 0L;
      this.field43 = Pattern.compile("Farming \\((.+?)%\\)");
      this.field18 = 1.0D;
      this.field28 = new Class447();
      this.field42 = new ArrayList(60);
      this.field14 = new Class447();
      this.field15 = 0L;
      this.method0((Setting[])(new Setting[]{this.field25, this.field50, this.field30, this.field6, this.field34, this.field26, this.field3, this.field37, this.field29, this.field31, this.field47, this.field13, this.field20, this.field11, this.field24, this.field5, this.field38, this.field44, this.field46, this.field22, this.field21}));
   }

   public void b_() {
      Iterator var1 = Class285.method0().iterator();

      while(var1.hasNext()) {
         Class83 var2 = (Class83)var1.next();
         MinecraftForge.EVENT_BUS.unregister(var2);
         var2.o_();
      }

      if (this.field21.method1()) {
         field58.gameSettings.thirdPersonView = 0;
      }

   }

   public BooleanSetting method11() {
      return this.field13;
   }

   public Boolean lambda$new$2() {
      return !this.field30.method1();
   }

   public void a_(JsonObject var1) {
      if (var1.has("warp_pos")) {
         JsonObject var2 = var1.getAsJsonObject("warp_pos");
         this.field23 = new BlockPos(var2.get("x").getAsDouble(), var2.get("y").getAsDouble(), var2.get("z").getAsDouble());
      }
   }

   public Class258 method13() {
      if (this.field7 == null) {
         this.field7 = Class258.method0(this.field25.method4());
      }

      return this.field7;
   }

   public BooleanSetting method14() {
      return this.field24;
   }

   public static Thread lambda$sendWebhook$7(Runnable var0) {
      return new Thread(var0, "Oringo-Webhook");
   }

   public int method15() {
      return this.field6.method3();
   }

   public void method4() {
      if (field58.thePlayer == null) {
         this.method1(false);
      } else if (this.field23 == null) {
         method2("Set a warp position");
         this.method1(false);
      } else {
         this.field7 = Class258.method0(this.field25.method4());
         if (this.field7 == null) {
            method2("Please select a valid crop!");
            this.method1(false);
         } else {
            Iterator var1 = Class285.method0().iterator();

            while(var1.hasNext()) {
               Class83 var2 = (Class83)var1.next();
               MinecraftForge.EVENT_BUS.register(var2);
               var2.o_();
            }

            this.field36 = new MovementInput();
            this.field0 = null;
            this.field12 = this.field7.method2();
            this.field12.o_();
            this.field52 = false;
            this.field17.o_();
            this.field28.o_();
            if (this.field21.method1()) {
               field58.gameSettings.thirdPersonView = 1;
            }

         }
      }
   }

   public void method0(Class34 var1) {
      this.field2 = var1;
   }

   public void method16() {
      if (this.field26.method1() && Class80.method1().method3()) {
         this.method0((Class83)Class80.method1());
      } else {
         this.field52 = false;
         this.field12.method16();
         if (this.field12.method30() != null) {
            Class229.method1().method32();
         }

      }
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (!field58.theWorld.isBlockLoaded(new BlockPos(field58.thePlayer), false)) {
         var1.method4();
      } else {
         if (this.field0 != null) {
            this.field0.method0(var1);
         } else {
            if (field58.thePlayer.capabilities.isFlying) {
               field58.thePlayer.capabilities.isFlying = false;
            }

            var1.method0(this.field12.method0(var1.method0()));
         }

         this.field36 = var1.method0();
      }
   }

   public static Boolean lambda$new$5() {
      return true;
   }

   public Boolean lambda$new$3() {
      return !this.field30.method1();
   }

   public boolean method19() {
      return this.field30.method1() && !this.method33() && (!this.field34.method1() || !DoorESPModule.method2()) && !this.method31();
   }

   public BooleanSetting method20() {
      return this.field3;
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      this.field8 += this.field28.method0();
      this.field28.o_();
      if (var1.phase == Phase.START) {
         if (this.field14.method0(60000L, true)) {
            long var2 = -1L;
            Iterator var4 = Class322.method0().iterator();

            while(var4.hasNext()) {
               String var5 = (String)var4.next();
               if (var5.startsWith("purse: ")) {
                  var2 = (long)Float.parseFloat(var5.split(" ")[1].replaceAll(",", ""));
               }
            }

            if (var2 != -1L) {
               this.field15 = var2;
            }
         }

         if (this.field17.method0(1000L, true) && this.field33 != 0L) {
            AutoFarmModule.Class0 var6 = new AutoFarmModule.Class0(this.field33 * (long)this.field7.method4(), this.field48, this.field33);
            this.field42.add(var6);
            this.field53 += var6.field1;
            this.field32 += var6.field2;
            this.field4 += var6.field0;
            this.field48 = 0L;
            this.field33 = 0L;
            if (this.field42.size() == 61) {
               AutoFarmModule.Class0 var3 = (AutoFarmModule.Class0)this.field42.remove(0);
               this.field4 -= var3.field0;
               this.field32 -= var3.field2;
               this.field53 -= var3.field1;
            }
         }

         if (field58.thePlayer != null) {
            if (this.method8()) {
               field58.thePlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)this.field7.method1() / 1000.0D);
            } else {
               field58.thePlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)field58.thePlayer.capabilities.getWalkSpeed());
            }
         }

         if (field58.thePlayer == null || field58.theWorld.isBlockLoaded(new BlockPos(field58.thePlayer), false)) {
            if (this.field0 != null) {
               this.field0.method0(var1);
            } else {
               Iterator var7 = Class501.method5().iterator();

               while(var7.hasNext()) {
                  Class83 var8 = (Class83)var7.next();
                  if (var8.method3()) {
                     this.field0 = var8;
                     var8.o_();
                     this.field0.method2();
                     var8.method0(var1);
                  }
               }
            }

            this.field52 = false;
         }
      }
   }

   public boolean method21() {
      return this.field41;
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S02PacketChat) {
         if (((S02PacketChat)var1.field0).getType() == 2) {
            String var9 = ((S02PacketChat)var1.field0).getChatComponent().getUnformattedText();
            Matcher var10 = this.field43.matcher(var9.replaceAll(",", ""));
            if (var10.find()) {
               this.field18 = Math.min(1.0D, Double.parseDouble(var10.group(1)) / 100.0D);
            }
         }
      } else if (var1.field0 instanceof S2FPacketSetSlot) {
         S2FPacketSetSlot var2 = (S2FPacketSetSlot)var1.field0;
         int var3 = var2.func_149173_d();
         int var4 = var2.func_149175_c();
         ItemStack var5 = field58.thePlayer.getHeldItem();
         if (var4 == 0 && var3 == field58.thePlayer.inventory.currentItem + 36) {
            if (!Objects.equals(this.field27, this.field27 = HitboxesModule.method0(var2.func_149174_e()))) {
               return;
            }

            int var6 = CriticalsModule.method0(var5);
            int var7 = CriticalsModule.method0(var2.func_149174_e());
            if (var6 != -1 && var7 != -1) {
               int var8 = var7 - var6;
               if (var8 == 0) {
                  return;
               }

               this.field33 += (long)var8;
               this.field45 += (long)var8;
               this.field40 += (long)var8 * (long)this.field7.method4();
               return;
            }

            return;
         }

         return;
      } else if (var1.field0 instanceof S08PacketPlayerPosLook) {
         this.field52 = true;
      }

   }

   public boolean method22() {
      return this.field47.method0(1);
   }

   public void method23() {
      this.field0 = null;
   }

   public BlockPos method24() {
      return this.field23;
   }

   public MovementInput method25() {
      return this.field36;
   }

   public static Boolean lambda$new$4() {
      return true;
   }

   public static void method27() {
      if (XRayModule.field58.renderGlobal != null) {
         XRayModule.field58.renderGlobal.loadRenderers();
         XRayModule.field6 = XRayModule.field4.method0();
      }

   }

   public boolean method28() {
      return this.field31.method1();
   }

   public int method29() {
      return this.field37.method3();
   }

   public void method0(WebhookMessage var1) {
      if (!this.field38.F_()) {
         try {
            WebhookClient var2 = (new WebhookClientBuilder(this.field38.method1())).setWait(false).setThreadFactory(AutoFarmModule::lambda$sendWebhook$7).build();
            Throwable var3 = null;

            try {
               var2.send(var1);
            } catch (Throwable var13) {
               var3 = var13;
               throw var13;
            } finally {
               if (var2 != null) {
                  if (var3 != null) {
                     try {
                        var2.close();
                     } catch (Throwable var12) {
                        var3.addSuppressed(var12);
                     }
                  } else {
                     var2.close();
                  }
               }

            }
         } catch (IllegalStateException var15) {
            PopupAnimationModule.method2("Invalid Auto Farm webhook!");
         }

      }
   }

   public EnumFacing method30() {
      return this.field12.method30();
   }

   public boolean method31() {
      return this.field0 != null;
   }

   public void method32() {
      if (Class313.method1().method3()) {
         this.field0 = Class313.method1();
         this.field0.o_();
         this.field0.method2();
         this.field0.method0(2137);
      }
   }

   public boolean method33() {
      return TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - Class101.field6) < 7L;
   }

   public static Boolean lambda$new$1() {
      return LWJGLUtil.getPlatform() != 3;
   }

   public boolean method35() {
      return this.field29.method1();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.thePlayer == null || this.field0 != null && !this.field0.method4()) {
         this.field41 = false;
      } else {
         this.field12.method3();
         this.field41 = true;
      }

   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      String var2 = var1.message.getUnformattedText();
      if (var2.endsWith("has arrived on your Garden!") && var2.indexOf(58) == -1) {
         if (this.field51.method0() < TimeUnit.MINUTES.toMillis(15L)) {
            method2("Visitor spawned in: §r" + ReachModule.method0(this.field51.method0(), TimeUnit.MILLISECONDS).replaceFirst("0h ", ""));
         }

         this.field51.o_();
      } else {
         Matcher var3 = this.field9.matcher(var2);
         if (var3.find()) {
            this.field19 = var3.group(2);
            String var4 = var3.group(1);
            byte var5 = -1;
            switch(var4.hashCode()) {
            case -1848981747:
               if (var4.equals("SILVER")) {
                  var5 = 1;
               }
               break;
            case 2193504:
               if (var4.equals("GOLD")) {
                  var5 = 0;
               }
               break;
            case 1967683994:
               if (var4.equals("BRONZE")) {
                  var5 = 2;
               }
            }

            switch(var5) {
            case 0:
               this.dh_ = Color.ORANGE;
               break;
            case 1:
               this.dh_ = Color.WHITE;
               break;
            case 2:
               this.dh_ = Color.ORANGE.darker().darker();
            }

         } else if (this.dh_ != null) {
            var3 = this.field35.matcher(var2);
            if (var3.find()) {
               this.method0((new WebhookMessageBuilder()).addEmbeds(new WebhookEmbed[]{(new WebhookEmbedBuilder()).setColor(this.dh_.getRGB()).setTitle(new EmbedTitle(this.field19 + " contest ended!", (String)null)).setDescription("Score: " + var3.group(1)).build()}).build());
               this.field19 = null;
               this.dh_ = null;
            }

         }
      }
   }

   public static void method0(Object var0) {
      Oringo.field9.getNetHandler().getNetworkManager().channel().write(var0);
   }

   public static class Class0 {
      public long field0;
      public long field1;
      public long field2;

      public Class0(long var1, long var3, long var5) {
         this.field1 = var1;
         this.field2 = var3;
         this.field0 = var5;
      }
   }
}
