package map;

import java.util.Iterator;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.scoreboard.ScorePlayerTeam;
import oringo.module.AntibotModule;
import oringo.module.AutoMiddleModule;

public class Class506 {
   public static ICamera field0 = new Frustum();

   public static boolean method0(ItemStack var0) {
      return var0.getItem() instanceof ItemSword && (var0.getDisplayName().contains("Astraea") || var0.getDisplayName().contains("Scylla") || var0.getDisplayName().contains("Valkyrie"));
   }

   public static boolean method0(Entity var0) {
      if (AntibotModule.field4.method44() && var0 instanceof EntityOtherPlayerMP) {
         String var1 = AntibotModule.field0.method4();
         byte var2 = -1;
         switch(var1.hashCode()) {
         case -1248403467:
            if (var1.equals("Hypixel")) {
               var2 = 1;
            }
            break;
         case 2029746065:
            if (var1.equals("Custom")) {
               var2 = 0;
            }
         }

         switch(var2) {
         case 0:
            return (!AntibotModule.field2.method1() || ((Class363)var0).method16() >= 150) && (!AntibotModule.field1.method1() || ((Class363)var0).method15() >= 150) && (!AntibotModule.field3.method1() || !AutoMiddleModule.method0(var0));
         case 1:
            if (AntibotModule.field58.theWorld != null && AntibotModule.field58.theWorld.getScoreboard() != null) {
               Iterator var3 = AntibotModule.field58.theWorld.getScoreboard().getTeams().iterator();

               while(true) {
                  ScorePlayerTeam var4;
                  do {
                     do {
                        do {
                           if (!var3.hasNext()) {
                              return false;
                           }

                           var4 = (ScorePlayerTeam)var3.next();
                        } while(!var4.getColorPrefix().startsWith("§"));
                     } while(var4.getTeamName().startsWith("team"));
                  } while(var4.getColorPrefix().endsWith("[NPC] "));

                  Iterator var5 = var4.getMembershipCollection().iterator();

                  while(var5.hasNext()) {
                     String var6 = (String)var5.next();
                     if (var6.equals(var0.getName())) {
                        return var4.func_98299_i() != 0;
                     }
                  }
               }
            }

            return false;
         default:
            return true;
         }
      } else {
         return true;
      }
   }
}
