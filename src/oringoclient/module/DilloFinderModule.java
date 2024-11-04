package oringo.module;

import java.awt.Color;
import java.awt.Toolkit;
import map.Class447;
import map.Class496;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.BlockStoneSlab.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class420;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class DilloFinderModule extends Module {
   public boolean field0;
   public final EnumSetting field1 = new EnumSetting("Hollows warp", "Crystals", new String[]{"Nucleus", "Crystals"});
   public final BooleanSetting field2 = new BooleanSetting("Tracers", true);
   public final BooleanSetting field3 = new BooleanSetting("Notify", true);
   public final ButtonSetting field4 = new ButtonSetting("Macro", this::method5);
   public final BooleanSetting field5 = new BooleanSetting("Share spots", false);
   public final Class447 field6;
   public final EnumSetting field7 = new EnumSetting("Mines warp", "Mines", new String[]{"Forge", "Mines"});
   public final Block[] field8;
   public static BlockPos cB_ = null;

   public static void method0(int var0) {
      DivanChestFinderModule.method0(var0, false);
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (cB_ != null) {
         AutoMaskModule.method0(cB_.down(), Color.CYAN);
         if (this.field2.method1()) {
            AutoArrowModule.method0((double)cB_.getX() + 0.5D, (double)cB_.getY() - 0.5D, (double)cB_.getZ() + 0.5D, Color.CYAN);
         }
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      cB_ = null;
   }

   public DilloFinderModule() {
      super("Dillo Finder", Category.skyblock, SubCategory.mining, "Finds the dillo structures");
      this.field8 = new Block[]{Blocks.double_stone_slab, Blocks.stone_slab, Blocks.double_stone_slab, Blocks.double_stone_slab, Blocks.stone_brick_stairs};
      this.field6 = new Class447();
      this.field0 = false;
      this.method0((Setting[])(new Setting[]{this.field3, this.field2, this.field5, this.field4, this.field7, this.field1}));
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field0) {
         this.field0 = cB_ == null;
         if (!this.field0) {
            Toolkit.getDefaultToolkit().beep();
            method2("Dillo structure has been found!");
         } else if (Keyboard.isKeyDown(211)) {
            method2("Macro has been forcefully stopped by clicking the delete key");
            this.field0 = false;
         } else {
            if (this.field6.a_(3500L)) {
               if (!Class496.field1) {
                  field58.thePlayer.sendChatMessage("/warp " + this.field1.method4());
                  this.field6.o_();
               } else if (field58.thePlayer.posX >= 460.0D && field58.thePlayer.posZ >= 460.0D) {
                  if (this.field6.a_(7000L)) {
                     field58.thePlayer.sendChatMessage("/warp " + this.field7.method4());
                     this.field6.o_();
                  }
               } else {
                  field58.thePlayer.sendChatMessage("/warp " + this.field7.method4());
                  this.field6.o_();
               }
            }

         }
      }
   }

   public void method5() {
      this.field6.o_();
      this.field0 = !this.field0;
      if (this.field0) {
         cB_ = null;
         field58.thePlayer.sendChatMessage("/warp " + this.field7.method4());
      }

   }

   @SubscribeEvent
   public void method0(Class420 var1) {
      if (cB_ == null) {
         int var2 = var1.field0.xPosition * 16;
         int var3 = var1.field0.zPosition * 16;
         if (var2 >= 512 && var3 >= 512) {
            Chunk var4 = var1.field0;
            if (var4.getBlock(0, 189, 0) == Blocks.bedrock) {
               for(int var5 = 0; var5 < 16; ++var5) {
                  for(int var6 = 0; var6 < 16; ++var6) {
                     label65:
                     for(int var7 = 0; var7 < 100; ++var7) {
                        Block var8 = var4.getBlock(var5, 170 - var7, var6);
                        IBlockState var9 = var4.getBlockState(new BlockPos(var5, 170 - var7, var6));
                        if (var8 == this.field8[1] && var9.getProperties().containsKey(BlockSlab.HALF) && var9.getProperties().get(BlockSlab.HALF) == EnumBlockHalf.TOP && var9.getProperties().containsKey(BlockStoneSlab.VARIANT) && var9.getProperties().get(BlockStoneSlab.VARIANT) == EnumType.STONE) {
                           int var10 = -1;
                           Block[] var11 = this.field8;
                           int var12 = var11.length;

                           for(int var13 = 0; var13 < var12; ++var13) {
                              Block var14 = var11[var13];
                              if (var4.getBlock(var5, 170 - var7 - var10, var6) != var14) {
                                 continue label65;
                              }

                              ++var10;
                           }

                           cB_ = new BlockPos(var5 + var2, 170 - var7, var6 + var3);
                           if (this.field3.method1()) {
                              method2(String.format("Found dillo structure at §a%s, %s, %s§f!", var5 + var2, 170 - var7, var6 + var3));
                              SecretHitboxesModule.method0("Dillo found!", 2500);
                           }

                           return;
                        }
                     }
                  }
               }

            }
         }
      }
   }
}
