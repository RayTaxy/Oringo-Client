package oringo.module;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.Vec4b;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class533;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class ScreenShotModule extends Module {
   public static int cX_;
   public static File field1;
   public final BooleanSetting cY_ = new BooleanSetting("Hide all names", true, this::lambda$new$0);
   public static final StringSetting field3 = new StringSetting("Uploader", "");
   public static String field4;
   public final BooleanSetting field5 = new BooleanSetting("Hide Chat", true);
   public static boolean field6 = false;
   public final BooleanSetting field7 = new BooleanSetting("Hide F3", false);
   public static boolean field8 = false;
   public static Framebuffer field9;
   public final BooleanSetting field10 = new BooleanSetting("Hide skins", true);
   public final BooleanSetting field11 = new BooleanSetting("Hide Sidebar", false);
   public static final BooleanSetting field12 = new BooleanSetting("Auto Copy", true);
   public final BooleanSetting field13 = new BooleanSetting("Enable nick hider", true);
   public static int field2;

   public ScreenShotModule() {
      super("ScreenShot", Category.other, SubCategory.ui, "Modifies minecraft screenshot behavior");
      this.method0((Setting[])(new Setting[]{field12, this.field11, this.field5, this.field7, this.field13, this.cY_, this.field10, field3}));
   }

   public Boolean lambda$new$0() {
      return !this.field13.method1();
   }

   public static float method0(Vec4b var0) {
      return (float)var0.func_176113_c() / 2.0F + 64.0F;
   }

   @SubscribeEvent
   public void method0(Pre var1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void method0(Class533 var1) {
      if (field8) {
         if (this.field11.method1()) {
            var1.method9();
         }

      }
   }

   public static class Class0 implements Transferable {
      private final BufferedImage field0;

      public DataFlavor[] method0() {
         return new DataFlavor[]{DataFlavor.imageFlavor};
      }

      public Object method0(DataFlavor var1) throws UnsupportedFlavorException {
         if (var1.equals(DataFlavor.imageFlavor) && this.field0 != null) {
            return this.field0;
         } else {
            throw new UnsupportedFlavorException(var1);
         }
      }

      public Class0(BufferedImage var1) {
         this.field0 = var1;
      }

      public boolean method1(DataFlavor var1) {
         return var1.equals(DataFlavor.imageFlavor);
      }
   }
}
