package map;

import java.awt.Color;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.event.Class270;
import oringo.module.PopupAnimationModule;

public class Class462 {
   public int field0;
   public Class462.Class0 field1 = new Class462.Class0(1.0D, 600);
   public static final Pattern field2 = Pattern.compile("§r Completed Rooms: §r§d(\\d+)§r");
   public boolean field3;
   public long field4;
   public boolean field5;
   public static final Pattern field6 = Pattern.compile("§r Secrets Found: §r§.(.*?)%§r");
   public static final Pattern field7 = Pattern.compile("^ §7 §cThe Catac§combs §7\\((.*?)\\)$");
   public static final Pattern field8 = Pattern.compile("Cleared: §.(.*?)% §8");
   public static final Pattern field9 = Pattern.compile("^Time Elapsed: §a§a(.*?)$");
   public static final Pattern field10 = Pattern.compile("§r .*: §r§7\\[§.§.§.(.?)§.§.]");
   public static final Minecraft field11 = Minecraft.getMinecraft();
   public double field12;
   public int field13;
   public static final Pattern field14 = Pattern.compile("§r Crypts: §r§6(.*?)§r");
   public int field15;
   public int field16;
   public int field17;
   public double field18;
   public static final Pattern field19 = Pattern.compile("§r§a§lDeaths: §r§f\\((.*?)\\)§r");

   public int method0() {
      return MathHelper.clamp_int((int)(60.0D * this.method14()) + (int)(40.0D * Math.min(1.0D, this.field12 / this.field1.field1)), 0, 100);
   }

   public String method0(NetworkPlayerInfo var1) {
      return var1.getDisplayName() != null ? var1.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(var1.getPlayerTeam(), var1.getGameProfile().getName());
   }

   @SubscribeEvent
   public void method0(LivingDeathEvent var1) {
      if (Class496.field5) {
         if (Class514.method0(var1.entity)) {
            this.field5 = true;
         }

      }
   }

   public int method1() {
      return Math.min(5, this.field17) + (this.field5 ? 2 : 0) + (Class496.field17 ? 10 : 0);
   }

   public int method2() {
      long var1 = (this.field4 - (long)this.field1.field0) * (long)(600 / this.field1.field0);
      return var1 < 0L ? 100 : (int)(100L - var1 / 12L);
   }

   public int method3() {
      return this.method2() + this.method1() + this.method0() + this.method6();
   }

   public static boolean method0(String var0) {
      return var0.contains("Correct all the panes!") || var0.contains("Navigate the maze!") || var0.contains("Click in order!") || var0.contains("What starts with:") || var0.contains("Select all the") || var0.contains("Change all to same color!") || var0.contains("Click the button on time!");
   }

   public static void method0(Vec3 var0, double var1, Color var3) {
      Class98.method0(var0, var1, var3, var3);
   }

   public double method4() {
      return this.field1.field1;
   }

   public int method5() {
      return this.field0;
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.o_();
   }

   public int method6() {
      return MathHelper.clamp_int(20 + (int)(80.0D * this.method14()) - 10 * this.field16 - (this.field15 > 0 ? this.field15 * 2 - 1 : 0), 0, 100);
   }

   public void method1(String var1) {
      Matcher var2 = field6.matcher(var1);
      if (var2.find()) {
         this.field12 = Double.parseDouble(var2.group(1)) / 100.0D;
      } else {
         var2 = field14.matcher(var1);
         if (var2.find()) {
            this.field17 = Integer.parseInt(var2.group(1));
         } else {
            var2 = field19.matcher(var1);
            if (var2.find()) {
               this.field15 = Integer.parseInt(var2.group(1));
            } else {
               var2 = field10.matcher(var1);
               if (var2.find()) {
                  if (!var2.group(1).equals("✔")) {
                     ++this.field16;
                  }

               } else {
                  var2 = field2.matcher(var1);
                  if (var2.find()) {
                     this.field13 = Integer.parseInt(var2.group(1));
                  }
               }
            }
         }
      }
   }

   public int method7() {
      return this.field15;
   }

   public void o_() {
      this.field0 = 0;
      this.field1 = new Class462.Class0(1.0D, 600);
      this.field3 = false;
      this.field4 = 0L;
      this.field15 = 0;
      this.field16 = 0;
      this.field18 = 0.0D;
      this.field12 = 0.0D;
      this.field13 = 0;
      this.field17 = 0;
      this.field5 = false;
   }

