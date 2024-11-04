package oringo.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.module.PopupAnimationModule;
import oringo.module.SecretHitboxesModule;

public class ChecknameCommand extends Command {
   public static String field0;

   public void method0(String[] var1) {
      if (var1.length != 1) {
         PopupAnimationModule.method2("/checkname [IGN]");
      } else {
         Iterator var2 = field9.theWorld.playerEntities.iterator();

         EntityPlayer var3;
         do {
            if (!var2.hasNext()) {
               PopupAnimationModule.method2("Invalid name");
               return;
            }

            var3 = (EntityPlayer)var2.next();
         } while(!var3.getName().equalsIgnoreCase(var1[0]));

         if (var3.getDistanceToEntity(field9.thePlayer) > 6.0F) {
            PopupAnimationModule.method2("You are too far away!");
         } else if (field9.thePlayer.getHeldItem() != null) {
            PopupAnimationModule.method2("Your hand must be empty!");
         } else {
            if (!field9.playerController.isPlayerRightClickingOnEntity(field9.thePlayer, var3, new MovingObjectPosition(var3))) {
               field9.playerController.interactWithEntitySendPacket(field9.thePlayer, var3);
            }

            field0 = var1[0];
         }
      }
   }

   public ChecknameCommand() {
      super("checkname", "shownicker", "denick", "revealname");
   }

   public static void lambda$onGui$0() {
      try {
         Thread.sleep(100L);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

      ItemStack var0 = Minecraft.getMinecraft().thePlayer.openContainer.getSlot(22).getStack();
      if (var0 != null && var0.getItem().equals(Items.skull)) {
         String var1 = var0.serializeNBT().getCompoundTag("tag").getCompoundTag("SkullOwner").getString("Name");
         Minecraft.getMinecraft().thePlayer.closeScreen();
         SecretHitboxesModule.method0("Real name: " + ChatFormatting.GOLD + var1.replaceFirst("§", ""), 4000);
      }

      field0 = null;
   }

   public String method1() {
      return null;
   }

   @SubscribeEvent
   public void method0(GuiOpenEvent var1) {
      if (var1.gui instanceof GuiChest && field0 != null && ((ContainerChest)((GuiChest)var1.gui).inventorySlots).getLowerChestInventory().getName().toLowerCase().startsWith(field0.toLowerCase())) {
         (new Thread(ChecknameCommand::lambda$onGui$0)).start();
      }

   }
}
