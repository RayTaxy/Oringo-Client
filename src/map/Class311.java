package map;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import oringo.Oringo;
import oringo.module.ClickGuiModule;

public class Class311 {
   public static Class195 field0;
   public static Class195 field1;
   public static Class195 field2;
   public static final Map field3 = new HashMap();
   public static Class195 field4;
   public static Class195 field5;
   public static Class195 field6;
   public static Class195 field7;
   public static Class195 field8;
   public static final Logger field9 = Oringo.method0("Oringo-Fonts");
   public static Class195 field10;
   public static Class195 field11;
   public static Class195 field12;
   public static Class195 field13;
   public static Class195 field14;
   public static Class195 field15;
   public static Class195 field16;
   public static Class61 field17;
   public static Class195 field18;

   public static void method0() {
      Font var0 = null;
      String var1 = ClickGuiModule.field12.method4();
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1841836187:
         if (var1.equals("Roboto")) {
            var2 = 1;
         }
         break;
      case -989313071:
         if (var1.equals("Quicksand")) {
            var2 = 0;
         }
         break;
      case 74710421:
         if (var1.equals("Museo")) {
            var2 = 3;
         }
         break;
      case 79311809:
         if (var1.equals("Rubik")) {
            var2 = 2;
         }
      }

      switch(var2) {
      case 0:
         var0 = Class42.method0("Quicksand-Bold.ttf", 10.0F);
         break;
      case 1:
         var0 = Class42.method0("robotoMedium.ttf", 10.0F);
         break;
      case 2:
         var0 = Class42.method0("Rubik-Medium.ttf", 10.0F);
         break;
      case 3:
         var0 = Class42.method0("Museo.ttf", 10.0F);
         break;
      default:
         Font[] var3 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Font var6 = var3[var5];
            if (var6.getName().equals(ClickGuiModule.field12.method4())) {
               var0 = var6.deriveFont(0, 10.0F);
            }
         }
      }

      if (var0 != null) {
         if (field18 != null) {
            field18.p_();
         }

         if (field6 != null) {
            field6.p_();
         }

         if (field1 != null) {
            field1.p_();
         }

         if (field2 != null) {
            field2.p_();
         }

         if (field0 != null) {
            field0.p_();
         }

         if (field12 != null) {
            field12.p_();
         }

         if (field5 != null) {
            field5.p_();
         }

         if (field13 != null) {
            field13.p_();
         }

         if (field4 != null) {
            field4.p_();
         }

         if (field10 != null) {
            field10.p_();
         }

         if (field14 != null) {
            field14.p_();
         }

         if (field7 != null) {
            field7.p_();
         }

         if (field11 != null) {
            field11.p_();
         }

         if (field16 != null) {
            field16.p_();
         }

         if (field8 != null) {
            field8.p_();
         }

         if (field17 != null) {
            field17.p_();
         }

         if (field15 != null) {
            field15.p_();
         }

         field15 = new Class61(var0.deriveFont(7.0F));
         field18 = new Class61(Class42.method0("Museo.ttf", 13.0F));
         field6 = new Class61(Class42.method0("Museo.ttf", 10.0F));
         field1 = new Class61(var0.deriveFont(7.0F), Class42.method0("seguisym.ttf", 7.0F));
         field2 = new Class61(var0.deriveFont(8.0F));
         field0 = new Class61(var0, Class42.method0("seguisym.ttf", 10.0F));
         field12 = new Class61(var0, Class42.method0("seguisym.ttf", 10.0F));
         field5 = new Class61(Class42.method0("TAHOMAB0.TTF", 11.0F));
         field13 = new Class61(Class42.method0("TAHOMA_0.TTF", 11.0F));
         field4 = new Class61(var0.deriveFont(11.0F));
         field10 = field12;
         field14 = new Class61(var0.deriveFont(76.0F), (Font)null, true, true, false, 0.3F, 1024);
         field7 = field2;
         field11 = new Class61(var0.deriveFont(9.0F), Class42.method0("seguisym.ttf", 9.0F));
         field16 = new Class61(var0.deriveFont(9.0F), Class42.method0("seguisym.ttf", 9.0F));
         field8 = field12;
         field17 = new Class61(var0);
      }
   }
}
