package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

public class Class315 {
   public static final boolean field0 = false;

   public final class Class3 {
      private final List field12;
      private int do_;
      public int field0;
      public int field1;
      public int[] field2;
      private Graphics2D field14;
      public int field3;
      public String field4;
      private int dn_;
      public String field5;
      private final Class315.Class2 field16;
      private BufferedImage dq_;
      public String field6;
      public int field7;
      public boolean dp_;
      private int field18;
      public boolean field9;
      public int field10;
      final Class315 field11;
      private final Class315.Class1 field19;

      public BufferedImage method0(int var1) {
         if (this.dq_ == null) {
            this.dq_ = new BufferedImage(this.dn_, this.do_, 2);
            this.field14 = this.dq_.createGraphics();
            this.field14.setBackground(new Color(0, true));
         }

         Class315.Class0 var2 = (Class315.Class0)this.field12.get(var1);
         if (Class315.Class0.access$2200(var2) == null) {
            for(int var3 = 0; var3 <= var1; ++var3) {
               var2 = (Class315.Class0)this.field12.get(var3);
               if (Class315.Class0.access$2200(var2) == null) {
                  this.method0(var2);
               }
            }
         }

         return Class315.Class0.access$2200(var2);
      }

      static int access$3002(Class315.Class3 var0, int var1) {
         return var0.field18 = var1;
      }

      private int[] method0(int[] var1, Class315.Class0 var2) {
         int var3 = Class315.Class0.access$1400(var2);
         int var4 = Class315.Class0.access$1500(var2);
         int var5 = Class315.Class0.access$1600(var2);
         int[] var6 = new int[var1.length];
         int var7 = var4 + 7 >>> 3;
         int var8 = var7 + (var4 + 3 >>> 3);
         int var9 = var8 + (var4 + 1 >>> 2);
         int var10 = var3 * var7;
         int var11 = var3 * var8;
         int var12 = var3 * var9;
         int var13 = var3 << 1;
         int var14 = var13 << 1;
         int var15 = var14 << 1;
         int var16 = 0;

         int var17;
         for(var17 = 0; var16 < var10; var17 += var15) {
            System.arraycopy(var1, var16, var6, var17, var3);
            var16 += var3;
         }

         for(var17 = var14; var16 < var11; var17 += var15) {
            System.arraycopy(var1, var16, var6, var17, var3);
            var16 += var3;
         }

         for(var17 = var13; var16 < var12; var17 += var14) {
            System.arraycopy(var1, var16, var6, var17, var3);
            var16 += var3;
         }

         for(var17 = var3; var16 < var5; var17 += var13) {
            System.arraycopy(var1, var16, var6, var17, var3);
            var16 += var3;
         }

         return var6;
      }

      public final int method0() {
         return this.do_;
      }

      private void method0(Class315.Class0 var1) {
         int[] var2 = Class315.Class0.access$1700(var1) ? Class315.Class0.access$1800(var1) : this.field2;
         int[] var3 = this.method0(var1, var2);
         if (Class315.Class0.access$1900(var1)) {
            var3 = this.method0(var3, var1);
         }

         BufferedImage var4 = new BufferedImage(Class315.Class0.access$1400(var1), Class315.Class0.access$1500(var1), 2);
         System.arraycopy(var3, 0, ((DataBufferInt)var4.getRaster().getDataBuffer()).getData(), 0, Class315.Class0.access$1600(var1));
         this.field14.drawImage(var4, Class315.Class0.access$2000(var1), Class315.Class0.access$2100(var1), (ImageObserver)null);
         int[] var5 = new int[this.field18];
         System.arraycopy(((DataBufferInt)this.dq_.getRaster().getDataBuffer()).getData(), 0, var5, 0, this.field18);
         Class315.Class0.access$2202(var1, new BufferedImage(this.dn_, this.do_, 2));
         System.arraycopy(var5, 0, ((DataBufferInt)Class315.Class0.access$2200(var1).getRaster().getDataBuffer()).getData(), 0, this.field18);
         if (Class315.Class0.access$2300(var1) == 2) {
            this.field14.clearRect(Class315.Class0.access$2000(var1), Class315.Class0.access$2100(var1), Class315.Class0.access$1400(var1), Class315.Class0.access$1500(var1));
         } else if (Class315.Class0.access$2300(var1) == 3) {
            System.arraycopy(var5, 0, ((DataBufferInt)this.dq_.getRaster().getDataBuffer()).getData(), 0, this.field18);
         }

      }

      static int access$2900(Class315.Class3 var0) {
         return var0.do_;
      }

      public final int method1() {
         Class315.Class0 var1 = (Class315.Class0)this.field12.get(0);
         if (Class315.Class0.access$1700(var1)) {
            return Class315.Class0.access$1800(var1)[this.field0];
         } else {
            return this.field9 ? this.field2[this.field0] : 0;
         }
      }

      public Class3(Class315 var1) {
         this.field11 = var1;
         this.field12 = new ArrayList(64);
         this.field5 = "";
         this.field6 = "";
         this.field3 = 0;
         this.dq_ = null;
         this.field16 = new Class315.Class2();
         this.field19 = new Class315.Class1();
      }

