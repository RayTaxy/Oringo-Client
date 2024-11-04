package map;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import oringo.Oringo;
import oringo.module.CameraModule;

public class Class91 {
   public static Framebuffer ap_ = new Framebuffer(1, 1, false);
   public static final Class354 field1 = new Class354("DepthShader.frag", "Vertex.vert");
   public static boolean field2 = false;
   public static int field3 = -1;

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(RenderWorldLastEvent var1) {
      field2 = true;
      ap_ = Class90.method0(ap_);
      field2 = false;
      if (field3 != -1) {
         GL11.glPushAttrib(8192);
         ScaledResolution var2 = new ScaledResolution(Oringo.field9);
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0D, (double)Oringo.field9.displayWidth / (double)var2.getScaleFactor(), (double)Oringo.field9.displayHeight / (double)var2.getScaleFactor(), 0.0D, -1.0D, 1.0D);
         GL11.glMatrixMode(5888);
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GlStateManager.bindTexture(ap_.framebufferTexture);
         GL13.glActiveTexture(33985);
         GL11.glBindTexture(3553, field3);
         GL13.glActiveTexture(33986);
         GL11.glBindTexture(3553, ap_.depthBuffer);
         GL13.glActiveTexture(33984);
         field1.method5();
         field1.method0("base", 0);
         field1.method0("worldDepth", 1);
         field1.method0("framebufferDepth", 2);
         field1.method0("resolution", (float)ap_.framebufferTextureWidth, (float)ap_.framebufferHeight);
         field1.method0("color", Class362.field7.method17());
         CameraModule.method5();
         field1.method2();
         field3 = -1;
         GL11.glMatrixMode(5889);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         GL11.glPopAttrib();
      }

   }
}
