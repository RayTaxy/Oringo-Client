package oringo.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import map.Class239;
import map.Class296;
import map.Class361;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class194;
import oringo.event.Class270;
import oringo.event.Class533;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class PinglessHardstoneModule extends Module {
   public final Class296 field0 = new Class296();
   public final BooleanSetting field1 = (BooleanSetting)(new BooleanSetting("Gold", false)).method2("For grinding gold collection in mines of divan, requires a lot of mining speed");
   public final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Prevent walking into blocks", true)).method2("Prevents creating ghost blocks by walking into hardstone");
   public final ConcurrentLinkedQueue field3 = new ConcurrentLinkedQueue();
   public IBlockState af_ = null;
   public final DoubleSetting field5 = (DoubleSetting)(new DoubleSetting("Mining Speed", 0.0D, 0.0D, 5.0D, 1.0D)).method2("Adjust until you stop creating ghost blocks");

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (Class496.field1) {
         if (this.field2.method1()) {
            BlockPos var2 = var1.field0;
            if (this.field3.contains(var2)) {
               var1.field1 = new AxisAlignedBB(var2, var2.add(1, 1, 1));
            }

         }
      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (Class496.field1) {
         if (var1.field0 instanceof S23PacketBlockChange) {
            this.field3.remove(((S23PacketBlockChange)var1.field0).getBlockPosition());
         }

      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class496.field1) {
         if (!(field58.thePlayer.openContainer instanceof ContainerChest)) {
            if (field58.gameSettings.keyBindAttack.isKeyDown()) {
               MovingObjectPosition var2 = field58.objectMouseOver;
               if (var2 != null && var2.typeOfHit == MovingObjectType.BLOCK) {
                  IBlockState var3 = field58.theWorld.getBlockState(var2.getBlockPos());
                  Block var4 = var3.getBlock();
                  if (var4 == Blocks.stone || var4 == Blocks.gold_block && this.field1.method1()) {
                     this.af_ = var3;
                     BlockPos var5 = var2.getBlockPos();
                     if (!this.field3.contains(var5)) {
                        this.field3.add(var5);
                        field58.theWorld.setBlockToAir(var5);
                     }
                  }
               }
            }

            if (!this.field3.isEmpty() && this.field0.method0((long)this.field5.method3(), true)) {
               if (Class533.method1()) {
                  BlockPos var7 = (BlockPos)this.field3.poll();
                  Class361.method0((Class94)(new Class510(var7, EnumFacing.UP)));
                  if (ThystHiderModule.method3().distanceSq(var7) < 33.0D) {
                     this.field3.add(var7);
                  } else {
                     field58.theWorld.setBlockState(var7, this.af_);
                  }

               } else {
                  Iterator var6 = this.field3.iterator();

                  while(var6.hasNext()) {
                     BlockPos var8 = (BlockPos)var6.next();
                     field58.theWorld.setBlockState(var8, this.af_);
                  }

                  this.field3.clear();
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field3.clear();
   }

   public PinglessHardstoneModule() {
      super("Pingless Hardstone", Category.skyblock, SubCategory.mining, "Helps you mine hardstone faster");
      this.method0((Setting[])(new Setting[]{this.field5, this.field1, this.field2}));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!this.field3.isEmpty() && Class496.field1) {
         GlStateManager.disableAlpha();
         GlStateManager.color(0.3F, 0.3F, 1.0F, 0.1F);
         LividFinderModule.method0(true, new ArrayList(this.field3));
         AutoCloseModule.method5();
         GlStateManager.enableAlpha();
      }
   }

   public static int z_(String var0) {
      return TrailModule.method0(Class239::lambda$getHotbarByID$2);
   }
}
