package map;

import com.google.common.collect.Lists;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import oringo.command.ArmorStandsCommand;
import oringo.command.BlockCommand;

public class Class61 implements Class195 {
   public static final int field0 = 5;
   public static final int[] S_ = new int[32];
   public int T_;
   public final boolean U_;
   public static final WorldRenderer field4;
   public Font field2;
   public final Class317 field6;
   public DynamicTexture field1;
   public int field8;
   public final Class61.Class0[] field9;
   public float field10;
   public final boolean field11;
   public int field7;
   public static final char field13 = '§';
   public static final Minecraft field14 = Minecraft.getMinecraft();
   public final int field15;
   public int field16;
   public boolean field17;
   public final boolean field18;
   public static final float field19 = 1.0F;
   public int field20;
   public final float field21;
   public static final Tessellator field22 = Tessellator.getInstance();
   public int cX_;

   public static void method0(AtomicInteger var0) {
      if (Class170.field0.size() == 200) {
         Class176.method0((AtomicInteger)Class170.field0.get(0));
      }

      Class170.field0.add(var0);
   }

   public void a_(int var1) {
      if (!this.field18) {
         var1 = 1;
      }

      if (this.field7 != var1) {
         this.field8 = this.field15 * var1;
         this.field7 = var1;
         this.field2 = this.field2.deriveFont(0, this.field10 * (float)var1);
         if (this.field6 != null) {
            this.field6.a_(var1);
         }

         this.method4();
      }

   }

