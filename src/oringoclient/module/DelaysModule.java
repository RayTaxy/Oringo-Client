package oringo.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class DelaysModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Hit Delay", 0.0D, 0.0D, 10.0D, 1.0D);
   public final BooleanSetting cU_ = new BooleanSetting("Only Term", false, this::lambda$new$0);
   public static final DelaysModule field2 = new DelaysModule();
   public final DoubleSetting field3 = new DoubleSetting("Jump Delay", 0.0D, 0.0D, 10.0D, 1.0D);
   public final DoubleSetting field4 = new DoubleSetting("Place Delay", 0.0D, 0.0D, 6.0D, 1.0D);

   public static void method0(Entity var0) {
      if (var0 instanceof EntityZombie && ((EntityZombie)var0).getCurrentArmor(3) != null) {
         Item var1 = ((EntityZombie)var0).getCurrentArmor(3).getItem();
         if (Items.leather_helmet == var1) {
            FlightModule.method0(DojoHelperModule::lambda$left$8);
         } else if (Items.golden_helmet == var1) {
            FlightModule.method0(DojoHelperModule::lambda$left$9);
         } else if (Items.diamond_helmet == var1) {
            FlightModule.method0(DojoHelperModule::lambda$left$10);
         } else if (Items.iron_helmet.equals(var1)) {
            FlightModule.method0(DojoHelperModule::lambda$left$11);
         }
      }

   }

   public Boolean lambda$new$0() {
      return this.field0.method0() == 10.0D;
   }

   public DelaysModule() {
      super("Delays", Category.other, SubCategory.qol, "Modify the click delay");
      this.method0(new Setting[]{this.field0, this.field3, this.field4, this.cU_});
   }
}
