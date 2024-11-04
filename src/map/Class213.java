package map;

import java.util.concurrent.TimeUnit;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovementInput;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.command.IRCNameCommand;
import oringo.event.Class468;
import oringo.module.ThornAimbotModule;

public class Class213 extends Class83 {
   public int field4;
   public final Class447 bI_ = new Class447();
   public Class34 field6;
   public EnumFacing field7;
   public int field8;
   public int field5 = 0;
   public Class34 field10;

   public boolean method3() {
      if (this.field3.thePlayer != null && Class496.field6) {
         BlockPos var1 = new BlockPos(this.field3.thePlayer);
         if (!Class514.method0(var1) && ThornAimbotModule.method0(this.field1.method13())) {
            if (this.field1.method30() == null) {
               return false;
            } else if (this.field7 != this.field1.method30()) {
               return true;
            } else {
               return this.field6 == null || IRCNameCommand.method0(this.field6, Class256.method1()) >= 2.0D;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void method2() {
      if (this.field1.method5()) {
         this.method0(true);
         if (this.field1.method35()) {
            this.method0();
         }

         if (this.field1.method28()) {
            Class157.method0();
         }

         if (this.field1.method22()) {
            this.field1.method1(false);
         }

         ++this.field5;
      }

   }

   public void method0(ClientTickEvent var1) {
      if (this.field3.thePlayer == null || !Class496.field6) {
         this.field1.method23();
      }

   }

   public void o_() {
      super.o_();
      if (this.field5 == 0) {
         this.bI_.o_();
      } else if (this.bI_.method0(1L, TimeUnit.MINUTES, true)) {
         this.field5 = 0;
      }

      this.field6 = null;
      this.field8 = 0;
      this.field10 = null;
      this.field7 = null;
      this.field4 = 0;
   }

   public Class213() {
      super("Rotation");
   }

   public boolean method4() {
      return this.Y_ < 7 && this.field1.method21();
   }

   public static float method0(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public void method0(Class468 var1) {
      if (this.field1.method30() == null) {
         this.field1.method23();
      } else {
         ++this.Y_;
         if (this.Y_ < (this.field5 > 1 ? 18 : 40)) {
            var1.method0(this.Y_ < 12 ? this.field1.method25() : new MovementInput());
            if (this.Y_ > 11 && this.Y_ < 15 && this.field5 == 1) {
               EntityPlayerSP var10000 = this.field3.thePlayer;
               var10000.rotationYaw += Class430.method0(5.0F, 15.0F);
               this.field3.thePlayer.rotationPitch = Class163.method0(this.field3.thePlayer.rotationPitch + Class430.method0(5.0F, 12.0F), 90.0F, -90.0F);
            }

         } else if (this.field4-- <= 0) {
            if (this.field10 != null && IRCNameCommand.method0(this.field10, Class256.method1()) > 2.0D) {
               this.field4 = 5;
            }

            if (this.field6 == null) {
               this.field6 = this.field1.method13().method0(this.field1.method30());
               this.field1.method0(this.field6);
               this.field7 = this.field1.method30();
            }

            Class34 var2 = Class256.method1();
            double var3 = IRCNameCommand.method0(var2, this.field6);
            Class34 var5 = Class305.method0(Class256.method1(), this.field6, var3 > 50.0D ? 15.0F : (var3 > 30.0D ? 10.0F : (var3 > 10.0D ? 5.0F : 2.0F)));
            this.field3.thePlayer.rotationYaw = var5.method5();
            this.field3.thePlayer.rotationPitch = var5.method2();
            this.field10 = var5;
            if (IRCNameCommand.method0(var5, this.field6) < 2.0D && this.field8++ > 5) {
               this.field1.method23();
               this.field6 = var5;
               this.field1.method0(this.field6);
            }

         }
      }
   }
}
