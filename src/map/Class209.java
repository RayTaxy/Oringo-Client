package map;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import oringo.module.AutoRequeueModule;
import oringo.module.LightningDetectModule;
import oringo.module.MurderMysteryModule;
import oringo.module.NBTCopyModule;
import oringo.module.NoCarpetModule;
import oringo.module.ScoreboardModule;
import oringo.module.ScreenShotModule;
import oringo.setting.BooleanSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;

public class Class209 extends Class283 {
   public final BooleanSetting field2;
   public final EnumSetting bC_;
   public static final Pattern field4 = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");
   public final BooleanSetting field5;
   public final BooleanSetting field6;
   public final BooleanSetting bD_;
   public final BooleanSetting bE_;
   public final EnumSetting field9;
   public final BooleanSetting field10;
   public final BooleanSetting field11;
   public final ColorSetting field12;
   public final Class354 field13 = new Class354("ScoreboardText.frag", "Vertex.vert");

   public float method0() {
      return this.field6.method1() ? Class311.field12.method7() : (float)field0.fontRendererObj.FONT_HEIGHT;
   }

   public void method16() {
      this.method0(true);
      this.field8 = this.k_() - (float)method6() + (this.bC_.method0("Right") ? this.method7() : 0.0F);
      this.field7 = this.method15() - (float)t_();
   }

   public ScoreObjective method17() {
      Scoreboard var1 = field0.theWorld.getScoreboard();
      ScoreObjective var2 = null;
      ScorePlayerTeam var3 = var1.getPlayersTeam(field0.thePlayer.getName());
      if (var3 != null) {
         int var4 = var3.getChatFormat().getColorIndex();
         if (var4 >= 0) {
            var2 = var1.getObjectiveInDisplaySlot(3 + var4);
         }
      }

      return var2 != null ? var2 : var1.getObjectiveInDisplaySlot(1);
   }

   public void method0(String var1, float var2, float var3, int var4) {
      if (this.field6.method1()) {
         Class311.field12.method0(var1, var2, var3, var4);
      } else {
         field0.fontRendererObj.drawStringWithShadow(var1, var2 - (float)field0.fontRendererObj.getStringWidth(var1) / 2.0F, var3, var4);
      }

   }

   public static boolean lambda$renderScoreboard$0(Score var0) {
      return var0.getPlayerName() == null || var0.getPlayerName().startsWith("#");
   }

   public float k_() {
      return super.k_() - (this.bC_.method0("Right") ? this.method7() : 0.0F);
   }

   public float method0(String var1) {
      return this.field6.method1() ? Class311.field12.method0(var1) + 1.0F : (float)field0.fontRendererObj.getStringWidth(var1);
   }

