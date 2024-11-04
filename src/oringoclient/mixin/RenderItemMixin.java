package oringo.mixin;

import java.awt.Color;
import map.Class362;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.module.GlintModule;

@Mixin({RenderItem.class})
public class RenderItemMixin {
   private Color getColorSetting(int var1) {
      GlintModule var2 = Class362.field16;
      String var3 = var2.field0.method4();
      byte var4 = -1;
      switch(var3.hashCode()) {
      case -1656737386:
         if (var3.equals("Rainbow")) {
            var4 = 1;
         }
         break;
      case 2513204:
         if (var3.equals("RGBA")) {
            var4 = 0;
         }
         break;
      case 80774569:
         if (var3.equals("Theme")) {
            var4 = 2;
         }
      }

      switch(var4) {
      case 0:
         return var2.bW_.method17();
      case 1:
         return Color.getHSBColor((float)((double)System.currentTimeMillis() % (var2.field2.method0() * 1000.0D) / (var2.field2.method0() * 1000.0D)), 0.65F, 1.0F);
      case 2:
         return Class362.field7.method17();
      default:
         return new Color(var1);
      }
   }

   @ModifyConstant(
      method = {"renderEffect"},
      constant = {@Constant(
   intValue = -8372020
)}
   )
   public int getColor(int var1) {
      return Class362.field16.method44() ? this.getColorSetting(var1).getRGB() : var1;
   }

   @Redirect(
      method = {"renderEffect"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/Minecraft;getSystemTime()J"
)
   )
   public long getSystemTime() {
      return Class362.field16.method44() ? (long)((double)Minecraft.getSystemTime() * (Class362.field16.field3.method0() / 100.0D)) : Minecraft.getSystemTime();
   }

   @Inject(
      method = {"renderItemModelTransform"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/entity/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/resources/model/IBakedModel;)V",
   shift = Shift.BEFORE
)},
      cancellable = true
   )
   public void method0(ItemStack var1, IBakedModel var2, TransformType var3, CallbackInfo var4) {
   }
}
