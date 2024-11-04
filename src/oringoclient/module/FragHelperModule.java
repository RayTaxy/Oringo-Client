package oringo.module;

import java.util.Iterator;
import map.Class12;
import map.Class362;
import map.Class374;
import map.Class447;
import map.Class496;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class270;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class FragHelperModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("/dh", true);
   public boolean bS_ = false;
   public final StringSetting field2 = new StringSetting("Frag Bot", "", 16);
   public final BooleanSetting field3 = new BooleanSetting("/pd", true);
   public final BooleanSetting field4 = new BooleanSetting("Auto Ready", true);
   public final Class447 field5 = new Class447();
   public final Class447 field6 = new Class447();
   public final EnumSetting field7 = new EnumSetting("Auto Join", "None", new String[]{"None", "E", "F1", "F2", "F3", "F4", "F5", "F6", "F7"});
   public final Class447 field8 = new Class447();

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (!this.field7.method0(0) && !this.field5.a_(5000L) && var1.message.getUnformattedText().endsWith("joined the party.") && "Catacombs Gate".equals(Class374.method0())) {
         field58.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(field58.thePlayer.openContainer.windowId, this.method5(), 0, 0, (ItemStack)null, DiscordRPCModule.method6()));
      }

   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.gui instanceof GuiChest && "Catacombs Gate".equals(Class374.method0()) && !this.field2.F_()) {
         int var2 = field58.fontRendererObj.getStringWidth("/party " + this.field2.method1());
         ScaledResolution var3 = new ScaledResolution(field58);
         int var4 = var3.getScaledWidth() / 2 - 88 - var2 - 26;
         var1.buttonList.add(new GuiButton(3321, var4, var3.getScaledHeight() / 2 - 70, var2 + 15, 20, "/party " + this.field2.method1()));
      }

      if (var1.gui instanceof GuiChest && "Undersized party!".equals(Class374.method0()) && !this.field7.method0(0)) {
         this.field6.o_();
      }

   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      this.bS_ = false;
   }

   public static float method0(ItemStack var0) {
      float var1 = 0.0F;
      if (var0 != null && (var0.getItem() instanceof ItemTool || var0.getItem() instanceof ItemSword)) {
         if (var0.getItem() instanceof ItemSword) {
            var1 += 4.0F;
            ++var1;
         } else if (var0.getItem() instanceof ItemAxe) {
            var1 += 3.0F;
         } else if (var0.getItem() instanceof ItemPickaxe) {
            var1 += 2.0F;
         } else if (var0.getItem() instanceof ItemSpade) {
            ++var1;
         }

         var1 += var0.getItem() instanceof ItemTool ? ((ItemTool)var0.getItem()).getToolMaterial().getDamageVsEntity() : ((ItemSword)var0.getItem()).getDamageVsEntity();
         var1 = (float)((double)var1 + 1.25D * (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, var0));
         var1 = (float)((double)var1 + (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, var0) * 0.5D);
      }

      return var1;
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C01PacketChatMessage) {
         if (((C01PacketChatMessage)var1.field0).getMessage().equalsIgnoreCase("/dh") & this.field0.method1()) {
            field58.thePlayer.sendChatMessage("/warp dungeon_hub");
            var1.method9();
         }

         if (((C01PacketChatMessage)var1.field0).getMessage().equalsIgnoreCase("/pd") & this.field3.method1()) {
            field58.thePlayer.sendChatMessage("/party disband");
            var1.method9();
         }
      }

   }

   public FragHelperModule() {
      super("Frag Helper", Category.dungeon, SubCategory.main, "Helps with frag runs");
      this.method0((Setting[])(new Setting[]{this.field2, this.field7, this.field4, this.field0, this.field3}));
      this.field8.method0(999999L);
   }

   public static Class12 method0(int var0, int var1) {
      var0 = Math.round((float)(var0 - -185) / 32.0F);
      var1 = Math.round((float)(var1 - -185) / 32.0F);
      return new Class12(var0 * 32 + -185, var1 * 32 + -185);
   }

   public int method5() {
      String var1 = this.field7.method4();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case 69:
         if (var1.equals("E")) {
            var2 = 0;
         }
         break;
      case 2219:
         if (var1.equals("F1")) {
            var2 = 1;
         }
         break;
      case 2220:
         if (var1.equals("F2")) {
            var2 = 2;
         }
         break;
      case 2221:
         if (var1.equals("F3")) {
            var2 = 3;
         }
         break;
      case 2222:
         if (var1.equals("F4")) {
            var2 = 4;
         }
         break;
      case 2223:
         if (var1.equals("F5")) {
            var2 = 5;
         }
         break;
      case 2224:
         if (var1.equals("F6")) {
            var2 = 6;
         }
         break;
      case 2225:
         if (var1.equals("F7")) {
            var2 = 7;
         }
      }

      switch(var2) {
      case 0:
         return 11;
      case 1:
         return 12;
      case 2:
         return 13;
      case 3:
         return 14;
      case 4:
         return 15;
      case 5:
         return 21;
      case 6:
         return 22;
      case 7:
         return 23;
      default:
         return -1;
      }
   }

   @SubscribeEvent
   public void method0(net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Post var1) {
      if ("Catacombs Gate".equals(Class374.method0()) && var1.button.id == 3321) {
         field58.thePlayer.sendChatMessage(var1.button.displayString);
      }

   }

   public static AxisAlignedBB method0(BlockPos var0) {
      IBlockState var1 = Oringo.field9.theWorld.getBlockState(var0);
      Block var2 = var1.getBlock();
      boolean var3 = Class362.field50.cg_.method1();
      Class362.field50.cg_.method0(false);
      AxisAlignedBB var4 = var2.getSelectedBoundingBox(Oringo.field9.theWorld, var0);
      Class362.field50.cg_.method0(var3);
      return var4;
   }

   @SubscribeEvent
   public void method0(net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Post var1) {
      String var2 = Class374.method0();
      if (var2 != null && var2.startsWith("Creating dungeon..") && this.field8.a_(90000L)) {
         this.field8.o_();
      }

      if ("Catacombs Gate".equals(var2)) {
         if (!this.field8.a_(90000L)) {
            String var3 = "Cooldown: " + (90000L - this.field8.method2()) / 1000L + "s";
            field58.fontRendererObj.drawStringWithShadow(var3, (float)((new ScaledResolution(field58)).getScaledWidth() - field58.fontRendererObj.getStringWidth(var3)) / 2.0F, 50.0F, -1);
         } else if (!this.field7.method0(0) && this.field5.a_(5000L)) {
            this.field5.o_();
            field58.thePlayer.sendChatMessage("/p " + this.field2.method1());
         }
      }

      if ("Undersized party!".equals(var2) && !this.field7.method0(0) && this.field6.a_(1000L)) {
         this.field6.o_();
         field58.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(((GuiChest)var1.gui).inventorySlots.windowId, 13, 0, 0, (ItemStack)null, DiscordRPCModule.method6()));
      }

      if ("Start Dungeon?".equals(var2) && this.field4.method1() && this.field6.a_(1000L)) {
         this.field6.o_();
         field58.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(((GuiChest)var1.gui).inventorySlots.windowId, 13, 0, 0, (ItemStack)null, DiscordRPCModule.method6()));
      }

      if ("Start Dungeon?".equals(var2) && this.field4.method1() && Class496.field5 && this.field6.a_(1000L)) {
         this.field6.o_();
         field58.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(((GuiChest)var1.gui).inventorySlots.windowId, 13, 0, 0, (ItemStack)null, DiscordRPCModule.method6()));
      }

      if (var2 != null && var2.startsWith("Catacombs - ") && !this.bS_ && Class496.field5 && this.field4.method1() && this.field6.a_(300L)) {
         this.bS_ = true;
         Iterator var5 = field58.thePlayer.openContainer.inventorySlots.iterator();

         while(var5.hasNext()) {
            Slot var4 = (Slot)var5.next();
            if (var4.getStack() != null && var4.getStack().getItem() == Items.skull && var4.getStack().getDisplayName().contains(field58.thePlayer.getName())) {
               field58.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(((GuiChest)var1.gui).inventorySlots.windowId, 4, 0, 0, (ItemStack)null, DiscordRPCModule.method6()));
               break;
            }
         }
      }

   }
}
