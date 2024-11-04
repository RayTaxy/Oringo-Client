package oringo.mixin;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({GuiScreen.class})
public abstract class GuiScreenMixin extends MapItemRenderer implements GuiYesNoCallback {
   @Shadow
   protected List buttonList;
   @Shadow
   protected FontRenderer fontRendererObj;
   @Shadow
   public int width;
   @Shadow
   public Minecraft mc;
   @Shadow
   public int height;
   @Shadow
   public boolean allowUserInput;

   @Shadow
   public abstract void drawScreen(int var1, int var2, float var3);

   @Shadow
   public abstract void handleInput();

   @Shadow
   public void drawWorldBackground(int var1) {
   }

   @Shadow
   public void handleMouseInput() throws IOException {
   }

   @Shadow
   public abstract void sendChatMessage(String var1);

   @Shadow
   protected void mouseReleased(int var1, int var2, int var3) {
   }

   @Shadow
   public void handleKeyboardInput() throws IOException {
   }

   @Shadow
   public abstract void drawBackground(int var1);
}
