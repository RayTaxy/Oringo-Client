package oringo.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import map.Class28;
import map.Class447;
import map.Class461;
import map.Class496;
import net.minecraft.block.BlockColored;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.BrushCommand;
import oringo.event.Class16;
import oringo.event.Class420;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class GemstoneESPOldModule extends Module {
   public static final BooleanSetting field0;
   public static final BooleanSetting field1;
   public static final BooleanSetting field2;
   public static final BooleanSetting field3;
   public static final DoubleSetting field4;
   public static final HashMap field5;
   public static final DoubleSetting field6;
   public static final BooleanSetting field7;
   public static final HashMap field8 = new HashMap();
   public static final EnumSetting field9 = new EnumSetting("Mode", "Fill", new String[]{"Outline", "Fill"});
   public static final BooleanSetting field10 = new BooleanSetting("Extra range", false);
   public static final Class447 field11;
   public static final BooleanSetting field12;
   public static final BooleanSetting field13;

   @SubscribeEvent
   public void method0(Class420 var1) {
      if (var1.field0.getBlock(0, 189, 0) == Blocks.bedrock) {
         for(int var2 = 0; var2 < 16; ++var2) {
            for(int var3 = 0; var3 < 16; ++var3) {
               for(int var4 = 0; var4 < 188; ++var4) {
                  if (var1.field0.getBlock(var2, var4, var3) == Blocks.stained_glass) {
                     EnumDyeColor var5 = (EnumDyeColor)var1.field0.getBlockState(new BlockPos(var2, var4, var3)).getValue(BlockColored.COLOR);
                     Class461 var6 = (Class461)field8.get(var5);
                     var6.method0(new Vec3((double)(var1.field0.xPosition * 16 + var2), (double)var4, (double)(var1.field0.zPosition * 16 + var3)));
                  }
               }
            }
         }

      }
   }

   public static boolean lambda$onFill$0(Vec3 var0) {
      Chunk var1 = field58.theWorld.getChunkFromChunkCoords((int)(var0.xCoord / 16.0D), (int)(var0.zCoord / 16.0D));
      return var1 == null || !var1.isLoaded();
   }

   @SubscribeEvent
   public void method0(EntityJoinWorldEvent var1) {
      if (var1.entity == field58.thePlayer) {
         CinderbatESPModule.o_();
      }

   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field1) {
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableBlend();
         GlStateManager.disableTexture2D();
         GlStateManager.disableDepth();
         GlStateManager.disableAlpha();
         GlStateManager.depthMask(false);
         GlStateManager.disableLighting();
         GlStateManager.enableCull();
         BrushCommand.method3();
         Iterator var2 = field8.keySet().iterator();

         while(var2.hasNext()) {
            EnumDyeColor var3 = (EnumDyeColor)var2.next();
            if (HideSummonsModule.method0(var3)) {
               Class461 var4 = (Class461)field8.get(var3);
               if (!var4.F_()) {
                  float[] var5 = EntitySheep.getDyeRgb(var3);
                  GlStateManager.color(var5[0], var5[1], var5[2], (float)field6.method0());
                  List var6 = null;
                  if (!field10.method1()) {
                     var4.method1(VampireHelperModule.method0(var3, var6 = var4.method0()));
                  }

                  var4.method0(field10.method1());
                  var4.method2();
                  if (!field10.method1()) {
                     var4.method1(var6);
                  }
               }
            }
         }

         if (field11.a_(1000L)) {
            field11.o_();
         }

         Class28.method1();
         GlStateManager.enableAlpha();
         GlStateManager.enableTexture2D();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
         GlStateManager.enableLighting();
      }
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field1) {
         Class461 var2;
         if (var1.field2.getBlock() == Blocks.stained_glass) {
            var2 = (Class461)field8.get(var1.field2.getValue(BlockColored.COLOR));
            var2.method0(new Vec3(var1.cB_));
         } else if (var1.field0.getBlock() == Blocks.stained_glass) {
            var2 = (Class461)field8.get(var1.field0.getValue(BlockColored.COLOR));
            var2.method1(new Vec3(var1.cB_));
         }

         Iterator var4 = field8.values().iterator();

         while(var4.hasNext()) {
            Class461 var3 = (Class461)var4.next();
            var3.method0(GemstoneESPOldModule::lambda$onFill$0);
         }

      }
   }

   public GemstoneESPOldModule() {
      super("Gemstone ESP Old", Category.render, SubCategory.world, "Renders esp outlines on gemstones");
      this.method0((Setting[])(new Setting[]{field6, field9, field4, field10, field12, field1, field13, field0, field2, field7, field3}));
      CinderbatESPModule.o_();
   }

   static {
      BooleanSetting var10007 = field10;
      var10007.getClass();
      field4 = new DoubleSetting("Range", 64.0D, 16.0D, 128.0D, 1.0D, var10007::method1);
      field6 = new DoubleSetting("Alpha", 0.20000000298023224D, 0.05D, 1.0D, 0.01D);
      field12 = new BooleanSetting("Jasper ESP", true);
      field0 = new BooleanSetting("Amber ESP", true);
      field1 = new BooleanSetting("Ruby ESP", true);
      field13 = new BooleanSetting("Jade ESP", true);
      field2 = new BooleanSetting("Sapphire ESP", true);
      field7 = new BooleanSetting("Amethyst ESP", true);
      field3 = new BooleanSetting("Topaz ESP", true);
      field11 = new Class447();
      field5 = new HashMap();
   }
}
