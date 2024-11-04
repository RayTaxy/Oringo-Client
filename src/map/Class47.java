package map;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.Oringo;
import oringo.mixin.MinecraftAccessor;
import oringo.module.BlockHitModule;
import oringo.module.Category;
import oringo.module.SubCategory;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class Class47 extends Class408 {
   public final BooleanSetting field0 = (BooleanSetting)(new BooleanSetting("Sync with TPS", false)).method2("Placebo setting");
   public static final DoubleSetting I_ = new Class47$1("Max CPS", 12.0D, 1.0D, 100.0D, 1.0D);
   public final Class447 field2 = new Class447();
   public static final EnumSetting J_ = (EnumSetting)(new EnumSetting("Mode", "Held", new String[]{"Toggle", "Held"})).method2("When the Auto Clicker clicks\nHeld - Clicks while the button is pressed\nToggle - Clicks after the button is pressed and stops when its pressed again");
   public double Z_ = 10.0D;
   public static final DoubleSetting field5 = new Class47$2("Min CPS", 10.0D, 1.0D, 100.0D, 1.0D);

   public static boolean method0(ItemStack var0) {
      if (var0 != null && var0.getItem() instanceof ItemBow) {
         String var1 = BlockHitModule.method0(var0);
         return "ARTISANAL_SHORTBOW".equals(var1) || "DRAGON_SHORTBOW".equals(var1) || "ITEM_SPIRIT_BOW".equals(var1) || "JUJU_SHORTBOW".equals(var1) || "TERMINATOR".equals(var1);
      } else {
         return false;
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field58.thePlayer != null && this.method1() && field58.currentScreen == null) {
         long var2 = (long)(1000.0D / this.Z_);
         if (this.field0.method1()) {
            var2 = (long)((double)var2 / Math.max((double)Class362.field7.method11() / 20.0D, 1.0E-10D));
         }

         if (this.field2.a_(var2)) {
            this.field2.o_();
            this.Z_ = Class427.method1(I_.method0(), field5.method0());
            KeyBinding.onTick(field58.gameSettings.keyBindAttack.getKeyCode());
            ((MinecraftAccessor)field58).setClickDelay(0);
         }

      }
   }

   public Class47() {
      super("Left Clicker", Category.combat, SubCategory.combat, "Left clicks while the keybind is held");
      this.method0((Setting[])(new Setting[]{field5, I_, this.field0, J_}));
   }

   public static void method0(int var0, int var1, float var2, float var3, int var4, int var5, int var6, int var7, float var8, float var9, ResourceLocation var10) {
      if (var10 != null) {
         GlStateManager.enableTexture2D();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         Oringo.field9.getTextureManager().bindTexture(var10);
         Gui.drawScaledCustomSizeModalRect(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
         GlStateManager.disableBlend();
      }

   }

   public boolean method1() {
      String var1 = J_.method4();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1784436876:
         if (var1.equals("Toggle")) {
            var2 = 1;
         }
         break;
      case 2245461:
         if (var1.equals("Held")) {
            var2 = 0;
         }
      }

      switch(var2) {
      case 0:
         return super.method1();
      case 1:
         return this.method7();
      default:
         return field58.gameSettings.keyBindAttack.isKeyDown();
      }
   }
}
