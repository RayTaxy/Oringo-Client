package map;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import org.apache.logging.log4j.Logger;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class269;
import oringo.event.Class270;
import oringo.event.Class290;
import oringo.event.Class332;
import oringo.event.Class438;
import oringo.mixin.EntityPlayerSPAccessor;
import oringo.module.CreateNewkeybindModule;
import oringo.module.PopupAnimationModule;
import oringo.module.TerminalAuraModule;

public class Class90 {
   public Field field0;
   public static final Set field1 = new HashSet();
   public final Logger field2 = Oringo.method0("Ban-Protection");
   public boolean field3;
   public final Class447 field4 = new Class447();
   public Class field5;

   @SubscribeEvent
   public void method0(Class269 var1) {
      if (var1.field0 instanceof EntityOtherPlayerMP && field1.contains(var1.field0)) {
         MinecraftForge.EVENT_BUS.post(new Class290((EntityOtherPlayerMP)var1.field0));
         field1.remove(var1.field0);
      }

   }

   @SubscribeEvent
   public void method0(Class438 var1) {
      if (var1.field0 instanceof EntityOtherPlayerMP && !field1.contains(var1.field0)) {
         field1.add((EntityOtherPlayerMP)var1.field0);
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      field1.clear();
   }

   @SubscribeEvent
   public void method0(Pre var1) {
      if (var1.type == ElementType.ALL) {
         GlStateManager.disableLighting();
      }

   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (!(var1.field0 instanceof S09PacketHeldItemChange) && var1.field0 instanceof S2FPacketSetSlot && Oringo.field9.currentScreen instanceof GuiRepair) {
         var1.method9();
      }

   }

   public static Framebuffer method0(Framebuffer var0) {
      return Class56.method0(var0, 0);
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C02PacketUseEntity && Oringo.field9.playerController.getIsHittingBlock()) {
         this.field2.info("z");
         var1.method9();
      } else {
         if (var1.field0 instanceof C08PacketPlayerBlockPlacement) {
            if (Oringo.field9.playerController.getIsHittingBlock()) {
               this.field2.info("zz");
               var1.method9();
               return;
            }

            C08PacketPlayerBlockPlacement var2 = (C08PacketPlayerBlockPlacement)var1.field0;
            if (var2.getStack() == null && var2.getPlacedBlockDirection() == 255) {
               PopupAnimationModule.method2("Error 2534. Please report this!");
               var1.method9();
               return;
            }

            if (var2.getStack() != null && (var2.getStack().getItem() instanceof ItemEnderPearl || !this.method0(var2))) {
               var1.setCanceled(true);
               Class302.method2(new C08PacketPlayerBlockPlacement(var2.getStack()));
            }
         }

      }
   }

   public boolean method0(C08PacketPlayerBlockPlacement var1) {
      return !((EntityPlayerSPAccessor)Oringo.field9.thePlayer).getServerSneakState() || var1.getPlacedBlockDirection() == 255 || var1.getStack() == null || !CreateNewkeybindModule.method0(var1.getStack(), "Ether Transmission", false) || Class496.field5 && TerminalAuraModule.field0(Class46.field3);
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (!this.field3 || this.field5 != null) {
         try {
            if (!this.field4.method0(1000L, true)) {
               return;
            }

            if (this.field5 == null) {
               try {
                  this.field5 = Class.forName("club.sk1er.patcher.config.PatcherConfig");
               } catch (ClassNotFoundException var3) {
                  this.field3 = true;
               }
            }

            if (this.field0 == null) {
               this.field0 = this.field5.getDeclaredField("hudCaching");
               this.field0.setAccessible(true);
            }

            this.field0.set((Object)null, false);
         } catch (Exception var4) {
         }

         this.field3 = false;
      }
   }
}
