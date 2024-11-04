package oringo.module;

import io.netty.buffer.Unpooled;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import map.Class12;
import map.Class208;
import map.Class25;
import map.Class479;
import map.Class496;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class217;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class IceFillHelperModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Ice slowdown", 0.15D, 0.05D, 1.0D, 0.05D);
   public final BooleanSetting field1 = new BooleanSetting("Auto stop", true);
   public final BooleanSetting field2 = new BooleanSetting("No ice slip", true);

   public static void method0(AxisAlignedBB var0) {
      Class479.field3.begin(7, DefaultVertexFormats.POSITION);
      Class479.field3.pos(var0.minX, var0.minY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.minY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.minY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.minX, var0.minY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.minX, var0.maxY, var0.minZ).endVertex();
      Class479.field3.pos(var0.minX, var0.maxY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.maxY, var0.minZ).endVertex();
      Class479.field3.pos(var0.minX, var0.minY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.minX, var0.maxY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.minX, var0.maxY, var0.minZ).endVertex();
      Class479.field3.pos(var0.minX, var0.minY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.minY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.maxY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.minY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.minX, var0.minY, var0.minZ).endVertex();
      Class479.field3.pos(var0.minX, var0.maxY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.maxY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.minY, var0.minZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.minY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.minX, var0.maxY, var0.maxZ).endVertex();
      Class479.field3.pos(var0.minX, var0.minY, var0.maxZ).endVertex();
      Class479.field1.draw();
   }

   public static Class208 method0(int var0, int var1) {
      Class12 var2 = FragHelperModule.method0(var0, var1);
      return Class25.method0((var2.method1() - -185) / 32, (var2.method0() - -185) / 32);
   }

   public void b_() {
      Blocks.packed_ice.slipperiness = 0.98F;
      Blocks.ice.slipperiness = 0.98F;
   }

   public static void method0(byte[] var0) {
      if (PacketLoggerModule.field0 != null) {
         int var1 = (new PacketBuffer(Unpooled.wrappedBuffer(var0))).readVarIntFromBuffer();
         if (!PacketLoggerModule.aw_.contains(var1)) {
            try {
               PacketLoggerModule.field0.write(var0);
            } catch (IOException var3) {
               throw new RuntimeException(var3);
            }
         }
      }
   }

   public static void n_() {
      try {
         DataOutputStream var0 = new DataOutputStream(Files.newOutputStream(Paths.get("config/OringoClient/InventoryManager.cfg")));
         var0.writeInt(InventoryManagerModule.field18.size());
         Iterator var1 = InventoryManagerModule.field18.iterator();

         while(var1.hasNext()) {
            String var2 = (String)var1.next();
            var0.writeUTF(var2);
         }

         var0.close();
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   @SubscribeEvent
   public void method0(Class217 var1) {
      if (field58.thePlayer.onGround && Class496.field5) {
         if (this.field2.method1()) {
            Blocks.packed_ice.slipperiness = 0.6F;
            Blocks.ice.slipperiness = 0.6F;
         } else {
            Blocks.packed_ice.slipperiness = 0.98F;
            Blocks.ice.slipperiness = 0.98F;
         }

         BlockPos var2 = (new BlockPos(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ)).down();
         if (field58.theWorld.getBlockState(var2).getBlock() == Blocks.ice) {
            var1.method1(var1.method2() * this.field0.method0());
            var1.method2(var1.w_() * this.field0.method0());
            field58.thePlayer.motionX = var1.w_();
            field58.thePlayer.motionZ = var1.method2();
            BlockPos var3 = new BlockPos(field58.thePlayer.posX + var1.w_(), field58.thePlayer.posY - 0.4D, field58.thePlayer.posZ + var1.method2());
            if (this.field1.method1() && !var2.equals(var3) && field58.theWorld.getBlockState(var3).getBlock() == Blocks.ice) {
               var1.method1(0.0D);
               var1.method2(0.0D);
            }
         }

      }
   }

   public IceFillHelperModule() {
      super("Ice Fill Helper", Category.dungeon, SubCategory.puzzle, "Helps you with the ice fill puzzle");
      this.method0((Setting[])(new Setting[]{this.field1, this.field0, this.field2}));
   }
}
