package oringo.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.Iterator;
import map.Class513;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import oringo.module.PopupAnimationModule;

public class PlayerCommand extends Command {
   public String field0 = null;

   public PlayerCommand() {
      super("pl");
   }

   public void method0(String[] var1) {
      if (var1.length > 0) {
         try {
            int var9 = Integer.parseInt(var1[0]);
            method2(Block.BLOCK_STATE_IDS.getByValue(var9 << 4));
         } catch (NumberFormatException var8) {
            MovingObjectPosition var10 = field9.objectMouseOver;
            if (var10.typeOfHit != MovingObjectType.BLOCK) {
               return;
            }

            IBlockState var11 = field9.theWorld.getBlockState(var10.getBlockPos());
            method2(Block.BLOCK_STATE_IDS.get(var11) >> 4);
            method2(Block.BLOCK_STATE_IDS.get(var11));
         }

      } else if (field9.theWorld.getScoreboard() != null) {
         Iterator var2 = field9.theWorld.getScoreboard().getTeams().iterator();

         while(true) {
            ScorePlayerTeam var3;
            do {
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  var3 = (ScorePlayerTeam)var2.next();
               } while(!var3.getColorPrefix().startsWith("§"));
            } while(var3.getTeamName().startsWith("team"));

            for(Iterator var4 = var3.getMembershipCollection().iterator(); var4.hasNext(); PopupAnimationModule.method2(String.format("%s %s %s %s", var3.func_98299_i(), var3.getRegisteredName().replace("§", "&"), var3.getTeamName().replace("§", "&"), var3.getDeathMessageVisibility().internalName))) {
               String var5 = (String)var4.next();
               PopupAnimationModule.method2(ScorePlayerTeam.formatPlayerName(var3, var5).replace("§k", "[OBF] "));
               if (var3.func_98299_i() == 0) {
                  Iterator var6 = field9.theWorld.playerEntities.iterator();

                  while(var6.hasNext()) {
                     EntityPlayer var7 = (EntityPlayer)var6.next();
                     if (var7.getName().equals(ChatFormatting.stripFormatting(ScorePlayerTeam.formatPlayerName(var3, var5)))) {
                        PopupAnimationModule.method2(var7.getGameProfile().toString());
                        PopupAnimationModule.method2("§cData: §f" + Class513.method0(var7.getGameProfile()));
                     }
                  }
               }
            }
         }
      }
   }

   public static Color method0(Color var0, int var1) {
      return new Color(var0.getRed(), var0.getGreen(), var0.getBlue(), var1);
   }

   public String method1() {
      return "Test command, displays all players from objectives";
   }

   public boolean a_() {
      return true;
   }
}
