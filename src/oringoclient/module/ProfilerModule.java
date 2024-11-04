package oringo.module;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import map.Class447;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import org.lwjgl.opengl.EXTFramebufferObject;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class ProfilerModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Ignore short events", true, "Fixes the problem of nanoTime method being slow");
   public static final EnumSetting cD_ = new EnumSetting("Push on", "Second", new String[]{"Render", "Tick", "Second", "5s"});
   public static ProfilerModule.Class0 cE_ = new ProfilerModule.Class0();
   public static final BooleanSetting field3 = new BooleanSetting("Custom", false);
   public static final ArrayList field4 = new ArrayList(100);
   public final Class447 field5 = new Class447();
   public static final EnumSetting field6 = new EnumSetting("Type", "Sum", new String[]{"Sum", "Max", "Amount"});
   public static final StringSetting field7 = new StringSetting("Filter", "");
   public int field2;
   public static final BooleanSetting field9 = new BooleanSetting("Only Oringo", true);
   public static ProfilerModule field10;

   public static EnumSetting access$200() {
      return field6;
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(RenderTickEvent var1) {
      if (var1.phase == Phase.START && cD_.method0(0)) {
         this.method12();
      }

   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START) {
         if (cD_.method0(1)) {
            this.method12();
         }

         if (cD_.method0(2) && this.field5.method0(1000L, true)) {
            this.method12();
         }

         if (cD_.method0(3) && this.field5.method0(5000L, true)) {
            this.method12();
         }
      }

   }

   public ProfilerModule() {
      super("Profiler", Category.other, SubCategory.other, (String)null);
      this.method0((Setting[])(new Setting[]{cD_, field6, field7, field9, field0, field3}));
   }

   @SubscribeEvent
   public void method1(RenderTickEvent var1) {
      if (var1.phase == Phase.END) {
         this.method11();
      }

   }

   public static BooleanSetting access$500() {
      return field9;
   }

   public static Minecraft access$000() {
      return field58;
   }

   public static StringSetting access$300() {
      return field7;
   }

   public void r_(String var1) {
      field58.fontRendererObj.drawString(var1, 5, this.field2, -1);
      this.field2 += 9;
   }

   public static BooleanSetting access$100() {
      return field0;
   }

   public static BooleanSetting access$400() {
      return field3;
   }

   public void method11() {
      if (!field4.isEmpty()) {
         ProfilerModule.Class0 var1 = (ProfilerModule.Class0)field4.get(field4.size() - 1);
         List var2 = var1.method1();
         long var3 = var1.method0();
         this.field2 = 85;
         if (cD_.method0(2)) {
            this.r_(String.format("Events took: §c%s§fms     FPS without: " + ForagingMacroModule.method0(Minecraft.getDebugFPS(), (int)((double)var3 / 1000000.0D)), (int)((double)var3 / 1000000.0D)));
         }

         Iterator var5 = var2.iterator();

         while(var5.hasNext()) {
            Entry var6 = (Entry)var5.next();
            long var7 = ((AtomicLong)var6.getValue()).get();
            this.r_(MithrilMacroModule.n_((String)var6.getKey()) + (field6.method0(2) ? " " + var7 : String.format(" %.1fms   (%s%%)", (float)var7 / 1000000.0F, var7 * 100L / var3)));
         }

      }
   }

   public void method12() {
      field4.add(cE_);
      cE_ = new ProfilerModule.Class0();
      if (field4.size() > 100) {
         field4.remove(0);
      }

   }

   public static void method0(Framebuffer var0) {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(var0.depthBuffer);
      int var1 = EXTFramebufferObject.glGenRenderbuffersEXT();
      EXTFramebufferObject.glBindRenderbufferEXT(36161, var1);
      EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, var1);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, var1);
   }

   public static class Class0 {
      private final ConcurrentHashMap field0 = new ConcurrentHashMap();

      public void method0(String var1, long var2) {
         if (!ProfilerModule.access$000().isCallingFromMinecraftThread()) {
            var1 = var1 + " ASYNC";
         }

         if (!ProfilerModule.access$100().method1() || var2 > 1000L) {
            AtomicLong var4 = (AtomicLong)this.field0.get(var1);
            if (var4 == null) {
               var4 = new AtomicLong();
               this.field0.put(var1, var4);
            }

            if (ProfilerModule.access$200().method0("Sum")) {
               var4.addAndGet(var2);
            } else if (ProfilerModule.access$200().method0("Max")) {
               var4.set(Math.max(var4.get(), var2));
            } else if (ProfilerModule.access$200().method0("Amount")) {
               var4.incrementAndGet();
            }

         }
      }

      private static boolean lambda$getTop5$1(Entry var0) {
         boolean var10000;
         label33: {
            if (ProfilerModule.access$300().F_() || ((String)var0.getKey()).contains(ProfilerModule.access$300().method1())) {
               if (ProfilerModule.access$400().method1()) {
                  if (!((String)var0.getKey()).startsWith("ASM:")) {
                     break label33;
                  }
               } else if (!ProfilerModule.access$500().method1() || ((String)var0.getKey()).startsWith("ASM: me.oringo")) {
                  break label33;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public long method0() {
         long var1 = 0L;
         Iterator var3 = this.field0.entrySet().iterator();

         while(var3.hasNext()) {
            Entry var4 = (Entry)var3.next();
            if (!((String)var4.getKey()).endsWith(" ASYNC")) {
               var1 += ((AtomicLong)var4.getValue()).get();
            }
         }

         return var1;
      }

      private static long lambda$getTop5$0(Entry var0) {
         return -((AtomicLong)var0.getValue()).get();
      }

      public List method1() {
         return (List)this.field0.entrySet().stream().sorted(Comparator.comparingLong(ProfilerModule.Class0::lambda$getTop5$0)).filter(ProfilerModule.Class0::lambda$getTop5$1).limit(8L).collect(Collectors.toList());
      }
   }
}
