package oringo.module;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import map.Class239;
import map.Class496;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class270;
import oringo.event.Class420;

public class GrottoNotificationModule extends Module {
   public final HashSet field0 = new HashSet();
   public final HashSet ch_ = new HashSet();

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field1) {
         Iterator var2 = (new HashSet(this.ch_)).iterator();

         while(var2.hasNext()) {
            GrottoNotificationModule.Class0 var3 = (GrottoNotificationModule.Class0)var2.next();
            BlockPos var4 = var3.method1();
            AutoWaterModule.method0("Blocks: §d" + var3.method2(), (double)var4.getX() + 0.5D, (double)var4.getY() + 0.5D, (double)var4.getZ() + 0.5D);
            AutoArrowModule.method0((double)var4.getX() + 0.5D, (double)var4.getY() + 0.5D, (double)var4.getZ() + 0.5D, Color.MAGENTA);
         }
      }

   }

   public GrottoNotificationModule() {
      super("Grotto Notification", Category.skyblock, SubCategory.mining, "Notifies you, when a fairy grotto is found");
   }

   public void b_() {
      this.ch_.clear();
      this.field0.clear();
   }

   public static int o_(String var0) {
      return TrailModule.method0(Class239::lambda$getHotbar$1);
   }

   @SubscribeEvent
   public void method0(Class420 var1) {
      if (var1.field0.getBlock(0, 189, 0) == Blocks.bedrock) {
         if (!this.method0(var1.field0)) {
            for(int var2 = 0; var2 < 16; ++var2) {
               for(int var3 = 0; var3 < 16; ++var3) {
                  for(int var4 = 50; var4 < 160; ++var4) {
                     if (var1.field0.getBlock(var2, var4, var3) == Blocks.stained_glass_pane) {
                        IBlockState var5 = var1.field0.getBlockState(new BlockPos(var2, var4, var3));
                        int var6 = var2 + var1.field0.xPosition * 16;
                        int var7 = var3 + var1.field0.zPosition * 16;
                        if ((var6 <= 460 || var6 >= 565 || var7 <= 460 || var7 >= 565) && var5.getProperties().containsKey(BlockColored.COLOR) && ((Comparable)var5.getProperties().get(BlockColored.COLOR)).equals(EnumDyeColor.MAGENTA)) {
                           boolean var8 = false;
                           Iterator var9 = this.ch_.iterator();

                           while(var9.hasNext()) {
                              GrottoNotificationModule.Class0 var10 = (GrottoNotificationModule.Class0)var9.next();
                              if (Math.hypot((double)(var10.method1().getX() - var6), (double)(var10.method1().getZ() - var7)) <= 80.0D) {
                                 var10.method0();
                                 var8 = true;
                              }
                           }

                           if (!var8) {
                              SecretHitboxesModule.method0("Fairy Grotto found!", 2500);
                              RenderBarriersModule.method1(String.format("Fairy Grotto found at §r%s, %s, %s§f!", var6, var4, var7), new ClickEvent(Action.SUGGEST_COMMAND, String.format("There's a Fairy Grotto at %s, %s, %s", var6, var4, var7)));
                              this.ch_.add(new GrottoNotificationModule.Class0(new BlockPos(var6, var4, var7)));
                           }
                        }
                     }
                  }
               }
            }

         }
      }
   }

   public boolean method0(Chunk var1) {
      int var2 = var1.xPosition << 16 | var1.zPosition;
      if (this.field0.contains(var2)) {
         return true;
      } else {
         this.field0.add(var2);
         return false;
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.b_();
   }

   public static class Class0 {
      private final BlockPos field0;
      private int field3;

      public Class0(BlockPos var1) {
         this.field0 = var1;
      }

      public int hashCode() {
         return this.field0.hashCode();
      }

      public void method0() {
         ++this.field3;
      }

      public BlockPos method1() {
         return this.field0;
      }

      public int method2() {
         return this.field3;
      }
   }
}
