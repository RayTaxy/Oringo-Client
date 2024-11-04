package map;

import java.util.Iterator;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

public class Class69 extends Class483 {
   public void method0(int var1, int var2, VertexBuffer var3) {
      var3.bindBuffer();
      GL11.glEnableClientState(32884);
      GL11.glVertexPointer(3, 5126, 12, 0L);
      Vec3 var4 = new Vec3(0.0D, 0.0D, 0.0D);

      Vec3 var6;
      for(Iterator var5 = this.field7.iterator(); var5.hasNext(); var4 = var6) {
         var6 = (Vec3)var5.next();
         GL11.glTranslated(var6.xCoord - var4.xCoord, var6.yCoord - var4.yCoord, var6.zCoord - var4.zCoord);
         GL11.glDrawArrays(var1, 0, var2);
      }

      GL11.glTranslated(-var4.xCoord, -var4.yCoord, -var4.zCoord);
      GL11.glDisableClientState(32884);
      var3.unbindBuffer();
   }
}
