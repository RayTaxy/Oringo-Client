package oringo.module;

import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class VClipModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Distance ", -7.0D, -10.0D, 10.0D, 0.1D);
   public static int H_;
   public static final EnumSetting field2 = new EnumSetting("Mode", "Hypixel", new String[]{"Hypixel", "Vanilla"});
   public static Vec3 field3;

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook && H_ == 1) {
         S08PacketPlayerPosLook var2 = (S08PacketPlayerPosLook)var1.field0;
         field3 = new Vec3(var2.getX(), var2.getY(), var2.getZ());
         H_ = 2;
      }

   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
      switch(H_) {
      case 0:
         var1.i_ -= 20.0D;
         H_ = 1;
         var1.method1(false);
         break;
      case 1:
         var1.method9();
         break;
      case 2:
         double var2 = 0.0D;

         double var4;
         for(var4 = 0.0D; field0.method0() < 0.0D && var2 <= field0.method0(); var2 += var4) {
            var4 -= 0.08D;
            var4 *= 0.9800000190734863D;
         }

         if (var2 == 0.0D) {
            var2 = field0.method0();
         }

         method2(new C04PacketPlayerPosition(field3.xCoord, field3.yCoord + var2, field3.zCoord, true));
         field58.thePlayer.setPosition(field3.xCoord, field3.yCoord + var2, field3.zCoord);
         field58.thePlayer.setVelocity(0.0D, var4, 0.0D);
         var1.method9();
         this.method1(false);
      }

   }

   public VClipModule() {
      super("VClip", Category.other, SubCategory.qol, "Clips you on the Y axis");
      this.method0(new Setting[]{field0});
   }

   public void method4() {
      if (field2.method0(1)) {
         field58.thePlayer.setPosition(field58.thePlayer.posX, field58.thePlayer.posY - field0.method0(), field58.thePlayer.posZ);
         this.method1(false);
      }

      H_ = 0;
      super.method4();
   }
}
