package oringo.mixin;

import java.io.IOException;
import map.Class220;
import map.Class229;
import map.Class362;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Timer;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.WorldServer;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.Oringo;
import oringo.event.Class148;
import oringo.event.Class156;
import oringo.event.Class210;
import oringo.event.Class274;
import oringo.event.Class307;
import oringo.event.Class437;
import oringo.module.AOTVReturnModule;
import oringo.module.AutoFishNewModule;
import oringo.module.AutoRabbitModule;
import oringo.module.BlockHitModule;
import oringo.module.CameraModule;
import oringo.module.DelaysModule;
import oringo.module.FastBreakModule;
import oringo.module.ForagingMacroModule;
import oringo.module.GardenHelperModule;
import oringo.module.KillAuraModule;
import oringo.module.LividFinderModule;
import oringo.module.MithrilMacroModule;

@Mixin({Minecraft.class})
public abstract class MinecraftMixin {
   @Shadow
   private int rightClickDelayTimer;
   @Shadow
   public boolean skipRenderWorld;
   @Shadow
   private TextureMap textureMapBlocks;
   @Shadow
   public PlayerControllerMP playerController;
   @Shadow
   public WorldClient theWorld;
   @Shadow
   public EntityPlayerSP thePlayer;
   @Shadow
   public GuiIngame ingameGUI;
   @Shadow
   private Entity renderViewEntity;
   @Shadow
   public boolean inGameHasFocus;
   @Shadow
   public boolean renderChunksMany;
   @Shadow
   public MovingObjectPosition objectMouseOver;
   @Shadow
   public TextureManager renderEngine;
   @Shadow
   private Timer timer;
   @Shadow
   private static Minecraft theMinecraft;
   @Shadow
   private int tempDisplayWidth;
   @Shadow
   public GuiScreen currentScreen;
   @Shadow
   @Final
   private static Logger logger;
   @Shadow
   private int leftClickCounter;
   @Shadow
   public EntityRenderer entityRenderer;
   @Shadow
   public EffectRenderer effectRenderer;
   @Shadow
   public GameSettings gameSettings;
   @Unique
   private boolean field21 = false;
   @Shadow
   private int tempDisplayHeight;

   @Redirect(
      method = {"runTick"},
      at = @At(
   value = "FIELD",
   target = "Lnet/minecraft/client/settings/GameSettings;thirdPersonView:I",
   opcode = 180,
   ordinal = 1
)
   )
   private int injected(GameSettings var1) {
      return Class362.field61.method44() && CameraModule.field5.method1() ? this.gameSettings.thirdPersonView + 1 : this.gameSettings.thirdPersonView;
   }

