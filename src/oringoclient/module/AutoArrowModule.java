package oringo.module;

import com.google.common.collect.Sets;
import java.awt.Color;
import java.util.Set;
import map.Class173;
import map.Class228;
import map.Class229;
import map.Class350;
import map.Class361;
import map.Class496;
import map.Class518;
import map.Class94;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class16;
import oringo.event.Class210;
import oringo.event.Class270;

public class AutoArrowModule extends Module {
   public BlockPos field0;
   public static final Set field1 = Sets.newHashSet(new BlockPos[]{new BlockPos(68, 130, 50), new BlockPos(66, 130, 50), new BlockPos(64, 130, 50), new BlockPos(68, 128, 50), new BlockPos(66, 128, 50), new BlockPos(64, 128, 50), new BlockPos(68, 126, 50), new BlockPos(66, 126, 50), new BlockPos(64, 126, 50)});

   public AutoArrowModule() {
      super("Auto Arrow", Category.dungeon, SubCategory.floor7, "Does the f7 p4 device. Needs a shortbow");
   }

   public static boolean lambda$onUpdate$0(ItemStack var0) {
      String var1 = BlockHitModule.method0(var0);
      return "TERMINATOR".equals(var1) || "ARTISANAL_SHORTBOW".equals(var1) || "ITEM_SPIRIT_BOW".equals(var1) || "JUJU_SHORTBOW".equals(var1);
   }

   public static void method0(S08PacketPlayerPosLook var0) {
      if (AntiStuckModule.field0.method44() || Class229.method1().method44()) {
         double var1 = 0.1D;
         AxisAlignedBB var3 = AntiStuckModule.field58.thePlayer.getEntityBoundingBox().offset(-AntiStuckModule.field58.thePlayer.posX, -AntiStuckModule.field58.thePlayer.posY, -AntiStuckModule.field58.thePlayer.posZ).offset(var0.getX(), var0.getY(), var0.getZ());
         if (AutoBeamsModule.method0(var3.expand(0.1D, 0.0D, 0.1D), 0.0D, 0.0D)) {
            if (!AutoBeamsModule.method0(var3, 0.1D, 0.0D)) {
               AntiStuckModule.field58.thePlayer.setPosition(AntiStuckModule.field58.thePlayer.posX + 0.1D, AntiStuckModule.field58.thePlayer.posY, AntiStuckModule.field58.thePlayer.posZ);
               return;
            }

            if (!AutoBeamsModule.method0(var3, 0.0D, 0.1D)) {
               AntiStuckModule.field58.thePlayer.setPosition(AntiStuckModule.field58.thePlayer.posX, AntiStuckModule.field58.thePlayer.posY, AntiStuckModule.field58.thePlayer.posZ + 0.1D);
               return;
            }

            if (!AutoBeamsModule.method0(var3, -0.1D, 0.0D)) {
               AntiStuckModule.field58.thePlayer.setPosition(AntiStuckModule.field58.thePlayer.posX - 0.1D, AntiStuckModule.field58.thePlayer.posY, AntiStuckModule.field58.thePlayer.posZ);
               return;
            }

            if (!AutoBeamsModule.method0(var3, 0.0D, -0.1D)) {
               AntiStuckModule.field58.thePlayer.setPosition(AntiStuckModule.field58.thePlayer.posX, AntiStuckModule.field58.thePlayer.posY, AntiStuckModule.field58.thePlayer.posZ - 0.1D);
               return;
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field0 = null;
   }

   public static void method0(double var0, double var2, double var4, Color var6) {
      var0 -= Oringo.field9.getRenderManager().viewerPosX;
      var2 -= Oringo.field9.getRenderManager().viewerPosY;
      var4 -= Oringo.field9.getRenderManager().viewerPosZ;
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GL11.glLineWidth(1.5F);
      GlStateManager.disableTexture2D();
      GlStateManager.depthMask(false);
      GlStateManager.disableLighting();
      AutoBlazeModule.method0(var6);
      GL11.glBegin(2);
      Class228 var7 = (new Class228(Oringo.field9.thePlayer.getLookVec())).method0().method1(0.1D);
      GL11.glVertex3d(var7.field2, (double)Oringo.field9.thePlayer.getEyeHeight() + var7.field1, var7.field0);
      GL11.glVertex3d(var0, var2, var4);
      GL11.glEnd();
      AutoCloseModule.method5();
      GlStateManager.enableTexture2D();
      GlStateManager.depthMask(true);
      GL11.glLineWidth(1.0F);
      GlStateManager.disableBlend();
   }

   public static String method0(EntityLivingBase var0, String var1, int var2, int var3) {
      String var4 = "§8[§7Lv" + var2 + "§8] ";
      String var5 = "§c" + var1;
      int var6 = (int)Class518.method0(var0);
      if (var6 == var3 * 4) {
         var5 = "§5Runic " + var1;
      }

      if (var6 == var3 * 3) {
         var5 = "§5§ka§5Corrupted" + var1;
      }

      if (var6 == var3 * 4 * 3) {
         var5 = "§5§ka§5Corrupted Runic " + var1;
      }

      return var4 + var5 + " §a" + AutoBeamsModule.method0((double)var0.getHealth()) + "§f/§a" + AutoBeamsModule.method0((double)var6) + "§c❤";
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field0 != null && Class496.field5) {
         int var2 = TrailModule.method0(AutoArrowModule::lambda$onUpdate$0);
         if (var2 != -1) {
            Vec3 var3 = field58.thePlayer.getPositionEyes(1.0F);
            Vec3 var4 = (new Vec3(this.field0)).addVector(0.5D, 0.5D, 0.5D);
            MovingObjectPosition var5 = field58.theWorld.rayTraceBlocks(var3, var4, false, true, false);
            if (var5 == null || var5.getBlockPos().equals(this.field0)) {
               if (!Class361.method0((Class94)(new Class350(var2)))) {
                  return;
               }

               var1.method0(Class173.method0(var4));
               this.field0 = null;
            }

         }
      }
   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field5) {
         IBlockState var2 = var1.field2;
         IBlockState var3 = var1.field0;
         BlockPos var4 = var1.cB_;
         if (var2.getBlock() == Blocks.emerald_block && var3.getBlock() == Blocks.stained_hardened_clay && field1.contains(var4)) {
            this.field0 = var4;
         }

      }
   }
}
