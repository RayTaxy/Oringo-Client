package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Start;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;

public class NoBlockModule extends Module {
   public static boolean lambda$onStartUsing$0(String var0) {
      return ChatFormatting.stripFormatting(var0).startsWith("Ability") && var0.endsWith("RIGHT CLICK");
   }

   public static boolean method1() {
      for(int var0 = MathHelper.floor_double(Oringo.field9.thePlayer.getEntityBoundingBox().minX); var0 < MathHelper.floor_double(Oringo.field9.thePlayer.getEntityBoundingBox().maxX) + 1; ++var0) {
         for(int var1 = MathHelper.floor_double(Oringo.field9.thePlayer.getEntityBoundingBox().minY); var1 < MathHelper.floor_double(Oringo.field9.thePlayer.getEntityBoundingBox().maxY) + 1; ++var1) {
            for(int var2 = MathHelper.floor_double(Oringo.field9.thePlayer.getEntityBoundingBox().minZ); var2 < MathHelper.floor_double(Oringo.field9.thePlayer.getEntityBoundingBox().maxZ) + 1; ++var2) {
               Block var3 = Oringo.field9.theWorld.getBlockState(new BlockPos(var0, var1, var2)).getBlock();
               if (var3 != null && !(var3 instanceof BlockAir)) {
                  AxisAlignedBB var4 = var3.getCollisionBoundingBox(Oringo.field9.theWorld, new BlockPos(var0, var1, var2), Oringo.field9.theWorld.getBlockState(new BlockPos(var0, var1, var2)));
                  if (var4 != null && Oringo.field9.thePlayer.getEntityBoundingBox().intersectsWith(var4)) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public NoBlockModule() {
      super("No Block", Category.skyblock, SubCategory.qol, "Prevents you from blocking with items if they have abilities");
   }

   @SubscribeEvent
   public void method0(Start var1) {
      if (var1.item != null && var1.item.getTooltip(field58.thePlayer, false).stream().anyMatch(NoBlockModule::lambda$onStartUsing$0)) {
         var1.setCanceled(true);
         KeyBinding.setKeyBindState(field58.gameSettings.keyBindUseItem.getKeyCode(), false);
      }

   }
}