      static int access$2902(Class315.Class3 var0, int var1) {
         return var0.do_ = var1;
      }

      public final int method1(int var1) {
         return Class315.Class0.access$2400((Class315.Class0)this.field12.get(var1));
      }

      public final int method2() {
         return this.field12.size();
      }

      static List access$2500(Class315.Class3 var0) {
         return var0.field12;
      }

      static int access$2800(Class315.Class3 var0) {
         return var0.dn_;
      }

      public final int method3() {
         return this.dn_;
      }

      private int[] method0(Class315.Class0 var1, int[] var2) {
         Class315.Class1.access$600(this.field19, var1, var2, this.field16);
         Class315.Class2.access$800(this.field16, Class315.Class0.access$700(var1));
         int var3 = Class315.Class0.access$300(var1);
         int var4 = Class315.Class0.access$200(var1);
         int[] var5 = new int[this.field18];
         int[][] var6 = Class315.Class1.access$900(this.field19);
         byte var7 = 0;
         Class315.Class1.access$1000(this.field19);
         Class315.Class2.access$1100(this.field16);
         int var8 = Class315.Class2.access$1100(this.field16);
         int[] var9 = var6[var8];
         System.arraycopy(var9, 0, var5, var7, var9.length);
         int var14 = var7 + var9.length;

         try {
            while(true) {
               while(true) {
                  int var10 = var8;
                  var8 = Class315.Class2.access$1100(this.field16);
                  if (var8 != var3) {
                     if (var8 == var4) {
                        return var5;
                     }

                     int[] var11 = var6[var10];
                     int[] var12 = new int[var11.length + 1];
                     System.arraycopy(var11, 0, var12, 0, var11.length);
                     if (var8 < Class315.Class1.access$1200(this.field19)) {
                        var9 = var6[var8];
                        System.arraycopy(var9, 0, var5, var14, var9.length);
                        var14 += var9.length;
                        var12[var11.length] = var6[var8][0];
                     } else {
                        var12[var11.length] = var11[0];
                        System.arraycopy(var12, 0, var5, var14, var12.length);
                        var14 += var12.length;
                     }

                     Class315.Class1.access$1300(this.field19, var12);
                  } else {
                     Class315.Class1.access$1000(this.field19);
                     var8 = Class315.Class2.access$1100(this.field16);
                     var9 = var6[var8];
                     System.arraycopy(var9, 0, var5, var14, var9.length);
                     var14 += var9.length;
                  }
               }
            }
         } catch (ArrayIndexOutOfBoundsException var13) {
            return var5;
         }
      }

      static int access$2802(Class315.Class3 var0, int var1) {
         return var0.dn_ = var1;
      }
   }

   final class Class0 {
      private int field1;
      private int dm_;
      private int dn_;
      private int field4;
      private int do_;
      final Class315 field0;
      private boolean dp_;
      private BufferedImage dq_;
      private byte[] field8;
      private int field2;
      private int[] field10;
      private int field11;
      private int field12;
      private boolean field13;
      private boolean field14;
      private int field15;
      private int field16;
      private int field17;
      private boolean dr_;
      private int field18;

      static int access$1600(Class315.Class0 var0) {
         return var0.field18;
      }

      static int access$1400(Class315.Class0 var0) {
         return var0.dn_;
      }

      static int access$2300(Class315.Class0 var0) {
         return var0.dm_;
      }

      static BufferedImage access$2200(Class315.Class0 var0) {
         return var0.dq_;
      }

      static int access$1500(Class315.Class0 var0) {
         return var0.do_;
      }

      static int access$2000(Class315.Class0 var0) {
         return var0.field17;
      }

      static int[] access$1800(Class315.Class0 var0) {
         return var0.field10;
      }

      static int access$2102(Class315.Class0 var0, int var1) {
         return var0.field2 = var1;
      }

      static int access$300(Class315.Class0 var0) {
         return var0.field15;
      }

      static int access$2100(Class315.Class0 var0) {
         return var0.field2;
      }

      static int access$200(Class315.Class0 var0) {
         return var0.field4;
      }

      static int[] access$1802(Class315.Class0 var0, int[] var1) {
         return var0.field10 = var1;
      }

      static int access$202(Class315.Class0 var0, int var1) {
         return var0.field4 = var1;
      }

      static int access$2400(Class315.Class0 var0) {
         return var0.field1;
      }

      static boolean access$1902(Class315.Class0 var0, boolean var1) {
         return var0.field13 = var1;
      }

      static byte[] access$700(Class315.Class0 var0) {
         return var0.field8;
      }

      static int access$302(Class315.Class0 var0, int var1) {
         return var0.field15 = var1;
      }

      static boolean access$1702(Class315.Class0 var0, boolean var1) {
         return var0.dr_ = var1;
      }

      static int access$100(Class315.Class0 var0) {
         return var0.field11;
      }

      static int access$2302(Class315.Class0 var0, int var1) {
         return var0.dm_ = var1;
      }

