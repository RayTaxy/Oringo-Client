package map;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import oringo.module.Category;
import oringo.module.Module;
import oringo.module.SubCategory;

public abstract class Class408 extends Module {
   public int field12;
   public boolean field13;

   public void method6() {
      this.field12 = 0;
      this.field13 = false;
   }

   public boolean method7() {
      return this.field13;
   }

   public boolean method0(boolean var1) {
      if (this.field12 > 0) {
         --this.field12;
         if (var1) {
            this.field12 = 0;
         }

         return true;
      } else {
         return false;
      }
   }

   public static List method1(ItemStack var0) {
      ArrayList var1 = new ArrayList();
      if (var0 == null) {
         return var1;
      } else {
         NBTTagCompound var2 = var0.getTagCompound();
         if (var2 == null) {
            return var1;
         } else {
            if (var2.hasKey("display")) {
               NBTTagCompound var3 = var2.getCompoundTag("display");
               if (var3.hasKey("Lore", 9)) {
                  NBTTagList var4 = var3.getTagList("Lore", 8);
                  if (var4.tagCount() > 0) {
                     for(int var5 = 0; var5 < var4.tagCount(); ++var5) {
                        var1.add(var4.getStringTagAt(var5));
                     }
                  }
               }
            }

            return var1;
         }
      }
   }

   public boolean method8() {
      return this.method0(true);
   }

   public void method9() {
      if (this.method44()) {
         ++this.field12;
         this.field13 = !this.field13;
      }
   }

   public boolean method1() {
      boolean var10000;
      label25: {
         if (!this.method8()) {
            if (this.field57 == 0) {
               break label25;
            }

            if (this.field57 < 0) {
               if (!Mouse.isButtonDown(this.field57 + 100)) {
                  break label25;
               }
            } else if (!Keyboard.isKeyDown(this.field57)) {
               break label25;
            }
         }

         if (this.method44() && field58.currentScreen == null) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public Class408(String var1, Category var2, SubCategory var3, String var4) {
      super(var1, var2, var3, var4);
   }

   public Class408(String var1, int var2, Category var3, SubCategory var4, String var5) {
      super(var1, var2, var3, var4, var5);
   }

   public void method1(boolean var1) {
      super.method1(var1);
      this.field12 = 0;
      this.field13 = false;
   }

   public static double method0(double var0, double var2, float var4) {
      return var0 + (var2 - var0) * (double)var4;
   }
}
