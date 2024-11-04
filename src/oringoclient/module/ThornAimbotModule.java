package oringo.module;

import java.awt.Color;
import java.util.List;
import map.Class198;
import map.Class21;
import map.Class245;
import map.Class258;
import map.Class321;
import map.Class350;
import map.Class361;
import map.Class362;
import map.Class447;
import map.Class490;
import map.Class94;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class ThornAimbotModule extends Module {
   public final BooleanSetting field0 = (BooleanSetting)(new BooleanSetting("Auto Bonemerang", true)).method2("Auto stuns thorn when a bonemerang/spear is held");
   public final DoubleSetting field1 = new DoubleSetting("Throw delay", 150.0D, 0.0D, 1000.0D, 50.0D, this::lambda$new$0);
   public final Class447 field2 = new Class447();

   public Boolean lambda$new$0() {
      return !this.field0.method1();
   }

   public static boolean method0(Class258 var0) {
      return Class490.method0(var0, new BlockPos(Class21.field0.thePlayer));
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (Class198.field0.method5() == 4) {
         ItemStack var2 = field58.thePlayer.getHeldItem();
         if (var2 != null) {
            boolean var3 = this.field0.method1() && (var2.getDisplayName().contains("Bonemerang") || var2.getDisplayName().contains("Tribal Spear")) && this.field2.method0((long)this.field1.method3(), true);
            if (var2.getDisplayName().equals("§5Spirit Bow") && field58.thePlayer.isUsingItem() || var3) {
               List var4 = field58.theWorld.getEntities(EntityGhast.class, ThornAimbotModule::lambda$onPre$1);
               if (!var4.isEmpty()) {
                  if (!Class361.method0((Class94)(new Class350()))) {
                     return;
                  }

                  var1.method0(Class321.method0((Entity)var4.get(0)));
               }
            }

         }
      }
   }

   public static void method0(ItemStack var0, float var1) {
      ScaledResolution var2 = new ScaledResolution(Class245.field1);
      int var3 = var2.getScaledWidth() / 2 + 95 + Class245.field0 * 35;
      int var4 = var2.getScaledHeight() - 35;
      MinigameAimbotModule.method0((float)var3, (float)var4, 15.0F, 3.0F, var1, new Color(30, 30, 30, 30), Class362.field7.method0((float)Class245.field0));
      Class245.field1.getRenderItem().renderItemAndEffectIntoGUI(var0, var3 + 15 - 8, var4 + 15 - 8);
      ++Class245.field0;
   }

   public static boolean lambda$onPre$1(EntityGhast var0) {
      return true;
   }

   public ThornAimbotModule() {
      super("Thorn Aimbot", Category.dungeon, SubCategory.main);
      this.method0((Setting[])(new Setting[]{this.field0, this.field1}));
   }
}
