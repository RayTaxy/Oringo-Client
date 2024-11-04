package oringo.command;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class207;
import map.Class447;
import map.Class514;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.module.AutoIcePathModule;
import oringo.module.Dadudze2Module;
import oringo.module.EnigmaSoulESPModule;
import oringo.module.FairySoulESPModule;
import oringo.notification.Notifications;

public class BloodSkipCommand extends Command {
   public static final Pattern field0 = Pattern.compile("§7 §cThe Catac§combs §7\\(([MF])(.*?)\\)");
   public static boolean db_;
   public static boolean field2;
   public static double field3;
   public static boolean field1;
   public static Vec3 field5;
   public static int Y_;
   public static BlockPos cB_;
   public static final Class447 field8 = new Class447();

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (cB_ != null && db_) {
         double var2 = (double)Math.max(cB_.getY(), 165);
         EnigmaSoulESPModule.method0(5.0F);
         if (Y_++ > 100) {
            cB_ = null;
            FairySoulESPModule.method6();
            return;
         }

         double var4;
         double var6;
         if (!field2) {
            if (field9.thePlayer.posY != Math.ceil(field5.yCoord)) {
               var4 = Math.ceil(field5.yCoord) - field9.thePlayer.posY;
               field9.thePlayer.setPosition((double)((int)field9.thePlayer.posX), field9.thePlayer.posY + Class207.method0(var4, 9.0D, -9.0D), (double)((int)field9.thePlayer.posZ));
            } else if (field9.thePlayer.getDistanceSq(field5.xCoord, field9.thePlayer.posY, field5.zCoord) > 0.05D) {
               var4 = Math.toRadians((double)ReplyCommand.method0(field5).method5());
               var6 = Math.min(9.0D, field9.thePlayer.getDistance(field5.xCoord, field9.thePlayer.posY, field5.zCoord));
               field9.thePlayer.setPosition(field9.thePlayer.posX + -Math.sin(var4) * var6, field9.thePlayer.posY, field9.thePlayer.posZ + Math.cos(var4) * var6);
            } else {
               field9.thePlayer.setPosition(field5.xCoord, field5.yCoord, field5.zCoord);
               field2 = true;
            }
         } else if (field9.thePlayer.posY != var2 && !field1) {
            var4 = var2 - field9.thePlayer.posY;
            field9.thePlayer.setPosition((double)((int)field9.thePlayer.posX), field9.thePlayer.posY + Class207.method0(var4, 9.0D, -9.0D), (double)((int)field9.thePlayer.posZ));
            field1 = field9.thePlayer.posY == var2;
         } else if (field9.thePlayer.getDistanceSq((double)cB_.getX(), field9.thePlayer.posY, (double)cB_.getZ()) != 0.0D) {
            var4 = Math.toRadians((double)ReplyCommand.method0(new Vec3(cB_)).method5());
            var6 = Math.min(9.0D, field9.thePlayer.getDistance((double)cB_.getX(), field9.thePlayer.posY, (double)cB_.getZ()));
            field9.thePlayer.setPosition(field9.thePlayer.posX + -Math.sin(var4) * var6, field9.thePlayer.posY, field9.thePlayer.posZ + Math.cos(var4) * var6);
         } else if (field9.thePlayer.posY != (double)cB_.getY()) {
            var4 = (double)cB_.getY() - field9.thePlayer.posY;
            field9.thePlayer.setPosition((double)((int)field9.thePlayer.posX), field9.thePlayer.posY + Class207.method0(var4, 9.0D, -9.0D), (double)((int)field9.thePlayer.posZ));
         } else {
            FairySoulESPModule.method6();
            field9.thePlayer.setPosition(field9.thePlayer.posX, field3, field9.thePlayer.posZ);
            Class514.method0("Thank you for flying with Hypixel Airlines!", 5000, Notifications.Class1.field1);
            cB_ = null;
         }

         System.out.println(field9.thePlayer.getPositionVector());
         var1.method0(field9.thePlayer.getPositionVector());
      }

   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook && cB_ != null) {
         S08PacketPlayerPosLook var2 = (S08PacketPlayerPosLook)var1.field0;
         if (var2.getY() < 50.0D) {
            return;
         }

         if ((int)var2.getY() == 72 || (int)var2.getY() == 73) {
            db_ = true;
            return;
         }

         cB_ = null;
         FairySoulESPModule.method6();
      }

   }

   public static String method0(String var0, Pattern var1, int var2) {
      Matcher var3 = var1.matcher(var0);
      return var3.find() ? var3.group(var2) : null;
   }

   public BloodSkipCommand() {
      super("bloodskip");
   }

   public static BlockPos method0(BlockPos var0, EnumFacing var1, HashSet var2) {
      do {
         var0 = var0.offset(var1);
      } while(AutoIcePathModule.field58.theWorld.getBlockState(var0).getBlock() == Blocks.air);

      var0 = var0.offset(var1, -1);
      return var2.contains(var0) ? null : var0;
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (cB_ != null) {
         var1.method4();
      }

   }

   public void method0(String[] var1) {
      Y_ = 0;
      db_ = false;
      field2 = false;
      field1 = false;
      field8.o_();
      if (field9.theWorld.getBlockState(new BlockPos(field9.thePlayer.getPositionVector())).getBlock() != Blocks.hopper) {
         Class514.method0("You need to stand on a hopper!", 5000, Notifications.Class1.field0);
      } else {
         switch(Dadudze2Module.method5()) {
         case 3:
            cB_ = new BlockPos(8, 119, 2);
            field3 = 113.0D;
            break;
         case 4:
            cB_ = new BlockPos(14, 113, -23);
            field3 = 106.0D;
            break;
         case 5:
            cB_ = new BlockPos(5, 113, 42);
            field3 = 105.0D;
            break;
         case 6:
            cB_ = new BlockPos(-15, 111, 62);
            field3 = 104.0D;
            break;
         case 7:
            cB_ = new BlockPos(90, 257, 50);
            field3 = 249.0D;
            break;
         default:
            Class514.method0("Invalid floor!", 4000, Notifications.Class1.field0);
         }

         field5 = field9.thePlayer.getPositionVector();
         field9.thePlayer.setPosition(field9.thePlayer.posX, field9.thePlayer.posY - 10.0D, field9.thePlayer.posZ);
      }
   }

   public String method1() {
      return "Teleports you to the boss room";
   }
}
