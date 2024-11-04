package map;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.event.Class16;
import oringo.event.Class270;
import oringo.event.Class329;
import oringo.module.Category;
import oringo.module.SubCategory;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class Class490 extends Class408 {
   public final EnumSetting field0 = new EnumSetting("Mode", "Keybind", new String[]{"Pickaxe held"});
   public final Map field1 = new HashMap();
   public final DoubleSetting field2 = (new DoubleSetting("Delay", 300.0D, 100.0D, 1000.0D, 1.0D)).method0("ms");

   public void lambda$onTick$0(List var1, BlockPos var2, Class490.Class0 var3) {
      if (var3.a_((long)this.field2.method3())) {
         var1.add(var2);
         if (var3.method1()) {
            field58.theWorld.setBlockState(var2, Class490.Class0.access$100(var3));
         }
      }
   }

   public static boolean method0(Entity var0) {
      if (var0 instanceof EntityZombie) {
         EntityZombie var1 = (EntityZombie)var0;
         if (var1.isChild() && var1.getCurrentArmor(0) != null && var1.getCurrentArmor(1) != null && var1.getCurrentArmor(2) != null && var1.getCurrentArmor(3) != null) {
            ItemStack var2 = var1.getCurrentArmor(3);
            return var2.getItem() instanceof ItemSkull && "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNiYmY0OGI2Zjk5NTI5OGZmYzhkNTVkM2M4MTgzYWUxYWVmOGEyYTlkNDIxN2MzMmVmNjljODY4ODMyM2IifX19".equals(Class98.method0(var2));
         }
      }

      return false;
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START && field58.theWorld != null) {
         ArrayList var2 = Lists.newArrayList();
         this.field1.forEach(this::lambda$onTick$0);
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            BlockPos var4 = (BlockPos)var3.next();
            this.field1.remove(var4);
         }

      }
   }

   public Class490() {
      super("Stonk Delay", Category.dungeon, SubCategory.main, "Delays blocks from coming back when the keybind is held");
      this.method0((Setting[])(new Setting[]{this.field0, this.field2}));
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      Class490.Class0 var2 = (Class490.Class0)this.field1.get(var1.cB_);
      if (var2 != null && !var2.a_((long)this.field2.method3()) && (var2.method0().getBlock() != var1.field0.getBlock() || var1.field2.getBlock() != Blocks.air)) {
         if (var2.method0().getBlock() != var1.field2.getBlock()) {
            this.field1.remove(var1.cB_);
         } else {
            var2.method0(true);
            var1.method9();
         }
      }
   }

   @SubscribeEvent
   public void method0(Class329 var1) {
      if (this.method1() || !this.field0.method0(-1)) {
         if (!this.field0.method0(0) || field58.thePlayer.getHeldItem() == null || !(field58.thePlayer.getHeldItem().getItem() instanceof ItemPickaxe)) {
            this.field1.put(var1.method0(), new Class490.Class0(var1.method1(), (Class490$1)null));
         }
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field1.clear();
   }

   public static boolean method0(Class258 var0, BlockPos var1) {
      // $FF: Couldn't be decompiled
   }

   public static final class Class0 {
      private final IBlockState field0;
      private boolean field1;
      private final long field2;

      Class0(IBlockState var1, Class490$1 var2) {
         this(var1);
      }

      public IBlockState method0() {
         return this.field0;
      }

      public boolean method1() {
         return this.field1;
      }

      private Class0(IBlockState var1) {
         this.field2 = System.currentTimeMillis();
         this.field0 = var1;
      }

      public void method0(boolean var1) {
         this.field1 = var1;
      }

      static IBlockState access$100(Class490.Class0 var0) {
         return var0.field0;
      }

      public boolean a_(long var1) {
         return System.currentTimeMillis() - this.field2 > var1;
      }
   }
}
