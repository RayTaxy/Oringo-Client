package oringo.module;

import java.util.Arrays;
import java.util.List;
import net.minecraft.init.Blocks;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class NoFoliageModule extends Module {
   public static final List field0;
   public final EnumSetting aT_ = new EnumSetting("Remove", "Hitbox + Render", new String[]{"Hitbox + Render", "Hitbox", "Render"});

   public NoFoliageModule() {
      super("No Foliage", Category.render, SubCategory.world, "Removes plants");
      this.method0(new Setting[]{this.aT_});
   }

   static {
      field0 = Arrays.asList(Blocks.vine, Blocks.tallgrass, Blocks.double_plant);
   }
}
