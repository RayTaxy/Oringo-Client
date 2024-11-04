package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import map.Class361;
import map.Class376;
import map.Class402;
import map.Class426;
import map.Class447;
import map.Class496;
import map.Class94;
import map.Class95;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S22PacketMultiBlockChange.BlockUpdateData;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.event.Class189;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoStunSnakeModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Predict tail position (Beta)", true);
   public BlockPos field1 = null;
   public BlockPos field2 = null;
   public final Class447 field3 = new Class447();
   public final CopyOnWriteArrayList field4 = new CopyOnWriteArrayList();

   public static boolean lambda$onTick$0(AutoStunSnakeModule.Class0 var0) {
      return var0.field0.a_(3000L) || var0.field2.size() > 12 || field58.theWorld.getBlockState((BlockPos)var0.field2.get(0)).getBlock() != Blocks.stained_glass && field58.theWorld.getBlockState((BlockPos)var0.field2.get(0)).getBlock() != Blocks.air;
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (Class496.field21) {
         if (field58.theWorld != null) {
            if (var1.field0 instanceof S22PacketMultiBlockChange) {
               BlockUpdateData[] var2 = ((S22PacketMultiBlockChange)var1.field0).getChangedBlocks();
               int var3 = var2.length;

               for(int var4 = 0; var4 < var3; ++var4) {
                  BlockUpdateData var5 = var2[var4];
                  this.method0(var5.getPos(), var5.getBlockState());
               }
            }

            if (var1.field0 instanceof S23PacketBlockChange) {
               this.method0(((S23PacketBlockChange)var1.field0).getBlockPosition(), ((S23PacketBlockChange)var1.field0).blockState);
            }

         }
      }
   }

   public AutoStunSnakeModule() {
      super("Auto Stun Snake", Category.skyblock, SubCategory.rift, "Automatically stuns snakes in the Living Cave, while mining them, use with No Break Reset module");
      this.method0((Setting[])(new Setting[]{this.field0}));
   }

   public static boolean lambda$onTick$1(ItemStack var0) {
      return "FROZEN_WATER_PUNGI".equals(BlockHitModule.method0(var0));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field0.method1() && Class496.field21 && !this.field4.isEmpty()) {
         GL11.glPushMatrix();
         GlStateManager.disableLighting();
         GlStateManager.disableDepth();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.disableTexture2D();
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(false);
         GL11.glTranslated(-field58.getRenderManager().viewerPosX, -field58.getRenderManager().viewerPosY, -field58.getRenderManager().viewerPosZ);
         GL11.glLineWidth(1.5F);
         Iterator var2 = this.field4.iterator();

         while(true) {
            AutoStunSnakeModule.Class0 var3;
            do {
               if (!var2.hasNext()) {
                  AutoCloseModule.method5();
                  GlStateManager.depthMask(true);
                  GlStateManager.enableCull();
                  GlStateManager.enableTexture2D();
                  GlStateManager.enableDepth();
                  GlStateManager.disableBlend();
                  GL11.glPopMatrix();
                  return;
               }

               var3 = (AutoStunSnakeModule.Class0)var2.next();
            } while(var3.field2.size() == 1);

            Tessellator var4 = Tessellator.getInstance();
            WorldRenderer var5 = var4.getWorldRenderer();
            var5.begin(3, DefaultVertexFormats.POSITION);
            AutoBlazeModule.method0(Color.GREEN);
            Iterator var6 = var3.field2.iterator();

            while(var6.hasNext()) {
               BlockPos var7 = (BlockPos)var6.next();
               var5.pos((double)var7.getX() + 0.5D, (double)var7.getY() + 0.5D, (double)var7.getZ() + 0.5D).endVertex();
            }

            var4.draw();
         }
      }
   }

   public void method0(BlockPos var1, IBlockState var2) {
      AutoStunSnakeModule.Class0 var3;
      if (var2.getBlock() != Blocks.stained_glass && var2.getBlock() != Blocks.lapis_block) {
         var3 = this.method0(var1);
         if (var3 != null) {
            if (var3.field2.indexOf(var1) == 1) {
               var3.field2.remove(0);
            }

            var3.field2.remove(var1);
            var3.field0.o_();
            if (var3.field2.isEmpty()) {
               this.field4.remove(var3);
            }

         }
      } else {
         if (var2.getBlock() == Blocks.lapis_block) {
            if (this.field2 == null) {
               this.field1 = var1;
            } else {
               if (var1.distanceSq(this.field2) > 3.0D) {
                  this.field1 = var1;
                  return;
               }

               var3 = this.method0(this.field2);
               if (var3 == null) {
                  var3 = new AutoStunSnakeModule.Class0(this.field2);
                  this.field4.add(var3);
               }

               var3.field2.add(var1);
               var3.field1 = var1;
               var3.field0.o_();
               this.field2 = null;
            }
         }

         if (var2.getBlock() == Blocks.stained_glass) {
            if (this.field1 != null) {
               if (var1.distanceSq(this.field1) > 3.0D) {
                  this.field2 = var1;
                  return;
               }

               var3 = this.method0(var1);
               if (var3 == null) {
                  var3 = new AutoStunSnakeModule.Class0(var1);
                  this.field4.add(var3);
               }

               var3.field2.add(this.field1);
               var3.field1 = this.field1;
               var3.field0.o_();
               this.field1 = null;
            } else {
               this.field2 = var1;
            }
         }

      }
   }

   public AutoStunSnakeModule.Class0 method0(BlockPos var1) {
      Iterator var2 = this.field4.iterator();

      AutoStunSnakeModule.Class0 var3;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         var3 = (AutoStunSnakeModule.Class0)var2.next();
      } while(!var3.field2.contains(var1));

      return var3;
   }

   public static void lambda$onTick$2(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!Class496.field21) {
         this.field4.clear();
      } else {
         this.field4.removeIf(AutoStunSnakeModule::lambda$onTick$0);
         if (!field58.playerController.getIsHittingBlock()) {
            if (field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
               IBlockState var2 = field58.theWorld.getBlockState(field58.objectMouseOver.getBlockPos());
               if (var2.getBlock() != Blocks.lapis_block) {
                  if (var2.getBlock() != Blocks.stained_glass) {
                     return;
                  }

                  if (var2.getValue(BlockStainedGlass.COLOR) != (Class376.method0("Living Cave") ? EnumDyeColor.BLUE : EnumDyeColor.LIGHT_BLUE)) {
                     return;
                  }
               }

               if (this.field3.method0(500L, true)) {
                  int var3 = Class95.method0(AutoStunSnakeModule::lambda$onTick$1);
                  if (var3 != -1) {
                     Class361.method0((Class94)(new Class402(var3, AutoStunSnakeModule::lambda$onTick$2)));
                  }
               }
            }
         }
      }
   }

   public static float method5() {
      return OdonataESPModule.method0(0.42F);
   }

   public static class Class0 {
      public Class447 field0 = new Class447();
      public BlockPos field1;
      public CopyOnWriteArrayList field2 = new CopyOnWriteArrayList();

      public Class0(BlockPos var1) {
         this.field1 = var1;
         this.field2.add(var1);
      }
   }
}
