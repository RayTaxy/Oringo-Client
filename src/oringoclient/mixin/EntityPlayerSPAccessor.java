package oringo.mixin;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EntityPlayerSP.class})
public interface EntityPlayerSPAccessor {
   @Accessor
   void setLastReportedPosY(double var1);

   @Accessor
   void setServerSneakState(boolean var1);

   @Accessor
   void setServerSprintState(boolean var1);

   @Accessor
   float getLastReportedPitch();

   @Accessor
   void setLastReportedPosZ(double var1);

   @Accessor
   void setLastReportedYaw(float var1);

   @Accessor
   double getLastReportedPosY();

   @Accessor
   boolean getServerSneakState();

   @Accessor
   double getLastReportedPosZ();

   @Accessor
   float getLastReportedYaw();

   @Accessor
   void setLastReportedPosX(double var1);

   @Accessor
   void setLastReportedPitch(float var1);

   @Accessor
   double getLastReportedPosX();

   @Accessor
   boolean getServerSprintState();
}
