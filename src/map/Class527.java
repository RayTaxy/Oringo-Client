package map;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import oringo.module.AntibotModule;
import oringo.module.AutoBlazeModule;
import oringo.module.AutoIcePathModule;
import oringo.module.AutoIceSprayModule;
import oringo.module.AutoMiddleModule;
import oringo.module.AutoRequeueModule;
import oringo.module.AutoSoulcryModule;
import oringo.module.DungeonMapModule;
import oringo.module.FDSwapperModule;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.HideSummonsModule;
import oringo.module.HitboxesModule;
import oringo.module.KillAuraModule;
import oringo.module.KuudraHelperModule;
import oringo.module.NBTCopyModule;
import oringo.module.NoCarpetModule;

public class Class527 extends Class283 {
   public static final Class343 field2 = (new Class301()).method2(500L).method1(200L);
   public static final Class527 df_ = new Class527();
   public static final Class447 field4 = new Class447();
   public float field5 = 0.8F;
   public static EntityLivingBase field6;

   public void method0(EntityLivingBase var1) {
      if (var1 != null) {
         field2.method0(field6 != null);
      }

      if (field2.a_(700L) || var1 != null) {
         field6 = var1;
      }

      if (field6 != null) {
         super.method2();
         int var2 = (int)this.k_();
         int var3 = (int)this.method15();
         float var4 = this.field5 + (AutoIcePathModule.method6() - this.field5) / 7.0F;
         byte var5 = 36;
         int var6 = (50 - var5) / 2;
         ScaledResolution var7 = new ScaledResolution(field0);
         GL11.glPushMatrix();
         AutoIceSprayModule.method0((float)(var2 + 75), (float)(var3 + 25));
         if (DungeonMapModule.method7().field3.method1()) {
            GlStateManager.disableAlpha();

            for(float var8 = 0.0F; var8 < 3.0F; var8 += 0.5F) {
               float var9 = (double)(var2 + 75) > (double)var7.getScaledWidth() / 2.0D ? var8 : -var8;
               NBTCopyModule.method0((float)var2 - var9, (float)var3 + var8, 150.0F, 50.0F, 5.0F, new Color(20, 20, 20, 10));
            }

            GlStateManager.enableAlpha();
            boolean var10 = field2.method0() != 1.0F;
            if (var10) {
               AutoSoulcryModule.method3();
               FDSwapperModule.method5();
               HitboxesModule.method0((double)var2, (double)var3, 150.0D, 50.0D, 10.0D, Color.black.getRGB());
               KuudraHelperModule.method0(1);
               GL11.glPopMatrix();
            }

            NoCarpetModule.method0((float)var2, (float)var3, 150.0F, 50.0F, 5.0F);
            HitboxesModule.method0((double)var2, (double)var3, 150.0D, 50.0D, 5.0D, (new Color(70, 70, 70, 70)).getRGB());
            if (var10) {
               GL11.glPushMatrix();
               AutoIceSprayModule.method0((float)(var2 + 75), (float)(var3 + 25));
               HideSummonsModule.method3();
            }
         }

         if (field0.currentScreen instanceof GuiChat && this.j_()) {
            AutoRequeueModule.method0((float)var2, (float)var3, 150.0F, 50.0F, 10.0F, 2.0F, (new Color(50, 50, 50, 30)).getRGB(), Color.white.getRGB());
         } else {
            NBTCopyModule.method0((float)var2, (float)var3, 150.0F, 50.0F, 5.0F, new Color(50, 50, 50, 30));
         }

         this.method1(var5, var6);
         Class311.field0.method3(ChatFormatting.stripFormatting(field6.getName()), (float)(var2 + 5 + var5 + 3), (float)(var3 + var6 + 1), Class362.field7.method0(0.0F).getRGB());
         Class311.field12.method3((double)((int)(field6.getDistanceToEntity(field0.thePlayer) * 10.0F)) / 10.0D + "m", (float)(var2 + 5 + var5 + 4), (float)(var3 + var6 + 6) + Class311.field12.method0(), Color.white.getRGB());
         String var11 = (int)(AutoIcePathModule.method6() * 100.0F) + "%";
         Class311.field12.method0(var11, (float)(var2 + var5 + 9) + 45.0F, (float)(var3 + var6 + 29) - Class311.field12.method7(), Color.white.getRGB());
         NBTCopyModule.method0((float)(var2 + var5 + 9), (float)(var3 + var6 + 30), 90.0F, 5.0F, 3.0F, new Color(20, 20, 20, 100));
         NBTCopyModule.method0((float)(var2 + var5 + 9), (float)(var3 + var6 + 30), 90.0F * var4, 5.0F, 3.0F, Class362.field7.method0(0.0F));
         if (field4.method0(16L, true)) {
            this.field5 = var4;
         }

         GL11.glPopMatrix();
      }
   }

   public void method1(int var1, int var2) {
      float var3 = (float)field6.hurtTime - FrozenTreasureESPModule.method5().renderPartialTicks;
      int var4 = (int)this.k_();
      int var5 = (int)this.method15();
      GL11.glPushMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      if (var3 > 0.0F && !field6.isDead) {
         float var6 = var3 * 20.0F;
         AutoBlazeModule.method0(new Color(255, (int)(255.0F - var6), (int)(255.0F - var6)));
         AutoMiddleModule.method0((double)(var4 + 25), (double)(var5 + 25), 1.0D - (double)(var3 / (float)field6.maxHurtTime) * 0.2D);
      }

      AntibotModule.method0(var4 + 5, var5 + var2, 3.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, field6);
      AntibotModule.method0(var4 + 5, var5 + var2, 15.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, field6);
      GL11.glPopMatrix();
   }

   public Class527() {
      super(DungeonMapModule.method7().field2, DungeonMapModule.method7().field7);
      this.method1(150.0F, 50.0F);
   }

   public void method2() {
      this.method0((EntityLivingBase)(KillAuraModule.field24 == null && DungeonMapModule.method7().field6 == null && field0.currentScreen instanceof GuiChat ? field0.thePlayer : (KillAuraModule.field24 == null && !DungeonMapModule.method7().field5.method1() ? DungeonMapModule.method7().field6 : KillAuraModule.field24)));
   }

   public boolean method1() {
      return DungeonMapModule.method7().method44();
   }
}
