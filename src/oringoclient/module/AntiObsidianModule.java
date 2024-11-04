package oringo.module;

import map.Class122;
import map.Class361;
import map.Class510;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AntiObsidianModule extends Module {
   public static final BooleanSetting field0 = (BooleanSetting)(new BooleanSetting("Cancel pos packets", true)).method2("Prevents you from taking damage while suffocating");
   public static final Block[] G_;

   public AntiObsidianModule() {
      super("Anti Obsidian", Category.player, SubCategory.player, "Prevents obsidian trap");
      this.method0((Setting[])(new Setting[]{field0}));
   }

   public static void method0(Object var0) {
      Oringo.field9.getNetHandler().getNetworkManager().channel().writeAndFlush(var0);
   }

   static {
      G_ = new Block[]{Blocks.cobblestone, Blocks.obsidian};
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (field0.method1()) {
         if (var1.field0 instanceof C03PacketPlayer && field58.thePlayer.onGround) {
            C03PacketPlayer var2 = (C03PacketPlayer)var1.field0;
            if (!var2.isMoving() || var2.getPositionX() == field58.thePlayer.prevPosX && var2.getPositionZ() == field58.thePlayer.prevPosZ && var2.getPositionY() == field58.thePlayer.prevPosY) {
               BlockPos var3 = new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ);
               if (!field58.theWorld.getBlockState(var3.down()).getBlock().isPassable(field58.theWorld, var3.down()) && Class122.method0(true)) {
                  var1.setCanceled(true);
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      BlockPos var2 = new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ);
      if (Class122.method0(false)) {
         BlockPos var3 = var2.down();
         if (field58.theWorld.getBlockState(var3).getBlock() instanceof BlockAir) {
            return;
         }

         int var4 = AutoJaxRangeModule.method0(var3);
         if (!Class361.method0((Class94)(new Class510(var4, var3, EnumFacing.UP)))) {
            return;
         }

         var1.method0(90.0F);
      }

   }
}
