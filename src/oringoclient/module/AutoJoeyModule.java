package oringo.module;

import java.util.HashSet;
import java.util.List;
import map.Class361;
import map.Class402;
import map.Class408;
import map.Class426;
import map.Class94;
import map.Class95;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class75;
import oringo.mixin.PlayerControllerMPAccessor;

public class AutoJoeyModule extends Module {
   public final BlockPos field0 = new BlockPos(-106, 73, -102);
   public final HashSet n_ = new HashSet();

   public boolean method0(ItemStack var1) {
      if (this.n_.contains(HitboxesModule.method0(var1))) {
         return false;
      } else {
         List var2 = Class408.method1(var1);
         return var2.isEmpty() ? false : ((String)var2.get(0)).equals("§7Deposit on Joey's plate.");
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (field58.currentScreen == null) {
         IBlockState var2 = field58.theWorld.getBlockState(this.field0);
         if (var2.getBlock() != Blocks.heavy_weighted_pressure_plate) {
            this.n_.clear();
         } else if (field58.thePlayer.getDistanceSq(this.field0) <= 36.0D) {
            int var3 = Class95.method0(this::method0);
            if (var3 != -1) {
               Class361.method0((Class94)(new Class402(var3, this::lambda$onTick$0)));
            }
         }
      }
   }

   public AutoJoeyModule() {
      super("Auto Joey", Category.skyblock, SubCategory.rift, "Automatic Joey deposits");
   }

   public static void method0(int var0) {
      if (var0 != -1) {
         if (((PlayerControllerMPAccessor)Oringo.field9.playerController).getCurrentPlayerItem() != var0) {
            if (var0 > 8 || var0 < 0) {
               (new Exception()).printStackTrace();
               return;
            }

            Oringo.field9.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(var0));
         }

         ((PlayerControllerMPAccessor)Oringo.field9.playerController).setCurrentPlayerItem(var0);
      }
   }

   public void lambda$onTick$0(ItemStack var1) {
      this.n_.add(HitboxesModule.method0(var1));
      Class426.method10();
      if (field58.playerController.onPlayerRightClick(field58.thePlayer, field58.theWorld, var1, this.field0, EnumFacing.DOWN, (new Vec3(this.field0)).addVector(0.5D, 0.0D, 0.5D))) {
         field58.thePlayer.swingItem();
      } else if (var1 != null) {
         method3(new C08PacketPlayerBlockPlacement(var1));
      }

   }
}
