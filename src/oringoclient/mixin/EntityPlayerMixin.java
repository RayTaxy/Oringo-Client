package oringo.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EntityPlayer.class})
public abstract class EntityPlayerMixin extends EntityLivingBaseMixin {
   @Shadow
   protected int flyToggleTimer;
   @Shadow
   public InventoryPlayer inventory;
   @Shadow
   public Container openContainer;
   @Shadow
   public PlayerCapabilities capabilities;
   @Shadow
   public float renderOffsetX;
   @Shadow
   public int experienceLevel;

   @Shadow
   public abstract void sendPlayerAbilities();

   @Shadow
   public abstract float getAIMoveSpeed();

   @Shadow
   public abstract FoodStats getFoodStats();

   @Shadow
   public abstract void addExhaustion(float var1);

   @Shadow
   public abstract ItemStack getHeldItem();

   @Shadow
   public abstract boolean isEntityInsideOpaqueBlock();

   @Shadow
   public void moveEntityWithHeading(float var1, float var2) {
   }

   @Shadow
   protected abstract void entityInit();

   @Shadow
   protected abstract boolean canTriggerWalking();

   @Shadow
   public abstract void destroyCurrentEquippedItem();

   @Shadow
   public abstract void addMovementStat(double var1, double var3, double var5);

   @Shadow
   public abstract boolean isUsingItem();

   @Shadow
   public void onLivingUpdate() {
   }

   @Shadow
   public void onUpdate() {
   }

   @Shadow
   public abstract ItemStack getCurrentEquippedItem();

   @Shadow
   public abstract void attackTargetEntityWithCurrentItem(Entity var1);

   @Shadow
   public abstract void triggerAchievement(StatBase var1);

   @Shadow
   protected abstract String getSwimSound();
}
