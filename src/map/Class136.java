package map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;

public class Class136 {
   public static final Minecraft field0 = Minecraft.getMinecraft();
   public static final DateFormat field1 = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
   public static IntBuffer field2;

   public static void lambda$saveScreenshot$1(File var0, BufferedImage var1) {
      try {
         ImageIO.write(var1, "png", var0);
      } catch (IOException var3) {
      }

   }

   public static void lambda$getScreenshotImage$0(int[] var0, int var1, int var2, Framebuffer var3, Consumer var4) {
      field2.get(var0);
      TextureUtil.processPixelValues(var0, var1, var2);
      BufferedImage var5 = null;
      if (OpenGlHelper.isFramebufferEnabled()) {
         var5 = new BufferedImage(var3.framebufferWidth, var3.framebufferHeight, 1);
         int var6 = var3.framebufferTextureHeight - var3.framebufferHeight;

         for(int var7 = var6; var7 < var3.framebufferTextureHeight; ++var7) {
            for(int var8 = 0; var8 < var3.framebufferWidth; ++var8) {
               var5.setRGB(var8, var7 - var6, var0[var7 * var3.framebufferTextureWidth + var8]);
            }
         }
      } else {
         var5 = new BufferedImage(var1, var2, 1);
         var5.setRGB(0, 0, var1, var2, var0, 0, var1);
      }

      var4.accept(var5);
   }
}
