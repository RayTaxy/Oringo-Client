package oringo.command;

import java.util.ArrayList;
import map.Class350;
import map.Class361;
import map.Class503;
import map.Class510;
import map.Class94;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.event.Class75;
import oringo.module.AntiTentacleModule;
import oringo.module.BlockHitModule;
import oringo.module.PopupAnimationModule;
import oringo.module.TrailModule;

public class UseCommand extends Command {
   public final ArrayList field0 = new ArrayList();
   public String cW_ = null;

   public void method0(String[] var1) {
      if (var1.length == 0) {
         method2("Correct usage: .use (item name) [arguments]");
         method2("Arguments:");
         method2(" -yaw:x (.use Hyperion -yaw:0 - Use hyperion, while looking at south)");
         method2(" -pitch:x (.use Hyperion -pitch:90 - Use hyperion, while looking below you)");
         method2(" Extra: -yaw/pitch:x+180 - Adds 180 to your rotation");
         method2(" Extra: -pitch:-x - Uses your reversed pitch");
      } else {
         this.field0.clear();
         ArrayList var2 = new ArrayList();
         String[] var3 = var1;
         int var4 = var1.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            if (var6.startsWith("-")) {
               this.field0.add(var6);
            } else {
               var2.add(var6);
            }
         }

         this.cW_ = String.join(" ", var2);
      }
   }

   public boolean lambda$onPreMove$1(ItemStack var1) {
      return var1.getDisplayName().toLowerCase().contains(this.cW_);
   }

   public UseCommand() {
      super("use");
   }

   public static boolean lambda$onPreMove$0(Class210.Class1 var0, String var1) {
      String var2;
      if (var1.startsWith("-yaw:")) {
         var2 = var1.split(":")[1];
         if (var2.startsWith("x")) {
            var2 = String.valueOf(field9.thePlayer.rotationYaw + Float.parseFloat(var2.substring(1)));
         }

         for(var0.t_ = Float.parseFloat(var2); var0.t_ > 180.0F; var0.t_ -= 360.0F) {
         }

         while(var0.t_ < -180.0F) {
            var0.t_ += 360.0F;
         }

         var0.method5();
         return true;
      } else if (!var1.startsWith("-pitch:")) {
         return false;
      } else {
         var2 = var1.split(":")[1];
         if (var2.startsWith("x")) {
            var2 = String.valueOf(field9.thePlayer.rotationPitch + Float.parseFloat(var2.substring(1)));
         }

         if (var2.equals("-x")) {
            var2 = String.valueOf(-field9.thePlayer.rotationPitch);
         }

         for(var0.bF_ = Float.parseFloat(var2); var0.bF_ > 90.0F; var0.bF_ -= 180.0F) {
         }

         while(var0.bF_ < -90.0F) {
            var0.bF_ += 180.0F;
         }

         var0.method5();
         return true;
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.cW_ != null) {
         if (!this.field0.isEmpty()) {
            method2("Ignored arguments: " + String.join(", ", this.field0));
            this.field0.clear();
         }

         int var2 = TrailModule.method0(this::lambda$onPreMove$1);
         if (var2 == -1) {
            PopupAnimationModule.method2(String.format("Couldn't find §r%s §fon your hotbar!", this.cW_));
            this.cW_ = null;
            return;
         }

         this.cW_ = null;
         ItemStack var3 = field9.thePlayer.inventory.getStackInSlot(var2);
         if (!"SUPERBOOM_TNT".equals(BlockHitModule.method0(var3)) && !"INFINITE_SUPERBOOM_TNT".equals(BlockHitModule.method0(var3))) {
            Class361.method0((Class94)(new Class350(var2)));
         } else {
            MovingObjectPosition var4 = AntiTentacleModule.method0(Class503.field0, Class503.field1, 6.0F);
            if (var4 == null || var4.typeOfHit != MovingObjectType.BLOCK) {
               return;
            }

            Class361.method0((Class94)(new Class510(var4.getBlockPos(), var4.sideHit)));
         }
      }

   }

   public String method1() {
      return "Uses the specified item";
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class210.Class1 var1) {
      if (this.cW_ != null) {
         this.field0.removeIf(UseCommand::lambda$onPreMove$0);
      }
   }
}
