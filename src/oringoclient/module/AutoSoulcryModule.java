package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.function.Predicate;
import map.Class361;
import map.Class376;
import map.Class386;
import map.Class402;
import map.Class426;
import map.Class447;
import map.Class94;
import map.Class95;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoSoulcryModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Delay", 4200.0D, 0.0D, 5000.0D, 50.0D);
   public final BooleanSetting bi_ = (BooleanSetting)(new BooleanSetting("Boss spawned", true)).method2("Only activates if your own boss is spawned");
   public boolean field2;
   public final BooleanSetting field3 = (BooleanSetting)(new BooleanSetting("From inventory", false)).method2("Uses the item from inventory");
   public static final Predicate field4 = AutoSoulcryModule::lambda$static$1;
   public final Class447 field5 = new Class447();

   public static boolean lambda$onAttack$3(Entity var0) {
      return var0 instanceof EntityArmorStand && var0.getName().contains("Voidgloom Seraph");
   }

   public static MovingObjectPosition method1(Entity var0) {
      return new MovingObjectPosition(var0, BlazeSwapperModule.method0(var0.getEntityBoundingBox()));
   }

   public static boolean lambda$static$1(ItemStack var0) {
      return var0.getTooltip(field58.thePlayer, false).stream().anyMatch(AutoSoulcryModule::lambda$null$0);
   }

   public static void lambda$onUpdate$2(ItemStack var0) {
      if (var0 != null) {
         Class426.method10();
         method3(new C08PacketPlayerBlockPlacement(var0));
      }
   }

   public static boolean lambda$null$0(String var0) {
      return ChatFormatting.stripFormatting(var0).contains("Soulcry");
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field5.a_((long)this.field0.method3()) && this.method5()) {
         this.field2 = false;
         int var2 = Class95.method0(field4);
         if (var2 == -1 || !this.field3.method1() && var2 < 35) {
            return;
         }

         if (Class361.method0((Class94)(new Class402(var2, AutoSoulcryModule::lambda$onUpdate$2)))) {
            this.field5.o_();
         }
      }

   }

   @SubscribeEvent
   public void method0(AttackEntityEvent var1) {
      if (var1.entityPlayer == field58.thePlayer && var1.target instanceof EntityEnderman && !this.field2) {
         if (!field58.theWorld.getEntitiesInAABBexcluding(var1.target, var1.target.getEntityBoundingBox().offset(0.0D, 2.0D, 0.0D), AutoSoulcryModule::lambda$onAttack$3).isEmpty()) {
            this.field2 = true;
         }

      }
   }

   public AutoSoulcryModule() {
      super("Auto Soulcry", Category.skyblock, SubCategory.slayer, "Activates the soulcry ability");
      this.method0((Setting[])(new Setting[]{this.field0, this.bi_, this.field3}));
   }

   public static void method3() {
      BlazeSwapperModule.method0(Class386.field0.getFramebuffer());
   }

   public boolean method5() {
      return (Class376.method0("Slay the boss!") && Class376.method0("Voidgloom seraph") || !this.bi_.method1()) && this.field2;
   }
}
