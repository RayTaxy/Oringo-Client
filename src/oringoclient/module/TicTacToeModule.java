package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import map.Class12;
import map.Class282;
import map.Class361;
import map.Class434;
import map.Class475;
import map.Class94;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class270;
import oringo.event.Class307;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class TicTacToeModule extends Module {
   public int[][] field0 = new int[3][3];
   public boolean field1 = false;
   public Class12 field2 = null;
   public BlockPos field3 = null;
   public BlockPos field4 = null;
   public final DoubleSetting field5 = new DoubleSetting("Distance", 5.0D, 1.0D, 6.0D, 0.1D);
   public boolean field6 = false;

   public int[][] method0(int[][] var1) {
      int[][] var2 = new int[3][3];
      int var3 = 0;
      int[][] var4 = var1;
      int var5 = var1.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         int[] var7 = var4[var6];
         int[] var8 = var7;
         int var9 = var7.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            int var11 = var8[var10];
            var2[var3 / 3][var3 % 3] = var11;
            ++var3;
         }
      }

      return var2;
   }

   public int method1(int[][] var1) {
      if (Arrays.stream(var1).anyMatch(TicTacToeModule::lambda$getGameState$2)) {
         return 1;
      } else {
         int var2;
         for(var2 = 0; var2 < 3; ++var2) {
            if (var1[0][var2] == 1 && var1[1][var2] == 1 && var1[2][var2] == 1) {
               return 1;
            }
         }

         if (var1[0][0] == 1 && var1[1][1] == 1 && var1[2][2] == 1) {
            return 1;
         } else if (var1[2][0] == 1 && var1[1][1] == 1 && var1[1][2] == 1) {
            return 1;
         } else if (Arrays.stream(var1).anyMatch(TicTacToeModule::lambda$getGameState$3)) {
            return 2;
         } else {
            for(var2 = 0; var2 < 3; ++var2) {
               if (var1[0][var2] == 2 && var1[1][var2] == 2 && var1[2][var2] == 2) {
                  return 2;
               }
            }

            if (var1[0][0] == 2 && var1[1][1] == 2 && var1[2][2] == 2) {
               return 2;
            } else if (var1[2][0] == 2 && var1[1][1] == 2 && var1[1][2] == 2) {
               return 2;
            } else {
               return 0;
            }
         }
      }
   }

   public static void method0(Entity var0, String var1, double var2, double var4, double var6, int var8) {
      double var9 = var0.getDistanceSqToEntity(Oringo.field9.getRenderManager().livingPlayer);
      if (var9 <= (double)(var8 * var8)) {
         FontRenderer var11 = Oringo.field9.getRenderManager().getFontRenderer();
         float var12 = 1.6F;
         float var13 = 0.016666668F * var12;
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)var2, (float)var4 + var0.height + 0.5F, (float)var6);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-Oringo.field9.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(Oringo.field9.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
         GlStateManager.scale(-var13, -var13, var13);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         byte var14 = 0;
         var11.drawString(var1, -var11.getStringWidth(var1) / 2, var14, -1);
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         var11.drawString(var1, -var11.getStringWidth(var1) / 2, var14, -1);
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }

   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (SecretAuraModule.w_("TTT")) {
         if (!this.field6 || !this.field1) {
            try {
               this.method8();
            } catch (Exception var5) {
               this.o_();
            }
         }

         if (this.field1) {
            if (this.method0(this.field0, 1) - 1 != this.method0(this.field0, 2)) {
               this.o_();
               return;
            }

            int var2 = this.method6();
            if (var2 != -1) {
               int var3 = var2 / 3;
               int var4 = var2 % 3;
               this.field4 = this.field3.add(var3 * this.field2.method1(), -var4, var3 * this.field2.method0());
            } else {
               method3(var2);
            }

            if (this.field4 != null && field58.thePlayer.getDistance((double)this.field4.getX(), (double)this.field4.getY(), (double)this.field4.getZ()) < this.field5.method0()) {
               method3("Click");
               Class361.method0((Class94)(new Class282(this.field4, new Vec3(this.field4), EnumFacing.DOWN)));
            }

            this.o_();
         }
      }

   }

   public static boolean lambda$scanBoard$0(EntityItemFrame var0) {
      return true;
   }

   public static boolean lambda$scanBoard$1(BlockPos var0, EntityItemFrame var1) {
      return var1.getDisplayedItem() != null && var1.getDisplayedItem().getItem() == Items.filled_map && var0.equals(var1.getPosition());
   }

   public static void method5() {
      Class434.field2 = true;
      if (Class434.field0 != -1 && BarPhaseModule.method3()) {
         GlStateManager.bindTexture(Class434.field0);
      }
   }

   public boolean method2(int[][] var1) {
      if (this.method1(var1) == 1) {
         return false;
      } else if (this.method1(var1) == 2) {
         return true;
      } else {
         int var2 = 0;
         int var3 = this.method0(var1, 1);
         int var4 = this.method0(var1, 2);
         boolean var5 = false;
         ArrayList var6 = new ArrayList();
         boolean var7 = var3 > var4;
         int[][] var8 = var1;
         int var9 = var1.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            int[] var11 = var8[var10];
            int[] var12 = var11;
            int var13 = var11.length;

            for(int var14 = 0; var14 < var13; ++var14) {
               int var15 = var12[var14];
               if (var15 == 0) {
                  var5 = true;
                  int[][] var16 = this.method0(var1);
                  var16[var2 / 3][var2 % 3] = var7 ? 2 : 1;
                  var6.add(var16);
               }

               ++var2;
            }
         }

         if (var5) {
            return var7 ? var6.stream().anyMatch(this::method2) : var6.stream().allMatch(this::method2);
         } else {
            return true;
         }
      }
   }

   public int method6() {
      int var1 = 0;
      int[][] var2 = this.field0;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int[] var5 = var2[var4];
         int[] var6 = var5;
         int var7 = var5.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            int var9 = var6[var8];
            if (var9 == 0) {
               int[][] var10 = this.method0(this.field0);
               var10[var1 / 3][var1 % 3] = 2;
               if (this.method2(var10)) {
                  return var1;
               }
            }

            ++var1;
         }
      }

      return -1;
   }

   public void o_() {
      this.field6 = false;
      this.field1 = false;
      this.field0 = new int[3][3];
      this.field3 = null;
      this.field2 = null;
   }

   public TicTacToeModule() {
      super("Tic Tac Toe", Category.dungeon, SubCategory.puzzle, "Automatically solves your TTT puzzle");
      this.method0((Setting[])(new Setting[]{this.field5}));
   }

   public static boolean lambda$getGameState$2(int[] var0) {
      return Arrays.equals(var0, new int[]{1, 1, 1});
   }

   public static boolean lambda$getGameState$3(int[] var0) {
      return Arrays.equals(var0, new int[]{2, 2, 2});
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.o_();
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.field4 != null) {
         if (field58.theWorld.getBlockState(this.field4).getBlock() == Blocks.air) {
            this.field4 = null;
            return;
         }

         if (Class307.method0(this.field4)) {
            AutoBlazeModule.method0(Color.RED);
            Class475.method0(this.field4);
            AutoCloseModule.method5();
         }
      }

   }

   public void method8() {
      // $FF: Couldn't be decompiled
   }

   public int method0(int[][] var1, int var2) {
      int var3 = 0;
      int[][] var4 = var1;
      int var5 = var1.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         int[] var7 = var4[var6];
         int[] var8 = var7;
         int var9 = var7.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            int var11 = var8[var10];
            if (var11 == var2) {
               ++var3;
            }
         }
      }

      return var3;
   }
}
