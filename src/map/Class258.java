package map;

import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockReed;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public enum Class258 {
   field0("Carrot", new Block[]{Blocks.carrots}, 3, 93, 0.0F, 3.0F, new Class325(), Class258::lambda$static$1),
   field1("Sugar cane", new BlockReed[]{Blocks.reeds}, 4, 327, -45.0F, 0.0F, new Class515(), Class258::lambda$static$5);

   private final Block[] field8;
   private static final Class258[] field9 = new Class258[]{field2, field0, field7, field5, field3, field1, field4, field6};
   private final Predicate field10;
   field2("Potato", new Block[]{Blocks.potatoes}, 3, 93, 0.0F, 3.0F, new Class325(), Class258::lambda$static$0),
   field3("Nether wart", new Block[]{Blocks.nether_wart}, 4, 93, 0.0F, 0.0F, new Class325(), Class258::lambda$static$4);

   private final float field11;
   private final String dl_;
   private final float field13;
   private final int field14;
   field4("Cocoa Beans", new Block[]{Blocks.cocoa}, 3, 155, 0.0F, -45.0F, new Class218(), Class258::lambda$static$6);

   private final int field15;
   field5("Mushroom", new Block[]{Blocks.red_mushroom, Blocks.brown_mushroom}, 10, 93, 0.0F, 0.0F, new Class325(), Class258::lambda$static$3),
   field6("Cactus", new Block[]{Blocks.cactus}, 3, 400, 0.0F, 0.0F, new Class524(), Class258::lambda$static$7),
   field7("Wheat", new Block[]{Blocks.wheat}, 6, 93, 0.0F, 0.0F, new Class325(), Class258::lambda$static$2);

   private final Class220 field12;

   private Class258(String var3, Block[] var4, int var5, int var6, float var7, float var8, Class220 var9, Predicate var10) {
      this.dl_ = var3;
      this.field8 = var4;
      this.field15 = var5;
      this.field14 = var6;
      this.field13 = var7;
      this.field11 = var8;
      this.field12 = var9;
      this.field10 = var10;
   }

   public float x_() {
      return this.field11;
   }

   public int method1() {
      return this.field14;
   }

   private static boolean lambda$static$7(ItemStack var0) {
      return var0.getItem() instanceof ItemHoe;
   }

   private static boolean lambda$static$0(ItemStack var0) {
      return var0.getItem() instanceof ItemHoe;
   }

   private static boolean lambda$static$2(ItemStack var0) {
      return var0.getItem() instanceof ItemHoe;
   }

   private static boolean lambda$static$4(ItemStack var0) {
      return var0.getItem() instanceof ItemHoe;
   }

   public Class220 method2() {
      return this.field12;
   }

   private static boolean lambda$static$1(ItemStack var0) {
      return var0.getItem() instanceof ItemHoe;
   }

   private static boolean lambda$static$6(ItemStack var0) {
      return var0.getItem() instanceof ItemAxe;
   }

   public Class34 method0(EnumFacing var1) {
      return new Class34((float)((double)this.method1(var1) + Class427.method1(0.05D, -0.05D)), (float)((double)this.x_() + Class427.method1(0.05D, -0.05D)));
   }

   public boolean method0(ItemStack var1) {
      return this.field10.test(var1);
   }

   private static boolean lambda$static$5(ItemStack var0) {
      return var0.getItem() instanceof ItemHoe;
   }

   private static boolean lambda$static$3(ItemStack var0) {
      return var0.getItem() instanceof ItemHoe;
   }

   public boolean method0(Block var1) {
      Block[] var2 = this.field8;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Block var5 = var2[var4];
         if (var1 == var5) {
            return true;
         }
      }

      return false;
   }

   public String method0() {
      return this.dl_;
   }

   public float method1(EnumFacing var1) {
      return this.field13 + 90.0F * (float)var1.getHorizontalIndex();
   }

   public static Class258 method0(String var0) {
      Class258[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Class258 var4 = var1[var3];
         if (var4.method0().equals(var0)) {
            return var4;
         }
      }

      return null;
   }

   public int method4() {
      return this.field15;
   }
}
