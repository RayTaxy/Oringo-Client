package oringo.module;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import map.Class175;
import map.Class28;
import map.Class34;
import map.Class47;
import map.Class503;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.BrushCommand;
import oringo.setting.ColorSetting;
import oringo.setting.Setting;

public class TrajectoryModule extends Module {
   public final ColorSetting field0 = new ColorSetting("Entity color", new Color(255, 21, 21), false);
   public final ColorSetting field1 = new ColorSetting("Trajectory color", new Color(66, 164, 245), false);

   public static int method0(Predicate var0) {
      for(int var1 = 8; var1 >= 0; --var1) {
         if (Oringo.field9.thePlayer.inventory.getStackInSlot(var1) != null && var0.test(Oringo.field9.thePlayer.inventory.getStackInSlot(var1))) {
            return var1;
         }
      }

      return -1;
   }

   public void method0(Class34 var1, Class34 var2, float var3, boolean var4, float var5, float var6, float var7, float var8) {
      float var9 = var2.method5() + (var1.method5() - var2.method5()) * var3;
      float var10 = var2.method2() + (var1.method2() - var2.method2()) * var3;
      double var11 = field58.getRenderManager().viewerPosX - (double)(MathHelper.cos(var9 / 180.0F * 3.1415927F) * 0.16F);
      double var13 = field58.getRenderManager().viewerPosY + (double)field58.thePlayer.getEyeHeight() - 0.10000000149011612D;
      double var15 = field58.getRenderManager().viewerPosZ - (double)(MathHelper.sin(var9 / 180.0F * 3.1415927F) * 0.16F);
      double var17 = (double)(-MathHelper.sin(var9 / 180.0F * 3.1415927F) * MathHelper.cos(var10 / 180.0F * 3.1415927F)) * (var4 ? 1.0D : 0.4D);
      double var19 = (double)(-MathHelper.sin(var10 / 180.0F * 3.1415927F)) * (var4 ? 1.0D : 0.4D);
      double var21 = (double)(MathHelper.cos(var9 / 180.0F * 3.1415927F) * MathHelper.cos(var10 / 180.0F * 3.1415927F)) * (var4 ? 1.0D : 0.4D);
      double var23 = (double)MathHelper.sqrt_double(var17 * var17 + var19 * var19 + var21 * var21);
      var17 /= var23;
      var19 /= var23;
      var21 /= var23;
      var17 *= (double)var5;
      var19 *= (double)var5;
      var21 *= (double)var5;
      MovingObjectPosition var25 = null;
      boolean var26 = false;
      boolean var27 = false;
      ArrayList var28 = Lists.newArrayList();

      while(!var26 && var13 > 0.0D) {
         Vec3 var29 = new Vec3(var11, var13, var15);
         Vec3 var30 = new Vec3(var11 + var17, var13 + var19, var15 + var21);
         var25 = field58.theWorld.rayTraceBlocks(var29, var30, false, true, false);
         var29 = new Vec3(var11, var13, var15);
         var30 = new Vec3(var11 + var17, var13 + var19, var15 + var21);
         if (var25 != null) {
            var26 = true;
            var30 = new Vec3(var25.hitVec.xCoord, var25.hitVec.yCoord, var25.hitVec.zCoord);
         }

         AxisAlignedBB var31 = (new AxisAlignedBB(var11 - (double)var8, var13 - (double)var8, var15 - (double)var8, var11 + (double)var8, var13 + (double)var8, var15 + (double)var8)).addCoord(var17, var19, var21).expand(1.0D, 1.0D, 1.0D);
         double var32 = (double)MathHelper.floor_double((var31.minX - 2.0D) / 16.0D);
         double var34 = (double)MathHelper.floor_double((var31.maxX + 2.0D) / 16.0D);
         double var36 = (double)MathHelper.floor_double((var31.minZ - 2.0D) / 16.0D);
         double var38 = (double)MathHelper.floor_double((var31.maxZ + 2.0D) / 16.0D);
         ArrayList var40 = Lists.newArrayList();

         for(int var41 = (int)var32; (double)var41 <= var34; ++var41) {
            for(int var42 = (int)var36; (double)var42 <= var38; ++var42) {
               field58.theWorld.getChunkFromChunkCoords(var41, var42).getEntitiesWithinAABBForEntity(field58.thePlayer, var31, var40, (com.google.common.base.Predicate)null);
            }
         }

         Iterator var51 = var40.iterator();

         while(var51.hasNext()) {
            Entity var45 = (Entity)var51.next();
            if (var45.canBeCollidedWith() && var45 != field58.thePlayer) {
               AxisAlignedBB var43 = var45.getEntityBoundingBox().expand((double)var8, (double)var8, (double)var8);
               MovingObjectPosition var44 = var43.calculateIntercept(var29, var30);
               if (var44 != null) {
                  var27 = true;
                  var26 = true;
                  var25 = var44;
               }
            }
         }

         var11 += var17;
         var13 += var19;
         var15 += var21;
         if (field58.theWorld.getBlockState(new BlockPos(var11, var13, var15)).getBlock().getMaterial() == Material.water) {
            var17 *= 0.6D;
            var19 *= 0.6D;
            var21 *= 0.6D;
         } else {
            var17 *= (double)var6;
            var19 *= (double)var6;
            var21 *= (double)var6;
         }

         var19 -= (double)var7;
         var28.add(new Vec3(var11, var13, var15));
      }

      GlStateManager.pushMatrix();
      GlStateManager.depthMask(false);
      GlStateManager.disableDepth();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glLineWidth(2.0F);
      ColorSetting var46 = var27 ? this.field0 : this.field1;
      GlStateManager.color((float)var46.method7() / 255.0F, (float)var46.method2() / 255.0F, (float)var46.method3() / 255.0F);
      BrushCommand.method3();
      Tessellator var47 = Tessellator.getInstance();
      WorldRenderer var48 = var47.getWorldRenderer();
      var48.begin(3, DefaultVertexFormats.POSITION);
      Iterator var49 = var28.iterator();

      while(var49.hasNext()) {
         Vec3 var33 = (Vec3)var49.next();
         var48.pos(var33.xCoord, var33.yCoord, var33.zCoord).endVertex();
      }

      var47.draw();
      if (var25 != null) {
         Vec3 var50 = var25.hitVec;
         GlStateManager.translate(var50.xCoord, var50.yCoord, var50.zCoord);
         switch(var25.sideHit.getAxis().ordinal()) {
         case 0:
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            break;
         case 2:
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
         }

         GlStateManager.color((float)var46.method7() / 255.0F, (float)var46.method2() / 255.0F, (float)var46.method3() / 255.0F, 0.3F);
         IceFillHelperModule.method0(new AxisAlignedBB(-0.5D, 0.0D, -0.5D, 0.5D, 0.1D, 0.5D));
         GlStateManager.translate(-var50.xCoord, -var50.yCoord, -var50.zCoord);
      }

      Class28.method1();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.popMatrix();
   }

