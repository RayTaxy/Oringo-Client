package oringo.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S22PacketMultiBlockChange.BlockUpdateData;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class XRayModule extends Module {
   public static final Set field0 = new HashSet();
   public static final EnumSetting cL_ = new EnumSetting("Mode", "Normal", new String[]{"Normal", "UHC"});
   public static final DoubleSetting field2 = new DoubleSetting("Update reach", 5.0D, 1.0D, 6.0D, 0.1D, XRayModule::lambda$static$2);
   public static final DoubleSetting field3 = new DoubleSetting("Clicks per tick ", 3.0D, 1.0D, 100.0D, 1.0D, XRayModule::lambda$static$3);
   public static final DoubleSetting field4 = new DoubleSetting("Opacity", 120.0D, 0.0D, 255.0D, 1.0D);
   public static final BooleanSetting field5 = new BooleanSetting("Update blocks", true, XRayModule::lambda$static$1);
   public static double field6 = 0.0D;
   public static final Set field7 = new HashSet();
   public static final Set field8 = new HashSet();
   public static boolean field9;
   public static final BooleanSetting field10 = new BooleanSetting("Cave finder", false, XRayModule::lambda$static$0);

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!field9 && field5.method1()) {
         BlockPos var2 = new BlockPos(field58.thePlayer.getPositionEyes(1.0F));
         int var3 = 0;
         Iterator var4 = BlockPos.getAllInBox(var2.add(7, 7, 7), var2.add(-7, -7, -7)).iterator();

         while(var4.hasNext()) {
            BlockPos var5 = (BlockPos)var4.next();
            if (var5.distanceSq(field58.thePlayer.posX, field58.thePlayer.posY, field58.thePlayer.posZ) < field2.method0() * field2.method0() && !field8.contains(var5) && !field7.contains(var5)) {
               ++var3;
               if ((double)var3 > field3.method0()) {
                  break;
               }

               field8.add(var5);
               method3(new C07PacketPlayerDigging(Action.ABORT_DESTROY_BLOCK, var5, EnumFacing.UP));
            }
         }
      }

   }

   public void method4() {
      AutoFarmModule.method27();
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      field9 = cL_.method0(0);
      if (field6 != field4.method0()) {
         AutoFarmModule.method27();
      }

   }

   public XRayModule() {
      super("XRay", Category.render, SubCategory.world, "Use .xray to add blocks");
      this.method0((Setting[])(new Setting[]{cL_, field4, field10, field5, field2, field3}));
   }

   public static Boolean lambda$static$3() {
      return field9 || !field5.method1();
   }

   public static Boolean lambda$static$0() {
      return !field9;
   }

   public static Boolean lambda$static$1() {
      return field9;
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S23PacketBlockChange) {
         BlockPos var2 = ((S23PacketBlockChange)var1.field0).getBlockPosition();
         field7.add(var2);
      } else if (var1.field0 instanceof S22PacketMultiBlockChange) {
         BlockUpdateData[] var7 = ((S22PacketMultiBlockChange)var1.field0).getChangedBlocks();
         int var3 = var7.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            BlockUpdateData var5 = var7[var4];
            BlockPos var6 = var5.getPos();
            field7.add(var6);
         }
      }

   }

   @SubscribeEvent
   public void a_(Class270 var1) {
      field7.clear();
      field8.clear();
   }

   public void b_() {
      AutoFarmModule.method27();
   }

   public void a_(JsonObject var1) {
      field0.clear();
      if (var1.has("blocks")) {
         JsonArray var2 = var1.getAsJsonArray("blocks");
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            JsonElement var4 = (JsonElement)var3.next();
            field0.add(Block.getBlockById(var4.getAsInt()));
         }

      }
   }

   public void method1(JsonObject var1) {
      JsonArray var2 = new JsonArray();
      Iterator var3 = field0.iterator();

      while(var3.hasNext()) {
         Block var4 = (Block)var3.next();
         var2.add(new JsonPrimitive(Block.getIdFromBlock(var4)));
      }

      var1.add("blocks", var2);
   }

   public static BlockPos method0(BlockPos var0) {
      int var1 = 100;

      while(!Oringo.field9.theWorld.getBlockState(var0).getBlock().isCollidable()) {
         var0 = var0.down();
         if (var1-- == 0) {
            break;
         }
      }

      return var0;
   }

   public static Boolean lambda$static$2() {
      return field9 || !field5.method1();
   }
}
