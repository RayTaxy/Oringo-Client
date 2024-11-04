package map;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import oringo.Oringo;

public class Class190 {
   public static boolean field0;
   public static final Class190 field1 = new Class190();

   @SubscribeEvent
   public void method0(ClientConnectedToServerEvent var1) {
      if (!var1.isLocal) {
         try {
            field0 = Oringo.field9.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
         } catch (Exception var3) {
         }
      }

   }

   @SubscribeEvent
   public void method0(ClientDisconnectionFromServerEvent var1) {
      field0 = false;
   }
}
