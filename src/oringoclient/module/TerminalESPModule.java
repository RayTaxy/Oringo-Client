package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import map.Class21;
import map.Class528;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class TerminalESPModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Mode", "Box", new String[]{"Box", "Tracers"});
   public final DoubleSetting field1;
   public final ColorSetting field2;

   public TerminalESPModule() {
      super("Terminal ESP", Category.dungeon, SubCategory.floor7, "Shows inactive terminals");
      this.field2 = new ColorSetting("Color", Color.MAGENTA, false);
      this.field1 = new DoubleSetting("Distance", 32.0D, 16.0D, 128.0D, 1.0D);
      this.method0((Setting[])(new Setting[]{this.field0, this.field1, this.field2}));
   }

   public static boolean method0(BlockPos var0) {
      return TerminalAuraModule.method0(Class21.field0.theWorld.getBlockState(var0), var0);
   }

   public static boolean method0(int var0, int var1) {
      return TerminatorAuraModule.method0(new BlockPos(var0, 0, var1));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      Iterator var2 = field58.theWorld.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity var3 = (Entity)var2.next();
         if (var3 instanceof EntityArmorStand && var3.getDistanceSqToEntity(field58.thePlayer) <= this.field1.method0() * this.field1.method0()) {
            EntityArmorStand var4 = (EntityArmorStand)var3;
            if (var4.getName().equals("§cInactive Terminal")) {
               if (this.field0.method0(0)) {
                  AutoIceSprayModule.method0((new AxisAlignedBB(-0.35D, 0.0D, -0.35D, 0.35D, 1.75D, 0.35D)).offset(var4.prevPosX + (var4.posX - var4.prevPosX) * (double)var1.partialTicks, var4.prevPosY + (var4.posY - var4.prevPosY) * (double)var1.partialTicks, var4.prevPosZ + (var4.posZ - var4.prevPosZ) * (double)var1.partialTicks), this.field2.method17());
               } else {
                  Class528.method0(var4, var1.partialTicks, 1.5F, this.field2.method17());
               }
            }
         }
      }

   }
}
