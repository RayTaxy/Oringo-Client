package oringo.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import map.Class361;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class16;
import oringo.event.Class75;
import oringo.mixin.EntityPlayerSPAccessor;

public class AutoFrozilleModule extends Module {
   public boolean field0 = false;
   public final ArrayList aN_ = new ArrayList();
   public final Queue field2 = new LinkedList();

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field0) {
         this.field0 = false;
      } else {
         if (!this.aN_.isEmpty()) {
            List var2 = field58.theWorld.getEntities(EntityArmorStand.class, AutoFrozilleModule::lambda$onTick$0);
            Iterator var3 = this.aN_.iterator();

            while(var3.hasNext()) {
               BlockPos var4 = (BlockPos)var3.next();
               boolean var5 = false;
               Iterator var6 = var2.iterator();

               while(var6.hasNext()) {
                  EntityArmorStand var7 = (EntityArmorStand)var6.next();
                  if (var7.getDistanceSq(var4.down(1)) < 36.0D) {
                     var5 = true;
                     break;
                  }
               }

               if (var5) {
                  this.field2.add(var4);
               }
            }

            this.aN_.clear();
         }

         if (!this.field2.isEmpty()) {
            BlockPos var8 = (BlockPos)this.field2.poll();
            double var9 = this.method0(var8);
            if (field58.theWorld.getBlockState(var8).getBlock() == Blocks.ice && var9 <= 900.0D) {
               this.field2.add(var8);
               if (var9 <= 30.25D) {
                  Class361.method0((Class94)(new Class510(var8, EnumFacing.UP)));
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field21) {
         if (var1.field2.getBlock() == Blocks.ice && var1.field0.getBlock() == Blocks.air) {
            this.aN_.add(var1.cB_);
            this.field0 = true;
         }
      }
   }

   public AutoFrozilleModule() {
      super("Auto Frozille", Category.skyblock, SubCategory.rift, "Breaks Frozille's cage");
   }

   public double method0(double var1, double var3, double var5) {
      var1 -= ((EntityPlayerSPAccessor)field58.thePlayer).getLastReportedPosX();
      var3 -= ((EntityPlayerSPAccessor)field58.thePlayer).getLastReportedPosY();
      var5 -= ((EntityPlayerSPAccessor)field58.thePlayer).getLastReportedPosZ();
      return var1 * var1 + var3 * var3 + var5 * var5;
   }

   public static boolean lambda$onTick$0(EntityArmorStand var0) {
      return var0.getDisplayName().getUnformattedText().contains("BOOM");
   }

   public static Vec3 method0(float var0, float var1) {
      float var2 = MathHelper.cos(-var0 * 0.017453292F - 3.1415927F);
      float var3 = MathHelper.sin(-var0 * 0.017453292F - 3.1415927F);
      float var4 = -MathHelper.cos(-var1 * 0.017453292F);
      float var5 = MathHelper.sin(-var1 * 0.017453292F);
      return new Vec3((double)(var3 * var4), (double)var5, (double)(var2 * var4));
   }

   public double method0(BlockPos var1) {
      return this.method0((double)var1.getX() + 0.5D, (double)var1.getY() + 0.5D - 1.5D, (double)var1.getZ() + 0.5D);
   }
}
