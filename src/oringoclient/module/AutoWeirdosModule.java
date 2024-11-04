package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import map.Class142;
import map.Class282;
import map.Class361;
import map.Class434;
import map.Class479;
import map.Class496;
import map.Class94;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class75;

public class AutoWeirdosModule extends Module {
   public String field0;
   public final List X_ = new ArrayList();
   public static final String[] field2 = new String[]{"The reward is not in my chest!", "At least one of them is lying, and the reward is not in", "My chest doesn't have the reward. We are all telling the truth", "My chest has the reward and I'm telling the truth", "The reward isn't in any of our chests", "Both of them are telling the truth."};
   public List field3 = new ArrayList();

   public boolean lambda$onUpdate$5(EntityArmorStand var1) {
      boolean var2 = this.X_.contains(var1);
      if ((double)var1.getDistanceToEntity(field58.thePlayer) < 4.5D && !var2) {
         if (!Class361.method0((Class94)(new Class142(var1)))) {
            return false;
         } else {
            method3("Clicked " + var1.getPositionVector());
            this.X_.add(var1);
            return true;
         }
      } else {
         return var2;
      }
   }

   public static boolean lambda$onUpdate$3(TileEntity var0) {
      return var0 instanceof TileEntityChest;
   }

   public static boolean lambda$onUpdate$0(EntityArmorStand var0) {
      return var0.getName().equals("§e§lCLICK") && field58.thePlayer.getDistanceSqToEntity(var0) < 256.0D;
   }

   public void b_() {
      this.field0 = null;
      this.field3.clear();
      this.X_.clear();
   }

   public static void method0(float var0, float var1, float var2, float var3) {
      Class434.field2 = true;
      if (Class434.field0 != -1 && BarPhaseModule.method3()) {
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableTexture2D();
         ScaledResolution var4 = new ScaledResolution(Class434.field6);
         GlStateManager.scale(1.0F / (float)var4.getScaleFactor(), 1.0F / (float)var4.getScaleFactor(), 1.0F);
         var0 *= (float)var4.getScaleFactor();
         var1 *= (float)var4.getScaleFactor();
         var2 *= (float)var4.getScaleFactor();
         var3 *= (float)var4.getScaleFactor();
         GlStateManager.bindTexture(Class434.field0);
         ChestESPModule.method0(var0, var1, var2, var3);
         GlStateManager.scale((float)var4.getScaleFactor(), (float)var4.getScaleFactor(), 1.0F);
         GlStateManager.disableBlend();
      }
   }

