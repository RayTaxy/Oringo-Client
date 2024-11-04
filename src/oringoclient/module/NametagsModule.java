package oringo.module;

import java.awt.Color;
import map.Class163;
import map.Class198;
import map.Class311;
import map.Class362;
import map.Class469;
import map.Class518;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class NametagsModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Render armor", true);
   public final EnumSetting bt_ = new EnumSetting("Armor scale", this::lambda$new$0, "Normal", new String[]{"Small", "Normal", "Large", "Very Large"});
   public final EnumSetting field2 = new EnumSetting("Held item scale", this::lambda$new$1, "Normal", new String[]{"Small", "Normal", "Large", "Very Large"});
   public final BooleanSetting field3 = new BooleanSetting("Render held item", true);
   public final BooleanSetting field4 = new BooleanSetting("Render simple name", true);

   public void method0(ItemStack var1, int var2) {
      if (var1 != null) {
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.enableAlpha();
         GlStateManager.alphaFunc(516, 0.1F);
         RenderItem var3 = field58.getRenderItem();
         IBakedModel var4 = var3.getItemModelMesher().getItemModel(var1);
         float var5 = 1.5F;
         GlStateManager.scale((float)var2 * var5, (float)(-var2) * var5, 1.0F);
         field58.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         var3.renderItem(var1, var4);
         GlStateManager.scale(1.0F / (float)var2 / var5, -1.0F / (float)var2 / var5, 1.0F);
         this.method1(var1, var2);
         GlStateManager.disableBlend();
         GlStateManager.disableAlpha();
      }
   }

   public void method0(EntityOtherPlayerMP var1, String var2, double var3, double var5, double var7) {
      String var9 = this.field4.method1() ? var1.getName() : var2;
      float var10 = (float)Math.max(1.0D, Class198.method0(DungeonESPModule.method0(var1, FrozenTreasureESPModule.method5().renderPartialTicks)) / 10.0D);
      float var11 = 0.016666668F * var10 / 4.0F;
      GlStateManager.pushMatrix();
      GlStateManager.alphaFunc(516, 0.1F);
      GlStateManager.translate(var3, var5 + (double)var1.height + 0.5D, var7);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-field58.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(field58.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(-var11, -var11, var11);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(false);
      GlStateManager.disableDepth();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableTexture2D();
      float var12 = Class311.field14.method0(var9) + 24.0F;
      float var13 = Class311.field14.method3() + 24.0F;
      Class469.method0(-var12 / 2.0F, -var13 / 2.0F, var12 / 2.0F, var13 / 2.0F, (new Color(20, 20, 20, 80)).getRGB());
      float var14 = Class163.method0(var1.getHealth() / Class518.method0(var1), 1.0F, 0.0F);
      Class469.method0(-var12 / 2.0F, var13 / 2.0F - 8.0F, var12 * (var14 - 0.5F), var13 / 2.0F, Class362.field7.method0((float)var1.getEntityId()).getRGB());
      GlStateManager.enableTexture2D();
      Class311.field14.method2(var9, 0.0F, -(var13 - Class311.field14.method0()) / 2.0F, -1);
      float var17;
      float var21;
      if (this.field0.method1()) {
         boolean var15 = false;

         for(int var16 = 3; var16 >= 0; --var16) {
            if (var1.getCurrentArmor(var16) != null) {
               var15 = true;
               break;
            }
         }

         if (var15) {
            var21 = (float)(64 * (this.bt_.method2() + 1));
            var17 = var21 / 5.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)(-var21) * 1.5D - (double)(var17 * 1.5F), -((double)(var13 / 2.0F) + (double)var21 * 0.5D + (double)var17), 0.0D);

            for(int var18 = 3; var18 >= 0; --var18) {
               ItemStack var19 = var1.getCurrentArmor(var18);
               NBTCopyModule.method0(-var21 / 2.0F, -var21 / 2.0F, var21, var21, var17, new Color(20, 20, 20, 80));
               this.method0(var19, (int)var21);
               GlStateManager.translate(var21 + var17, 0.0F, 0.0F);
            }

            GlStateManager.popMatrix();
         }
      }

      if (this.field3.method1() && var1.getHeldItem() != null) {
         float var20 = (float)(64 * (this.field2.method2() + 1));
         var21 = var20 / 5.0F;
         var17 = -var13 / 2.0F + var20 / 2.0F;
         float var22 = -var12 / 2.0F - var21 - var20 / 2.0F;
         GlStateManager.translate(var22, var17, 0.0F);
         NBTCopyModule.method0(-var20 / 2.0F, -var20 / 2.0F, var20, var20, var21, new Color(20, 20, 20, 80));
         this.method0(var1.getHeldItem(), (int)var20);
         GlStateManager.translate(-var22, -var17, 0.0F);
      }

      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.enableLighting();
      GlStateManager.disableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public Boolean lambda$new$1() {
      return !this.field3.method1();
   }

   public Boolean lambda$new$0() {
      return !this.field0.method1();
   }

   public void method1(ItemStack var1, int var2) {
      if (var1.getItem().showDurabilityBar(var1)) {
         GlStateManager.disableTexture2D();
         Tessellator var3 = Tessellator.getInstance();
         WorldRenderer var4 = var3.getWorldRenderer();
         int var5 = var2 * 3 / 4;
         int var6 = -var5 / 2;
         int var7 = var2 / 3;
         double var8 = var1.getItem().getDurabilityForDisplay(var1);
         int var10 = (int)Math.round((double)var5 - var8 * (double)var5);
         int var11 = (int)Math.round(255.0D - var8 * 255.0D);
         var4.begin(7, DefaultVertexFormats.POSITION_COLOR);
         this.method0(var4, (double)var6, (double)var7, var5 + 1, 4, 0, 0);
         this.method0(var4, (double)var6, (double)var7, var5, 2, (255 - var11) / 4, 64);
         this.method0(var4, (double)var6, (double)var7, var10, 2, 255 - var11, var11);
         var3.draw();
         GlStateManager.enableTexture2D();
      }
   }

   public NametagsModule() {
      super("Nametags", Category.render, SubCategory.world, "Custom nametag rendering");
      this.method0(new Setting[]{this.field4, this.field0, this.bt_, this.field3, this.field2});
   }

   public void method0(WorldRenderer var1, double var2, double var4, int var6, int var7, int var8, int var9) {
      var1.pos(var2 + 0.0D, var4 + 0.0D, 0.0D).color(var8, var9, 0, 200).endVertex();
      var1.pos(var2 + 0.0D, var4 + (double)var7, 0.0D).color(var8, var9, 0, 200).endVertex();
      var1.pos(var2 + (double)var6, var4 + (double)var7, 0.0D).color(var8, var9, 0, 200).endVertex();
      var1.pos(var2 + (double)var6, var4 + 0.0D, 0.0D).color(var8, var9, 0, 200).endVertex();
   }
}