   @Inject(
      method = {"sendClickBlockToController"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(boolean var1, CallbackInfo var2) {
      if (AutoFishNewModule.method7() || Class229.method1().method44()) {
         var2.cancel();
      }

      if (Class362.field54.method44()) {
         this.leftClickCounter = 0;
      }

   }

   @Inject(
      method = {"rightClickMouse"},
      at = {@At("RETURN")}
   )
   public void method0(CallbackInfo var1) {
      if (DelaysModule.field2.method44() && this.isUsingTerm()) {
         this.rightClickDelayTimer = (int)DelaysModule.field2.field4.method0();
      }

   }

   @Inject(
      method = {"rightClickMouse"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(CallbackInfo var1) {
      if (!AutoFishNewModule.method7() && !Class229.method1().method44()) {
         if ((new Class274()).method7()) {
            var1.cancel();
         }

      } else {
         var1.cancel();
      }
   }

   @Inject(
      method = {"runTick"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/Minecraft;theWorld:Lnet/minecraft/client/multiplayer/WorldClient;",
   ordinal = 2
)}
   )
   public void method2(CallbackInfo var1) {
      if (Oringo.field9.thePlayer == null) {
         ForagingMacroModule.o_();
      } else {
         MithrilMacroModule.method12();
      }
   }

   @Inject(
      method = {"displayGuiScreen"},
      at = {@At("RETURN")}
   )
   public void method0(GuiScreen var1, CallbackInfo var2) {
      (new Class437(var1)).method7();
   }

   @Inject(
      method = {"refreshResources"},
      at = {@At("HEAD")}
   )
   public void method3(CallbackInfo var1) {
      AOTVReturnModule.method1();
   }

   @Redirect(
      method = {"runTick"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z",
   ordinal = 0
)
   )
   public boolean isDown(KeyBinding var1) {
      return var1.isKeyDown() || KillAuraModule.field24 != null && !KillAuraModule.field26.method0(2) && !KillAuraModule.field26.method0(3);
   }

   private boolean isUsingTerm() {
      if (!DelaysModule.field2.cU_.method1()) {
         return true;
      } else if (Oringo.field9.thePlayer.getHeldItem() == null) {
         return false;
      } else {
         String var1 = BlockHitModule.method0(Oringo.field9.thePlayer.getHeldItem());
         if (var1 == null) {
            return false;
         } else if (var1.endsWith("POWER_ORB")) {
            return false;
         } else {
            return "TERMINATOR".equals(var1) || "ARTISANAL_SHORTBOW".equals(var1) || "ITEM_SPIRIT_BOW".equals(var1) || "JUJU_SHORTBOW".equals(var1);
         }
      }
   }

   @Inject(
      method = {"clickMouse"},
      at = {@At("RETURN")}
   )
   public void method4(CallbackInfo var1) {
      if (DelaysModule.field2.method44() && this.leftClickCounter == 10 && (this.objectMouseOver == null || this.objectMouseOver.typeOfHit == MovingObjectType.MISS)) {
         this.leftClickCounter = (int)DelaysModule.field2.field0.method0();
      }

   }

   @Unique
   private void oringo$fastBreakTick() {
      boolean var1 = this.currentScreen == null && this.gameSettings.keyBindAttack.isKeyDown() && this.inGameHasFocus;
      boolean var2 = this.oringo$fastBreakCactus();
      if (var1 && this.objectMouseOver != null && this.renderViewEntity != null && this.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
         if (var2) {
            Oringo.field9.theWorld.setBlockToAir(this.objectMouseOver.getBlockPos());
         }

         double var3 = Class362.field35.field2.method0();
         boolean var5 = var3 % 1.0D != 0.0D;
         if (var5) {
            this.field21 = !this.field21;
         }

         for(int var6 = 0; (double)var6 < var3 + (var5 && this.field21 ? 0.5D : -0.5D); ++var6) {
            BlockPos var7 = this.objectMouseOver.getBlockPos();
            this.entityRenderer.getMouseOver(1.0F);
            if (this.objectMouseOver == null) {
               break;
            }

            BlockPos var8 = this.objectMouseOver.getBlockPos();
            if (var8 == null || this.objectMouseOver.typeOfHit != MovingObjectType.BLOCK || var8.equals(var7) || this.theWorld.getBlockState(var8).getBlock().getMaterial() == Material.air) {
               break;
            }

            float var9 = this.theWorld.getBlockState(var8).getBlock().getPlayerRelativeBlockHardness(this.thePlayer, this.thePlayer.worldObj, var8);
            if (var9 < 1.0F && !var2) {
               return;
            }

            if (this.oringo$fastBreakCactus()) {
               this.playerController.resetBlockRemoving();
            }

            this.thePlayer.swingItem();
            this.playerController.clickBlock(var8, this.objectMouseOver.sideHit);
         }
      }

   }

   @Inject(
      method = {"runTick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V"
)}
   )
   public void method5(CallbackInfo var1) {
      int var2 = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
      char var3 = Keyboard.getEventCharacter();
      if (Keyboard.getEventKeyState() && Oringo.field9.currentScreen == null) {
         if (var3 == AutoRabbitModule.method1() && this.gameSettings.chatVisibility != EnumChatVisibility.HIDDEN) {
            Oringo.field9.displayGuiScreen(new GuiChat());
         }

         Class362.method0(var2);
      }

   }

   @Inject(
      method = {"middleClickMouse"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method6(CallbackInfo var1) {
      if ((new Class148()).method7()) {
         var1.cancel();
      }

   }

   @Inject(
      method = {"runTick"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/settings/KeyBinding;isPressed()Z",
   ordinal = 7
)}
   )
   public void method7(CallbackInfo var1) {
      if (Class362.field31.method44() && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
         while(Oringo.field9.gameSettings.keyBindAttack.isPressed()) {
            this.clickMouse();
         }
      } else if (Class362.field18.method44() && (this.gameSettings.keyBindAttack.isPressed() || this.gameSettings.keyBindAttack.isKeyDown() && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) && (!this.thePlayer.isSwingInProgress || this.thePlayer.swingProgressInt >= (int)((double)((EntityLivingBaseAccessor)this.thePlayer).getArmSwingAnimationEndInvoker() / Class362.field18.field0.method0()) || this.thePlayer.swingProgressInt < 0)) {
         this.thePlayer.swingProgressInt = -1;
         this.thePlayer.isSwingInProgress = true;
         if (this.thePlayer.worldObj instanceof WorldServer) {
            ((WorldServer)this.thePlayer.worldObj).getEntityTracker().sendToAllTrackingEntity(this.thePlayer, new S0BPacketAnimation(this.thePlayer, 0));
         }
      }

   }

   @Inject(
      method = {"runGameLoop"},
      at = {@At(
   value = "INVOKE",
   target = "Ljava/lang/Thread;yield()V"
)}
   )
   public void method8(CallbackInfo var1) {
      FastBreakModule.method6();
   }

   @Shadow
   protected abstract void clickMouse();

   @Inject(
      method = {"sendClickBlockToController"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/entity/EntityPlayerSP;swingItem()V",
   shift = Shift.AFTER
)}
   )
   public void method9(CallbackInfo var1) {
      if (Class362.field35.method44()) {
         this.oringo$fastBreakTick();
      }
   }

   @Inject(
      method = {"getRenderViewEntity"},
      at = {@At("HEAD")}
   )
   public void method0(CallbackInfoReturnable var1) {
      if (LividFinderModule.method5().method44() && this.renderViewEntity != null && this.renderViewEntity == Oringo.field9.thePlayer) {
         ((EntityLivingBase)this.renderViewEntity).rotationYawHead = ((EntityPlayerSPAccessor)this.renderViewEntity).getLastReportedYaw();
      }
   }

   @Redirect(
      method = {"runTick"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/GuiScreen;handleInput()V"
)
   )
   public void onAutoTerminalsClick(GuiScreen var1) throws IOException {
      if (var1 instanceof GuiChest) {
         (new Class156((ContainerChest)((GuiChest)var1).inventorySlots, ((GuiContainerAccessor)var1).getSlotAtPos(Class148.method6(), Class210.t_()))).method7();
      }

      var1.handleInput();
   }

   @Inject(
      method = {"clickMouse"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method10(CallbackInfo var1) {
      if (!AutoFishNewModule.method7() && !Class229.method1().method44()) {
         if ((new Class307()).method7()) {
            var1.cancel();
         }

         if (Class362.field54.method44()) {
            this.leftClickCounter = 0;
         }

      } else {
         var1.cancel();
      }
   }

   @Inject(
      method = {"runTick"},
      at = {@At(
   value = "INVOKE",
   target = "Lorg/lwjgl/input/Mouse;getEventButton()I"
)}
   )
   public void method11(CallbackInfo var1) {
      int var2 = Mouse.getEventButton() - 100;
      if (Mouse.getEventButtonState() && Oringo.field9.currentScreen == null) {
         Class362.method0(var2);
      }

   }

   @Unique
   private boolean oringo$fastBreakCactus() {
      BlockPos var1 = this.objectMouseOver.getBlockPos();
      return GardenHelperModule.method1(var1);
   }

   @Shadow
   public abstract Entity getRenderViewEntity();

   @Inject(
      method = {"setIngameNotInFocus"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method12(CallbackInfo var1) {
      if (this.currentScreen instanceof GuiChest && Class362.field57.method0((ContainerChest)((GuiChest)this.currentScreen).inventorySlots)) {
         KeyBinding.unPressAllKeys();
         var1.cancel();
      }

   }

   @Redirect(
      method = {"sendClickBlockToController"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;resetBlockRemoving()V"
)
   )
   public void onResetBlockRemoving(PlayerControllerMP var1) {
      if (Class220.field4) {
         Class220.field4 = false;
      } else {
         var1.resetBlockRemoving();
      }
   }
}
