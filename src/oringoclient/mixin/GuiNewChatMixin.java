package oringo.mixin;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import map.Class163;
import map.Class203;
import map.Class253;
import map.Class311;
import map.Class362;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.module.AutoSoulcryModule;
import oringo.module.AutoWaterModule;
import oringo.module.FDSwapperModule;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.HideSummonsModule;
import oringo.module.KuudraHelperModule;
import oringo.module.NBTCopyModule;
import oringo.module.ReachModule;

@Mixin(
   value = {GuiNewChat.class},
   priority = 1001
)
public abstract class GuiNewChatMixin extends MapItemRenderer implements Class203 {
   @Shadow
   @Final
   private List drawnChatLines;
   @Shadow
   private int scrollPos;
   @Shadow
   @Final
   private Minecraft mc;
   @Shadow
   private boolean isScrolled;
   private int field4 = 0;

   private float method7() {
      return Class362.field48.cv_.method1() && Class362.field48.field4.method1() && Class362.field48.method44() ? Class311.field0.method3() : (float)this.mc.fontRendererObj.FONT_HEIGHT;
   }

   public ChatLine method0(int var1, int var2) {
      if (!this.getChatOpen()) {
         return null;
      } else {
         ScaledResolution var3 = new ScaledResolution(this.mc);
         int var4 = var3.getScaleFactor();
         float var5 = this.getChatScale();
         int var6 = var1 / var4 - 3;
         int var7 = var2 / var4 - 27 - 12;
         var6 = MathHelper.floor_float((float)var6 / var5);
         var7 = MathHelper.floor_float((float)var7 / var5);
         if (var6 >= 0 && var7 >= 0) {
            int var8 = Math.min(this.getLineCount(), this.drawnChatLines.size());
            if (var6 <= MathHelper.floor_float((float)this.getChatWidth() / this.getChatScale()) && (float)var7 < this.method7() * (float)var8 + (float)var8) {
               int var9 = (int)((float)var7 / this.method7() + (float)this.scrollPos);
               return var9 >= 0 && var9 < this.drawnChatLines.size() ? (ChatLine)this.drawnChatLines.get(var9) : null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   @Shadow
   public abstract boolean getChatOpen();

   @Redirect(
      method = {"drawChat"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"
)
   )
   public int redir(FontRenderer var1, String var2, float var3, float var4, int var5) {
      return var1.drawStringWithShadow(var2, var3, var4, this.field4 == -1 ? var5 : this.field4 + (var5 - 16777215));
   }

   private List wrapToLen(IChatComponent var1, int var2, FontRenderer var3) {
      int var4 = 0;
      ChatComponentText var5 = new ChatComponentText("");
      ArrayList var6 = Lists.newArrayList();
      ArrayList var7 = Lists.newArrayList(var1);

      for(int var8 = 0; var8 < var7.size(); ++var8) {
         IChatComponent var9 = (IChatComponent)var7.get(var8);
         String var10 = var9.getUnformattedTextForChat();
         boolean var11 = false;
         String var13;
         if (var10.indexOf(10) != -1) {
            int var12 = var10.indexOf(10);
            var13 = var10.substring(var12 + 1);
            var10 = var10.substring(0, var12 + 1);
            ChatComponentText var14 = new ChatComponentText(var13);
            var14.setChatStyle(var9.getChatStyle().createShallowCopy());
            var7.add(var8 + 1, var14);
            var11 = true;
         }

         String var21 = GuiUtilRenderComponents.func_178909_a(var9.getChatStyle().getFormattingCode() + var10, false);
         var13 = var21.endsWith("\n") ? var21.substring(0, var21.length() - 1) : var21;
         double var22 = (double)Class311.field0.method0(var13);
         ChatComponentText var16 = new ChatComponentText(var13);
         var16.setChatStyle(var9.getChatStyle().createShallowCopy());
         if ((double)var4 + var22 > (double)var2) {
            String var17 = Class311.field0.method0(var21, (float)(var2 - var4), false);
            String var18 = var17.length() < var21.length() ? var21.substring(var17.length()) : null;
            if (var18 != null && var18.length() > 0) {
               int var19 = var17.lastIndexOf(" ");
               if (var19 >= 0 && Class311.field0.method0(var21.substring(0, var19)) > 0.0F) {
                  var17 = var21.substring(0, var19);
                  var18 = var21.substring(var19);
               } else if (var4 > 0 && !var21.contains(" ")) {
                  var17 = "";
                  var18 = var21;
               }

               var18 = FontRenderer.getFormatFromString(var17) + var18;
               ChatComponentText var20 = new ChatComponentText(var18);
               var20.setChatStyle(var9.getChatStyle().createShallowCopy());
               var7.add(var8 + 1, var20);
            }

            var22 = (double)Class311.field0.method0(var17);
            var16 = new ChatComponentText(var17);
            var16.setChatStyle(var9.getChatStyle().createShallowCopy());
            var11 = true;
         }

         if ((double)var4 + var22 <= (double)var2) {
            var4 = (int)((double)var4 + var22);
            var5.appendSibling(var16);
         } else {
            var11 = true;
         }

         if (var11) {
            var6.add(var5);
            var4 = 0;
            var5 = new ChatComponentText("");
         }
      }

      var6.add(var5);
      return var6;
   }

   @Redirect(
      method = {"drawChat"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/IChatComponent;getFormattedText()Ljava/lang/String;"
)
   )
   public String redir(IChatComponent var1) {
      this.field4 = -1;
      if (var1 instanceof Class253.Class0) {
         this.field4 = ((Class253.Class0)var1).ca_;
      }

      return var1.getFormattedText();
   }

   @Shadow
   public abstract float getChatScale();

   @Shadow
   public abstract int getLineCount();

   private static Class253.Class0 lambda$onFunc$0(IChatComponent var0, IChatComponent var1) {
      return new Class253.Class0(var1, ((Class253.Class0)var0).ca_);
   }

   @Shadow
   public abstract int getChatWidth();

   @Redirect(
      method = {"setChatLine"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/gui/GuiUtilRenderComponents;splitText(Lnet/minecraft/util/IChatComponent;ILnet/minecraft/client/gui/FontRenderer;ZZ)Ljava/util/List;"
)
   )
   private List onFunc(IChatComponent var1, int var2, FontRenderer var3, boolean var4, boolean var5) {
      List var6 = Class362.field48.cv_.method1() && Class362.field48.method44() && Class362.field48.field4.method1() ? this.wrapToLen(var1, var2, var3) : GuiUtilRenderComponents.splitText(var1, var2, var3, var4, var5);
      if (var1 instanceof Class253.Class0) {
         var6 = (List)var6.stream().map(GuiNewChatMixin::lambda$onFunc$0).collect(Collectors.toList());
      }

      return var6;
   }

   @Overwrite
   public IChatComponent getChatComponent(int var1, int var2) {
      if (!this.getChatOpen()) {
         return null;
      } else {
         ScaledResolution var3 = new ScaledResolution(this.mc);
         int var4 = var3.getScaleFactor();
         float var5 = this.getChatScale();
         int var6 = var1 / var4 - 3;
         int var7 = var2 / var4 - 27 - 12;
         var6 = MathHelper.floor_float((float)var6 / var5);
         var7 = MathHelper.floor_float((float)var7 / var5);
         if (var6 >= 0 && var7 >= 0) {
            int var8 = Math.min(this.getLineCount(), this.drawnChatLines.size());
            if (var6 <= MathHelper.floor_float((float)this.getChatWidth() / this.getChatScale()) && (float)var7 < this.method7() * (float)var8 + (float)var8) {
               int var9 = (int)((float)var7 / this.method7() + (float)this.scrollPos);
               if (var9 >= 0 && var9 < this.drawnChatLines.size()) {
                  ChatLine var10 = (ChatLine)this.drawnChatLines.get(var9);
                  int var11 = 0;
                  Iterator var12 = var10.getChatComponent().iterator();

                  IChatComponent var13;
                  do {
                     do {
                        if (!var12.hasNext()) {
                           return null;
                        }

                        var13 = (IChatComponent)var12.next();
                     } while(!(var13 instanceof ChatComponentText));

                     var11 = (int)((float)var11 + (Class362.field48.cv_.method1() && Class362.field48.method44() && Class362.field48.field4.method1() ? Class311.field0.method0(GuiUtilRenderComponents.func_178909_a(((ChatComponentText)var13).getChatComponentText_TextValue(), false)) : (float)this.mc.fontRendererObj.getStringWidth(GuiUtilRenderComponents.func_178909_a(((ChatComponentText)var13).getChatComponentText_TextValue(), false))));
                  } while(var11 <= var6);

                  return var13;
               } else {
                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   @Inject(
      method = {"drawChat"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void method0(int var1, CallbackInfo var2) {
      if (Class362.field48.field4.method1() && Class362.field48.method44()) {
         if (this.mc.gameSettings.chatVisibility != EnumChatVisibility.HIDDEN) {
            ScaledResolution var3 = new ScaledResolution(this.mc);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GlStateManager.translate(0.0F, (float)(var3.getScaledHeight() - 60), 0.0F);
            int var4 = this.getLineCount();
            boolean var5 = false;
            int var6 = 0;
            int var7 = this.drawnChatLines.size();
            boolean var8 = Class362.field48.cv_.method1();
            float var9 = var8 ? Class311.field0.method3() : (float)this.mc.fontRendererObj.FONT_HEIGHT;
            if (var7 > 0) {
               if (this.getChatOpen()) {
                  var5 = true;
               }

               float var10 = this.getChatScale();
               GlStateManager.pushMatrix();
               GlStateManager.translate(2.0F, 20.0F, 0.0F);
               GlStateManager.scale(var10, var10, 1.0F);
               int var11 = MathHelper.ceiling_float_int((float)this.getChatWidth() / var10);
               float var12 = 0.0F;
               float var13 = 0.0F;
               boolean var14 = false;

               int var15;
               ChatLine var16;
               float var17;
               for(var15 = 0; var15 + this.scrollPos < this.drawnChatLines.size() && var15 < var4; ++var15) {
                  var16 = (ChatLine)this.drawnChatLines.get(var15 + this.scrollPos);
                  if (var16 != null && (var1 - var16.getUpdatedCounter() < 200 || var5)) {
                     var14 = true;
                     if (!var5 && var1 - var16.getUpdatedCounter() > 195) {
                        var17 = 1.0F - ((float)(var1 - var16.getUpdatedCounter()) + FrozenTreasureESPModule.method5().renderPartialTicks - 195.0F) / 5.0F;
                        var17 = (float)Math.sin((double)Class163.method0(var17, 0.0F, 1.0F) * 3.141592653589793D / 2.0D);
                        var13 -= var9 * var17 * var17;
                     } else {
                        var13 -= var9;
                     }
                  }
               }

               float var22;
               if (var14) {
                  if (Class362.field48.field5.method1()) {
                     for(var22 = 0.5F; var22 < 3.0F; var22 += 0.5F) {
                        ReachModule.method0((double)(var12 + var22 - 2.0F), (double)(var13 + var22), (double)(var12 + (float)var11 + 4.0F + var22), (double)(1.0F + var22), 10.0D, (new Color(20, 20, 20, 10)).getRGB());
                     }
                  }

                  if (!Class362.field48.field3.method1()) {
                     AutoSoulcryModule.method3();
                     FDSwapperModule.method5();
                     ReachModule.method0((double)(var12 - 2.0F), (double)var13, (double)(var12 + (float)var11 + 4.0F), 1.0D, 10.0D, Color.white.getRGB());
                     GL11.glPopMatrix();
                     GL11.glPopMatrix();
                     KuudraHelperModule.method0(1);
                     if (Class362.field48.field0.method1()) {
                        if (this.mc.currentScreen != null && this.mc.currentScreen instanceof GuiContainer && Class362.field48.field2.method1()) {
                           GlStateManager.color(0.25F, 0.25F, 0.25F);
                        }

                        AutoWaterModule.method8();
                     }

                     GL11.glPushMatrix();
                     GlStateManager.translate(0.0F, (float)(var3.getScaledHeight() - 60), 0.0F);
                     GL11.glPushMatrix();
                     GlStateManager.translate(2.0F, 20.0F, 0.0F);
                     GlStateManager.scale(var10, var10, 1.0F);
                     NBTCopyModule.method0(var12 - 2.0F, var13, var12 + (float)var11 + 6.0F, -var13 + 1.0F, 5.0F, new Color(50, 50, 50, 30));
                  }
               }

               if (var8) {
                  Class311.field0.method5();
               }

               GlStateManager.enableBlend();

               for(var15 = 0; var15 + this.scrollPos < this.drawnChatLines.size() && var15 < var4; ++var15) {
                  var16 = (ChatLine)this.drawnChatLines.get(var15 + this.scrollPos);
                  if (var16 != null) {
                     int var24 = var1 - var16.getUpdatedCounter();
                     if (var24 < 200 || var5) {
                        ++var6;
                        byte var18 = 0;
                        float var19 = (float)(-var15) * var9;
                        String var20 = var16.getChatComponent().getFormattedText();
                        GlStateManager.enableBlend();
                        boolean var21 = var16.getChatComponent() instanceof Class253.Class0;
                        if (var8) {
                           Class311.field0.method3(var20, (float)var18, (float)((double)var19 - ((double)var9 - 2.3D)), var21 ? ((Class253.Class0)var16.getChatComponent()).ca_ : Color.white.getRGB());
                        } else {
                           this.mc.fontRendererObj.drawStringWithShadow(var20, (float)var18, var19 - (var9 - 1.0F), var21 ? ((Class253.Class0)var16.getChatComponent()).ca_ : 16777215);
                        }
                     }
                  }
               }

               if (var8) {
                  Class311.field0.method6();
               }

               GlStateManager.disableAlpha();
               GlStateManager.disableBlend();
               if (var14) {
                  HideSummonsModule.method3();
               }

               if (var5) {
                  GlStateManager.translate(-3.0F, 0.0F, 0.0F);
                  var9 = (float)this.mc.fontRendererObj.FONT_HEIGHT;
                  var22 = (float)var7 * var9 + (float)var7;
                  float var23 = (float)var6 * var9 + (float)var6;
                  var17 = (float)this.scrollPos * var23 / (float)var7;
                  float var25 = var23 * var23 / var22;
                  if (var22 != var23) {
                     int var26 = var17 > 0.0F ? 170 : 96;
                     int var27 = this.isScrolled ? 13382451 : 3355562;
                     drawRect(0, (int)(-var17), 2, (int)(-var17 - var25), var27 + (var26 << 24));
                     drawRect(2, (int)(-var17), 1, (int)(-var17 - var25), 13421772 + (var26 << 24));
                  }
               }

               GlStateManager.popMatrix();
            }
         }

         var2.cancel();
      }

   }
}
