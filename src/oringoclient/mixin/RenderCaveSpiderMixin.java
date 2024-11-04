package oringo.mixin;

import map.Class362;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderCaveSpider;
import net.minecraft.entity.monster.EntityCaveSpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderCaveSpider.class})
public class RenderCaveSpiderMixin {
   @Inject(
      method = {"preRenderCallback(Lnet/minecraft/entity/monster/EntityCaveSpider;F)V"},
      at = {@At("HEAD")}
   )
   private void method0(EntityCaveSpider var1, float var2, CallbackInfo var3) {
      if (Class362.field53.method44() && Class362.field53.cn_.method1()) {
         GlStateManager.scale(Class362.field53.field0.method0(), Class362.field53.field0.method0(), Class362.field53.field0.method0());
      }

   }
}
