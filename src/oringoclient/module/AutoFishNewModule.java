package oringo.module;

import java.util.Iterator;
import map.Class21;
import map.Class263;
import map.Class296;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class75;

public class AutoFishNewModule extends Module {
   public final Class296 field0 = new Class296();
   public EntityFishHook cw_ = null;

   public AutoFishNewModule() {
      super("Auto Fish New", Category.other, SubCategory.skills, (String)null);
   }

   public boolean method5() {
      if (this.cw_ == null) {
         return false;
      } else {
         Iterator var1 = field58.theWorld.loadedEntityList.iterator();

         Entity var2;
         do {
            if (!var1.hasNext()) {
               return false;
            }

            var2 = (Entity)var1.next();
         } while(!(var2 instanceof EntityArmorStand) || !((EntityArmorStand)var2).hasMarker() || !var2.getName().equals("§c§l!!!") || var2.getDistanceSqToEntity(this.cw_) >= 0.15D);

         return true;
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      this.method6();
      if (this.method5() && this.field0.method0(5L, true)) {
         Class21.method4();
         Class21.method4();
      }

   }

   public void method6() {
      this.cw_ = null;
      Iterator var1 = field58.theWorld.loadedEntityList.iterator();

      while(var1.hasNext()) {
         Entity var2 = (Entity)var1.next();
         if (var2 instanceof EntityFishHook && ((EntityFishHook)var2).angler == field58.thePlayer) {
            this.cw_ = (EntityFishHook)var2;
         }
      }

   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.type == ElementType.TEXT && this.cw_ != null) {
         field58.fontRendererObj.drawString("In water: " + this.cw_.isInWater(), 5, 5, -1);
      }
   }

   public static boolean method7() {
      return Class263.field0 != null;
   }

   public String d_() {
      return "Auto Fish";
   }
}
