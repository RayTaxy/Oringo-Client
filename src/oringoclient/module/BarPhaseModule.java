package oringo.module;

import map.Class408;
import map.Class46;
import net.minecraft.block.BlockPane;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class194;
import oringo.event.Class210;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class BarPhaseModule extends Class408 {
   public int field0 = 0;
   public double bu_ = Double.MAX_VALUE;
   public boolean field2 = true;
   public double field3 = Double.MAX_VALUE;
   public final EnumSetting field4 = new EnumSetting("Trigger", "Keybind", new String[]{"Keybind", "Puzzle", "Always"});
   public final EnumSetting field5 = new EnumSetting("Mode", "Phase", new String[]{"Phase", "Clip"});

   public static float method0(Vec3 var0) {
      return BearAuraModule.method0(var0.xCoord, var0.yCoord, var0.zCoord);
   }

   public BarPhaseModule() {
      super("Bar Phase", Category.dungeon, SubCategory.main, "Lets you phase through thin blocks");
      this.method0((Setting[])(new Setting[]{this.field5, this.field4}));
   }

   public static boolean method3() {
      return OpenGlHelper.isFramebufferEnabled() && OpenGlHelper.areShadersSupported();
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      BlockPos var2;
      IBlockState var3;
      if (this.field5.method0(1)) {
         --this.field0;
         if (this.field0 > 0) {
            return;
         }

         if (this.field4.method0(0) && !this.method1()) {
            return;
         }

         if (this.field4.method0(1) && TerminalAuraModule.field0(Class46.field3)) {
            return;
         }

         var2 = new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY + 0.5D, field58.thePlayer.posZ);
         var3 = field58.theWorld.getBlockState(var2);
         if (var3.getBlock() != Blocks.iron_bars && var3.getBlock() != Blocks.glass_pane && var3.getBlock() != Blocks.stained_glass_pane) {
            this.field2 = true;
         } else {
            if (this.method0(var2, var3)) {
               return;
            }

            if (this.field2) {
               if (field58.thePlayer.isCollidedHorizontally) {
                  this.field0 = 0;
                  this.method0(var1, 0.061F);
                  this.field2 = false;
               }
            } else {
               this.method0(var1, 0.7F);
               this.field0 = 5;
               this.field2 = true;
            }
         }
      } else {
         if (!field58.thePlayer.onGround) {
            return;
         }

         if (this.field4.method0(0) && !this.method1()) {
            return;
         }

         if (this.field4.method0(1) && TerminalAuraModule.field0(Class46.field3)) {
            return;
         }

         var2 = new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY + 0.5D, field58.thePlayer.posZ);
         var3 = field58.theWorld.getBlockState(var2);
         if (var3.getBlock() == Blocks.iron_bars || var3.getBlock() == Blocks.glass_pane || var3.getBlock() == Blocks.stained_glass_pane) {
            if (this.method0(var2, var3)) {
               return;
            }

            AxisAlignedBB var4 = var3.getBlock().getCollisionBoundingBox(field58.theWorld, var2, var3);
            boolean var5;
            double var6;
            double var8;
            if (Math.abs(var4.minX - var4.maxX) <= 0.3D) {
               var5 = Math.abs(field58.thePlayer.posX - var4.minX) < Math.abs(field58.thePlayer.posX - var4.maxX);
               var6 = Math.min(Math.abs(field58.thePlayer.posX - var4.maxX), Math.abs(field58.thePlayer.posX - var4.minX));
               var8 = var5 ? var4.minX - 0.28D : var4.maxX + 0.28D;
               if (var6 < 0.4D) {
                  var1.j_ = var8;
                  this.field3 = var8;
               } else if (this.field3 != var8 && this.field3 != Double.MAX_VALUE) {
                  var1.j_ = var8;
                  this.field3 = Double.MAX_VALUE;
               }
            }

            if (Math.abs(var4.minZ - var4.maxZ) <= 0.3D) {
               var5 = Math.abs(field58.thePlayer.posZ - var4.minZ) < Math.abs(field58.thePlayer.posZ - var4.maxZ);
               var6 = Math.min(Math.abs(field58.thePlayer.posZ - var4.maxZ), Math.abs(field58.thePlayer.posZ - var4.minZ));
               var8 = var5 ? var4.minZ - 0.28D : var4.maxZ + 0.28D;
               if (var6 < 0.4D) {
                  var1.field4 = var8;
                  this.bu_ = var8;
               } else if (this.bu_ != var8 && this.bu_ != Double.MAX_VALUE) {
                  var1.field4 = var8;
                  this.bu_ = Double.MAX_VALUE;
               }
            }
         }
      }

   }

   public void method0(Class210.Class1 var1, float var2) {
      double var3 = Math.toRadians((double)PacketLoggerModule.method5());
      field58.thePlayer.setPosition(field58.thePlayer.posX + -Math.sin(var3) * (double)var2, field58.thePlayer.posY, field58.thePlayer.posZ + Math.cos(var3) * (double)var2);
      var1.method0(field58.thePlayer.getPositionVector());
      field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
   }

   public boolean method0(BlockPos var1, IBlockState var2) {
      BlockPane var3 = (BlockPane)var2.getBlock();
      if (!var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.NORTH) && !var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.SOUTH) || !var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.WEST) && !var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.EAST)) {
         return !var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.NORTH) && !var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.SOUTH) && !var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.WEST) && !var3.canPaneConnectTo(field58.theWorld, var1, EnumFacing.EAST);
      } else {
         return true;
      }
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (var1.field2 instanceof BlockPane) {
         if (!this.field5.method0(1) && field58.thePlayer != null && field58.thePlayer.onGround) {
            if (!this.field4.method0(0) || this.method1()) {
               if (!this.field4.method0(1) || !TerminalAuraModule.field0(Class46.field3)) {
                  BlockPos var2 = new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY + 0.5D, field58.thePlayer.posZ);
                  IBlockState var3 = field58.theWorld.getBlockState(var2);
                  if (var3.getBlock() instanceof BlockPane) {
                     if (!this.method0(var1.field0, field58.theWorld.getBlockState(var1.field0))) {
                        AxisAlignedBB var4 = var3.getBlock().getCollisionBoundingBox(field58.theWorld, var2, var3);
                        boolean var5 = false;
                        if ((var3.getBlock() == Blocks.glass_pane || var3.getBlock() == Blocks.iron_bars || var3.getBlock() == Blocks.stained_glass_pane) && var4.intersectsWith(field58.thePlayer.getEntityBoundingBox())) {
                           var5 = true;
                        }

                        if (field58.thePlayer.isCollidedHorizontally || var5) {
                           var1.method9();
                        }

                     }
                  }
               }
            }
         }
      }
   }
}
