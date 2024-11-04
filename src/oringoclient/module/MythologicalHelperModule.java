package oringo.module;

import java.awt.Color;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import map.Class228;
import map.Class361;
import map.Class447;
import map.Class496;
import map.Class510;
import map.Class94;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class MythologicalHelperModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Burrow Aura", true);
   public final BooleanSetting bh_ = new BooleanSetting("Auto swap to spade", false, this::lambda$new$0);
   public final BooleanSetting field2 = new BooleanSetting("Highlight Burrows", true);
   public final Class447 field3 = new Class447();
   public final Class447 field4 = new Class447();
   public final CopyOnWriteArrayList field5 = new CopyOnWriteArrayList();
   public final Class447 field6 = new Class447();
   public final BooleanSetting field7 = new BooleanSetting("Draw Echo", true);
   public final CopyOnWriteArrayList field8 = new CopyOnWriteArrayList();

   public static boolean lambda$onPacket$1(Class228 var0, Class228 var1) {
      return var1.method1(var0) < 2.5D;
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!Class496.field21) {
         if (this.field6.a_(10000L)) {
            this.field8.clear();
         }

         Class228 var3;
         if (this.field8.size() > 1) {
            Class228 var2 = (Class228)this.field8.get(0);
            var3 = (Class228)this.field8.get(this.field8.size() - 1);
            Class228 var4 = var3.method2(var2).method1(50.0D);
            AutoBeamsModule.method0(var2.method6(), var2.method4(var4).method6(), Color.GREEN);
         }

         if (!this.field5.isEmpty()) {
            GlStateManager.blendFunc(770, 771);
            GlStateManager.enableBlend();
            GL11.glLineWidth(2.0F);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask(false);
            GlStateManager.disableLighting();
            AutoBlazeModule.method0(Color.YELLOW);
            Iterator var5 = this.field5.iterator();

            while(var5.hasNext()) {
               var3 = (Class228)var5.next();
               if (var3.method3(field58.thePlayer.getPositionEyes(1.0F)) <= 50.0D) {
                  RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(var3.field2 - Minecraft.getMinecraft().getRenderManager().viewerPosX, var3.field1 - Minecraft.getMinecraft().getRenderManager().viewerPosY, var3.field0 - Minecraft.getMinecraft().getRenderManager().viewerPosZ, var3.field2 + 1.0D - Minecraft.getMinecraft().getRenderManager().viewerPosX, var3.field1 - Minecraft.getMinecraft().getRenderManager().viewerPosY, var3.field0 + 1.0D - Minecraft.getMinecraft().getRenderManager().viewerPosZ));
               }
            }

            AutoCloseModule.method5();
            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
            GlStateManager.disableBlend();
         }
      }
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (!Class496.field21) {
         if (var1.field0 instanceof S2APacketParticles) {
            Class228 var2;
            if (this.field2.method1() && ((S2APacketParticles)var1.field0).getParticleType() == EnumParticleTypes.FOOTSTEP) {
               var2 = new Class228((new Class228((S2APacketParticles)var1.field0)).method3());
               if (!this.field5.contains(var2)) {
                  this.field5.add(var2);
               }
            } else if (this.field7.method1() && ((S2APacketParticles)var1.field0).getParticleType() == EnumParticleTypes.FIREWORKS_SPARK) {
               if (this.field6.a_(700L)) {
                  if (!"ANCESTRAL_SPADE".equals(BlockHitModule.method0(field58.thePlayer.getHeldItem())) || this.field4.a_(500L)) {
                     return;
                  }

                  this.field8.clear();
               }

               var2 = new Class228((S2APacketParticles)var1.field0);
               if (this.field8.size() == 0) {
                  if (field58.thePlayer.getPositionEyes(1.0F).distanceTo(var2.method6()) > 2.8D) {
                     return;
                  }
               } else if (this.field8.stream().noneMatch(MythologicalHelperModule::lambda$onPacket$1)) {
                  return;
               }

               this.field8.add(var2);
               this.field6.o_();
            }
         }

         if (var1.field0 instanceof S29PacketSoundEffect) {
            if (((S29PacketSoundEffect)var1.field0).getSoundName().equals("note.pling")) {
               this.field5.removeIf(MythologicalHelperModule::field0);
            }

            if (((S29PacketSoundEffect)var1.field0).getSoundName().equals("note.harp")) {
               this.field4.o_();
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field8.clear();
      this.field5.clear();
   }

   public static boolean field0(Class189 var0, Class228 var1) {
      return var1.method1(new Class228(((S29PacketSoundEffect)var0.field0).getX(), ((S29PacketSoundEffect)var0.field0).getY(), ((S29PacketSoundEffect)var0.field0).getZ())) < 1.0D;
   }

   public static int method3() {
      int var0 = 0;
      ItemStack[] var1 = Oringo.field9.thePlayer.inventory.mainInventory;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ItemStack var4 = var1[var3];
         if (var4 == null) {
            ++var0;
         }
      }

      return var0;
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (!this.field5.isEmpty() && !Class496.field21) {
         Iterator var2 = this.field5.iterator();

         while(var2.hasNext()) {
            Class228 var3 = (Class228)var2.next();
            if (var3.method3(field58.thePlayer.getPositionEyes(1.0F)) < 5.0D && this.field3.method0(1500L, true) && this.field0.method1()) {
               int var4 = field58.thePlayer.inventory.currentItem;
               if (!"ANCESTRAL_SPADE".equals(BlockHitModule.method0(field58.thePlayer.getHeldItem()))) {
                  if (!this.bh_.method1()) {
                     return;
                  }

                  var4 = PinglessHardstoneModule.z_("ANCESTRAL_SPADE");
                  if (var4 == -1) {
                     return;
                  }
               }

               Class361.method0((Class94)(new Class510(var4, var3.method3().down(), EnumFacing.UP)));
            }
         }

      }
   }

   public Boolean lambda$new$0() {
      return !this.field0.method1();
   }

   public MythologicalHelperModule() {
      super("Mythological Helper", Category.skyblock, SubCategory.qol, "Helps with mythological festival");
      this.method0((Setting[])(new Setting[]{this.field2, this.field0, this.bh_, this.field7}));
   }
}
