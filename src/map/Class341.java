package map;

import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.event.Class468;
import oringo.module.ThornAimbotModule;

public class Class341 extends Class83 {
   public final Class447 field4 = new Class447();
   public int field5 = 0;
   public final Class447 field6 = new Class447();

   public void method0(ClientTickEvent var1) {
      if (this.field5 > 3) {
         if (this.Y_ > 25 && this.field3.thePlayer != null) {
            this.field3.theWorld.sendQuittingDisconnectingPacket();
            this.field3.loadWorld((WorldClient)null);
            this.field3.displayGuiScreen(new GuiMainMenu());
         } else if (this.field6.method0(5L, TimeUnit.MINUTES)) {
            this.field1.method0((Class83)Class526.method0());
         }
      } else {
         if (this.field3.thePlayer == null || !Class496.field6) {
            this.field1.method23();
         }

      }
   }

   public void method2() {
      this.method0(true);
      if (this.field1.method35()) {
         this.method0();
      }

      if (this.field1.method28()) {
         Class157.method0();
      }

      if (this.field1.method5()) {
         if (this.field1.method22()) {
            this.field1.method1(false);
         }

         ++this.field5;
      }

   }

   public Class341() {
      super("Outside farm");
   }

   public void method0(Class468 var1) {
      if (this.Y_++ < 30) {
         if (this.Y_ < 20) {
            var1.method0(this.field1.method25());
         }

      } else if (ThornAimbotModule.method0(this.field1.method13())) {
         this.field1.method23();
      } else {
         if (this.field4.method0(1000L, true)) {
            this.field3.thePlayer.sendChatMessage("/warp garden");
         }

      }
   }

   public boolean method4() {
      return this.Y_ < 10 && this.field1.method21();
   }

   public boolean method3() {
      if (!this.field1.method14().method1()) {
         return false;
      } else if (this.field3.thePlayer != null && Class496.field6) {
         BlockPos var1 = new BlockPos(this.field3.thePlayer);
         return !Class514.method0(var1) && !ThornAimbotModule.method0(this.field1.method13());
      } else {
         return false;
      }
   }

   public static long method0(long var0, long var2, long var4) {
      if (var2 < var4) {
         long var6 = var2;
         var2 = var4;
         var4 = var6;
      }

      return Math.max(Math.min(var2, var0), var4);
   }

   public void o_() {
      super.o_();
      if (this.field5 == 0) {
         this.field6.o_();
      } else if (this.field6.method0(1L, TimeUnit.MINUTES, true)) {
         this.field5 = 0;
      }

   }
}
