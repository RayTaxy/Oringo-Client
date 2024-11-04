package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import map.Class282;
import map.Class361;
import map.Class94;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec4b;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.DebugCommand;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class SimulatorAuraModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Distance", 6.0D, 1.0D, 6.0D, 0.1D);
   public static final HashMap B_ = new HashMap();

   public static boolean lambda$onMotionUpdate$0(String var0) {
      return ChatFormatting.stripFormatting(var0).contains("SIMULATOR");
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (DebugCommand.method0(SimulatorAuraModule::lambda$onMotionUpdate$0)) {
         B_.entrySet().removeIf(SimulatorAuraModule::lambda$onMotionUpdate$1);
         Iterator var2 = field58.theWorld.loadedTileEntityList.iterator();

         while(var2.hasNext()) {
            TileEntity var3 = (TileEntity)var2.next();
            if (var3 instanceof TileEntitySkull && field58.thePlayer.getDistance((double)var3.getPos().getX(), (double)((float)var3.getPos().getY() - field58.thePlayer.getEyeHeight()), (double)var3.getPos().getZ()) < field0.method0() && !B_.containsKey(var3.getPos()) && Class361.method0((Class94)(new Class282(var3.getPos(), new Vec3(var3.getPos()), EnumFacing.DOWN)))) {
               B_.put(var3.getPos(), System.currentTimeMillis());
               break;
            }
         }
      }

   }

   public SimulatorAuraModule() {
      super("Simulator Aura", Category.other, SubCategory.other, "Seasonal simulator mini game aura");
      this.method0((Setting[])(new Setting[]{field0}));
   }

   public static boolean lambda$onMotionUpdate$1(Entry var0) {
      return System.currentTimeMillis() - (Long)var0.getValue() > 500L;
   }

   public static float method0(Vec4b var0) {
      return (float)(var0.func_176111_d() * 360) / 16.0F;
   }
}
