package oringo.module;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import map.Class176;
import map.Class34;
import map.Class352;
import map.Class417;
import map.Class447;
import map.Class528;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.MessageCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AntiNukekubiModule extends Module {
   public static final BooleanSetting field0 = new BooleanSetting("Attack with aura", true);
   public final Class447 cJ_ = new Class447();
   public static final BooleanSetting field2 = new BooleanSetting("Tracer", true);
   public static final DoubleSetting field3 = new DoubleSetting("Fov", 360.0D, 1.0D, 360.0D, 1.0D);
   public static final DoubleSetting field4 = new DoubleSetting("Timeout", 100.0D, 10.0D, 250.0D, 1.0D);
   public static final Set field5 = new HashSet();
   public static EntityArmorStand field6;
   public static final DoubleSetting field7 = new DoubleSetting("Distance", 10.0D, 5.0D, 20.0D, 1.0D);

   public static boolean method0(double var0, double var2) {
      BlockPos var4 = new BlockPos(Oringo.field9.thePlayer.posX, Oringo.field9.thePlayer.posY, Oringo.field9.thePlayer.posZ);
      if (!Oringo.field9.theWorld.isBlockLoaded(var4)) {
         return false;
      } else {
         AxisAlignedBB var5 = Oringo.field9.thePlayer.getEntityBoundingBox().offset(var0, 0.0D, var2);
         return Oringo.field9.theWorld.getCollidingBoundingBoxes(Oringo.field9.thePlayer, new AxisAlignedBB(var5.minX, 0.0D, var5.minZ, var5.maxX, var5.maxY, var5.maxZ)).isEmpty();
      }
   }

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public void method0(Class210.Class1 var1) {
      if (this.method44()) {
         if (field6 == null || this.cJ_.a_((long)(field4.method0() * 50.0D)) || field6.isDead || !field6.canEntityBeSeen(field58.thePlayer)) {
            field6 = null;
            Iterator var2 = ((List)field58.theWorld.loadedEntityList.stream().filter(AntiNukekubiModule::lambda$onMotionUpdate$0).collect(Collectors.toList())).iterator();
            if (var2.hasNext()) {
               Entity var3 = (Entity)var2.next();
               EntityArmorStand var4 = (EntityArmorStand)var3;
               field6 = var4;
               this.cJ_.o_();
               field5.add(var4);
            }
         }

         if (field6 != null) {
            Class34 var5 = Class352.method0(field6.posX, field6.posY + 0.85D, field6.posZ);
            var1.method0(var5);
         }
      }

   }

   public void b_() {
      this.o_();
   }

   public void o_() {
      field6 = null;
      field5.clear();
   }

   public AntiNukekubiModule() {
      super("Anti Nukekubi", Category.skyblock, SubCategory.slayer, "Aims at enderman heads");
      this.method0((Setting[])(new Setting[]{field7, field3, field0, field4, field2}));
   }

   public static boolean lambda$onMotionUpdate$0(Entity var0) {
      return var0 instanceof EntityArmorStand && Class176.method0((EntityArmorStand)var0) && !field5.contains(var0) && (double)var0.getDistanceToEntity(field58.thePlayer) < field7.method0() && ((EntityArmorStand)var0).canEntityBeSeen(field58.thePlayer) && Math.hypot(var0.posX - var0.prevPosX, var0.posZ - var0.prevPosZ) < 0.1D && Class417.method0(MessageCommand.method0(var0), (float)field3.method0());
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method44() && field6 != null) {
         Class528.method0(field6, var1.partialTicks, 1.0F, Color.white);
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.o_();
   }
}
