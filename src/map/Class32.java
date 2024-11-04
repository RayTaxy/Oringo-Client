package map;

import java.awt.Color;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import oringo.module.AutoRabbitModule;
import oringo.module.AutoSimonModule;
import oringo.module.HitboxesModule;
import oringo.module.InventoryHUDModule;
import oringo.module.LightningDetectModule;
import oringo.module.NoVoidModule;
import oringo.module.ReachModule;

public class Class32 extends Class283 {
   public void method0(WorldRenderer var1, double var2, double var4, int var6, int var7, int var8, int var9, int var10, int var11) {
      var1.begin(7, DefaultVertexFormats.POSITION_COLOR);
      var1.pos(var2 + 0.0D, var4 + 0.0D, 0.0D).color(var8, var9, var10, var11).endVertex();
      var1.pos(var2 + 0.0D, var4 + (double)var7, 0.0D).color(var8, var9, var10, var11).endVertex();
      var1.pos(var2 + (double)var6, var4 + (double)var7, 0.0D).color(var8, var9, var10, var11).endVertex();
      var1.pos(var2 + (double)var6, var4 + 0.0D, 0.0D).color(var8, var9, var10, var11).endVertex();
      Tessellator.getInstance().draw();
   }

   public void method0(ItemStack var1, double var2, double var4) {
      if (var1 != null) {
         if (var1.stackSize != 1) {
            String var6 = String.valueOf(var1.stackSize);
            if (var1.stackSize < 1) {
               var6 = EnumChatFormatting.RED + String.valueOf(var1.stackSize);
            }

            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.disableBlend();
            Class311.field12.method3(var6, (float)(var2 + 19.0D - 2.0D - (double)Class311.field12.method0(var6)), (float)(var4 + 6.0D + 3.0D), Color.white.getRGB());
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
         }

         if (var1.getItem().showDurabilityBar(var1)) {
            double var12 = var1.getItem().getDurabilityForDisplay(var1);
            int var8 = (int)Math.round(13.0D - var12 * 13.0D);
            int var9 = (int)Math.round(255.0D - var12 * 255.0D);
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();
            Tessellator var10 = Tessellator.getInstance();
            WorldRenderer var11 = var10.getWorldRenderer();
            this.method0(var11, var2 + 2.0D, var4 + 13.0D, 13, 2, 0, 0, 0, 255);
            this.method0(var11, var2 + 2.0D, var4 + 13.0D, 12, 1, (255 - var9) / 4, 64, 0, 255);
            this.method0(var11, var2 + 2.0D, var4 + 13.0D, var8, 1, 255 - var9, var9, 0, 255);
            GlStateManager.enableAlpha();
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
         }
      }

   }

   public static boolean method0() {
      return NoVoidModule.field5;
   }

   public boolean method1() {
      return Class362.field44.method44();
   }

   public void method0(float var1, float var2, float var3, float var4, float var5, float var6) {
      ReachModule.method0((double)var1, (double)var2, (double)(var1 + var3), (double)(var2 + var4), (double)var5, (new Color(50, 50, 50, 30)).getRGB());
      if (this.j_() && field0.currentScreen instanceof GuiChat) {
         AutoSimonModule.method0(var1, var2, var3, var4, var5, var6, Color.white.getRGB());
      } else if (Class362.field44.bP_.method1()) {
         AutoRabbitModule.method0(var1, var2, var3, var4, var5, var6, Class362.field7.method0(0.0F).getRGB(), Class362.field7.method0(3.0F).getRGB(), Class362.field7.method0(6.0F).getRGB(), Class362.field7.method0(9.0F).getRGB());
      }

   }

   public Class32() {
      super(Class362.field44.field0, Class362.field44.field2);
   }

   public void method2() {
      this.method1(185.0F, 80.0F - (Class311.field12.method0() - 4.0F));
      GL11.glPushMatrix();
      super.method2();
      new ScaledResolution(field0);
      boolean var2 = false;
      double var3 = (double)this.k_();
      double var5 = (double)this.method15();
      new ScaledResolution(field0);
      if (!Class362.field44.bP_.method1()) {
         for(float var8 = 0.0F; var8 < 3.0F; var8 += 0.5F) {
            float var9 = var3 + (double)(this.method7() / 2.0F) > (double)(new ScaledResolution(field0)).getScaledWidth() / 2.0D ? var8 : -var8;
            HitboxesModule.method0(var3 - (double)var9, var5 + (double)Class311.field12.method0() - 4.0D + (double)var8, (double)this.method7(), (double)this.method13(), 10.0D, (new Color(20, 20, 20, 30)).getRGB());
         }
      }

      if (this.j_() && field0.currentScreen instanceof GuiChat) {
         AutoSimonModule.method0((float)var3, (float)var5 + Class311.field12.method0() - 4.0F, 182.0F, 80.0F - (Class311.field12.method0() - 4.0F), 10.0F, 2.5F, Color.white.getRGB());
      } else {
         LightningDetectModule.method0((float)var3, (float)var5 + Class311.field12.method0() - 4.0F, 182.0F, 80.0F - (Class311.field12.method0() - 4.0F), 5.0F, 1.25F);
      }

      Class311.field12.method2("Inventory", (float)var3 + 90.0F, (float)var5 + Class311.field12.method0(), Color.white.getRGB());
      GlStateManager.enableRescaleNormal();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      RenderHelper.enableGUIStandardItemLighting();

      for(int var10 = 9; var10 < 36; ++var10) {
         if (var10 % 9 == 0) {
            var5 += 20.0D;
         }

         ItemStack var12 = field0.thePlayer.inventory.getStackInSlot(var10);
         if (var12 != null) {
            field0.getRenderItem().renderItemAndEffectIntoGUI(var12, (int)var3 + 4 + 20 * (var10 % 9), (int)var5);
            this.method0(var12, var3 + 4.0D + (double)(20 * (var10 % 9)), var5);
         }
      }

      RenderHelper.disableStandardItemLighting();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableRescaleNormal();
      GlStateManager.disableBlend();
      GL11.glPopMatrix();
      InventoryHUDModule var11 = Class362.field44;
   }
}
