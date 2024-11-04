package oringo.module;

import java.awt.Color;
import map.Class362;
import map.Class447;
import map.Class479;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.command.MessageCommand;
import oringo.event.Class419;
import oringo.event.Class468;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class TargetStrafeModule extends Module {
   public final BooleanSetting bX_ = new BooleanSetting("Space only", true, "Only strafe when space is held");
   public final EnumSetting bY_ = new EnumSetting("Mode", "Normal", new String[]{"Normal", "Back"});
   public final BooleanSetting field2 = new BooleanSetting("Smart", true);
   public final BooleanSetting field3 = new BooleanSetting("Liquid check", false, this::lambda$new$0);
   public final BooleanSetting field4 = new BooleanSetting("Void check", true, this::lambda$new$1);
   public final Class447 field5 = new Class447();
   public final BooleanSetting field6 = new BooleanSetting("Third person", false, "Change the perspective when strafing");
   public double field7;
   public final DoubleSetting field8 = (DoubleSetting)(new DoubleSetting("Distance", 2.0D, 1.0D, 4.0D, 0.1D)).method2("Distance at the player should strafe");
   public int field9 = -1;
   public double field10;
   public final BooleanSetting field11 = new BooleanSetting("Controllable", true, "Allows you to change strafe direction");
   public float field0 = 1.0F;

   public double method0(double var1, double var3, double var5, double var7) {
      double var9 = var5 - var1;
      double var11 = var7 - var3;
      return Math.sqrt(var9 * var9 + var11 * var11);
   }

   public static boolean method5() {
      return AutoHarpModule.field58.thePlayer != null && AutoHarpModule.field58.thePlayer.openContainer instanceof ContainerChest && ((ContainerChest)AutoHarpModule.field58.thePlayer.openContainer).getLowerChestInventory().getName().startsWith("Harp - ");
   }

   @SubscribeEvent
   public void method0(Class419 var1) {
      if (this.method7()) {
         var1.method0(MessageCommand.method0((Entity)KillAuraModule.field24).method5());
      }

   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method7()) {
         EntityLivingBase var2 = KillAuraModule.field24;
         float var3 = var1.partialTicks;
         GL11.glPushMatrix();
         GlStateManager.enableBlend();
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glEnable(2848);
         GL11.glHint(3154, 4354);
         GlStateManager.disableTexture2D();
         GlStateManager.disableCull();
         GlStateManager.disableDepth();
         GL11.glShadeModel(7425);
         GlStateManager.disableLighting();
         GL11.glTranslated(var2.lastTickPosX + (var2.posX - var2.lastTickPosX) * (double)var3 - field58.getRenderManager().viewerPosX, var2.lastTickPosY + (var2.posY - var2.lastTickPosY) * (double)var3 - field58.getRenderManager().viewerPosY + 0.1D, var2.lastTickPosZ + (var2.posZ - var2.lastTickPosZ) * (double)var3 - field58.getRenderManager().viewerPosZ);
         double var4 = this.field8.method0();
         GL11.glLineWidth(4.0F);
         GL11.glBegin(2);
         byte var6 = 90;

         for(int var7 = 0; var7 <= var6; ++var7) {
            Color var8 = Color.white;
            GlStateManager.color((float)var8.getRed() / 255.0F, (float)var8.getGreen() / 255.0F, (float)var8.getBlue() / 255.0F, 1.0F);
            GL11.glVertex3d(Math.cos((double)var7 * 3.141592653589793D / ((double)var6 / 2.0D)) * var4, 0.0D, Math.sin((double)var7 * 3.141592653589793D / ((double)var6 / 2.0D)) * var4);
         }

         GL11.glEnd();
         GL11.glPopMatrix();
         GL11.glShadeModel(7424);
         GlStateManager.enableDepth();
         GlStateManager.enableCull();
         GlStateManager.resetColor();
         GlStateManager.enableTexture2D();
         GlStateManager.disableBlend();
         GL11.glDisable(2848);
      }

   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = 1.0F / var6;
      float var9 = 1.0F / var7;
      Class479.field3.begin(7, DefaultVertexFormats.POSITION_TEX);
      Class479.field3.pos((double)var0, (double)(var1 + var5), 0.0D).tex((double)(var2 * var8), (double)((var3 + var5) * var9)).endVertex();
      Class479.field3.pos((double)(var0 + var4), (double)(var1 + var5), 0.0D).tex((double)((var2 + var4) * var8), (double)((var3 + var5) * var9)).endVertex();
      Class479.field3.pos((double)(var0 + var4), (double)var1, 0.0D).tex((double)((var2 + var4) * var8), (double)(var3 * var9)).endVertex();
      Class479.field3.pos((double)var0, (double)var1, 0.0D).tex((double)(var2 * var8), (double)(var3 * var9)).endVertex();
      Class479.field1.draw();
   }

   public Boolean lambda$new$1() {
      return !this.field2.method1();
   }

   public boolean method7() {
      return KillAuraModule.field24 != null && this.method44() && (field58.gameSettings.keyBindJump.isKeyDown() || !this.bX_.method1()) && (Class362.field20.method44() && !DragonHitboxesModule.method6() || Class362.field5.method6());
   }

   public Boolean lambda$new$0() {
      return !this.field2.method1();
   }

   @SubscribeEvent
   public void method0(Class468 var1) {
      if (!this.method7() || field58.currentScreen != null && !((GuiMoveModule)Class362.method0(GuiMoveModule.class)).method44()) {
         if (this.field6.method1() && this.field9 != -1) {
            field58.gameSettings.thirdPersonView = this.field9;
            this.field9 = -1;
         }
      } else {
         if (this.field6.method1()) {
            if (this.field9 == -1) {
               this.field9 = field58.gameSettings.thirdPersonView;
            }

            field58.gameSettings.thirdPersonView = 1;
         }

         double var2 = 0.0D;
         double var4 = 0.0D;
         if (!this.field11.method1() || !field58.gameSettings.keyBindRight.isKeyDown() && !field58.gameSettings.keyBindLeft.isKeyDown()) {
            if (this.field5.a_(200L)) {
               if (!field58.thePlayer.isCollidedHorizontally && (!this.field2.method1() || Class362.field5.method6() || (!this.field4.method1() || (double)field58.thePlayer.fallDistance >= 2.5D || !GardenHelperModule.method0(6.0F, (field58.thePlayer.posX - field58.thePlayer.prevPosX) * 2.5D, (field58.thePlayer.posZ - field58.thePlayer.prevPosZ) * 2.5D)) && (!this.field3.method1() || field58.thePlayer.isInLava() || field58.thePlayer.isInWater() || !RodStackerModule.method0(3.0F, (field58.thePlayer.posX - field58.thePlayer.prevPosX) * 2.5D, (field58.thePlayer.posZ - field58.thePlayer.prevPosZ) * 2.5D)))) {
                  if (this.bY_.method0(1)) {
                     EntityLivingBase var6 = KillAuraModule.field24;
                     float var7 = (var6.rotationYaw - 90.0F) % 360.0F;
                     var2 = Math.cos((double)var7 * 3.141592653589793D / 180.0D) * this.field8.method0() + var6.posX;
                     var4 = Math.sin((double)var7 * 3.141592653589793D / 180.0D) * this.field8.method0() + var6.posZ;
                     if (this.method0(var2, var4, field58.thePlayer.posX, field58.thePlayer.posZ) > this.method0(this.field7, this.field10, var2, var4)) {
                        this.field0 = -this.field0;
                        this.field5.o_();
                     }

                     this.field7 = field58.thePlayer.posX;
                     this.field10 = field58.thePlayer.posZ;
                  }
               } else {
                  this.field0 = -this.field0;
                  this.field5.o_();
               }
            }
         } else {
            if (field58.gameSettings.keyBindLeft.isKeyDown()) {
               this.field0 = 1.0F;
            }

            if (field58.gameSettings.keyBindRight.isKeyDown()) {
               this.field0 = -1.0F;
            }
         }

         if (this.method1(KillAuraModule.field24) <= this.field8.method0() + 2.0D || this.field11.method1() && (field58.gameSettings.keyBindRight.isKeyDown() || field58.gameSettings.keyBindLeft.isKeyDown())) {
            var1.method1(this.field0);
         }

         var1.method0(!field58.gameSettings.keyBindBack.isKeyDown() ? (float)(this.method1(KillAuraModule.field24) <= this.field8.method0() ? 0 : 1) : -1.0F);
         if (this.bY_.method0(1) && this.method0(var2, var4, field58.thePlayer.posX, field58.thePlayer.posZ) <= 1.2D && (!this.field11.method1() || !field58.gameSettings.keyBindRight.isKeyDown() && !field58.gameSettings.keyBindLeft.isKeyDown())) {
            var1.method1(0.0F);
         }
      }

   }

   public TargetStrafeModule() {
      super("Target Strafe", Category.combat, SubCategory.combat, "Strafes around kill aura's target");
      this.method0((Setting[])(new Setting[]{this.bY_, this.field8, this.field6, this.field2, this.field4, this.field3, this.field11, this.bX_}));
   }

   public double method1(Entity var1) {
      return Math.hypot(var1.posX - field58.thePlayer.posX, var1.posZ - field58.thePlayer.posZ);
   }

   public static NBTTagCompound method0(ItemStack var0) {
      return var0 != null && var0.hasTagCompound() ? var0.getSubCompound("ExtraAttributes", false) : null;
   }
}
