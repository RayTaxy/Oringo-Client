package map;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import org.lwjgl.opengl.GL11;
import oringo.command.PlayerCommand;
import oringo.module.AutoFarmModule;
import oringo.module.HitboxesModule;
import oringo.module.LightningDetectModule;
import oringo.module.NBTCopyModule;
import oringo.module.ReachModule;

public class Class532 extends Class283 {
   public int field2;
   public final Class138[] dg_;
   public DecimalFormat e_;
   public final AutoFarmModule field5 = Class229.method1();

   public int method0() {
      Iterator var1 = Class322.method0().iterator();
      String var2 = null;
      String var3 = null;

      while(var1.hasNext()) {
         String var4 = (String)var1.next();
         if (var4.contains(" with ")) {
            var3 = var4;
         }

         if (var4.startsWith("○ " + this.field5.field25.method4().toLowerCase())) {
            var2 = var4;
         }
      }

      if (var3 != null && var2 != null) {
         String[] var10 = var2.split(" ");
         String[] var5 = var3.split(" ");
         if (var5[var5.length - 1].equals("ranking...")) {
            return 0;
         } else {
            String var6 = var10[var10.length - 1];
            int var7 = Integer.parseInt(var5[var5.length - 1].replaceAll(",", ""));
            double var8 = (double)Integer.parseInt(var6.split("m")[0]) + (double)Integer.parseInt(var6.split("m")[1].split("s")[0]) / 60.0D;
            return (int)((double)var7 + var8 * (double)this.field5.field4);
         }
      } else {
         return 0;
      }
   }

   public int method17() {
      Iterator var1 = field0.getNetHandler().getPlayerInfoMap().iterator();

      while(var1.hasNext()) {
         NetworkPlayerInfo var2 = (NetworkPlayerInfo)var1.next();
         if (var2 != null) {
            IChatComponent var3 = var2.getDisplayName();
            if (var3 != null) {
               String var4 = var3.getUnformattedText();
               if (var4.startsWith(" Farming Fortune: ☘")) {
                  return Integer.parseInt(var4.split("☘")[1]);
               }
            }
         }
      }

      return 0;
   }

   public static String method1(JsonElement var0) {
      return var0.getAsString();
   }

   public Class532() {
      super(Class229.method1().field46, Class229.method1().field22);
      this.dg_ = new Class138[]{new Class158(), new Class223(), Class304.field0, new Class88()};
      this.field2 = 45;
      this.e_ = null;
      this.method1(200.0F, 150.0F);
   }

