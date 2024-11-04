package oringo.command;

import com.google.common.base.Predicates;
import java.util.Iterator;
import java.util.List;
import map.Class168;
import map.Class447;
import map.Class82;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.module.BearAuraModule;

public class WardrobeCommand extends Command {
   public final Class447 field0 = new Class447();
   public int al_ = -1;

   public void method0(String[] var1) {
      field9.getNetHandler().getNetworkManager().sendPacket(new C01PacketChatMessage("/wd"));
      if (var1.length > 0) {
         this.al_ = Math.min(Math.max(Integer.parseInt(var1[0]), 1), 18);
         this.field0.o_();
      }

   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.al_ != -1 && var1.field0 instanceof S2DPacketOpenWindow) {
         if (this.field0.a_(1500L)) {
            this.al_ = -1;
            return;
         }

         if (((S2DPacketOpenWindow)var1.field0).getWindowTitle().getUnformattedText().startsWith("Wardrobe")) {
            int var2 = ((S2DPacketOpenWindow)var1.field0).getWindowId();
            byte var3 = 1;
            if (this.al_ > 9) {
               field9.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(var2, 53, 0, 3, (ItemStack)null, var3));
               this.al_ -= 9;
            }

            field9.getNetHandler().getNetworkManager().sendPacket(new C0EPacketClickWindow(var2, 35 + this.al_, 0, 0, (ItemStack)null, var3));
            field9.getNetHandler().getNetworkManager().sendPacket(new C0DPacketCloseWindow(var2));
            var1.setCanceled(true);
            this.al_ = -1;
         }
      }

   }

   public static boolean method0(Entity var0, Vec3 var1) {
      if (!BearAuraModule.field58.thePlayer.canEntityBeSeen(var0)) {
         return false;
      } else {
         float var2 = 1.0F;
         double var3 = BearAuraModule.field58.thePlayer.getDistance(var0.posX, var0.posY + (double)var0.getEyeHeight() / 2.0D - (double)BearAuraModule.field58.thePlayer.getEyeHeight(), var0.posZ);
         List var5 = BearAuraModule.field58.theWorld.getEntitiesInAABBexcluding(BearAuraModule.field58.thePlayer, BearAuraModule.field58.thePlayer.getEntityBoundingBox().addCoord(var1.xCoord * var3, var1.yCoord * var3, var1.zCoord * var3).expand((double)var2, (double)var2, (double)var2), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::func_70067_L));
         Vec3 var6 = BearAuraModule.field58.thePlayer.getPositionEyes(1.0F);
         Vec3 var7 = var6.addVector(var1.xCoord * var3, var1.yCoord * var3, var1.zCoord * var3);
         Iterator var8 = var5.iterator();

         AxisAlignedBB var11;
         MovingObjectPosition var12;
         do {
            Entity var9;
            do {
               if (!var8.hasNext()) {
                  return true;
               }

               var9 = (Entity)var8.next();
            } while(var9 == var0);

            float var10 = var9.getCollisionBorderSize();
            var11 = var9.getEntityBoundingBox().expand((double)var10, (double)var10, (double)var10);
            var12 = var11.calculateIntercept(var6, var7);
         } while(!var11.isVecInside(var6) && var12 == null);

         return false;
      }
   }

   public String method1() {
      return "Instant wardrobe";
   }

   public static boolean i_(String var0) {
      return Class82.field0.equalsIgnoreCase(var0);
   }

   public static void method0(IBakedModel var0) {
      Class168.field0.begin(7, DefaultVertexFormats.ITEM);
      EnumFacing[] var1 = EnumFacing.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EnumFacing var4 = var1[var3];
         XRayCommand.method0(var0.getFaceQuads(var4));
      }

      XRayCommand.method0(var0.getGeneralQuads());
      Class168.field1.draw();
   }

   public WardrobeCommand() {
      super("wd", "wardrobe");
   }
}
