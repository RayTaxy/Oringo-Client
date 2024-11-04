package oringo.module;

import map.Class362;
import map.Class447;
import map.Class506;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class TargetHUDModule extends Module {
   public final Class447 field0 = new Class447();
   public final BooleanSetting field1 = new BooleanSetting("Target ESP", true);
   public final DoubleSetting field2 = new DoubleSetting("X123   ", 0.6D, -100000.0D, 100000.0D, 1.0E-5D, TargetHUDModule::lambda$new$0);
   public final BooleanSetting field3 = new BooleanSetting("Blur", true);
   public static final TargetHUDModule field4 = new TargetHUDModule();
   public final BooleanSetting field5 = new BooleanSetting("Only aura", false);
   public EntityLivingBase field6;
   public final DoubleSetting field7 = new DoubleSetting("Y123   ", 0.6D, -100000.0D, 100000.0D, 1.0E-5D, TargetHUDModule::lambda$new$1);

   public static Boolean lambda$new$1() {
      return true;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method44()) {
         if (this.field0.a_(1500L)) {
            this.field6 = null;
         }

         EntityLivingBase var2 = KillAuraModule.field24 == null && !this.field5.method1() ? this.field6 : KillAuraModule.field24;
         if (var2 != null && var2.getHealth() > 0.0F && !var2.isDead && this.field1.method1()) {
            AimAssistModule.method0(var2, Class362.field7.method17(), var1.partialTicks);
         }

      }
   }

   public static void b_(int var0) {
      Oringo.field9.playerController.windowClick(Oringo.field9.thePlayer.inventoryContainer.windowId, var0, 0, 1, Oringo.field9.thePlayer);
   }

   public TargetHUDModule() {
      super("Target HUD", Category.render, SubCategory.ui);
      this.method0((Setting[])(new Setting[]{this.field1, this.field3, this.field5, this.field2, this.field7}));
   }

   public static Boolean lambda$new$0() {
      return true;
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (this.method44()) {
         if (var1.field0 instanceof C02PacketUseEntity && ((C02PacketUseEntity)var1.field0).getAction() == Action.ATTACK) {
            Entity var2 = ((C02PacketUseEntity)var1.field0).getEntityFromWorld(field58.theWorld);
            if (var2 instanceof EntityOtherPlayerMP && Class506.method0(var2)) {
               this.field6 = (EntityLivingBase)var2;
               this.field0.o_();
            }
         }

      }
   }
}
