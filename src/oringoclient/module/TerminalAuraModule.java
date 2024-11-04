package oringo.module;

import java.util.Iterator;
import map.Class142;
import map.Class208;
import map.Class21;
import map.Class361;
import map.Class417;
import map.Class447;
import map.Class46;
import map.Class469;
import map.Class496;
import map.Class94;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.MessageCommand;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class TerminalAuraModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Only Ground", true);
   public final DoubleSetting cS_ = new DoubleSetting("Terminal Reach ", 5.0D, 2.0D, 6.0D, 0.1D);
   public final Class447 field2 = new Class447();
   public final DoubleSetting field3 = new DoubleSetting("Fov", 360.0D, 1.0D, 360.0D, 1.0D);

   public double method0(EntityArmorStand var1) {
      return field58.thePlayer.getDistance(var1.posX, var1.posY - (double)field58.thePlayer.getEyeHeight(), var1.posZ);
   }

   public boolean lambda$onTick$0(EntityArmorStand var1) {
      return var1.posY < 150.0D && var1.getName().contains("CLICK HERE") && Class417.method0(MessageCommand.method0((Entity)var1), (float)this.field3.method0());
   }

   public static boolean method0(IBlockState var0, BlockPos var1) {
      return var0.getBlock().getCollisionBoundingBox(Class21.field0.theWorld, var1, var0) == null;
   }

   public static boolean field0(Class46 var0) {
      Class208 var1 = IceFillHelperModule.method0((int)Class469.field0.thePlayer.posX, (int)Class469.field0.thePlayer.posZ);
      return var1 != null && var1.method5() == var0;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class496.field5 && this.field2.a_(300L)) {
         if (!field58.playerController.getIsHittingBlock()) {
            if ((field58.thePlayer.onGround || !this.field0.method1()) && RenderChunkBoundsModule.method5() && !field58.thePlayer.isInLava()) {
               Iterator var2 = field58.theWorld.getEntities(EntityArmorStand.class, this::lambda$onTick$0).iterator();

               while(var2.hasNext()) {
                  EntityArmorStand var3 = (EntityArmorStand)var2.next();
                  if (this.method0(var3) < this.cS_.method0()) {
                     Class361.method0((Class94)(new Class142(var3)));
                     this.field2.o_();
                     break;
                  }
               }
            }

         }
      }
   }

   public TerminalAuraModule() {
      super("Terminal Aura", 0, Category.dungeon, SubCategory.floor7, "Opens terminals automatically");
      this.method0((Setting[])(new Setting[]{this.cS_, this.field3, this.field0}));
   }
}
