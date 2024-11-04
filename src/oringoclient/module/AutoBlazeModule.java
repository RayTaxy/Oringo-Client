package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class350;
import map.Class361;
import map.Class447;
import map.Class462;
import map.Class501;
import map.Class94;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.MessageCommand;
import oringo.command.XRayCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.KeyBindSetting;
import oringo.setting.Setting;

public class AutoBlazeModule extends Module {
   public static final BooleanSetting field0 = (BooleanSetting)(new BooleanSetting("Only still", false)).method2("Only attack when not moving");
   public static final EnumSetting R_ = new EnumSetting("Click", "Right", new String[]{"Left", "Right"});
   public final Class447 field2 = new Class447();
   public static final Pattern field3 = Pattern.compile("\\[Lv.*] Blaze .*/(.*?)❤");
   public EntityBlaze field4;
   public boolean field5 = false;
   public static final DoubleSetting field6 = new DoubleSetting("Retry Delay", 1500.0D, 500.0D, 5000.0D, 100.0D);
   public static final BooleanSetting field7 = new BooleanSetting("Use terminator", false);

   public static boolean method1() {
      return XRayCommand.method2().startsWith("SKYBLOCK");
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (SecretHitboxesModule.method0("H Blaze", "L Blaze")) {
         EntityBlaze var2 = this.method2();
         if (var2 != null) {
            Class462.method0(var2.getPositionVector(), 0.6D, Color.GREEN);
         }
      }
   }

   public static void method0(Color var0) {
      GlStateManager.color((float)var0.getRed() / 255.0F, (float)var0.getGreen() / 255.0F, (float)var0.getBlue() / 255.0F, (float)var0.getAlpha() / 255.0F);
   }

   public boolean lambda$getShortBow$2(ItemStack var1) {
      String var2 = BlockHitModule.method0(var1);
      return "TERMINATOR".equals(var2) && field7.method1() && (this.field5 = true) || "ARTISANAL_SHORTBOW".equals(var2) || "ITEM_SPIRIT_BOW".equals(var2) || "JUJU_SHORTBOW".equals(var2) || "DRAGON_SHORTBOW".equals(var2);
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.b_();
   }

   public EntityBlaze method2() {
      boolean var1 = SecretAuraModule.w_("H Blaze");
      List var2 = field58.theWorld.getEntities(EntityArmorStand.class, AutoBlazeModule::lambda$findBlaze$0);
      var2.sort(Comparator.comparingInt(AutoBlazeModule::lambda$findBlaze$1));
      if (var2.isEmpty()) {
         return null;
      } else {
         Entity var3 = field58.theWorld.getEntityByID(((EntityArmorStand)var2.get(0)).getEntityId() - 1);
         return var3 instanceof EntityBlaze ? (EntityBlaze)var3 : null;
      }
   }

   public static boolean lambda$findBlaze$0(EntityArmorStand var0) {
      return var0.getName().contains("Blaze") && var0.getName().contains("/");
   }

   public static int lambda$findBlaze$1(boolean var0, EntityArmorStand var1) {
      String var2 = ChatFormatting.stripFormatting(var1.getName()).trim();
      Matcher var3 = field3.matcher(var2);
      if (var3.find()) {
         return !var0 ? -Integer.parseInt(var3.group(1).replaceAll(",", "")) : Integer.parseInt(var3.group(1).replaceAll(",", ""));
      } else {
         return 1000000;
      }
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (SecretHitboxesModule.method0("H Blaze", "L Blaze") && (!field0.method1() || field58.thePlayer.motionX == 0.0D && field58.thePlayer.motionY == 0.0D && field58.thePlayer.motionZ == 0.0D)) {
         int var2 = this.method3();
         if (var2 != -1) {
            EntityBlaze var3 = this.method2();
            if (var3 != null && (var3 != this.field4 || this.field2.a_((long)field6.method3())) && KeyBindSetting.method0(var3, AutoHackModule.method0(MessageCommand.method0((Entity)var3)))) {
               if (!Class361.method0((Class94)(!R_.method0(0) && !this.field5 ? new Class350(var2) : new Class501(var2)))) {
                  return;
               }

               var1.method0(MessageCommand.method0((Entity)var3));
               this.field4 = var3;
               this.field2.o_();
            }

         }
      }
   }

   public int method3() {
      return TrailModule.method0(this::lambda$getShortBow$2);
   }

   public AutoBlazeModule() {
      super("Auto Blaze", Category.dungeon, SubCategory.puzzle, "Does the blaze puzzle automatically");
      this.method0((Setting[])(new Setting[]{R_, field6, field7, field0}));
   }
}
