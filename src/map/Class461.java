package map;

import com.google.common.collect.Lists;
import com.sun.jna.Pointer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.MemoryUtil;
import org.lwjgl.opengl.GL11;

public class Class461 extends Class483 {
   public boolean field0;
   public ArrayList field1 = new ArrayList();
   public boolean field2 = false;

   public void method0(int var1, int var2, VertexBuffer var3) {
      if (!this.F_()) {
         if (this.field6) {
            if (this.field0) {
               if (!this.field2) {
                  (new Thread(this::method1)).start();
               }
            } else {
               this.method1();
            }

            this.field6 = false;
         }

         var3.bindBuffer();
         GL11.glEnableClientState(32884);
         GL11.glVertexPointer(3, 5126, 12, 0L);
         Iterator var4 = this.field1.iterator();

         while(var4.hasNext()) {
            Class461.Class0 var5 = (Class461.Class0)var4.next();
            Class278.method0(var1, var2, Class461.Class0.access$000(var5), Class461.Class0.access$100(var5));
         }

         GL11.glDisableClientState(32884);
         var3.unbindBuffer();
      }
   }

   public List method0() {
      return this.field7;
   }

   public Class461(boolean var1) {
      this.field0 = var1;
   }

   public void method1() {
      this.field2 = true;
      ArrayList var1 = this.field0 ? new ArrayList() : this.field1;
      var1.clear();
      Iterator var2 = Lists.partition(this.field7, 512).iterator();

      while(var2.hasNext()) {
         List var3 = (List)var2.next();
         var1.add(new Class461.Class0(var3));
      }

      if (this.field0) {
         this.field1 = var1;
      }

      this.field2 = false;
   }

   public void method0(boolean var1) {
      this.field0 = var1;
   }

   private static class Class0 {
      private final int field0;
      private final FloatBuffer field1;

      static int access$000(Class461.Class0 var0) {
         return var0.field0;
      }

      static FloatBuffer access$100(Class461.Class0 var0) {
         return var0.field1;
      }

      public Class0(List var1) {
         this.field0 = var1.size();
         this.field1 = BufferUtils.createFloatBuffer(this.field0 * 3);
         long var2 = MemoryUtil.getAddress(this.field1);
         float[] var4 = new float[this.field0 * 3];
         Pointer var5 = new Pointer(var2);
         int var6 = 0;

         Vec3 var8;
         for(Iterator var7 = var1.iterator(); var7.hasNext(); var4[var6++] = (float)var8.zCoord) {
            var8 = (Vec3)var7.next();
            var4[var6++] = (float)var8.xCoord;
            var4[var6++] = (float)var8.yCoord;
         }

         var5.write(0L, var4, 0, this.field0 * 3);
      }
   }
}
