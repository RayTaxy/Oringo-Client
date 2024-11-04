package oringo.command;

import map.Class302;
import map.Class514;
import map.Class95;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.module.AutoCloakModule;
import oringo.module.BlockHitModule;
import oringo.module.ServerRotationsModule;
import oringo.notification.Notifications;

public class ClipCommand extends Command {
   public int al_ = -1;
   public static Vec3 field3;
   public double i_;
   public int H_;

   public static boolean lambda$onUpdate$0(ItemStack var0) {
      return "RABBIT_HAT".equals(BlockHitModule.method0(var0));
   }

   public ClipCommand() {
      super("clip", "vclip");
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.i_ != 0.0D) {
         field9.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
         switch(this.H_) {
         case -2:
            if (!field9.thePlayer.isPotionActive(Potion.jump) && this.al_ == -1) {
               int var2 = Class95.method0(ClipCommand::lambda$onUpdate$0);
               if (var2 == -1) {
                  this.i_ = 0.0D;
                  Class514.method0("Unable to find a rabbit hat!", 4000, Notifications.Class1.field0);
                  return;
               }

               this.al_ = var2;
               ServerRotationsModule.method0(var2, 0);
               ServerRotationsModule.method0(5, 0);
               ServerRotationsModule.method0(var2, 0);
            }

            var1.i_ += 0.05D;
            var1.method1(false);
            this.H_ = -1;
            break;
         case -1:
            this.H_ = 0;
            break;
         case 0:
            var1.i_ -= 20.0D;
            this.H_ = 1;
            var1.method1(false);
            break;
         case 1:
            var1.method9();
            break;
         case 2:
            var1.method9();
            if (field9.thePlayer.isPotionActive(Potion.jump)) {
               if (this.al_ != -1) {
                  ServerRotationsModule.method0(this.al_, 0);
                  ServerRotationsModule.method0(5, 0);
                  ServerRotationsModule.method0(this.al_, 0);
                  this.al_ = -1;
               }

               Class302.method2(new C04PacketPlayerPosition(field3.xCoord, this.i_, field3.zCoord, true));
               field9.thePlayer.setPosition(field3.xCoord, this.i_, field3.zCoord);
               this.i_ = 0.0D;
            }
         }

      }
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook && this.H_ == 1) {
         S08PacketPlayerPosLook var2 = (S08PacketPlayerPosLook)var1.field0;
         field3 = new Vec3(var2.getX(), var2.getY(), var2.getZ());
         this.H_ = 2;
      }

   }

   public String method1() {
      return "Clips you up x blocks";
   }

   public void method0(String[] var1) {
      this.al_ = -1;
      if (this.i_ != 0.0D) {
         this.i_ = 0.0D;
         Class514.method0("Disabled clip", 2500, Notifications.Class1.field1);
      } else {
         this.H_ = -2;
         if (var1.length == 1) {
            this.i_ = field9.thePlayer.posY + Double.parseDouble(var1[0]);
         } else {
            int var2 = (int)field9.thePlayer.posY;

            for(int var3 = 0; var2 > -10; --var2) {
               BlockPos var4 = new BlockPos(field9.thePlayer.posX, (double)var2, field9.thePlayer.posZ);
               IBlockState var5 = field9.theWorld.getBlockState(var4);
               if (var5.getBlock().equals(Blocks.air) && !(var5.getBlock() instanceof BlockLiquid)) {
                  ++var3;
                  if (var3 == 2) {
                     if (AutoCloakModule.method0(var2)) {
                        return;
                     }
                     break;
                  }
               } else {
                  var3 = 0;
               }
            }

            if (var2 != 0) {
               this.i_ = (double)var2;
            } else {
               Class514.method0("No valid position!", 1500, Notifications.Class1.field0);
            }
         }

      }
   }
}
