package oringo.command;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class351;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class332;
import oringo.module.AutoIceSprayModule;
import oringo.module.PrinterModule;
import oringo.module.ThystHiderModule;

public class BlueprintCommand extends Command {
   public boolean field0 = false;
   public Class351 ag_ = null;
   public BlockPos field2 = null;
   public BlockPos field3 = null;
   public BlockPos field4 = null;

   public void method2() {
      this.field0 = false;
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C08PacketPlayerBlockPlacement && this.field0) {
         C08PacketPlayerBlockPlacement var2 = (C08PacketPlayerBlockPlacement)var1.field0;
         ItemStack var3 = var2.getStack();
         Item var4 = var3.getItem();
         if (var2.getPosition() == null || !(var4 instanceof ItemBlock) || var2.getPlacedBlockDirection() == 255) {
            return;
         }

         EnumFacing var5 = EnumFacing.getFront(var2.getPlacedBlockDirection());
         BlockPos var6 = var2.getPosition().offset(var5);
         if (this.field2 == null) {
            this.field2 = var6;
         }

         this.ag_.field0(new Class351.Class0(var6.subtract(this.field2), (ItemBlock)var4, var3.getMetadata(), var5));
      }

   }

   public static ArrayList method0(String var0, Pattern var1, int var2) {
      ArrayList var3 = new ArrayList();
      Matcher var4 = var1.matcher(var0);

      while(var4.find()) {
         var3.add(var4.group(var2));
      }

      return var3;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field4 != null) {
         BlockPos var2 = this.field3 == null ? this.field4 : this.field3;
         AutoIceSprayModule.method0((new AxisAlignedBB((double)this.field4.getX() + 0.5D, (double)this.field4.getY() + 0.5D, (double)this.field4.getZ() + 0.5D, (double)var2.getX() + 0.5D, (double)var2.getY() + 0.5D, (double)var2.getZ() + 0.5D)).expand(0.5D, 0.5D, 0.5D), Color.GREEN);
      }
   }

   public String method1() {
      return null;
   }

   public void method3() {
      this.ag_ = new Class351(new ArrayList());
      this.field2 = null;
      this.field0 = true;
   }

   public BlueprintCommand() {
      super("blueprint", "bp");
   }

   public void method0(String[] var1) throws Exception {
      if (var1.length != 0) {
         String var2 = var1[0];
         byte var3 = -1;
         switch(var2.hashCode()) {
         case 3521:
            if (var2.equals("p1")) {
               var3 = 0;
            }
            break;
         case 3522:
            if (var2.equals("p2")) {
               var3 = 1;
            }
            break;
         case 100571:
            if (var2.equals("end")) {
               var3 = 4;
            }
            break;
         case 3059573:
            if (var2.equals("copy")) {
               var3 = 2;
            }
            break;
         case 109757538:
            if (var2.equals("start")) {
               var3 = 3;
            }
            break;
         case 1280882667:
            if (var2.equals("transfer")) {
               var3 = 5;
            }
         }

         switch(var3) {
         case 0:
            this.field4 = ThystHiderModule.method3();
            break;
         case 1:
            this.field3 = ThystHiderModule.method3();
            break;
         case 2:
            Class351 var4 = new Class351();
            Iterator var5 = BlockPos.getAllInBox(this.field4, this.field3).iterator();

            while(var5.hasNext()) {
               BlockPos var6 = (BlockPos)var5.next();
               IBlockState var7 = field9.theWorld.getBlockState(var6);
               if (Item.getItemFromBlock(var7.getBlock()) instanceof ItemBlock && var7.getBlock() != Blocks.air && var7.getBlock().getDefaultState() != null) {
                  var4.field0(new Class351.Class0(var6.subtract(this.field4), (ItemBlock)Item.getItemFromBlock(var7.getBlock()), var7.getBlock().getMetaFromState(var7), EnumFacing.UP));
               }
            }

            this.ag_ = var4;
            PrinterModule.ag_ = var4;
            this.field4 = null;
            this.field3 = null;
            break;
         case 3:
            this.method3();
            break;
         case 4:
            this.method2();
            break;
         case 5:
            PrinterModule.ag_ = this.ag_.method1();
         }

      }
   }
}
