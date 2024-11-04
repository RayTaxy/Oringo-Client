package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.mixin.MinecraftAccessor;
import oringo.module.Category;
import oringo.module.SubCategory;
import oringo.module.TargetStrafeModule;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class Class393 extends Class408 {
   public static final DoubleSetting field0 = new Class393$1("Max CPS", 12.0D, 1.0D, 100.0D, 1.0D);
   public final Class447 field1 = new Class447();
   public final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Sync with TPS", false)).method2("Placebo setting");
   public double Z_ = 10.0D;
   public static final DoubleSetting field4 = new Class393$2("Min CPS", 10.0D, 1.0D, 100.0D, 1.0D);
   public static final EnumSetting field5 = (EnumSetting)(new EnumSetting("Mode", "Held", new String[]{"Toggle", "Held"})).method2("When the Auto Clicker clicks\nHeld - Clicks while the button is pressed\nToggle - Clicks after the button is pressed and stops when its pressed again");

   public static Map method0(ItemStack var0) {
      HashMap var1 = new HashMap();
      if (var0 == null) {
         return var1;
      } else {
         NBTTagCompound var2 = TargetStrafeModule.method0(var0);
         if (var2 != null && var2.hasKey("enchantments")) {
            NBTTagCompound var3 = var2.getCompoundTag("enchantments");
            Iterator var4 = var3.getKeySet().iterator();

            while(var4.hasNext()) {
               String var5 = (String)var4.next();
               var1.put(var5, var3.getInteger(var5));
            }

            return var1;
         } else {
            return var1;
         }
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field58.thePlayer != null && this.method1() && field58.currentScreen == null) {
         long var2 = (long)(1000.0D / this.Z_);
         if (this.field2.method1()) {
            var2 = (long)((double)var2 / Math.max((double)Class362.field7.method11() / 20.0D, 1.0E-10D));
         }

         if (this.field1.a_(var2)) {
            this.field1.o_();
            this.Z_ = Class427.method1(field0.method0(), field4.method0());
            KeyBinding.onTick(field58.gameSettings.keyBindUseItem.getKeyCode());
            ((MinecraftAccessor)field58).setPlaceDelay(0);
         }

      }
   }

   public Class393() {
      super("Right Clicker", Category.combat, SubCategory.combat, "Right clicks while the keybind is held");
      this.method0((Setting[])(new Setting[]{field4, field0, this.field2, field5}));
   }

   public boolean method1() {
      String var1 = field5.method4();
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
         return false;
      }
   }

   public static void method0(double var0, double var2, double var4, double var6, double var8, int var10) {
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      float var11 = (float)(var10 >> 24 & 255) / 255.0F;
      float var12 = (float)(var10 >> 16 & 255) / 255.0F;
      float var13 = (float)(var10 >> 8 & 255) / 255.0F;
      float var14 = (float)(var10 & 255) / 255.0F;
      var8 /= 2.0D;
      GlStateManager.color(var12, var13, var14, var11);
      Class479.field3.begin(9, DefaultVertexFormats.POSITION);

      int var15;
      for(var15 = 0; var15 <= 90; var15 += 3) {
         Class479.field3.pos(var0 + var8 + Math.sin((double)var15 * 3.141592653589793D / 180.0D) * var8 * -1.0D, var2 + var8 + Math.cos((double)var15 * 3.141592653589793D / 180.0D) * var8 * -1.0D, 0.0D).endVertex();
      }

      for(var15 = 90; var15 <= 180; var15 += 3) {
         Class479.field3.pos(var0 + var8 + Math.sin((double)var15 * 3.141592653589793D / 180.0D) * var8 * -1.0D, var6 - var8 + Math.cos((double)var15 * 3.141592653589793D / 180.0D) * var8 * -1.0D, 0.0D).endVertex();
      }

      for(var15 = 0; var15 <= 90; var15 += 3) {
         Class479.field3.pos(var4 - var8 + Math.sin((double)var15 * 3.141592653589793D / 180.0D) * var8, var6 - var8 + Math.cos((double)var15 * 3.141592653589793D / 180.0D) * var8, 0.0D).endVertex();
      }

      for(var15 = 90; var15 <= 180; var15 += 3) {
         Class479.field3.pos(var4 - var8 + Math.sin((double)var15 * 3.141592653589793D / 180.0D) * var8, var2 + var8 + Math.cos((double)var15 * 3.141592653589793D / 180.0D) * var8, 0.0D).endVertex();
      }

      Class479.field1.draw();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }
}
