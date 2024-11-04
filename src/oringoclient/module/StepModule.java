package oringo.module;

import map.Class119;
import map.Class12;
import map.Class362;
import map.Class492;
import map.Class500;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class217;
import oringo.event.Class523;
import oringo.mixin.MinecraftAccessor;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class StepModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Height", 1.0D, 1.0D, 1.5D, 0.5D);
   public static final DoubleSetting cA_ = new DoubleSetting("Timer", 0.3D, 0.1D, 10.0D, 0.1D);
   public static final EnumSetting field2 = new EnumSetting("Mode ", "NCP", new String[]{"NCP"});
   public static boolean field3;

   public static boolean C_() {
      return OpenGlHelper.isFramebufferEnabled() && OpenGlHelper.areShadersSupported();
   }

   @SubscribeEvent
   public void field0(Class523.Class0 var1) {
      if (this.method44() && !Class362.field20.method44() && !field58.thePlayer.movementInput.jump && !field58.thePlayer.isInWater() && !field58.thePlayer.isInLava()) {
         Class492.method0(field58.thePlayer.getEntityBoundingBox().minY - field58.thePlayer.posY);
      }

   }

   public static double method5() {
      return Math.sqrt(Oringo.field9.thePlayer.motionX * Oringo.field9.thePlayer.motionX + Oringo.field9.thePlayer.motionZ * Oringo.field9.thePlayer.motionZ);
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (field3) {
         field3 = false;
         ((MinecraftAccessor)field58).getTimer().timerSpeed = 1.0F;
      }

   }

   public void b_() {
      if (field3) {
         field3 = false;
         ((MinecraftAccessor)field58).getTimer().timerSpeed = 1.0F;
      }

   }

   @SubscribeEvent
   public void method0(Class523.Class1 var1) {
      if (this.method44() && !Class362.field20.method44() && !field58.thePlayer.movementInput.jump && !field58.thePlayer.isInWater() && !field58.thePlayer.isInLava() && field58.thePlayer.onGround) {
         var1.method0(field0.method0());
      }

   }

   public static String method0(StackTraceElement var0) {
      return var0.getClassName();
   }

   public StepModule() {
      super("Step", Category.movement, SubCategory.movement, "Allows you to step up higher");
      this.method0((Setting[])(new Setting[]{field2, field0, cA_}));
      EnumSetting var10001 = field2;
      this.method0((Class500)(var10001::method4));
   }

   public static void method7() {
      if (!Class119.field1) {
         Class12 var0 = AntiNickerModule.method2();
         if (var0 != null) {
            Class119.field4 = var0.method0();
            int var1 = var0.method1();
            int var2 = Class119.field4 + 4;
            Class119.field7 = var1 % 128 % var2;
            Class119.field3 = (var1 >> 7) % var2;
            Class119.field1 = true;
         }
      }
   }
}
