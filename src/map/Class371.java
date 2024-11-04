package map;

import java.io.PrintStream;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Class371 extends PrintStream {
   private final Logger field0;
   private static final int field1 = 3;

   public static void method0() {
      System.setOut(new Class371(LogManager.getLogger("STDOUT"), System.out));
      System.setErr(new Class371(LogManager.getLogger("STDERR"), System.err));
   }

   public void println(boolean var1) {
      this.field0.info(this.method1() + var1);
   }

   public PrintStream printf(Locale var1, String var2, Object... var3) {
      this.field0.info(this.method1() + String.format(var1, var2, var3));
      return this;
   }

   public void println(char var1) {
      this.field0.info(this.method1() + var1);
   }

   public void println(String var1) {
      this.field0.info(this.method1() + var1);
   }

   public PrintStream printf(String var1, Object... var2) {
      this.field0.info(this.method1() + String.format(Locale.getDefault(), var1, var2));
      return this;
   }

   private String method1() {
      StackTraceElement[] var1 = Thread.currentThread().getStackTrace();
      StackTraceElement var2 = var1[3];
      if (var2.getClassName().startsWith("kotlin.io.")) {
         var2 = var1[5];
      } else if (var2.getClassName().startsWith("java.lang.Throwable")) {
         var2 = var1[7];
      }

      return !var2.getClassName().isEmpty() && var2.getClassName().charAt(0) > 'x' ? "[Oringo Client]: " : "[" + var2.getClassName() + ":" + var2.getMethodName() + ":" + var2.getLineNumber() + "]: ";
   }

   public void println(long var1) {
      this.field0.info(this.method1() + var1);
   }

   public void println() {
      this.field0.info("");
   }

   public void println(float var1) {
      this.field0.info(this.method1() + var1);
   }

   public void println(int var1) {
      this.field0.info(this.method1() + var1);
   }

   public Class371(Logger var1, PrintStream var2) {
      super(var2);
      this.field0 = var1;
   }

   public void println(Object var1) {
      this.field0.info(this.method1() + var1);
   }

   public void println(double var1) {
      this.field0.info(this.method1() + var1);
   }

   public void println(char[] var1) {
      this.field0.info(this.method1() + String.valueOf(var1));
   }
}
