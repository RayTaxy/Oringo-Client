package map;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.Iterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.scoreboard.Score;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import oringo.Oringo;
import oringo.event.Class217;
import oringo.setting.KeyBindSetting;

public class Class81 extends Class122 {
   public boolean field0;

   public boolean method0(char var1, int var2) {
      if (!this.field0) {
         return false;
      } else {
         ((KeyBindSetting)this.field1).method3(var2 != 1 && var2 != Class362.field7.method46() ? var2 : 0);
         this.field0 = false;
         return true;
      }
   }

   public double method0() {
      return (double)Class311.field16.method3();
   }

   public Class81(KeyBindSetting var1) {
      super(var1);
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      String var4 = this.field0 ? "..." : (((KeyBindSetting)this.field1).method46() >= 256 ? "" : (((KeyBindSetting)this.field1).method46() < 0 ? Mouse.getButtonName(((KeyBindSetting)this.field1).method46() + 100) : Keyboard.getKeyName(((KeyBindSetting)this.field1).method46())));
      if (var4 == null) {
         var4 = "UNKNOWN";
      } else if (var4.equals("NONE")) {
         var4 = "   ";
      }

      double var5 = this.method6();
      Class311.field16.method1(((KeyBindSetting)this.field1).cW_, (float)var5, (float)(var1 + (this.method0() - (double)Class311.field16.method0()) / 2.0D), Color.white.getRGB());
      String var7 = "[" + var4 + "]";
      double var8 = (double)Class311.field16.method0(var7);
      Class311.field16.method1(var7, (float)(var5 + 148.003D - var8), (float)(var1 + (this.method0() - (double)Class311.field16.method0()) / 2.0D), Color.white.getRGB());
   }

   public static boolean method0(ItemStack var0) {
      return var0.getItem() instanceof ItemSword && (var0.getDisplayName().contains("Hyperion") || var0.getDisplayName().contains("Astraea") || var0.getDisplayName().contains("Scylla") || var0.getDisplayName().contains("Valkyrie"));
   }

   public void q_() {
      this.field0 = false;
   }

   public void method0(int var1) {
   }

   public void method1(int var1) {
      double var2 = this.method6();
      if (this.field0) {
         ((KeyBindSetting)this.field1).method3(var1 - 100);
         this.field0 = false;
      } else {
         if (Class217.method0(var2, this.field14, 148.003D, this.method0())) {
            if (var1 == 1) {
               ((KeyBindSetting)this.field1).method3(0);
            } else {
               this.field0 = true;
            }
         }

      }
   }

   public boolean method2() {
      return true;
   }

   public void method3() {
   }

   public static boolean method0(EntityPlayer var0) {
      Iterator var1 = Oringo.field9.thePlayer.getWorldScoreboard().getScores().iterator();

      Score var2;
      do {
         if (!var1.hasNext()) {
            return false;
         }

         var2 = (Score)var1.next();
      } while(!var2.getObjective().getName().equals("health") || !var2.getPlayerName().contains(var0.getName()));

      return true;
   }

   public static Clipboard method0(Toolkit var0) {
      return var0.getSystemClipboard();
   }
}