   public List method0(String var1, float var2) {
      if (var1 != null && !var1.isEmpty()) {
         var1 = var1.trim();
         float var3 = this.method0(var1);
         if (var3 <= var2) {
            return Lists.newArrayList(new String[]{var1});
         } else {
            var3 = 0.0F;
            ArrayList var4 = Lists.newArrayList();
            String[] var5 = var1.split(" ");
            StringBuilder var6 = new StringBuilder();
            String[] var7 = var5;
            int var8 = var5.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               String var10 = var7[var9];
               float var11 = this.method0(var10 + " ");
               if (var6.length() == 0) {
                  var6.append(var10);
                  var3 += var11;
               } else if (var3 + var11 < var2) {
                  var3 += var11;
                  var6.append(" ").append(var10);
               } else {
                  var4.add(var6.toString());
                  var6 = new StringBuilder();
                  var6.append(var10);
                  var3 = var11;
               }
            }

            if (var6.length() != 0) {
               var4.add(var6.toString());
            }

            return var4;
         }
      } else {
         return Lists.newArrayList();
      }
   }

   public float method0(String var1, float var2, float var3, int var4, boolean var5) {
      if (var1 != null && !var1.isEmpty()) {
         if (Class362.field32.method44()) {
            var1 = ArmorStandsCommand.q_(var1);
         }

         if (!this.field17) {
            ScaledResolution var6 = new ScaledResolution(field14);
            this.a_(var6.getScaleFactor());
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F / (float)this.field7, 1.0F / (float)this.field7, 1.0F);
         }

         var2 *= (float)this.field7;
         var3 *= (float)this.field7;
         var3 -= (float)this.field20;
         if (!this.field17) {
            GlStateManager.disableAlpha();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            field4.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         }

         boolean var14 = false;
         boolean var7 = false;
         boolean var8 = false;
         boolean var9 = false;
         int var10 = var4;

         for(int var11 = 0; var11 < var1.length(); ++var11) {
            char var12 = var1.charAt(var11);
            if (var12 == 167) {
               ++var11;
               if (var11 >= var1.length()) {
                  break;
               }

               int var13 = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(var1.charAt(var11)));
               if (var13 != -1) {
                  if (var13 < 16) {
                     var8 = false;
                     var9 = false;
                     var14 = false;
                     var7 = false;
                     if (!var5) {
                        var10 = S_[var13];
                     }
                  } else if (var13 != 16) {
                     if (var13 == 17) {
                        var8 = true;
                     } else if (var13 == 18) {
                        var7 = true;
                     } else if (var13 == 19) {
                        var14 = true;
                     } else if (var13 == 20) {
                        var9 = true;
                     } else {
                        var14 = false;
                        var7 = false;
                        var9 = false;
                        var8 = false;
                        var10 = var4;
                     }
                  }
               }
            } else {
               float var15 = (float)((int)this.method0(var12, var2, var3, var8, var9, var10));
               if (var15 != 0.0F) {
                  ++var15;
                  if (var14) {
                     this.method0(var2, var3 + (float)this.cX_ + 1.0F, var15);
                  }

                  if (var7) {
                     this.method0(var2, var3 + (float)this.cX_ / 2.0F, var15);
                  }

                  var2 += var15;
               }
            }
         }

         if (!this.field17) {
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(this.field1.getGlTextureId());
            field22.draw();
            GlStateManager.bindTexture(0);
            this.method2(-1);
            GlStateManager.popMatrix();
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
         }

         return var2 / (float)this.field7;
      } else {
         return var2;
      }
   }

   public float method0(int var1, float var2, float var3, int var4) {
      if (this.field6 == null) {
         return 0.0F;
      } else {
         var3 += (float)this.field20;
         this.method2(var4);
         return this.field6.method0(var1, var2, var3);
      }
   }

   public float method0(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         float var2 = 0.0F;
         boolean var3 = false;
         boolean var4 = false;

         for(int var5 = 0; var5 < var1.length(); ++var5) {
            char var6 = var1.charAt(var5);
            if (var6 == 167) {
               ++var5;
               if (var5 >= var1.length()) {
                  break;
               }

               int var7 = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(var1.charAt(var5)));
               if (var7 != -1) {
                  if (var7 < 16) {
                     var3 = false;
                     var4 = false;
                  } else if (var7 != 16) {
                     if (var7 == 17) {
                        var3 = true;
                     } else if (var7 == 20) {
                        var4 = true;
                     } else {
                        var3 = false;
                        var4 = false;
                     }
                  }
               }
            } else {
               var2 += (float)((int)this.method0(var6, var3, var4) + 1);
            }
         }

         var2 /= (float)this.field7;
         return var2;
      } else {
         return 0.0F;
      }
   }

   public float method0(String var1, float var2, float var3, int var4) {
      float var5 = this.method0(var1) / 2.0F;
      float var6 = this.method0(var1, var2 - var5 + 1.0F, var3 + 1.0F, (var4 & 16316664) >> 3 | var4 & -16777216, true);
      this.method1(var1, var2 - var5, var3, var4);
      return var6;
   }

   public float method1(String var1, float var2, float var3, int var4) {
      return this.method0(var1, var2, var3, var4, false);
   }

   public void method0(boolean var1) {
      this.field17 = var1;
   }

   public void method0(Font var1, Graphics2D var2) {
      BlockCommand.method0(var1, var2, this.field8, this.field8, this.U_, this.field11);
   }

   public void method0(float var1) {
      if (this.field10 != var1) {
         this.field10 = var1;
         this.field2 = this.field2.deriveFont(0, this.field10 * (float)this.field7);
         this.method4();
      }
   }

   public Class61(Font var1, Font var2) {
      this(var1, var2, true, true, true, 0.3F, 256);
   }

   public float method1(int var1) {
      return this.field6 == null ? 0.0F : this.field6.method0(var1);
   }

   public void method0(float var1, float var2, float var3) {
      GlStateManager.disableTexture2D();
      GL11.glLineWidth(2.0F);
      GL11.glBegin(1);
      GL11.glVertex2f(var1, var2);
      GL11.glVertex2f(var1 + var3, var2);
      GL11.glEnd();
      GL11.glLineWidth(1.0F);
      GlStateManager.enableTexture2D();
   }

   public float method0() {
      return (float)this.field16 / (float)this.field7;
   }

   public void method1() {
      GlStateManager.bindTexture(this.field1.getGlTextureId());
   }

   public void p_() {
      if (this.field6 != null) {
         this.field6.p_();
      }

      if (this.field1 != null) {
         this.field1.deleteGlTexture();
         this.field1 = null;
      }

   }

   public DynamicTexture method0(Font var1, Class61.Class0[] var2) {
      DynamicTexture var3 = new DynamicTexture(this.method1(var1, var2));
      GlStateManager.enableTexture2D();
      GlStateManager.bindTexture(var3.getGlTextureId());
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      return var3;
   }

   public String method0(String var1, float var2, boolean var3) {
      if (var1 != null && !var1.isEmpty()) {
         var2 *= (float)this.field7;
         StringBuilder var4 = new StringBuilder();
         boolean var5 = false;
         boolean var6 = false;
         float var7 = 0.0F;
         int var8;
         char var9;
         float var10;
         int var11;
         if (!var3) {
            for(var8 = 0; var8 < var1.length(); ++var8) {
               var9 = var1.charAt(var8);
               if (var9 == 167) {
                  ++var8;
                  if (var8 >= var1.length()) {
                     break;
                  }

                  var11 = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(var1.charAt(var8)));
                  if (var11 != -1) {
                     if (var11 < 16) {
                        var5 = false;
                        var6 = false;
                     } else if (var11 != 16) {
                        if (var11 == 17) {
                           var5 = true;
                        } else if (var11 == 20) {
                           var6 = true;
                        } else {
                           var5 = false;
                           var6 = false;
                        }
                     }
                  }
               } else {
                  var10 = this.method0(var9, var5, var6);
                  if (var7 + var10 > var2) {
                     return var4.toString();
                  }

                  var4.append(var9);
                  var7 += var10;
               }
            }

            return var4.toString().trim();
         } else {
            for(var8 = var1.length() - 1; var8 >= 0; --var8) {
               var9 = var1.charAt(var8);
               if (var8 - 1 > 0 && var1.charAt(var8 - 1) == 167) {
                  var11 = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(var1.charAt(var8)));
                  if (var11 != -1) {
                     if (var11 < 16) {
                        var5 = false;
                        var6 = false;
                     } else if (var11 != 16) {
                        if (var11 == 17) {
                           var5 = true;
                        } else if (var11 == 20) {
                           var6 = true;
                        } else {
                           var5 = false;
                           var6 = false;
                        }
                     }

                     --var8;
                  }
               } else {
                  var10 = this.method0(var9, var5, var6);
                  if (var7 + var10 > var2) {
                     return var4.toString();
                  }

                  var4.insert(0, var9);
                  var7 += var10;
               }
            }

            return var4.toString().trim();
         }
      } else {
         return "";
      }
   }

   public float method3() {
      return (float)this.T_ / (float)this.field7;
   }

   public float method0(int var1, boolean var2, boolean var3) {
      if (var1 >= this.field9.length) {
         return this.method1(var1);
      } else {
         Class61.Class0 var4 = this.field9[var1];
         return var4 == null ? this.method1(var1) : (var2 && var3 ? var4.field2 : (var2 ? var4.field9 : (var3 ? var4.V_ : var4.dj_))) - this.field21;
      }
   }

   public float method2(String var1, float var2, float var3, int var4) {
      return this.method1(var1, var2 - this.method0(var1) / 2.0F, var3, var4);
   }

   public float method3(String var1, float var2, float var3, int var4) {
      float var5 = this.method0(var1, var2 + 1.0F, var3 + 1.0F, (var4 & 16316664) >> 3 | var4 & -16777216, true);
      this.method1(var1, var2, var3, var4);
      return var5;
   }

   public Class61(Font var1) {
      this(var1, (Font)null);
   }

   public Class61(Font var1, Font var2, boolean var3, boolean var4, boolean var5, float var6, int var7) {
      this.field9 = new Class61.Class0[256];
      this.field17 = false;
      this.field2 = var1;
      this.field10 = var1.getSize2D();
      this.field6 = var2 == null ? null : new Class407(var2, var3, var4, var6);
      this.field11 = var4;
      this.U_ = var3;
      this.field18 = var5;
      this.field15 = var7 * 2;
      this.field21 = 5.0F + var6;
      this.a_((new ScaledResolution(field14)).getScaleFactor());
      ArmorStandsCommand.method2();
   }

   public void method2(int var1) {
      GlStateManager.color((float)(var1 >> 16 & 255) / 255.0F, (float)(var1 >> 8 & 255) / 255.0F, (float)(var1 & 255) / 255.0F, (float)(var1 >> 24 & 255) / 255.0F);
   }

   public List method1(String var1, float var2) {
      if (var1 != null && !var1.isEmpty()) {
         ArrayList var3 = Lists.newArrayList();

         String var4;
         do {
            var4 = this.method2(var1, var2);
            var1 = var1.substring(var4.length());
            var3.add(var4);
         } while(!var1.isEmpty() && !var1.equals(var4));

         return var3;
      } else {
         return Lists.newArrayList();
      }
   }

   public void method0(float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      float var8 = var3 / (float)this.field8;
      float var9 = var4 / (float)this.field8;
      float var10 = var5 / (float)this.field8;
      float var11 = var6 / (float)this.field8;
      float var12 = (float)(var7 >> 16 & 255) / 255.0F;
      float var13 = (float)(var7 >> 8 & 255) / 255.0F;
      float var14 = (float)(var7 & 255) / 255.0F;
      float var15 = (float)(var7 >> 24 & 255) / 255.0F;
      field4.pos((double)(var1 + var5), (double)var2, 0.0D).tex((double)(var8 + var10), (double)var9).color(var12, var13, var14, var15).endVertex();
      field4.pos((double)var1, (double)var2, 0.0D).tex((double)var8, (double)var9).color(var12, var13, var14, var15).endVertex();
      field4.pos((double)var1, (double)(var2 + var6), 0.0D).tex((double)var8, (double)(var9 + var11)).color(var12, var13, var14, var15).endVertex();
      field4.pos((double)(var1 + var5), (double)(var2 + var6), 0.0D).tex((double)(var8 + var10), (double)(var9 + var11)).color(var12, var13, var14, var15).endVertex();
   }

   static {
      field4 = field22.getWorldRenderer();
   }

   public void method4() {
      this.cX_ = 0;
      if (this.field1 != null) {
         this.field1.deleteGlTexture();
      }

      this.field1 = this.method0(this.field2, this.field9);
   }

   public void method5() {
      if (!this.field17) {
         ScaledResolution var1 = new ScaledResolution(field14);
         this.a_(var1.getScaleFactor());
         GlStateManager.pushMatrix();
         GlStateManager.scale(1.0F / (float)this.field7, 1.0F / (float)this.field7, 1.0F);
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         this.field17 = true;
         field4.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      }
   }

   public BufferedImage method1(Font var1, Class61.Class0[] var2) {
      BufferedImage var3 = new BufferedImage(this.field8, this.field8, 2);
      Graphics2D var4 = var3.createGraphics();
      this.method0(var1, var4);
      FontMetrics var5 = var4.getFontMetrics();
      int var6 = 0;
      int var7 = 0;
      int var8 = 1;
      this.cX_ = var5.getAscent();
      this.field20 = var5.getDescent();
      this.field16 = var5.getAscent() - var5.getDescent();
      this.T_ = var5.getHeight();

      for(int var9 = 0; var9 <= 3; ++var9) {
         var4.setFont(var1.deriveFont(var9));
         var5 = var4.getFontMetrics();

         for(int var10 = 0; var10 < var2.length; ++var10) {
            char var11 = (char)var10;
            Class61.Class0 var12 = var9 == 0 ? new Class61.Class0((Class61$1)null) : var2[var10];
            if (var12 != null && var1.canDisplay(var11)) {
               Rectangle2D var13 = var5.getStringBounds(String.valueOf(var11), var4);
               float var14 = (float)(var13.getWidth() + 5.0D);
               float var15 = (float)var13.getHeight();
               if ((float)var7 + var14 >= (float)this.field8) {
                  var7 = 0;
                  var8 += var6 + 3;
                  var6 = 0;
               }

               if (var15 > (float)var6) {
                  var6 = (int)Math.ceil((double)var15);
               }

               switch(var9) {
               case 0:
                  var12.dj_ = var14;
                  var12.di_ = var15;
                  var12.field4 = (float)var7;
                  var12.field14 = (float)var8;
                  break;
               case 1:
                  var12.field9 = var14;
                  var12.field11 = var15;
                  var12.field3 = (float)var7;
                  var12.field6 = (float)var8;
                  break;
               case 2:
                  var12.V_ = var14;
                  var12.field10 = var15;
                  var12.field12 = (float)var7;
                  var12.field13 = (float)var8;
                  break;
               case 3:
                  var12.field2 = var14;
                  var12.field8 = var15;
                  var12.field0 = (float)var7;
                  var12.field7 = (float)var8;
               }

               var4.drawString(String.valueOf(var11), var7, var8 + var5.getAscent());
               var7 = (int)((float)var7 + var14 + 3.0F);
               var2[var10] = var12;
            } else {
               var2[var10] = null;
            }
         }
      }

      return var3;
   }

   public void method6() {
      if (this.field17) {
         GlStateManager.enableTexture2D();
         GlStateManager.bindTexture(this.field1.getGlTextureId());
         field22.draw();
         GlStateManager.bindTexture(0);
         this.method2(-1);
         GlStateManager.popMatrix();
         GlStateManager.disableBlend();
         GlStateManager.enableAlpha();
         this.field17 = false;
      }
   }

   public float method0(int var1, float var2, float var3, boolean var4, boolean var5, int var6) {
      if (var1 >= this.field9.length) {
         return this.method0(var1, var2, var3, var6);
      } else {
         Class61.Class0 var7 = this.field9[var1];
         if (var7 == null) {
            return this.method0(var1, var2, var3, var6);
         } else {
            float var8;
            if (var4 && var5) {
               var8 = var7.field2;
               this.method0(var2, var3, var7.field0, var7.field7, var8, var7.field8, var6);
            } else if (var4) {
               var8 = var7.field9;
               this.method0(var2, var3, var7.field3, var7.field6, var8, var7.field11, var6);
            } else if (var5) {
               var8 = var7.V_;
               this.method0(var2, var3, var7.field12, var7.field13, var8, var7.field10, var6);
            } else {
               var8 = var7.dj_;
               this.method0(var2, var3, var7.field4, var7.field14, var8, var7.di_, var6);
            }

            return var8 - this.field21;
         }
      }
   }

   public float method7() {
      return (float)this.cX_ / (float)this.field7;
   }

   private static class Class0 {
      public float field0;
      public float dj_;
      public float field2;
      public float field3;
      public float field4;
      public float V_;
      public float field6;
      public float field7;
      public float field8;
      public float field9;
      public float field10;
      public float field11;
      public float field12;
      public float field13;
      public float field14;
      public float di_;

      private Class0() {
      }

      Class0(Class61$1 var1) {
         this();
      }
   }
}
