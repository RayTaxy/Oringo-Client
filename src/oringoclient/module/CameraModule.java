package oringo.module;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.world.storage.MapData;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class CameraModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Start distance", 3.0D, 1.0D, 10.0D, 0.1D, CameraModule::lambda$static$1);
   public static final DoubleSetting cK_ = new DoubleSetting("Camera Distance", 4.0D, 2.0D, 10.0D, 0.1D);
   public static final BooleanSetting field2 = new BooleanSetting("No hurt cam", false);
   public static final DoubleSetting field3 = new DoubleSetting("Smooth speed", 1.0D, 0.1D, 5.0D, 0.1D, CameraModule::lambda$static$0);
   public static final BooleanSetting field4 = new BooleanSetting("Smooth f5", true);
   public static final BooleanSetting field5 = new BooleanSetting("No front", false);
   public static final BooleanSetting field6 = new BooleanSetting("Camera Clip", true);

   public static void method5() {
      ScaledResolution var0 = new ScaledResolution(Oringo.field9);
      float var1 = (float)var0.getScaledWidth_double();
      float var2 = (float)var0.getScaledHeight_double();
      GL11.glBegin(7);
      GL11.glTexCoord2f(0.0F, 1.0F);
      GL11.glVertex2f(0.0F, 0.0F);
      GL11.glTexCoord2f(0.0F, 0.0F);
      GL11.glVertex2f(0.0F, var2);
      GL11.glTexCoord2f(1.0F, 0.0F);
      GL11.glVertex2f(var1, var2);
      GL11.glTexCoord2f(1.0F, 1.0F);
      GL11.glVertex2f(var1, 0.0F);
      GL11.glEnd();
   }

   public CameraModule() {
      super("Camera", Category.render, SubCategory.ui, "Changes the third person camera");
      this.method0(new Setting[]{cK_, field6, field2, field5, field4, field3, field0});
   }

   public static Boolean lambda$static$0() {
      return !field4.method1();
   }

   public static void method0(MapData var0) {
      if (ArcadeESPModule.method3()) {
         CustomHubMap.field58.fontRendererObj.drawString(var0.mapName, 0, 64, -1);
      } else if (var0.mapName.equals("map_31545")) {
         Tessellator var1 = Tessellator.getInstance();
         WorldRenderer var2 = var1.getWorldRenderer();
         short var3 = 448;
         CustomHubMap.Class0 var4 = CustomHubMap.field5;
         if (var4 == null && CustomHubMap.aD_.length != 0) {
            long var5 = System.currentTimeMillis();

            while(var5 - CustomHubMap.field6 >= (long)CustomHubMap.Class0.access$000(CustomHubMap.aD_[CustomHubMap.field2])) {
               CustomHubMap.field6 += (long)CustomHubMap.Class0.access$000(CustomHubMap.aD_[CustomHubMap.field2++]);
               if (CustomHubMap.field2 == CustomHubMap.aD_.length) {
                  CustomHubMap.field2 = 0;
               }
            }

            var4 = CustomHubMap.aD_[CustomHubMap.field2];
         }

         if (var4 != null) {
            var4.method1();
            GL11.glTexParameteri(3553, 10241, 9728);
            GL11.glTexParameteri(3553, 10240, 9728);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(1, 771, 0, 1);
            GlStateManager.disableAlpha();
            GL11.glTranslated(64.0D, 63.99D, -0.001D);
            var2.begin(7, DefaultVertexFormats.POSITION_TEX);
            double var7 = OptimizationsModule.field0.method44() ? 8.0D : 0.0D;
            var2.pos((double)(-var3), (double)(-var3), var7).tex(0.0D, 0.0D).endVertex();
            var2.pos((double)(-var3), (double)var3, var7).tex(0.0D, 1.0D).endVertex();
            var2.pos((double)var3, (double)var3, var7).tex(1.0D, 1.0D).endVertex();
            var2.pos((double)var3, (double)(-var3), var7).tex(1.0D, 0.0D).endVertex();
            var1.draw();
            GL11.glTranslated(-64.0D, -63.99D, 0.001D);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
         }
      }
   }

   public static Boolean lambda$static$1() {
      return !field4.method1();
   }
}
