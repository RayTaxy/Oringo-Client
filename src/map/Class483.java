package map;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.Vec3;

public abstract class Class483 {
   public boolean field6 = false;
   public List field7 = new ArrayList();

   public void method0(Predicate var1) {
      if (this.field7.removeIf(var1)) {
         this.field6 = true;
      }

   }

   public void method2() {
      this.method0(7, Class278.field1, Class278.field3);
   }

   public void method3() {
      this.method0(1, Class278.field0, Class278.field2);
   }

   public abstract void method0(int var1, int var2, VertexBuffer var3);

   public void method0(List var1) {
      if (!this.method0(var1, this.field7)) {
         this.method5();
         this.field7.addAll(var1);
      }
   }

   public void method1(List var1) {
      this.field6 = true;
      this.field7 = var1;
   }

   public boolean method0(List var1, List var2) {
      if (var1 == var2) {
         return true;
      } else {
         ListIterator var3 = var1.listIterator();
         ListIterator var4 = var2.listIterator();

         while(true) {
            if (var3.hasNext() && var4.hasNext()) {
               Vec3 var5 = (Vec3)var3.next();
               Vec3 var6 = (Vec3)var4.next();
               if (var5.xCoord == var6.xCoord && var5.yCoord == var6.yCoord && var5.zCoord == var6.zCoord) {
                  continue;
               }

               return false;
            }

            return !var3.hasNext() && !var4.hasNext();
         }
      }
   }

   public void method0(Vec3 var1) {
      this.field6 = true;
      this.field7.add(var1);
   }

   public void method1(Vec3 var1) {
      this.field6 = true;
      this.field7.remove(var1);
   }

   public boolean F_() {
      return this.field7.isEmpty();
   }

   public void method5() {
      this.field6 = true;
      this.field7.clear();
   }
}
