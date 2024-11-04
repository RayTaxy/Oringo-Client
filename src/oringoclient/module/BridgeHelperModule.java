package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class194;
import oringo.event.Class270;
import oringo.event.Class439;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class BridgeHelperModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Goal Blocker", true, "Prevents you from falling out of the goal");
   public Block cu_ = null;

   @SubscribeEvent
   public void method0(Class439 var1) {
      Entity var2 = var1.field0;
      if (var2 instanceof EntityArmorStand && ChatFormatting.stripFormatting(var2.getDisplayName().getFormattedText()).equals("Jump in to score!")) {
         BlockPos var3 = AutoPowderChestModule.method0(new BlockPos(var2));
         IBlockState var4 = field58.theWorld.getBlockState(var3);
         this.cu_ = var4.getBlock();
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.cu_ = null;
   }

   public static Color method0(Color var0, Color var1, float var2) {
      return new Color((int)((float)var0.getRed() + (float)(var1.getRed() - var0.getRed()) * var2), (int)((float)var0.getGreen() + (float)(var1.getGreen() - var0.getGreen()) * var2), (int)((float)var0.getBlue() + (float)(var1.getBlue() - var0.getBlue()) * var2));
   }

   public boolean method0(BlockPos var1) {
      var1 = AutoPowderChestModule.method0(var1);
      IBlockState var2 = field58.theWorld.getBlockState(var1);
      return var2.getBlock() == this.cu_;
   }

   public BridgeHelperModule() {
      super("Bridge Helper", Category.combat, SubCategory.combat, "Useful features for the bridge mode");
      this.method0((Setting[])(new Setting[]{this.field0}));
   }

   public static String method0(ItemStack var0) {
      if (var0 == null) {
         return null;
      } else {
         NBTTagCompound var1 = TargetStrafeModule.method0(var0);
         return var1 != null && var1.hasKey("modifier", 8) ? var1.getString("modifier") : null;
      }
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (this.field0.method1()) {
         BlockPos var2 = var1.field0;
         if (this.method0(ThystHiderModule.method3()) && !this.method0(var2) && var1.field2 == Blocks.air) {
            var1.field1 = new AxisAlignedBB(var2, var2.add(1, 1, 1));
         }

      }
   }
}
