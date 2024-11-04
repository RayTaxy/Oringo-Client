package map;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Class266 extends Class483 {
   public boolean field1;
   public final WorldRenderer field2 = new WorldRenderer(131072);
   public boolean field3;
   public ByteBuffer field4;
   public final VertexBuffer field5;

   public void method0(ArrayList var1) {
      this.field2.reset();
      this.field2.begin(7, DefaultVertexFormats.POSITION);
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         Vec3 var3 = (Vec3)var2.next();
         if (this.field1 && !var1.contains(var3.addVector(0.0D, -1.0D, 0.0D))) {
            this.field2.pos(var3.xCoord, var3.yCoord, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         }

         if (this.field1 && !var1.contains(var3.addVector(0.0D, 1.0D, 0.0D))) {
            this.field2.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         }

         if (this.field1 && !var1.contains(var3.addVector(-1.0D, 0.0D, 0.0D))) {
            this.field2.pos(var3.xCoord, var3.yCoord, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord, var3.zCoord).endVertex();
         }

         if (this.field1 && !var1.contains(var3.addVector(1.0D, 0.0D, 0.0D))) {
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         }

         if (this.field1 && !var1.contains(var3.addVector(0.0D, 0.0D, -1.0D))) {
            this.field2.pos(var3.xCoord, var3.yCoord, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord).endVertex();
         }

         if (this.field1 && !var1.contains(var3.addVector(0.0D, 0.0D, 1.0D))) {
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
            this.field2.pos(var3.xCoord, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         }
      }

      this.field2.finishDrawing();
      this.field4 = this.field2.getByteBuffer();
      this.field3 = false;
   }

   public Class266() {
      this.field5 = new VertexBuffer(DefaultVertexFormats.POSITION);
      this.field3 = false;
      this.field4 = null;
      this.field1 = true;
   }

   public void method0(int var1, int var2, VertexBuffer var3) {
      if (this.field4 != null) {
         this.field5.bufferData(this.field4);
         this.field4 = null;
      }

      if (this.field6 && !this.field3) {
         this.field6 = false;
         this.field3 = true;
         ArrayList var4 = new ArrayList(this.field7);
         (new Thread(this::lambda$render$0)).start();
      }

      this.field5.bindBuffer();
      GL11.glEnableClientState(32884);
      GL11.glVertexPointer(3, 5126, 12, 0L);
      this.field5.drawArrays(var1);
      this.field5.unbindBuffer();
      GL11.glDisableClientState(32884);
   }

   public void lambda$render$0(ArrayList var1) {
      this.method0(var1);
   }

   public List method0() {
      return this.field7;
   }
}
