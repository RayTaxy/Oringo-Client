package oringo.mixin;

import java.io.IOException;
import map.Class311;
import map.Class362;
import map.Class496;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class274;
import oringo.event.Class533;

@Mixin({GuiContainer.class})
public abstract class GuiContainerMixin extends GuiScreenMixin {
   private GuiTextField field9;
   private boolean field10;
   @Shadow
   protected int xSize;
   @Shadow
   public Container inventorySlots;
   @Shadow
   private boolean ignoreMouseUp;
   @Shadow
   protected int ySize;

   @Inject(
      method = {"initGui"},
      at = {@At("HEAD")}
   )
   public void oringo$onInitGui(CallbackInfo var1) {
      this.field9 = new GuiTextField(0, this.fontRendererObj, 4, this.height - 12, this.width - 4, 12);
      this.field9.setText("");
      this.field9.setMaxStringLength(100);
      this.field9.setEnableBackgroundDrawing(false);
      this.field9.setCanLoseFocus(true);
      this.field9.setFocused(false);
   }

   public void handleKeyboardInput() throws IOException {
      super.handleKeyboardInput();
      if (this.inventorySlots instanceof ContainerChest && Class362.field57.method0((ContainerChest)this.inventorySlots)) {
         int var1 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
         KeyBinding.setKeyBindState(var1, Keyboard.getEventKeyState());
         if (Keyboard.getEventKeyState()) {
            KeyBinding.onTick(var1);
            Class362.method0(var1);
         }
      }

   }

   @Inject(
      method = {"mouseClicked"},
      at = {@At("HEAD")}
   )
   public void oringo$mouseClicked(int var1, int var2, int var3, CallbackInfo var4) {
      this.field9.mouseClicked(var1, var2, var3);
   }

   @Shadow
   public void initGui() {
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      if (this.inventorySlots instanceof ContainerChest && Class362.field57.method0((ContainerChest)this.inventorySlots)) {
         int var1 = Mouse.getEventButton();
         KeyBinding.setKeyBindState(var1 - 100, Mouse.getEventButtonState());
         if (Mouse.getEventButtonState()) {
            KeyBinding.onTick(var1 - 100);
            Class362.method0(var1 - 100);
         }

         int var2 = Mouse.getEventDWheel();
         if (var2 != 0) {
            if (this.mc.thePlayer.isSpectator()) {
               var2 = var2 < 0 ? -1 : 1;
               if (this.mc.ingameGUI.getSpectatorGui().func_175262_a()) {
                  this.mc.ingameGUI.getSpectatorGui().func_175259_b(-var2);
               } else {
                  float var3 = MathHelper.clamp_float(this.mc.thePlayer.capabilities.getFlySpeed() + (float)var2 * 0.005F, 0.0F, 0.2F);
                  this.mc.thePlayer.capabilities.setFlySpeed(var3);
               }
            } else {
               this.mc.thePlayer.inventory.changeCurrentItem(var2);
            }
         }
      }

   }

   @Inject(
      method = {"keyTyped"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void oringo$keyTyped(char var1, int var2, CallbackInfo var3) {
      if (this.field9.isFocused()) {
         if (var2 != 28 && var2 != 156) {
            this.field9.textboxKeyTyped(var1, var2);
         } else {
            String var4 = this.field9.getText().trim();
            if (var4.length() > 0) {
               this.sendChatMessage(var4);
               this.field9.setText("");
            }
         }

         var3.cancel();
      }

   }

   @Inject(
      method = {"drawScreen"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(int var1, int var2, float var3, CallbackInfo var4) {
      boolean var5 = Class362.field48.method44() && Class362.field48.field2.method1();
      if (!var5) {
         this.field9.setFocused(false);
      }

      this.field9.setVisible(var5);
      if (this.inventorySlots instanceof ContainerChest && Class362.field57.method0((ContainerChest)this.inventorySlots)) {
         this.allowUserInput = true;
         this.field10 = true;
         if (!this.mc.inGameHasFocus) {
            this.mc.inGameHasFocus = true;
            this.mc.mouseHelper.grabMouseCursor();
         }

         ScaledResolution var6 = new ScaledResolution(this.mc);
         Class311.field0.method0("In gui!", (float)(var6.getScaledWidth() / 2), (float)(var6.getScaledHeight() / 2), Class362.field7.method17().getRGB());
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         MinecraftForge.EVENT_BUS.post(new BackgroundDrawnEvent((GuiContainer)this));
         var4.cancel();
      } else {
         if (this.field10 && this.mc.inGameHasFocus) {
            this.mc.inGameHasFocus = false;
            this.mc.mouseHelper.ungrabMouseCursor();
            this.field10 = false;
         }

         if (this.field9.getVisible()) {
            drawRect(2, this.height - 14, this.width / 3, this.height - 2, Integer.MIN_VALUE);
         }

         if (this.field9.isFocused()) {
            KeyBinding.unPressAllKeys();
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   @Inject(
      method = {"drawScreen"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/inventory/GuiContainer;drawGuiContainerBackgroundLayer(FII)V"
)}
   )
   public void method1(int var1, int var2, float var3, CallbackInfo var4) {
      if (Class274.method0((GuiScreen)this)) {
         GL11.glPushMatrix();
         Class533.method0();
      }

   }

   @Inject(
      method = {"drawScreen"},
      at = {@At("RETURN")}
   )
   public void method2(int var1, int var2, float var3, CallbackInfo var4) {
      if (Class274.method0((GuiScreen)this)) {
         GL11.glPopMatrix();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
      GlStateManager.enableDepth();
      this.field9.setEnabled(Class362.field48.method44() && Class362.field48.field2.method1());
      this.field9.setVisible(Class362.field48.method44() && Class362.field48.field2.method1());
      if (Class362.field48.method44() && Class362.field48.field2.method1()) {
         Chat var5 = new Chat(Class496.field19, 0, this.height - 48);
         GL11.glPushMatrix();
         if (!Class362.field48.field4.method1()) {
            MinecraftForge.EVENT_BUS.post(var5);
            GlStateManager.translate((float)var5.posX, (float)var5.posY, 0.0F);
         }

         this.mc.ingameGUI.getChatGUI().drawChat(this.mc.ingameGUI.getUpdateCounter());
         MinecraftForge.EVENT_BUS.post(new Post(Class496.field19, ElementType.CHAT));
         GL11.glPopMatrix();
         this.field9.drawTextBox();
      }

      GlStateManager.enableAlpha();
      GlStateManager.enableLighting();
   }

   @Shadow
   protected abstract void keyTyped(char var1, int var2);
}
