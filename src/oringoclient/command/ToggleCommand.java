package oringo.command;

import java.util.Iterator;
import map.Class362;
import map.Class417;
import map.Class496;
import map.Class514;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import oringo.module.AutoIceFillModule;
import oringo.module.AutoIceSprayModule;
import oringo.module.LividFinderModule;
import oringo.module.Module;

public class ToggleCommand extends Command {
   public ToggleCommand() {
      super("toggle", "t");
   }

   public String method1() {
      return "Toggles the specified module";
   }

   public void method0(String[] var1) {
      if (var1.length == 0) {
         method2(".toggle [Name of the module]");
      } else {
         String var2 = String.join(" ", var1).replaceAll(" ", "");
         Iterator var3 = Class362.method0().iterator();

         while(var3.hasNext()) {
            Module var4 = (Module)var3.next();
            if (var4.cW_.replaceAll(" ", "").equalsIgnoreCase(var2)) {
               var4.method40();
               method2(String.format("%s has been %s!", var4.cW_, var4.method44() ? "enabled" : "disabled"));
               return;
            }
         }

         method2("Module not found!");
      }

   }

   public static boolean a_(EntityLivingBase var0) {
      boolean var10000;
      label85: {
         if ((double)AutoIceSprayModule.field58.thePlayer.getDistanceToEntity(var0) < AutoIceSprayModule.field6.method0() && Class417.method0(MessageCommand.method0((Entity)var0), AutoIceSprayModule.field3.method1()) && (AutoIceSprayModule.field58.thePlayer.canEntityBeSeen(var0) || AutoIceSprayModule.field8.method1())) {
            if (LividFinderModule.field2.method1() && LividFinderModule.field0 != null) {
               if (LividFinderModule.field0.equals(var0)) {
                  break label85;
               }
            } else if (var0 instanceof EntityBat && !var0.isInvisible() && AutoIceSprayModule.field0.method1() || AutoIceFillModule.method0((Entity)var0) && AutoIceSprayModule.field4.method1() || AutoIceSprayModule.field2.method1() && var0 instanceof EntityEnderman && var0.getDisplayName().getUnformattedText().equals("Dinnerbone") && !var0.isInvisible() || var0 instanceof EntityWither && ((EntityWither)var0).getInvulTime() > 140 && ((EntityWither)var0).getInvulTime() < 160 && !var0.isInvisible() && AutoIceSprayModule.field5.method1() || Class514.method0((Entity)var0) && AutoIceSprayModule.field10.method1() || AutoIceSprayModule.field11.method1() && !Class496.field13 && TrollYouCommand.method0((Entity)var0)) {
               break label85;
            }
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }
}
