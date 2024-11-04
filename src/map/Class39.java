package map;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Class39 extends MessageToByteEncoder {
   public final Class398 field0;

   public Class39(Class398 var1) {
      this.field0 = var1;
   }

   public void method0(ChannelHandlerContext var1, Object var2, ByteBuf var3) throws Exception {
      this.method0(var1, (ByteBuf)var2, var3);
   }

   public void method0(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) {
      byte[] var4 = this.field0.method1(var2);
      var3.writeBytes(var4);
   }
}
