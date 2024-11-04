package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import map.Class350;
import map.Class361;
import map.Class386;
import map.Class496;
import map.Class514;
import map.Class94;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.ReplyCommand;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.notification.Notifications;

public class AntiTentacleModule extends Module {
   public BlockPos field10 = null;
   public boolean aJ_ = false;

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (field58.thePlayer != null && Class496.field18) {
         if (var1.field0 instanceof C0CPacketInput && field58.thePlayer.isRiding() && !this.aJ_) {
            this.aJ_ = true;
         }

      }
   }

   public AntiTentacleModule() {
      super("Anti Tentacle", Category.skyblock, SubCategory.slayer);
   }

   public static MovingObjectPosition method0(float var0, float var1, float var2) {
      Vec3 var3 = Oringo.field9.thePlayer.getPositionEyes(1.0F);
      Vec3 var4 = AutoFrozilleModule.method0(var0, var1);
      Vec3 var5 = var3.addVector(var4.xCoord * (double)var2, var4.yCoord * (double)var2, var4.zCoord * (double)var2);
      return Oringo.field9.theWorld.rayTraceBlocks(var3, var5, false, true, true);
   }

   public static boolean lambda$null$0(String var0) {
      return ChatFormatting.stripFormatting(var0).contains("Ether Transmission");
   }

   public static boolean lambda$onMotion$1(ItemStack var0) {
      return var0.getTooltip(field58.thePlayer, false).stream().anyMatch(AntiTentacleModule::lambda$null$0);
   }

   public static void method0(Framebuffer var0) {
      if (var0 != null && var0.depthBuffer > -1 && Class386.field0.getFramebuffer() != var0) {
         AutoCloakModule.method0(var0);
         var0.depthBuffer = -1;
      }

   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (Class496.field18) {
         if (!this.aJ_) {
            if (field58.thePlayer.onGround) {
               this.field10 = ThystHiderModule.method3();
            }
         } else {
            int var2 = TrailModule.method0(AntiTentacleModule::lambda$onMotion$1);
            if (var2 == -1) {
               Class514.method0("Unable to find an item with etherwarp!", 5000, Notifications.Class1.field0);
               return;
            }

            if (!Class361.method0((Class94)(new Class350(var2)))) {
               return;
            }

            var1.method2(true);
            var1.method0(ReplyCommand.method0((new Vec3(this.field10)).addVector(0.5D, 0.5D, 0.5D)));
         }

         this.aJ_ = false;
      }
   }
}
