package oringo.mixin;

import map.Class362;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import oringo.module.KillAuraModule;

@Mixin(
   value = {ItemRenderer.class},
   priority = 1
)
public abstract class ItemRendererMixin {
   @Shadow
   private float equippedProgress;
   @Shadow
   private ItemStack itemToRender;
   @Shadow
   @Final
   private Minecraft mc;
   @Shadow
   private float prevEquippedProgress;

   @Shadow
   protected abstract void performDrinking(AbstractClientPlayer var1, float var2);

   @Overwrite
   public void transformFirstPersonItem(float var1, float var2) {
      float var3 = Class362.field18.field4.method1();
      float var4 = Class362.field18.field15.method1();
      float var5 = Class362.field18.field13.method1();
      float var6 = Class362.field18.field11.method1();
      if (!Class362.field18.method44()) {
         var3 = 1.0F;
         var6 = 0.0F;
         var5 = 0.0F;
         var4 = 0.0F;
      }

      GlStateManager.translate(var4, var5, var6);
      GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
      GlStateManager.translate(0.0F, var1 * -0.6F, 0.0F);
      GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
      float var7 = MathHelper.sin(var2 * var2 * 3.1415927F);
      float var8 = MathHelper.sin(MathHelper.sqrt_float(var2) * 3.1415927F);
      GlStateManager.rotate(var7 * -20.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(var8 * -20.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.rotate(var8 * -80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(0.4F * var3, 0.4F * var3, 0.4F * var3);
      if (Class362.field18.method44()) {
         GlStateManager.rotate(Class362.field18.field10.method1(), 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(Class362.field18.field7.method1(), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(Class362.field18.field5.method1(), 0.0F, 0.0F, 1.0F);
      }

   }

   @Shadow
   protected abstract void rotateArroundXAndY(float var1, float var2);

   @Shadow
   protected abstract void doBowTransformations(float var1, AbstractClientPlayer var2);

   @Shadow
   public abstract void renderItem(EntityLivingBase var1, ItemStack var2, TransformType var3);

   @Overwrite
   public void renderItemInFirstPerson(float var1) {
      float var2 = 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * var1);
      if (Class362.field18.method44() && Class362.field18.field12.method1()) {
         var2 = 0.0F;
         this.itemToRender = this.mc.thePlayer.getHeldItem();
      }

      EntityPlayerSP var3 = this.mc.thePlayer;
      float var4 = var3.getSwingProgress(var1);
      float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * var1;
      float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * var1;
      this.rotateArroundXAndY(var5, var6);
      this.setLightMapFromPlayer(var3);
      this.rotateWithPlayerRotations(var3, var1);
      GlStateManager.enableRescaleNormal();
      GlStateManager.pushMatrix();
      if (this.itemToRender != null) {
         boolean var7 = KillAuraModule.field24 != null && !KillAuraModule.field26.method4().equals("None");
         if (this.itemToRender.getItem() instanceof ItemMap) {
            this.renderItemMap(var3, var5, var2, var4);
         } else if (var3.getItemInUseCount() <= 0 && !var7) {
            if (!Class362.field18.method44() || !Class362.field18.field2.method1()) {
               this.doItemUsedTransformations(var4);
            }

            this.transformFirstPersonItem(var2, var4);
         } else {
            EnumAction var8 = this.itemToRender.getItemUseAction();
            if (var7) {
               var8 = EnumAction.BLOCK;
            }

            label128:
            switch(var8.ordinal()) {
            case 0:
               this.transformFirstPersonItem(var2, 0.0F);
               break;
            case 1:
            case 2:
               this.performDrinking(var3, var1);
               this.transformFirstPersonItem(var2, Class362.field18.method44() ? var4 : 0.0F);
               break;
            case 3:
               if (!Class362.field18.method44()) {
                  this.transformFirstPersonItem(var2, 0.0F);
                  this.doBlockTransformations();
               } else {
                  if (Class362.field18.field3.method1()) {
                     var2 = 0.0F;
                  }

                  float var9 = MathHelper.sin(MathHelper.sqrt_float(var4) * 3.1415927F);
                  float var10 = MathHelper.sin(var4 * var4 * 3.1415927F);
                  String var11 = Class362.field18.field14.method4();
                  byte var12 = -1;
                  switch(var11.hashCode()) {
                  case -1685782617:
                     if (var11.equals("Z axis")) {
                        var12 = 6;
                     }
                     break;
                  case -1530467646:
                     if (var11.equals("Reverse")) {
                        var12 = 2;
                     }
                     break;
                  case -352259601:
                     if (var11.equals("Exhibition")) {
                        var12 = 5;
                     }
                     break;
                  case 48570:
                     if (var11.equals("1.7")) {
                        var12 = 0;
                     }
                     break;
                  case 2433880:
                     if (var11.equals("None")) {
                        var12 = 9;
                     }
                     break;
                  case 2499386:
                     if (var11.equals("Push")) {
                        var12 = 4;
                     }
                     break;
                  case 2587234:
                     if (var11.equals("Stab")) {
                        var12 = 1;
                     }
                     break;
                  case 65078532:
                     if (var11.equals("Chill")) {
                        var12 = 3;
                     }
                     break;
                  case 76999651:
                     if (var11.equals("Penis")) {
                        var12 = 8;
                     }
                     break;
                  case 79882757:
                     if (var11.equals("Sigma")) {
                        var12 = 7;
                     }
                  }

                  switch(var12) {
                  case 0:
                     this.transformFirstPersonItem(var2 / 2.0F, var4);
                     this.doBlockTransformations();
                     break label128;
                  case 1:
                     this.transformFirstPersonItem(var2, 0.0F);
                     GlStateManager.translate(-0.5F, 0.2F, 0.0F);
                     GlStateManager.rotate(-10.0F, 0.0F, 1.0F, 0.0F);
                     GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 1.0F);
                     GlStateManager.translate(0.0F, var9, 0.0F);
                     GlStateManager.rotate(var10 * -50.0F, 1.0F, 0.0F, 1.0F);
                     break label128;
                  case 2:
                     this.transformFirstPersonItem(var2, var4);
                     GlStateManager.rotate(var10 * 20.0F, 0.0F, 1.0F, 0.0F);
                     GlStateManager.rotate(var9 * 20.0F, 0.0F, 0.0F, 1.0F);
                     GlStateManager.rotate(var9 * 80.0F, 1.0F, 0.0F, 0.0F);
                     this.doBlockTransformations();
                     break label128;
                  case 3:
                     this.transformFirstPersonItem(var2, 0.0F);
                     GL11.glRotatef(var9 * 60.0F / 2.0F, -var9 / 2.0F, -0.0F, -16.0F);
                     GL11.glRotatef(-var9 * 30.0F, 1.0F, var9 / 2.0F, -1.0F);
                     this.doBlockTransformations();
                     break label128;
                  case 4:
                     this.transformFirstPersonItem(var2, var4 - 1.0F);
                     this.doBlockTransformations();
                     break label128;
                  case 5:
                     this.transformFirstPersonItem(var2 / 2.0F, 0.0F);
                     GlStateManager.rotate(-var9 * 31.0F, 1.0F, 0.0F, 2.0F);
                     GlStateManager.rotate(-var9 * 33.0F, 1.5F, var9 / 1.1F, 0.0F);
                     this.doBlockTransformations();
                     break label128;
                  case 6:
                     this.transformFirstPersonItem(var2, 0.0F);
                     this.doBlockTransformations();
                     GlStateManager.rotate(var9 * -40.0F, 0.0F, 0.0F, 1.0F);
                     break label128;
                  case 7:
                     this.transformFirstPersonItem(var2 / 2.0F, 0.0F);
                     GlStateManager.rotate(-var10 * 55.0F / 2.0F, -8.0F, -0.0F, 9.0F);
                     GlStateManager.rotate(-var10 * 45.0F, 1.0F, var10 / 2.0F, -0.0F);
                     this.doBlockTransformations();
                     GL11.glTranslated(1.2D, 0.3D, 0.5D);
                     GL11.glTranslatef(-1.0F, this.mc.thePlayer.isSneaking() ? -0.1F : -0.2F, 0.2F);
                     break label128;
                  case 8:
                     this.transformFirstPersonItem(var2, 0.0F);
                     GlStateManager.rotate(14.0F, 1.0F, 0.0F, 0.0F);
                     GlStateManager.translate(var9 - 2.0F, 0.2F, -var9);
                     GlStateManager.rotate(-59.0F, -1.0F, 0.0F, 3.0F);
                     GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
                     GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
                     break label128;
                  case 9:
                     if (!Class362.field18.method44() || !Class362.field18.field2.method1()) {
                        this.doItemUsedTransformations(var4);
                     }

                     this.transformFirstPersonItem(var2, var4);
                     break label128;
                  default:
                     this.transformFirstPersonItem(var2, 0.0F);
                     this.doBlockTransformations();
                  }
               }
               break;
            case 4:
               this.transformFirstPersonItem(var2, Class362.field18.method44() ? var4 : 0.0F);
               this.doBowTransformations(var1, var3);
            }
         }

         this.renderItem(var3, this.itemToRender, TransformType.FIRST_PERSON);
      } else if (!var3.isInvisible() && !Class362.field18.field9.method1()) {
         this.renderPlayerArm(var3, var2, var4);
      }

      GlStateManager.popMatrix();
      GlStateManager.disableRescaleNormal();
      RenderHelper.disableStandardItemLighting();
   }

   @Shadow
   protected abstract void doItemUsedTransformations(float var1);

   @Shadow
   protected abstract void rotateWithPlayerRotations(EntityPlayerSP var1, float var2);

   @Shadow
   protected abstract void setLightMapFromPlayer(AbstractClientPlayer var1);

   @Overwrite
   public void doBlockTransformations() {
      GlStateManager.translate(-0.5F, 0.2F, 0.0F);
      GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
   }

   @Shadow
   protected abstract void renderPlayerArm(AbstractClientPlayer var1, float var2, float var3);

   @Shadow
   protected abstract void renderItemMap(AbstractClientPlayer var1, float var2, float var3, float var4);
}
