package oringo.module;

import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class CakeNukerModule extends Module {
   public static final HashMap field0 = new HashMap();
   public static final DoubleSetting aB_ = new DoubleSetting("Distance", 6.0D, 1.0D, 6.0D, 0.1D);

   @SubscribeEvent
   public void method0(Class270 var1) {
      field0.clear();
   }

   public static ItemStack method0(C0EPacketClickWindow var0) {
      return var0.getClickedItem();
   }

   public CakeNukerModule() {
      super("Cake Nuker", Category.other, SubCategory.other, (String)null);
      this.method0((Setting[])(new Setting[]{aB_}));
   }

   public static void method0(double var0) {
      if (AutoReconnectModule.method1()) {
         double var2 = DadudzeModule.method3();
         Oringo.field9.thePlayer.motionX = -Math.sin(var2) * var0;
         Oringo.field9.thePlayer.motionZ = Math.cos(var2) * var0;
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.method44()) {
         BlockPos var2 = new BlockPos(field58.thePlayer.getPositionVector());
         int var3 = 0;
         Iterator var4 = BlockPos.getAllInBox(var2.add(-7, -7, -7), var2.add(7, 7, 7)).iterator();

         while(var4.hasNext()) {
            BlockPos var5 = (BlockPos)var4.next();
            IBlockState var6 = field58.theWorld.getBlockState(var5);
            if (var6.getBlock().equals(Blocks.cake) && field58.thePlayer.getDistanceSq((double)var5.getX(), (double)((float)var5.getY() - field58.thePlayer.getEyeHeight()), (double)var5.getZ()) < aB_.method0() * aB_.method0()) {
               Integer var7 = (Integer)field0.get(var5);
               int var8 = var7 == null ? (Integer)var6.getValue(BlockCake.BITES) : var7 + 1;
               if (var8 <= 7) {
                  if (var7 == null) {
                     field0.put(var5, var8);
                  } else {
                     field0.replace(var5, var8);
                  }

                  AutoRelayModule.method0(var5, EnumFacing.DOWN, new Vec3(var5));
                  ++var3;
                  if (var3 > 20) {
                     break;
                  }
               }
            }
         }
      }

   }

   public void b_() {
      field0.clear();
   }
}