      static int access$102(Class315.Class0 var0, int var1) {
         return var0.field11 = var1;
      }

      static boolean access$1700(Class315.Class0 var0) {
         return var0.dr_;
      }

      static int access$2600(Class315.Class0 var0) {
         return var0.field16;
      }

      static int access$502(Class315.Class0 var0, int var1) {
         return var0.field12 = var1;
      }

      static boolean access$1900(Class315.Class0 var0) {
         return var0.field13;
      }

      static int access$2602(Class315.Class0 var0, int var1) {
         return var0.field16 = var1;
      }

      static byte[] access$702(Class315.Class0 var0, byte[] var1) {
         return var0.field8 = var1;
      }

      static int access$1502(Class315.Class0 var0, int var1) {
         return var0.do_ = var1;
      }

      static BufferedImage access$2202(Class315.Class0 var0, BufferedImage var1) {
         return var0.dq_ = var1;
      }

      static boolean access$400(Class315.Class0 var0) {
         return var0.field14;
      }

      static int access$1602(Class315.Class0 var0, int var1) {
         return var0.field18 = var1;
      }

      static boolean access$2702(Class315.Class0 var0, boolean var1) {
         return var0.dp_ = var1;
      }

      Class0(Class315 var1) {
         this.field0 = var1;
      }

      static boolean access$402(Class315.Class0 var0, boolean var1) {
         return var0.field14 = var1;
      }

      static int access$500(Class315.Class0 var0) {
         return var0.field12;
      }

      static int access$2402(Class315.Class0 var0, int var1) {
         return var0.field1 = var1;
      }

      static int access$2002(Class315.Class0 var0, int var1) {
         return var0.field17 = var1;
      }

      static int access$1402(Class315.Class0 var0, int var1) {
         return var0.dn_ = var1;
      }
   }

   static final class Class1 {
      private final int[][] field0 = new int[4096][1];
      private int field1;
      private int field2;
      private int field3;
      private int field4;
      private int field5;
      private Class315.Class2 field6;
      private int field7;

      static void access$600(Class315.Class1 var0, Class315.Class0 var1, int[] var2, Class315.Class2 var3) {
         var0.method0(var1, var2, var3);
      }

      static int access$1000(Class315.Class1 var0) {
         return var0.method0();
      }

      private void method0(Class315.Class0 var1, int[] var2, Class315.Class2 var3) {
         this.field6 = var3;
         int var4 = var2.length;
         this.field2 = Class315.Class0.access$100(var1);
         this.field1 = (1 << this.field2) - 1;
         this.field4 = Class315.Class0.access$200(var1) + 1;
         this.field5 = this.field4;

         for(int var5 = var4 - 1; var5 >= 0; --var5) {
            this.field0[var5][0] = var2[var5];
         }

         this.field0[Class315.Class0.access$300(var1)] = new int[]{Class315.Class0.access$300(var1)};
         this.field0[Class315.Class0.access$200(var1)] = new int[]{Class315.Class0.access$200(var1)};
         if (Class315.Class0.access$400(var1) && Class315.Class0.access$500(var1) < var4) {
            this.field0[Class315.Class0.access$500(var1)][0] = 0;
         }

      }

      public Class1() {
      }

      static int access$1200(Class315.Class1 var0) {
         return var0.field5;
      }

      static int[][] access$900(Class315.Class1 var0) {
         return var0.field0;
      }

      private int method0() {
         this.field3 = this.field2;
         Class315.Class2.access$000(this.field6, this.field3);
         this.field7 = this.field1;
         this.field5 = this.field4;
         return this.field3;
      }

      private int method0(int[] var1) {
         if (this.field5 < 4096) {
            if (this.field5 == this.field7 && this.field3 < 12) {
               ++this.field3;
               Class315.Class2.access$000(this.field6, this.field3);
               this.field7 = (1 << this.field3) - 1;
            }

            this.field0[this.field5++] = var1;
         }

         return this.field3;
      }

      static int access$1300(Class315.Class1 var0, int[] var1) {
         return var0.method0(var1);
      }
   }

   static final class Class2 {
      private byte[] field0;
      private int field1;
      private int field2;
      private int field3;

      private int method0() {
         int var1 = this.field1 >>> 3;
         int var2 = this.field1 & 7;
         int var3 = this.field0[var1++] & 255;
         int var4 = this.field0[var1++] & 255;
         int var5 = this.field0[var1] & 255;
         int var6 = ((var5 << 8 | var4) << 8 | var3) >>> var2;
         this.field1 += this.field3;
         return var6 & this.field2;
      }

      private void method0(int var1) {
         this.field3 = var1;
         this.field2 = (1 << var1) - 1;
      }

      static void access$000(Class315.Class2 var0, int var1) {
         var0.method0(var1);
      }

      static int access$1100(Class315.Class2 var0) {
         return var0.method0();
      }

      private void method0(byte[] var1) {
         this.field0 = var1;
         this.field1 = 0;
      }

      static void access$800(Class315.Class2 var0, byte[] var1) {
         var0.method0(var1);
      }
   }
}
