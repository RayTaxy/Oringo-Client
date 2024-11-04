package oringo.module;

import java.awt.Color;
import java.util.List;
import java.util.Objects;
import map.Class118;
import map.Class228;
import map.Class350;
import map.Class351;
import map.Class352;
import map.Class361;
import map.Class362;
import map.Class388;
import map.Class94;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class125;
import oringo.event.Class210;
import oringo.event.Class274;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class MinigameAimbotModule extends Module {
   public EntityOtherPlayerMP field0 = null;
   public boolean cz_ = false;
   public final BooleanSetting field2 = new BooleanSetting("Ping based prediction", true);
   public final BooleanSetting field3 = new BooleanSetting("Color check", true);

   public MinigameAimbotModule() {
      super("Minigame Aimbot", Category.other, SubCategory.other, "Aimbot for Cops and Crims and Quake");
      this.method0((Setting[])(new Setting[]{this.field3, this.field2}));
   }

   public EntityOtherPlayerMP method5() {
      Class228 var1 = (new Class228(field58.thePlayer.getLookVec())).method1(0.2D);
      Class228 var2 = new Class228(field58.thePlayer.getPositionEyes(1.0F));

      for(int var3 = 0; var3 < 1000; ++var3) {
         var2 = var2.method4(var1);
         List var5 = field58.theWorld.getPlayers(EntityOtherPlayerMP.class, this::lambda$getTarget$0);
         if (!var5.isEmpty()) {
            return (EntityOtherPlayerMP)var5.get(0);
         }
      }

      return null;
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
      if (this.field0 != null) {
         if (field58.objectMouseOver != null && field58.objectMouseOver.typeOfHit == MovingObjectType.MISS) {
            this.cz_ = true;
            var1.method9();
         }
      }
   }

   @SubscribeEvent
   public void method0(Class125 var1) {
      if (this.field0 == var1.field4) {
         method0(var1, Color.CYAN);
      }

   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      this.field0 = this.method5();
      if (this.field0 == null) {
         this.cz_ = false;
      } else if (this.cz_) {
         double var2 = (double)Class362.field7.field8 / 30.0D;
         if (!this.field2.method1()) {
            var2 = 4.5D;
         }

         Class228 var4 = (new Class228(this.field0.posX - this.field0.lastTickPosX, this.field0.posY - this.field0.lastTickPosY, this.field0.posZ - this.field0.lastTickPosZ)).method1(var2, 2.0D, var2);
         if (Class361.method0((Class94)(new Class350()))) {
            var1.method0(Class352.method0(this.field0.posX + var4.field2, this.field0.posY + (double)this.field0.getEyeHeight() + var4.field1, this.field0.posZ + var4.field0));
            this.cz_ = false;
         }

      }
   }

   public boolean lambda$getTarget$0(Class228 var1, EntityOtherPlayerMP var2) {
      float var3 = var2.getDistanceToEntity(field58.thePlayer);
      if (var2.getEntityBoundingBox().expand((double)(2.0F + var3 / 7.0F), (double)(2.0F + var3 / 20.0F), (double)(2.0F + var3 / 7.0F)).isVecInside(var1.method6()) && Class118.method0(var2)) {
         if (this.field3.method1()) {
            Team var4 = field58.thePlayer.getTeam();
            Team var5 = var2.getTeam();
            if (var4 instanceof ScorePlayerTeam && var5 instanceof ScorePlayerTeam && Objects.equals(((ScorePlayerTeam)var4).getColorPrefix(), ((ScorePlayerTeam)var5).getColorPrefix())) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, Color var5, Color var6) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableAlpha();
      Class388.field2.method5();
      Class388.field2.method0("progress", var4);
      Class388.field2.method0("color", var5);
      Class388.field2.method0("color1", var6);
      Class351.field0(Class388.field2, var2 * 2.0F, var2 * 2.0F, var2);
      BossBarModule.method0(var0, var1, var2 * 2.0F, var2 * 2.0F);
      Class388.field2.method2();
      NoCarpetModule.method0(var0 + var3, var1 + var3, var2 * 2.0F - var3 * 2.0F, var2 * 2.0F - var3 * 2.0F, var2 - var3);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field0 != null) {
         double var2 = (double)Class362.field7.field8 / 30.0D;
         if (!this.field2.method1()) {
            var2 = 4.5D;
         }

         Class228 var4 = (new Class228(this.field0.posX - this.field0.lastTickPosX, this.field0.posY - this.field0.lastTickPosY, this.field0.posZ - this.field0.lastTickPosZ)).method1(var2, 2.0D, var2);
         AutoReadyModule.method0(this.field0.getPositionEyes(var1.partialTicks).add(var4.method6()), Color.GREEN);
      }

   }

   public static void method0(Class125 var0, Color var1) {
      Minecraft var2 = Minecraft.getMinecraft();
      boolean var3 = var2.gameSettings.fancyGraphics;
      float var4 = var2.gameSettings.gammaSetting;
      var2.gameSettings.fancyGraphics = false;
      var2.gameSettings.gammaSetting = 100000.0F;
      GlStateManager.disableLighting();
      GlStateManager.resetColor();
      Class351.method0(var1);
      ModHiderModule.method0(3.0F);
      var0.field7.render(var0.field4, var0.field0, var0.field5, var0.field3, var0.field6, var0.field2, var0.field1);
      Class351.method0(var1);
      MurderMysteryModule.method1();
      var0.field7.render(var0.field4, var0.field0, var0.field5, var0.field3, var0.field6, var0.field2, var0.field1);
      Class351.method0(var1);
      NamesOnlyModule.method3();
      var0.field7.render(var0.field4, var0.field0, var0.field5, var0.field3, var0.field6, var0.field2, var0.field1);
      Class351.method0(var1);
      NBTCopyModule.method0(var1);
      var0.field7.render(var0.field4, var0.field0, var0.field5, var0.field3, var0.field6, var0.field2, var0.field1);
      Class351.method0(var1);
      NoCarpetModule.method5();
      GlStateManager.resetColor();
      GlStateManager.disableBlend();
      GlStateManager.disableLighting();
      var2.gameSettings.fancyGraphics = var3;
      var2.gameSettings.gammaSetting = var4;
   }
}
