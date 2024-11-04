package oringo.command;

import java.util.Random;
import map.Class296;
import map.Class515;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class75;

public class FakeBanCommand extends Command {
   public final Class296 field0 = new Class296(-999L);

   public void method0(String[] var1) throws Exception {
      this.field0.o_();
   }

   public String method1() {
      return null;
   }

   public FakeBanCommand() {
      super("fakeban", "barkos", "zombie");
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field9.theWorld != null) {
         if (this.field0.method0() == 25L) {
            method0((String)"§c§lA player has been removed from your game.");
            method0((String)"§bUse /report to continue helping out the server!");
         }

         if (this.field0.method0() == 40L) {
            field9.displayGuiScreen(new FakeBanCommand.Class0(field9.currentScreen, new ChatComponentText(String.format("§cYou are temporarily banned for §f%s §cfrom this server!\n\n§7Reason: §f%s.\n§7Find out more: §b§nhttps://www.hypixel.net/appeal\n\n§7Ban ID: §f#%s\n§7Sharing your Ban ID may affect the processing of your appeal!", "359d 23h 59m 59s", "Cheating through the use of unfair game advantages", String.format("%08X", (new Random()).nextInt(Integer.MAX_VALUE))))));
            Class515.method1();
         }

      }
   }

   public static class Class0 extends GuiDisconnected {
      public Class0(GuiScreen var1, IChatComponent var2) {
         super(var1, "disconnect.lost", var2);
      }

      public void drawDefaultBackground() {
         this.drawBackground(0);
      }
   }
}
