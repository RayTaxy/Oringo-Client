package oringo.module;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import map.Class302;
import map.Class447;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import oringo.Oringo;
import oringo.event.Class270;
import oringo.event.Class332;
import oringo.event.Class525;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class BlinkModule extends Module {
   public final Class447 field0 = new Class447();
   public final Queue field1 = new ConcurrentLinkedQueue();
   public BooleanSetting field2 = new BooleanSetting("Pulse", false);
   public BooleanSetting field3 = new BooleanSetting("Only pos packets", false);
   public DoubleSetting field4 = new BlinkModule$1(this, "Pulse ticks", 10.0D, 1.0D, 100.0D, 1.0D);

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field1.clear();
      if (this.method44()) {
         this.method40();
      }

   }

   public static boolean method0(double var0) {
      return !Oringo.field9.theWorld.getCollidingBoundingBoxes(Oringo.field9.thePlayer, Oringo.field9.thePlayer.getEntityBoundingBox().offset(0.0D, -var0, 0.0D)).isEmpty();
   }

   @SubscribeEvent
   public void method0(ClientDisconnectionFromServerEvent var1) {
      this.field1.clear();
      if (this.method44()) {
         this.method1(false);
      }

   }

   public static void method0(EntityItemFrame var0, int var1, EnumFacing var2, List var3) {
      // $FF: Couldn't be decompiled
   }

   public void method5() {
      if (field58.getNetHandler() != null) {
         while(!this.field1.isEmpty()) {
            Class302.method2((Packet)this.field1.poll());
         }
      }

   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (this.method44()) {
         if (field58.thePlayer == null) {
            this.field1.clear();
            this.method1(false);
            return;
         }

         if (var1.field0 instanceof C03PacketPlayer || !this.field3.method1()) {
            var1.setCanceled(true);
            this.field1.offer(var1.field0);
         }
      }

   }

   public static int method0(C0EPacketClickWindow var0) {
      return var0.getSlotId();
   }

   @SubscribeEvent
   public void method0(Class525 var1) {
      if (this.field0.a_((long)(this.field4.method0() * 50.0D)) && this.field2.method1()) {
         this.method5();
         this.field0.o_();
      }

   }

   public void method4() {
      this.field0.o_();
   }

   public BlinkModule() {
      super("Blink", Category.other, SubCategory.other, "Lag switch");
      this.method0((Setting[])(new Setting[]{this.field3, this.field2, this.field4}));
   }

   public void b_() {
      this.method5();
   }
}
