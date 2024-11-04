package oringo.module;

import map.Class362;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.DebugCommand;
import oringo.event.Class274;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.KeyBindSetting;
import oringo.setting.Setting;

public class ExtraFeaturesModule extends Module {
   public final KeyBindSetting field0 = new KeyBindSetting("Auto route setup keybind", ExtraFeaturesModule::lambda$new$0, this::lambda$new$1);
   public final BooleanSetting v_ = new BooleanSetting("Auto Snipe", false, "Automatically buys auctions sent in chat by mods");
   public final BooleanSetting field2 = new BooleanSetting("Auto route setup", false, "Creates .brush placewarp after etherwarp");

   @SubscribeEvent
   public void method0(Post var1) {
      if (this.field2.method1()) {
         if (this.field0.method44()) {
            String var2 = "Route setup enabled!";
            ScaledResolution var3 = new ScaledResolution(field58);
            field58.fontRendererObj.drawString(var2, var3.getScaledWidth() - field58.fontRendererObj.getStringWidth(var2) - 1, var3.getScaledHeight() - 10, Class362.field7.method17().getRGB());
            AutoCloseModule.method5();
         }
      }
   }

   public ExtraFeaturesModule() {
      super("Extra Features", Category.other, SubCategory.other, "Allows you to enable features, which don't have their own modules");
      this.method0((Setting[])(new Setting[]{this.v_, this.field2, this.field0}));
   }

   public static Boolean lambda$new$0() {
      return true;
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
      if (this.field2.method1()) {
         if (this.field0.method44()) {
            if (field58.gameSettings.keyBindSneak.isKeyDown()) {
               if (CreateNewkeybindModule.method0(field58.thePlayer.getHeldItem(), "Ability: Ether Transmission", false)) {
                  if (Class362.field42.method44() && !Class362.field42.field0.method1()) {
                     MovingObjectPosition var2 = AntiTentacleModule.method0(field58.thePlayer.rotationYaw, field58.thePlayer.rotationPitch, 61.0F);
                     if (var2.typeOfHit == MovingObjectType.BLOCK) {
                        EnumSetting.method0(ThystHiderModule.method3(), var2.hitVec);
                     }

                  }
               }
            }
         }
      }
   }

   public static boolean method0(TileEntitySkull var0) {
      if (var0.getSkullType() == 3 && var0.getPlayerProfile() != null && var0.getPlayerProfile().getProperties() != null) {
         if (GiftESPModule.field0.method1() && !var0.getPlayerProfile().getId().toString().startsWith("04049c90-d3e9-4621-9caf-") && DebugCommand.method0(GiftESPModule::lambda$isGift$1)) {
            return true;
         }

         if (GiftESPModule.field1.method1() && var0.getPlayerProfile().getName() != null && (var0.getPlayerProfile().getName().length() == 10 && var0.getPlayerProfile().getName().toLowerCase().equals(var0.getPlayerProfile().getName()) || var0.getPlayerProfile().getName().equals("§item"))) {
            return true;
         }
      }

      return false;
   }

   public Boolean lambda$new$1() {
      return !this.field2.method1();
   }
}
