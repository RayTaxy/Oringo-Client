package oringo.module;

import map.Class362;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

class AutoReconnectModule$1 extends GuiButton {
   final AutoReconnectModule field0;

   public void drawButton(Minecraft var1, int var2, int var3) {
      this.displayString = "Reconnecting in: " + (int)Math.max(0.0D, AutoReconnectModule.field2.method0() - (double)AutoReconnectModule.access$000().method0()) + "ms";
      if (!((AutoReconnectModule)Class362.method0(AutoReconnectModule.class)).method44()) {
         this.visible = false;
      } else {
         super.drawButton(var1, var2, var3);
      }
   }

   AutoReconnectModule$1(AutoReconnectModule var1, int var2, int var3, int var4, String var5) {
      super(var2, var3, var4, var5);
      this.field0 = var1;
   }
}
