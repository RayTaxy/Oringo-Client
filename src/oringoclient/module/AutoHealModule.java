package oringo.module;

import com.google.common.collect.Lists;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import map.Class361;
import map.Class402;
import map.Class426;
import map.Class447;
import map.Class506;
import map.Class94;
import map.Class95;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S08PacketPlayerPosLook.EnumFlags;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoHealModule extends Module {
   public final Class447 field0 = new Class447();
   public final List m_ = Lists.newArrayList(new AutoHealModule.Class2[]{new AutoHealModule.Class1("Wand", "WAND_OF_ATONEMENT", 7200L, (AutoHealModule$1)null), new AutoHealModule.Class1("Pigman Sword", "PIGMAN_SWORD", 300L, (AutoHealModule$1)null), new AutoHealModule.Class1("Zombie Sword", "FLORID_ZOMBIE_SWORD", 300L, (AutoHealModule$1)null), new AutoHealModule.Class0("Sword of Bad Health", "SWORD_OF_BAD_HEALTH", 5200L)});
   public final BooleanSetting field2 = new BooleanSetting("Wither Impact", true);
   public final Class447 field3 = new Class447();
   public final DoubleSetting field4 = (new DoubleSetting("Impact health", 60.0D, 0.0D, 100.0D, 1.0D, this::lambda$new$0)).method0("%");
   public final BooleanSetting field5 = new BooleanSetting("From Inv", false);

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C08PacketPlayerBlockPlacement && ((C08PacketPlayerBlockPlacement)var1.field0).getStack() != null && Class506.method0(((C08PacketPlayerBlockPlacement)var1.field0).getStack()) && this.field0.a_(5200L)) {
         this.field0.o_();
      }

   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      this.field3.o_();
   }

   public static Minecraft access$100() {
      return field58;
   }

   public static HttpURLConnection method0(URL var0) {
      try {
         HttpURLConnection var1 = (HttpURLConnection)var0.openConnection();
         var1.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
         var1.setRequestMethod("GET");
         var1.setDoInput(true);
         return var1;
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public static void lambda$onPre$2(ItemStack var0) {
      if (var0 != null) {
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.type == ElementType.TEXT && field58.thePlayer != null) {
         if (this.field2.method1() && field58.thePlayer.getHealth() / field58.thePlayer.getMaxHealth() <= this.field4.method1() / 100.0F) {
            int var2 = Class95.method0(Class506::method0);
            if ((var2 >= 36 || this.field5.method1()) && var2 != -1) {
               ItemStack var3 = field58.thePlayer.inventoryContainer.getSlot(var2).getStack();
               ThornAimbotModule.method0(var3, (float)this.field0.method2() / 5200.0F);
            }
         }

         Iterator var5 = this.m_.iterator();

         while(var5.hasNext()) {
            AutoHealModule.Class2 var6 = (AutoHealModule.Class2)var5.next();
            if (var6.method1()) {
               int var4 = var6.method0(this.field5.method1());
               if (var4 != -1) {
                  ThornAimbotModule.method0(field58.thePlayer.inventoryContainer.getSlot(var4).getStack(), (float)var6.field1.method0() / (float)var6.field0);
               }
            }
         }

      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public void method0(Class210.Class1 var1) {
      if (this.field3.a_(3000L)) {
         if (this.field2.method1() && this.field0.a_(5200L) && field58.thePlayer.onGround && field58.thePlayer.getHealth() / field58.thePlayer.getMaxHealth() <= this.field4.method1() / 100.0F) {
            int var2 = Class95.method0(Class506::method0);
            if (!this.field5.method1() && var2 < 36) {
               var2 = -1;
            }

            if (var2 != -1 && Class361.method0((Class94)(new Class402(var2, AutoHealModule::lambda$onPre$1)))) {
               var1.method0(90.0F);
               this.field0.o_();
               return;
            }
         }

         Iterator var5 = this.m_.iterator();

         while(var5.hasNext()) {
            AutoHealModule.Class2 var3 = (AutoHealModule.Class2)var5.next();
            if (var3.method1()) {
               int var4 = var3.method0(this.field5.method1());
               if (var4 != -1 && Class361.method0((Class94)(new Class402(var4, AutoHealModule::lambda$onPre$2)))) {
                  var3.field1.o_();
                  break;
               }
            }
         }

      }
   }

   public static void lambda$onPre$1(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   public AutoHealModule() {
      super("Auto Heal", Category.player, SubCategory.slayer, "Automatically uses healing and utility items");
      this.method0((Setting[])(new Setting[]{this.field2, this.field4}));
      Iterator var1 = this.m_.iterator();

      while(var1.hasNext()) {
         AutoHealModule.Class2 var2 = (AutoHealModule.Class2)var1.next();
         this.method0((Setting[])((Setting[])var2.method47().toArray(new Setting[0])));
      }

      this.method0((Setting[])(new Setting[]{this.field5}));
   }

   public Boolean lambda$new$0() {
      return !this.field2.method1();
   }

   public static C06PacketPlayerPosLook method0(S08PacketPlayerPosLook var0) {
      double var1 = var0.getX();
      double var3 = var0.getY();
      double var5 = var0.getZ();
      float var7 = var0.getYaw();
      float var8 = var0.getPitch();
      if (var0.func_179834_f().contains(EnumFlags.X)) {
         var1 += Oringo.field9.thePlayer.posX;
      }

      if (var0.func_179834_f().contains(EnumFlags.Y)) {
         var3 += Oringo.field9.thePlayer.posY;
      }

      if (var0.func_179834_f().contains(EnumFlags.Z)) {
         var5 += Oringo.field9.thePlayer.posZ;
      }

      if (var0.func_179834_f().contains(EnumFlags.X_ROT)) {
         var8 += Oringo.field9.thePlayer.rotationPitch;
      }

      if (var0.func_179834_f().contains(EnumFlags.Y_ROT)) {
         var7 += Oringo.field9.thePlayer.rotationYaw;
      }

      return new C06PacketPlayerPosLook(var1, var3, var5, var7 % 360.0F, var8 % 360.0F, false);
   }

   public static Minecraft access$200() {
      return field58;
   }

   public static class Class1 extends AutoHealModule.Class2 {
      private final DoubleSetting field3;
      private final String field4;

      public boolean method1() {
         return this.field2.method1() && this.field1.a_(this.field0) && AutoHealModule.access$100().thePlayer.getHealth() / AutoHealModule.access$200().thePlayer.getMaxHealth() <= this.field3.method1() / 100.0F;
      }

      private Class1(String var1, String var2, long var3) {
         super(var1, var3);
         this.field3 = (new DoubleSetting(var1 + " health", 60.0D, 0.0D, 100.0D, 1.0D, this::lambda$new$0)).method0("%");
         this.field4 = var2;
      }

      Class1(String var1, String var2, long var3, AutoHealModule$1 var5) {
         this(var1, var2, var3);
      }

      private boolean lambda$getSlot$1(ItemStack var1) {
         return this.field4.equals(BlockHitModule.method0(var1));
      }

      private Boolean lambda$new$0() {
         return !this.field2.method1();
      }

      public List method47() {
         return Lists.newArrayList(new Setting[]{this.field2, this.field3});
      }

      public int method0(boolean var1) {
         int var2 = Class95.method0(this::lambda$getSlot$1);
         if (!var1 && var2 < 36) {
            var2 = -1;
         }

         return var2;
      }
   }

   public static class Class0 extends AutoHealModule.Class2 {
      private final String field3;

      public Class0(String var1, String var2, long var3) {
         super(var1, var3);
         this.field3 = var2;
      }

      private boolean lambda$getSlot$0(ItemStack var1) {
         return this.field3.equals(BlockHitModule.method0(var1));
      }

      public int method0(boolean var1) {
         int var2 = Class95.method0(this::lambda$getSlot$0);
         if (!var1 && var2 < 36) {
            var2 = -1;
         }

         return var2;
      }

      public List method47() {
         return Lists.newArrayList(new Setting[]{this.field2});
      }

      public boolean method1() {
         return this.field2.method1() && this.field1.a_(this.field0);
      }
   }

   public abstract static class Class2 {
      protected final long field0;
      protected final Class447 field1 = new Class447();
      protected final BooleanSetting field2;

      public abstract boolean method1();

      public Class2(String var1, long var2) {
         this.field2 = new BooleanSetting(var1, false);
         this.field0 = var2;
      }

      public abstract int method0(boolean var1);

      public abstract List method47();
   }
}
