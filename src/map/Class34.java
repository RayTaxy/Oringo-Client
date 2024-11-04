package map;

import java.util.Objects;
import net.minecraft.util.MathHelper;
import oringo.Oringo;
import oringo.command.IRCNameCommand;
import oringo.mixin.EntityPlayerSPAccessor;

public class Class34 {
   public float t_;
   public float bF_;

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class34 var2 = (Class34)var1;
         return IRCNameCommand.method0(this, var2) == 0.0D;
      } else {
         return false;
      }
   }

   public Class34(float var1, float var2) {
      this.bF_ = var2;
      this.t_ = var1;
   }

   public Class34 method0() {
      this.bF_ = Class163.method0(MathHelper.wrapAngleTo180_float(this.bF_), -90.0F, 90.0F);
      this.t_ = MathHelper.wrapAngleTo180_float(this.t_);
      return this;
   }

   public Class34 method0(float var1) {
      this.bF_ += var1;
      return this;
   }

   public float method5() {
      return this.t_;
   }

   public float method2() {
      return this.bF_;
   }

   public Class34 method1(float var1) {
      this.bF_ = var1;
      return this;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.t_, this.bF_});
   }

   public Class34 method2(float var1) {
      this.t_ += var1;
      return this;
   }

   public Class34 method3(float var1) {
      this.t_ = var1;
      return this;
   }

   public Class228 method3() {
      float var1 = MathHelper.cos(-this.t_ * 0.017453292F - 3.1415927F);
      float var2 = MathHelper.sin(-this.t_ * 0.017453292F - 3.1415927F);
      float var3 = -MathHelper.cos(-this.bF_ * 0.017453292F);
      float var4 = MathHelper.sin(-this.bF_ * 0.017453292F);
      return new Class228((double)(var2 * var3), (double)var4, (double)(var1 * var3));
   }

   public Class34 method4() {
      this.t_ = ((EntityPlayerSPAccessor)Oringo.field9.thePlayer).getLastReportedYaw() + MathHelper.wrapAngleTo180_float(this.t_ - ((EntityPlayerSPAccessor)Oringo.field9.thePlayer).getLastReportedYaw());
      this.bF_ = ((EntityPlayerSPAccessor)Oringo.field9.thePlayer).getLastReportedPitch() + MathHelper.wrapAngleTo180_float(this.bF_ - ((EntityPlayerSPAccessor)Oringo.field9.thePlayer).getLastReportedPitch());
      this.bF_ = Class163.method0(this.bF_, 90.0F, -90.0F);
      return this;
   }
}
