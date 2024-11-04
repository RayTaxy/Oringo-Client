package oringo.mixin;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.oio.OioSocketChannel;
import java.net.Proxy;
import java.net.Socket;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import oringo.Oringo;

@Mixin({NetworkManager.class})
public class NetworkManagerMixin {
   @Redirect(
      method = {"createNetworkManagerAndConnect"},
      at = @At(
   value = "INVOKE",
   target = "Lio/netty/bootstrap/Bootstrap;group(Lio/netty/channel/EventLoopGroup;)Lio/netty/bootstrap/AbstractBootstrap;"
)
   )
   private static AbstractBootstrap onSetGroup(Bootstrap var0, EventLoopGroup var1) {
      Proxy var2 = Oringo.field0.l_();
      if (var2 == null) {
         var0.group(var1);
         return var0;
      } else {
         OioEventLoopGroup var3 = new OioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty OIO Client IO #%d").setDaemon(true).build());
         ((Bootstrap)var0.group(var3)).channelFactory(NetworkManagerMixin::lambda$onSetGroup$0);
         return var0;
      }
   }

   @Redirect(
      method = {"createNetworkManagerAndConnect"},
      at = @At(
   value = "INVOKE",
   target = "Lio/netty/bootstrap/Bootstrap;channel(Ljava/lang/Class;)Lio/netty/bootstrap/AbstractBootstrap;"
)
   )
   private static AbstractBootstrap onCreateNetworkManager(Bootstrap var0, Class var1) {
      if (!(var0.group() instanceof OioEventLoopGroup)) {
         var0.channel(var1);
      }

      return var0;
   }

   private static Channel lambda$onSetGroup$0(Proxy var0) {
      return new OioSocketChannel(new Socket(var0));
   }
}
