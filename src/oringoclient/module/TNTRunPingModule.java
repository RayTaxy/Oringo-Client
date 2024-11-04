package oringo.module;

import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.command.WardrobeCommand;
import oringo.event.Class16;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class TNTRunPingModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Ping (Ticks)", 100.0D, 1.0D, 100.0D, 1.0D);
   public static final CopyOnWriteArrayList bv_ = new CopyOnWriteArrayList();

   public TNTRunPingModule() {
      super("TNT Run ping", 0, Category.other, SubCategory.other, (String)null);
      this.method0((Setting[])(new Setting[]{this.field0}));
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (!bv_.isEmpty()) {
         bv_.removeIf(this::lambda$onTick$0);
      }
   }

   public boolean lambda$onTick$0(TNTRunPingModule.Class0 var1) {
      return (double)(++var1.Y_) >= this.field0.method0();
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (var1.field2.getBlock() == Blocks.air && var1.field0.getBlock() != Blocks.wool) {
         if (!WardrobeCommand.i_("TNT RUN") && !WardrobeCommand.i_("PVP RUN")) {
            return;
         }

         bv_.add(new TNTRunPingModule.Class0(var1.cB_, 0));
      }

   }

   public static class Class0 {
      public final BlockPos field0;
      public int Y_;

      public Class0(BlockPos var1, int var2) {
         this.field0 = var1;
         this.Y_ = var2;
      }
   }
}
