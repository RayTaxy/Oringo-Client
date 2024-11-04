package oringo.module;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import map.Class457;
import map.Class496;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.setting.BooleanSetting;
import oringo.setting.ColorSetting;
import oringo.setting.Setting;

public class NucleusHelperModule extends Module {
   public static final List field0;
   private final BooleanSetting field1 = new BooleanSetting("Tracers", false);

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S21PacketChunkData) {
         int var2 = ((S21PacketChunkData)var1.field0).getChunkX() * 16;
         int var3 = ((S21PacketChunkData)var1.field0).getChunkZ() * 16;
         byte[] var4 = ((S21PacketChunkData)var1.field0).getExtractedDataBytes();
         if (var4.length <= 96768 || var4[88576] != 112) {
            return;
         }

         ArrayList var5 = new ArrayList();
         Iterator var6 = field0.iterator();

         label113:
         while(true) {
            NucleusHelperModule.Class0 var7;
            do {
               if (!var6.hasNext()) {
                  if (var5.isEmpty()) {
                     return;
                  }

                  int var15 = 73728;

                  for(int var16 = 0; var16 < 16; ++var16) {
                     for(int var8 = 0; var8 < 16; ++var8) {
                        NucleusHelperModule.Class0 var10;
                        for(Iterator var9 = var5.iterator(); var9.hasNext(); var10.field8 = 0) {
                           var10 = (NucleusHelperModule.Class0)var9.next();
                        }

                        boolean var17 = true;

                        label96:
                        for(int var18 = 160; var18 > 45; --var18) {
                           int var11 = ((var4[var15 + 1] & 255) << 8 | var4[var15] & 255) >> 4;
                           var15 -= 512;
                           if (!var17 || var11 != 1 && var11 != 0 && var11 != 159 && var11 != 16 && var11 != 15) {
                              Iterator var12 = var5.iterator();

                              while(true) {
                                 while(true) {
                                    if (!var12.hasNext()) {
                                       continue label96;
                                    }

                                    NucleusHelperModule.Class0 var13 = (NucleusHelperModule.Class0)var12.next();
                                    if (var13.field0[var13.field8] != var11) {
                                       if (var13.field8 != 0) {
                                          var17 = true;
                                       }

                                       var13.field8 = 0;
                                    } else if (var13.field3 || var13.field7.isEmpty()) {
                                       var17 = false;
                                       if (++var13.field8 == var13.field0.length) {
                                          var17 = true;
                                          var13.field8 = 0;
                                          BlockPos var14 = new BlockPos(var2 + var16, var18 + var13.field5.length - 1, var3 + var8);
                                          if (!var13.field7.contains(var14)) {
                                             var13.method0(var14);
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }

                        var15 += 32;
                        var15 += 58880;
                     }

                     var15 += 2;
                     var15 -= 512;
                  }
                  break label113;
               }

               var7 = (NucleusHelperModule.Class0)var6.next();
            } while(!var7.field3 && !var7.field7.isEmpty());

            var5.add(var7);
         }
      }

   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field1) {
         Iterator var2 = field0.iterator();

         while(true) {
            NucleusHelperModule.Class0 var3;
            do {
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  var3 = (NucleusHelperModule.Class0)var2.next();
               } while(!var3.field6.method1());
            } while(var3.field7.isEmpty());

            Iterator var4 = var3.field7.iterator();

            while(var4.hasNext()) {
               BlockPos var5 = (BlockPos)var4.next();
               AutoMaskModule.method0(var5, var3.method17());
               AutoWaterModule.method0(var3.field1, (double)var5.getX() + 0.5D, (double)var5.getY() + 0.5D, (double)var5.getZ() + 0.5D);
               if (this.field1.method1()) {
                  AutoArrowModule.method0((double)var5.getX() + 0.5D, (double)var5.getY() + 0.5D, (double)var5.getZ() + 0.5D, var3.method17());
               }
            }
         }
      }
   }

   static {
      field0 = Lists.newArrayList(new NucleusHelperModule.Class0[]{new NucleusHelperModule.Class0("Goblin King", "internal_king", new BlockPos(1, -4, 2), Color.YELLOW, false, new Block[]{Blocks.dark_oak_stairs, Blocks.dark_oak_stairs, Blocks.dark_oak_stairs, Blocks.wool, Blocks.stonebrick, Blocks.air}), new NucleusHelperModule.Class0("Goblin Queen", "internal_den", new BlockPos(-10, -4, 3), Color.ORANGE, false, new Block[]{Blocks.fire, Blocks.cauldron, Blocks.log2, Blocks.log2, Blocks.log2}), new NucleusHelperModule.Class0("Jungle Temple", "internal_temple", new BlockPos(5, -40, 15), Color.MAGENTA, false, new Block[]{Blocks.jungle_stairs, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence, Blocks.jungle_fence}), new NucleusHelperModule.Class0("Precursor City", "internal_city", new BlockPos(0, 0, 0), Color.CYAN, false, new Block[]{Blocks.stone_slab, Blocks.quartz_block, Blocks.stone_slab, Blocks.iron_block, Blocks.iron_block, Blocks.iron_block, Blocks.air}), new NucleusHelperModule.Class0("Mines of Divan", "internal_mines", new BlockPos(0, 1, 4), Color.GREEN, false, new Block[]{Blocks.stonebrick, Blocks.stone_brick_stairs, Blocks.stone_slab, Blocks.stone_slab, Blocks.double_stone_slab}), new NucleusHelperModule.Class0("Bal", "internal_bal", new BlockPos(-2, -1, 20), Color.RED, false, new Block[]{Blocks.barrier, Blocks.lava}), new NucleusHelperModule.Class0("Key Guardian", (String)null, new BlockPos(0, 0, 0), Color.YELLOW.darker().darker(), false, new Block[]{Blocks.wooden_slab, Blocks.air, Blocks.air, Blocks.air, Blocks.fire, Blocks.hopper, Blocks.air, Blocks.air, Blocks.stone_brick_stairs}), new NucleusHelperModule.Class0("Dragon's Lair", (String)null, new BlockPos(0, 0, 0), Color.WHITE, false, new Block[]{Blocks.snow, Blocks.quartz_block, Blocks.redstone_block}), new NucleusHelperModule.Class0("Bear Story", (String)null, new BlockPos(8, -3, 4), Color.ORANGE.darker().darker(), false, new Block[]{Blocks.trapdoor, Blocks.gold_block, Blocks.gold_block, Blocks.dirt}), new NucleusHelperModule.Class0("Xalx's Ravine", (String)null, new BlockPos(29, -10, 15), Color.GREEN.darker(), false, new Block[]{Blocks.oak_fence, Blocks.skull, Blocks.double_wooden_slab, Blocks.air, Blocks.air, Blocks.air, Blocks.air, Blocks.wooden_slab}), new NucleusHelperModule.Class0("Jungle Shop", (String)null, new BlockPos(2, -2, -1), Color.YELLOW.darker(), false, new Block[]{Blocks.tripwire_hook, Blocks.log, Blocks.oak_stairs}), new NucleusHelperModule.Class0("Corleone 1", "internal_corleone", new BlockPos(2, -2, -1), Color.YELLOW.darker(), false, new Block[]{Blocks.fire, Blocks.stone, Blocks.stone_brick_stairs, Blocks.stained_hardened_clay, Blocks.stone, Blocks.stone, Blocks.air}), new NucleusHelperModule.Class0("Corleone 2", "internal_corleone", new BlockPos(2, -2, -1), Color.YELLOW.darker(), false, new Block[]{Blocks.stone_slab, Blocks.double_stone_slab, Blocks.cobblestone_wall, Blocks.cobblestone_wall, Blocks.stone, Blocks.double_stone_slab})});
   }

   public NucleusHelperModule() {
      super("Nucleus Helper", Category.skyblock, SubCategory.mining, "Shows mining structures");
      this.method0(new Setting[]{this.field1});
      Iterator var1 = field0.iterator();

      while(var1.hasNext()) {
         NucleusHelperModule.Class0 var2 = (NucleusHelperModule.Class0)var1.next();
         this.method0(new Setting[]{var2.field6, var2.field4});
      }

   }

   static Minecraft access$000() {
      return field58;
   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      this.method5();
   }

   public void method5() {
      Iterator var1 = field0.iterator();

      while(var1.hasNext()) {
         NucleusHelperModule.Class0 var2 = (NucleusHelperModule.Class0)var1.next();
         var2.field7.clear();
      }

   }

   public static class Class0 {
      final int[] field0;
      public final String field1;
      public final String field2;
      public final boolean field3;
      final ColorSetting field4;
      final Block[] field5;
      public final BooleanSetting field6;
      public final HashSet field7 = Sets.newHashSet();
      public int field8;
      final BlockPos field9;

      public void method0(BlockPos var1) {
         var1 = var1.add(this.field9);
         this.field7.add(var1);
         if (this.field6.method1()) {
            if (this.field2 != null) {
               ClientCommandHandler.instance.executeCommand(NucleusHelperModule.access$000().thePlayer, String.format("/sthw set %s %s %s %s", var1.getX(), var1.getY(), var1.getZ(), this.field2));
            }

            RenderBarriersModule.method1(String.format("Found %s at §a%s, %s, %s§f!", this.field1, var1.getX(), var1.getY(), var1.getZ()), new ClickEvent(Action.SUGGEST_COMMAND, String.format("%s is at %s, %s, %s", this.field1, var1.getX(), var1.getY(), var1.getZ())));
            SecretHitboxesModule.method0(String.format("%s found at X: %s Y: %s Z: %s", this.field1, var1.getX(), var1.getY(), var1.getZ()), 7500);
         }

      }

      public Class0(String var1, String var2, BlockPos var3, Color var4, boolean var5, Block... var6) {
         this.field1 = var1;
         this.field2 = var2;
         this.field9 = var3;
         this.field4 = new ColorSetting(var1 + " color", var4, false);
         this.field5 = var6;
         this.field0 = Class457.method0(var6);
         this.field3 = var5;
         this.field6 = new BooleanSetting(var1, true);
      }

      public Color method17() {
         return this.field4.method17();
      }
   }
}
