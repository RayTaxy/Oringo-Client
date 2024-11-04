package oringo.module;

import map.Class46;
import map.Class479;
import map.Class95;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AutoRabbitModule extends Module {
   public int P_;
   public boolean field0;
   public int field9;
   public boolean field3;
   public final BooleanSetting field4 = (BooleanSetting)(new BooleanSetting("Spring Boots", false)).method2("Should spring boots also be equipped");

   public static boolean lambda$onMotion$0(ItemStack var0) {
      return "SPRING_BOOTS".equals(BlockHitModule.method0(var0));
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field0 = false;
   }

   public AutoRabbitModule() {
      super("Auto Rabbit", Category.dungeon, SubCategory.main, "Auto equip rabbit hat in trap");
      this.method0((Setting[])(new Setting[]{this.field4}));
   }

   public static char method1() {
      return ClickGuiModule.field25.method1().isEmpty() ? '.' : ClickGuiModule.field25.method1().toLowerCase().charAt(0);
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, float var5, int var6, int var7, int var8, int var9) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      double var10 = (double)(var0 + var2);
      double var12 = (double)(var1 + var3);
      float var14 = (float)(var6 >> 24 & 255) / 255.0F;
      float var15 = (float)(var6 >> 16 & 255) / 255.0F;
      float var16 = (float)(var6 >> 8 & 255) / 255.0F;
      float var17 = (float)(var6 & 255) / 255.0F;
      float var18 = (float)(var7 >> 24 & 255) / 255.0F;
      float var19 = (float)(var7 >> 16 & 255) / 255.0F;
      float var20 = (float)(var7 >> 8 & 255) / 255.0F;
      float var21 = (float)(var7 & 255) / 255.0F;
      float var22 = (float)(var8 >> 24 & 255) / 255.0F;
      float var23 = (float)(var8 >> 16 & 255) / 255.0F;
      float var24 = (float)(var8 >> 8 & 255) / 255.0F;
      float var25 = (float)(var8 & 255) / 255.0F;
      float var26 = (float)(var9 >> 24 & 255) / 255.0F;
      float var27 = (float)(var9 >> 16 & 255) / 255.0F;
      float var28 = (float)(var9 >> 8 & 255) / 255.0F;
      float var29 = (float)(var9 & 255) / 255.0F;
      GL11.glPushAttrib(0);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      var0 *= 2.0F;
      var1 *= 2.0F;
      var10 *= 2.0D;
      var12 *= 2.0D;
      GL11.glLineWidth(var5);
      GlStateManager.disableTexture2D();
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      Class479.field3.begin(2, DefaultVertexFormats.POSITION_COLOR);
      byte var30 = 15;

      int var31;
      for(var31 = 0; var31 <= 90; var31 += var30) {
         Class479.field3.pos((double)(var0 + var4) + Math.sin((double)var31 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), (double)(var1 + var4) + Math.cos((double)var31 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), 0.0D).color(var15, var16, var17, var14).endVertex();
      }

      for(var31 = 90; var31 <= 180; var31 += var30) {
         Class479.field3.pos((double)(var0 + var4) + Math.sin((double)var31 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), var12 - (double)var4 + Math.cos((double)var31 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), 0.0D).color(var19, var20, var21, var18).endVertex();
      }

      for(var31 = 0; var31 <= 90; var31 += var30) {
         Class479.field3.pos(var10 - (double)var4 + Math.sin((double)var31 * 3.141592653589793D / 180.0D) * (double)var4, var12 - (double)var4 + Math.cos((double)var31 * 3.141592653589793D / 180.0D) * (double)var4, 0.0D).color(var23, var24, var25, var22).endVertex();
      }

      for(var31 = 90; var31 <= 180; var31 += var30) {
         Class479.field3.pos(var10 - (double)var4 + Math.sin((double)var31 * 3.141592653589793D / 180.0D) * (double)var4, (double)(var1 + var4) + Math.cos((double)var31 * 3.141592653589793D / 180.0D) * (double)var4, 0.0D).color(var27, var28, var29, var26).endVertex();
      }

      Class479.field1.draw();
      GL11.glDisable(2848);
      GlStateManager.enableTexture2D();
      GL11.glShadeModel(7424);
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glPopAttrib();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (RenderChunkBoundsModule.method5()) {
         if (TerminalAuraModule.field0(Class46.field1)) {
            int var2;
            ItemStack var3;
            if (this.field4.method1()) {
               var2 = Class95.method0(AutoRabbitModule::lambda$onMotion$0);
               if (var2 != -1 && !this.field3) {
                  var3 = field58.thePlayer.inventoryContainer.getSlot(5).getStack();
                  if (!AutoAlignModule.method0(var3)) {
                     ServerRotationsModule.method0(var2, 0);
                     ServerRotationsModule.method0(9, 0);
                     ServerRotationsModule.method0(var2, 0);
                     this.P_ = var2;
                     this.field3 = true;
                  }
               }
            }

            var2 = Class95.method0(AutoRabbitModule::lambda$onMotion$1);
            if (var2 != -1 && !this.field0) {
               var3 = field58.thePlayer.inventoryContainer.getSlot(5).getStack();
               if (!AutoAlignModule.method0(var3)) {
                  ServerRotationsModule.method0(var2, 0);
                  ServerRotationsModule.method0(5, 0);
                  ServerRotationsModule.method0(var2, 0);
                  this.field9 = var2;
                  this.field0 = true;
               }
            }
         } else {
            ItemStack var4;
            if (this.field0) {
               this.field0 = false;
               var4 = field58.thePlayer.inventoryContainer.getSlot(5).getStack();
               if (AutoAlignModule.method0(var4)) {
                  ServerRotationsModule.method0(this.field9, 0);
                  ServerRotationsModule.method0(5, 0);
                  ServerRotationsModule.method0(this.field9, 0);
               }
            }

            if (this.field3) {
               this.field3 = false;
               var4 = field58.thePlayer.inventoryContainer.getSlot(5).getStack();
               if (AutoAlignModule.method0(var4)) {
                  ServerRotationsModule.method0(this.P_, 0);
                  ServerRotationsModule.method0(9, 0);
                  ServerRotationsModule.method0(this.P_, 0);
               }
            }
         }

      }
   }

   public static boolean lambda$onMotion$1(ItemStack var0) {
      return "RABBIT_HAT".equals(BlockHitModule.method0(var0));
   }
}
