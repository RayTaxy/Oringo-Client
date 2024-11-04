package oringo.mixin;

import java.util.Random;
import java.util.UUID;
import map.Class289;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.command.BrushCommand;
import oringo.event.Class269;
import oringo.event.Class344;
import oringo.module.OptimizationsModule;

@Mixin({Entity.class})
public abstract class EntityMixin {
   @Shadow
   public boolean isCollidedHorizontally;
   @Shadow
   public float rotationYaw;
   @Shadow
   private int nextStepDistance;
   @Shadow
   public float rotationPitch;
   @Shadow
   public float fallDistance;
   @Shadow
   public int fireResistance;
   @Shadow
   public boolean onGround;
   @Shadow
   public boolean isCollided;
   @Shadow
   protected boolean inWater;
   @Shadow
   public double motionX;
   @Shadow
   protected UUID entityUniqueID;
   @Shadow
   public float width;
   @Shadow
   protected Random rand;
   @Shadow
   private int entityId;
   @Shadow
   public Entity ridingEntity;
   @Shadow
   public boolean noClip;
   @Shadow
   public double motionY;
   @Shadow
   public double prevPosX;
   @Shadow
   public double prevPosZ;
   @Shadow
   public boolean isAirBorne;
   @Shadow
   public int ticksExisted;
   @Shadow
   public double posY;
   @Shadow
   protected boolean isInWeb;
   @Shadow
   public double motionZ;
   @Shadow
   protected boolean inPortal;
   @Shadow
   public float distanceWalkedModified;
   @Shadow
   public boolean isCollidedVertically;
   @Shadow
   public int timeUntilPortal;
   @Shadow
   public World worldObj;
   @Shadow
   public double posZ;
   @Shadow
   public double posX;
   @Shadow
   protected DataWatcher dataWatcher;
   @Shadow
   public float distanceWalkedOnStepModified;
   @Shadow
   private int fire;
   @Shadow
   public float stepHeight;

   @Shadow
   public abstract UUID getUniqueID();

   @Shadow
   private void resetPositionToBB() {
   }

   public void plusPlusFire() {
      ++this.fire;
   }

   @Shadow
   public abstract void setEntityBoundingBox(AxisAlignedBB var1);

   public int getNextStepDistance() {
      return this.nextStepDistance;
   }

   @Shadow
   protected abstract void entityInit();

   @Shadow
   public abstract Vec3 getLook(float var1);

   @Shadow
   public abstract void moveFlying(float var1, float var2, float var3);

   @Inject(
      method = {"setDead"},
      at = {@At("HEAD")}
   )
   private void method0(CallbackInfo var1) {
      MinecraftForge.EVENT_BUS.post(new Class269((Entity)this));
   }

   @Inject(
      method = {"canBeCollidedWith"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(CallbackInfoReturnable var1) {
      if ((new Class344((Entity)this)).method7()) {
         var1.setReturnValue(false);
      }

   }

   public int getFire() {
      return this.fire;
   }

   public void setNextStepDistance(int var1) {
      this.nextStepDistance = var1;
   }

   @Shadow
   public abstract boolean isWet();

   @Shadow
   public void moveEntity(double var1, double var3, double var5) {
   }

   @Shadow
   public abstract AxisAlignedBB getEntityBoundingBox();

   @Shadow
   public abstract boolean isSprinting();

   @Inject(
      method = {"rayTrace"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void brush$rayTrace(double var1, float var3, CallbackInfoReturnable var4) {
      if (BrushCommand.field0 && Minecraft.getMinecraft().thePlayer == this) {
         Vec3 var5 = this.getPositionEyes(var3);
         Vec3 var6 = this.getLook(var3);
         Vec3 var7 = var5.addVector(var6.xCoord * var1, var6.yCoord * var1, var6.zCoord * var1);
         var4.setReturnValue(this.worldObj.rayTraceBlocks(var5, var7, true, false, true));
      }

   }

   @Shadow
   public abstract void addEntityCrashInfo(CrashReportCategory var1);

   public void SetFire(int var1) {
      this.fire = var1;
   }

   @Shadow
   public abstract boolean isRiding();

   @Redirect(
      method = {"<init>"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/Entity;entityInit()V"
)
   )
   public void preEntityInit(Entity var1) {
      if (OptimizationsModule.field0.method44()) {
         this.dataWatcher = new Class289((Entity)this);
         this.dataWatcher.addObject(0, (byte)0);
         this.dataWatcher.addObject(1, (short)300);
         this.dataWatcher.addObject(3, (byte)0);
         this.dataWatcher.addObject(2, "");
         this.dataWatcher.addObject(4, (byte)0);
      }

      this.entityInit();
   }

   @Inject(
      method = {"handleWaterMovement"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(CallbackInfoReturnable var1) {
      if (this != Minecraft.getMinecraft().thePlayer && OptimizationsModule.field0.method44() && this.worldObj.isRemote) {
         var1.setReturnValue(this.inWater);
      }
   }

   @Shadow
   public abstract float getCollisionBorderSize();

   private static boolean lambda$write$0(StackTraceElement var0) {
      return var0.getClassName().startsWith("codes.biscuit");
   }

   @Shadow
   public abstract void setFire(int var1);

   public void doResetPositionToBB() {
      this.resetPositionToBB();
   }

   @Shadow
   protected abstract void dealFireDamage(int var1);

   @Shadow
   protected abstract void playStepSound(BlockPos var1, Block var2);

   @Shadow
   public abstract boolean isInvisible();

   @Shadow
   public abstract boolean isOffsetPositionInLiquid(double var1, double var3, double var5);

   @Shadow
   public abstract boolean isInWater();

   @Shadow
   public abstract boolean equals(Object var1);

   @Shadow
   public abstract boolean isInLava();

   @Shadow
   public abstract Vec3 getPositionEyes(float var1);

   @Shadow
   protected abstract void doBlockCollisions();
}
