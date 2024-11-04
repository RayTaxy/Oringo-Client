package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import map.Class361;
import map.Class447;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class16;
import oringo.event.Class274;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class RiftFarmingHelperModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Quick caducous", true);
   public final BooleanSetting bo_ = new BooleanSetting("Wilted berberis nuker", true);
   public final Class447 field2 = new Class447();
   public final ArrayList field3 = new ArrayList();
   public final BooleanSetting field4 = new BooleanSetting("Wilted berberis ESP", true);
   public boolean field5 = false;
   public final BooleanSetting field6 = new BooleanSetting("Auto agaricus", true);

   public boolean lambda$onBlockChange$0(BlockPos var1) {
      return field58.thePlayer.getDistanceSq(var1) > 225.0D || this.field3.indexOf(var1) == 0 && field58.theWorld.getBlockState(var1).getBlock() != Blocks.deadbush;
   }

   public static boolean method0(double var0) {
      return !Oringo.field9.theWorld.getCollidingBoundingBoxes(Oringo.field9.thePlayer, Oringo.field9.thePlayer.getEntityBoundingBox().offset(0.0D, -var0, 0.0D)).isEmpty();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field5) {
         if (field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            Class361.method0((Class94)(new Class510(field58.objectMouseOver.getBlockPos(), field58.objectMouseOver.sideHit)));
         }

         this.field5 = false;
      }

      if (this.bo_.method1()) {
         if (this.field2.a_(1000L) && !this.field3.isEmpty()) {
            BlockPos var2 = (BlockPos)this.field3.get(0);
            double var3 = field58.thePlayer.getDistanceSq((double)var2.getX() + 0.5D, (double)var2.getY() + 0.5D - 1.5D, (double)var2.getZ() + 0.5D);
            if (var3 > 121.0D) {
               this.field3.clear();
            }

            if (var3 <= 30.25D) {
               this.field3.remove(0);
               if (field58.theWorld.getBlockState(var2).getBlock() == Blocks.deadbush) {
                  Class361.method0((Class94)(new Class510(var2, EnumFacing.UP)));
               }
            }
         }
      }
   }

   public RiftFarmingHelperModule() {
      super("Rift Farming Helper", Category.skyblock, SubCategory.rift, "Helps you with farming in the rift");
      this.method0((Setting[])(new Setting[]{this.field0, this.field6, this.field4, this.bo_}));
   }

   public boolean method1(BlockPos var1) {
      IBlockState var2 = field58.theWorld.getBlockState(var1);
      return var2.getBlock() == Blocks.double_plant;
   }

   public void b_() {
      this.field3.clear();
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (!Class496.field21) {
         this.field3.clear();
      } else {
         if (this.bo_.method1() || this.field4.method1()) {
            this.field3.removeIf(this::lambda$onBlockChange$0);
            if (var1.field0.getBlock() == Blocks.deadbush && var1.field2.getBlock() == Blocks.air && this.field3.indexOf(var1.cB_) == 0) {
               this.field3.remove(0);
            }

            if (field58.thePlayer.getDistanceSq(var1.cB_.down(2)) < 100.0D && !this.field3.contains(var1.cB_) && var1.field2.getBlock() == Blocks.deadbush && var1.field0.getBlock() == Blocks.air) {
               this.field2.o_();
               this.field3.add(var1.cB_);
            }
         }

         if (this.field6.method1() && field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK && var1.cB_.equals(field58.objectMouseOver.getBlockPos())) {
            if (var1.field0.getBlock() != Blocks.brown_mushroom || var1.field2.getBlock() != Blocks.red_mushroom) {
               return;
            }

            this.field5 = true;
         }

      }
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field4.method1()) {
         Color var2 = Color.GREEN;

         for(Iterator var3 = this.field3.iterator(); var3.hasNext(); var2 = var2.darker()) {
            BlockPos var4 = (BlockPos)var3.next();
            AutoMaskModule.method0(var4, var2);
         }

      }
   }
}
