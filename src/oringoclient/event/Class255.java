package oringo.event;

import java.util.function.Consumer;
import map.Class136;
import map.Class228;
import map.Class25;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.Vec3;
import net.minecraft.world.pathfinder.WalkNodeProcessor;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import oringo.module.PopupAnimationModule;

@Cancelable
public class Class255 extends Event {
   public static void method0(Vec3 var0) {
      Class228 var1 = new Class228(var0);
      if (Class25.field0.theWorld != null) {
         IBlockState var2 = Class25.field0.theWorld.getBlockState(var1.method3());
         if (!var2.getBlock().isCollidable() && !Class25.field0.theWorld.getBlockState(var1.method3().up()).getBlock().isCollidable()) {
            WalkNodeProcessor var3 = new WalkNodeProcessor();
            PathFinder var4 = new PathFinder(var3);
            PathEntity var5 = var4.createEntityPathTo(Class25.field0.theWorld, Class25.field0.thePlayer, var1.method3(), 128.0F);
            if (var5 != null) {
               Class25.field1.clear();

               for(int var6 = 0; var6 < var5.getCurrentPathLength(); ++var6) {
                  Class25.field1.add(new Class228(var5.getVectorFromIndex(Class25.field0.thePlayer, var6)));
               }

               Class274.method0();
            }
         }
      }
   }

   public static int method0(byte[] var0, int var1) {
      int var2 = var1 + 2;

      for(int var3 = var0[var2++] & 255; var3 != 0 && var2 < var0.length; var3 = var0[var2++] & 255) {
         var2 += var3;
      }

      return var2;
   }

   public static void method0(int var0, int var1, Framebuffer var2, Consumer var3) {
      if (OpenGlHelper.isFramebufferEnabled()) {
         var0 = var2.framebufferTextureWidth;
         var1 = var2.framebufferTextureHeight;
      }

      int var4 = var0 * var1;
      int[] var5 = new int[var4];
      if (Class136.field2 == null || Class136.field2.capacity() < var4) {
         Class136.field2 = BufferUtils.createIntBuffer(var4);
      }

      GL11.glPixelStorei(3333, 1);
      GL11.glPixelStorei(3317, 1);
      Class136.field2.clear();
      if (OpenGlHelper.isFramebufferEnabled()) {
         GlStateManager.bindTexture(var2.framebufferTexture);
         GL11.glGetTexImage(3553, 0, 32993, 33639, Class136.field2);
      } else {
         GL11.glReadPixels(0, 0, var0, var1, 32993, 33639, Class136.field2);
      }

      (new Thread(Class136::lambda$getScreenshotImage$0)).start();
   }

   public static float method0() {
      PopupAnimationModule.Q_.method1((long)PopupAnimationModule.field6.method3()).method0(PopupAnimationModule.field5.method1());
      return PopupAnimationModule.Q_.method0();
   }
}
