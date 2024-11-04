package oringo.module;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import map.Class362;
import map.Class447;
import map.Class500;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class332;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class NoVoidModule extends Module {
   public final Queue field0 = new ConcurrentLinkedQueue();
   public Vec3 ac_ = new Vec3(0.0D, 0.0D, 0.0D);
   public final EnumSetting field2 = new EnumSetting("Mode", "Blink", new String[]{"Flag", "Blink"});
   public final DoubleSetting field3 = new DoubleSetting("Fall distance", 5.0D, 0.5D, 10.0D, 0.1D);
   public Vec3 field4 = new Vec3(0.0D, 0.0D, 0.0D);
   public static boolean field5;
   public final Class447 field6 = new Class447();

   public static void method1() {
      Oringo.field9.getNetHandler().getNetworkManager().channel().flush();
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C03PacketPlayer && field58.thePlayer != null) {
         C03PacketPlayer var2 = (C03PacketPlayer)var1.field0;
         if (this.field2.method0(1) && !Class362.field5.method44() && DojoHelperModule.method3() && this.field6.a_(200L)) {
            var1.method9();
            this.field0.offer(var2);
            field5 = true;
            if ((double)field58.thePlayer.fallDistance > this.field3.method0()) {
               field58.thePlayer.fallDistance = 0.0F;
               field58.thePlayer.setPosition(this.field4.xCoord, this.field4.yCoord, this.field4.zCoord);
               field58.thePlayer.setVelocity(0.0D, this.ac_.yCoord, 0.0D);
               this.field0.clear();
            }

         } else {
            this.method2();
            this.ac_ = new Vec3(field58.thePlayer.motionX, field58.thePlayer.motionY, field58.thePlayer.motionZ);
            this.field4 = field58.thePlayer.getPositionVector();
            field5 = false;
         }
      }
   }

   @SubscribeEvent
   public void a_(Class210.Class1 var1) {
      if (!Class362.field5.method44() && this.field2.method0(0)) {
         if ((double)field58.thePlayer.fallDistance > this.field3.method0() && DojoHelperModule.method3()) {
            var1.method0(field58.thePlayer.posX, field58.thePlayer.posY + 10.0D, field58.thePlayer.posZ);
         }

      }
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook) {
         this.field6.o_();
         this.ac_ = new Vec3(0.0D, 0.0D, 0.0D);
         this.field4 = new Vec3(((S08PacketPlayerPosLook)var1.field0).getX(), ((S08PacketPlayerPosLook)var1.field0).getY(), ((S08PacketPlayerPosLook)var1.field0).getZ());
         this.method2();
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field0.clear();
   }

   public void method2() {
      while(!this.field0.isEmpty()) {
         method2((Packet)this.field0.poll());
      }

   }

   public NoVoidModule() {
      super("No Void", 0, Category.player, SubCategory.player, "Prevents falling into the void");
      this.method0((Setting[])(new Setting[]{this.field2, this.field3}));
      EnumSetting var10001 = this.field2;
      this.method0((Class500)(var10001::method4));
   }
}
