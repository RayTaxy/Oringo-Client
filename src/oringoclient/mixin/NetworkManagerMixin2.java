package oringo.mixin;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import map.Class222;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class189;
import oringo.event.Class332;

@Mixin({NetworkManager.class})
public abstract class NetworkManagerMixin2 implements Class222 {
   @Shadow
   @Final
   private ReentrantReadWriteLock readWriteLock;
   @Shadow
   @Final
   private Queue outboundPacketsQueue;

   @Shadow
   protected abstract void flushOutboundQueue();

   @Shadow
   protected abstract void dispatchPacket(Packet var1, GenericFutureListener[] var2);

   @Inject(
      method = {"channelRead0*"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void method0(ChannelHandlerContext var1, Packet var2, CallbackInfo var3) {
      if ((new Class189(var2, (NetworkManager)this)).method7()) {
         var3.cancel();
      }

   }

   @Inject(
      method = {"sendPacket(Lnet/minecraft/network/Packet;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void method0(Packet var1, CallbackInfo var2) {
      if ((new Class332(var1)).method7()) {
         var2.cancel();
      }

   }

   @Shadow
   public abstract boolean isChannelOpen();

   @Inject(
      method = {"sendPacket(Lnet/minecraft/network/Packet;)V"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void method1(Packet var1, CallbackInfo var2) {
      if ((new Class332.Class0(var1)).method7()) {
         var2.cancel();
      }

   }

   @Inject(
      method = {"channelRead0*"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void method1(ChannelHandlerContext var1, Packet var2, CallbackInfo var3) {
      if ((new Class189.Class0(var2, (NetworkManager)this)).method7()) {
         var3.cancel();
      }

   }

   public void method0(Packet var1) {
      if (this.isChannelOpen()) {
         this.flushOutboundQueue();
         this.dispatchPacket(var1, (GenericFutureListener[])null);
      } else {
         this.readWriteLock.writeLock().lock();

         try {
            this.outboundPacketsQueue.add(new Class27(var1, new GenericFutureListener[]{(GenericFutureListener)null}));
         } finally {
            this.readWriteLock.writeLock().unlock();
         }
      }

   }
}
