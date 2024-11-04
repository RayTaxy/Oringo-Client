package map;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Class351 {
   public final ArrayList field0 = new ArrayList();

   public static void field0(Class354 var0, float var1, float var2, float var3) {
      var0.method0("rectSize", var1, var2);
      var0.method0("radius", var3);
   }

   public void method0(File var1) throws IOException {
      DataOutputStream var2 = new DataOutputStream(new GZIPOutputStream(Files.newOutputStream(var1.toPath())));
      var2.write(this.field0.size());
      Iterator var3 = this.field0.iterator();

      while(var3.hasNext()) {
         Class351.Class0 var4 = (Class351.Class0)var3.next();
         var2.writeInt(Item.getIdFromItem(var4.field3));
         var2.writeInt(var4.field0);
         var2.writeInt(var4.field1.ordinal());
         var2.writeInt(var4.field2.getX());
         var2.writeInt(var4.field2.getY());
         var2.writeInt(var4.field2.getZ());
      }

      var2.close();
   }

   public boolean F_() {
      return this.field0.isEmpty();
   }

   public static void method0(Color var0) {
      GlStateManager.color((float)var0.getRed() / 255.0F, (float)var0.getGreen() / 255.0F, (float)var0.getBlue() / 255.0F, (float)var0.getAlpha() / 255.0F);
   }

   public void field0(Class351.Class0 var1) {
      this.field0.add(var1);
   }

   public Class351 method1() {
      return new Class351(this.field0);
   }

   public Class351(ArrayList var1) {
      this.field0.addAll(var1);
   }

   public ArrayList method2() {
      return this.field0;
   }

   public Class351() {
   }

   public static class Class0 {
      public int field0;
      public EnumFacing field1;
      public BlockPos field2;
      public ItemBlock field3;

      public Class0(BlockPos var1, ItemBlock var2, int var3, EnumFacing var4) {
         this.field2 = var1;
         this.field3 = var2;
         this.field0 = var3;
         this.field1 = var4;
      }
   }
}
