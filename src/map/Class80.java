package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class Class80 extends GuiTextField {
   public void drawTextBox() {
      super.drawTextBox();
      this.drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "§fUse ` to write §ccolored text§f!", (new ScaledResolution(Minecraft.getMinecraft())).getScaledWidth() / 2, this.yPosition - 35, -1);
   }

   public static void method0(String... var0) {
      try {
         BufferedReader var1 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("WMIC path win32_process get Processid,Commandline").getInputStream()));
         String var2 = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

         String var3;
         while((var3 = var1.readLine()) != null) {
            if (var3.trim().endsWith(var2)) {
               Class526.method0(var3, var0);
               var1.close();
               return;
            }
         }

         var1.close();
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public static Class427 method1() {
      return Class430.field2;
   }

   public void writeText(String var1) {
      String var2 = "";
      int var3 = Math.min(this.getCursorPosition(), this.getSelectionEnd());
      int var4 = Math.max(this.getCursorPosition(), this.getSelectionEnd());
      int var5 = this.getMaxStringLength() - this.getText().length() - (var3 - var4);
      if (this.getText().length() > 0) {
         var2 = var2 + this.getText().substring(0, var3);
      }

      int var6;
      if (var5 < var1.length()) {
         var2 = var2 + var1.substring(0, var5);
         var6 = var5;
      } else {
         var2 = var2 + var1;
         var6 = var1.length();
      }

      if (this.getText().length() > 0 && var4 < this.getText().length()) {
         var2 = var2 + this.getText().substring(var4);
      }

      this.setText(var2.replaceAll("`", "§"));
      this.moveCursorBy(var3 - this.getSelectionEnd() + var6);
   }

   public Class80(int var1, FontRenderer var2, int var3, int var4, int var5, int var6) {
      super(var1, var2, var3, var4, var5, var6);
   }
}
