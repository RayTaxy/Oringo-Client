package map;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.event.Class307;
import oringo.module.BlockHitModule;
import oringo.module.Category;
import oringo.module.FragHelperModule;
import oringo.module.GhostBlocksModule;
import oringo.module.SubCategory;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class Class475 extends Class408 {
   public int field0;
   public final BooleanSetting field1 = new BooleanSetting("From inventory", false);
   public static final Pattern field2 = Pattern.compile("(.*) opened a WITHER door!");
   public static final Predicate field3 = Class475::lambda$static$0;
   public final EnumSetting field4 = new EnumSetting("Activate", "Swing", new String[]{"Swing", "Keybind", "Door Open"});
   public final BooleanSetting field5 = new BooleanSetting("Only leap held", true);
   public String cW_;
   public boolean field7;

   public Class475() {
      super("Auto Leap", Category.dungeon, SubCategory.main, "Leaps to the door opener when activated");
      this.method0((Setting[])(new Setting[]{this.field5, this.field4, this.field1}));
   }

   public static void method0(BlockPos var0) {
      IBlockState var1 = Oringo.field9.theWorld.getBlockState(var0);
      Block var2 = var1.getBlock();
      var2.setBlockBoundsBasedOnState(Oringo.field9.theWorld, var0);
      GhostBlocksModule.method0(FragHelperModule.method0(var0).offset(-Oringo.field9.getRenderManager().viewerPosX, -Oringo.field9.getRenderManager().viewerPosY, -Oringo.field9.getRenderManager().viewerPosZ), false);
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (Class496.field5 && var1.type != 2) {
         String var2 = var1.message.getUnformattedText();
         Matcher var3 = field2.matcher(var2);
         if (var3.find()) {
            this.cW_ = var3.group(1);
            if (this.cW_.contains(field58.thePlayer.getName())) {
               return;
            }

            if (this.field4.method0(2)) {
               this.method9();
            }
         }

      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.field7) {
         if (var1.field0 instanceof S2DPacketOpenWindow) {
            S2DPacketOpenWindow var2 = (S2DPacketOpenWindow)var1.field0;
            if (!var2.getWindowTitle().getUnformattedText().equals("Spirit Leap")) {
               this.field7 = false;
               return;
            }

            var1.method9();
            this.field0 = var2.getWindowId();
         } else if (var1.field0 instanceof S30PacketWindowItems) {
            S30PacketWindowItems var9 = (S30PacketWindowItems)var1.field0;
            if (var9.func_148911_c() != this.field0) {
               return;
            }

            this.field7 = false;
            int var3 = -1;
            ItemStack[] var4 = var9.getItemStacks();
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               ItemStack var7 = var4[var6];
               ++var3;
               if (var7 != null && var7.getItem() instanceof ItemSkull) {
                  String var8 = var7.getDisplayName();
                  if (var8.contains(this.cW_)) {
                     field58.playerController.windowClick(this.field0, var3, 0, 4, field58.thePlayer);
                     field58.thePlayer.closeScreen();
                     return;
                  }
               }
            }
         }
      }

   }

   public static boolean lambda$static$0(ItemStack var0) {
      String var1 = BlockHitModule.method0(var0);
      return "SPIRIT_LEAP".equals(var1) || "INFINITE_SPIRIT_LEAP".equals(var1);
   }

   public static int method2(ItemStack var0) {
      return EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, var0) + EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, var0) * 2;
   }

   public static int method0(int var0, int var1) {
      StringBuilder var2 = new StringBuilder();

      for(int var3 = 140; var3 >= 12; --var3) {
         int var4 = Block.getIdFromBlock(Class469.field0.theWorld.getBlockState(new BlockPos(var0, var3, var1)).getBlock());
         if (var4 != 5 && var4 != 54) {
            var2.append(var4);
         }
      }

      return var2.toString().hashCode();
   }

   @SubscribeEvent
   public void method0(Class307 var1) {
      if (this.field4.method0(0)) {
         this.method9();
      }

   }

   public void method9() {
      if (this.cW_ != null && Class496.field5) {
         String var1 = BlockHitModule.method0(field58.thePlayer.getHeldItem());
         if (!this.field5.method1() || "SPIRIT_LEAP".equals(var1) || "INFINITE_SPIRIT_LEAP".equals(var1)) {
            int var2 = Class95.method0(field3);
            if (var2 == -1 || !this.field1.method1() && var2 < 36) {
               return;
            }

            Class361.method0((Class94)(new Class402(var2, Class475::lambda$handlePressKey$1)));
            this.field7 = true;
         }

      }
   }

   public static void lambda$handlePressKey$1(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.cW_ = null;
      this.field7 = false;
   }
}
