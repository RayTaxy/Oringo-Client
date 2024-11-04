package oringo.mixin;

import map.Class362;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S08PacketPlayerPosLook.EnumFlags;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class270;
import oringo.module.AutoArrowModule;

@Mixin({NetHandlerPlayClient.class})
public abstract class NetHandlerPlayClientMixin {
   @Shadow
   private Minecraft gameController;
   @Shadow
   private boolean doneLoadingTerrain;
   @Shadow
   @Final
   private NetworkManager netManager;

   @Inject(
      method = {"handlePlayerPosLook"},
      at = {@At("RETURN")}
   )
   public void method0(S08PacketPlayerPosLook var1, CallbackInfo var2) {
      AutoArrowModule.method0(var1);
   }

   @Inject(
      method = {"handleJoinGame"},
      at = {@At("RETURN")}
   )
   public void method0(S01PacketJoinGame var1, CallbackInfo var2) {
      (new Class270()).method7();
   }

   @Redirect(
      method = {"handleUpdateTileEntity"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/WorldClient;isBlockLoaded(Lnet/minecraft/util/BlockPos;)Z"
)
   )
   public boolean handleUpdateTileEntity(WorldClient var1, BlockPos var2) {
      return var1.getTileEntity(var2) != null;
   }

   @Inject(
      method = {"handlePlayerPosLook"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(S08PacketPlayerPosLook var1, CallbackInfo var2) {
      if (Class362.field59.method44()) {
         var2.cancel();
         Minecraft var3 = this.gameController;
         double var4 = var1.getX();
         double var6 = var1.getY();
         double var8 = var1.getZ();
         float var10 = var1.getYaw();
         float var11 = var1.getPitch();
         if (var1.func_179834_f().contains(EnumFlags.X)) {
            var4 += var3.thePlayer.posX;
         } else if (!Class362.field59.bd_.method1()) {
            var3.thePlayer.motionX = 0.0D;
         }

         if (var1.func_179834_f().contains(EnumFlags.Y)) {
            var6 += var3.thePlayer.posY;
         } else {
            var3.thePlayer.motionY = 0.0D;
         }

         if (var1.func_179834_f().contains(EnumFlags.Z)) {
            var8 += var3.thePlayer.posZ;
         } else if (!Class362.field59.bd_.method1()) {
            var3.thePlayer.motionZ = 0.0D;
         }

         if (var1.func_179834_f().contains(EnumFlags.X_ROT)) {
            var11 += var3.thePlayer.rotationPitch;
         }

         if (var1.func_179834_f().contains(EnumFlags.Y_ROT)) {
            var10 += var3.thePlayer.rotationYaw;
         }

         if (var11 == 0.0F && Class362.field59.field2.method1() && !var1.func_179834_f().contains(EnumFlags.X_ROT)) {
            var3.thePlayer.rotationYaw = var10 % 360.0F;
            var3.thePlayer.rotationPitch = var11 % 360.0F;
         }

         var3.thePlayer.setPosition(var4, var6, var8);
         ((EntityPlayerSPAccessor)var3.thePlayer).setLastReportedPosX(var4);
         ((EntityPlayerSPAccessor)var3.thePlayer).setLastReportedPosY(var6);
         ((EntityPlayerSPAccessor)var3.thePlayer).setLastReportedPosZ(var8);
         ((EntityPlayerSPAccessor)var3.thePlayer).setLastReportedYaw(var10 % 360.0F);
         ((EntityPlayerSPAccessor)var3.thePlayer).setLastReportedPitch(var11 % 360.0F);
         this.netManager.sendPacket(new C06PacketPlayerPosLook(var4, var6, var8, var10 % 360.0F, var11 % 360.0F, false));
         if (!this.doneLoadingTerrain) {
            var3.thePlayer.prevPosX = var3.thePlayer.posX;
            var3.thePlayer.prevPosY = var3.thePlayer.posY;
            var3.thePlayer.prevPosZ = var3.thePlayer.posZ;
            var3.displayGuiScreen((GuiScreen)null);
            this.doneLoadingTerrain = true;
         }

      }
   }
}
