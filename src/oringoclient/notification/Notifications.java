package oringo.notification;

import java.awt.Color;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import map.Class311;
import map.Class362;
import map.Class469;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.module.ReachModule;

public class Notifications {
   public static final CopyOnWriteArrayList  = new CopyOnWriteArrayList();

   public static boolean lambda$onRender$0(Notifications.Class0 var0) {
      return var0.method5() <= System.currentTimeMillis();
   }

   @SubscribeEvent
   public void method0(RenderTickEvent var1) {
      if (!.isEmpty()) {
         if (var1.phase == Phase.END) {
            GL11.glPushMatrix();
            if (!.isEmpty()) {
               .removeIf(Notifications::lambda$onRender$0);
               ScaledResolution var2 = new ScaledResolution(Oringo.field9);
               float var3 = (float)(var2.getScaledHeight() - 37);
               Iterator var4 = .iterator();

               while(var4.hasNext()) {
                  Notifications.Class0 var5 = (Notifications.Class0)var4.next();
                  GL11.glPushMatrix();
                  float var6 = Math.max(150.0F, Class311.field12.method0(var5.method1()) + 10.0F);
                  float var7 = 35.0F;
                  float var8 = (float)var2.getScaledWidth() - var6 - 2.0F;
                  long var9;
                  if (var5.method0() <= 250L) {
                     if (var5.method0() >= 100L) {
                        var8 = (float)((double)var8 + (double)(250L - var5.method0()) / 150.0D * (double)(var6 + 2.0F));
                     } else {
                        var8 += 10000.0F;
                     }
                  } else if (var5.method5() - System.currentTimeMillis() <= 250L) {
                     var9 = var5.method5() - System.currentTimeMillis();
                     if (var9 >= 100L) {
                        var8 = (float)((double)var8 + (double)(250L - var9) / 150.0D * (double)(var6 + 2.0F));
                     } else {
                        var8 += 10000.0F;
                     }
                  }

                  ReachModule.method0((double)var8, (double)var3, (double)(var8 + var6), (double)(var3 + var7), 3.0D, (new Color(21, 21, 21, 90)).getRGB());
                  Class311.field0.method3(var5.method2(), var8 + 3.0F, var3 + 5.0F, var5.method17().getRGB());
                  Class311.field12.method3(var5.method1(), var8 + 5.0F, var3 + 10.0F + Class311.field0.method0(), Color.white.getRGB());
                  Class469.method0(var8, var3 + var7 - 2.0F, var8 + var6 * (float)var5.method0() / (float)var5.h_(), var3 + var7, var5.method17().getRGB());
                  if (var5.method0() < 100L) {
                     var7 = (float)((double)var7 * ((double)var5.method0() / 100.0D));
                  } else if (var5.method5() - System.currentTimeMillis() < 100L) {
                     var9 = var5.method5() - System.currentTimeMillis();
                     var7 = (float)((double)var7 * ((double)var9 / 100.0D));
                  }

                  var3 -= var7 + 1.0F;
                  GL11.glPopMatrix();
               }
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
         }

      }
   }

   public static CopyOnWriteArrayList access$000() {
      return ;
   }

   public static enum Class1 {
      field0("Error", new Color(208, 3, 3));

      private final String field3;
      private final Color dh_;
      field1("Notification", Color.white);

      private static final Notifications.Class1[] field5 = new Notifications.Class1[]{field2, field1, field0};
      field2("Warning", new Color(255, 204, 0));

      public Color getColor(int var1) {
         return this == field1 ? Class362.field7.method0((float)var1) : this.dh_;
      }

      private Class1(String var3, Color var4) {
         this.field3 = var3;
         this.dh_ = var4;
      }

      public String method0() {
         return this.field3;
      }
   }

   private static class Class0 {
      private final long field0;
      private final int field1;
      private final Notifications.Class1 field2;
      private final int field3;
      private final String field61;

      public long method0() {
         return System.currentTimeMillis() - this.field0 + (long)this.field1;
      }

      public int h_() {
         return this.field1;
      }

      public Color method17() {
         return this.field2.getColor(this.field3);
      }

      public String method1() {
         return this.field61;
      }

      public String method2() {
         return this.field2.method0();
      }

      public long method5() {
         return this.field0;
      }

      public Class0(String var1, int var2, Notifications.Class1 var3) {
         this.field61 = var1;
         this.field0 = System.currentTimeMillis() + (long)var2;
         this.field1 = var2;
         this.field2 = var3;
         this.field3 = Notifications.access$000().size() + 1;
      }
   }
}
