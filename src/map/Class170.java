package map;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;

public class Class170 {
   public static final ArrayList field0 = new ArrayList();

   public static String method0() {
      Scoreboard var0 = Class289.method2();
      if (var0 == null) {
         return "";
      } else {
         ScoreObjective var1 = var0.getObjectiveInDisplaySlot(1);
         return var1 == null ? "" : ChatFormatting.stripFormatting(var1.getDisplayName());
      }
   }
}
