package oringo.module;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoJoinSkyblockModule extends Module {
   public boolean field0 = true;
   public boolean ax_ = false;
   public BooleanSetting field2 = new BooleanSetting("Auto join Hypixel", true);

   public AutoJoinSkyblockModule() {
      super("Auto Join Skyblock", Category.skyblock, SubCategory.qol);
      this.method0((Setting)this.field2);
   }

   @SubscribeEvent
   public void method0(ClientConnectedToServerEvent var1) {
      if (!var1.isLocal) {
         if (field58.getCurrentServerData().serverIP.toLowerCase().contains("hypixel")) {
            this.ax_ = true;
         }
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (this.field2.method1() && this.field0 && field58.currentScreen instanceof GuiMainMenu) {
         field58.displayGuiScreen(new GuiConnecting(field58.currentScreen, field58, new ServerData("Hypixel", "play.hypixel.net", false)));
         this.field0 = false;
      }

      if (this.ax_ && field58.currentScreen == null) {
         field58.thePlayer.sendChatMessage("/skyblock");
         this.ax_ = false;
      }

   }

   public static float method0(IBlockState var0, ItemStack var1) {
      if (var1 == null) {
         return 1.0F;
      } else {
         float var2 = var1.getItem().getDigSpeed(var1, var0);
         if (var2 > 1.0F && var1.canHarvestBlock(var0.getBlock())) {
            int var3 = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, var1);
            if (var3 > 0) {
               var2 += (float)(var3 * var3 + 1);
            }
         }

         return var2;
      }
   }
}
