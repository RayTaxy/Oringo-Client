package oringo.command;

import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;
import map.Class220;
import map.Class362;
import map.Class61;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.util.Vec3;
import oringo.mixin.C02PacketUseEntityAccessor;
import oringo.module.FastBreakModule;
import oringo.module.NickHiderModule;

public class ArmorStandsCommand extends Command {
   public ArmorStandsCommand() {
      super("armorstands");
   }

   public static boolean lambda$execute$0(Entity var0) {
      return var0 instanceof EntityArmorStand && var0.getDisplayName().getFormattedText().length() > 5 && var0.hasCustomName();
   }

   public void method0(String[] var1) {
      C02PacketUseEntity var2;
      int var3;
      if (var1.length == 1) {
         var2 = new C02PacketUseEntity();
         var3 = Integer.parseInt(var1[0]);
         ((C02PacketUseEntityAccessor)var2).setEntityId(var3);
         ((C02PacketUseEntityAccessor)var2).setAction(Action.INTERACT_AT);
         ((C02PacketUseEntityAccessor)var2).setVec(new Vec3(0.0D, 0.0D, 0.0D));
         field9.getNetHandler().getNetworkManager().sendPacket(var2);
         var2 = new C02PacketUseEntity();
         ((C02PacketUseEntityAccessor)var2).setEntityId(var3);
         ((C02PacketUseEntityAccessor)var2).setAction(Action.INTERACT);
         field9.getNetHandler().getNetworkManager().sendPacket(var2);
      } else if (var1.length == 2) {
         field9.thePlayer.swingItem();
         var2 = new C02PacketUseEntity();
         var3 = Integer.parseInt(var1[0]);
         ((C02PacketUseEntityAccessor)var2).setEntityId(var3);
         ((C02PacketUseEntityAccessor)var2).setAction(Action.ATTACK);
         field9.getNetHandler().getNetworkManager().sendPacket(var2);
      } else {
         Stream var10000 = field9.theWorld.loadedEntityList.stream().filter(ArmorStandsCommand::lambda$execute$0);
         EntityPlayerSP var10001 = field9.thePlayer;
         var10001.getClass();
         var10000.sorted(Comparator.comparingDouble(var10001::func_70032_d).reversed()).forEach(Class220::method0);
      }
   }

   public String method1() {
      return "Shows you a list of loaded armor stands.";
   }

   public static String q_(String var0) {
      if (var0.length() < 3) {
         return var0;
      } else {
         var0 = SelfBanCommand.method0(var0, "Chum", "Cum");
         if (!Class362.field32.method44()) {
            return var0;
         } else {
            var0 = SelfBanCommand.method0(var0, NickHiderModule.field58.getSession().getUsername(), NickHiderModule.field0.method1());
            if (NickHiderModule.field1 && NickHiderModule.field58.getNetHandler() != null) {
               Iterator var1 = NickHiderModule.field58.getNetHandler().getPlayerInfoMap().iterator();

               while(var1.hasNext()) {
                  NetworkPlayerInfo var2 = (NetworkPlayerInfo)var1.next();
                  if (var2.getPlayerTeam() != null) {
                     var0 = SelfBanCommand.method0(var0, var2.getGameProfile().getName(), "OringoClient");
                  }
               }
            }

            return var0;
         }
      }
   }

   public static void method2() {
      for(int var0 = 0; var0 < Class61.S_.length; ++var0) {
         Class61.S_[var0] = Class362.field34.method44() ? Class362.field34.method0(var0, true) : FastBreakModule.method0(var0, true);
      }

   }
}
