package oringo.module;

import java.awt.Color;
import map.Class122;
import map.Class368;
import map.Class376;
import map.Class496;
import map.Class506;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.command.XRayCommand;
import oringo.event.Class125;
import oringo.event.Class438;

public class ArcadeESPModule extends Module {
   public boolean field0 = false;
   public boolean aY_ = false;
   public boolean field2 = false;

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      this.field2 = Class376.method0("Mode: Prop Hunt");
      this.field0 = Class376.method0("Mode: Party Pooper");
      this.aY_ = XRayCommand.method2().equals("FARM HUNT");
   }

   @SubscribeEvent
   public void method0(Class125 var1) {
      if (!Class496.field7 && Class506.method0((Entity)var1.field4)) {
         if (this.field2 && var1.field4 instanceof EntityArmorStand && var1.field4.getHeldItem() != null && var1.field4.rotationPitch != 0.0F) {
            MinigameAimbotModule.method0(var1, Color.RED);
         }

         if (this.field0 && var1.field4 instanceof EntityOtherPlayerMP && !var1.field4.isInvisible() && Class368.method0(var1.field4) != 0.1D && Class122.method0((EntityOtherPlayerMP)var1.field4)) {
            MinigameAimbotModule.method0(var1, Color.RED);
         }

         if (this.aY_ && var1.field4 instanceof EntityAnimal && Class368.method0(var1.field4) != 0.0D && var1.field4.rotationPitch != 0.0F) {
            MinigameAimbotModule.method0(var1, Color.RED);
         }

      }
   }

   @SubscribeEvent
   public void method0(Class438 var1) {
      if (var1.field0 instanceof EntityOtherPlayerMP && Class376.method0("Mode: Party Pooper")) {
         if (var1.field0 instanceof EntityOtherPlayerMP && !var1.field0.isInvisible() && Class368.method0((EntityOtherPlayerMP)var1.field0) != 0.1D && Class122.method0((EntityOtherPlayerMP)var1.field0)) {
            return;
         }

         var1.method9();
      }

   }

   public static boolean method3() {
      return false;
   }

   public ArcadeESPModule() {
      super("Arcade ESP", Category.render, SubCategory.world, "ESP for hide and seek arcade games.\nYou will also see props that hiders used before on Prop Hunt");
   }
}