   public static void method0(String var0, double var1, double var3, double var5, double var7, Color var9) {
      FontRenderer var10 = Oringo.field9.fontRendererObj;
      float var11 = Math.max(2.0F, BearAuraModule.method0(var1, var3, var5) / 5.0F);
      float var12 = 0.016666668F * var11;
      GlStateManager.pushMatrix();
      GlStateManager.translate(var1 - Oringo.field9.getRenderManager().viewerPosX, var3 - Oringo.field9.getRenderManager().viewerPosY, var5 - Oringo.field9.getRenderManager().viewerPosZ);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-Oringo.field9.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(Oringo.field9.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(-var12, -var12, var12);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(false);
      GlStateManager.disableDepth();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      byte var13 = 0;
      int var14 = var10.getStringWidth(var0) / 2;
      GlStateManager.disableTexture2D();
      Class479.field3.begin(7, DefaultVertexFormats.POSITION);
      Class479.field3.pos((double)(-var14 - 1), (double)(-1 + var13), 0.0D).endVertex();
      Class479.field3.pos((double)(-var14 - 1), (double)(8 + var13), 0.0D).endVertex();
      Class479.field3.pos((double)(var14 + 1), (double)(8 + var13), 0.0D).endVertex();
      Class479.field3.pos((double)(var14 + 1), (double)(-1 + var13), 0.0D).endVertex();
      GlStateManager.color(0.0F, 0.0F, 0.0F, 0.25F);
      Class479.field1.draw();
      GL11.glLineWidth(3.0F);
      double var15 = (double)(2 * (var14 + 1));
      Class479.field3.begin(1, DefaultVertexFormats.POSITION);
      Class479.field3.pos((double)(-var14 - 1), (double)(-2 + var13), 0.0D).endVertex();
      Class479.field3.pos((double)(-var14 - 1) + var15, (double)(-2 + var13), 0.0D).endVertex();
      AutoBlazeModule.method0(Color.black);
      Class479.field1.draw();
      var15 *= Math.min(1.0D, var7);
      Class479.field3.begin(1, DefaultVertexFormats.POSITION);
      Class479.field3.pos((double)(-var14 - 1), (double)(-2 + var13), 0.0D).endVertex();
      Class479.field3.pos((double)(-var14 - 1) + var15, (double)(-2 + var13), 0.0D).endVertex();
      AutoBlazeModule.method0(var9);
      Class479.field1.draw();
      AutoCloseModule.method5();
      GlStateManager.enableTexture2D();
      var10.drawStringWithShadow(var0, (float)(-var10.getStringWidth(var0)) / 2.0F, (float)var13, -1);
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.enableLighting();
      GlStateManager.disableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class496.field5 && this.method44()) {
         if (this.field3.size() + this.X_.size() < 3) {
            Iterator var2 = field58.theWorld.getEntities(EntityArmorStand.class, AutoWeirdosModule::lambda$onUpdate$0).iterator();

            while(var2.hasNext()) {
               EntityArmorStand var3 = (EntityArmorStand)var2.next();
               List var4 = field58.theWorld.getEntitiesWithinAABB(EntityArmorStand.class, var3.getEntityBoundingBox().expand(6.0D, 6.0D, 6.0D), AutoWeirdosModule::lambda$onUpdate$1);
               if (var4.size() == 3) {
                  this.field3 = var4;
                  break;
               }
            }
         } else if (this.X_.size() > 2 && this.field0 != null) {
            List var5 = field58.theWorld.getEntities(EntityArmorStand.class, this::lambda$onUpdate$2);
            List var6 = (List)field58.theWorld.loadedTileEntityList.stream().filter(AutoWeirdosModule::lambda$onUpdate$3).collect(Collectors.toList());
            if (!var5.isEmpty() && !var6.isEmpty()) {
               var6.sort(Comparator.comparingDouble(AutoWeirdosModule::lambda$onUpdate$4));
               TileEntityChest var7 = (TileEntityChest)var6.get(0);
               if (field58.thePlayer.getDistance((double)var7.getPos().getX(), (double)((float)var7.getPos().getY() - field58.thePlayer.getEyeHeight()), (double)var7.getPos().getZ()) < 6.0D && Class361.method0((Class94)(new Class282(var7.getPos(), new Vec3(var7.getPos()), EnumFacing.DOWN)))) {
                  this.field0 = null;
               }
            }
         }

         if (!field58.playerController.getIsHittingBlock()) {
            this.field3.removeIf(this::lambda$onUpdate$5);
         }
      }
   }

   public boolean lambda$onUpdate$2(EntityArmorStand var1) {
      return var1.getName().contains(this.field0);
   }

   public static boolean lambda$onUpdate$1(EntityArmorStand var0) {
      return var0.getName().equals("§e§lCLICK");
   }

   @SubscribeEvent
   public void method0(Load var1) {
      this.field0 = null;
      this.field3.clear();
      this.X_.clear();
   }

   public AutoWeirdosModule() {
      super("Auto Weirdos", Category.dungeon, SubCategory.puzzle, "Does the weirdos puzzle");
   }

   public static double lambda$onUpdate$4(List var0, TileEntity var1) {
      return var1.getDistanceSq(((EntityArmorStand)var0.get(0)).posX, ((EntityArmorStand)var0.get(0)).posY, ((EntityArmorStand)var0.get(0)).posZ);
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (Class496.field5 && this.method44() && var1.type != 2) {
         String var2 = ChatFormatting.stripFormatting(var1.message.getFormattedText().trim());
         if (var2.startsWith("[NPC]")) {
            if (var2.contains("Talk to all of us first!")) {
               this.field3.clear();
               this.X_.clear();
               this.field0 = null;
               return;
            }

            String[] var3 = field2;
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               String var6 = var3[var5];
               if (var2.contains(var6)) {
                  this.field0 = var2.substring(var2.indexOf(93) + 2, var2.indexOf(58));
                  method3(this.field0);
               }
            }
         }

      }
   }
}