   public void method2() {
      super.method2();
      long var3;
      int var5;
      Class138[] var6;
      int var7;
      int var8;
      Class138 var9;
      if (this.field5.field44.method0(1)) {
         LightningDetectModule.method0(this.k_(), this.method15(), this.method7(), this.method13(), 5.0F, 1.5F);
         GL11.glTranslated((double)this.k_(), (double)this.method15(), 0.0D);
         GL11.glPushMatrix();
         ItemStack var12 = this.method18();
         if (var12 != null) {
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            double var13 = 2.0D;
            GL11.glScaled(var13, var13, var13);
            RenderHelper.enableGUIStandardItemLighting();
            field0.getRenderItem().renderItemIntoGUI(var12, 4, 4);
            RenderHelper.disableStandardItemLighting();
            GL11.glScaled(1.0D / var13, 1.0D / var13, 1.0D / var13);
         }

         field0.fontRendererObj.drawString("Farmed this session: §a" + this.method0(this.field5.field45), 48, 8, -1);
         field0.fontRendererObj.drawString("Last minute: §a" + this.method1(this.field5.field4), 48, 18, -1);
         field0.fontRendererObj.drawString("Per hour: §a" + this.method1(this.field5.field4 * 60L), 48, 28, -1);
         this.field2 = 45;
         int var14 = this.field5.method13().method4();
         var3 = this.field5.field4 * 60L * 24L * (long)var14;
         this.method0("Raw profit this session: §6" + this.method0(this.field5.field40));
         this.method0("Raw profit/h: §6" + this.method0(this.field5.field4 * 60L * (long)var14));
         this.method0("Raw profit/d: §6" + this.method0(var3));
         this.method0("BPS: §6" + (double)((int)((double)this.field5.field32 / 60.0D * 10.0D)) / 10.0D);
         var5 = this.method0();
         if (var5 != 0) {
            this.method0("Estimated contest result: §e" + this.method1((long)var5));
         }

         var6 = this.dg_;
         var7 = var6.length;

         for(var8 = 0; var8 < var7; ++var8) {
            var9 = var6[var8];
            if (var9.method0()) {
               this.field2 += 7;
               var3 += var9.method0(this, this.field5.field4 * 60L * 24L, this.field5.field32 * 60L * 24L);
            }
         }

         this.field2 += 7;
         this.method0("Final profit/d: §6" + this.method0(var3));
         this.method1(200.0F, (float)(this.field2 + 8));
         int var17 = (int)(32.0D * this.field5.field18);
         NBTCopyModule.method0(8.0F, 8.0F, 32.0F, 32.0F, 2.0F, PlayerCommand.method0(Color.GRAY, 60));
         NBTCopyModule.method0(8.0F, (float)(40 - var17), 32.0F, (float)var17, 2.0F, PlayerCommand.method0(Color.WHITE, 120));
         GL11.glPopMatrix();
         GL11.glTranslated((double)(-this.k_()), (double)(-this.method15()), 0.0D);
      } else {
         Class195 var1 = Class311.field8;
         ArrayList var2 = Lists.newArrayList();
         var2.add("Collection this session: §a" + this.method0(this.field5.field45));
         var2.add("Collection/h: §a" + this.method1(this.field5.field4 * 60L));
         var2.add("Profit this session: §6" + this.method0(this.field5.field40));
         var2.add("Profit/h: §6" + this.method0(this.field5.field53 * 60L));
         var3 = this.field5.field53 * 60L * 24L;
         var2.add("Profit/d: §6" + this.method0(var3));
         var5 = this.method0();
         if (var5 != 0) {
            var2.add("Estimated contest result: §e" + this.method1((long)var5));
         }

         var6 = this.dg_;
         var7 = var6.length;

         for(var8 = 0; var8 < var7; ++var8) {
            var9 = var6[var8];
            if (var9.method0()) {
               var3 += var9.method0(this.field5.field4 * 60L * 24L, this.field5.field32 * 60L * 24L);
               var2.add(var9.method1(this.field5.field4 * 60L * 24L, this.field5.field32 * 60L * 24L));
            }
         }

         var2.add("Total profit/d: §6" + this.method0(var3));
         var2.add("Session time: " + ChatFormatting.AQUA + ReachModule.method0(Class229.method1().field8, TimeUnit.MILLISECONDS));
         float var15 = 100.0F;
         Iterator var16 = var2.iterator();

         float var21;
         while(var16.hasNext()) {
            String var19 = (String)var16.next();
            var21 = var1.method0(var19);
            if (var21 > var15) {
               var15 = var21;
            }
         }

         float var18 = var1.method3() * (float)var2.size() + var1.method7();
         float var20 = 5.0F;
         this.method1(var15 + var20 * 2.0F, var18 + var20 * 2.0F);
         var18 = this.method13();
         var15 = this.method7();
         var21 = this.k_();
         float var10 = this.method15();
         LightningDetectModule.method0(var21, var10, var15, var18, 5.0F, 1.5F);
         var1.method0("Statistics", var21 + var15 / 2.0F, var10 + var20, -1);

         int var11;
         for(var11 = 0; var11 < var2.size(); ++var11) {
            var1.method3((String)var2.get(var11), var21 + var20, var10 + var20 + (float)(var11 + 1) * var1.method3(), -1);
         }

         var10 = 45.0F;
         var11 = (int)(32.0D * this.field5.field18);
      }
   }

   public String method0(long var1) {
      return HitboxesModule.method0(var1);
   }

   public ItemStack method18() {
      // $FF: Couldn't be decompiled
   }

   public String method1(long var1) {
      if (this.e_ == null) {
         DecimalFormat var3 = new DecimalFormat("#,###");
         DecimalFormatSymbols var4 = var3.getDecimalFormatSymbols();
         var4.setGroupingSeparator(',');
         var3.setDecimalFormatSymbols(var4);
         this.e_ = var3;
      }

      return this.e_.format(var1);
   }

   public void method0(String var1) {
      field0.fontRendererObj.drawString(var1, 8, this.field2, -1);
      this.field2 += 10;
   }

   public boolean method1() {
      return this.field5.method44() && !this.field5.field44.method0(0);
   }
}
