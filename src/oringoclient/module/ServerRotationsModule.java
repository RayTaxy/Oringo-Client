package oringo.module;

import oringo.Oringo;

public class ServerRotationsModule extends Module {
   public static final ServerRotationsModule field0 = new ServerRotationsModule();

   public ServerRotationsModule() {
      super("Server Rotations", Category.render, SubCategory.ui);
      this.method1(true);
   }

   public static void method0(int var0, int var1) {
      Oringo.field9.playerController.windowClick(Oringo.field9.thePlayer.inventoryContainer.windowId, var0, var1, 2, Oringo.field9.thePlayer);
   }
}