   public void method0(ScoreObjective var1) {
      ScaledResolution var2 = i_();
      Scoreboard var3 = var1.getScoreboard();
      Object var4 = (List)var3.getSortedScores(var1);
      ((List)var4).removeIf(Class209::lambda$renderScoreboard$0);
      if (((List)var4).size() > 15) {
         var4 = Lists.newArrayList(Iterables.skip((Iterable)var4, ((List)var4).size() - 15));
      }

      Collections.reverse((List)var4);
      float var5 = this.method0(var1.getDisplayName());

      String var8;
      for(Iterator var6 = ((List)var4).iterator(); var6.hasNext(); var5 = Math.max(var5, this.method0(var8))) {
         Score var7 = (Score)var6.next();
         var8 = ScorePlayerTeam.formatPlayerName(var3.getPlayersTeam(var7.getPlayerName()), var7.getPlayerName());
      }

      byte var17 = 3;
      float var18 = (float)(((List)var4).size() + 1) * this.method0();
      if (this.field6.method1()) {
         var18 -= 2.0F;
      }

      if (this.field5.method1()) {
         this.method1((float)(var2.getScaledWidth() - var17 - 2));
         this.method0((float)var2.getScaledHeight() / 2.0F - var18 * 2.0F / 3.0F);
      }

      this.method1(var5, var18);
      float var19 = this.method15();
      float var9 = this.k_();
      if (this.bE_.method1() && this.field11.method1()) {
         GlStateManager.disableAlpha();

         for(float var10 = 0.5F; var10 < 3.0F; var10 += 0.5F) {
            float var11 = (double)var9 > (double)var2.getScaledWidth() / 2.0D ? var10 : -var10;
            NBTCopyModule.method0(var9 - (float)var17 - var11, var19 - (float)var17 + var10, var5 + (float)(var17 * 2), var18 + (float)(var17 * 2), 5.0F, new Color(20, 20, 20, 10));
         }

         GlStateManager.enableAlpha();
      }

      if (this.bD_.method1()) {
         if (this.field9.method0("Gui Color") && this.field2.method1()) {
            LightningDetectModule.method0(var9 - (float)var17, var19 - (float)var17, var5 + (float)(var17 * 2), var18 + (float)(var17 * 2), 5.0F, 1.5F);
         } else {
            MurderMysteryModule.method0(var9 - (float)var17, var19 - (float)var17, var5 + (float)(var17 * 2), var18 + (float)(var17 * 2), 5.0F, 1.5F, this.field9.method0("Static Color") ? this.field12.method17() : Class362.field7.method17());
         }
      } else if (this.field11.method1()) {
         NoCarpetModule.method0(var9 - (float)var17, var19 - (float)var17, var5 + (float)(var17 * 2), var18 + (float)(var17 * 2), 5.0F);
      }

      if (this.j_() && field0.currentScreen instanceof GuiChat) {
         AutoRequeueModule.method0(var9 - (float)var17, var19 - (float)var17, var5 + (float)(var17 * 2), var18 + (float)(var17 * 2), 10.0F, 2.0F, (new Color(50, 50, 50, 30)).getRGB(), -1);
      } else {
         NBTCopyModule.method0(var9 - (float)var17, var19 - (float)var17, var5 + (float)(var17 * 2), var18 + (float)(var17 * 2), 5.0F, new Color(50, 50, 50, 30));
      }

      if (this.field6.method1()) {
         Class311.field12.method5();
      }

      this.method0(var1.getDisplayName(), var9 + var5 / 2.0F, var19, -1);
      int var20 = 0;
      Iterator var21 = ((List)var4).iterator();

      while(true) {
         while(var21.hasNext()) {
            Score var12 = (Score)var21.next();
            ++var20;
            String var13 = var12.getPlayerName();
            if (var13.length() == 1) {
               var13 = "";
            }

            String var14 = ScorePlayerTeam.formatPlayerName(var3.getPlayersTeam(var12.getPlayerName()), var13);
            Matcher var15 = field4.matcher(var14);
            if (this.field10.method1() && var15.find()) {
               var14 = ChatFormatting.GRAY + var15.group();
            } else if (var14.equals("§ewww.hypixel.ne\ud83c\udf82§et")) {
               if (this.field6.method1()) {
                  Class311.field12.method6();
               }

               if (this.field2.method1()) {
                  this.field13.method5();
                  Class362.field7.field0(this.field13);
                  ScaledResolution var16 = new ScaledResolution(field0);
                  this.field13.method0("middle", var9 * (float)var16.getScaleFactor(), ((float)var16.getScaledHeight() - var19 - var18) * (float)var16.getScaleFactor());
                  this.field13.method0("range", var5 * (float)var16.getScaleFactor(), var18 * (float)var16.getScaleFactor());
                  this.method0("Oringo Client", var9 + var5 / 2.0F, var19 + this.method0() * (float)var20, -1);
                  this.field13.method2();
               } else {
                  this.method0("Oringo Client", var9 + var5 / 2.0F, var19 + this.method0() * (float)var20, Class362.field7.method17().getRGB());
               }
               continue;
            }

            this.method1(var14, var9, var19 + this.method0() * (float)var20, -1);
         }

         if (this.field6.method1()) {
            Class311.field12.method6();
         }

         return;
      }
   }

   public void method2() {
      if (!ScreenShotModule.field8 || !Class362.field0.field11.method1()) {
         ScoreObjective var1 = this.method17();
         if (var1 != null) {
            super.method2();
            this.method0(var1);
         }
      }
   }

   public boolean method1() {
      return ((ScoreboardModule)Class362.method0(ScoreboardModule.class)).method44();
   }

   public Class209(DoubleSetting var1, DoubleSetting var2, BooleanSetting var3, BooleanSetting var4, BooleanSetting var5, BooleanSetting var6, BooleanSetting var7, BooleanSetting var8, ColorSetting var9, EnumSetting var10, BooleanSetting var11, EnumSetting var12) {
      super(var1, var2);
      this.field6 = var3;
      this.field11 = var4;
      this.bE_ = var5;
      this.field5 = var6;
      this.field10 = var7;
      this.bD_ = var8;
      this.field12 = var9;
      this.field9 = var10;
      this.field2 = var11;
      this.bC_ = var12;
   }

   public void method1(String var1, float var2, float var3, int var4) {
      if (this.field6.method1()) {
         Class311.field12.method3(var1, var2, var3, var4);
      } else {
         field0.fontRendererObj.drawStringWithShadow(var1, var2, var3, var4);
      }

   }
}
