package oringo.module;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import map.Class173;
import map.Class296;
import map.Class361;
import map.Class47;
import map.Class486;
import map.Class501;
import map.Class94;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoJaxRangeModule extends Module {
   public BlockPos field10 = null;
   public final Class296 z_ = new Class296();
   public final BlockPos field2 = new BlockPos(0, 62, -145);
   public final DoubleSetting field3 = new DoubleSetting("Cooldown", 400.0D, 50.0D, 1000.0D, 50.0D);

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (ThystHiderModule.method3().equals(this.field2)) {
         if (this.z_.method0((long)(this.field3.method3() / 50), true)) {
            if (Class47.method0(field58.thePlayer.getHeldItem())) {
               BlockPos var2 = null;
               Iterator var3 = BlockPos.getAllInBox(new BlockPos(-13, 67, -129), new BlockPos(8, 59, -157)).iterator();

               while(true) {
                  BlockPos var4;
                  IBlockState var5;
                  do {
                     do {
                        if (!var3.hasNext()) {
                           this.field10 = var2;
                           if (var2 == null) {
                              method3("IWasNotChosen");
                              return;
                           }

                           Vec3 var6 = this.method1(var2);
                           if (var6 == null) {
                              method3("Invisible");
                              return;
                           }

                           var1.method0(Class173.method0(var6));
                           Class361.method0((Class94)(new Class501()));
                           return;
                        }

                        var4 = (BlockPos)var3.next();
                        var5 = field58.theWorld.getBlockState(var4);
                     } while(var5.getBlock() != Blocks.redstone_lamp);
                  } while(var4.equals(this.field10) && var2 != null);

                  var2 = var4;
               }
            }
         }
      }
   }

   public static Map method1() {
      try {
         Field var0 = RenderGlobal.class.getDeclaredField("field_72738_E");
         var0.setAccessible(true);
         return (Map)var0.get(Minecraft.getMinecraft().renderGlobal);
      } catch (Exception var1) {
         return new HashMap();
      }
   }

   public static int method0(BlockPos var0) {
      IBlockState var1 = Oringo.field9.theWorld.getBlockState(var0);
      int var2 = Oringo.field9.thePlayer.inventory.currentItem;
      float var3 = AutoJoinSkyblockModule.method0(var1, Oringo.field9.thePlayer.inventory.getStackInSlot(var2));

      for(int var4 = 0; var4 < 9; ++var4) {
         float var5 = AutoJoinSkyblockModule.method0(var1, Oringo.field9.thePlayer.inventory.getStackInSlot(var4));
         if (var5 > var3) {
            var2 = var4;
            var3 = var5;
         }
      }

      return var2;
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.type == ElementType.TEXT && field58.thePlayer != null) {
         if (!this.z_.a_((long)(this.field3.method3() / 50))) {
            if (Class47.method0(field58.thePlayer.getHeldItem())) {
               Class486.method0(field58.thePlayer.getHeldItem(), this.z_, this.field3.method3() / 50);
            }
         }
      }
   }

   public AutoJaxRangeModule() {
      super("Auto Jax Range", Category.skyblock, SubCategory.qol, "Shoots targets at Jax's shooting range");
      this.method0((Setting[])(new Setting[]{this.field3}));
   }

   public Vec3 method1(BlockPos var1) {
      ArrayList var2 = new ArrayList();

      for(int var3 = 0; var3 < 10; ++var3) {
         for(int var4 = 0; var4 < 10; ++var4) {
            for(int var5 = 0; var5 < 10; ++var5) {
               Vec3 var6 = (new Vec3(0.05D + (double)var3 / 10.0D, 0.05D + (double)var4 / 10.0D, 0.05D + (double)var5 / 10.0D)).add(new Vec3(var1));
               MovingObjectPosition var7 = field58.theWorld.rayTraceBlocks(field58.thePlayer.getPositionEyes(1.0F), var6, false, true, false);
               if (var7.typeOfHit == MovingObjectType.BLOCK && var1.equals(var7.getBlockPos())) {
                  var2.add(var6);
               }
            }
         }
      }

      return this.method0((List)var2);
   }

   public Vec3 method0(List var1) {
      if (var1.isEmpty()) {
         return null;
      } else {
         Vec3 var2 = new Vec3(0.0D, 0.0D, 0.0D);

         Vec3 var4;
         for(Iterator var3 = var1.iterator(); var3.hasNext(); var2 = var2.addVector(var4.xCoord / (double)var1.size(), var4.yCoord / (double)var1.size(), var4.zCoord / (double)var1.size())) {
            var4 = (Vec3)var3.next();
         }

         return var2;
      }
   }
}
