package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import map.Class207;
import map.Class350;
import map.Class452;
import map.Class496;
import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class217;
import oringo.event.Class270;

public class AutoIceFillModule extends Module {
   public static int field0 = 2;
   public static boolean[][] ad_ = new boolean[][]{new boolean[0]};
   public static int field2 = 2;
   public final List field3 = new ArrayList();
   public static int field4 = 0;
   public static int field5 = 4;
   public BlockPos field11;
   public boolean field6;
   public boolean field8;

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.o_();
   }

   public void o_() {
      this.field6 = false;
      this.field8 = false;
      this.field3.clear();
   }

   public boolean[][] method0(BlockPos var1, BlockPos var2, EnumFacing var3) {
      EnumFacing var4 = var3.rotateYCCW();
      int var5 = Math.abs(Class452.method0(var1, var2, var3)) + 1;
      int var6 = Math.abs(Class452.method0(var1, var2, var4)) + 1;
      boolean[][] var7 = new boolean[var5][var6];
      BlockPos var8 = var1;

      for(int var9 = 0; var9 < var5; ++var9) {
         for(int var10 = 0; var10 < var6; ++var10) {
            var7[var9][var10] = (field58.theWorld.getBlockState(var8).getBlock() == Blocks.ice || field58.theWorld.getBlockState(var8).getBlock() == Blocks.packed_ice) && field58.theWorld.getBlockState(var8.up()).getBlock() == Blocks.air;
            var8 = var8.offset(var4);
         }

         var8 = var8.offset(var4, -var6).offset(var3);
      }

      return var7;
   }

   public ArrayList method0(List var1, EnumFacing var2, BlockPos var3) {
      Collections.reverse(var1);
      ArrayList var4 = new ArrayList();
      var4.add(var3);

      for(Iterator var5 = var1.iterator(); var5.hasNext(); var4.add(var3)) {
         String var6 = (String)var5.next();
         byte var8 = -1;
         switch(var6.hashCode()) {
         case 2715:
            if (var6.equals("UP")) {
               var8 = 0;
            }
            break;
         case 2104482:
            if (var6.equals("DOWN")) {
               var8 = 1;
            }
            break;
         case 2332679:
            if (var6.equals("LEFT")) {
               var8 = 3;
            }
            break;
         case 77974012:
            if (var6.equals("RIGHT")) {
               var8 = 2;
            }
         }

         switch(var8) {
         case 0:
            var3 = var3.offset(var2.getOpposite());
            break;
         case 1:
            var3 = var3.offset(var2);
            break;
         case 2:
            var3 = var3.offset(var2.rotateYCCW());
            break;
         case 3:
            var3 = var3.offset(var2.rotateY());
         }
      }

      return var4;
   }

   public static void method0(Entity var0, float var1, Color var2) {
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GL11.glLineWidth(1.5F);
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      AutoBlazeModule.method0(var2);
      GlStateManager.disableLighting();
      RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(var0.getEntityBoundingBox().minX - var0.posX + (var0.prevPosX + (var0.posX - var0.prevPosX) * (double)var1 - Oringo.field9.getRenderManager().viewerPosX), var0.getEntityBoundingBox().minY - var0.posY + (var0.prevPosY + (var0.posY - var0.prevPosY) * (double)var1 - Oringo.field9.getRenderManager().viewerPosY), var0.getEntityBoundingBox().minZ - var0.posZ + (var0.prevPosZ + (var0.posZ - var0.prevPosZ) * (double)var1 - Oringo.field9.getRenderManager().viewerPosZ), var0.getEntityBoundingBox().maxX - var0.posX + (var0.prevPosX + (var0.posX - var0.prevPosX) * (double)var1 - Oringo.field9.getRenderManager().viewerPosX), var0.getEntityBoundingBox().maxY - var0.posY + (var0.prevPosY + (var0.posY - var0.prevPosY) * (double)var1 - Oringo.field9.getRenderManager().viewerPosY), var0.getEntityBoundingBox().maxZ - var0.posZ + (var0.prevPosZ + (var0.posZ - var0.prevPosZ) * (double)var1 - Oringo.field9.getRenderManager().viewerPosZ)));
      GlStateManager.enableLighting();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.disableBlend();
   }

   public static boolean lambda$scan$0(TileEntity var0) {
      return var0 instanceof TileEntityChest;
   }

   public double method5() {
      double var1 = field58.thePlayer.isSneaking() ? 0.3D : 1.0D;
      return Math.min(1.0D, var1 * 0.2873D * (double)field58.thePlayer.capabilities.getWalkSpeed() * 10.0D);
   }

   public void method2() {
      if (SecretAuraModule.w_("Ice Fill")) {
         TileEntity var1 = (TileEntity)field58.theWorld.loadedTileEntityList.stream().filter(AutoIceFillModule::lambda$scan$0).filter(AutoIceFillModule::lambda$scan$1).findFirst().orElse((Object)null);
         if (var1 == null) {
            this.o_();
         } else {
            IBlockState var2 = field58.theWorld.getBlockState(var1.getPos());
            EnumFacing var3 = (EnumFacing)var2.getValue(BlockChest.FACING);
            EnumFacing var4 = var3.rotateY();
            BlockPos var5 = var1.getPos().down(4).offset(var3, 3).offset(var4, 3);
            BlockPos var6 = var5.offset(var3, 7).offset(var4, -6);
            boolean[][] var7 = this.method0(var5, var6, var3);
            ArrayList var8 = this.method0(this.method0(var7), var3, var1.getPos().offset(var3, 10).down(4));
            Collections.reverse(var8);
            this.field3.addAll(var8);
            var5 = var5.offset(var3, 9).down().offset(var4, -1);
            var6 = var6.offset(var3, 7).down().offset(var4);
            var7 = this.method0(var5, var6, var3);
            ArrayList var9 = this.method0(this.method0(var7), var3, var1.getPos().offset(var3, 17).down(5));
            Collections.reverse(var9);
            this.field3.add(var1.getPos().offset(var3, 11).down(4));
            this.field3.addAll(var9);
            var5 = var5.offset(var3, 7).down().offset(var4, -1);
            var6 = var6.offset(var3, 5).down().offset(var4);
            var7 = this.method0(var5, var6, var3);
            ArrayList var10 = this.method0(this.method0(var7), var3, var1.getPos().offset(var3, 22).down(6));
            Collections.reverse(var10);
            this.field3.add(var1.getPos().offset(var3, 18).down(5));
            this.field3.addAll(var10);
            Collections.reverse(this.field3);
            this.field6 = true;
         }
      }
   }

   public static boolean method0(Entity var0) {
      String var1 = var0.getName();
      return var1.equals("Shadow Assassin") || var1.equals("Lost Adventurer") || var1.equals("Diamond Guy");
   }

   public AutoIceFillModule() {
      super("Auto Ice Fill", Category.dungeon, SubCategory.puzzle);
   }

   public static MovingObjectPosition method0(float var0, float var1) {
      Vec3 var2 = PrinterModule.field58.thePlayer.getPositionEyes(1.0F);
      Vec3 var3 = AutoFrozilleModule.method0(var0, var1);
      Vec3 var4 = var2.addVector(var3.xCoord * 5.0D, var3.yCoord * 5.0D, var3.zCoord * 5.0D);
      return PrinterModule.field58.theWorld.rayTraceBlocks(var2, var4);
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field5 && SecretAuraModule.w_("Ice Fill") && !this.field3.isEmpty()) {
         GL11.glPushMatrix();
         GlStateManager.disableLighting();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.color(0.0F, 1.0F, 0.0F);
         GlStateManager.disableTexture2D();
         GlStateManager.disableCull();
         GlStateManager.depthMask(false);
         GL11.glTranslated(-field58.getRenderManager().viewerPosX, -field58.getRenderManager().viewerPosY, -field58.getRenderManager().viewerPosZ);
         GL11.glLineWidth(1.5F);
         Tessellator var2 = Tessellator.getInstance();
         WorldRenderer var3 = var2.getWorldRenderer();
         var3.begin(3, DefaultVertexFormats.POSITION);
         Iterator var4 = this.field3.iterator();

         while(var4.hasNext()) {
            BlockPos var5 = (BlockPos)var4.next();
            var3.pos((double)var5.getX() + 0.5D, (double)var5.getY() + 0.5D, (double)var5.getZ() + 0.5D).endVertex();
         }

         var2.draw();
         GlStateManager.depthMask(true);
         GlStateManager.enableCull();
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
         GL11.glPopMatrix();
      }
   }

   public void b_() {
      this.o_();
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (Class496.field5) {
         if (!this.field6) {
            try {
               this.method2();
            } catch (NullPointerException var11) {
               method2("Unable to solve the ice fill puzzle!");
            }

            if (!this.field6) {
               return;
            }
         }

         if (!field58.thePlayer.onGround) {
            this.field8 = false;
         } else {
            BlockPos var2 = this.field8 ? this.field11 : new BlockPos(field58.thePlayer);
            int var3 = this.field3.indexOf(var2.down());
            if (var3 == -1) {
               var3 = this.field3.indexOf(var2);
            }

            if (var3 != -1 && var3 != this.field3.size() - 1) {
               field58.thePlayer.motionX = field58.thePlayer.motionZ = 0.0D;
               var1.method2(0.0D).method1(0.0D);
               if (!this.field8 && (Math.abs(field58.thePlayer.posX % 1.0D) != 0.5D || Math.abs(field58.thePlayer.posZ % 1.0D) != 0.5D)) {
                  var1.method2((double)var2.getX() + 0.5D - field58.thePlayer.posX).method1((double)var2.getZ() + 0.5D - field58.thePlayer.posZ);
               } else if (field58.theWorld.getBlockState(var2.down()).getBlock() != Blocks.ice) {
                  BlockPos var4 = (BlockPos)this.field3.get(var3 + 1);
                  double var5 = this.method5();
                  double var7 = (double)var4.getX() + 0.5D - field58.thePlayer.posX;
                  double var9 = (double)var4.getZ() + 0.5D - field58.thePlayer.posZ;
                  this.field8 = var7 != Class207.method0(var7, var5, -var5) || var9 != Class207.method0(var9, var5, -var5);
                  var1.method2(Class207.method0(var7, var5, -var5)).method1(Class207.method0(var9, var5, -var5));
                  this.field11 = var2;
               }
            } else {
               this.field8 = false;
            }
         }
      }
   }

   public List method0(boolean[][] var1) {
      int var2 = var1[0].length / 2;
      ad_ = var1;
      field4 = 0;
      field2 = var2;
      field5 = var1.length - 1;
      field0 = var2;
      return Class350.method5();
   }

   public static boolean lambda$scan$1(TileEntity var0) {
      return ShortbowTriggerbotModule.method0(var0.getPos(), "Ice Fill");
   }
}
