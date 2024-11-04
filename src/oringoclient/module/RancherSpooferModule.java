package oringo.module;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class RancherSpooferModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Speed", 400.0D, 100.0D, 500.0D, 1.0D);

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START && field58.thePlayer != null) {
         field58.thePlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(Math.min((double)field58.thePlayer.capabilities.getWalkSpeed(), this.field0.method0() / 1000.0D));
      }
   }

   public RancherSpooferModule() {
      super("Rancher Spoofer", Category.skyblock, SubCategory.skills, "Limit your speed without Rancher Boots");
      this.method0(new Setting[]{this.field0});
   }

   public void b_() {
      if (field58.thePlayer != null) {
         field58.thePlayer.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((double)field58.thePlayer.capabilities.getWalkSpeed());
      }

   }

   public static boolean method0(float var0) {
      return RodStackerModule.method0(var0, 0.0D, 0.0D);
   }
}
