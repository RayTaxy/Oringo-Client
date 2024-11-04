package oringo.module;

import map.Class496;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;

public class ThystHiderModule extends Module {
   public ThystHiderModule() {
      super("Thyst Hider", Category.skyblock, SubCategory.mining, "Hides thysts when mining in Crystal Hollows");
   }

   @SubscribeEvent
   public void method0(EntityJoinWorldEvent var1) {
      if (Class496.field1) {
         if (var1.entity instanceof EntityEndermite || var1.entity instanceof EntityArmorStand && var1.entity.getName().contains("Thyst")) {
            var1.setCanceled(true);
         }

      }
   }

   public static BlockPos method3() {
      return new BlockPos(Oringo.field9.thePlayer);
   }
}
