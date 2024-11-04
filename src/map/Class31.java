package map;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

public class Class31 extends MessageToMessageDecoder {
   public final Class398 field1;

   public void decode(ChannelHandlerContext var1, Object var2, List var3) throws Exception {
      this.decode(var1, (ByteBuf)var2, var3);
   }

   public Class31(Class398 var1) {
      this.field1 = var1;
   }

   public void decode(ChannelHandlerContext var1, ByteBuf var2, List var3) {
      byte[] var4 = this.field1.method0(var2);
      ByteBuf var5 = var1.alloc().heapBuffer(var4.length);
      var5.writeBytes(var4);
      var3.add(var5);
   }
}
