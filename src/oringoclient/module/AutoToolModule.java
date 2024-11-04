package oringo.module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import map.Class413;
import map.Class426;
import map.Class447;
import net.minecraft.item.EnumAction;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoToolModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Tools", true);
   public static final Class447 cI_ = new Class447();
   public final BooleanSetting field2 = new BooleanSetting("Swords", true);
   public final BooleanSetting field3 = (BooleanSetting)(new BooleanSetting("Auto fire aspect", true, this::lambda$new$0)).method2("Attempts to use a sword with fire aspect if the target isn't on fire");

   public Boolean lambda$new$0() {
      return !this.field2.method1();
   }

   public AutoToolModule() {
      super("Auto Tool", Category.player, SubCategory.player, "Selects the best tool");
      this.method0((Setting[])(new Setting[]{this.field0, this.field2, this.field3}));
   }

   public static String s_(String var0) {
      try {
         HttpURLConnection var1 = AutoHealModule.method0(new URL(String.format("https://plancke.io/hypixel/player/stats/%s", var0)));
         if (var1 != null) {
            BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
            StringBuilder var3 = new StringBuilder();

            String var4;
            while((var4 = var2.readLine()) != null) {
               var3.append(var4).append("\n");
            }

            return var3.toString();
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return "";
   }

   public static String method0(Packet var0) {
      return var0.getClass().getSimpleName() + Class413.field0.toJson(var0);
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (this.method44() && field58.thePlayer != null) {
         if (var1.field0 instanceof C07PacketPlayerDigging) {
            C07PacketPlayerDigging var2 = (C07PacketPlayerDigging)var1.field0;
            if (this.field0.method1() && !field58.thePlayer.isUsingItem() && var2.getStatus() == Action.START_DESTROY_BLOCK) {
               field58.thePlayer.inventory.currentItem = AutoJaxRangeModule.method0(var2.getPosition());
               Class426.method10();
            }
         } else if (var1.field0 instanceof C02PacketUseEntity) {
            C02PacketUseEntity var3 = (C02PacketUseEntity)var1.field0;
            if (this.field2.method1() && var3.getAction() == net.minecraft.network.play.client.C02PacketUseEntity.Action.ATTACK && cI_.a_(250L) && (!field58.thePlayer.isUsingItem() || field58.thePlayer.getItemInUse().getItemUseAction() == EnumAction.BLOCK) && !field58.playerController.getIsHittingBlock()) {
               field58.thePlayer.inventory.currentItem = CustomHubMap.method0(var3.getEntityFromWorld(field58.theWorld), this.field3.method1());
               Class426.method10();
            }
         } else if (var1.field0 instanceof C09PacketHeldItemChange) {
            cI_.o_();
         }

      }
   }
}
