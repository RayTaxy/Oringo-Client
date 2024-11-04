package map;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec4b;
import net.minecraft.world.WorldSettings.GameType;
import oringo.module.TerminalESPModule;
import oringo.module.TerminatorAuraModule;

public class Class119 {
   public static final Pattern field0 = Pattern.compile("^\\[\\d*] (.*) \\((.*)\\)$");
   public static boolean field1;
   public static final Pattern field2 = Pattern.compile("^§.§.\\[.*] §.(.*?) §.§.\\(.*\\)§.$");
   public static int field3;
   public static int field4;
   public static final List field5 = new ArrayList();
   public static final Ordering field6 = Ordering.from(new Class119.Class0((Class119$1)null));
   public static int field7;

   public static void method0(ArrayList var0, int var1) {
      float var2 = (float)(var1 >> 24 & 255) / 255.0F;
      float var3 = (float)(var1 >> 16 & 255) / 255.0F;
      float var4 = (float)(var1 >> 8 & 255) / 255.0F;
      float var5 = (float)(var1 & 255) / 255.0F;
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var3, var4, var5, var2);
      Class479.field3.begin(9, DefaultVertexFormats.POSITION);
      Iterator var6 = var0.iterator();

      while(var6.hasNext()) {
         float[] var7 = (float[])var6.next();
         Class479.field3.pos((double)var7[0], (double)var7[1], 0.0D).endVertex();
      }

      Class479.field1.draw();
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static void lambda$updatePlayers$0(int var0, NetworkPlayerInfo var1, List var2, String var3, Vec4b var4) {
      if (var4.func_176110_a() == 3 && var3.length() == 6 && var3.charAt(5) - 48 == var0) {
         Matcher var5 = field2.matcher(var1.getDisplayName().getFormattedText());
         var2.add(new Class229(var1, var4, var3, var5.find() ? var5.group(1) : ""));
      }

   }

   public static void method0(int var0, int var1, Class208 var2) {
      if (var2 != null) {
         if (!var2.field10) {
            BlockPos var3 = new BlockPos(var0, 0, var1);
            Class507 var4;
            if (var2.field3 == null) {
               var4 = Class507.method0(var0, var1, var2.method3());
               if (var4 != null) {
                  var2.field3 = var4;
                  var2.method0(EnumFacing.NORTH);
               }
            } else {
               var4 = var2.field3;
               EnumFacing[] var5 = EnumFacing.HORIZONTALS;
               int var6 = var5.length;

               for(int var7 = 0; var7 < var6; ++var7) {
                  EnumFacing var8 = var5[var7];
                  BlockPos var9 = var3.offset(var8, var4.field3);
                  if (TerminatorAuraModule.method0(var9)) {
                     int var10 = Class475.method0(var9.getX(), var9.getZ());
                     if (var4.field0 == var10) {
                        var2.method0(var8);
                        var4.field1.forEach(Class198::field0);
                        break;
                     }
                  }
               }
            }
         }

         if (TerminalESPModule.method0(var0, var1 + 16) && var2.field11 == null) {
            var2.field11 = Class229.method0(var0, var1 + 16);
         }

         if (TerminalESPModule.method0(var0 + 16, var1) && var2.field4 == null) {
            var2.field4 = Class229.method0(var0 + 16, var1);
         }

      }
   }

   static class Class0 implements Comparator {
      private Class0() {
      }

      public int compare(Object var1, Object var2) {
         return this.method0((NetworkPlayerInfo)var1, (NetworkPlayerInfo)var2);
      }

      public int method0(NetworkPlayerInfo var1, NetworkPlayerInfo var2) {
         ScorePlayerTeam var3 = var1.getPlayerTeam();
         ScorePlayerTeam var4 = var2.getPlayerTeam();
         return ComparisonChain.start().compareTrueFirst(var1.getGameType() != GameType.SPECTATOR, var2.getGameType() != GameType.SPECTATOR).compare(var3 != null ? var3.getRegisteredName() : "", var4 != null ? var4.getRegisteredName() : "").compare(var1.getGameProfile().getName(), var2.getGameProfile().getName()).result();
      }

      Class0(Class119$1 var1) {
         this();
      }
   }
}
