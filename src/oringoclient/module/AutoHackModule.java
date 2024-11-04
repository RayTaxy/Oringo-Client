package oringo.module;

import java.awt.Color;
import java.util.HashSet;
import map.Class34;
import map.Class374;
import map.Class447;
import map.Class496;
import map.Class528;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class156;

public class AutoHackModule extends Module {
   public final HashSet field0 = new HashSet();
   public final Class447 bc_ = new Class447();

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field21) {
         ItemStack var2 = field58.thePlayer.inventory.armorItemInSlot(3);
         if (var2 != null && var2.getDisplayName().equals("§dRetro-Encabulating Visor")) {
            AutoMaskModule.method0(new BlockPos(-69, 65, -63), Color.RED);
            AutoMaskModule.method0(new BlockPos(-44, 71, -62), Color.ORANGE.darker());
            AutoMaskModule.method0(new BlockPos(-39, 71, -95), Color.YELLOW);
            AutoMaskModule.method0(new BlockPos(-62, 71, -83), Color.GREEN);
            AutoMaskModule.method0(new BlockPos(-66, 71, -119), Color.BLUE);
            AutoMaskModule.method0(new BlockPos(-33, 69, -134), Color.CYAN);
            AutoMaskModule.method0(new BlockPos(-89, 73, -115), Color.MAGENTA.darker().darker());
            AutoMaskModule.method0(new BlockPos(-110, 73, -107), Color.MAGENTA);
         }
      }
   }

   public AutoHackModule() {
      super("Auto Hack", Category.skyblock, SubCategory.rift, "Automatic terminal hacking");
   }

   @SubscribeEvent
   public void field0(Class156 var1) {
      if (field58.thePlayer != null && Class496.field21) {
         if (!"Hacking".equals(Class374.method0())) {
            this.bc_.o_();
            this.field0.clear();
         } else if (this.bc_.a_(1000L)) {
            ContainerChest var2 = Class528.method1();

            for(int var3 = 0; var3 < 5; ++var3) {
               if (!this.field0.contains(var3)) {
                  int var4 = 2 + var3;
                  ItemStack var5 = var2.getSlot(var4).getStack();
                  if (var5 != null && !var5.getDisplayName().startsWith("§a")) {
                     int var6 = var5.stackSize;

                     for(int var7 = 1; var7 < 6; ++var7) {
                        int var8 = var4 + var7 * 9;
                        ItemStack var9 = var2.getSlot(var8).getStack();
                        if (var9 != null && var9.stackSize == var6) {
                           field58.getNetHandler().addToSendQueue(new C0EPacketClickWindow(field58.thePlayer.openContainer.windowId, var8, 0, 0, (ItemStack)null, DiscordRPCModule.method6()));
                        }
                     }
                  }
               }
            }

         }
      }
   }

   public static Vec3 method0(Class34 var0) {
      return AutoFrozilleModule.method0(var0.method5(), var0.method2());
   }
}
