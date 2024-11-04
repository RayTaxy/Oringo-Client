package map;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import oringo.module.AutoFishNewModule;

public class Class21 {
   public static final Minecraft field0 = Minecraft.getMinecraft();

   public static void method4() {
      AutoFishNewModule.method3(new C08PacketPlayerBlockPlacement(AutoFishNewModule.field58.thePlayer.getHeldItem()));
      AutoFishNewModule.field58.thePlayer.swingItem();
   }
}
