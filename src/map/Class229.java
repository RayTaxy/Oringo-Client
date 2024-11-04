package map;

import com.google.common.collect.BiMap;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.block.BlockSilverfish.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.renderer.vertex.VertexFormatElement.EnumUsage;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec4b;
import org.lwjgl.opengl.GL11;
import oringo.module.AutoFarmModule;
import oringo.module.AutoQuizModule;
import oringo.module.DungeonESPModule;
import oringo.module.ResetVLModule;
import oringo.module.ScreenShotModule;
import oringo.module.ServerBeamerModule;
import oringo.module.SimulatorAuraModule;

public class Class229 {
   public final Vec4b field0;
   public Vec4b field1;
   public final NetworkPlayerInfo field2;
   public final String field3;
   public final String field4;
   public static final Class447 field5 = new Class447();

   public String d_() {
      return this.field3;
   }

   public static AutoFarmModule method1() {
      return AutoFarmModule.field39;
   }

   public Class229(NetworkPlayerInfo var1, Vec4b var2, String var3, String var4) {
      this.field2 = var1;
      this.field0 = var2;
      this.field4 = var3;
      this.field3 = var4;
   }

   public static boolean method0(EntityArmorStand var0) {
      if ((double)var0.getDistanceToEntity(AutoQuizModule.field58.thePlayer) > AutoQuizModule.field2.method0()) {
         return false;
      } else {
         boolean var1 = false;
         String var2 = var0.getDisplayName().getUnformattedText();
         String[] var3 = AutoQuizModule.field0;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            if (var2.contains(var6)) {
               var1 = true;
               break;
            }
         }

         return (var2.contains("ⓐ") || var2.contains("ⓑ") || var2.contains("ⓒ")) && var1;
      }
   }

   public void method0(Vec4b var1) {
      this.field1 = var1;
   }

   public float method2() {
      float var1 = ScreenShotModule.method0(this.method3());
      float var2 = ScreenShotModule.method0(this.field0);
      return var1 + (var2 - var1) * ServerBeamerModule.method5();
   }

   public Vec4b method3() {
      return this.field1 == null ? this.field0 : this.field1;
   }

   public static void method0(Class var0, Class465.Class0 var1) {
      BiMap var2 = ((BiMap)Class465.field0.get(var1)).inverse();
      if (var2.containsKey(var0)) {
         throw new IllegalStateException("Packet " + var0.getSimpleName() + " already registered!");
      } else {
         var2.put(var0, var2.size());
      }
   }

   public NetworkPlayerInfo method4() {
      return this.field2;
   }

   public float method5() {
      return SimulatorAuraModule.method0(this.field0);
   }

   public String method45() {
      return this.field4;
   }

   public Vec4b method7() {
      return this.field0;
   }

   public static Class178 method0(int var0, int var1) {
      IBlockState var2 = Class198.field2.theWorld.getBlockState(new BlockPos(var0, 69, var1));
      Block var3 = var2.getBlock();
      if (var3 == Blocks.monster_egg && var2.getValue(BlockSilverfish.VARIANT) == EnumType.CHISELED_STONEBRICK) {
         return new Class178(Class424.field3);
      } else if (!DungeonESPModule.method0(var0, var1)) {
         return new Class178(Class424.field5);
      } else if (var3 == Blocks.coal_block) {
         return new Class178(Class424.field2);
      } else {
         return var3 == Blocks.stained_hardened_clay ? new Class178(Class424.field0) : new Class178(Class424.field1);
      }
   }

   public float k_() {
      float var1 = ResetVLModule.method0(this.method3());
      float var2 = ResetVLModule.method0(this.field0);
      return var1 + (var2 - var1) * ServerBeamerModule.method5();
   }

   public ResourceLocation method9() {
      return this.field2.getLocationSkin();
   }

   public static void method0(ArrayList var0, int var1, float var2) {
      float var3 = (float)(var1 >> 24 & 255) / 255.0F;
      float var4 = (float)(var1 >> 16 & 255) / 255.0F;
      float var5 = (float)(var1 >> 8 & 255) / 255.0F;
      float var6 = (float)(var1 & 255) / 255.0F;
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var4, var5, var6, var3);
      if (Class479.field4 == null) {
         Class479.field4 = new VertexFormat();
         Class479.field4.addElement(new VertexFormatElement(0, net.minecraft.client.renderer.vertex.VertexFormatElement.EnumType.FLOAT, EnumUsage.POSITION, 3));
      }

      if (Class479.field0 != var2) {
         Class479.field0 = var2;
         Class479.field3.begin(9, DefaultVertexFormats.POSITION);
         Iterator var7 = var0.iterator();

         while(var7.hasNext()) {
            float[] var8 = (float[])var7.next();
            Class479.field3.pos((double)var8[0], (double)var8[1], 0.0D).endVertex();
         }

         Class479.field2 = new VertexBuffer(Class479.field4);
         Class479.field3.finishDrawing();
         Class479.field3.reset();
         Class479.field2.bufferData(Class479.field3.getByteBuffer());
      }

      Class479.field2.bindBuffer();
      GL11.glEnableClientState(32884);
      GL11.glVertexPointer(3, 5126, 12, 0L);
      Class479.field2.drawArrays(9);
      Class479.field2.unbindBuffer();
      GL11.glDisableClientState(32884);
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }
}
