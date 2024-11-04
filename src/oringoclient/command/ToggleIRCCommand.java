package oringo.command;

import java.io.DataOutputStream;
import java.io.IOException;
import map.Class34;
import map.Class390;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class332;
import oringo.module.DadudzeModule;

public class ToggleIRCCommand extends Command {
   public static boolean field0;

   public static void method0(DataOutputStream var0, byte[] var1) {
      try {
         int var2 = var1.length;
         int[] var3 = new int[4];

         int var4;
         for(var4 = 0; var2 > 0; ++var4) {
            var3[var4] = var2 % 256;
            var2 >>= 8;
         }

         var0.writeByte(var4);

         for(int var5 = 0; var5 < var4; ++var5) {
            var0.writeByte(var3[var4 - var5 - 1]);
         }

         var0.write(var1);
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      }
   }

   public String method1() {
      return null;
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C01PacketChatMessage) {
         C01PacketChatMessage var2 = (C01PacketChatMessage)var1.field0;
         String var3 = var2.getMessage();
         if (!var3.isEmpty() && var3.charAt(0) != '/' && field0) {
            Class390.method0(var3);
            var1.method9();
         }
      }

   }

   public ToggleIRCCommand() {
      super("toggleirc", "irctoggle");
   }

   public static Class34 method0(BlockPos var0) {
      double var1 = DadudzeModule.method3();
      double var3 = -Math.sin(var1) * 0.5D;
      double var5 = Math.cos(var1) * 0.5D;
      double var7 = (double)var0.getX() - Oringo.field9.thePlayer.posX - var3;
      double var9 = (double)var0.getY() - Oringo.field9.thePlayer.prevPosY - (double)Oringo.field9.thePlayer.getEyeHeight();
      double var11 = (double)var0.getZ() - Oringo.field9.thePlayer.posZ - var5;
      double var13 = Math.hypot(var7, var11);
      float var15 = (float)(Math.atan2(var11, var7) * 180.0D / 3.141592653589793D - 90.0D);
      float var16 = (float)(-(Math.atan2(var9, var13) * 180.0D / 3.141592653589793D));
      return new Class34(var15, var16);
   }

   public void method0(String[] var1) {
      field0 = !field0;
      method2(field0 ? "You are now in the IRC channel!" : "You are no longer in the IRC channel!");
   }
}
