package map;

import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import org.lwjgl.opengl.GL11;

public class Class479 {
   public static float field0;
   public static final Tessellator field1 = Tessellator.getInstance();
   public static VertexBuffer field2;
   public static final WorldRenderer field3;
   public static VertexFormat field4;

   static {
      field3 = field1.getWorldRenderer();
   }

   public static void method0() {
      field3.finishDrawing();
      if (field3.getVertexCount() > 0) {
         GL11.glDrawArrays(field3.getDrawMode(), 0, field3.getVertexCount());
      }

   }

   public static void method1() {
      VertexFormat var0 = field3.getVertexFormat();
      int var1 = var0.getNextOffset();
      ByteBuffer var2 = field3.getByteBuffer();
      List var3 = var0.getElements();
      int var4 = 0;

      for(int var5 = var3.size(); var4 < var5; ++var4) {
         VertexFormatElement var6 = (VertexFormatElement)var3.get(var4);
         var6.getUsage().postDraw(var0, var4, var1, var2);
      }

      field3.reset();
   }

   public static void method2() {
      VertexFormat var0 = field3.getVertexFormat();
      int var1 = var0.getNextOffset();
      ByteBuffer var2 = field3.getByteBuffer();
      List var3 = var0.getElements();

      for(int var4 = 0; var4 < var3.size(); ++var4) {
         VertexFormatElement var5 = (VertexFormatElement)var3.get(var4);
         var5.getUsage().preDraw(var0, var4, var1, var2);
      }

   }
}
