package oringo.command;

import java.util.HashSet;
import java.util.Random;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import oringo.module.AutoCraftModule;
import oringo.module.PopupAnimationModule;

public class SelfBanCommand extends Command {
   public boolean field0 = false;
   public static boolean field1 = false;

   public String method1() {
      return null;
   }

   public static EnumFacing method0(BlockPos var0, HashSet var1, BlockPos var2) {
      EnumFacing var3 = null;
      int var4 = 50;
      var1.add(var0);
      EnumFacing[] var5 = EnumFacing.HORIZONTALS;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         EnumFacing var8 = var5[var7];
         BlockPos var9 = BloodSkipCommand.method0(var0, var8, var1);
         if (var9 != null) {
            if (var9.equals(var2)) {
               return var8;
            }

            int var10 = BindCommand.method0(var9, new HashSet(var1), var2);
            if (var10 != -1 && var10 < var4) {
               var3 = var8;
               var4 = var10;
            }
         }
      }

      return var3;
   }

   public static void t_(String var0) {
      ++AutoCraftModule.field3;
      AutoCraftModule.field4.o_();
      AutoCraftModule.field58.thePlayer.sendChatMessage(var0);
   }

   public static String method0(String var0, String var1, String var2) {
      int var3 = var0.length();
      int var4 = var1.length();
      if (var3 < var4) {
         return var0;
      } else {
         StringBuilder var5 = new StringBuilder(var4);
         int var6 = 0;

         for(int var7 = 0; var7 < var3; ++var7) {
            char var8 = var0.charAt(var7);
            if (var8 == var1.charAt(var6)) {
               ++var6;
               if (var6 == var4) {
                  var5.append(var2);
                  var6 = 0;
               }
            } else if (var6 > 0) {
               var5.append(var0, var7 - var6, var7 + 1);
               var6 = 0;
            } else {
               var5.append(var8);
            }
         }

         if (var6 > 0) {
            var5.append(var0, var3 - var6, var3);
         }

         return var5.toString();
      }
   }

   public SelfBanCommand() {
      super("selfban");
   }

   public void method0(String[] var1) {
      if (var1.length == 1 && var1[0].equals("confirm")) {
         if (!this.field0) {
            PopupAnimationModule.method2("This command is §cNOT §fa fake ban command! If you use it again it will ban you!");
            this.field0 = true;
            return;
         }

         PopupAnimationModule.method2("You will get banned in ~3 seconds!");
         field1 = true;

         for(int var2 = 0; var2 < 10; ++var2) {
            field9.getNetHandler().getNetworkManager().sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos((new Random()).nextInt(), (new Random()).nextInt(), (new Random()).nextInt()), 1, field9.thePlayer.inventory.getCurrentItem(), 0.0F, 0.0F, 0.0F));
         }
      }

   }
}
