package oringo.mixin;

import map.Class362;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class255;
import oringo.event.Class329;
import oringo.event.Class438;
import oringo.event.Class75;
import oringo.module.MithrilMacroModule2;

@Mixin({PlayerControllerMP.class})
public class PlayerControllerMPMixin {
   @Shadow
   private BlockPos currentBlock;
   @Shadow
   private int currentPlayerItem;
   @Shadow
   private ItemStack currentItemHittingBlock;
   @Shadow
   @Final
   private Minecraft mc;

   @Overwrite
   private boolean isHittingPosition(BlockPos var1) {
      ItemStack var2 = this.mc.thePlayer.getHeldItem();
      boolean var3 = this.currentItemHittingBlock == null && var2 == null;
      if (this.currentItemHittingBlock != null && var2 != null) {
         var3 = var2.getItem() == this.currentItemHittingBlock.getItem() && ItemStack.areItemStackTagsEqual(var2, this.currentItemHittingBlock) && (var2.isItemStackDamageable() || var2.getMetadata() == this.currentItemHittingBlock.getMetadata());
      }

      return var1.equals(this.currentBlock) && (var3 || Class362.field37.method44());
   }

   @Inject(
      method = {"updateController"},
      at = {@At("RETURN")}
   )
   public void method0(CallbackInfo var1) {
      int var2 = MithrilMacroModule2.method6();
      if (var2 != -1) {
         this.mc.thePlayer.inventory.currentItem = var2;
      }

      if (this.mc.thePlayer != null) {
         (new Class75()).method7();
      }

   }

   @Redirect(
      method = {"onPlayerDestroyBlock"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/block/Block;removedByPlayer(Lnet/minecraft/world/World;Lnet/minecraft/util/BlockPos;Lnet/minecraft/entity/player/EntityPlayer;Z)Z"
)
   )
   public boolean onDestroyBlockConfirm(Block var1, World var2, BlockPos var3, EntityPlayer var4, boolean var5) {
      IBlockState var6 = this.mc.theWorld.getBlockState(var3);
      boolean var7 = var1.removedByPlayer(var2, var3, var4, var5);
      if (var7) {
         (new Class329(var3, var6)).method7();
      }

      return var7;
   }

   @Inject(
      method = {"resetBlockRemoving"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(CallbackInfo var1) {
      if ((new Class255()).method7()) {
         var1.cancel();
      }

   }

   @Inject(
      method = {"attackEntity"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;syncCurrentPlayItem()V"
)},
      cancellable = true
   )
   public void method0(EntityPlayer var1, Entity var2, CallbackInfo var3) {
      if ((new Class438(var2)).method7()) {
         var3.cancel();
      }

   }
}
