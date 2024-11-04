package oringo.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import map.Class239;
import map.Class266;
import map.Class28;
import map.Class297;
import map.Class359;
import map.Class447;
import map.Class465;
import map.Class496;
import net.minecraft.block.BlockColored;
import net.minecraft.client.multiplayer.ChunkProviderClient;
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
import oringo.mixin.ChunkProviderClientAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class GemstoneESPModule extends Module {
   public static final HashMap field0 = new HashMap();
   public static final DoubleSetting C_;
   public static final BooleanSetting field2;
   public static final BooleanSetting field3;
   public static final DoubleSetting field4;
   public static final Class447 field5;
   public static final BooleanSetting field6;
   public static final BooleanSetting field7;
   public static final BooleanSetting field8;
   public static final BooleanSetting field9 = new BooleanSetting("Extra range", false);
   public static final HashMap field10;
   public static final BooleanSetting field11;
   public static final BooleanSetting field12;

   public void method4() {
      Class297.o_();
      if (field58.theWorld != null && Class496.field1) {
         if (field58.theWorld.getChunkProvider() instanceof ChunkProviderClient) {
            Iterator var1 = ((ChunkProviderClientAccessor)field58.theWorld.getChunkProvider()).getChunkListing().iterator();

            while(true) {
               Chunk var2;
               do {
                  do {
                     if (!var1.hasNext()) {
                        return;
                     }

                     var2 = (Chunk)var1.next();
                  } while(var2.isEmpty());
               } while(var2.getBlock(0, 189, 0) != Blocks.bedrock);

               for(int var3 = 0; var3 < 16; ++var3) {
                  for(int var4 = 0; var4 < 16; ++var4) {
                     for(int var5 = 0; var5 < 188; ++var5) {
                        if (var2.getBlock(var3, var5, var4) == Blocks.stained_glass) {
                           EnumDyeColor var6 = (EnumDyeColor)var2.getBlockState(new BlockPos(var3, var5, var4)).getValue(BlockColored.COLOR);
                           Class266 var7 = (Class266)field0.get(var6);
                           var7.method0(new Vec3((double)(var2.xPosition * 16 + var3), (double)var5, (double)(var2.zPosition * 16 + var4)));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public GemstoneESPModule() {
      super("Gemstone ESP", Category.render, SubCategory.mining, "Renders esp boxes on gemstones");
      this.method0((Setting[])(new Setting[]{C_, field9, field4, field11, field2, field7, field8, field6, field12, field3}));
      Class297.o_();
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field1) {
         Class266 var2;
         if (var1.field2.getBlock() == Blocks.stained_glass) {
            var2 = (Class266)field0.get(var1.field2.getValue(BlockColored.COLOR));
            var2.method0(new Vec3(var1.cB_));
         } else if (var1.field0.getBlock() == Blocks.stained_glass) {
            var2 = (Class266)field0.get(var1.field0.getValue(BlockColored.COLOR));
            var2.method1(new Vec3(var1.cB_));
         }

         Iterator var4 = field0.values().iterator();

         while(var4.hasNext()) {
            Class266 var3 = (Class266)var4.next();
            var3.method0(GemstoneESPModule::lambda$onFill$0);
         }

      }
   }

   public static boolean lambda$onFill$0(Vec3 var0) {
      Chunk var1 = field58.theWorld.getChunkFromChunkCoords((int)(var0.xCoord / 16.0D), (int)(var0.zCoord / 16.0D));
      return var1 == null || !var1.isLoaded();
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
         Iterator var2 = field0.keySet().iterator();

         while(var2.hasNext()) {
            EnumDyeColor var3 = (EnumDyeColor)var2.next();
            if (Class465.method0(var3)) {
               Class266 var4 = (Class266)field0.get(var3);
               if (!var4.F_()) {
                  float[] var5 = EntitySheep.getDyeRgb(var3);
                  GlStateManager.color(var5[0], var5[1], var5[2], (float)C_.method0());
                  List var6 = null;
                  if (!field9.method1()) {
                     var4.method1(Class359.method0(var3, var6 = var4.method0()));
                  }

                  var4.method2();
                  if (!field9.method1()) {
                     var4.method1(var6);
                  }
               }
            }
         }

         if (field5.a_(1000L)) {
            field5.o_();
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

   static {
      BooleanSetting var10007 = field9;
      var10007.getClass();
      field4 = new DoubleSetting("Range", 64.0D, 16.0D, 128.0D, 1.0D, var10007::method1);
      C_ = new DoubleSetting("Alpha", 0.20000000298023224D, 0.05D, 1.0D, 0.01D);
      field11 = new BooleanSetting("Jasper ESP", true);
      field8 = new BooleanSetting("Amber ESP", true);
      field2 = new BooleanSetting("Ruby ESP", true);
      field7 = new BooleanSetting("Jade ESP", true);
      field6 = new BooleanSetting("Sapphire ESP", true);
      field12 = new BooleanSetting("Amethyst ESP", true);
      field3 = new BooleanSetting("Topaz ESP", true);
      field5 = new Class447();
      field10 = new HashMap();
   }

   @SubscribeEvent
   public void method0(Class420 var1) {
      if (var1.field0.getBlock(0, 189, 0) == Blocks.bedrock) {
         for(int var2 = 0; var2 < 16; ++var2) {
            for(int var3 = 0; var3 < 16; ++var3) {
               for(int var4 = 0; var4 < 188; ++var4) {
                  if (var1.field0.getBlock(var2, var4, var3) == Blocks.stained_glass) {
                     EnumDyeColor var5 = (EnumDyeColor)var1.field0.getBlockState(new BlockPos(var2, var4, var3)).getValue(BlockColored.COLOR);
                     Class266 var6 = (Class266)field0.get(var5);
                     var6.method0(new Vec3((double)(var1.field0.xPosition * 16 + var2), (double)var4, (double)(var1.field0.zPosition * 16 + var3)));
                  }
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(EntityJoinWorldEvent var1) {
      if (var1.entity == field58.thePlayer) {
         Class297.o_();
      }

   }

   public static void method0(int var0, boolean var1, boolean var2) {
      BlockNukerModule.method0(var0, Class239::lambda$doFromInv$0, var2);
   }
}
