package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import map.Class256;
import map.Class263;
import map.Class34;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStone.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.ReplyCommand;
import oringo.event.Class16;
import oringo.event.Class210;
import oringo.event.Class255;
import oringo.event.Class290;

public class MithrilMacroModule2 extends Module {
   public Vec3 field0;
   public BlockPos ba_;
   public BlockPos field2;

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      this.ba_ = this.method0(this.field2);
      if (this.ba_ != null) {
         BlockPos var2 = this.method0(this.ba_);
         if (var2 == null) {
            this.field0 = (new Vec3(this.ba_)).addVector(0.5D, 0.5D, 0.5D);
         } else {
            this.field0 = (new Vec3(this.ba_)).subtract(new Vec3(var2)).normalize();
         }

         Vec3 var3 = field58.thePlayer.getLookVec().add(field58.thePlayer.getPositionEyes(0.0F));
         Vec3 var4 = new Vec3(var3.xCoord + (this.field0.xCoord - var3.xCoord) / 20.0D, var3.yCoord + (this.field0.yCoord - var3.yCoord) / 20.0D, var3.zCoord + (this.field0.zCoord - var3.zCoord) / 20.0D);
         Class34 var5 = ReplyCommand.method0(var4);
         field58.thePlayer.rotationYaw = var5.method5();
         field58.thePlayer.rotationPitch = var5.method2();
         var1.method0(Class256.method1());
      }
   }

   public MithrilMacroModule2() {
      super("Mithril Macro", Category.skyblock, SubCategory.skills);
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.ba_ != null) {
         AutoMaskModule.method0(this.ba_, Color.CYAN);
         AutoReadyModule.method0(this.field0, Color.CYAN);
      }
   }

   @SubscribeEvent
   public void field0(Class255 var1) {
      if (this.ba_ != null) {
         var1.method9();
      }
   }

   public static boolean lambda$findTarget$0(BlockPos var0, BlockPos var1, ICamera var2, BlockPos var3) {
      if (!var3.equals(var0) && !var3.equals(var1)) {
         IBlockState var4 = field58.theWorld.getBlockState(var3);
         MithrilMacroModule2.Class0 var5 = MithrilMacroModule2.Class0.access$000(var4);
         if (var5 == MithrilMacroModule2.Class0.field4) {
            return false;
         } else {
            return field58.theWorld.rayTraceBlocks(field58.thePlayer.getPositionEyes(1.0F), (new Vec3(var3)).addVector(0.5D, 0.5D, 0.5D)) == null && var2.isBoundingBoxInFrustum(var4.getBlock().getSelectedBoundingBox(field58.theWorld, var3));
         }
      } else {
         return false;
      }
   }

   public static ProfilerModule.Class0 method5() {
      return ProfilerModule.cE_;
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (this.ba_ != null && this.field0 != null) {
         IBlockState var2 = field58.theWorld.getBlockState(this.ba_);
         MovingObjectPosition var3 = var2.getBlock().getSelectedBoundingBox(field58.theWorld, this.ba_).calculateIntercept(field58.thePlayer.getPositionEyes(1.0F), this.field0);
         if (var3 != null && var3.sideHit != null) {
            field58.playerController.onPlayerDamageBlock(this.ba_, var3.sideHit);
            field58.thePlayer.swingItem();
         }
      }
   }

   public static int method6() {
      int var0 = Class263.bq_;
      Class263.bq_ = -1;
      return var0;
   }

   public BlockPos method0(BlockPos var1) {
      BlockPos var2 = new BlockPos(field58.thePlayer);
      ArrayList var3 = new ArrayList();

      for(int var4 = -5; var4 <= 5; ++var4) {
         for(int var5 = -5; var5 <= 5; ++var5) {
            for(int var6 = -5; var6 <= 5; ++var6) {
               var3.add(var2.add(var4, var5, var6));
            }
         }
      }

      ICamera var7 = Class290.method0();
      BlockPos var8 = var1 != null ? var1 : var2;
      Stream var10000 = var3.stream().filter(MithrilMacroModule2::lambda$findTarget$0);
      var8.getClass();
      Optional var9 = var10000.min(Comparator.comparingDouble(var8::func_177951_i));
      return (BlockPos)var9.orElse((Object)null);
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (var1.cB_.equals(this.ba_)) {
         this.field2 = this.ba_;
         this.ba_ = null;
      }
   }

   private static enum Class0 {
      field0(MithrilMacroModule2.Class0::lambda$static$4),
      field1(MithrilMacroModule2.Class0::lambda$static$3),
      field2(MithrilMacroModule2.Class0::lambda$static$2);

      private final Predicate field5;
      field3(MithrilMacroModule2.Class0::lambda$static$1),
      field4(MithrilMacroModule2.Class0::lambda$static$0);

      private static final MithrilMacroModule2.Class0[] field6 = new MithrilMacroModule2.Class0[]{field4, field3, field2, field1, field0};

      static MithrilMacroModule2.Class0 access$000(IBlockState var0) {
         return method5(var0);
      }

      private static boolean lambda$static$0(IBlockState var0) {
         return false;
      }

      private static boolean lambda$static$1(IBlockState var0) {
         return (var0.getBlock() == Blocks.wool || var0.getBlock() == Blocks.stained_hardened_clay) && var0.getValue(BlockColored.COLOR) == EnumDyeColor.GRAY;
      }

      private static boolean lambda$static$2(IBlockState var0) {
         return var0.getBlock() == Blocks.wool && var0.getValue(BlockColored.COLOR) == EnumDyeColor.LIGHT_BLUE;
      }

      private static boolean lambda$static$3(IBlockState var0) {
         return var0.getBlock() == Blocks.prismarine;
      }

      private static MithrilMacroModule2.Class0 method5(IBlockState var0) {
         MithrilMacroModule2.Class0[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            MithrilMacroModule2.Class0 var4 = var1[var3];
            if (var4.field5.test(var0)) {
               return var4;
            }
         }

         return field4;
      }

      private Class0(Predicate var3) {
         this.field5 = var3;
      }

      private static boolean lambda$static$4(IBlockState var0) {
         return var0.getBlock() == Blocks.stone && ((EnumType)var0.getValue(BlockStone.VARIANT)).equals(EnumType.DIORITE_SMOOTH);
      }
   }
}
