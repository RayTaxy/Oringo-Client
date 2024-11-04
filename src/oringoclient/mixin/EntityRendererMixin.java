package oringo.mixin;

import java.util.Iterator;
import map.Class207;
import map.Class208;
import map.Class229;
import map.Class362;
import map.Class374;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.WorldChunkManager;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.command.PetCommand;
import oringo.event.Class26;
import oringo.module.CameraModule;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.NoDebuffModule;
import oringo.module.NoRenderModule;
import oringo.module.TimeChangerModule;

@Mixin(
   value = {EntityRenderer.class},
   priority = Integer.MAX_VALUE
)
public class EntityRendererMixin {
   @Shadow
   private float thirdPersonDistanceTemp;
   @Shadow
   private Minecraft mc;
   @Shadow
   @Final
   public ItemRenderer itemRenderer;
   @Shadow
   private float thirdPersonDistance;

   @Redirect(
      method = {"orientCamera"},
      at = @At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/Entity;rotationPitch:F",
   ordinal = 1
)
   )
   public float onCamera2(Entity var1) {
      return Class374.method0(var1.rotationPitch);
   }

   @Redirect(
      method = {"getMouseOver"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getBlockReachDistance()F"
)
   )
   private float getBlockReachDistance(PlayerControllerMP var1) {
      Class362.field8.method1();
      return Class362.field8.method44() ? (float)Class362.field8.x_.method0() : this.mc.playerController.getBlockReachDistance();
   }

   @Redirect(
      method = {"setupFog"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/entity/EntityLivingBase;isPotionActive(Lnet/minecraft/potion/Potion;)Z"
)
   )
   public boolean removeBlindness(EntityLivingBase var1, Potion var2) {
      return (!Class362.field36.method44() || !NoDebuffModule.field0.method1()) && var1.isPotionActive(var2);
   }

   @Redirect(
      method = {"orientCamera"},
      at = @At(
   value = "FIELD",
   target = "Lnet/minecraft/client/renderer/EntityRenderer;thirdPersonDistanceTemp:F"
)
   )
   public float thirdPersonDistanceTemp(EntityRenderer var1) {
      return Class362.field61.method44() && !CameraModule.field4.method1() ? CameraModule.cK_.method1() : this.thirdPersonDistanceTemp;
   }

   @Redirect(
      method = {"orientCamera"},
      at = @At(
   value = "FIELD",
   target = "Lnet/minecraft/entity/Entity;rotationYaw:F",
   ordinal = 1
)
   )
   public float onCamera(Entity var1) {
      return Class208.method0(var1.rotationYaw);
   }

   @ModifyConstant(
      method = {"getMouseOver"},
      constant = {@Constant(
   doubleValue = 3.0D,
   ordinal = 1
)}
   )
   public double getReach(double var1) {
      return Class362.field8.method44() ? Class362.field8.field2 : var1;
   }

   @Redirect(
      method = {"updateLightmap"},
      at = @At(
   value = "FIELD",
   target = "Lnet/minecraft/client/settings/GameSettings;gammaSetting:F"
)
   )
   public float gamma(GameSettings var1) {
      return !Class362.field21.method44() && !Class362.field27.method44() ? var1.gammaSetting : 10000.0F;
   }

   @Inject(
      method = {"updateCameraAndRender"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(float var1, long var2, CallbackInfo var4) {
      if (Class362.method2()) {
         if (NoRenderModule.field5 && !NoRenderModule.field6.isEmpty()) {
            Iterator var5 = NoRenderModule.field6.iterator();

            while(var5.hasNext()) {
               Runnable var6 = (Runnable)var5.next();
               var6.run();
            }

            NoRenderModule.field6.clear();
         }

         if (PetCommand.method3()) {
            NoRenderModule.field5 = false;

            try {
               Thread.sleep(1L);
            } catch (InterruptedException var7) {
            }

            var4.cancel();
         } else {
            NoRenderModule.field5 = true;
         }

      }
   }

   @Redirect(
      method = {"updateCameraAndRender"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/entity/EntityPlayerSP;setAngles(FF)V"
)
   )
   public void preventRotationMacro(EntityPlayerSP var1, float var2, float var3) {
      if (!Class229.method1().method44()) {
         var1.setAngles(var2, var3);
      }
   }

   @Inject(
      method = {"hurtCameraEffect"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(float var1, CallbackInfo var2) {
      if (CameraModule.field2.method1() && Class362.field61.method44()) {
         var2.cancel();
      }

   }

   @Inject(
      method = {"updateRenderer"},
      at = {@At("RETURN")}
   )
   public void _/* $FF was: */(CallbackInfo var1) {
      if (this.mc.gameSettings.thirdPersonView > 0) {
         if (Class362.field61.method44() && CameraModule.field4.method1()) {
            this.thirdPersonDistance = (float)Class207.method0((double)this.thirdPersonDistance + CameraModule.field3.method0(), CameraModule.cK_.method0(), 0.0D);
         }
      } else {
         this.thirdPersonDistance = this.thirdPersonDistanceTemp = Class362.field61.method44() && CameraModule.field4.method1() ? (float)CameraModule.field0.method0() : 4.0F;
      }

   }

   @Redirect(
      method = {"orientCamera"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/Vec3;distanceTo(Lnet/minecraft/util/Vec3;)D"
)
   )
   public double onCamera(Vec3 var1, Vec3 var2) {
      return Class362.field61.method44() && CameraModule.field6.method1() ? CameraModule.cK_.method0() : var1.distanceTo(var2);
   }

   @Redirect(
      method = {"renderRainSnow"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/world/biome/WorldChunkManager;getTemperatureAtHeight(FI)F"
)
   )
   public float getTemp(WorldChunkManager var1, float var2, int var3) {
      TimeChangerModule var4 = TimeChangerModule.cO_;
      return var4.method44() && var4.field0.method0(4) ? 0.0F : var1.getTemperatureAtHeight(var2, var3);
   }

   @Redirect(
      method = {"renderRainSnow"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/WorldClient;getRainStrength(F)F"
)
   )
   public float getRain(WorldClient var1, float var2) {
      TimeChangerModule var3 = TimeChangerModule.cO_;
      if (var3.method44() && var3.field0.method0("Snowing", "Thunder", "Raining")) {
         return var3.field3.method1() / 100.0F;
      } else {
         return var3.method44() && var3.field0.method0(3) ? 0.0F : var1.getRainStrength(var2);
      }
   }

   @Redirect(
      method = {"orientCamera"},
      at = @At(
   value = "FIELD",
   target = "Lnet/minecraft/client/renderer/EntityRenderer;thirdPersonDistance:F"
)
   )
   public float thirdPersonDistance(EntityRenderer var1) {
      return Class362.field61.method44() && !CameraModule.field4.method1() ? (float)CameraModule.cK_.method0() : this.thirdPersonDistance;
   }

   @Redirect(
      method = {"updateCameraAndRender"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/Minecraft;displayInGameMenu()V"
)
   )
   public void onDisplayInGameMenu(Minecraft var1) {
      if (!Class229.method1().method44()) {
         var1.displayInGameMenu();
      }

   }

   @Redirect(
      method = {"renderWorldPass"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/culling/ICamera;setPosition(DDD)V"
)
   )
   public void preventRotationMacro(ICamera var1, double var2, double var4, double var6) {
      var1.setPosition(var2, var4, var6);
      Class26.method0(var1);
   }

   @ModifyVariable(
      method = {"orientCamera"},
      at = @At(
   value = "STORE",
   ordinal = 0
),
      ordinal = 3
   )
   @Dynamic
   public double pizzaFix(double var1) {
      if (Class362.field61.method44()) {
         return !CameraModule.field4.method1() ? (double)CameraModule.cK_.method1() : (double)(this.thirdPersonDistanceTemp + (this.thirdPersonDistance - this.thirdPersonDistanceTemp) * FrozenTreasureESPModule.method5().renderPartialTicks);
      } else {
         return var1;
      }
   }
}
