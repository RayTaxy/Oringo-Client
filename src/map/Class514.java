package map;

import net.minecraft.block.BlockBarrier;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class194;
import oringo.event.Class217;
import oringo.module.Category;
import oringo.module.ClickGuiModule;
import oringo.module.NoBlockModule;
import oringo.module.PopupAnimationModule;
import oringo.module.SubCategory;
import oringo.notification.Notifications;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class Class514 extends Class408 {
   public boolean field0;
   public final EnumSetting field1 = new EnumSetting("Activate", "On press", new String[]{"Auto", "On press"});
   public final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Barrier clip", true)).method2("Phase through barier blocks");
   public int Y_;
   public boolean field4;
   public final BooleanSetting field5 = (BooleanSetting)(new BooleanSetting("Activate inside a block", true)).method2("Allows you to activate while inside a block");
   public final BooleanSetting field6 = (BooleanSetting)(new BooleanSetting("Clip down", true)).method2("Will clip you down the stair");

   public Class514() {
      super("Stair Phase", Category.dungeon, SubCategory.main, (String)null);
      this.method0((Setting[])(new Setting[]{this.field1, this.field5, this.field6, this.field2}));
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      if (field58.thePlayer != null) {
         if (!(var1.field2 instanceof BlockBarrier) || !this.field2.method1() || (var1.field1 == null || var1.field1.maxY <= field58.thePlayer.getEntityBoundingBox().minY) && !field58.gameSettings.keyBindSneak.isKeyDown()) {
            if (this.field0 && (var1.field1 != null && var1.field1.maxY > field58.thePlayer.getEntityBoundingBox().minY || field58.gameSettings.keyBindSneak.isKeyDown() || this.Y_ >= 0)) {
               var1.setCanceled(true);
            }

         } else {
            var1.setCanceled(true);
         }
      }
   }

   public static boolean method0(BlockPos var0) {
      return var0.getX() > 240 || var0.getX() < -240 || var0.getZ() > 240 || var0.getZ() < -240;
   }

   public static void method0(String var0, int var1, Notifications.Class1 var2) {
      var1 += 500;
      if (ClickGuiModule.field1.method1()) {
         PopupAnimationModule.method2(var0);
      } else {
         Notifications..add(new Notifications.Class0(var0, var1, var2));
      }
   }

   public static float method0(float var0, float var1) {
      float var2 = var1 - var0;
      return var2 > 180.0F ? 360.0F - var2 : (var2 < -180.0F ? var2 + 360.0F : var2);
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (field58.theWorld != null && field58.thePlayer != null) {
         if (this.field4 && !this.field0 && this.field1.method0(1) && var1.type == ElementType.HOTBAR) {
            ScaledResolution var2 = new ScaledResolution(field58);
            Class311.field12.method0("Phase usage detected", (float)var2.getScaledWidth() / 2.0F, (float)var2.getScaledHeight() - (float)var2.getScaledHeight() / 4.5F, Class362.field7.method17().getRGB());
         }

      }
   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      this.field4 = this.field5.method1() && NoBlockModule.method1() || field58.thePlayer.onGround && field58.thePlayer.isCollidedVertically && Class376.method0(field58.theWorld.getBlockState(new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ)).getBlock());
      --this.Y_;
      if ((this.method8() || this.field1.method0(0)) && !this.field0 && this.field4) {
         this.field0 = true;
         this.Y_ = 2;
      } else if (this.field0 && !NoBlockModule.method1()) {
         field58.thePlayer.setVelocity(0.0D, field58.thePlayer.motionY, 0.0D);
         this.field0 = false;
      }

   }

   public void b_() {
      this.field0 = false;
   }

   public static boolean method0(Entity var0) {
      if (var0 instanceof EntityZombie) {
         EntityZombie var1 = (EntityZombie)var0;
         if (var1.isChild() && var1.getCurrentArmor(0) != null && var1.getCurrentArmor(1) != null && var1.getCurrentArmor(2) != null && var1.getCurrentArmor(3) != null) {
            ItemStack var2 = var1.getCurrentArmor(3);
            return var2.getItem() instanceof ItemSkull && "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTE5YzEyNTQzYmM3NzkyNjA1ZWY2OGUxZjg3NDlhZThmMmEzODFkOTA4NWQ0ZDRiNzgwYmExMjgyZDM1OTdhMCJ9fX0K".equals(Class98.method0(var2));
         }
      }

      return false;
   }
}
