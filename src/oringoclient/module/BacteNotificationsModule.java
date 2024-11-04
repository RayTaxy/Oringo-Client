package oringo.module;

import java.awt.Toolkit;
import map.Class34;
import map.Class354;
import map.Class362;
import map.Class496;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.input.Keyboard;
import oringo.event.Class16;
import oringo.event.Class189;

public class BacteNotificationsModule extends Module {
   public Class34[] field0 = new Class34[0];
   public int aE_ = 0;
   public boolean field2 = false;
   public static PacketLoggerModule field3 = null;
   public final Class354 field4 = new Class354("ScoreboardText.frag", "Vertex.vert");

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field21) {
         if (var1.cB_.equals(new BlockPos(-123, 67, -31))) {
            if (var1.field0.getBlock() != Blocks.slime_block) {
               if (var1.field2.getBlock() == Blocks.slime_block) {
                  method2("Bacte blaster phase!");
                  Toolkit.getDefaultToolkit().beep();
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.type == ElementType.TEXT) {
         if (Keyboard.isKeyDown(11)) {
            this.method2();
            ScaledResolution var2 = new ScaledResolution(field58);
            int var3 = var2.getScaledWidth() / 2;
            int var4 = var2.getScaledHeight() / 2;
            this.field4.method5();
            this.field4.method0("middle", (float)var3, (float)var3);
            this.field4.method0("range", (float)var3, (float)var3);
            Class362.field7.field0(this.field4);
            AutoCloseModule.method5();
            BossBarModule.method0(0.0F, 0.0F, (float)var3, (float)var4);
            BossBarModule.method0((float)var3, 0.0F, (float)var3, (float)var4);
            BossBarModule.method0(0.0F, (float)var4, (float)var3, (float)var4);
            BossBarModule.method0((float)var3, (float)var4, (float)var3, (float)var4);
            this.field4.method2();
         }
      }
   }

   public void method2() {
   }

   public static double h_(String var0) {
      double var1 = 100000.0D;
      var0 = var0.replaceAll(":", ".");

      try {
         var1 = Double.parseDouble(var0);
      } catch (NumberFormatException var4) {
         DojoHelperModule.method0((String)var4.getMessage());
      }

      return var1;
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase != Phase.START) {
         if (!this.field2 && Keyboard.isKeyDown(43)) {
            field3.method40();
            SecretHitboxesModule.method0("Packet logger " + (field3.method44() ? "enabled" : "disabled"), 1000);
         }

         this.field2 = Keyboard.isKeyDown(43);
      }
   }

   public BacteNotificationsModule() {
      super("Bacte Notifications", Category.other, SubCategory.other);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S2FPacketSetSlot) {
         S2FPacketSetSlot var2 = (S2FPacketSetSlot)var1.field0;
         int var3 = var2.func_149173_d();
         int var4 = var2.func_149175_c();
         ItemStack var5 = field58.thePlayer.getHeldItem();
         if (var4 != 0 || var3 != field58.thePlayer.inventory.currentItem + 36) {
            return;
         }

         int var6 = CriticalsModule.method0(var5);
         int var7 = CriticalsModule.method0(var2.func_149174_e());
         if (var6 == -1 || var7 == -1) {
            return;
         }

         int var8 = var7 - var6;
         if (var8 == 0) {
            return;
         }
      }

   }
}
