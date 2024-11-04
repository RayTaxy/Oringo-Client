package oringo.command;

import map.Class361;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class16;
import oringo.event.Class255;
import oringo.event.Class75;
import oringo.mixin.PlayerControllerMPAccessor;
import oringo.module.Category;
import oringo.module.Module;
import oringo.module.SubCategory;

public class LivingMetalMinerModule extends Module {
   public BlockPos ba_ = null;

   public void b_() {
      this.ba_ = null;
   }

   public static float method0(float var0, float var1) {
      if (Oringo.field9.thePlayer.isPotionActive(Potion.jump)) {
         var0 += (float)(Oringo.field9.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * var1;
      }

      return var0;
   }

   @SubscribeEvent
   public void field0(Class255 var1) {
      if (this.ba_ != null && field58.theWorld.getBlockState(this.ba_).getBlock() == Blocks.lapis_ore) {
         var1.method9();
      }

   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field21) {
         if (this.ba_ != null && var1.cB_.distanceSq(this.ba_) <= 3.0D && var1.field2.getBlock() == Blocks.lapis_ore && var1.field0.getBlock() != Blocks.lapis_ore) {
            this.ba_ = var1.cB_;
         } else {
            if (field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && var1.cB_.equals(field58.objectMouseOver.getBlockPos()) && ((PlayerControllerMPAccessor)field58.playerController).getCurBlockDamageMP() >= 1.0F) {
               this.ba_ = var1.cB_;
            }

         }
      }
   }

   public LivingMetalMinerModule() {
      super("Living Metal Miner", Category.skyblock, SubCategory.rift, "Automatically mines the next living metal");
   }

   public void method5() {
      if (this.ba_ != null) {
         if (field58.thePlayer.getDistanceSq((double)this.ba_.getX() + 0.5D, (double)this.ba_.getY() + 0.5D - 1.5D, (double)this.ba_.getZ() + 0.5D) >= 30.25D) {
            this.ba_ = null;
         }
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!Class496.field21) {
         this.ba_ = null;
      }

      this.method5();
      if (this.ba_ != null) {
         if (field58.theWorld.getBlockState(this.ba_).getBlock() == Blocks.lapis_ore) {
            Class361.method0((Class94)(new Class510(this.ba_, EnumFacing.DOWN)));
         }
      }
   }
}
