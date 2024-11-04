package oringo.mixin;

import map.Class503;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.Oringo;
import oringo.module.LividFinderModule;

@Mixin({ModelBiped.class})
public class ModelBipedMixin {
   @Shadow
   public ModelRenderer bipedHead;

   @Inject(
      method = {"setRotationAngles"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/model/ModelBiped;swingProgress:F"
)}
   )
   private void method0(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7, CallbackInfo var8) {
      if (LividFinderModule.method5().method44()) {
         if (var7 != null && (float)((int)var3) != var3 && var7.equals(Minecraft.getMinecraft().thePlayer)) {
            this.bipedHead.rotateAngleX = (Class503.field1 + (((EntityPlayerSPAccessor)var7).getLastReportedPitch() - Class503.field1) * ((MinecraftAccessor)Oringo.field9).getTimer().renderPartialTicks) / 57.295776F;
         }

      }
   }
}
