package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import map.Class175;
import map.Class282;
import map.Class296;
import map.Class34;
import map.Class351;
import map.Class361;
import map.Class388;
import map.Class94;
import map.Class95;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.JerryBoxCommand;
import oringo.command.LoginCommand;
import oringo.command.ReplyCommand;
import oringo.event.Class148;
import oringo.event.Class210;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class PrinterModule extends Module {
   public final Class296 field0 = new Class296();
   public BlockPos ae_ = new BlockPos(0, 0, 0);
   public final DoubleSetting field2 = (new DoubleSetting("Delay", 5.0D, 1.0D, 20.0D, 1.0D)).method0(" ticks");
   public IBlockState af_ = null;
   public Vec3 field4 = null;
   public boolean field5 = false;
   public static Class351 ag_ = null;
   public BlockPos field7 = null;

   public static boolean lambda$placeBlock$1(ItemStack var0, ItemStack var1) {
      return var1 == var0;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      this.field4 = null;
      this.field5 = false;
      if (ag_ != null && this.field7 != null) {
         if (ag_.F_()) {
            ag_ = null;
            this.field7 = null;
         } else {
            Iterator var2 = ag_.method2().iterator();

            PrinterModule.Class0 var6;
            do {
               if (!var2.hasNext()) {
                  return;
               }

               Class351.Class0 var3 = (Class351.Class0)var2.next();
               ItemBlock var4 = var3.field3;
               ItemStack var5 = field58.thePlayer.getHeldItem();
               if (var5 == null || var5.getItem() != var4) {
                  var5 = field58.thePlayer.inventoryContainer.getSlot(Class95.method0(PrinterModule::field0)).getStack();
               }

               var6 = this.method0(var3.field2.add(this.field7), var5);
               if (var6 == PrinterModule.Class0.field2) {
                  var2.remove();
               }
            } while(var6 == PrinterModule.Class0.field1);

         }
      }
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, boolean var5, Color var6) {
      Class388.field1.method0("rectSize", var2, var3);
      Class388.field1.method0("radius", var4);
      Class388.field1.method0("blurred", var5 ? 1 : 0);
      Class388.field1.method0("color", var6);
      if (var5) {
         Class388.field1.method0("tSize", (float)Oringo.field9.displayWidth, (float)Oringo.field9.displayHeight);
         Class388.field1.method0("location", var0, var1);
      }

   }

   public boolean method0(Vec3 var1, EnumFacing var2, BlockPos var3) {
      MovingObjectPosition var4 = field58.theWorld.rayTraceBlocks(field58.thePlayer.getPositionEyes(1.0F), var1);
      return var4 != null && var4.sideHit == var2 && var4.getBlockPos().equals(var3);
   }

   public String d_() {
      return "§cPrinter";
   }

   public static void method2() {
      Framebuffer var0 = Minecraft.getMinecraft().getFramebuffer();
      if (var0 != null && var0.depthBuffer > -1) {
         ProfilerModule.method0(var0);
         var0.depthBuffer = -1;
      }

   }

   public Vec3 method0(BlockPos var1, EnumFacing var2) {
      // $FF: Couldn't be decompiled
   }

   public PrinterModule() {
      super("Printer", Category.other, SubCategory.other, "Testuję to, na razie pewnie będzie banować");
      this.method0((Setting[])(new Setting[]{this.field2}));
   }

   public void method3() {
      field58.theWorld.setBlockState(this.ae_, this.af_);
   }

   public PrinterModule.Class0 method0(BlockPos var1, ItemStack var2) {
      if (var2 != null && var2.getItem() instanceof ItemBlock) {
         ItemBlock var3 = (ItemBlock)var2.getItem();
         PrinterModule.Class0 var4 = PrinterModule.Class0.field1;
         EnumFacing[] var5 = EnumFacing.values();
         int var6 = var5.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            EnumFacing var8 = var5[var7];
            BlockPos var9 = var1.offset(var8);
            IBlockState var10 = field58.theWorld.getBlockState(var9);
            Block var11 = var10.getBlock();
            if (var11 != Blocks.air) {
               var11.setBlockBoundsBasedOnState(field58.theWorld, var9);
               EnumFacing var12 = var8.getOpposite();
               if (var3.canPlaceBlockOnSide(field58.theWorld, var9, var12, field58.thePlayer, var2)) {
                  Vec3 var13 = this.method0(var9, var12);
                  if (var13.squareDistanceTo(field58.thePlayer.getPositionEyes(1.0F)) <= 25.0D && this.method0(var13, var12, var9)) {
                     this.field4 = var13;
                     if (field58.theWorld.isRemote) {
                        this.field5 = var11.onBlockActivated(field58.theWorld, var1, var10, field58.thePlayer, var12, 0.0F, 0.0F, 0.0F);
                     }

                     if (this.field5) {
                        field58.thePlayer.setSneaking(true);
                     }

                     Class34 var14 = Class175.method0();
                     MovingObjectPosition var15 = AutoIceFillModule.method0(var14.method5(), var14.method2());
                     if (var15 != null && var15.sideHit == var12 && var15.getBlockPos().equals(var9)) {
                        if (this.field0.method0((long)this.field2.method3(), true)) {
                           if (field58.thePlayer.getHeldItem() != var2) {
                              int var16 = Class95.method0(PrinterModule::lambda$placeBlock$1);
                              if (var16 == -1) {
                                 return PrinterModule.Class0.field1;
                              }

                              field58.playerController.windowClick(field58.thePlayer.inventoryContainer.windowId, var16, field58.thePlayer.inventory.currentItem, 2, field58.thePlayer);
                           }

                           Class361.method0((Class94)(new Class282(var9, var15.hitVec, var12)));
                           return PrinterModule.Class0.field2;
                        }
                     } else {
                        var4 = PrinterModule.Class0.field0;
                     }
                  }
               }
            }
         }

         return var4;
      } else {
         return PrinterModule.Class0.field1;
      }
   }

   public static boolean field0(Class351.Class0 var0, ItemStack var1) {
      return var1.getItem() == var0.field3;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (ag_ != null && this.field7 != null && !ag_.F_()) {
         GlStateManager.enableBlend();
         JerryBoxCommand.method0(0.3F, 0.3F, 1.0F, 0.1F, 0.3F);
         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         Tessellator var2 = Tessellator.getInstance();
         WorldRenderer var3 = var2.getWorldRenderer();
         var3.begin(7, DefaultVertexFormats.BLOCK);
         var3.setTranslation(-field58.getRenderManager().viewerPosX, -field58.getRenderManager().viewerPosY, -field58.getRenderManager().viewerPosZ);
         Iterator var4 = ag_.method2().iterator();

         while(var4.hasNext()) {
            Class351.Class0 var5 = (Class351.Class0)var4.next();
            Block var6 = var5.field3.getBlock();
            IBlockState var7 = var6.onBlockPlaced(field58.theWorld, new BlockPos(0, 0, 0), var5.field1, 0.0F, 0.0F, 0.0F, var5.field0, field58.thePlayer);
            BlockPos var8 = var5.field2.add(this.field7);
            BlockRendererDispatcher var9 = Minecraft.getMinecraft().getBlockRendererDispatcher();
            IBakedModel var10 = var9.getModelFromBlockState(var7, field58.theWorld, this.method0(var7, var8));
            var9.getBlockModelRenderer().renderModel(field58.theWorld, var10, var7, var8, var3, false);
            this.method3();
         }

         var3.setTranslation(0.0D, 0.0D, 0.0D);
         var2.draw();
         LoginCommand.method2();
         GlStateManager.enableLighting();
         GlStateManager.popMatrix();
      }

   }

   @SubscribeEvent
   public void method0(Class148 var1) {
      this.field7 = field58.objectMouseOver.getBlockPos().up();
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field4 != null) {
         var1.method0(ReplyCommand.method0(this.field4));
      }
   }

   public BlockPos method0(IBlockState var1, BlockPos var2) {
      this.af_ = field58.theWorld.getBlockState(var2);
      field58.theWorld.setBlockState(var2, var1);
      this.ae_ = var2;
      return var2;
   }

   public static enum Class0 {
      field0;

      private static final PrinterModule.Class0[] field3 = new PrinterModule.Class0[]{field1, field0, field2};
      field1,
      field2;
   }
}
