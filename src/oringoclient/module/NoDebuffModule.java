package oringo.module;

import java.io.FileInputStream;
import map.Class315;
import net.minecraft.block.BlockAir;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class NoDebuffModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Blindness", true);
   public static final BooleanSetting bj_ = new BooleanSetting("Nausea", true);
   public static final BooleanSetting field2 = new BooleanSetting("Fire", true);

   public static ScaffoldModule.Class0 method3() {
      double var0 = 100.0D;
      EnumFacing var2 = null;
      BlockPos var3 = null;
      int var4 = (int)Math.ceil(ScaffoldModule.field16.method0());
      Vec3 var5 = ScaffoldModule.field58.thePlayer.getPositionVector();

      for(int var6 = -var4; var6 <= var4; ++var6) {
         for(int var7 = -var4; var7 <= var4; ++var7) {
            for(int var8 = -var4; var8 <= var4; ++var8) {
               Vec3 var9 = var5.addVector((double)var6, (double)var7, (double)var8);
               if (!(ScaffoldModule.field58.theWorld.getBlockState(new BlockPos(var9)).getBlock() instanceof BlockAir)) {
                  int var10;
                  Vec3 var11;
                  double var12;
                  BlockPos var14;
                  for(var10 = -1; var10 <= 1; var10 += 2) {
                     var11 = var9.addVector((double)var10, 0.0D, 0.0D);
                     var12 = ScaffoldModule.field58.thePlayer.getDistanceSq(var11.xCoord, var11.yCoord + 1.0D, var11.zCoord);
                     var14 = new BlockPos(var11);
                     if (var0 > var12 && ScaffoldModule.field58.theWorld.getBlockState(var14).getBlock() instanceof BlockAir) {
                        var0 = var12;
                        var3 = new BlockPos(var9);
                        var2 = EnumFacing.getFacingFromVector((float)var10, 0.0F, 0.0F);
                     }
                  }

                  for(var10 = -1; var10 <= 1; var10 += 2) {
                     var11 = var9.addVector(0.0D, (double)var10, 0.0D);
                     var12 = ScaffoldModule.field58.thePlayer.getDistanceSq(var11.xCoord, var11.yCoord + 1.0D, var11.zCoord);
                     var14 = new BlockPos(var11);
                     if (var0 > var12 && ScaffoldModule.field58.theWorld.getBlockState(var14).getBlock() instanceof BlockAir) {
                        var0 = var12;
                        var3 = new BlockPos(var9);
                        var2 = EnumFacing.getFacingFromVector(0.0F, (float)var10, 0.0F);
                     }
                  }

                  for(var10 = -1; var10 <= 1; var10 += 2) {
                     var11 = var9.addVector(0.0D, 0.0D, (double)var10);
                     var12 = ScaffoldModule.field58.thePlayer.getDistanceSq(var11.xCoord, var11.yCoord + 1.0D, var11.zCoord);
                     var14 = new BlockPos(var11);
                     if (var0 > var12 && ScaffoldModule.field58.theWorld.getBlockState(var14).getBlock() instanceof BlockAir) {
                        var0 = var12;
                        var3 = new BlockPos(var9);
                        var2 = EnumFacing.getFacingFromVector(0.0F, 0.0F, (float)var10);
                     }
                  }
               }
            }
         }
      }

      return var3 == null ? null : new ScaffoldModule.Class0(var3, var2);
   }

   @SubscribeEvent
   public void method0(RenderBlockOverlayEvent var1) {
      if (field2.method1()) {
         if (var1.overlayType == OverlayType.FIRE) {
            var1.setCanceled(true);
         }

      }
   }

   public NoDebuffModule() {
      super("No Debuff", Category.render, SubCategory.ui, "Removes bad potion effects");
      this.method0((Setting[])(new Setting[]{bj_, field0, field2}));
   }

   public static void method0(FileInputStream var0) throws Exception {
      CustomHubMap.field2 = 0;
      Class315.Class3 var1 = Class332.method0(var0);
      CustomHubMap.aD_ = new CustomHubMap.Class0[var1.method2()];

      for(int var2 = 0; var2 < var1.method2(); ++var2) {
         CustomHubMap.aD_[var2] = new CustomHubMap.Class0(CustomHubMap.field58.getTextureManager().getDynamicTextureLocation("customHubMap_" + var2, new DynamicTexture(var1.method0(var2))), var1.method1(var2) * 10);
      }

   }
}
