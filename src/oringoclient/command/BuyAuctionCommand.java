package oringo.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class447;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.module.KillAuraModule;
import oringo.module.ScoreboardModule;

public class BuyAuctionCommand extends Command {
   public static final Class447 field0 = new Class447();

   public BuyAuctionCommand() {
      super("buyauction");
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (!field0.a_(1000L)) {
         if (var1.field0 instanceof S2DPacketOpenWindow) {
            S2DPacketOpenWindow var2 = (S2DPacketOpenWindow)var1.field0;
            if (var2.getWindowTitle().getUnformattedText().equals("BIN Auction View")) {
               field9.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(var2.getWindowId(), 31, 0, 0, (ItemStack)null, (short)1));
            }

            if (var2.getWindowTitle().getUnformattedText().equals("Confirm Purchase")) {
               field9.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(var2.getWindowId(), 11, 0, 0, (ItemStack)null, (short)1));
            }
         }

      }
   }

   public String method1() {
      return "Buys the specified auction almost instantly";
   }

   public void method0(String[] var1) throws Exception {
      if (var1.length != 0) {
         ScoreboardModule.v_(var1[0]);
      }
   }

   public static void method2() {
      if (KillAuraModule.field58.thePlayer.isUsingItem()) {
         KillAuraModule.field58.playerController.onStoppedUsingItem(KillAuraModule.field58.thePlayer);
      }
   }

   public static int method0(String var0, Pattern var1, int var2) {
      Matcher var3 = var1.matcher(var0);
      return var3.find() ? Integer.parseInt(var3.group(var2)) : -1;
   }
}
