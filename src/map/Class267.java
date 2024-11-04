package map;

import java.io.IOException;
import java.net.Proxy.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.BlockPos;
import oringo.Oringo;
import oringo.module.BrushModule;
import oringo.setting.StringSetting;

public class Class267 extends GuiScreen {
   public GuiTextField field0;
   public final Predicate field1 = Class267::lambda$new$0;
   public GuiTextField field2;
   public GuiTextField field3;
   public GuiTextField field4;

   public void initGui() {
      super.initGui();
      byte var1 = 20;
      byte var2 = 4;
      byte var3 = 100;
      this.buttonList.add(new GuiButton(0, this.width / 2 - var3 - var2 / 2, this.height / 2 - var1 / 2 - var1 - var2, var3, var1, Oringo.field0.method1() ? "Enabled" : "Disabled"));
      this.buttonList.add(new GuiButton(1, this.width / 2 + var2 / 2, this.height / 2 - var1 / 2 - var1 - var2, var3, var1, "Type: " + (Oringo.field0.method4() == Type.SOCKS ? "Socks" : "HTTP")));
      this.field0 = new GuiTextField(2, this.fontRendererObj, this.width / 2 - var3 - var2 / 2 + 1, this.height / 2 + 1 - var1 / 2 + 1 + this.fontRendererObj.FONT_HEIGHT, var3 - 1, var1 - 1);
      this.field0.setText(Oringo.field0.m_());
      this.field2 = new GuiTextField(3, this.fontRendererObj, this.width / 2 + var2 / 2 + 1, this.height / 2 + 1 - var1 / 2 + 1 + this.fontRendererObj.FONT_HEIGHT, var3 - 1, var1 - 1);
      if (Oringo.field0.method0() != 0) {
         this.field2.setText(String.valueOf(Oringo.field0.method0()));
      }

      Predicate var10001 = this.field1;
      this.field2.setValidator(var10001::test);
      this.field3 = new GuiTextField(4, this.fontRendererObj, this.width / 2 - var3 - var2 / 2 + 1, this.height / 2 + 1 - var1 / 2 + 2 + this.fontRendererObj.FONT_HEIGHT * 2 + var1 + var2, var3 - 1, var1 - 1);
      this.field3.setText(Oringo.field0.method2());
      this.field4 = new GuiTextField(5, this.fontRendererObj, this.width / 2 + var2 / 2 + 1, this.height / 2 + 1 - var1 / 2 + 2 + this.fontRendererObj.FONT_HEIGHT * 2 + var1 + var2, var3 - 1, var1 - 1);
      this.field4.setText(Oringo.field0.method3());
      this.field3.setVisible(Oringo.field0.method4() == Type.SOCKS);
      this.field4.setVisible(Oringo.field0.method4() == Type.SOCKS);
   }

   public static boolean lambda$new$0(String var0) {
      return var0.matches("^\\d*$");
   }

   public void actionPerformed(GuiButton var1) throws IOException {
      switch(var1.id) {
      case 0:
         Oringo.field0.method0(!Oringo.field0.method1());
         var1.displayString = Oringo.field0.method1() ? "Enabled" : "Disabled";
         break;
      case 1:
         Oringo.field0.method0(Oringo.field0.method4() == Type.HTTP ? Type.SOCKS : Type.HTTP);
         var1.displayString = "Type: " + (Oringo.field0.method4() == Type.SOCKS ? "Socks" : "HTTP");
         this.field3.setVisible(Oringo.field0.method4() == Type.SOCKS);
         this.field4.setVisible(Oringo.field0.method4() == Type.SOCKS);
      }

   }

   public void keyTyped(char var1, int var2) throws IOException {
      if (this.field4.getVisible()) {
         this.field4.textboxKeyTyped(var1, var2);
         Oringo.field0.method1(this.field4.getText());
      }

      if (this.field3.getVisible()) {
         this.field3.textboxKeyTyped(var1, var2);
         Oringo.field0.method0(this.field3.getText());
      }

      this.field0.textboxKeyTyped(var1, var2);
      Oringo.field0.method2(this.field0.getText());
      this.field2.textboxKeyTyped(var1, var2);
      Oringo.field0.method0(this.field2.getText().isEmpty() ? 0 : Integer.parseInt(this.field2.getText()));
      super.keyTyped(var1, var2);
   }

   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.fontRendererObj.drawString("IP", this.field0.xPosition, this.field0.yPosition - this.fontRendererObj.FONT_HEIGHT - 1, -1);
      this.field0.drawTextBox();
      this.fontRendererObj.drawString("Port", this.field2.xPosition, this.field2.yPosition - this.fontRendererObj.FONT_HEIGHT - 1, -1);
      this.field2.drawTextBox();
      if (this.field3.getVisible()) {
         this.fontRendererObj.drawString("Username", this.field3.xPosition, this.field3.yPosition - this.fontRendererObj.FONT_HEIGHT - 1, -1);
         this.field3.drawTextBox();
      }

      if (this.field4.getVisible()) {
         this.fontRendererObj.drawString("Password", this.field4.xPosition, this.field4.yPosition - this.fontRendererObj.FONT_HEIGHT - 1, -1);
         this.field4.drawTextBox();
      }

      super.drawScreen(var1, var2, var3);
   }

   public static Class1 method0(BlockPos var0, Class12 var1, Class208 var2) {
      Class1 var5;
      if (var2 != null && var2.field3 != null) {
         ConcurrentHashMap var6 = var2.field3.field1;
         BlockPos var7 = var2.method0(var0.add(-var1.method1(), 0, -var1.method0()), true);
         var5 = (Class1)var6.get(var7);
         if (var5 != null) {
            return var5;
         } else {
            var5 = new Class1();
            var6.put(var7, var5);
            BrushModule.method7().field0(var0, var5);
            return var5;
         }
      } else {
         int var3 = Class198.field0.method5();
         Map var4 = StringSetting.method0(var3);
         var5 = (Class1)var4.get(var0);
         if (var5 != null) {
            return var5;
         } else {
            var5 = new Class1();
            var4.put(var0, var5);
            BrushModule.method7().field0(var0, var5);
            return var5;
         }
      }
   }

   public void mouseClicked(int var1, int var2, int var3) throws IOException {
      if (this.field4.getVisible()) {
         this.field4.mouseClicked(var1, var2, var3);
      }

      if (this.field3.getVisible()) {
         this.field3.mouseClicked(var1, var2, var3);
      }

      this.field0.mouseClicked(var1, var2, var3);
      this.field2.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }
}
