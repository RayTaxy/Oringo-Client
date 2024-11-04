package oringo.module;

import java.util.Iterator;
import map.Class506;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class HidePlayersModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Only AntiBot valid", true);

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (this.method44() && var1.phase != Phase.START && field58.theWorld != null) {
         Iterator var2 = field58.theWorld.playerEntities.iterator();

         while(true) {
            EntityPlayer var3;
            do {
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  var3 = (EntityPlayer)var2.next();
               } while(!(var3 instanceof EntityOtherPlayerMP));
            } while(field0.method1() && !Class506.method0((Entity)var3));

            var3.setPosition(2137.0D, 2137.0D, 2137.0D);
         }
      }
   }

   public HidePlayersModule() {
      super("Hide Players", Category.render, SubCategory.world);
      this.method0(new Setting[]{field0});
   }
}
