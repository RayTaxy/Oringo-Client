package oringo.module;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import map.Class256;
import map.Class34;
import map.Class465;
import map.Class479;
import map.Class496;
import map.Class506;
import map.Class6;
import map.Class83;
import map.Class92;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.LogsCommand;
import oringo.command.MessageCommand;
import oringo.command.MoveItemCommand;
import oringo.command.ReplyCommand;
import oringo.mixin.GuiPlayerTabOverlayAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AimAssistModule extends Module {
   public final BooleanSetting field0 = (BooleanSetting)(new BooleanSetting("Players", true)).method2("Allows targeting players");
   public final BooleanSetting ay_ = (BooleanSetting)(new BooleanSetting("Invisibles", false)).method2("Allows target to be invisible");
   public final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Vertical", true)).method2("Modifies pitch");
   public final DoubleSetting field3 = new AimAssistModule$1(this, "Max speed", 30.0D, 1.0D, 40.0D, 0.1D);
   public final BooleanSetting field4 = (BooleanSetting)(new BooleanSetting("Teams", true)).method2("Checks if target is a teammate");
   public final DoubleSetting field5 = new DoubleSetting("Range", 5.0D, 0.0D, 6.0D, 0.1D);
   public final BooleanSetting field6 = (BooleanSetting)(new BooleanSetting("Click aim", false)).method2("Only aims when attack button is pressed");
   public final BooleanSetting field7 = (BooleanSetting)(new BooleanSetting("Mobs", false)).method2("Allows targeting non-players");
   public final DoubleSetting field8 = new DoubleSetting("Fov", 60.0D, 30.0D, 360.0D, 1.0D);
   public final DoubleSetting field9 = new AimAssistModule$2(this, "Min speed", 20.0D, 1.0D, 40.0D, 0.1D);

   public Entity method2() {
      List var1 = field58.theWorld.getEntities(EntityLivingBase.class, this::lambda$getTarget$0);
      var1.sort(Comparator.comparingDouble(AimAssistModule::lambda$getTarget$1));
      return !var1.isEmpty() ? (Entity)var1.get(0) : null;
   }

   public boolean a_(EntityLivingBase var1) {
      if (var1 != field58.thePlayer && Class506.method0((Entity)var1) && (this.ay_.method1() || !var1.isInvisible()) && !(var1 instanceof EntityArmorStand) && field58.thePlayer.canEntityBeSeen(var1) && var1.getHealth() > 0.0F && (double)var1.getDistanceToEntity(field58.thePlayer) <= this.field5.method0() && (double)Math.abs(MathHelper.wrapAngleTo180_float(field58.thePlayer.rotationYaw) - MathHelper.wrapAngleTo180_float(this.method2(var1).method5())) <= this.field8.method0()) {
         if (LogsCommand.method2().method44()) {
            boolean var2 = MoveItemCommand.method0((Entity)var1);
            if (NamesOnlyModule.bs_.method0(1) || var2) {
               return NamesOnlyModule.bs_.method0(1) && var2;
            }
         }

         if ((var1 instanceof EntityMob || var1 instanceof EntityVillager || var1 instanceof EntitySnowman || var1 instanceof EntityAmbientCreature || var1 instanceof EntityWaterMob || var1 instanceof EntityAnimal || var1 instanceof EntitySlime) && !this.field7.method1()) {
            return false;
         } else if (!(var1 instanceof EntityPlayer) || (!Class6.method0(var1) || !this.field4.method1()) && this.field0.method1()) {
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean lambda$getTarget$0(Entity var1) {
      return this.a_((EntityLivingBase)var1);
   }

   public static boolean method3() {
      IChatComponent var0 = ((GuiPlayerTabOverlayAccessor)Class496.field26.ingameGUI.getTabList()).getFooter();
      if (var0 == null) {
         return false;
      } else {
         return !var0.getUnformattedText().toLowerCase().contains("not active");
      }
   }

   public static void method0(EntityLivingBase var0, Color var1, float var2) {
      GL11.glPushMatrix();
      float var3 = (float)((Math.sin((double)System.currentTimeMillis() * 0.005D) + 1.0D) * 0.5D);
      GlStateManager.translate(var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * (double)var2 - Oringo.field9.getRenderManager().viewerPosX, var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * (double)var2 - Oringo.field9.getRenderManager().viewerPosY + (double)(var0.height * var3), var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * (double)var2 - Oringo.field9.getRenderManager().viewerPosZ);
      GlStateManager.disableTexture2D();
      GlStateManager.disableCull();
      GlStateManager.disableLighting();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.shadeModel(7425);
      GL11.glLineWidth(3.0F);
      GlStateManager.color((float)var1.getRed() / 255.0F, (float)var1.getGreen() / 255.0F, (float)var1.getBlue() / 255.0F, 1.0F);
      Class479.field3.begin(3, DefaultVertexFormats.POSITION);
      double var4 = Math.cos((double)System.currentTimeMillis() * 0.005D);

      int var6;
      double var7;
      double var9;
      for(var6 = 0; var6 <= 120; ++var6) {
         var7 = Math.cos((double)var6 * 3.141592653589793D / 60.0D) * (double)var0.width;
         var9 = Math.sin((double)var6 * 3.141592653589793D / 60.0D) * (double)var0.width;
         Class479.field3.pos(var7, 0.15000000596046448D * var4, var9).endVertex();
      }

      Class479.field1.draw();
      Class479.field3.begin(5, DefaultVertexFormats.POSITION_COLOR);

      for(var6 = 0; var6 <= 120; ++var6) {
         var7 = Math.cos((double)var6 * 3.141592653589793D / 60.0D) * (double)var0.width;
         var9 = Math.sin((double)var6 * 3.141592653589793D / 60.0D) * (double)var0.width;
         Class479.field3.pos(var7, 0.15000000596046448D * var4, var9).color((float)var1.getRed() / 255.0F, (float)var1.getGreen() / 255.0F, (float)var1.getBlue() / 255.0F, 0.5F).endVertex();
         Class479.field3.pos(var7, -0.15000000596046448D * var4, var9).color((float)var1.getRed() / 255.0F, (float)var1.getGreen() / 255.0F, (float)var1.getBlue() / 255.0F, 0.2F).endVertex();
      }

      Class479.field1.draw();
      AutoCloseModule.method5();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableCull();
      GlStateManager.enableTexture2D();
      GL11.glPopMatrix();
   }

   public static double lambda$getTarget$1(Entity var0) {
      return (double)Class92.method0(Class256.method1().method5(), MessageCommand.method0(var0).method5());
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method44() && (!this.field6.method1() || field58.gameSettings.keyBindAttack.isKeyDown())) {
         Entity var2 = this.method2();
         if (var2 != null && field58.objectMouseOver != null && field58.objectMouseOver.entityHit != var2) {
            Class34 var3 = this.method2(var2);
            Vec3 var4 = field58.thePlayer.getPositionEyes(1.0F);
            Vec3 var5 = AutoFrozilleModule.method0(field58.thePlayer.rotationYaw, field58.thePlayer.rotationPitch);
            Vec3 var6 = var4.addVector(var5.xCoord * this.field5.method0() + 2.0D, var5.yCoord * this.field5.method0() + 2.0D, var5.zCoord * this.field5.method0() + 2.0D);
            if (var2.getEntityBoundingBox().expand(-0.1D, -0.1D, -0.1D).calculateIntercept(var4, var6) != null) {
               return;
            }

            double var7 = Class83.method0(this.field3.method0(), this.field9.method0()) * (double)Minecraft.getDebugFPS() / 20.0D;
            double var9 = Class83.method0(this.field3.method0(), this.field9.method0()) * (double)Minecraft.getDebugFPS() / 20.0D;
            float var11 = field58.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(var3.method5() - field58.thePlayer.rotationYaw);
            float var12 = field58.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(var3.method2() - field58.thePlayer.rotationPitch);
            float var13 = (float)((double)(var11 - field58.thePlayer.rotationYaw) / var7);
            float var14 = (float)((double)(var12 - field58.thePlayer.rotationPitch) / var9);
            field58.thePlayer.rotationYaw += var13;
            if (this.field2.method1()) {
               field58.thePlayer.rotationPitch += var14;
            }
         }
      }

   }

   public AimAssistModule() {
      super("Aim Assist", Category.combat, SubCategory.combat, "Aims at enemies");
      this.method0(new Setting[]{this.field8, this.field5, this.field9, this.field3, this.field0, this.field7, this.field4, this.ay_, this.field2, this.field6});
   }

   public Class34 method2(Entity var1) {
      if (var1 != null) {
         Vec3 var2 = field58.thePlayer.getPositionEyes(1.0F);
         Vec3 var3 = AutoFrozilleModule.method0(field58.thePlayer.rotationYaw, field58.thePlayer.rotationPitch);
         Vec3 var4 = var2.addVector(var3.xCoord, var3.yCoord, var3.zCoord);
         return ReplyCommand.method0(Class465.method0(var4, var1.getEntityBoundingBox()));
      } else {
         return null;
      }
   }
}
