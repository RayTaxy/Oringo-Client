package oringo.mixin;

import java.util.Map;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.OptimizationsModule;

@Mixin({LayerArmorBase.class})
public class LayerArmorBaseMixin {
   @Shadow
   @Final
   private static Map ARMOR_TEXTURE_RES_MAP;

   @Inject(
      method = {"getArmorResource(Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;ILjava/lang/String;)Lnet/minecraft/util/ResourceLocation;"},
      at = {@At("HEAD")},
      cancellable = true,
      remap = false
   )
   public void method0(Entity var1, ItemStack var2, int var3, String var4, CallbackInfoReturnable var5) {
      if (OptimizationsModule.field0.method44()) {
         ItemArmor var6 = (ItemArmor)var2.getItem();
         String var7 = var6.getArmorMaterial().getName();
         String var8 = "minecraft";
         int var9 = var7.indexOf(58);
         if (var9 != -1) {
            var8 = var7.substring(0, var9);
            var7 = var7.substring(var9 + 1);
         }

         String var10 = var4 == null ? "" : "_" + var4;
         String var11 = var8 + ":textures/models/armor/" + var7 + "_layer_" + (var3 == 2 ? 2 : 1) + var10 + ".png";
         var11 = ForgeHooksClient.getArmorTexture(var1, var2, var11, var3, var4);
         ResourceLocation var12 = (ResourceLocation)ARMOR_TEXTURE_RES_MAP.get(var11);
         if (var12 == null) {
            var12 = new ResourceLocation(var11);
            ARMOR_TEXTURE_RES_MAP.put(var11, var12);
         }

         var5.setReturnValue(var12);
      }
   }
}
