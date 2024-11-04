package oringo.module;

import java.util.Random;
import map.Class229;
import map.Class302;
import map.Class447;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class ServerBeamerModule extends Module {
   public static final EnumSetting field0 = new EnumSetting("Packet", "C08", new String[]{"C08", "C04"});
   public static final DoubleSetting cx_ = new DoubleSetting("Packets", 10.0D, 1.0D, 50.0D, 1.0D);
   public static C06PacketPlayerPosLook field2;
   public static final DoubleSetting field3 = new DoubleSetting("Delay", 100.0D, 0.0D, 1000.0D, 50.0D, ServerBeamerModule::lambda$static$0);
   public final Class447 field4 = new Class447();
   public static final EnumSetting field5 = new EnumSetting("Mode", "Delay", new String[]{"Sync", "Delay"});

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void field0(Class189 var1) {
      if (this.method44() && var1.field0 instanceof S08PacketPlayerPosLook && field0.method0(1)) {
         field2 = AutoHealModule.method0((S08PacketPlayerPosLook)var1.field0);
         var1.setCanceled(true);
      }

   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (this.method44()) {
         if (field0.method0(1) && !(var1.field0 instanceof C03PacketPlayer) && field2 != null) {
            var1.setCanceled(true);
         }

         if (field5.method0(0) && var1.field0 instanceof C0FPacketConfirmTransaction) {
            this.method7();
         }
      }

   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.method44()) {
         if (field2 == null && field0.method0(1)) {
            var1.method0(2137.0D, 2137.0D, 2137.0D);
         } else if (field5.method0(1) && this.field4.a_((long)field3.method0())) {
            this.method7();
            this.field4.o_();
         }

      }
   }

   public void method4() {
      field2 = null;
   }

   public ServerBeamerModule() {
      super("Server Beamer", Category.other, SubCategory.other, (String)null);
      this.method0((Setting[])(new Setting[]{field5, field0, cx_, field3}));
   }

   public static float method5() {
      return Math.min(1.0F, (float)Class229.field5.method0() / 1000.0F);
   }

   public static Boolean lambda$static$0() {
      return !field5.method0(1);
   }

   public void method7() {
      for(int var1 = 0; (double)var1 < cx_.method0(); ++var1) {
         String var2 = field0.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case 65927:
            if (var2.equals("C04")) {
               var3 = 0;
            }
            break;
         case 65931:
            if (var2.equals("C08")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
            if (field2 == null) {
               return;
            }

            BlockPos var4 = new BlockPos((new Random()).nextInt(1000) * 16, 255, (new Random()).nextInt(1000) * 16);
            Class302.method2(new C04PacketPlayerPosition((double)var4.getX(), (double)var4.getY(), (double)var4.getZ(), false));
            Class302.method2(field2);
            break;
         case 1:
            BlockPos var5 = new BlockPos((new Random()).nextInt(16000), 1, (new Random()).nextInt(16000));
            EnumFacing var6 = EnumFacing.UP;
            Vec3 var7 = (new Vec3(var5)).add(new Vec3(var6.getDirectionVec()));
            float var8 = (float)(var7.xCoord - (double)var5.getX());
            float var9 = (float)(var7.yCoord - (double)var5.getY());
            float var10 = (float)(var7.zCoord - (double)var5.getZ());
            field58.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(var5, var6.getIndex(), field58.thePlayer.getHeldItem(), var8, var9, var10));
         }
      }

   }
}
