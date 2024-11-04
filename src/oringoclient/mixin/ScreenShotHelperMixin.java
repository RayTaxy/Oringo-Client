package oringo.mixin;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import map.Class362;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ScreenShotHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.ChestStealerModule;
import oringo.module.CropNukerModule;
import oringo.module.NickHiderModule;
import oringo.module.RenderBarriersModule;
import oringo.module.ScreenShotModule;

@Mixin(
   value = {ScreenShotHelper.class},
   priority = Integer.MAX_VALUE
)
public abstract class ScreenShotHelperMixin {
   @Inject(
      method = {"saveScreenshot(Ljava/io/File;Ljava/lang/String;IILnet/minecraft/client/shader/Framebuffer;)Lnet/minecraft/util/IChatComponent;"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void method0(File var0, String var1, int var2, int var3, Framebuffer var4, CallbackInfoReturnable var5) {
      if (Class362.field0.method44() && !ScreenShotModule.field8) {
         ScreenShotModule.field1 = var0;
         ScreenShotModule.field4 = var1;
         ScreenShotModule.field2 = var2;
         ScreenShotModule.cX_ = var3;
         ScreenShotModule.field9 = var4;
         ScreenShotModule.field6 = Class362.field32.method44();
         if (Class362.field0.field13.method1()) {
            Class362.field32.method1(true);
            NickHiderModule.field1 = Class362.field0.cY_.method1();
         }

         File var6 = new File(var0, "screenshots");
         var6.mkdir();
         File var7 = null;
         if (var1 == null) {
            var7 = getTimestampedPNGFileForDirectory(var7);
         } else {
            new File(var0, var1);
         }

         ScreenShotModule.field8 = true;
         var5.setReturnValue((Object)null);
      }

   }

   @Redirect(
      method = {"saveScreenshot(Ljava/io/File;Ljava/lang/String;IILnet/minecraft/client/shader/Framebuffer;)Lnet/minecraft/util/IChatComponent;"},
      at = @At(
   value = "INVOKE",
   target = "Ljavax/imageio/ImageIO;write(Ljava/awt/image/RenderedImage;Ljava/lang/String;Ljava/io/File;)Z"
)
   )
   private static boolean copyScreen(RenderedImage var0, String var1, File var2) throws IOException {
      if (Class362.field0.method44()) {
         RenderBarriersModule.method1(String.format("Your screenshot has been saved as §r%s§f!", var2.getName()), new ClickEvent(Action.OPEN_FILE, var2.getAbsolutePath()));
         (new Thread(ScreenShotHelperMixin::lambda$copyScreen$0)).start();
         return true;
      } else {
         return ImageIO.write(var0, var1, var2);
      }
   }

   @Shadow
   private static File getTimestampedPNGFileForDirectory(File var0) {
      return null;
   }

   private static void lambda$copyScreen$0(RenderedImage var0, String var1, File var2) {
      if (ScreenShotModule.field12.method1()) {
         ChestStealerModule.method0((BufferedImage)var0);
      }

      try {
         ImageIO.write(var0, var1, var2);
         CropNukerModule.method0(var2);
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }
}
