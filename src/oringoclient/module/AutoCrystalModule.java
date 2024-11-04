package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import map.Class198;
import map.Class350;
import map.Class361;
import map.Class479;
import map.Class496;
import map.Class514;
import map.Class94;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.ReplyCommand;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class525;
import oringo.notification.Notifications;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class AutoCrystalModule extends Module {
   public static final EnumSetting field0 = (EnumSetting)(new EnumSetting("Location", "Left", new String[]{"Left", "Right", "Conveyor"})).method2("The target location");
   public boolean field1;
   public boolean db_;

   @SubscribeEvent
   public void method0(Class525 var1) {
      if (this.db_) {
         var1.field0 = true;
         field58.thePlayer.motionY = 0.0D;
      }
   }

   public static boolean method0(ItemStack var0, int var1) {
      if (!(var0.getItem() instanceof ItemArmor)) {
         return false;
      } else {
         for(int var2 = 5; var2 < 45; ++var2) {
            if (InventoryManagerModule.field58.thePlayer.inventoryContainer.getSlot(var2).getHasStack()) {
               ItemStack var3 = InventoryManagerModule.field58.thePlayer.inventoryContainer.getSlot(var2).getStack();
               if (var3.getItem() instanceof ItemArmor && (Class198.method0(var3) > Class198.method0(var0) && var1 < 9 || var1 >= 9 && Class198.method0(var3) >= Class198.method0(var0) && var1 != var2) && ((ItemArmor)var3.getItem()).armorType == ((ItemArmor)var0.getItem()).armorType) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static void method0(Entity var0, String var1, double var2, double var4, double var6, int var8) {
      double var9 = var0.getDistanceSqToEntity(Oringo.field9.getRenderManager().livingPlayer);
      if (var9 <= (double)(var8 * var8)) {
         FontRenderer var11 = Oringo.field9.getRenderManager().getFontRenderer();
         float var12 = 1.6F;
         float var13 = 0.016666668F * var12;
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)var2, (float)var4 + var0.height + 0.5F, (float)var6);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-Oringo.field9.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(Oringo.field9.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
         GlStateManager.scale(-var13, -var13, var13);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         byte var14 = 0;
         int var15 = var11.getStringWidth(var1) / 2;
         GlStateManager.disableTexture2D();
         if (var8 != 0) {
            Class479.field3.begin(7, DefaultVertexFormats.POSITION_COLOR);
            Class479.field3.pos((double)(-var15 - 1), (double)(-1 + var14), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            Class479.field3.pos((double)(-var15 - 1), (double)(8 + var14), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            Class479.field3.pos((double)(var15 + 1), (double)(8 + var14), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            Class479.field3.pos((double)(var15 + 1), (double)(-1 + var14), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            Class479.field1.draw();
         }

         GlStateManager.enableTexture2D();
         var11.drawString(var1, -var11.getStringWidth(var1) / 2, var14, 553648127);
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         var11.drawString(var1, -var11.getStringWidth(var1) / 2, var14, -1);
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }

   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void field0(Class189 var1) {
      if (Class496.field5 && !this.field1) {
         if (var1.field0 instanceof S18PacketEntityTeleport) {
            S18PacketEntityTeleport var2 = (S18PacketEntityTeleport)var1.field0;
            if (var2.getX() / 32 == 73 && var2.getY() / 32 == 221 && var2.getZ() / 32 == 14) {
               this.db_ = true;
               this.field1 = true;
               field58.thePlayer.motionY = 0.0D;
            }
         }

      }
   }

   public static boolean lambda$null$0(String var0) {
      return ChatFormatting.stripFormatting(var0).contains("Ether Transmission");
   }

   public AutoCrystalModule() {
      super("Auto Crystal", Category.dungeon, SubCategory.floor7, "Picks up the f7 crystal. Requires etherwarp.");
      this.method0((Setting[])(new Setting[]{field0}));
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.db_) {
         this.db_ = false;
         Vec3 var2 = field0.method0(1) ? new Vec3(66.5D, 237.75D, 49.5D) : (field0.method0(0) ? new Vec3(80.5D, 237.5D, 49.5D) : new Vec3(73.5D, 225.0D, 70.5D));
         int var3 = TrailModule.method0(AutoCrystalModule::lambda$onUpdate$1);
         if (var3 != -1) {
            AutoJoeyModule.method0(var3);
            method3(new C08PacketPlayerBlockPlacement(field58.thePlayer.inventory.getStackInSlot(var3)));
            if (Class361.method0((Class94)(new Class350(var3)))) {
               var1.method0(ReplyCommand.method0(var2));
               var1.method2(true);
            }
         } else {
            Class514.method0("Unable to find an item with etherwarp!", 5000, Notifications.Class1.field0);
         }
      }
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void method0(Class270 var1) {
      this.field1 = false;
      this.db_ = false;
   }

   public static boolean lambda$onUpdate$1(ItemStack var0) {
      return var0.getTooltip(field58.thePlayer, false).stream().anyMatch(AutoCrystalModule::lambda$null$0);
   }
}
