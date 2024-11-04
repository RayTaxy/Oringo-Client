package oringo.mixin;

import java.util.Map;
import map.Class362;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.Oringo;
import oringo.event.Class344;
import oringo.module.DelaysModule;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.OptimizationsModule;

@Mixin({EntityLivingBase.class})
public abstract class EntityLivingBaseMixin extends EntityMixin {
   @Shadow
   public int swingProgressInt;
   @Shadow
   public float limbSwingAmount;
   @Shadow
   public float prevLimbSwingAmount;
   @Shadow
   protected boolean isJumping;
   @Shadow
   private int jumpTicks;
   @Shadow
   public float jumpMovementFactor;
   @Shadow
   public boolean isSwingInProgress;
   @Shadow
   public float swingProgress;
   @Shadow
   public float limbSwing;
   @Shadow
   public float renderYawOffset;
   @Shadow
   public float moveForward;
   @Shadow
   @Final
   private Map activePotionsMap;
   @Shadow
   public float moveStrafing;

   @Shadow
   protected abstract void entityInit();

   @Inject(
      method = {"onLivingUpdate"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V"
)}
   )
   private void method0(CallbackInfo var1) {
      if (DelaysModule.field2.method44() && this.jumpTicks == 10) {
         this.jumpTicks = (int)DelaysModule.field2.field3.method0();
      }

   }

   @Inject(
      method = {"updatePotionEffects"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(CallbackInfo var1) {
      if (OptimizationsModule.field0.method44() && this.activePotionsMap.isEmpty()) {
         var1.cancel();
      }

   }

   @Shadow
   protected abstract float getJumpUpwardsMotion();

   @Shadow
   protected abstract void updateFallState(double var1, boolean var3, Block var4, BlockPos var5);

   @Shadow
   public abstract ItemStack getHeldItem();

   @Redirect(
      method = {"onEntityUpdate"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/EntityLivingBase;isEntityAlive()Z"
)
   )
   public boolean isAlive(EntityLivingBase var1) {
      return OptimizationsModule.field0.method44() && var1 != Oringo.field9.thePlayer && var1.worldObj.isRemote ? false : var1.isEntityAlive();
   }

   @Shadow
   public abstract void setLastAttacker(Entity var1);

   @Inject(
      method = {"getArmSwingAnimationEnd"},
      at = {@At("RETURN")},
      cancellable = true
   )
   protected void method4(CallbackInfoReturnable var1) {
      if (this == Oringo.field9.thePlayer) {
         int var2 = 6;
         if (Class362.field18.method44()) {
            var2 = (int)(Class362.field18.field8.method0() * (double)FrozenTreasureESPModule.method5().timerSpeed);
         }

         if (Class362.field18.method44() && !Class362.field18.aV_.method1()) {
            var2 = this.isPotionActive(Potion.digSpeed) ? var2 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier()) : (this.isPotionActive(Potion.digSlowdown) ? var2 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : var2);
         }

         var1.setReturnValue(var2);
      }

   }

   @Overwrite
   public void swingItem() {
      ItemStack var1 = this.getHeldItem();
      if (var1 == null || var1.getItem() == null || !var1.getItem().onEntitySwing((EntityLivingBase)this, var1)) {
         if (!this.isSwingInProgress || this.swingProgressInt >= (int)(this == Oringo.field9.thePlayer && Class362.field18.method44() ? (double)this.getArmSwingAnimationEnd() / Class362.field18.field0.method0() : (double)this.getArmSwingAnimationEnd() / 2.0D) || this.swingProgressInt < 0) {
            this.swingProgressInt = -1;
            this.isSwingInProgress = true;
            if (this.worldObj instanceof WorldServer) {
               ((WorldServer)this.worldObj).getEntityTracker().sendToAllTrackingEntity((EntityLivingBase)this, new S0BPacketAnimation((EntityLivingBase)this, 0));
            }
         }

      }
   }

   public int getJumpTicks() {
      return this.jumpTicks;
   }

   @Shadow
   public abstract PotionEffect getActivePotionEffect(Potion var1);

   @Shadow
   public abstract boolean isPotionActive(Potion var1);

   @Shadow
   public abstract IAttributeInstance getEntityAttribute(IAttribute var1);

   @Shadow
   public abstract void setSprinting(boolean var1);

   @Shadow
   public abstract void onUpdate();

   @Shadow
   public abstract float getAIMoveSpeed();

   @Shadow
   public abstract float getHealth();

   @Shadow
   public abstract boolean isPotionActive(int var1);

   @Shadow
   protected abstract void jump();

   @Inject(
      method = {"canBeCollidedWith"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(CallbackInfoReturnable var1) {
      if (this.getHealth() <= 0.0F) {
         var1.setReturnValue(false);
      }

      if ((new Class344((Entity)this)).method7()) {
         var1.setReturnValue(false);
      }

   }

   @Shadow
   protected abstract float updateDistance(float var1, float var2);

   @Shadow
   public abstract boolean isOnLadder();

   public void setJumpTicks(int var1) {
      this.jumpTicks = var1;
   }

   @Shadow
   protected abstract int getArmSwingAnimationEnd();
}
