package map;

import java.util.Iterator;
import net.minecraft.util.BlockPos;

final class Class226$1 implements Iterator {
   final BlockPos bN_;
   int field0;

   public boolean hasNext() {
      return this.field0 < Class226.access$000().length;
   }

   public Object next() {
      return this.method0();
   }

   public BlockPos method0() {
      return Class226.access$000()[this.field0++].add(this.bN_);
   }

   Class226$1(BlockPos var1) {
      this.bN_ = var1;
      this.field0 = 0;
   }
}
