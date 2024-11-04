package oringo.module;

import java.awt.Color;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import map.Class228;
import map.Class447;
import map.Class500;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.event.Class332;
import oringo.mixin.MinecraftAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class FlightModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Timer", 1.0D, 0.1D, 10.0D, 0.1D);
   public static final DoubleSetting field1 = new FlightModule$2("Auto disable", 1500.0D, 0.0D, 5000.0D, 50.0D);
   public final Class447 field2 = new Class447();
   public static final BooleanSetting field3 = new BooleanSetting("Vertical", true, FlightModule::lambda$static$1);
   public float field4;
   public static final EnumSetting field5 = new EnumSetting("Mode", "Vanilla", new String[]{"Vanilla"});
   public boolean field6;
   public boolean field7;
   public final ConcurrentHashMap field8 = new ConcurrentHashMap();
   public int H_;
   public final Class447 field10 = new Class447();
   public double field11;
   public static final DoubleSetting field12 = new DoubleSetting("Speed", 1.0D, 0.1D, 10.0D, 0.1D, FlightModule::lambda$static$0);
   public Class228 field13;
   public double field14;
   public float field15;
   public static final DoubleSetting field16 = new FlightModule$1("Disabler timer", 1200.0D, 250.0D, 2500.0D, 1.0D);

   public void b_() {
      ((MinecraftAccessor)field58).getTimer().timerSpeed = 1.0F;
      if (!field5.method0(-1)) {
         field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
      } else {
         field58.thePlayer.motionY = 0.0D;
      }

      this.field8.clear();
   }

   public void method4() {
      this.field6 = false;
      this.field2.o_();
      this.field13 = null;
      this.H_ = 0;
      this.field11 = 0.0D;
      this.field7 = false;
      if (field5.method0(-1) && field58.thePlayer.onGround) {
         field58.thePlayer.jump();
         field58.thePlayer.motionY = 0.41999998688697815D;
      }

   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field5.method0(-1)) {
         if (var1.field0 instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook var2 = (S08PacketPlayerPosLook)var1.field0;
            double var3 = var2.getX();
            double var5 = var2.getY();
            double var7 = var2.getZ();
            this.field4 = var2.getPitch();
            this.field15 = var2.getYaw();
            if (this.field13 == null) {
               this.field13 = new Class228(var3, var5, var7);
               field58.thePlayer.setPosition(var3, var5, var7);
               method2(AutoHealModule.method0(var2));
            } else {
               Class228 var9 = new Class228(var3, var5, var7);
               Integer var10 = (Integer)this.field8.get(var9);
               if (var10 != null && var10 >= 1) {
                  this.field8.replace(var9, var10 - 1);
               } else {
                  this.H_ = 0;
                  this.field13 = var9;
                  field58.thePlayer.setPosition(var9.field2, var9.field1, var9.field0);
                  method2(AutoHealModule.method0(var2));
                  if (var10 != null) {
                     this.field8.remove(var9);
                  }
               }
            }

            var1.method9();
         }

      }
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (field5.method0(-1)) {
         if (this.field13 == null) {
            if (!this.field7 && field58.thePlayer.motionY < 0.0D) {
               var1.method1(false);
               var1.method0(var1.i_ - 30.0D);
               this.field7 = true;
            }

         } else {
            if (this.H_ == 1) {
               this.field13 = new Class228(field58.thePlayer.posX, this.field14, field58.thePlayer.posZ);
               Integer var2 = (Integer)this.field8.get(this.field13);
               if (var2 != null) {
                  this.field8.replace(this.field13, var2 + 1);
               } else {
                  this.field8.put(this.field13, 1);
               }

               method2(new C04PacketPlayerPosition(this.field13.field2, this.field13.field1, this.field13.field0, true));
               method2(new C03PacketPlayer(true));
               method2(new C04PacketPlayerPosition(this.field13.field2, this.field13.field1 - 30.0D, this.field13.field0, false));
               method2(new C06PacketPlayerPosLook(this.field13.field2, this.field13.field1, this.field13.field0, this.field15, this.field4, false));
            } else {
               this.field11 += 1.0E-10D;
               method2(new C04PacketPlayerPosition(field58.thePlayer.posX, field58.thePlayer.posY - this.field11, field58.thePlayer.posZ, true));
               this.field14 = field58.thePlayer.posY - this.field11;
            }

            ++this.H_;
            this.H_ %= 2;
         }
      }
   }

   public static boolean method0(Gui var0) {
      if (var0 instanceof GuiChest && ((GuiChest)var0).inventorySlots instanceof ContainerChest) {
         ContainerChest var1 = (ContainerChest)((GuiChest)var0).inventorySlots;
         return var1.getLowerChestInventory().getName().contains("Click the button on time!");
      } else {
         return false;
      }
   }

   public static Boolean lambda$static$1() {
      return !field5.method0(0) || !field5.method0(-1);
   }

   public boolean method6() {
      return this.method44() && (!field5.method0(-1) || !this.field10.a_((long)field16.method0()));
   }

   public static void method0(Predicate var0) {
      int var1 = TrailModule.method0(var0);
      if (var1 != -1) {
         RenderBarriersModule.method0(var1);
      }

   }

   public static Color method7() {
      return ClickGuiModule.field20.method17();
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(Class332 var1) {
      if (field5.method0(-1) && this.field13 != null && (var1.field0 instanceof C0BPacketEntityAction || var1.field0 instanceof C03PacketPlayer)) {
         var1.method9();
      }

   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (this.method44()) {
         String var2 = field5.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -1248403467:
            if (var2.equals("Hypixel")) {
               var3 = 0;
            }
            break;
         case 67973692:
            if (var2.equals("Float")) {
               var3 = 1;
            }
            break;
         case 376026813:
            if (var2.equals("Hypixel Slime")) {
               var3 = 2;
            }
            break;
         case 1897755483:
            if (var2.equals("Vanilla")) {
               var3 = 3;
            }
         }

         switch(var3) {
         case 0:
            if (this.field13 != null) {
               EnigmaSoulESPModule.method0(field0.method1());
               boolean var7 = AutoReconnectModule.method1();
               double var5 = var7 ? 0.2873D * (double)field58.thePlayer.capabilities.getWalkSpeed() * 10.0D * (1.0D + (double)(field58.thePlayer.isPotionActive(Potion.moveSpeed) ? field58.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1 : 0) * 0.2D) : 0.0D;
               Dadudze2Module.method0(var1, var5);
               var1.method0(0.0D);
               field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
            }
            break;
         case 1:
            var1.method4();
            field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
            break;
         case 2:
            if (field58.thePlayer.capabilities.allowFlying) {
               if (field58.thePlayer.ticksExisted % 6 == 0 || !this.field6 || this.field10.a_((long)field16.method0() - 150L)) {
                  PlayerCapabilities var4 = new PlayerCapabilities();
                  var4.allowFlying = true;
                  var4.isFlying = false;
                  field58.getNetHandler().getNetworkManager().sendPacket(new C13PacketPlayerAbilities(var4));
                  var4.isFlying = true;
                  field58.getNetHandler().getNetworkManager().sendPacket(new C13PacketPlayerAbilities(var4));
                  this.field6 = true;
                  this.field10.o_();
               }
            } else if (this.field10.a_((long)field16.method0())) {
               if (this.field6) {
                  field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
                  this.field6 = false;
                  ((MinecraftAccessor)field58).getTimer().timerSpeed = 1.0F;
               }
               break;
            }
         case 3:
            EnigmaSoulESPModule.method0((float)field0.method0());
            var1.method0(0.0D);
            Dadudze2Module.method0(var1, field12.method0());
            if (field3.method1()) {
               if (field58.gameSettings.keyBindJump.isKeyDown()) {
                  var1.method0(var1.method6() + field12.method0());
               }

               if (field58.gameSettings.keyBindSneak.isKeyDown()) {
                  var1.method0(var1.method6() - field12.method0());
               }
            }

            if (field5.method0(0) && this.field2.a_((long)field1.method0()) && field1.method0() != 0.0D) {
               this.method1(false);
            }
         }
      }

   }

   public static Boolean lambda$static$0() {
      return field5.method0(-1) || field5.method0(-1);
   }

   public FlightModule() {
      super("Flight", Category.movement, SubCategory.movement, "Allows you to fly");
      this.method0((Setting[])(new Setting[]{field5, field12, field0, field16, field1}));
      EnumSetting var10001 = field5;
      this.method0((Class500)(var10001::method4));
   }
}
