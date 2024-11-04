package oringo.module;

import java.awt.Color;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;
import map.Class355;
import map.Class361;
import map.Class447;
import map.Class475;
import map.Class510;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class307;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class MushroomESPModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Nuker", false);
   public final BooleanSetting cc_ = new BooleanSetting("Tracers", false);
   public final ConcurrentHashMap field2 = new ConcurrentHashMap();

   public MushroomESPModule() {
      super("Mushroom ESP", Category.skyblock, SubCategory.visual, "Glowing Mushroom ESP");
      this.method0((Setting[])(new Setting[]{this.cc_, this.field0}));
   }

   public static void z_() {
      if (!SystemTray.isSupported()) {
         Class355.field2.error("System Tray not supported!");
      } else {
         SystemTray var0 = SystemTray.getSystemTray();

         try {
            InputStream var1 = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("oringoclient", "logo.png")).getInputStream();
            Throwable var2 = null;

            try {
               BufferedImage var3 = ImageIO.read(var1);
               int var4 = var0.getTrayIconSize().width;
               TrayIcon var5 = new TrayIcon(var3.getScaledInstance(var4, -1, 4), "Oringo Client");
               var5.setImageAutoSize(true);
               Class355.field1 = var5;
               var0.add(Class355.field1);
               Class355.field0 = true;
            } catch (Throwable var14) {
               var2 = var14;
               throw var14;
            } finally {
               if (var1 != null) {
                  if (var2 != null) {
                     try {
                        var1.close();
                     } catch (Throwable var13) {
                        var2.addSuppressed(var13);
                     }
                  } else {
                     var1.close();
                  }
               }

            }
         } catch (Exception var16) {
            Class355.field2.error("Exception thrown while creating a tray!", var16);
         }

      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S2APacketParticles && (double)((S2APacketParticles)var1.field0).getParticleSpeed() == 1.0D && ((S2APacketParticles)var1.field0).getParticleType() == EnumParticleTypes.SPELL_MOB && ((S2APacketParticles)var1.field0).getParticleCount() == 0) {
         S2APacketParticles var2 = (S2APacketParticles)var1.field0;
         BlockPos var3 = new BlockPos(var2.getXCoordinate(), var2.getYCoordinate(), var2.getZCoordinate());
         if (this.field2.containsKey(var3)) {
            ((Class447)this.field2.get(var3)).o_();
         } else {
            this.field2.put(var3, new Class447());
         }
      }

   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!this.field2.isEmpty()) {
         this.field2.entrySet().removeIf(MushroomESPModule::lambda$onRender$0);
         if (!this.field2.isEmpty()) {
            Iterator var2 = this.field2.entrySet().iterator();

            while(true) {
               BlockPos var4;
               Block var5;
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  Entry var3 = (Entry)var2.next();
                  var4 = (BlockPos)var3.getKey();
                  var5 = field58.theWorld.getBlockState(var4).getBlock();
               } while(var5 != Blocks.brown_mushroom && var5 != Blocks.red_mushroom);

               if (Class307.method0(var4)) {
                  GlStateManager.color(0.2F, 0.7F, 0.11F, 0.5F);
                  Class475.method0(var4);
                  GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
               }

               if (this.cc_.method1()) {
                  AutoArrowModule.method0((double)var4.getX() + 0.5D, (double)var4.getY() + 0.25D, (double)var4.getZ() + 0.5D, new Color(0.2F, 0.7F, 0.1F, 0.5F));
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!this.field2.isEmpty()) {
         Iterator var2 = this.field2.entrySet().iterator();

         while(true) {
            BlockPos var4;
            Block var5;
            do {
               if (!var2.hasNext()) {
                  return;
               }

               Entry var3 = (Entry)var2.next();
               var4 = (BlockPos)var3.getKey();
               var5 = field58.theWorld.getBlockState(var4).getBlock();
            } while(var5 != Blocks.brown_mushroom && var5 != Blocks.red_mushroom);

            if (this.field0.method1() && field58.thePlayer.getDistance((double)var4.getX(), (double)var4.getY(), (double)var4.getZ()) <= 5.0D && Class361.method0((Class94)(new Class510(var4, EnumFacing.UP)))) {
               field58.theWorld.setBlockToAir(var4);
            }
         }
      }
   }

   public static boolean lambda$onRender$0(Entry var0) {
      return ((Class447)var0.getValue()).a_(1500L);
   }
}
