package oringo.module;

import map.Class196;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class EagleModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Only look down", false);
   public static final EnumSetting w_ = new EnumSetting("Mode", "Sneak", new String[]{"Sneak", "Safe walk"});
   public static final BooleanSetting field2 = new BooleanSetting("Only Backwards", true);

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field58.thePlayer != null && field58.theWorld != null && this.method44() && field58.currentScreen == null) {
         if (w_.method0(0)) {
            BlockPos var2 = new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY - 0.5D, field58.thePlayer.posZ);
            if (field58.theWorld.getBlockState(var2).getBlock() != Blocks.air || field58.theWorld.getBlockState(var2.down()).getBlock() != Blocks.air || !field58.thePlayer.onGround || field58.thePlayer.movementInput.moveForward >= 0.1F && field2.method1() || field58.thePlayer.rotationPitch <= 60.0F && field0.method1()) {
               KeyBinding.setKeyBindState(field58.gameSettings.keyBindSneak.getKeyCode(), GameSettings.isKeyDown(field58.gameSettings.keyBindSneak));
            } else {
               KeyBinding.setKeyBindState(field58.gameSettings.keyBindSneak.getKeyCode(), true);
            }
         }

      }
   }

   public void b_() {
      KeyBinding.setKeyBindState(field58.gameSettings.keyBindSneak.getKeyCode(), GameSettings.isKeyDown(field58.gameSettings.keyBindSneak));
   }

   public static void method42() {
      MinecraftForge.EVENT_BUS.register(new Class196());
   }

   public EagleModule() {
      super("Eagle", Category.movement, SubCategory.movement, "Prevents you from walking off blocks");
      this.method0(new Setting[]{w_, field2, field0});
   }

   public static boolean method0(String var0, Object var1) {
      return var0.equals(var1);
   }
}
