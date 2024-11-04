package oringo.module;

import java.awt.Color;
import map.Class388;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class194;

public class NoCarpetModule extends Module {
   public NoCarpetModule() {
      super("No Carpet", Category.other, SubCategory.qol, "Removes carpet collisions to prevent false flags");
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      ScaledResolution var5 = new ScaledResolution(Oringo.field9);
      GlStateManager.scale(1.0F / (float)var5.getScaleFactor(), 1.0F / (float)var5.getScaleFactor(), 1.0F / (float)var5.getScaleFactor());
      var0 *= (float)var5.getScaleFactor();
      var1 *= (float)var5.getScaleFactor();
      var2 *= (float)var5.getScaleFactor();
      var3 *= (float)var5.getScaleFactor();
      var4 *= (float)var5.getScaleFactor();
      Class388.field1.method5();
      PrinterModule.method0(var0, var1, var2, var3, var4, true, Color.WHITE);
      TicTacToeModule.method5();
      BossBarModule.method0(var0, var1, var2, var3);
      Class388.field1.method2();
      GlStateManager.scale((float)var5.getScaleFactor(), (float)var5.getScaleFactor(), (float)var5.getScaleFactor());
      GlStateManager.disableBlend();
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (var1.field0 != null && field58.theWorld != null) {
         if (var1.field2 == Blocks.carpet && field58.theWorld.getBlockState(var1.field0.down()).getBlock().isFullCube()) {
            BlockPos var2 = var1.field0.down();
            if (!field58.theWorld.getBlockState(var2).getBlock().isPassable(field58.theWorld, var2)) {
               var1.method9();
            }
         }

      }
   }

   public static void method5() {
      GlStateManager.doPolygonOffset(1.0F, 2000000.0F);
      GlStateManager.disablePolygonOffset();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GL11.glDisable(2960);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GlStateManager.disableBlend();
      GlStateManager.disableLighting();
      GlStateManager.enableTexture2D();
      GlStateManager.enableAlpha();
      GL11.glPopAttrib();
   }
}
