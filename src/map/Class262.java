package map;

import com.google.gson.annotations.SerializedName;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Pattern;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

public class Class262 {
   @SerializedName("width")
   public double k_ = 1.0D;
   @SerializedName("color")
   public int ca_ = -1;
   @SerializedName("height")
   public double bV_ = 1.0D;
   @SerializedName("name")
   public String cW_ = null;
   public Pattern field4;
   @SerializedName("cape")
   public String field5 = null;

   public Pattern y_() {
      if (this.field4 == null) {
         this.field4 = Pattern.compile("\\b" + this.cW_ + "\\b", 2);
      }

      return this.field4;
   }

   public static String method0(String var0) {
      StringBuilder var1 = new StringBuilder();
      char[] var2 = var0.toCharArray();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         char var5 = var2[var4];
         if (Class265.method0(var5)) {
            var1.append(var5);
         }
      }

      return var1.toString();
   }

   public static void method1() {
      if (Class82.field8) {
         Class82.field8 = false;
         Scoreboard var0 = Class289.method2();
         if (var0 != null) {
            Collection var1 = var0.getScores();
            Class82.field0 = Class170.method0();
            ArrayList var2 = new ArrayList();
            Iterator var3 = var1.iterator();

            while(var3.hasNext()) {
               Score var4 = (Score)var3.next();
               ScorePlayerTeam var5 = var0.getPlayersTeam(var4.getPlayerName());
               if (var5 != null && var5.getTeamName().startsWith("team")) {
                  String var6 = var5.getColorPrefix() + var5.getColorSuffix();
                  if (!var6.matches(" *")) {
                     var2.add(ChatFormatting.stripFormatting(var6).toLowerCase());
                  }
               }
            }

            if (!var2.equals(Class82.field2)) {
               Class82.field2 = var2;
               Class82.field3.clear();
            }

         }
      }
   }

   public void method1(String var1) {
      this.field5 = var1;
   }

   public void method0(int var1) {
      this.ca_ = var1;
   }

   public double method0() {
      return this.bV_;
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class262 var2 = (Class262)var1;
         return Double.compare(var2.k_, this.k_) == 0 && Double.compare(var2.bV_, this.bV_) == 0 && this.ca_ == var2.ca_ && Objects.equals(this.field5, var2.field5);
      } else {
         return false;
      }
   }

   public int method3() {
      return this.ca_;
   }

   public void method2(String var1) {
      this.cW_ = var1;
   }

   public void method0(double var1) {
      this.bV_ = var1;
   }

   public double method4() {
      return this.k_;
   }

   public String method5() {
      return this.field5;
   }

   public void method1(double var1) {
      this.k_ = var1;
   }

   public String method45() {
      return this.cW_;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.k_, this.bV_, this.ca_, this.field5});
   }
}
