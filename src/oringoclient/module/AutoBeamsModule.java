package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import map.Class173;
import map.Class208;
import map.Class350;
import map.Class361;
import map.Class447;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class307;

public class AutoBeamsModule extends Module {
   public final ArrayList field0 = new ArrayList();
   public final Class447 cj_ = new Class447();
   public EntityCreeper field2 = null;
   public final HashMap field3 = new HashMap();

   public void b_() {
      this.field3.clear();
      this.field0.clear();
      this.field2 = null;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!this.field0.isEmpty() && this.method0(new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ))) {
         AutoBlazeModule.method0(this.field2.getDistance((double)((BlockPos)this.field0.get(0)).getX(), (double)((BlockPos)this.field0.get(0)).getY(), (double)((BlockPos)this.field0.get(0)).getZ()) > 4.0D ? Color.BLUE : Color.cyan);
         this.method1((BlockPos)this.field0.get(0));
         AutoCloseModule.method5();
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.b_();
   }

   public static void method0(Vec3 var0, Vec3 var1, Color var2) {
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glLineWidth(2.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.disableLighting();
      GlStateManager.depthMask(false);
      AutoBlazeModule.method0(var2);
      GL11.glTranslated(-Oringo.field9.getRenderManager().viewerPosX, -Oringo.field9.getRenderManager().viewerPosY, -Oringo.field9.getRenderManager().viewerPosZ);
      GL11.glBegin(1);
      GL11.glVertex3d(var0.xCoord, var0.yCoord, var0.zCoord);
      GL11.glVertex3d(var1.xCoord, var1.yCoord, var1.zCoord);
      AutoCloseModule.method5();
      GL11.glEnd();
      GlStateManager.enableTexture2D();
      GlStateManager.depthMask(true);
      GL11.glPopMatrix();
   }

   public static String method0(double var0) {
      if (var0 >= 1.0E9D) {
         return (int)(var0 / 1.0E9D) + "B";
      } else if (var0 >= 1000000.0D) {
         return (int)(var0 / 1000000.0D) + "M";
      } else {
         return var0 >= 1000.0D ? (int)(var0 / 1000.0D) + "k" : String.valueOf((int)var0);
      }
   }

   public static boolean lambda$onUpdate$2(ItemStack var0) {
      String var1 = BlockHitModule.method0(var0);
      return "TERMINATOR".equals(var1) || "ARTISANAL_SHORTBOW".equals(var1) || "ITEM_SPIRIT_BOW".equals(var1) || "JUJU_SHORTBOW".equals(var1);
   }

   public AutoBeamsModule() {
      super("Auto Beams", Category.dungeon, SubCategory.puzzle, "Auto solves creeper puzzle");
   }

   public boolean method0(BlockPos var1) {
      Class208 var2 = IceFillHelperModule.method0(var1.getX(), var1.getZ());
      return var2 == null ? false : var2.method45().equals("Creeper");
   }

   public void method5() {
      ArrayList var1 = new ArrayList();
      BlockPos var2 = new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ);

      BlockPos var6;
      for(int var3 = -17; var3 <= 17; ++var3) {
         for(int var4 = -17; var4 <= 17; ++var4) {
            for(int var5 = -6; var5 <= 8; ++var5) {
               var6 = var2.add(var3, var5, var4);
               if (this.method0(var6) && field58.theWorld.getBlockState(var6).getBlock() == Blocks.sea_lantern) {
                  var1.add(var6);
               }
            }
         }
      }

      Iterator var7 = var1.iterator();

      while(true) {
         BlockPos var8;
         do {
            do {
               if (!var7.hasNext()) {
                  return;
               }

               var8 = (BlockPos)var7.next();
            } while(this.field3.containsKey(var8));
         } while(this.field3.containsValue(var8));

         Iterator var9 = var1.iterator();

         while(var9.hasNext()) {
            var6 = (BlockPos)var9.next();
            if (!var6.equals(var8) && !this.field3.containsKey(var6) && !this.field3.containsValue(var6) && this.method1(var8, var6)) {
               this.field3.put(var8, var6);
            }
         }
      }
   }

   public void lambda$onUpdate$1(BlockPos var1, BlockPos var2) {
      this.field0.add(var1);
      this.field0.add(var2);
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (Class496.field5 && SecretAuraModule.w_("Creeper")) {
         if (this.field2 == null) {
            List var5 = field58.theWorld.getEntitiesWithinAABB(EntityCreeper.class, field58.thePlayer.getEntityBoundingBox().expand(1.0D, 1.0D, 1.0D), AutoBeamsModule::lambda$onUpdate$0);
            if (var5.size() == 1) {
               this.field2 = (EntityCreeper)var5.get(0);
               method2("Jump down");
               this.method5();
               this.field3.forEach(this::lambda$onUpdate$1);
            }

         } else if (this.field2.hasIgnited()) {
            this.b_();
         } else {
            if (this.cj_.method0(1000L, true) && field58.thePlayer.posY < 70.0D) {
               if (this.field0.isEmpty()) {
                  return;
               }

               MovingObjectPosition var2 = field58.theWorld.rayTraceBlocks(field58.thePlayer.getPositionEyes(1.0F), (new Vec3((Vec3i)this.field0.get(0))).addVector(0.5D, 0.5D, 0.5D), false, true, false);
               BlockPos var3 = (BlockPos)this.field0.get(0);
               if (var2 != null && !var2.getBlockPos().equals(var3)) {
                  return;
               }

               if (this.field2.getDistance((double)var3.getX(), (double)var3.getY(), (double)var3.getZ()) > 4.0D) {
                  if (var2 == null) {
                     return;
                  }

                  if (this.field2.getDistance((double)var3.getX(), (double)var3.getY(), (double)var3.getZ()) < 4.0D) {
                     method2("Walk close to the creeper and jump");
                     return;
                  }

                  int var4 = TrailModule.method0(AutoBeamsModule::lambda$onUpdate$2);
                  if (var4 == -1) {
                     return;
                  }

                  if (Class361.method0((Class94)(new Class350(var4)))) {
                     this.field0.remove(0);
                     var1.method0(Class173.method0((new Vec3((Vec3i)this.field0.get(0))).addVector(0.5D, 0.8D, 0.5D)));
                  }

                  return;
               }

               if (field58.thePlayer.getDistance((double)var3.getX(), (double)var3.getY(), (double)var3.getZ()) >= 5.5D) {
                  return;
               }

               if (Class361.method0((Class94)(new Class510(var3, EnumFacing.DOWN)))) {
                  this.field0.remove(0);
               }
            }

         }
      }
   }

   public void method1(BlockPos var1) {
      if (Class307.method0(var1)) {
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableBlend();
         GlStateManager.disableTexture2D();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
         IceFillHelperModule.method0((new AxisAlignedBB((double)var1.getX(), (double)var1.getY(), (double)var1.getZ(), (double)(var1.getX() + 1), (double)(var1.getY() + 1), (double)(var1.getZ() + 1))).offset(-field58.getRenderManager().viewerPosX, -field58.getRenderManager().viewerPosY, -field58.getRenderManager().viewerPosZ));
         GlStateManager.enableTexture2D();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
      }
   }

   public static boolean method0(AxisAlignedBB var0, double var1, double var3) {
      return AntiStuckModule.field58.theWorld.getCollidingBoundingBoxes(AntiStuckModule.field58.thePlayer, var0.offset(var1, 0.0D, var3)).size() > 0;
   }

   public static boolean lambda$onUpdate$0(EntityCreeper var0) {
      return !var0.isInvisible() && !var0.hasIgnited() && var0.getMaxHealth() == var0.getHealth();
   }

   public boolean method1(BlockPos var1, BlockPos var2) {
      Vec3 var3 = new Vec3(var1);
      Vec3 var4 = new Vec3(var2);
      Vec3 var5 = var4.subtract(var3).normalize();
      Vec3 var6 = new Vec3(var5.xCoord / 3.0D, var5.yCoord / 3.0D, var5.zCoord / 3.0D);

      for(int var7 = 0; (double)var7 < var3.distanceTo(var4) * 4.0D; ++var7) {
         var3 = var3.add(var6);
         if (this.field2.getEntityBoundingBox().expand(0.5D, 0.5D, 0.5D).isVecInside(var3)) {
            return true;
         }
      }

      return false;
   }
}
