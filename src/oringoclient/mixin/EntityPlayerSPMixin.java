package oringo.mixin;

import java.util.Iterator;
import java.util.List;
import map.Class362;
import map.Class496;
import map.Class503;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraft.network.play.client.C0BPacketEntityAction.Action;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.command.MessageCommand;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.event.Class419;
import oringo.event.Class493;
import oringo.event.Class523;
import oringo.event.Class525;
import oringo.event.Class537;
import oringo.module.AutoReadyModule;
import oringo.module.AutoReconnectModule;
import oringo.module.Dadudze2Module;
import oringo.module.DragonHitboxesModule;
import oringo.module.EagleModule;
import oringo.module.KillAuraModule;
import oringo.module.LividFinderModule;
import oringo.module.NoDebuffModule;
import oringo.module.NoJumpBoostModule;
import oringo.module.PacketLoggerModule;

@Mixin(
   value = {EntityPlayerSP.class},
   priority = 1
)
public abstract class EntityPlayerSPMixin extends AbstractClientPlayerMixin {
   @Shadow
   protected int sprintToggleTimer;
   @Shadow
   public MovementInput movementInput;
   @Shadow
   private double lastReportedPosY;
   @Shadow
   public int sprintingTicksLeft;
   @Shadow
   public float renderArmPitch;
   @Shadow
   private float lastReportedPitch;
   @Shadow
   private boolean serverSprintState;
   @Shadow
   private boolean serverSneakState;
   @Shadow
   private int positionUpdateTicks;
   @Shadow
   protected Minecraft mc;
   @Shadow
   private float horseJumpPower;
   @Shadow
   private double lastReportedPosZ;
   @Shadow
   @Final
   public NetHandlerPlayClient sendQueue;
   @Shadow
   public float prevTimeInPortal;
   @Shadow
   private float lastReportedYaw;
   @Shadow
   private double lastReportedPosX;
   @Shadow
   public float prevRenderArmYaw;
   @Shadow
   private int horseJumpPowerCounter;
   @Shadow
   public float prevRenderArmPitch;
   @Shadow
   public float timeInPortal;
   @Shadow
   public float renderArmYaw;

   @Shadow
   public abstract void addStat(StatBase var1, int var2);

   public void moveFlying(float var1, float var2, float var3) {
      Class419 var4 = new Class419(var2, var1, var3, this.rotationYaw);
      if (!var4.method7()) {
         var1 = var4.G_();
         var2 = var4.method2();
         var3 = var4.method1();
         float var5 = var1 * var1 + var2 * var2;
         if (var5 >= 1.0E-4F) {
            var5 = MathHelper.sqrt_float(var5);
            if (var5 < 1.0F) {
               var5 = 1.0F;
            }

            var5 = var3 / var5;
            var1 *= var5;
            var2 *= var5;
            float var6 = var4.method3();
            float var7 = MathHelper.sin(var6 * 3.1415927F / 180.0F);
            float var8 = MathHelper.cos(var6 * 3.1415927F / 180.0F);
            this.motionX += (double)(var1 * var8 - var2 * var7);
            this.motionZ += (double)(var2 * var8 + var1 * var7);
         }

      }
   }

