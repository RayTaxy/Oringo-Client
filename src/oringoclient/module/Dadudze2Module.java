package oringo.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import map.Class362;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.BloodSkipCommand;
import oringo.command.MessageCommand;
import oringo.event.Class217;

public class Dadudze2Module extends Module {
   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(Post var1) {
      if (this.method1(var1.entity)) {
         String var2 = String.valueOf(this.method0(var1.entity));
         GlStateManager.disableDepth();
         AutoCrystalModule.method0(var1.entity, var2, var1.x, var1.y + 0.35D, var1.z, 64);
         GlStateManager.enableDepth();
      }
   }

   public Object method0(EntityLivingBase var1) {
      return var1.getClass().getSimpleName() + ": " + var1.getEntityId();
   }

   public static void method0(Class217 var0, double var1) {
      double var3 = (double)Oringo.field9.thePlayer.movementInput.moveForward;
      double var5 = (double)Oringo.field9.thePlayer.movementInput.moveStrafe;
      float var7 = KillAuraModule.field24 != null && KillAuraModule.field12.method1() ? MessageCommand.method0((Entity)KillAuraModule.field24).method5() : Oringo.field9.thePlayer.rotationYaw;
      if (var3 == 0.0D && var5 == 0.0D) {
         Oringo.field9.thePlayer.motionX = 0.0D;
         Oringo.field9.thePlayer.motionZ = 0.0D;
         if (var0 != null) {
            var0.method2(0.0D);
            var0.method1(0.0D);
         }
      } else {
         var3 = var3 < 0.0D ? -1.0D : (var3 > 0.0D ? 1.0D : 0.0D);
         var5 = var5 < 0.0D ? -1.0D : (var5 > 0.0D ? 1.0D : 0.0D);
         if (var3 != 0.0D) {
            if (var5 > 0.0D) {
               var7 += (float)(var3 > 0.0D ? -45 : 45);
            } else if (var5 < 0.0D) {
               var7 += (float)(var3 > 0.0D ? 45 : -45);
            }

            var5 = 0.0D;
         }

         double var8 = Math.cos(Math.toRadians((double)(var7 + 90.0F)));
         double var10 = Math.sin(Math.toRadians((double)(var7 + 90.0F)));
         Oringo.field9.thePlayer.motionX = var3 * var1 * var8 + var5 * var1 * var10;
         Oringo.field9.thePlayer.motionZ = var3 * var1 * var10 - var5 * var1 * var8;
         if (var0 != null) {
            var0.method2(Oringo.field9.thePlayer.motionX);
            var0.method1(Oringo.field9.thePlayer.motionZ);
         }
      }

   }

   public static void method0(IChatComponent var0) {
      ExtraFeaturesModule var1 = Class362.field26;
      if (var1.method44() && var1.v_.method1()) {
         if (!PacketLoggerModule.method0(var0)) {
            Iterator var2 = var0.getSiblings().iterator();

            IChatComponent var3;
            do {
               if (!var2.hasNext()) {
                  return;
               }

               var3 = (IChatComponent)var2.next();
            } while(!PacketLoggerModule.method0(var3));

         }
      }
   }

   public static int method5() {
      if (BloodSkipCommand.field9.thePlayer != null && BloodSkipCommand.field9.thePlayer.getWorldScoreboard() != null) {
         Scoreboard var0 = BloodSkipCommand.field9.thePlayer.getWorldScoreboard();
         ArrayList var1 = new ArrayList(var0.getSortedScores(var0.getObjectiveInDisplaySlot(1)));
         Iterator var2 = var1.iterator();

         while(var2.hasNext()) {
            Score var3 = (Score)var2.next();
            StringBuilder var4 = new StringBuilder();
            ScorePlayerTeam var5 = var0.getPlayersTeam(var3.getPlayerName());
            String var6 = var5.getColorPrefix() + var3.getPlayerName() + var5.getColorSuffix();
            char[] var7 = var6.toCharArray();
            int var8 = var7.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               char var10 = var7[var9];
               if (var10 < 256) {
                  var4.append(var10);
               }
            }

            Matcher var11 = BloodSkipCommand.field0.matcher(var4.toString());
            if (var11.find()) {
               return Integer.parseInt(var11.group(2));
            }
         }
      }

      return 0;
   }

   public Dadudze2Module() {
      super("Dadudze 2", Category.other, SubCategory.other, (String)null);
   }

   public boolean method1(EntityLivingBase var1) {
      return true;
   }
}