   public TrajectoryModule() {
      super("Trajectory", Category.render, SubCategory.world, "Shows you the trajectory of projectiles");
      this.method0((Setting[])(new Setting[]{this.field1, this.field0}));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field58.thePlayer.getHeldItem() != null) {
         ItemStack var2 = field58.thePlayer.getHeldItem();
         Item var3 = field58.thePlayer.getHeldItem().getItem();
         boolean var4 = false;
         boolean var5 = false;
         float var6 = 1.5F;
         float var7 = 0.99F;
         float var8;
         float var9;
         if (var3 instanceof ItemBow) {
            boolean var10 = Class47.method0(var2);
            var5 = var10 && BlockHitModule.method0(var2).equals("TERMINATOR");
            if (!var10 && !field58.thePlayer.isUsingItem()) {
               return;
            }

            var4 = true;
            var8 = 0.05F;
            var9 = 0.3F;
            float var11 = (!var10 ? (float)field58.thePlayer.getItemInUseDuration() + var1.partialTicks : (float)var3.getMaxItemUseDuration(var2)) / 20.0F;
            var11 = (var11 * var11 + var11 * 2.0F) / 3.0F;
            if (var11 < 0.1F) {
               return;
            }

            if (var11 > 1.0F) {
               var11 = 1.0F;
            }

            var6 = var11 * 3.0F;
         } else if (var3 instanceof ItemFishingRod) {
            var8 = 0.04F;
            var9 = 0.25F;
            var7 = 0.92F;
         } else {
            if (!(var3 instanceof ItemSnowball) && !(var3 instanceof ItemEnderPearl) && !(var3 instanceof ItemEgg)) {
               return;
            }

            var8 = 0.03F;
            var9 = 0.25F;
         }

         Class34 var12 = Class175.method0();
         Class34 var13 = new Class34(Class503.field0, Class503.field1);
         this.method0(var12, var13, var1.partialTicks, var4, var6, var7, var8, var9);
         if (var5) {
            this.method0(var12.method2(5.0F), var13.method2(5.0F), var1.partialTicks, true, var6, var7, var8, var9);
            this.method0(var12.method2(-10.0F), var13.method2(-10.0F), var1.partialTicks, true, var6, var7, var8, var9);
         }

      }
   }
}
