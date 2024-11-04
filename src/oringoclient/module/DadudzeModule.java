package oringo.module;

import java.util.ArrayList;
import java.util.Iterator;
import map.Class447;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.opengl.GL11;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class DadudzeModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Mode", "Frame Times", new String[]{"Frame Times", "Tick Times"});
   public final ArrayList aW_ = new ArrayList();
   public final Class447 field2 = new Class447();

   public static double method3() {
      return Math.toRadians((double)PacketLoggerModule.method5());
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(ClientTickEvent var1) {
      if (this.field0.method0(1)) {
         if (var1.phase == Phase.START) {
            this.field2.o_();
         }

      }
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(RenderTickEvent var1) {
      if (var1.phase == Phase.START && this.field0.method0(0)) {
         this.field2.o_();
      }

   }

   public long method5() {
      return 0L;
   }

   public DadudzeModule() {
      super("Dadudze", Category.other, SubCategory.other, (String)null);
      this.method0((Setting[])(new Setting[]{this.field0}));
   }

   public long method6() {
      return 0L;
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method1(RenderTickEvent var1) {
      if (var1.phase == Phase.END) {
         if (this.field0.method0(0)) {
            this.aW_.add(this.field2.method2());
            if (this.aW_.size() > 940) {
               this.aW_.remove(0);
            }
         }

         this.method7();
      }

   }

   public void method7() {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glBegin(1);
      int var1 = 0;

      for(Iterator var2 = this.aW_.iterator(); var2.hasNext(); ++var1) {
         Long var3 = (Long)var2.next();
         if (var3 > 40L) {
            GlStateManager.color(1.0F, 0.0F, 0.0F);
         } else {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
         }

         GL11.glVertex2f((float)(10 + var1), 10.0F);
         GL11.glVertex2f((float)(10 + var1), (float)(10L + var3));
      }

      GL11.glEnd();
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method1(ClientTickEvent var1) {
      if (this.field0.method0(1)) {
         if (var1.phase == Phase.END) {
            this.aW_.add(this.field2.method2());
            if (this.field2.method2() > 40L) {
               System.out.println(System.currentTimeMillis());
            }

            if (this.aW_.size() > 940) {
               this.aW_.remove(0);
            }
         }

      }
   }
}
