package oringo.mixin;

import java.nio.ByteBuffer;
import map.Class362;
import map.Class91;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Framebuffer.class})
public class FramebufferMixin {
   @Shadow
   public boolean useDepth;
   @Shadow
   public int framebufferTextureHeight;
   @Shadow
   public float[] framebufferColor;
   private boolean field5 = false;
   @Shadow
   public int depthBuffer;
   @Shadow
   public int framebufferTextureWidth;
   private boolean field6 = false;

   @Inject(
      method = {"createFramebuffer"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/OpenGlHelper;glFramebufferTexture2D(IIIII)V",
   shift = Shift.AFTER
)}
   )
   public void method0(int var1, int var2, CallbackInfo var3) {
      if (this.field5) {
         this.depthBuffer = GL11.glGenTextures();
         GL11.glBindTexture(3553, this.depthBuffer);
         GL11.glTexImage2D(3553, 0, 34041, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, 6402, 5126, (ByteBuffer)null);
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
         GL11.glTexParameteri(3553, 34891, 6409);
         GL30.glFramebufferTexture2D(36160, 33306, 3553, this.depthBuffer, 0);
      }
   }

   @Redirect(
      method = {"deleteFramebuffer"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/OpenGlHelper;glDeleteRenderbuffers(I)V"
)
   )
   public void onDepth(int var1) {
      if (!this.field6) {
         OpenGlHelper.glDeleteRenderbuffers(var1);
      } else {
         GL11.glDeleteTextures(this.depthBuffer);
      }

   }

   @Redirect(
      method = {"framebufferClear"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/GlStateManager;clearDepth(D)V"
)
   )
   public void onClear(double var1) {
      if (!this.field6) {
         GlStateManager.clearDepth(var1);
      } else {
         GlStateManager.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.clearDepth(var1);
         GlStateManager.clear(256);
         GlStateManager.clearColor(this.framebufferColor[0], this.framebufferColor[1], this.framebufferColor[2], this.framebufferColor[3]);
      }
   }

   @Inject(
      method = {"createFramebuffer"},
      at = {@At("RETURN")}
   )
   public void method1(int var1, int var2, CallbackInfo var3) {
      if (this.field6) {
         this.useDepth = this.field5;
      }
   }

   @Inject(
      method = {"createFramebuffer"},
      at = {@At("HEAD")}
   )
   public void method2(int var1, int var2, CallbackInfo var3) {
      this.field6 = Minecraft.getMinecraft().getFramebuffer() == this || Class362.method2() && Class91.field2;
      if (this.field6) {
         this.field5 = this.useDepth;
         this.useDepth = false;
      }
   }
}
