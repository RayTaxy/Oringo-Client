package oringo.command;

import com.mojang.authlib.GameProfile;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Iterator;
import map.Class157;
import map.Class418;
import map.Class95;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus.EnumState;
import net.minecraft.util.Session;
import oringo.module.NamesOnlyModule;
import oringo.module.PopupAnimationModule;

public class MoveItemCommand extends Command {
   public static boolean method0(Entity var0) {
      String var1 = ChatFormatting.stripFormatting(var0.getName()).toLowerCase();
      Entity var2 = NamesOnlyModule.field58.theWorld.getEntityByID(var0.getEntityId() + 1);
      String var3 = !(var2 instanceof EntityArmorStand) ? null : ChatFormatting.stripFormatting(var2.getName()).toLowerCase();
      Iterator var4 = NamesOnlyModule.field0.iterator();

      String var5;
      do {
         if (!var4.hasNext()) {
            return false;
         }

         var5 = (String)var4.next();
      } while(!var1.contains(var5) && (var3 == null || !var3.contains(var5)));

      return true;
   }

   public String method1() {
      return "Moves a specified item to your hotbar.";
   }

   public static void k_(String var0) {
      Class418.field0 = var0;
      GameProfile var1 = IRCNameCommand.y_(var0);
      Session var2 = new Session(var1.getName(), var1.getId().toString().replaceAll("-", ""), "2137", "mojang");
      Class157.method0(var2, "field_71449_j");
   }

   public void method0(String[] var1) {
      if (var1.length < 2) {
         PopupAnimationModule.method2("Correct usage: .moveitem [name] [hotbar slot]");
      } else {
         try {
            int var2 = Integer.parseInt(var1[var1.length - 1]);
            if (var2 < 0 || var2 > 8) {
               throw new NumberFormatException();
            }

            int var3 = 0;
            StringBuilder var4 = new StringBuilder();
            String[] var5 = var1;
            int var6 = var1.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               String var8 = var5[var7];
               ++var3;
               if (var3 != var1.length) {
                  var4.append(var8).append(" ");
               }
            }

            int var10 = Class95.method0(MoveItemCommand::lambda$execute$0);
            if (var10 == -1) {
               method2("You don't have this item!");
            }

            field9.getNetHandler().addToSendQueue(new C16PacketClientStatus(EnumState.OPEN_INVENTORY_ACHIEVEMENT));
            field9.playerController.windowClick(field9.thePlayer.inventoryContainer.windowId, var10, var2, 2, field9.thePlayer);
            field9.getNetHandler().addToSendQueue(new C0DPacketCloseWindow(0));
         } catch (NumberFormatException var9) {
            PopupAnimationModule.method2("Invalid slot type. Valid slots: [0-8]");
         }
      }

   }

   public static boolean lambda$execute$0(StringBuilder var0, ItemStack var1) {
      return var1.getDisplayName().toLowerCase().contains(var0.toString());
   }

   public MoveItemCommand() {
      super("moveitem");
   }
}
