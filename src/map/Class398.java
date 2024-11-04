package map;

import io.netty.buffer.ByteBuf;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;
import net.minecraft.entity.Entity;
import oringo.command.ToggleIRCCommand;

public class Class398 {
   public final DataInputStream field0;
   public final byte[] field1;
   public final DataOutputStream field2;

   public static Class34 method0(Entity var0, float var1) {
      return Class352.method0(var0.posX + (double)Class430.method0(-var1, var1), var0.posY + (double)var0.getEyeHeight() / 2.0D + (double)Class430.method0(-var1, var1), var0.posZ + (double)Class430.method0(-var1, var1));
   }

   public static String method0(String var0, String var1, String var2) {
      try {
         Socket var3 = Class271.method1();
         DataOutputStream var4 = new DataOutputStream(var3.getOutputStream());
         var4.writeByte(0);
         var4.writeUTF(var0);
         var4.writeUTF(var1);
         var4.writeUTF(var2);
         var4.flush();
         DataInputStream var5 = new DataInputStream(var3.getInputStream());
         String var6 = var5.readUTF();
         return var6.isEmpty() ? null : var6;
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }

   public byte[] method0() {
      return this.field1;
   }

   public static void method0(Object var0, String var1, Object var2) {
      try {
         Field var3 = var0.getClass().getDeclaredField(var1);
         var3.setAccessible(true);
         var3.set(var0, var2);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public byte[] method0(ByteBuf var1) {
      int var2 = var1.readableBytes();
      byte[] var3 = new byte[var2];
      var1.readBytes(var3, 0, var2);

      try {
         this.field2.writeBoolean(false);
         ToggleIRCCommand.method0(this.field2, var3);
         this.field2.flush();
         return Class352.method0(this.field0);
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }
   }

   public byte[] method1(ByteBuf var1) {
      int var2 = var1.readableBytes();
      byte[] var3 = new byte[var2];
      var1.readBytes(var3, 0, var2);

      try {
         this.field2.writeBoolean(true);
         ToggleIRCCommand.method0(this.field2, var3);
         this.field2.flush();
         return Class352.method0(this.field0);
      } catch (Exception var5) {
         throw new RuntimeException(var5);
      }
   }

   public Class398(byte[] var1, Socket var2) throws IOException {
      this.field1 = var1;
      this.field0 = new DataInputStream(var2.getInputStream());
      this.field2 = new DataOutputStream(var2.getOutputStream());
   }
}