   public void method9() {
      if (field11.getNetHandler().getPlayerInfoMap() != null) {
         Iterator var1 = field11.getNetHandler().getPlayerInfoMap().iterator();

         while(var1.hasNext()) {
            NetworkPlayerInfo var2 = (NetworkPlayerInfo)var1.next();
            this.method1(this.method0(var2));
         }

      }
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void method0(ClientChatReceivedEvent var1) {
      if (this.field0 >= 6 && var1.type != 2) {
         String var2 = var1.message.getUnformattedText().trim();
         if ((var2.startsWith("Party > ") || var2.contains(":") && !var2.contains(">")) && (var2.contains("SKYTILS-DUNGEON-SCORE-MIMIC") || var2.contains("Mimic dead!") || var2.contains("Mimic Killed!") || var2.contains("Mimic Dead!"))) {
            this.field5 = true;
         }

      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase != Phase.END && Class496.field5 && field11.thePlayer != null && field11.thePlayer.ticksExisted % 5 == 0) {
         this.field16 = 0;
         this.method15();
         this.method9();
      }
   }

   public int method10() {
      return this.field17;
   }

   public double method11() {
      return this.field12;
   }

   public int method12() {
      int var1 = 0;
      HashSet var2 = new HashSet();
      Class208[][] var3 = Class198.field1;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Class208[] var6 = var3[var5];
         Class208[] var7 = var6;
         int var8 = var6.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            Class208 var10 = var7[var9];
            if (var10 != null && !var2.contains(var10.method45())) {
               var1 += var10.method0();
               var2.add(var10.method45());
            }
         }
      }

      return var1;
   }

   public void method2(String var1) {
      Matcher var2 = field7.matcher(var1);
      String var3;
      if (var2.find()) {
         var3 = var2.group(1);
         if (var3.length() == 2) {
            this.field0 = var3.charAt(1) - 48;
            this.field3 = var3.charAt(0) == 'M';
            if (this.field3) {
               this.field1 = new Class462.Class0(1.0D, 480);
            } else {
               switch(this.field0) {
               case 1:
                  this.field1 = new Class462.Class0(0.3D, 600);
                  break;
               case 2:
                  this.field1 = new Class462.Class0(0.4D, 600);
                  break;
               case 3:
                  this.field1 = new Class462.Class0(0.5D, 600);
                  break;
               case 4:
                  this.field1 = new Class462.Class0(0.6D, 720);
                  break;
               case 5:
                  this.field1 = new Class462.Class0(0.7D, 600);
                  break;
               case 6:
                  this.field1 = new Class462.Class0(0.85D, 720);
                  break;
               case 7:
                  this.field1 = new Class462.Class0(1.0D, 840);
               }
            }
         }

      } else {
         var2 = field9.matcher(var1);
         if (var2.find()) {
            var3 = "PT" + var2.group(1).replaceAll(" ", "").toUpperCase(Locale.ENGLISH);

            try {
               this.field4 = Duration.parse(var3).getSeconds();
            } catch (DateTimeParseException var5) {
               PopupAnimationModule.method2("An error occurred while getting dungeon time! Please report this " + var5.getParsedString());
               this.field4 = 0L;
            }

         } else {
            var2 = field8.matcher(var1);
            if (var2.find()) {
               this.field18 = Double.parseDouble(var2.group(1)) / 100.0D;
            }

         }
      }
   }

   public Class462() {
      MinecraftForge.EVENT_BUS.register(this);
   }

   public boolean method13() {
      return this.field5;
   }

   public double method14() {
      int var1 = this.field13;
      if (!Class496.field13) {
         ++var1;
      }

      if (!Class496.field16) {
         ++var1;
      }

      return this.field18 == 0.0D ? 0.0D : Class207.method0((double)var1 / ((double)this.field13 / this.field18), 1.0D, 0.0D);
   }

   public void method15() {
      if (field11.thePlayer.getWorldScoreboard() != null && field11.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1) != null) {
         Scoreboard var1 = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
         ArrayList var2 = new ArrayList(var1.getSortedScores(var1.getObjectiveInDisplaySlot(1)));
         Iterator var3 = var2.iterator();

         while(true) {
            Score var4;
            ScorePlayerTeam var5;
            do {
               if (!var3.hasNext()) {
                  return;
               }

               var4 = (Score)var3.next();
               var5 = var1.getPlayersTeam(var4.getPlayerName());
            } while(var5 == null);

            String var6 = var5.getColorPrefix() + var4.getPlayerName() + var5.getColorSuffix();
            StringBuilder var7 = new StringBuilder();
            char[] var8 = var6.toCharArray();
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
               char var11 = var8[var10];
               if (var11 < 256) {
                  var7.append(var11);
               }
            }

            this.method2(var7.toString());
         }
      }
   }

   public static class Class0 {
      public final int field0;
      public final double field1;

      public Class0(double var1, int var3) {
         this.field1 = var1;
         this.field0 = var3;
      }
   }
}
