package map;

import com.google.common.collect.Lists;
import com.sun.jna.Pointer;
import java.nio.FloatBuffer;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.MemoryUtil;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;

public class Class278 {
   protected static final int field0;
   protected static final int field1;
   private static final FloatBuffer field4 = BufferUtils.createFloatBuffer(1536);
   protected static final VertexBuffer field2;
   private static final float[] field5;
   private static VertexBuffer field6;
   private static final long field7;
   private static final Class354 field8 = new Class354("Empty.frag", "Instanced.vert");
   protected static final VertexBuffer field3;

   public static void method0(int var0, VertexBuffer var1, int var2, List var3) {
      var1.bindBuffer();
      GL11.glEnableClientState(32884);
      GL11.glVertexPointer(3, 5126, 12, 0L);
      method0(var0, var2, var3);
      GL11.glDisableClientState(32884);
      var1.unbindBuffer();
   }

   public static void method0(int var0, WorldRenderer var1, List var2) {
      field6 = new VertexBuffer(DefaultVertexFormats.POSITION);
      field6.bufferData(var1.getByteBuffer());
      method0(var0, field6, var1.getVertexCount(), var2);
      var1.reset();
   }

   static {
      field7 = MemoryUtil.getAddress(field4);
      field5 = new float[1536];
      field6 = new VertexBuffer(DefaultVertexFormats.POSITION);
      field2 = new VertexBuffer(DefaultVertexFormats.POSITION);
      field3 = new VertexBuffer(DefaultVertexFormats.POSITION);
      Tessellator var0 = Tessellator.getInstance();
      WorldRenderer var1 = var0.getWorldRenderer();
      var1.begin(7, DefaultVertexFormats.POSITION);
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.finishDrawing();
      field3.bufferData(var1.getByteBuffer());
      field1 = var1.getVertexCount();
      var1.reset();
      var1.begin(7, DefaultVertexFormats.POSITION);
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 1.0D, 1.0D).endVertex();
      var1.pos(0.0D, 0.0D, 1.0D).endVertex();
      var1.pos(0.0D, 1.0D, 0.0D).endVertex();
      var1.pos(0.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 0.0D).endVertex();
      var1.pos(1.0D, 0.0D, 0.0D).endVertex();
      var1.pos(1.0D, 1.0D, 1.0D).endVertex();
      var1.pos(1.0D, 0.0D, 1.0D).endVertex();
      var1.finishDrawing();
      field2.bufferData(var1.getByteBuffer());
      field0 = var1.getVertexCount();
      var1.reset();
   }

   public static void method0(int var0, int var1, List var2) {
      Pointer var3 = new Pointer(field7);
      int var4 = 0;
      float[] var5 = field5;

      Vec3 var7;
      for(Iterator var6 = var2.iterator(); var6.hasNext(); var5[var4++] = (float)var7.zCoord) {
         var7 = (Vec3)var6.next();
         var5[var4++] = (float)var7.xCoord;
         var5[var4++] = (float)var7.yCoord;
      }

      var3.write(0L, var5, 0, 1536);
      method0(var0, var1, var2.size(), field4);
   }

   public static void method0(int var0, List var1) {
      field3.bindBuffer();
      GL11.glEnableClientState(32884);
      GL11.glVertexPointer(3, 5126, 12, 0L);
      Iterator var2 = Lists.partition(var1, 512).iterator();

      while(var2.hasNext()) {
         List var3 = (List)var2.next();
         method0(var0, field0, var3);
      }

      GL11.glDisableClientState(32884);
      field3.unbindBuffer();
   }

   public static void method0(int var0, int var1, int var2, FloatBuffer var3) {
      field8.method5();
      field8.method1("positions", var3);
      GL31.glDrawArraysInstanced(var0, 0, var1, var2);
      field8.method2();
   }
}
