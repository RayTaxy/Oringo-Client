package oringo.module;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import map.Class28;
import map.Class34;
import map.Class352;
import map.Class361;
import map.Class376;
import map.Class408;
import map.Class447;
import map.Class510;
import map.Class94;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.pathfinder.WalkNodeProcessor;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.event.Class16;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.event.Class307;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class ShinyBlocksModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Nuker", false);
   public BlockPos bn_ = null;
   public final Class447 field2 = new Class447();
   public final ConcurrentHashMap field3 = new ConcurrentHashMap();
   public final PathFinder field4 = new PathFinder(new WalkNodeProcessor());
   public BlockPos field5 = null;
   public ArrayList field6 = new ArrayList();
   public final BooleanSetting field7 = new BooleanSetting("ESP", true);
   public final BooleanSetting field8 = new BooleanSetting("Tracers", false);
   public final BooleanSetting field9 = new BooleanSetting("Macro", false, ShinyBlocksModule::lambda$new$0);
   public final Class447 field10 = new Class447();

   public int r_() {
      int var1 = 0;
      double var2 = 9999999.0D;
      int var4 = 0;

      for(Iterator var5 = this.field6.iterator(); var5.hasNext(); ++var4) {
         Vec3 var6 = (Vec3)var5.next();
         double var7 = field58.thePlayer.getDistance(var6.xCoord, var6.yCoord, var6.zCoord);
         if (var7 < var2) {
            var2 = var7;
            var1 = var4;
         }
      }

      return var1;
   }

   public boolean method0(Vec3 var1) {
      return var1.xCoord % 0.25D == 0.0D && var1.yCoord % 0.25D == 0.0D && var1.zCoord % 0.25D == 0.0D;
   }

   public static Boolean lambda$new$0() {
      return true;
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field3.clear();
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (Class376.method0("The End") || Class376.method0("Bruiser")) {
         if (var1.field0 instanceof S2APacketParticles && ((S2APacketParticles)var1.field0).getParticleType() == EnumParticleTypes.SPELL_WITCH) {
            Vec3 var2 = new Vec3(((S2APacketParticles)var1.field0).getXCoordinate(), ((S2APacketParticles)var1.field0).getYCoordinate(), ((S2APacketParticles)var1.field0).getZCoordinate());
            if (this.method0(var2) && ((S2APacketParticles)var1.field0).getParticleCount() == 5) {
               BlockPos var3 = new BlockPos(var2);
               BlockPos var4 = new BlockPos((double)var3.getX() + 0.5D + (((S2APacketParticles)var1.field0).getXCoordinate() - (double)var3.getX() - 0.5D) * 3.0D, (double)var3.getY() + 0.5D + (((S2APacketParticles)var1.field0).getYCoordinate() - (double)var3.getY() - 0.5D) * 3.0D, (double)var3.getZ() + 0.5D + (((S2APacketParticles)var1.field0).getZCoordinate() - (double)var3.getZ() - 0.5D) * 3.0D);
               if (var4.equals(this.bn_) || var4.equals(this.field5) || field58.theWorld.getBlockState(var4).getBlock() == Blocks.air) {
                  return;
               }

               if (this.field3.get(var4) == null) {
                  this.field3.put(var4, new Class447());
               } else {
                  ((Class447)this.field3.get(var4)).o_();
               }
            }
         }

      }
   }

   @SubscribeEvent
   public void a_(RenderWorldLastEvent var1) {
      if (this.field9.method1()) {
         Iterator var2 = this.field6.iterator();

         while(var2.hasNext()) {
            Vec3 var3 = (Vec3)var2.next();
            AutoReadyModule.method0(var3, Color.ORANGE);
         }

      }
   }

   public static void method0(File var0) throws IOException {
      Desktop.getDesktop().open(var0);
   }

   public static void method6() {
      Class28.field3.forEach(Class28::lambda$removeEmptyData$12);
      Class28.field2.forEach(Class28::lambda$removeEmptyData$14);
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class376.method0("The End") || Class376.method0("Bruiser")) {
         if (!this.field3.isEmpty()) {
            this.field3.entrySet().removeIf(ShinyBlocksModule::lambda$onTick$3);
         }

         if (this.field0.method1() && this.field2.a_(1000L)) {
            if (this.method7()) {
               Iterator var2 = this.field3.keySet().iterator();

               BlockPos var3;
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  var3 = (BlockPos)var2.next();
               } while(field58.thePlayer.getDistance((double)var3.getX(), (double)var3.getY(), (double)var3.getZ()) >= 5.5D || var3.equals(this.bn_));

               this.field2.o_();
               this.bn_ = var3;
               Class361.method0((Class94)(new Class510(var3, EnumFacing.UP)));
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (this.field3.containsKey(var1.cB_)) {
         this.field3.remove(var1.cB_);
      }

   }

   public static double lambda$onMacro$1(BlockPos var0) {
      return field58.thePlayer.getDistance((double)var0.getX(), (double)var0.getY(), (double)var0.getZ());
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      this.field9.method0(false);
      if (this.field9.method1()) {
         if (!this.field10.a_(100L)) {
            Optional var2 = this.field3.keySet().stream().min(Comparator.comparingDouble(ShinyBlocksModule::lambda$onMacro$1));
            this.field6.clear();
            if (var2.isPresent()) {
               PathEntity var3 = this.method0((BlockPos)var2.get(), this.field4);
               if (var3 == null) {
                  return;
               }

               for(int var4 = 0; var4 < var3.getCurrentPathLength(); ++var4) {
                  this.field6.add(var3.getVectorFromIndex(field58.thePlayer, var4));
               }

               this.method9();
            }
         }

         this.field10.o_();
         if (!this.field6.isEmpty()) {
            Vec3 var5 = (Vec3)this.field6.get(Math.min(this.r_() + 6, this.field6.size() - 1));
            KeyBinding.setKeyBindState(field58.gameSettings.keyBindForward.getKeyCode(), true);
            KeyBinding.setKeyBindState(field58.gameSettings.keyBindSprint.getKeyCode(), true);
            KeyBinding.setKeyBindState(field58.gameSettings.keyBindJump.getKeyCode(), var5.yCoord > field58.thePlayer.posY);
            Class34 var6 = Class352.method0(var5.xCoord, var5.yCoord + 2.0D, var5.zCoord);
            field58.thePlayer.rotationYaw = var6.method5();
         }

      }
   }

   public boolean method7() {
      try {
         ItemStack var1 = field58.thePlayer.getHeldItem();
         if (var1 == null) {
            return false;
         } else {
            Iterator var2 = Class408.method1(var1).iterator();

            String var3;
            do {
               if (!var2.hasNext()) {
                  return false;
               }

               var3 = (String)var2.next();
            } while(!var3.contains("Mining Speed"));

            return true;
         }
      } catch (Exception var4) {
         var4.printStackTrace();
         return false;
      }
   }

   public void lambda$onRender$2(BlockPos var1) {
      Color var2 = field58.thePlayer.getDistance((double)var1.getX(), (double)var1.getY(), (double)var1.getZ()) < 5.5D && this.field0.method1() ? Color.BLUE : new Color(9515688);
      if (Class307.method0(var1)) {
         AutoMaskModule.method0(var1, var2);
      }

      if (this.field8.method1()) {
         AutoArrowModule.method0((double)var1.getX() + 0.5D, (double)var1.getY() + 0.5D, (double)var1.getZ() + 0.5D, var2);
      }

   }

   public static void z_() {
      (new File(Class376.field2)).mkdirs();
      VanquisherESPModule.method5();
   }

   public void method9() {
      ArrayList var1 = new ArrayList();
      Vec3 var2 = null;

      Vec3 var4;
      for(Iterator var3 = this.field6.iterator(); var3.hasNext(); var2 = var4) {
         var4 = (Vec3)var3.next();
         if (var2 != null) {
            byte var5 = 5;

            for(int var6 = 0; var6 <= var5; ++var6) {
               var1.add(new Vec3(var2.xCoord + (var4.xCoord - var2.xCoord) / (double)var5 * (double)var6, var2.yCoord + (var4.yCoord - var2.yCoord) / (double)var5 * (double)var6, var2.zCoord + (var4.zCoord - var2.zCoord) / (double)var5 * (double)var6));
            }
         }
      }

      this.field6.clear();
      this.field6 = var1;
   }

   public ShinyBlocksModule() {
      super("Shiny Blocks", Category.skyblock, SubCategory.visual);
      this.method0((Setting[])(new Setting[]{this.field7, this.field0, this.field8, this.field9}));
   }

   public PathEntity method0(BlockPos var1, PathFinder var2) {
      return var2.createEntityPathTo(field58.theWorld, field58.thePlayer, var1, 50.0F);
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getUnformattedText().startsWith("ENDER NODE!")) {
         this.field5 = this.bn_;
         this.field3.remove(this.bn_);
         this.field2.method0(1000000L);
      }

   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field7.method1()) {
         if (Class376.method0("The End") || Class376.method0("Bruiser")) {
            this.field3.keySet().forEach(this::lambda$onRender$2);
            if (!this.field2.a_(1000L) && this.bn_ != null && Class307.method0(this.bn_)) {
               AutoMaskModule.method0(this.bn_, Color.GREEN);
            }

         }
      }
   }

   public static boolean lambda$onTick$3(Entry var0) {
      return ((Class447)var0.getValue()).a_(5000L);
   }
}
