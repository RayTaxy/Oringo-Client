package map;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import oringo.event.Class274;
import oringo.event.Class533;
import oringo.module.Category;
import oringo.module.ClickGuiModule;
import oringo.module.Module;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class Class306 extends GuiScreen {
   public Class306.Class0 field0;
   public static ArrayList field1;
   public int field2;
   public StringSetting field3;
   public DoubleSetting field4;
   public final Class447 field5 = new Class447();
   public final StringSetting field6 = new StringSetting("Search");
   public float field7;
   public float field8;
   public Module field9 = null;

   public static Float field0(Module var0) {
      return Class311.field12.method0(var0.d_());
   }

   public void mouseClicked(int var1, int var2, int var3) {
      this.field4 = null;
      this.field0 = null;
      this.field3 = null;
      if (this.field9 != null) {
         this.field9.method3(var3 - 100);
         this.field9 = null;
      } else {
         this.method0(var1, var2, var3, 0.0F, Class306.Class1.field0);
      }
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      int var1 = Mouse.getEventDWheel();
      if (var1 != 0) {
         if (var1 > 1) {
            var1 = 1;
         }

         if (var1 < -1) {
            var1 = -1;
         }

         int var2 = Mouse.getX() * this.width / this.mc.displayWidth;
         int var3 = this.height - Mouse.getY() * this.height / this.mc.displayHeight - 1;
         this.method0(var2, var3, var1, 0.0F, Class306.Class1.field3);
      }

   }

   public int method0(Class306.Class0 var1) {
      int var2 = 0;
      Iterator var3 = ((List)Class362.method0(var1.field0).stream().filter(this::field2).collect(Collectors.toList())).iterator();

      while(true) {
         Module var4;
         do {
            if (!var3.hasNext()) {
               return var2;
            }

            var4 = (Module)var3.next();
            ++var2;
         } while(!var4.field54);

         Iterator var5 = var4.field56.iterator();

         while(var5.hasNext()) {
            Setting var6 = (Setting)var5.next();
            if (!var6.g_()) {
               ++var2;
            }
         }

         ++var2;
      }
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      this.field0 = null;
      this.field4 = null;
      this.field9 = null;
      this.field3 = null;
      super.onGuiClosed();
   }

   public boolean method0(int var1, int var2, double var3, double var5, double var7, double var9) {
      return (double)var1 > var3 && (double)var1 < var3 + var9 && (double)var2 > var5 && (double)var2 < var5 + var7;
   }

   public boolean field1(Module var1) {
      return this.field6.method1() == null || var1.cW_.toLowerCase().contains(this.field6.method1().toLowerCase());
   }

   public Class306() {
      field1 = new ArrayList();
      byte var1 = 100;
      byte var2 = 20;
      int var3 = 40;
      byte var4 = 10;
      Category[] var5 = Category.values();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Category var8 = var5[var7];
         field1.add(new Class306.Class0(var8, (float)var3, (float)var4, (float)var1, (float)var2));
         var3 += var1 + 10;
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public static JsonElement method0(JsonParser var0, Reader var1) {
      return var0.parse(var1);
   }

   public void mouseReleased(int var1, int var2, int var3) {
      this.method0(var1, var2, var3, 0.0F, Class306.Class1.field1);
      super.mouseReleased(var1, var2, var3);
   }

   public void method0(int var1, int var2, int var3, float var4, Class306.Class1 var5) {
      // $FF: Couldn't be decompiled
   }

   public void setWorldAndResolution(Minecraft var1, int var2, int var3) {
      if (ClickGuiModule.field11.method1()) {
         this.field3 = this.field6;
      }

      Keyboard.enableRepeatEvents(true);
      this.field5.o_();
      super.setWorldAndResolution(var1, var2, var3);
   }

   public void keyTyped(char var1, int var2) throws IOException {
      int var3 = Mouse.getX() * this.width / this.mc.displayWidth;
      int var4 = this.height - Mouse.getY() * this.height / this.mc.displayHeight - 1;
      if (var2 != 1 && var2 != Class362.field7.method46()) {
         if (this.field9 != null) {
            this.field9.method3(var2);
            this.field9 = null;
         } else if (this.field3 != null) {
            if (var2 == 28) {
               this.field3 = null;
            } else if (var2 == 47 && (Keyboard.isKeyDown(157) || Keyboard.isKeyDown(29))) {
               this.field3.method1(this.field3.method1() + getClipboardString());
            } else if (var2 != 14) {
               this.field3.method1(this.field3.method1() + var1);
            } else if (!Keyboard.isKeyDown(157) && !Keyboard.isKeyDown(29)) {
               this.field3.method1(this.field3.method1().substring(0, Math.max(0, this.field3.method1().length() - 1)));
            } else {
               this.field3.method1(this.field3.method1().substring(0, Math.max(0, this.field3.method1().lastIndexOf(" "))));
            }
         }
      } else if (this.field9 != null) {
         this.field9.method3(0);
         this.field9 = null;
      } else if (this.field3 != null && this.field3 != this.field6) {
         this.field3 = null;
      } else {
         this.field3 = null;
         this.field0 = null;
         this.field4 = null;
         super.keyTyped(var1, var2);
      }

      this.method0(var3, var4, var2, 0.0F, Class306.Class1.field4);
   }

   public void handleInput() throws IOException {
      Class306.Class0 var2;
      for(Iterator var1 = field1.iterator(); var1.hasNext(); var2.field8 = var2.field4) {
         var2 = (Class306.Class0)var1.next();
      }

      super.handleInput();
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.method0(var1, var2, -1, var3, Class306.Class1.field2);
      super.drawScreen(var1, var2, var3);
   }

   public boolean field2(Module var1) {
      return this.field6.method1() == null || var1.cW_.toLowerCase().contains(this.field6.method1().toLowerCase());
   }

   public static class Class0 {
      public final Category field0;
      public float dj_;
      public float field2;
      public float cq_;
      public int field4;
      public float di_;
      public boolean field54;
      public boolean field3;
      public int field8;

      public Class0(Category var1, float var2, float var3, float var4, float var5) {
         this.field0 = var1;
         this.field2 = var2;
         this.cq_ = var3;
         this.dj_ = var4;
         this.di_ = var5;
         this.field54 = true;
         this.field3 = false;
      }
   }

   static enum Class1 {
      private static final Class306.Class1[] field5 = new Class306.Class1[]{field2, field0, field1, field3, field4};
      field0,
      field1,
      field2,
      field3,
      field4;
   }
}
