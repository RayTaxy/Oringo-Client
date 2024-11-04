package oringo.module;

import java.util.Comparator;
import java.util.List;
import map.Class361;
import map.Class496;
import map.Class501;
import map.Class94;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.MessageCommand;
import oringo.command.WardrobeCommand;
import oringo.event.Class210;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class BearAuraModule extends Module {
   public final StringSetting field0 = (StringSetting)(new StringSetting("Swap item")).method2("The item the module should use to attack");

   public static boolean lambda$onUpdate$0(EntityOtherPlayerMP var0) {
      return var0.getName().equals("Spirit Bear") && var0.getHealth() > 0.0F && WardrobeCommand.method0(var0, AutoHackModule.method0(MessageCommand.method0((Entity)var0)));
   }

   public static float method0(double var0, double var2, double var4) {
      double var6 = Oringo.field9.getRenderManager().viewerPosX - var0;
      double var8 = Oringo.field9.getRenderManager().viewerPosY - var2;
      double var10 = Oringo.field9.getRenderManager().viewerPosZ - var4;
      return MathHelper.sqrt_double(var6 * var6 + var8 * var8 + var10 * var10);
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (Class496.field5 && (this.field0.F_() || GrottoNotificationModule.o_(this.field0.method1()) != -1)) {
         List var2 = field58.theWorld.getEntities(EntityOtherPlayerMP.class, BearAuraModule::lambda$onUpdate$0);
         if (!var2.isEmpty()) {
            EntityPlayerSP var10001 = field58.thePlayer;
            var10001.getClass();
            var2.sort(Comparator.comparingDouble(var10001::func_70068_e));
            EntityOtherPlayerMP var3 = (EntityOtherPlayerMP)var2.get(0);
            int var4 = GrottoNotificationModule.o_(this.field0.method1());
            if (var4 == -1 && !this.field0.F_()) {
               return;
            }

            if (!Class361.method0((Class94)(this.field0.F_() ? new Class501() : new Class501(var4)))) {
               return;
            }

            var1.method0(MessageCommand.method0((Entity)var3));
         }

      }
   }

   public BearAuraModule() {
      super("Bear Aura", Category.dungeon, SubCategory.main);
      this.method0(new Setting[]{this.field0});
   }
}
