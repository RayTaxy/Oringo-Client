package oringo.mixin;

import map.Class262;
import map.Class265;
import map.Class362;
import map.Class480;
import map.Class510;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.codec.digest.DigestUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.AutoCombineModule;
import oringo.module.ScreenShotModule;

@Mixin(
   value = {AbstractClientPlayer.class},
   priority = 1001
)
public abstract class AbstractClientPlayerMixin extends EntityPlayerMixin implements Class480 {
   private boolean field38;
   private ResourceLocation field39;
   private Class262 field0;

   public Class262 method17() {
      return this.field0;
   }

   public AbstractClientPlayerMixin() {
      this.field0 = (Class262)Class265.field1.get(DigestUtils.sha256Hex(this.getUniqueID().toString().replaceAll("-", "")));
   }

   @Inject(
      method = {"getLocationSkin()Lnet/minecraft/util/ResourceLocation;"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void oringo$getLocationSkin(CallbackInfoReturnable var1) {
      if (Class362.field0.field10.method1() && ScreenShotModule.field8) {
         var1.setReturnValue(DefaultPlayerSkin.getDefaultSkin(this.getUniqueID()));
      }

   }

   public void method0(Class262 var1) {
      this.field0 = var1;
      this.field38 = false;
   }

   @Inject(
      method = {"getLocationCape"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void oringo$getLocationCape(CallbackInfoReturnable var1) {
      if (this.field38) {
         if (this.field39 != null) {
            var1.setReturnValue(this.field39);
         }

      } else {
         this.field39 = Class510.method0((EntityPlayer)this);
         if (this.field39 != null) {
            if (AutoCombineModule.method1(this.field0.method5())) {
               var1.setReturnValue(this.field39);
               this.field38 = true;
            }
         } else {
            this.field38 = true;
         }

      }
   }
}
