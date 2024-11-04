package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import map.Class362;
import map.Class408;
import map.Class486;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class270;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class TrailModule extends Module {
   public static int field0;
   public static final List be_ = new ArrayList();
   public static final DoubleSetting field2 = new DoubleSetting("Points", 20.0D, 5.0D, 100.0D, 1.0D);
   public static final EnumSetting field3 = new EnumSetting("Mode", "Crumbs", new String[]{"Crumbs", "Trail"});

   public TrailModule() {
      super("Trail", Category.render, SubCategory.ui);
      this.method0((Setting[])(new Setting[]{field3, field2}));
   }

   public boolean method3() {
      return field58.thePlayer.posZ != field58.thePlayer.prevPosZ || field58.thePlayer.posY != field58.thePlayer.prevPosY || field58.thePlayer.posX != field58.thePlayer.prevPosX;
   }

   public static int method0(Predicate var0) {
      for(int var1 = 0; var1 < 9; ++var1) {
         if (Oringo.field9.thePlayer.inventory.getStackInSlot(var1) != null && var0.test(Oringo.field9.thePlayer.inventory.getStackInSlot(var1))) {
            return var1;
         }
      }

      return -1;
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      be_.clear();
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field58.thePlayer != null && field58.getRenderManager() != null) {
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.disableTexture2D();
         GlStateManager.disableCull();
         if (field0 != field58.thePlayer.ticksExisted && this.method3()) {
            be_.add(field58.thePlayer.getPositionVector().addVector(0.0D, 0.15D, 0.0D));
            field0 = field58.thePlayer.ticksExisted;
         }

         while((double)be_.size() > field2.method0()) {
            be_.remove(0);
         }

         String var2 = field3.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case 81068518:
            if (var2.equals("Trail")) {
               var3 = 0;
            }
            break;
         case 2027027960:
            if (var2.equals("Crumbs")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
            GL11.glShadeModel(7425);
            GL11.glLineWidth(2.5F);
            GlStateManager.disableLighting();
            GL11.glBegin(3);
            int var4 = 0;
            Iterator var10 = be_.iterator();

            Color var5;
            while(var10.hasNext()) {
               Vec3 var11 = (Vec3)var10.next();
               boolean var12 = var4 == 0;
               ++var4;
               var5 = Class362.field7.method0((float)var4);
               GlStateManager.color((float)var5.getRed() / 255.0F, (float)var5.getGreen() / 255.0F, (float)var5.getBlue() / 255.0F);
               if (var4 != be_.size()) {
                  if (var12 && be_.size() >= 2 && this.method3()) {
                     Vec3 var9 = (Vec3)be_.get(1);
                     GL11.glVertex3d(Class408.method0(var11.xCoord, var9.xCoord, var1.partialTicks) - field58.getRenderManager().viewerPosX, Class408.method0(var11.yCoord, var9.yCoord, var1.partialTicks) - field58.getRenderManager().viewerPosY, Class408.method0(var11.zCoord, var9.zCoord, var1.partialTicks) - field58.getRenderManager().viewerPosZ);
                  } else {
                     GL11.glVertex3d(var11.xCoord - field58.getRenderManager().viewerPosX, var11.yCoord - field58.getRenderManager().viewerPosY, var11.zCoord - field58.getRenderManager().viewerPosZ);
                  }
               }
            }

            GL11.glVertex3d(0.0D, 0.15D, 0.0D);
            var5 = Class362.field7.method0((float)var4);
            GlStateManager.color((float)var5.getRed() / 255.0F, (float)var5.getGreen() / 255.0F, (float)var5.getBlue() / 255.0F);
            GL11.glVertex3d(field58.thePlayer.prevPosX + (field58.thePlayer.posX - field58.thePlayer.prevPosX) * (double)var1.partialTicks - field58.getRenderManager().viewerPosX, field58.thePlayer.prevPosY + (field58.thePlayer.posY - field58.thePlayer.prevPosY) * (double)var1.partialTicks - field58.getRenderManager().viewerPosY + 0.1D, field58.thePlayer.prevPosZ + (field58.thePlayer.posZ - field58.thePlayer.prevPosZ) * (double)var1.partialTicks - field58.getRenderManager().viewerPosZ);
            GL11.glEnd();
            GL11.glShadeModel(7424);
            GL11.glLineWidth(1.0F);
            break;
         case 1:
            Color var6 = Class362.field7.method17();
            Collections.reverse(be_);
            Iterator var7 = be_.iterator();

            while(var7.hasNext()) {
               Vec3 var8 = (Vec3)var7.next();
               GL11.glPushMatrix();
               var8 = Class486.method0(var8);
               GL11.glTranslated(var8.xCoord, var8.yCoord, var8.zCoord);
               GL11.glRotated((double)(-field58.getRenderManager().playerViewY), 0.0D, 1.0D, 0.0D);
               GL11.glRotated((double)field58.getRenderManager().playerViewX, 1.0D, 0.0D, 0.0D);
               GlStateManager.color((float)var6.getRed() / 255.0F, (float)var6.getGreen() / 255.0F, (float)var6.getBlue() / 255.0F, 0.3F);
               DoorESPModule.method0(0.07D);
               GlStateManager.color((float)var6.getRed() / 255.0F, (float)var6.getGreen() / 255.0F, (float)var6.getBlue() / 255.0F, 1.0F);
               DoorESPModule.method0(0.04D);
               GL11.glPopMatrix();
            }

            Collections.reverse(be_);
         }

         GlStateManager.enableCull();
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
      } else {
         be_.clear();
      }

   }
}