   public void attackTargetEntityWithCurrentItem(Entity var1) {
      if (ForgeHooks.onPlayerAttackTarget((EntityPlayer)this, var1) && var1.canAttackWithItem() && !var1.hitByEntity((EntityPlayer)this)) {
         float var2 = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
         byte var3 = 0;
         float var4;
         if (var1 instanceof EntityLivingBase) {
            var4 = EnchantmentHelper.getModifierForCreature(this.getHeldItem(), ((EntityLivingBase)var1).getCreatureAttribute());
         } else {
            var4 = EnchantmentHelper.getModifierForCreature(this.getHeldItem(), EnumCreatureAttribute.UNDEFINED);
         }

         int var18 = var3 + EnchantmentHelper.getKnockbackModifier((EntityPlayer)this);
         if (this.isSprinting()) {
            ++var18;
         }

         if (var2 > 0.0F || var4 > 0.0F) {
            boolean var5 = this.fallDistance > 0.0F && !this.onGround && !this.isOnLadder() && !this.isInWater() && !this.isPotionActive(Potion.blindness) && this.ridingEntity == null && var1 instanceof EntityLivingBase;
            if (var5 && var2 > 0.0F) {
               var2 *= 1.5F;
            }

            var2 += var4;
            boolean var6 = false;
            int var7 = EnchantmentHelper.getFireAspectModifier((EntityPlayer)this);
            if (var1 instanceof EntityLivingBase && var7 > 0 && !var1.isBurning()) {
               var6 = true;
               var1.setFire(1);
            }

            double var8 = var1.motionX;
            double var10 = var1.motionY;
            double var12 = var1.motionZ;
            boolean var14 = var1.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)this), var2);
            if (var14) {
               if (var18 > 0) {
                  var1.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * (float)var18 * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * (float)var18 * 0.5F));
                  if ((!Class362.field17.method44() || !Class362.field17.field2.method1()) && (!Class362.field1.method44() || !Class362.field1.field2.method1())) {
                     this.motionX *= 0.6D;
                     this.motionZ *= 0.6D;
                     this.setSprinting(false);
                  }
               }

               if (var1 instanceof EntityPlayerMP && var1.velocityChanged) {
                  ((EntityPlayerMP)var1).playerNetServerHandler.sendPacket(new S12PacketEntityVelocity(var1));
                  var1.velocityChanged = false;
                  var1.motionX = var8;
                  var1.motionY = var10;
                  var1.motionZ = var12;
               }

               if (var5) {
                  this.onCriticalHit(var1);
               }

               if (var4 > 0.0F) {
                  this.onEnchantmentCritical(var1);
               }

               if (var2 >= 18.0F) {
                  this.triggerAchievement(AchievementList.overkill);
               }

               this.setLastAttacker(var1);
               if (var1 instanceof EntityLivingBase) {
                  EnchantmentHelper.applyThornEnchantments((EntityLivingBase)var1, (EntityPlayer)this);
               }

               EnchantmentHelper.applyArthropodEnchantments((EntityPlayer)this, var1);
               ItemStack var15 = this.getCurrentEquippedItem();
               Object var16 = var1;
               if (var1 instanceof EntityDragonPart) {
                  IEntityMultiPart var17 = ((EntityDragonPart)var1).entityDragonObj;
                  if (var17 instanceof EntityLivingBase) {
                     var16 = (EntityLivingBase)var17;
                  }
               }

               if (var15 != null && var16 instanceof EntityLivingBase) {
                  var15.hitEntity((EntityLivingBase)var16, (EntityPlayer)this);
                  if (var15.stackSize <= 0) {
                     this.destroyCurrentEquippedItem();
                  }
               }

               if (var1 instanceof EntityLivingBase) {
                  this.addStat(StatList.damageDealtStat, Math.round(var2 * 10.0F));
                  if (var7 > 0) {
                     var1.setFire(var7 * 4);
                  }
               }

               this.addExhaustion(0.3F);
            } else if (var6) {
               var1.extinguish();
            }
         }
      }

   }

   @Shadow
   public abstract void mountEntity(Entity var1);

   @Shadow
   public abstract boolean isServerWorld();

   @Shadow
   public abstract void onCriticalHit(Entity var1);

   @Overwrite
   public void onUpdateWalkingPlayer() {
      Class210.Class1 var1 = new Class210.Class1(this.posX, this.getEntityBoundingBox().minY, this.posZ, this.rotationYaw, this.rotationPitch, this.onGround, this.isSprinting(), this.isSneaking());
      if (!var1.method7()) {
         var1.bF_ = MathHelper.clamp_float(var1.bF_, -90.0F, 90.0F);
         boolean var2 = var1.field8;
         if (var2 != this.serverSprintState) {
            if (var2) {
               this.sendQueue.addToSendQueue(new C0BPacketEntityAction((EntityPlayerSP)this, Action.START_SPRINTING));
            } else {
               this.sendQueue.addToSendQueue(new C0BPacketEntityAction((EntityPlayerSP)this, Action.STOP_SPRINTING));
            }

            this.serverSprintState = var2;
         }

         boolean var3 = var1.field1;
         if (var3 != this.serverSneakState) {
            if (var3) {
               this.sendQueue.addToSendQueue(new C0BPacketEntityAction((EntityPlayerSP)this, Action.START_SNEAKING));
            } else {
               this.sendQueue.addToSendQueue(new C0BPacketEntityAction((EntityPlayerSP)this, Action.STOP_SNEAKING));
            }

            this.serverSneakState = var3;
         }

         if (this.isCurrentViewEntity()) {
            double var4 = var1.j_ - this.lastReportedPosX;
            double var6 = var1.i_ - this.lastReportedPosY;
            double var8 = var1.field4 - this.lastReportedPosZ;
            float var10 = var1.t_;
            float var11 = var1.bF_;
            double var12 = (double)(var10 - this.lastReportedYaw);
            double var14 = (double)(var11 - this.lastReportedPitch);
            boolean var16 = var4 * var4 + var6 * var6 + var8 * var8 > 9.0E-4D || this.positionUpdateTicks >= 20 || var1.field0;
            boolean var17 = var12 != 0.0D || var14 != 0.0D || var1.field6;
            if (this.ridingEntity == null) {
               if (var16 && var17) {
                  this.sendQueue.addToSendQueue(new C06PacketPlayerPosLook(var1.j_, var1.i_, var1.field4, var10, var11, var1.bG_));
               } else if (var16) {
                  this.sendQueue.addToSendQueue(new C04PacketPlayerPosition(var1.j_, var1.i_, var1.field4, var1.bG_));
               } else if (var17) {
                  this.sendQueue.addToSendQueue(new C05PacketPlayerLook(var10, var11, var1.bG_));
               } else {
                  this.sendQueue.addToSendQueue(new C03PacketPlayer(var1.bG_));
               }
            } else {
               this.sendQueue.addToSendQueue(new C06PacketPlayerPosLook(this.motionX, -999.0D, this.motionZ, var10, var11, var1.bG_));
               var16 = false;
            }

            ++this.positionUpdateTicks;
            if (var16) {
               this.lastReportedPosX = var1.j_;
               this.lastReportedPosY = var1.i_;
               this.lastReportedPosZ = var1.field4;
               this.positionUpdateTicks = 0;
            }

            Class503.field1 = this.lastReportedPitch;
            Class503.field0 = this.lastReportedYaw;
            if (var17) {
               this.lastReportedYaw = var10;
               this.lastReportedPitch = var11;
            }
         }

         (new Class210.Class0(var1)).method7();
      }
   }

   @Shadow
   protected abstract void sendHorseJump();

   @Inject(
      method = {"addChatMessage"},
      at = {@At("RETURN")}
   )
   public void method0(IChatComponent var1, CallbackInfo var2) {
      Dadudze2Module.method0(var1);
   }

   protected float updateDistance(float var1, float var2) {
      if (this.swingProgress > 0.0F && LividFinderModule.method5().method44()) {
         var1 = this.lastReportedYaw;
      }

      float var3 = MathHelper.wrapAngleTo180_float(var1 - this.renderYawOffset);
      this.renderYawOffset += var3 * 0.3F;
      float var4 = MathHelper.wrapAngleTo180_float((LividFinderModule.method5().method44() ? this.lastReportedYaw : this.rotationYaw) - this.renderYawOffset);
      boolean var5 = var4 < -90.0F || var4 >= 90.0F;
      if (var4 < -75.0F) {
         var4 = -75.0F;
      }

      if (var4 >= 75.0F) {
         var4 = 75.0F;
      }

      this.renderYawOffset = (LividFinderModule.method5().method44() ? this.lastReportedYaw : this.rotationYaw) - var4;
      if (var4 * var4 > 2500.0F) {
         this.renderYawOffset += var4 * 0.2F;
      }

      if (var5) {
         var2 *= -1.0F;
      }

      return var2;
   }

   @Shadow
   public abstract boolean isSneaking();

   @Shadow
   public abstract void sendPlayerAbilities();

   public void moveEntity(double var1, double var3, double var5) {
      Class217 var7 = new Class217(var1, var3, var5);
      if (!var7.method7()) {
         var1 = var7.w_();
         var3 = var7.method6();
         var5 = var7.method2();
         if (this.noClip) {
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, var3, var5));
            this.doResetPositionToBB();
         } else {
            this.worldObj.theProfiler.startSection("move");
            double var8 = this.posX;
            double var10 = this.posY;
            double var12 = this.posZ;
            if (this.isInWeb) {
               this.isInWeb = false;
               var1 *= 0.25D;
               var3 *= 0.05000000074505806D;
               var5 *= 0.25D;
               this.motionX = 0.0D;
               this.motionY = 0.0D;
               this.motionZ = 0.0D;
            }

            double var14 = var1;
            double var16 = var3;
            double var18 = var5;
            boolean var20 = this.onGround && this.isSneaking() || this.onGround && ((EagleModule)Class362.method0(EagleModule.class)).method44() && (this.mc.thePlayer.movementInput.moveForward < 0.1F || !EagleModule.field2.method1()) && (this.mc.thePlayer.rotationPitch > 60.0F || !EagleModule.field0.method1()) && EagleModule.w_.method0(1);
            if (var20) {
               double var21;
               for(var21 = 0.05D; var1 != 0.0D && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().offset(var1, -1.0D, 0.0D)).isEmpty(); var14 = var1) {
                  if (var1 < var21 && var1 >= -var21) {
                     var1 = 0.0D;
                  } else if (var1 > 0.0D) {
                     var1 -= var21;
                  } else {
                     var1 += var21;
                  }
               }

               for(; var5 != 0.0D && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().offset(0.0D, -1.0D, var5)).isEmpty(); var18 = var5) {
                  if (var5 < var21 && var5 >= -var21) {
                     var5 = 0.0D;
                  } else if (var5 > 0.0D) {
                     var5 -= var21;
                  } else {
                     var5 += var21;
                  }
               }

               for(; var1 != 0.0D && var5 != 0.0D && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().offset(var1, -1.0D, var5)).isEmpty(); var18 = var5) {
                  if (var1 < var21 && var1 >= -var21) {
                     var1 = 0.0D;
                  } else if (var1 > 0.0D) {
                     var1 -= var21;
                  } else {
                     var1 += var21;
                  }

                  var14 = var1;
                  if (var5 < var21 && var5 >= -var21) {
                     var5 = 0.0D;
                  } else if (var5 > 0.0D) {
                     var5 -= var21;
                  } else {
                     var5 += var21;
                  }
               }
            }

            List var55 = this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().addCoord(var1, var3, var5));
            AxisAlignedBB var22 = this.getEntityBoundingBox();

            AxisAlignedBB var24;
            for(Iterator var23 = var55.iterator(); var23.hasNext(); var3 = var24.calculateYOffset(this.getEntityBoundingBox(), var3)) {
               var24 = (AxisAlignedBB)var23.next();
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, var3, 0.0D));
            boolean var56 = this.onGround || var16 != var3 && var16 < 0.0D;

            AxisAlignedBB var25;
            Iterator var57;
            for(var57 = var55.iterator(); var57.hasNext(); var1 = var25.calculateXOffset(this.getEntityBoundingBox(), var1)) {
               var25 = (AxisAlignedBB)var57.next();
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(var1, 0.0D, 0.0D));

            for(var57 = var55.iterator(); var57.hasNext(); var5 = var25.calculateZOffset(this.getEntityBoundingBox(), var5)) {
               var25 = (AxisAlignedBB)var57.next();
            }

            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, 0.0D, var5));
            if (this.stepHeight > 0.0F && var56 && (var14 != var1 || var18 != var5)) {
               double var58 = var1;
               double var26 = var3;
               double var28 = var5;
               AxisAlignedBB var30 = this.getEntityBoundingBox();
               this.setEntityBoundingBox(var22);
               Class523.Class1 var31 = new Class523.Class1((double)this.stepHeight);
               var31.method7();
               var3 = var31.method0();
               List var32 = this.worldObj.getCollidingBoundingBoxes((Entity)this, this.getEntityBoundingBox().addCoord(var14, var3, var18));
               AxisAlignedBB var33 = this.getEntityBoundingBox();
               AxisAlignedBB var34 = var33.addCoord(var14, 0.0D, var18);
               double var35 = var3;

               AxisAlignedBB var38;
               for(Iterator var37 = var32.iterator(); var37.hasNext(); var35 = var38.calculateYOffset(var34, var35)) {
                  var38 = (AxisAlignedBB)var37.next();
               }

               var33 = var33.offset(0.0D, var35, 0.0D);
               double var70 = var14;

               AxisAlignedBB var40;
               for(Iterator var39 = var32.iterator(); var39.hasNext(); var70 = var40.calculateXOffset(var33, var70)) {
                  var40 = (AxisAlignedBB)var39.next();
               }

               var33 = var33.offset(var70, 0.0D, 0.0D);
               double var71 = var18;

               AxisAlignedBB var42;
               for(Iterator var41 = var32.iterator(); var41.hasNext(); var71 = var42.calculateZOffset(var33, var71)) {
                  var42 = (AxisAlignedBB)var41.next();
               }

               var33 = var33.offset(0.0D, 0.0D, var71);
               AxisAlignedBB var72 = this.getEntityBoundingBox();
               double var73 = var3;

               AxisAlignedBB var45;
               for(Iterator var44 = var32.iterator(); var44.hasNext(); var73 = var45.calculateYOffset(var72, var73)) {
                  var45 = (AxisAlignedBB)var44.next();
               }

               var72 = var72.offset(0.0D, var73, 0.0D);
               double var74 = var14;

               AxisAlignedBB var47;
               for(Iterator var46 = var32.iterator(); var46.hasNext(); var74 = var47.calculateXOffset(var72, var74)) {
                  var47 = (AxisAlignedBB)var46.next();
               }

               var72 = var72.offset(var74, 0.0D, 0.0D);
               double var75 = var18;

               AxisAlignedBB var49;
               for(Iterator var48 = var32.iterator(); var48.hasNext(); var75 = var49.calculateZOffset(var72, var75)) {
                  var49 = (AxisAlignedBB)var48.next();
               }

               var72 = var72.offset(0.0D, 0.0D, var75);
               double var76 = var70 * var70 + var71 * var71;
               double var50 = var74 * var74 + var75 * var75;
               if (var76 > var50) {
                  var1 = var70;
                  var5 = var71;
                  var3 = -var35;
                  this.setEntityBoundingBox(var33);
               } else {
                  var1 = var74;
                  var5 = var75;
                  var3 = -var73;
                  this.setEntityBoundingBox(var72);
               }

               AxisAlignedBB var53;
               for(Iterator var52 = var32.iterator(); var52.hasNext(); var3 = var53.calculateYOffset(this.getEntityBoundingBox(), var3)) {
                  var53 = (AxisAlignedBB)var52.next();
               }

               this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0D, var3, 0.0D));
               if (var58 * var58 + var28 * var28 >= var1 * var1 + var5 * var5) {
                  var1 = var58;
                  var3 = var26;
                  var5 = var28;
                  this.setEntityBoundingBox(var30);
               } else {
                  (new Class523.Class0(1.0D + var3)).method7();
               }
            }

            this.worldObj.theProfiler.endSection();
            this.worldObj.theProfiler.startSection("rest");
            this.doResetPositionToBB();
            this.isCollidedHorizontally = var14 != var1 || var18 != var5;
            this.isCollidedVertically = var16 != var3;
            this.onGround = this.isCollidedVertically && var16 < 0.0D;
            this.isCollided = this.isCollidedHorizontally || this.isCollidedVertically;
            int var59 = MathHelper.floor_double(this.posX);
            int var60 = MathHelper.floor_double(this.posY - 0.20000000298023224D);
            int var61 = MathHelper.floor_double(this.posZ);
            BlockPos var27 = new BlockPos(var59, var60, var61);
            Block var62 = this.worldObj.getBlockState(var27).getBlock();
            if (var62.getMaterial() == Material.air) {
               Block var29 = this.worldObj.getBlockState(var27.down()).getBlock();
               if (var29 instanceof BlockFence || var29 instanceof BlockWall || var29 instanceof BlockFenceGate) {
                  var62 = var29;
                  var27 = var27.down();
               }
            }

            this.updateFallState(var3, this.onGround, var62, var27);
            if (var14 != var1) {
               this.motionX = 0.0D;
            }

            if (var18 != var5) {
               this.motionZ = 0.0D;
            }

            if (var16 != var3) {
               var62.onLanded(this.worldObj, (Entity)this);
            }

            if (this.canTriggerWalking() && !var20 && this.ridingEntity == null) {
               double var63 = this.posX - var8;
               double var66 = this.posY - var10;
               double var68 = this.posZ - var12;
               if (var62 != Blocks.ladder) {
                  var66 = 0.0D;
               }

               if (var62 != null && this.onGround) {
                  var62.onEntityCollidedWithBlock(this.worldObj, var27, (Entity)this);
               }

               this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)MathHelper.sqrt_double(var63 * var63 + var68 * var68) * 0.6D);
               this.distanceWalkedOnStepModified = (float)((double)this.distanceWalkedOnStepModified + (double)MathHelper.sqrt_double(var63 * var63 + var66 * var66 + var68 * var68) * 0.6D);
               if (this.distanceWalkedOnStepModified > (float)this.getNextStepDistance() && var62.getMaterial() != Material.air) {
                  this.setNextStepDistance((int)this.distanceWalkedOnStepModified + 1);
                  if (this.isInWater()) {
                     float var69 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.35F;
                     if (var69 > 1.0F) {
                        var69 = 1.0F;
                     }

                     this.playSound(this.getSwimSound(), var69, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                  }

                  this.playStepSound(var27, var62);
               }
            }

            try {
               this.doBlockCollisions();
            } catch (Throwable var54) {
               CrashReport var65 = CrashReport.makeCrashReport(var54, "Checking entity block collision");
               CrashReportCategory var67 = var65.makeCategory("Entity being checked for collision");
               this.addEntityCrashInfo(var67);
               throw new ReportedException(var65);
            }

            boolean var64 = this.isWet();
            if (this.worldObj.isFlammableWithin(this.getEntityBoundingBox().contract(0.001D, 0.001D, 0.001D))) {
               this.dealFireDamage(1);
               if (!var64) {
                  this.plusPlusFire();
                  if (this.getFire() == 0) {
                     this.setFire(8);
                  }
               }
            } else if (this.getFire() <= 0) {
               this.SetFire(-this.fireResistance);
            }

            if (var64 && this.getFire() > 0) {
               this.playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
               this.SetFire(-this.fireResistance);
            }

            this.worldObj.theProfiler.endSection();
         }

      }
   }

   public boolean isEntityInsideOpaqueBlock() {
      return false;
   }

   @Redirect(
      method = {"onLivingUpdate"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"
)
   )
   public void closeScreen(Minecraft var1, GuiScreen var2) {
   }

   @Shadow
   public abstract void setSprinting(boolean var1);

   public void jump() {
      this.motionY = (double)this.getJumpUpwardsMotion();
      NoJumpBoostModule var1 = (NoJumpBoostModule)Class362.method0(NoJumpBoostModule.class);
      if ((!var1.method44() || var1.field0.method1() && !Class496.field5) && this.isPotionActive(Potion.jump.id)) {
         this.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
      }

      if (this.isSprinting() && AutoReconnectModule.method1()) {
         float var2 = (Class362.field17.method44() && Class362.field17.field1.method1() ? PacketLoggerModule.method5() : (Class362.field25.method44() && KillAuraModule.field24 != null && KillAuraModule.field12.method1() ? MessageCommand.method0((Entity)KillAuraModule.field24).method5() : this.rotationYaw)) * 0.017453292F;
         this.motionX -= (double)(MathHelper.sin(var2) * 0.2F);
         this.motionZ += (double)(MathHelper.cos(var2) * 0.2F);
      }

      this.isAirBorne = true;
      ForgeHooks.onLivingJump((EntityPlayerSP)this);
      this.triggerAchievement(StatList.jumpStat);
      if (this.isSprinting()) {
         this.addExhaustion(0.8F);
      } else {
         this.addExhaustion(0.2F);
      }

   }

   @Inject(
      method = {"pushOutOfBlocks"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(double var1, double var3, double var5, CallbackInfoReturnable var7) {
      var7.setReturnValue(false);
   }

   @Overwrite
   public void onLivingUpdate() {
      if (this.sprintingTicksLeft > 0) {
         --this.sprintingTicksLeft;
         if (this.sprintingTicksLeft == 0) {
            this.setSprinting(false);
         }
      }

      if (this.sprintToggleTimer > 0) {
         --this.sprintToggleTimer;
      }

      this.prevTimeInPortal = this.timeInPortal;
      if (this.inPortal) {
         if (this.mc.currentScreen != null && !this.mc.currentScreen.doesGuiPauseGame()) {
            this.mc.displayGuiScreen((GuiScreen)null);
         }

         if (this.timeInPortal == 0.0F) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("portal.trigger"), this.rand.nextFloat() * 0.4F + 0.8F));
         }

         this.timeInPortal += 0.0125F;
         if (this.timeInPortal >= 1.0F) {
            this.timeInPortal = 1.0F;
         }

         this.inPortal = false;
      } else if (this.isPotionActive(Potion.confusion) && this.getActivePotionEffect(Potion.confusion).getDuration() > 60) {
         if (!Class362.field36.method44() || !NoDebuffModule.bj_.method1()) {
            this.timeInPortal += 0.006666667F;
            if (this.timeInPortal > 1.0F) {
               this.timeInPortal = 1.0F;
            }
         }
      } else {
         if (this.timeInPortal > 0.0F) {
            this.timeInPortal -= 0.05F;
         }

         if (this.timeInPortal < 0.0F) {
            this.timeInPortal = 0.0F;
         }
      }

      if (this.timeUntilPortal > 0) {
         --this.timeUntilPortal;
      }

      boolean var1 = this.movementInput.jump;
      boolean var2 = this.movementInput.sneak;
      float var3 = 0.8F;
      boolean var4 = this.movementInput.moveForward >= var3;
      this.movementInput.updatePlayerMoveState();
      if (this.isUsingItem() && !this.isRiding()) {
         MovementInput var10000;
         if (Class362.field1.method44()) {
            EnumAction var5 = this.getHeldItem().getItem().getItemUseAction(this.getHeldItem());
            if (var5 == EnumAction.BLOCK) {
               var10000 = this.movementInput;
               var10000.moveForward = (float)((double)var10000.moveForward * Class362.field1.field4.method0());
               var10000 = this.movementInput;
               var10000.moveStrafe = (float)((double)var10000.moveStrafe * Class362.field1.field4.method0());
            } else if (var5 == EnumAction.BOW) {
               var10000 = this.movementInput;
               var10000.moveForward = (float)((double)var10000.moveForward * Class362.field1.ao_.method0());
               var10000 = this.movementInput;
               var10000.moveStrafe = (float)((double)var10000.moveStrafe * Class362.field1.ao_.method0());
            } else if (var5 != EnumAction.NONE) {
               var10000 = this.movementInput;
               var10000.moveForward = (float)((double)var10000.moveForward * Class362.field1.field5.method0());
               var10000 = this.movementInput;
               var10000.moveStrafe = (float)((double)var10000.moveStrafe * Class362.field1.field5.method0());
            }
         } else {
            var10000 = this.movementInput;
            var10000.moveStrafe *= 0.2F;
            var10000 = this.movementInput;
            var10000.moveForward *= 0.2F;
            this.sprintToggleTimer = 0;
         }
      }

      this.pushOutOfBlocks(this.posX - (double)this.width * 0.35D, this.getEntityBoundingBox().minY + 0.5D, this.posZ + (double)this.width * 0.35D);
      this.pushOutOfBlocks(this.posX - (double)this.width * 0.35D, this.getEntityBoundingBox().minY + 0.5D, this.posZ - (double)this.width * 0.35D);
      this.pushOutOfBlocks(this.posX + (double)this.width * 0.35D, this.getEntityBoundingBox().minY + 0.5D, this.posZ - (double)this.width * 0.35D);
      this.pushOutOfBlocks(this.posX + (double)this.width * 0.35D, this.getEntityBoundingBox().minY + 0.5D, this.posZ + (double)this.width * 0.35D);
      boolean var6 = (float)this.getFoodStats().getFoodLevel() > 6.0F || this.capabilities.allowFlying;
      if ((!var6 || !Class362.field17.field1.method1() && this.movementInput.moveForward < var3 || !Class362.field17.method44()) && !Class362.field20.method44()) {
         if (this.onGround && !var2 && !var4 && this.movementInput.moveForward >= var3 && !this.isSprinting() && var6 && (!this.isUsingItem() || Class362.field1.method44() && Class362.field1.field2.method1()) && !this.isPotionActive(Potion.blindness)) {
            if (this.sprintToggleTimer <= 0 && !this.mc.gameSettings.keyBindSprint.isKeyDown()) {
               this.sprintToggleTimer = 7;
            } else {
               this.setSprinting(true);
            }
         }

         if (!this.isSprinting() && this.movementInput.moveForward >= var3 && var6 && (!this.isUsingItem() || Class362.field1.method44() && Class362.field1.field2.method1()) && !this.isPotionActive(Potion.blindness) && this.mc.gameSettings.keyBindSprint.isKeyDown()) {
            this.setSprinting(true);
         }

         if (this.isSprinting() && (this.movementInput.moveForward < var3 || this.isCollidedHorizontally || !var6)) {
            this.setSprinting(false);
         }
      } else {
         this.setSprinting(true);
      }

      if (this.capabilities.allowFlying) {
         if (this.mc.playerController.isSpectatorMode()) {
            if (!this.capabilities.isFlying) {
               this.capabilities.isFlying = true;
               this.sendPlayerAbilities();
            }
         } else if (!var1 && this.movementInput.jump) {
            if (this.flyToggleTimer == 0) {
               this.flyToggleTimer = 7;
            } else {
               this.capabilities.isFlying = !this.capabilities.isFlying;
               this.sendPlayerAbilities();
               this.flyToggleTimer = 0;
            }
         }
      }

      if (this.capabilities.isFlying && this.isCurrentViewEntity()) {
         if (this.movementInput.sneak) {
            this.motionY -= (double)(this.capabilities.getFlySpeed() * 3.0F);
         }

         if (this.movementInput.jump) {
            this.motionY += (double)(this.capabilities.getFlySpeed() * 3.0F);
         }
      }

      if (this.isRidingHorse()) {
         if (this.horseJumpPowerCounter < 0) {
            ++this.horseJumpPowerCounter;
            if (this.horseJumpPowerCounter == 0) {
               this.horseJumpPower = 0.0F;
            }
         }

         if (var1 && !this.movementInput.jump) {
            this.horseJumpPowerCounter = -10;
            this.sendHorseJump();
         } else if (!var1 && this.movementInput.jump) {
            this.horseJumpPowerCounter = 0;
            this.horseJumpPower = 0.0F;
         } else if (var1) {
            ++this.horseJumpPowerCounter;
            if (this.horseJumpPowerCounter < 10) {
               this.horseJumpPower = (float)this.horseJumpPowerCounter * 0.1F;
            } else {
               this.horseJumpPower = 0.8F + 2.0F / (float)(this.horseJumpPowerCounter - 9) * 0.1F;
            }
         }
      } else {
         this.horseJumpPower = 0.0F;
      }

      if (Class362.field43.method44()) {
         this.noClip = true;
      }

      super.onLivingUpdate();
      if (this.onGround && this.capabilities.isFlying && !this.mc.playerController.isSpectatorMode()) {
         this.capabilities.isFlying = false;
         this.sendPlayerAbilities();
      }

   }

   @Inject(
      method = {"sendChatMessage"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(String var1, CallbackInfo var2) {
      if (AutoReadyModule.c_(var1)) {
         var2.cancel();
      }

   }

   public void moveEntityWithHeading(float var1, float var2) {
      Class493 var3 = new Class493(this.onGround, var2, var1);
      if (!var3.method7()) {
         var2 = var3.de_;
         var1 = var3.field0;
         double var4 = this.posX;
         double var6 = this.posY;
         double var8 = this.posZ;
         if (this.capabilities.isFlying && this.ridingEntity == null) {
            double var10 = this.motionY;
            float var12 = this.jumpMovementFactor;
            this.jumpMovementFactor = this.capabilities.getFlySpeed() * (float)(this.isSprinting() ? 2 : 1);
            super.moveEntityWithHeading(var1, var2);
            this.motionY = var10 * 0.6D;
            this.jumpMovementFactor = var12;
         } else {
            this.superMoveEntityWithHeading(var1, var2, var3.method0(), var3.method1());
         }

         this.addMovementStat(this.posX - var4, this.posY - var6, this.posZ - var8);
      }
   }

   @Shadow
   public abstract boolean attackEntityFrom(DamageSource var1, float var2);

   @Shadow
   protected abstract boolean isCurrentViewEntity();

   @Inject(
      method = {"updateEntityActionState"},
      at = {@At("RETURN")}
   )
   public void method0(CallbackInfo var1) {
      if (Class362.field20.method44() && !DragonHitboxesModule.method6() && AutoReconnectModule.method1()) {
         this.isJumping = false;
      }

   }

   public void superMoveEntityWithHeading(float var1, float var2, boolean var3, float var4) {
      double var5;
      float var9;
      if (this.isServerWorld()) {
         float var7;
         float var8;
         if (!this.isInWater() || (Entity)this instanceof EntityPlayer && this.capabilities.isFlying) {
            if (this.isInLava() && (!((Entity)this instanceof EntityPlayer) || !this.capabilities.isFlying)) {
               var5 = this.posY;
               this.moveFlying(var1, var2, 0.02F);
               this.moveEntity(this.motionX, this.motionY, this.motionZ);
               this.motionX *= 0.5D;
               this.motionY *= 0.5D;
               this.motionZ *= 0.5D;
               this.motionY -= 0.02D;
               if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + var5, this.motionZ)) {
                  this.motionY = 0.30000001192092896D;
               }
            } else {
               float var10 = 0.91F;
               if (var3) {
                  var10 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * 0.91F;
               }

               float var6 = 0.16277136F / (var10 * var10 * var10);
               if (var3) {
                  var7 = this.getAIMoveSpeed() * var6;
               } else {
                  var7 = this.jumpMovementFactor;
               }

               this.moveFlying(var1, var2, var7);
               var10 = 0.91F;
               if (var3) {
                  var10 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(this.posZ))).getBlock().slipperiness * var4;
               }

               if (this.isOnLadder()) {
                  var8 = 0.15F;
                  this.motionX = MathHelper.clamp_double(this.motionX, (double)(-var8), (double)var8);
                  this.motionZ = MathHelper.clamp_double(this.motionZ, (double)(-var8), (double)var8);
                  this.fallDistance = 0.0F;
                  if (this.motionY < -0.15D) {
                     this.motionY = -0.15D;
                  }

                  boolean var12 = this.isSneaking();
                  if (var12 && this.motionY < 0.0D) {
                     this.motionY = 0.0D;
                  }
               }

               this.moveEntity(this.motionX, this.motionY, this.motionZ);
               if (this.isCollidedHorizontally && this.isOnLadder()) {
                  this.motionY = 0.2D;
               }

               if (!this.worldObj.isRemote || this.worldObj.isBlockLoaded(new BlockPos((int)this.posX, 0, (int)this.posZ)) && this.worldObj.getChunkFromBlockCoords(new BlockPos((int)this.posX, 0, (int)this.posZ)).isLoaded()) {
                  this.motionY -= 0.08D;
               } else {
                  this.motionY = 0.0D;
               }

               this.motionY *= 0.9800000190734863D;
               this.motionX *= (double)var10;
               this.motionZ *= (double)var10;
            }
         } else {
            var5 = this.posY;
            var7 = 0.8F;
            var8 = 0.02F;
            var9 = (float)EnchantmentHelper.getDepthStriderModifier((Entity)this);
            if (var9 > 3.0F) {
               var9 = 3.0F;
            }

            if (!this.onGround) {
               var9 *= 0.5F;
            }

            if (var9 > 0.0F) {
               var7 += (0.54600006F - var7) * var9 / 3.0F;
               var8 += (this.getAIMoveSpeed() - var8) * var9 / 3.0F;
            }

            this.moveFlying(var1, var2, var8);
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)var7;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= (double)var7;
            this.motionY -= 0.02D;
            if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + var5, this.motionZ)) {
               this.motionY = 0.30000001192092896D;
            }
         }
      }

      this.prevLimbSwingAmount = this.limbSwingAmount;
      var5 = this.posX - this.prevPosX;
      double var11 = this.posZ - this.prevPosZ;
      var9 = MathHelper.sqrt_double(var5 * var5 + var11 * var11) * 4.0F;
      if (var9 > 1.0F) {
         var9 = 1.0F;
      }

      this.limbSwingAmount += (var9 - this.limbSwingAmount) * 0.4F;
      this.limbSwing += this.limbSwingAmount;
   }

   @Overwrite
   public void onUpdate() {
      Class525 var1 = new Class525();
      if (!var1.method7()) {
         if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, 0.0D, this.posZ)) || var1.field0) {
            super.onUpdate();
            if (this.isRiding()) {
               Class537.Class1 var2 = new Class537.Class1(this.rotationYaw, this.rotationPitch, this.onGround, this.moveForward, this.moveStrafing, this.movementInput.jump, this.movementInput.sneak);
               if (var2.method7()) {
                  return;
               }

               this.sendQueue.addToSendQueue(new C05PacketPlayerLook(var2.t_, var2.bF_, var2.bG_));
               this.sendQueue.addToSendQueue(new C0CPacketInput(var2.field0, var2.de_, var2.field3, var2.field1));
               (new Class537.Class0(var2)).method7();
            } else {
               this.onUpdateWalkingPlayer();
            }
         }

      }
   }

   @Shadow
   public abstract boolean isRidingHorse();

   @Shadow
   public abstract void onEnchantmentCritical(Entity var1);

   @Shadow
   public abstract void playSound(String var1, float var2, float var3);

   @Shadow
   protected abstract boolean pushOutOfBlocks(double var1, double var3, double var5);
}
