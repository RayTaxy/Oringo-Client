package oringo.module;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import map.Class12;
import map.Class282;
import map.Class34;
import map.Class350;
import map.Class361;
import map.Class434;
import map.Class479;
import map.Class496;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.command.ReplyCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class75;

public class AutoWaterModule extends Module {
   public BlockPos field0;
   public BlockPos bw_;
   public boolean field2;
   public boolean field3;
   public static final Gson field4 = (new GsonBuilder()).setPrettyPrinting().registerTypeAdapter(BlockPos.class, new AutoWaterModule.Class1((AutoWaterModule$1)null)).create();
   public int field5;
   public static final List field6;
   public int field7;
   public boolean field8;
   public final List field9 = Lists.newArrayList();
   public EnumFacing bB_;
   public AutoWaterModule.Class3 field11;

   public static boolean lambda$null$0(String var0) {
      return ChatFormatting.stripFormatting(var0).contains("Ether Transmission");
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.bB_ = null;
      this.field11 = null;
      this.field0 = null;
      this.field8 = false;
      this.bw_ = null;
      this.field9.clear();
      this.field7 = 0;
      this.field2 = false;
   }

   public static Minecraft access$400() {
      return field58;
   }

   public static Minecraft access$500() {
      return field58;
   }

   public static void method0(String var0, double var1, double var3, double var5) {
      FontRenderer var7 = Oringo.field9.fontRendererObj;
      float var8 = Math.max(2.0F, BearAuraModule.method0(var1, var3, var5) / 5.0F);
      float var9 = 0.016666668F * var8;
      GlStateManager.pushMatrix();
      GlStateManager.translate(var1 - Oringo.field9.getRenderManager().viewerPosX, var3 - Oringo.field9.getRenderManager().viewerPosY, var5 - Oringo.field9.getRenderManager().viewerPosZ);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-Oringo.field9.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(Oringo.field9.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(-var9, -var9, var9);
      GlStateManager.disableLighting();
      GlStateManager.depthMask(false);
      GlStateManager.disableDepth();
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      byte var10 = 0;
      int var11 = var7.getStringWidth(var0) / 2;
      GlStateManager.disableTexture2D();
      Class479.field3.begin(7, DefaultVertexFormats.POSITION_COLOR);
      Class479.field3.pos((double)(-var11 - 1), (double)(-1 + var10), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      Class479.field3.pos((double)(-var11 - 1), (double)(8 + var10), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      Class479.field3.pos((double)(var11 + 1), (double)(8 + var10), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      Class479.field3.pos((double)(var11 + 1), (double)(-1 + var10), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
      Class479.field1.draw();
      GlStateManager.enableTexture2D();
      var7.drawStringWithShadow(var0, (float)(-var7.getStringWidth(var0)) / 2.0F, (float)var10, -1);
      GlStateManager.enableDepth();
      GlStateManager.depthMask(true);
      GlStateManager.enableLighting();
      GlStateManager.disableBlend();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public void method7() {
      Class12 var1 = FragHelperModule.method0((int)field58.thePlayer.posX, (int)field58.thePlayer.posZ);
      this.bw_ = new BlockPos(var1.method1(), 0, var1.method0());
      int var4;
      if (this.bB_ == null) {
         BlockPos var2 = this.bw_.up(56);
         EnumFacing[] var3 = EnumFacing.HORIZONTALS;
         var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            EnumFacing var6 = var3[var5];
            BlockPos var7 = var2.offset(var6, 7);
            if (field58.theWorld.getBlockState(var7).getBlock() == Blocks.chest) {
               this.bB_ = var6;
               method3(this.bB_.getName());
               break;
            }
         }

         if (this.bB_ == null) {
            return;
         }
      }

      List var8 = AutoWaterModule.Class2.method0(this.bw_, this.bB_);
      if (var8.size() == 3) {
         StringBuilder var9 = new StringBuilder();
         Iterator var10 = var8.iterator();

         while(var10.hasNext()) {
            AutoWaterModule.Class2 var12 = (AutoWaterModule.Class2)var10.next();
            var9.append(var12.ordinal());
         }

         for(var4 = 77; var4 <= 78 && this.field11 == null; ++var4) {
            BlockPos var13 = this.bw_.offset(this.bB_, 11).up(var4);
            Block var14 = field58.theWorld.getBlockState(var13.offset(this.bB_.rotateY())).getBlock();
            if (var14 == Blocks.air) {
               var14 = field58.theWorld.getBlockState(var13.offset(this.bB_).offset(this.bB_.rotateY())).getBlock();
            }

            Block var15 = field58.theWorld.getBlockState(var13.offset(this.bB_.rotateYCCW())).getBlock();
            if (var15 == Blocks.air) {
               var15 = field58.theWorld.getBlockState(var13.offset(this.bB_).offset(this.bB_.rotateYCCW())).getBlock();
            }

            this.field11 = AutoWaterModule.Class3.method0(var14, var15);
         }

         if (this.field11 == null) {
            this.field8 = true;
            method2("Unknown Water Board variant!");
         } else {
            List var11 = (List)((Map)field6.get(this.field11.ordinal())).get(var9.toString());
            if (var11 == null) {
               this.field8 = true;
               method2("Unable to find a solution for water board! " + var9);
            } else {
               this.field9.clear();
               this.field9.addAll(var11);
            }
         }
      }
   }

   public static boolean lambda$onMotionUpdatePost$1(ItemStack var0) {
      return var0.getTooltip(field58.thePlayer, false).stream().anyMatch(AutoWaterModule::lambda$null$0);
   }

   public AutoWaterModule() {
      super("Auto Water", Category.dungeon, SubCategory.puzzle, "Completes the water board puzzle");
   }

   public static void method8() {
      Class434.field2 = true;
      if (Class434.field0 != -1) {
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GlStateManager.enableTexture2D();
         GlStateManager.bindTexture(Class434.field0);
         CameraModule.method5();
         GlStateManager.disableBlend();
      }
   }

   static {
      try {
         InputStream var0 = AutoWaterModule.class.getResourceAsStream("/assets/oringoclient/waterboard.json");
         Throwable var1 = null;

         try {
            InputStreamReader var2 = new InputStreamReader(var0);
            Throwable var3 = null;

            try {
               field6 = (List)field4.fromJson(var2, (new AutoWaterModule$1()).getType());
            } catch (Throwable var28) {
               var3 = var28;
               throw var28;
            } finally {
               if (var2 != null) {
                  if (var3 != null) {
                     try {
                        var2.close();
                     } catch (Throwable var27) {
                        var3.addSuppressed(var27);
                     }
                  } else {
                     var2.close();
                  }
               }

            }
         } catch (Throwable var30) {
            var1 = var30;
            throw var30;
         } finally {
            if (var0 != null) {
               if (var1 != null) {
                  try {
                     var0.close();
                  } catch (Throwable var26) {
                     var1.addSuppressed(var26);
                  }
               } else {
                  var0.close();
               }
            }

         }

      } catch (Exception var32) {
         throw new IllegalStateException("Failed to load waterboard solutions!", var32);
      }
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (Class496.field5 && SecretAuraModule.w_("Board") && !this.field8) {
         if (this.field11 == null) {
            this.method7();
            this.field7 = 0;
         }

         if (this.field2 && !this.field9.isEmpty()) {
            ++this.field7;
            if (this.field7 > 400) {
               PopupAnimationModule.method2("Timed out!");
               this.field9.clear();
               this.method0((Class270)null);
            } else {
               AutoWaterModule.Class5 var2 = (AutoWaterModule.Class5)this.field9.get(0);
               if (AutoWaterModule.Class5.field1(var2).method0(this.field7, this.bw_, this.bB_)) {
                  Class34 var3 = ReplyCommand.method0((new Vec3(AutoWaterModule.Class5.field0(var2).method1(this.bw_, this.bB_))).addVector(0.5D, 1.0D + (!field58.thePlayer.isSneaking() ? 0.08D : 0.0D), 0.5D));
                  var1.method5().method2(true).method0(var3);
                  this.field0 = AutoWaterModule.Class5.field0(var2).method0(this.bw_, this.bB_);
                  if (!this.field3) {
                     this.field5 = 2;
                  }

                  this.field3 = true;
               }

            }
         }
      }
   }

   public boolean method0(BlockPos var1) {
      return field58.thePlayer.getDistanceSq((double)var1.getX(), (double)((float)var1.getY() - field58.thePlayer.eyeHeight), (double)var1.getZ()) < 36.0D;
   }

   @SubscribeEvent
   public void method0(PlayerInteractEvent var1) {
      if (Class496.field5 && !this.field2 && !this.field9.isEmpty() && var1.action == Action.RIGHT_CLICK_BLOCK && var1.pos != null) {
         BlockPos var2 = var1.pos;
         if (var2.equals(AutoWaterModule.Class0.field1.method0(this.bw_, this.bB_))) {
            this.field2 = true;
            method2("Started water board!");
         }

      }
   }

   public void method4() {
      this.method0((Class270)null);
   }

   public static BlockPos access$300(BlockPos var0, EnumFacing var1) {
      return Class350.method0(var0, var1);
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field0 != null && this.method0(this.field0)) {
         if (Class361.method0((Class94)(new Class282(this.field0, new Vec3(this.field0), EnumFacing.DOWN)))) {
            this.field9.remove(0);
            if (this.field9.isEmpty()) {
               method2("Water Board finished!");
            }

            this.field0 = null;
            this.field3 = false;
         }
      } else {
         if (this.field3 && this.field5-- == 0) {
            int var2 = TrailModule.method0(AutoWaterModule::lambda$onMotionUpdatePost$1);
            if (var2 != -1 && !Class361.method0((Class94)(new Class350(var2)))) {
               ++this.field5;
            }
         }

      }
   }

   public static class Class1 extends TypeAdapter {
      private Class1() {
      }

      public BlockPos method0(JsonReader var1) throws IOException {
         var1.beginObject();
         var1.skipValue();
         int var2 = var1.nextInt();
         var1.skipValue();
         int var3 = var1.nextInt();
         var1.skipValue();
         int var4 = var1.nextInt();
         var1.endObject();
         return new BlockPos(var2, var3, var4);
      }

      public Object method1(JsonReader var1) throws IOException {
         return this.method0(var1);
      }

      public void method0(JsonWriter var1, BlockPos var2) throws IOException {
         var1.beginObject();
         var1.name("x").value((long)var2.getX());
         var1.name("y").value((long)var2.getY());
         var1.name("z").value((long)var2.getZ());
         var1.endObject();
      }

      Class1(AutoWaterModule$1 var1) {
         this();
      }

      public void method0(JsonWriter var1, Object var2) throws IOException {
         this.method0(var1, (BlockPos)var2);
      }
   }

   public static enum Class2 {
      field0,
      field1,
      field2,
      field3;

      private static final AutoWaterModule.Class2[] field5 = new AutoWaterModule.Class2[]{field3, field0, field1, field4, field2};
      field4;

      private boolean method1(BlockPos var1, EnumFacing var2) {
         return AutoWaterModule.access$500().theWorld.getBlockState(var1.up(56).offset(var2, this.ordinal())).getBlock() == Blocks.wool;
      }

      public static List method0(BlockPos var0, EnumFacing var1) {
         ArrayList var2 = Lists.newArrayList();
         AutoWaterModule.Class2[] var3 = values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            AutoWaterModule.Class2 var6 = var3[var5];
            if (var6.method1(var0, var1)) {
               var2.add(var6);
            }
         }

         return var2;
      }
   }

   public static enum Class3 {
      field0(Blocks.quartz_block, Blocks.diamond_block),
      field1(Blocks.gold_block, Blocks.hardened_clay),
      field2(Blocks.gold_block, Blocks.quartz_block);

      private final Block field4;
      field3(Blocks.emerald_block, Blocks.quartz_block);

      private final Block field5;
      private static final AutoWaterModule.Class3[] field6 = new AutoWaterModule.Class3[]{field1, field3, field0, field2};

      private Class3(Block var3, Block var4) {
         this.field4 = var3;
         this.field5 = var4;
      }

      public static AutoWaterModule.Class3 method0(Block var0, Block var1) {
         AutoWaterModule.Class3[] var2 = values();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            AutoWaterModule.Class3 var5 = var2[var4];
            if ((var5.field4 == var0 || var5.field4 == var1) && (var5.field5 == var0 || var5.field5 == var1)) {
               return var5;
            }
         }

         return null;
      }
   }

   public static enum Class0 {
      field0(0, 61, 5);

      private final int field7;
      field1(-10, 60, 0);

      private static final AutoWaterModule.Class0[] field8 = new AutoWaterModule.Class0[]{field5, field3, field2, field6, field0, field4, field1};
      field2(5, 61, -5),
      field3(0, 61, -5),
      field4(-5, 61, 5),
      field5(-5, 61, -5);

      private final int field9;
      field6(5, 61, 5);

      private final int field10;

      private Class0(int var3, int var4, int var5) {
         this.field9 = var3;
         this.field7 = var5;
         this.field10 = var4;
      }

      public BlockPos method0(BlockPos var1, EnumFacing var2) {
         return var1.offset(var2, this.field9).offset(var2.rotateY(), this.field7).up(this.field10);
      }

      public BlockPos method1(BlockPos var1, EnumFacing var2) {
         return this == field1 ? this.method0(var1, var2).offset(var2.getOpposite()).down() : var1.offset(var2, this.field9).offset(var2.rotateY(), this.field7 == 0 ? 0 : (this.field7 > 0 ? 3 : -3)).up(58);
      }
   }

   public static class Class4 {
      @SerializedName("ticks")
      private int Y_ = 0;
      @SerializedName("water")
      private boolean field1 = false;
      @SerializedName("block_pos")
      private BlockPos cB_ = null;

      private Class4() {
      }

      public boolean method0(int var1, BlockPos var2, EnumFacing var3) {
         if (var1 < this.Y_) {
            return false;
         } else if (this.cB_ == null) {
            return true;
         } else {
            Block var4 = AutoWaterModule.access$400().theWorld.getBlockState(var2.add(AutoWaterModule.access$300(this.cB_, var3.getOpposite()))).getBlock();
            return this.field1 ? var4 == Blocks.water || var4 == Blocks.flowing_water : var4 == Blocks.air;
         }
      }
   }

   private static class Class5 {
      @SerializedName("condition")
      private AutoWaterModule.Class4 field0;
      @SerializedName("lever")
      private AutoWaterModule.Class0 field1;

      static AutoWaterModule.Class0 field0(AutoWaterModule.Class5 var0) {
         return var0.field1;
      }

      static AutoWaterModule.Class4 field1(AutoWaterModule.Class5 var0) {
         return var0.field0;
      }
   }
}
