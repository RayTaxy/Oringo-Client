package oringo.module;

import map.Class362;
import map.Class528;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class194;
import oringo.event.Class217;
import oringo.event.Class332;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class FreeCamModule extends Module {
   public EntityOtherPlayerMP field0;
   public final DoubleSetting aH_ = new DoubleSetting("Vertical speed", 2.0D, 0.1D, 5.0D, 0.1D);
   public final DoubleSetting field2 = new DoubleSetting("Speed", 3.0D, 0.1D, 5.0D, 0.1D);
   public final BooleanSetting field3 = new BooleanSetting("Hide gemstones", true);
   public final BooleanSetting field4 = new BooleanSetting("Reset rotation", false);
   public final BooleanSetting field5 = new BooleanSetting("Show tracer", false);

   @SubscribeEvent
   public void method0(Load var1) {
      this.method1(false);
   }

   public FreeCamModule() {
      super("Free Cam", Category.render, SubCategory.world);
      this.method0((Setting[])(new Setting[]{this.field2, this.aH_, this.field3, this.field5, this.field4}));
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      var1.setCanceled(true);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook) {
         var1.method9();
         method2(AutoHealModule.method0((S08PacketPlayerPosLook)var1.field0));
      }

   }

   public void method4() {
      if (field58.theWorld != null) {
         field58.renderGlobal.loadRenderers();
         this.field0 = new EntityOtherPlayerMP(field58.theWorld, field58.thePlayer.getGameProfile());
         this.field0.inventory.armorInventory = field58.thePlayer.inventory.armorInventory;
         this.field0.inventory.mainInventory = field58.thePlayer.inventory.mainInventory;
         this.field0.onGround = field58.thePlayer.onGround;
         field58.theWorld.addEntityToWorld(-2137, this.field0);
         this.field0.copyLocationAndAnglesFrom(field58.thePlayer);
         this.field0.renderYawOffset = field58.thePlayer.renderYawOffset;
         this.field0.rotationYawHead = field58.thePlayer.rotationYawHead;
      }

   }

   public void b_() {
      if (field58.thePlayer != null && field58.theWorld != null && this.field0 != null) {
         if (this.field0.getDistanceToEntity(field58.thePlayer) > 16.0F || this.field3.method1()) {
            field58.renderGlobal.loadRenderers();
         }

         field58.thePlayer.setPosition(this.field0.posX, this.field0.posY, this.field0.posZ);
         if (this.field4.method1()) {
            field58.thePlayer.rotationPitch = this.field0.rotationPitch;
            field58.thePlayer.rotationYaw = this.field0.rotationYaw;
         }

         field58.theWorld.removeEntityFromWorld(this.field0.getEntityId());
         this.field0 = null;
         field58.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
      }
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      var1.method0(0.0D);
      Dadudze2Module.method0(var1, this.field2.method0());
      if (field58.gameSettings.keyBindJump.isKeyDown()) {
         var1.method0(var1.method6() + this.aH_.method0());
      }

      if (field58.gameSettings.keyBindSneak.isKeyDown()) {
         var1.method0(var1.method6() - this.aH_.method0());
      }

      if (this.field0 != null) {
         this.field0.inventory.currentItem = field58.thePlayer.inventory.currentItem;
      }

   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field0 != null && this.field5.method1()) {
         Class528.method0(this.field0, var1.partialTicks, 1.0F, Class362.field7.method17());
      }

   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C03PacketPlayer) {
         var1.setCanceled(true);
      }

   }

   public static EnumDyeColor method0(ItemStack var0) {
      return EnumDyeColor.byMetadata(var0.getItemDamage());
   }
}
