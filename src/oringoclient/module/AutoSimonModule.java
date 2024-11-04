package oringo.module;

import java.util.ArrayList;
import java.util.List;
import map.Class282;
import map.Class284;
import map.Class296;
import map.Class361;
import map.Class479;
import map.Class496;
import map.Class94;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.event.Class16;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class AutoSimonModule extends Module {
   public boolean field0 = false;
   public final BooleanSetting au_ = (BooleanSetting)(new BooleanSetting("Auto activate", true)).method2("Should the device be started by the module");
   public final List field2 = new ArrayList();
   public static boolean field9;
   public final DoubleSetting field4 = new DoubleSetting("Delay", 250.0D, 0.0D, 500.0D, 50.0D);
   public final BooleanSetting field5 = (BooleanSetting)(new BooleanSetting("Skip", false)).method2("Quickly completes the device");
   public final Class296 field6 = new Class296();
   public final BlockPos field7 = new BlockPos(110, 121, 91);

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (Class496.field5 && Class496.field11) {
         if (!field9) {
            if (this.au_.method1() && Class284.method0(this.field7)) {
               if (!Class361.method0((Class94)(new Class282(this.field7, new Vec3(this.field7), EnumFacing.DOWN)))) {
                  return;
               }

               if (this.field5.method1()) {
                  this.field6.o_();
                  this.field0 = true;
               }

               field9 = true;
               this.field2.clear();
            }

         } else if (this.field0 && this.field5.method1()) {
            if (this.field6.method0() == 1L) {
               Class361.method0((Class94)(new Class282(this.field7, new Vec3(this.field7), EnumFacing.DOWN)));
            }

            if (this.field6.method0() == 20L) {
               Class361.method0((Class94)(new Class282(this.field7, new Vec3(this.field7), EnumFacing.DOWN)));
               this.field0 = false;
            }

         } else if (field58.theWorld.getBlockState(this.field7.add(0, 0, 2)).getBlock() == Blocks.stone_button) {
            if (this.field6.a_((long)(this.field4.method3() / 50)) && !this.field2.isEmpty()) {
               BlockPos var2 = (BlockPos)this.field2.get(0);
               if (Class284.method0(var2)) {
                  if (Class361.method0((Class94)(new Class282(var2, new Vec3(var2), EnumFacing.DOWN)))) {
                     this.field2.remove(0);
                     this.field6.o_();
                  }
               }
            }
         }
      } else {
         this.field0 = false;
      }
   }

   @SubscribeEvent
   public void method0(Load var1) {
      field9 = false;
      this.field2.clear();
   }

   public AutoSimonModule() {
      super("Auto Simon", Category.dungeon, SubCategory.floor7, "Does the simon says device");
      this.method0((Setting[])(new Setting[]{this.field4, this.au_, this.field5}));
   }

   public void b_() {
      field9 = false;
      this.field2.clear();
   }

   public void method4() {
      this.field0 = false;
   }

   public static void method0(float var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      double var7 = (double)(var0 + var2);
      double var9 = (double)(var1 + var3);
      float var11 = (float)(var6 >> 24 & 255) / 255.0F;
      float var12 = (float)(var6 >> 16 & 255) / 255.0F;
      float var13 = (float)(var6 >> 8 & 255) / 255.0F;
      float var14 = (float)(var6 & 255) / 255.0F;
      GL11.glPushAttrib(0);
      GL11.glScaled(0.5D, 0.5D, 0.5D);
      var0 *= 2.0F;
      var1 *= 2.0F;
      var7 *= 2.0D;
      var9 *= 2.0D;
      GL11.glLineWidth(var5);
      GlStateManager.disableTexture2D();
      GlStateManager.color(var12, var13, var14, var11);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      Class479.field3.begin(2, DefaultVertexFormats.POSITION);
      byte var15 = 15;

      int var16;
      for(var16 = 0; var16 <= 90; var16 += var15) {
         Class479.field3.pos((double)(var0 + var4) + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), (double)(var1 + var4) + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), 0.0D).endVertex();
      }

      for(var16 = 90; var16 <= 180; var16 += var15) {
         Class479.field3.pos((double)(var0 + var4) + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), var9 - (double)var4 + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * (double)(var4 * -1.0F), 0.0D).endVertex();
      }

      for(var16 = 0; var16 <= 90; var16 += var15) {
         Class479.field3.pos(var7 - (double)var4 + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * (double)var4, var9 - (double)var4 + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * (double)var4, 0.0D).endVertex();
      }

      for(var16 = 90; var16 <= 180; var16 += var15) {
         Class479.field3.pos(var7 - (double)var4 + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * (double)var4, (double)(var1 + var4) + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * (double)var4, 0.0D).endVertex();
      }

      Class479.field1.draw();
      GlStateManager.enableTexture2D();
      GL11.glScaled(2.0D, 2.0D, 2.0D);
      GL11.glPopAttrib();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   @SubscribeEvent
   public void method0(PlayerInteractEvent var1) {
      if (this.field7.equals(var1.pos) && var1.action == Action.RIGHT_CLICK_BLOCK) {
         this.field2.clear();
         field9 = true;
      }

   }

   @SubscribeEvent
   public void method0(Class16 var1) {
      if (Class496.field11 && var1.field2.getBlock() == Blocks.sea_lantern && var1.cB_.getX() == 111 && var1.cB_.getY() >= 100 && var1.cB_.getY() <= 150 && var1.cB_.getZ() >= 70 && var1.cB_.getZ() <= 120 && !this.field2.contains(var1.cB_.add(-1, 0, 0))) {
         this.field2.add(var1.cB_.add(-1, 0, 0));
      }

   }
}
