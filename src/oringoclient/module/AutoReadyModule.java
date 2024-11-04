package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.Iterator;
import map.Class24;
import map.Class447;
import map.Class496;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.Command;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoReadyModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Kuudra", true);
   public final Class447 ab_ = new Class447();
   public final BooleanSetting field2 = new BooleanSetting("Dungeon", true);
   public boolean field9;
   public final BooleanSetting field4 = new BooleanSetting("Open Mort", true, this::lambda$new$0);
   public final DoubleSetting field5 = new DoubleSetting("Mort distance", 5.0D, 1.0D, 6.0D, 0.1D, this::lambda$new$1);

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!field58.playerController.getIsHittingBlock()) {
         ItemStack var4;
         ItemStack var5;
         String var6;
         int var7;
         if (this.field2.method1() && Class496.field5) {
            if (!this.field9 && this.ab_.a_(500L) && this.field4.method1() && RenderChunkBoundsModule.method5()) {
               Iterator var2 = field58.theWorld.loadedEntityList.iterator();

               while(var2.hasNext()) {
                  Entity var3 = (Entity)var2.next();
                  if (var3 instanceof EntityArmorStand && var3.getName().equals("§bMort") && (double)field58.thePlayer.getDistanceToEntity(var3) < this.field5.method0()) {
                     if (!field58.playerController.isPlayerRightClickingOnEntity(field58.thePlayer, var3, new MovingObjectPosition(var3))) {
                        field58.playerController.interactWithEntitySendPacket(field58.thePlayer, var3);
                     }

                     this.ab_.o_();
                     break;
                  }
               }
            }

            if (field58.thePlayer.openContainer instanceof ContainerChest) {
               var6 = ChatFormatting.stripFormatting(((ContainerChest)field58.thePlayer.openContainer).getLowerChestInventory().getName());
               if (var6.startsWith("Start Dungeon?")) {
                  field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 13, 0, 0, field58.thePlayer);
               } else if (var6.startsWith("Catacombs - ")) {
                  this.field9 = true;

                  for(var7 = 2; var7 < 7; ++var7) {
                     var4 = field58.thePlayer.openContainer.getSlot(var7).getStack();
                     if (var4 != null && var4.getItem() instanceof ItemSkull && var4.getDisplayName().contains(field58.thePlayer.getName())) {
                        var5 = field58.thePlayer.openContainer.getSlot(var7 + 9).getStack();
                        if (var5 != null && EnumDyeColor.byMetadata(var5.getItemDamage()) == EnumDyeColor.RED) {
                           field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, var7 + 9, 0, 0, field58.thePlayer);
                           break;
                        }
                     }
                  }
               }
            }
         }

         if (this.field0.method1() && field58.thePlayer.openContainer instanceof ContainerChest) {
            var6 = ChatFormatting.stripFormatting(((ContainerChest)field58.thePlayer.openContainer).getLowerChestInventory().getName());
            if (var6.startsWith("Ready Up")) {
               for(var7 = 19; var7 < 27; ++var7) {
                  var4 = field58.thePlayer.openContainer.getSlot(var7).getStack();
                  if (var4 != null && var4.getItem() instanceof ItemSkull && var4.getDisplayName().contains(field58.thePlayer.getName())) {
                     var5 = field58.thePlayer.openContainer.getSlot(var7 - 9).getStack();
                     if (var5 != null && EnumDyeColor.byMetadata(var5.getItemDamage()) == EnumDyeColor.RED) {
                        field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, var7 - 9, 0, 0, field58.thePlayer);
                        break;
                     }
                  }
               }
            }
         }

      }
   }

   public static boolean c_(String var0) {
      var0 = var0.trim();
      boolean var1 = AutoRabbitModule.method1() == '/';
      if (var0.length() > 0 && var0.charAt(0) == AutoRabbitModule.method1()) {
         var0 = var0.substring(1);
         String var2 = var0.split(" ")[0];
         String[] var3 = new String[0];
         String[] var4 = new String[0];
         if (var0.indexOf(32) != -1) {
            var3 = var0.replaceFirst(var2, "").replaceFirst(" ", "").split(" ");
            var4 = var0.replaceFirst(var2, "").replaceFirst(" ", "").toLowerCase().split(" ");
         }

         if (Class24.field0.containsKey(var2.toLowerCase())) {
            Command var5 = (Command)Class24.field0.get(var2.toLowerCase());

            try {
               var5.method0(var5.a_() ? var3 : var4);
            } catch (Exception var7) {
               PopupAnimationModule.method2(String.format("Error executing command %s", var5.method5()[0]));
               if (Oringo.cV_) {
                  var7.printStackTrace();
               }
            }

            return true;
         } else {
            if (!var1) {
               PopupAnimationModule.method2(String.format("§cInvalid command \"%shelp\" for §cmore info", AutoRabbitModule.method1()));
            }

            return !var1;
         }
      } else {
         return false;
      }
   }

   public AutoReadyModule() {
      super("Auto Ready", Category.dungeon, SubCategory.main);
      this.method0((Setting[])(new Setting[]{this.field2, this.field4, this.field5, this.field0}));
   }

   public Boolean lambda$new$0() {
      return !this.field2.method1();
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field9 = false;
   }

   public Boolean lambda$new$1() {
      return !this.field2.method1() || !this.field4.method1();
   }

   public static void method0(Vec3 var0, Color var1) {
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableBlend();
      GL11.glLineWidth(2.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.disableDepth();
      AutoBlazeModule.method0(var1);
      GlStateManager.disableLighting();
      RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(var0.xCoord - 0.05D - Oringo.field9.getRenderManager().viewerPosX, var0.yCoord - 0.05D - Oringo.field9.getRenderManager().viewerPosY, var0.zCoord - 0.05D - Oringo.field9.getRenderManager().viewerPosZ, var0.xCoord + 0.05D - Oringo.field9.getRenderManager().viewerPosX, var0.yCoord + 0.05D - Oringo.field9.getRenderManager().viewerPosY, var0.zCoord + 0.05D - Oringo.field9.getRenderManager().viewerPosZ));
      AutoCloseModule.method5();
      GlStateManager.enableTexture2D();
      GlStateManager.enableDepth();
      GlStateManager.disableBlend();
   }
}
