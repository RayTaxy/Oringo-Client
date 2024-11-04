package map;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Vec3;
import oringo.module.MinigameAimbotModule;

public class Class118 extends Class266 {
   public final WorldRenderer field0 = new WorldRenderer(131072);

   public static boolean method0(EntityOtherPlayerMP var0) {
      return var0.canEntityBeSeen(MinigameAimbotModule.field58.thePlayer);
   }

   public void method0(ArrayList var1) {
      this.field6 = false;
      this.field0.reset();
      this.field0.begin(1, DefaultVertexFormats.POSITION);
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         Vec3 var3 = (Vec3)var2.next();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord + 1.0D, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord).endVertex();
         this.field0.pos(var3.xCoord, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord + 1.0D).endVertex();
         this.field0.pos(var3.xCoord + 1.0D, var3.yCoord, var3.zCoord).endVertex();
      }

      this.field0.finishDrawing();
      this.field4 = this.field0.getByteBuffer();
      this.field3 = false;
   }
}
