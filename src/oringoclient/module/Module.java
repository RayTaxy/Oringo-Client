package oringo.module;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import map.Class169;
import map.Class216;
import map.Class247;
import map.Class259;
import map.Class302;
import map.Class341;
import map.Class368;
import map.Class408;
import map.Class411;
import map.Class447;
import map.Class492;
import map.Class500;
import map.Class518;
import map.Class528;
import map.Class6;
import map.Class81;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.network.Packet;
import net.minecraftforge.common.MinecraftForge;
import oringo.Oringo;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.KeyBindSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class Module {
   public boolean field54;
   public final Class447 field55;
   public final List field56;
   protected int field57;
   private boolean field0;
   private boolean field1;
   protected static final Minecraft field58 = Minecraft.getMinecraft();
   private boolean field2;
   public final BooleanSetting field59;
   private final SubCategory field3;
   private final Category field4;
   private Class500 field5;
   private final List field6;
   private final Class6 field7;
   public final Class447 field60;
   public String field61;
   public String cW_;
   private final List field8;

   protected static void method2(Object var0) {
      PopupAnimationModule.method2(var0);
   }

   public Class6 method36() {
      return this.field7;
   }

   public void method0(Setting var1) {
      if (this.method47().contains(var1)) {
         throw new IllegalArgumentException("Setting " + var1.cW_ + " already present");
      } else {
         this.method47().add(var1);
         if (var1 instanceof BooleanSetting) {
            this.field6.add(new Class492((BooleanSetting)var1));
         } else if (var1 instanceof StringSetting) {
            this.field6.add(new Class368((StringSetting)var1));
         } else if (var1 instanceof DoubleSetting) {
            this.field6.add(new Class411((DoubleSetting)var1));
         } else if (var1 instanceof EnumSetting) {
            this.field6.add(new Class216((EnumSetting)var1));
         } else if (var1 instanceof ColorSetting) {
            this.field6.add(new Class259((ColorSetting)var1));
         } else if (var1 instanceof ButtonSetting) {
            this.field6.add(new Class518((ButtonSetting)var1));
         } else if (var1 instanceof KeyBindSetting) {
            this.field6.add(new Class81((KeyBindSetting)var1));
            this.field8.add((KeyBindSetting)var1);
         }

      }
   }

   public Category method37() {
      return this.field4;
   }

   protected String method38() {
      return this.field5 == null ? "" : (String)this.field5.get();
   }

   public SubCategory method39() {
      return this.field3;
   }

   /** @deprecated */
   @Deprecated
   public Module(String var1, Category var2, SubCategory var3) {
      this(var1, 0, var2, var3, (String)null);
   }

   public void method40() {
      this.method1(!this.field0);
   }

   public List method41() {
      return this.field6;
   }

   public boolean c_() {
      return this instanceof Class408;
   }

   public void method3(int var1) {
      this.field57 = var1;
   }

   public void method0(Setting... var1) {
      Setting[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Setting var5 = var2[var4];
         this.method0(var5);
      }

   }

   protected void method42() {
      if (!this.field2 && Class528.method0(this.getClass())) {
         this.field2 = true;
         MinecraftForge.EVENT_BUS.register(this);
      }

   }

   public String d_() {
      return this.cW_;
   }

   protected static void method2(Packet var0) {
      Class302.method2(var0);
   }

   public void method1(JsonObject var1) {
   }

   protected static void method0(String var0) {
      NoRenderModule2.method0(var0);
   }

   protected void method43() {
      if (this.field2 && !this.field1) {
         this.field2 = false;
         MinecraftForge.EVENT_BUS.unregister(this);
      }

   }

   public Module(String var1, int var2, Category var3, SubCategory var4, String var5) {
      this.field56 = new ArrayList();
      this.field8 = new ArrayList();
      this.field7 = new Class6();
      this.field6 = new ArrayList();
      this.field60 = new Class447();
      this.field55 = new Class447();
      if (var4 != null && var3 != null) {
         this.cW_ = var1;
         this.field57 = var2;
         this.field4 = var3;
         this.field61 = var5;
         this.field3 = var4;
         this.field6.add(new Class247(this));
         this.field6.add(new Class169(this));
         this.field59 = new BooleanSetting("Hide", this.field4 == Category.render);
         this.method0((Setting)this.field59);
      } else {
         throw new IllegalArgumentException("Module category can not be null");
      }
   }

   public boolean method44() {
      return this.field0;
   }

   protected static void method1(Object var0, ClickEvent var1) {
      RenderBarriersModule.method1(var0, var1);
   }

   public void e_() {
   }

   public void b_() {
   }

   public String method45() {
      return this.cW_;
   }

   public void a_(JsonObject var1) {
   }

   public int method46() {
      return this.field57;
   }

   public String method2(boolean var1) {
      String var2 = this.method38().isEmpty() ? this.method45() : this.method45() + " §7" + this.method38();
      if (var1) {
         var2 = var2.toLowerCase(Locale.ENGLISH);
      }

      return var2;
   }

   protected void method0(Class500 var1) {
      this.field5 = var1;
   }

   public void method1(boolean var1) {
      if (this.field0 != var1) {
         this.field0 = var1;
         if (var1) {
            this.method42();
         } else {
            this.method43();
         }

         this.field60.method0(Class341.method0(250L - this.field60.method0(), 250L, 0L));
         this.e_();
         if (var1) {
            this.method4();
         } else {
            this.b_();
         }
      }

   }

   public void method4(int var1) {
      Iterator var2 = this.field8.iterator();

      while(var2.hasNext()) {
         KeyBindSetting var3 = (KeyBindSetting)var2.next();
         if (var1 == var3.method46()) {
            var3.method4();
         }
      }

   }

   public List method47() {
      return this.field56;
   }

   /** @deprecated */
   @Deprecated
   public Module(String var1, int var2, Category var3, SubCategory var4) {
      this(var1, var2, var3, var4, (String)null);
   }

   protected static void method3(Object var0) {
      if (Oringo.cV_) {
         method0(String.valueOf(var0));
      }

   }

   protected void method48() {
      this.method42();
      this.field1 = true;
   }

   public void method4() {
   }

   public Module(String var1, Category var2, SubCategory var3, String var4) {
      this(var1, 0, var2, var3, var4);
   }

   protected static void method3(Packet var0) {
      field58.getNetHandler().addToSendQueue(var0);
   }
}
