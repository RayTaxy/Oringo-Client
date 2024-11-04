package oringo.mixin;

import map.Class362;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.RenderItemFrame;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.command.WardrobeCommand;
import oringo.module.OptimizationsModule;

@Mixin({RenderItemFrame.class})
public abstract class RenderItemFrameMixin {
   @Shadow
   protected abstract void renderItem(EntityItemFrame var1);

   @Inject(
      method = {"doRender(Lnet/minecraft/entity/item/EntityItemFrame;DDDFF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(EntityItemFrame var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      if (OptimizationsModule.field0.method44()) {
         ItemStack var11 = var1.getDisplayedItem();
         if (var11 != null) {
            int var12 = var11.getMetadata();
            if (Class362.field62.method44() && var12 >= 31521 && var12 < 31570 || var12 == 382) {
               var10.cancel();
               if (var12 == 31545) {
                  BlockPos var13 = var1.getHangingPosition();
                  double var14 = (double)var13.getX() - var1.posX + var2;
                  double var16 = (double)var13.getY() - var1.posY + var4;
                  double var18 = (double)var13.getZ() - var1.posZ + var6;
                  GlStateManager.pushMatrix();
                  GlStateManager.translate(var14 + 0.5D, var16 + 0.5D, var18 + 0.5D);
                  GlStateManager.rotate(180.0F - var1.rotationYaw, 0.0F, 1.0F, 0.0F);
                  GlStateManager.translate(0.0F, 0.0F, 0.4375F);
                  this.renderItem(var1);
                  GlStateManager.popMatrix();
               }
            }

         }
      }
   }

   @Redirect(
      method = {"doRender(Lnet/minecraft/entity/item/EntityItemFrame;DDDFF)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/BlockModelRenderer;renderModelBrightnessColor(Lnet/minecraft/client/resources/model/IBakedModel;FFFF)V"
)
   )
   public void renderModel(BlockModelRenderer var1, IBakedModel var2, float var3, float var4, float var5, float var6) {
      if (!OptimizationsModule.field0.method44()) {
         var1.renderModelBrightnessColor(var2, var3, var4, var5, var6);
      } else {
         WardrobeCommand.method0(var2);
      }
   }
}
