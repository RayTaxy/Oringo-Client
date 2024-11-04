package oringo.mixin;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.MessageDeserializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.module.IceFillHelperModule;

@Mixin({MessageDeserializer.class})
public class MessageDeserializerMixin {
   @Inject(
      method = {"decode"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/network/PacketBuffer;<init>(Lio/netty/buffer/ByteBuf;)V"
)}
   )
   public void method0(ChannelHandlerContext var1, ByteBuf var2, List var3, CallbackInfo var4) {
      if (var1.channel().attr(NetworkManager.attrKeyConnectionState).get() == EnumConnectionState.PLAY) {
         byte[] var5 = var2.array();
         IceFillHelperModule.method0(var5);
      }
   }
}
