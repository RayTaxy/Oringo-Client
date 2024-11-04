package oringo.module;

import map.Class348;
import map.Class361;
import map.Class402;
import map.Class426;
import map.Class447;
import map.Class514;
import map.Class7;
import map.Class94;
import map.Class95;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class194;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.notification.Notifications;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class HClipModule extends Module {
   public boolean field0;
   public double ct_;
   public double field2;
   public final Class447 field3 = new Class447();
   public static final String[] field4 = new String[]{"LEAPING_SWORD", "SILK_EDGE_SWORD"};
   public boolean db_;
   public double field6;
   public static final DoubleSetting field7 = new DoubleSetting("Distance", 7.5D, 1.0D, 10.0D, 0.1D);
   public final EnumSetting field8 = new EnumSetting("Mode", "Leaping", new String[]{"Leaping", "Walk"});

   public static void method0(Class348 var0) {
      if (var0 != Class7.field11) {
         Class7.field11.method1();
         Class7.field11 = var0;
         Class7.field11.q_();
      }
   }

   @SubscribeEvent
   public void a_(Class217 var1) {
      if (this.field8.method0(0)) {
         var1.method4();
         field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
      }
   }

   public void method4() {
      this.field6 = field7.method0();
      this.field0 = true;
      this.ct_ = Math.toRadians(field58.thePlayer == null ? 0.0D : (double)field58.thePlayer.rotationYaw);
      this.field2 = 0.0D;
      this.db_ = false;
      this.field3.o_();
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void field0(Class189 var1) {
      if (this.field8.method0(0)) {
         if (var1.field0 instanceof S12PacketEntityVelocity) {
            S12PacketEntityVelocity var2 = (S12PacketEntityVelocity)var1.field0;
            if (var2.getEntityID() != field58.thePlayer.getEntityId()) {
               return;
            }

            var1.method9();
            this.field2 += Class361.method1((double)var2.getMotionX() / 8000.0D, (double)var2.getMotionZ() / 8000.0D);
            if (this.field2 >= field7.method0() && !this.db_) {
               double var3 = Math.toRadians((double)field58.thePlayer.rotationYaw);
               field58.thePlayer.setPosition(field58.thePlayer.posX + -Math.sin(var3) * field7.method0(), field58.thePlayer.posY, field58.thePlayer.posZ + Math.cos(var3) * field7.method0());
               field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
               this.db_ = true;
               this.field3.o_();
            }
         }

      }
   }

   public static void lambda$onUpdate$1(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   public HClipModule() {
      super("HClip", Category.other, SubCategory.main, "Clips you forward");
      this.method0((Setting[])(new Setting[]{this.field8, field7}));
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (this.field8.method0(1)) {
         if (var1.field1 != null) {
            if (var1.field1.maxY > field58.thePlayer.posY) {
               var1.method9();
            }

         }
      }
   }

   public static boolean lambda$onUpdate$0(ItemStack var0) {
      return field4[field58.thePlayer.ticksExisted % field4.length].equals(BlockHitModule.method0(var0));
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field8.method0(0)) {
         if (this.db_) {
            if (this.field3.a_(250L)) {
               this.method1(false);
            }

         } else {
            int var2 = Class95.method0(HClipModule::lambda$onUpdate$0);
            if (var2 == -1) {
               Class514.method0("You need a Leaping Sword and a Silk-Edge Sword", 5000, Notifications.Class1.field0);
               this.method1(false);
            } else {
               if (Class361.method0((Class94)(new Class402(var2, HClipModule::lambda$onUpdate$1)))) {
                  var1.method9();
               }

            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (this.field8.method0(1)) {
         if (this.field0) {
            this.field0 = false;
            var1.method2(0.0D).method1(0.0D);
            field58.thePlayer.motionX = field58.thePlayer.motionZ = 0.0D;
         } else {
            double var2 = Math.min(0.27D * (double)field58.thePlayer.capabilities.getWalkSpeed() * 10.0D, this.field6);
            var1.method2(var2 * -Math.sin(this.ct_)).method1(var2 * Math.cos(this.ct_));
            field58.thePlayer.motionX = var1.w_();
            field58.thePlayer.motionZ = var1.method2();
            this.field6 -= var2;
            if (this.field6 <= 0.0D) {
               this.method1(false);
            }

         }
      }
   }
}
