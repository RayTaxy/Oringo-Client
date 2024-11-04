package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class1;
import map.Class12;
import map.Class198;
import map.Class208;
import map.Class228;
import map.Class278;
import map.Class28;
import map.Class34;
import map.Class350;
import map.Class361;
import map.Class447;
import map.Class462;
import map.Class469;
import map.Class514;
import map.Class94;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.opengl.GL11;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.event.Class307;
import oringo.notification.Notifications;
import oringo.setting.BooleanSetting;
import oringo.setting.ButtonSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class BrushModule extends Module {
   public final BooleanSetting field0;
   private final ButtonSetting field3;
   private final ColorSetting field4;
   private static BrushModule field5;
   private final ColorSetting field6;
   private final DoubleSetting field7;
   private static final VertexBuffer field8;
   private final BooleanSetting field9;
   private int field10;
   private final BooleanSetting field11;
   public static boolean field1;
   public static BlockPos field2;
   private final DoubleSetting field12;
   private final ColorSetting field13;
   private static int field14;
   private static final int field15;
   private final ButtonSetting field16;
   private final ConcurrentHashMap field17 = new ConcurrentHashMap();
   private final ColorSetting field18;
   private final ButtonSetting field19;
   private final Class447 field20;

   @SubscribeEvent
   public void method0(Class307 var1) {
      if (this.field11.method1()) {
         BlockPos var2 = ThystHiderModule.method3();
         Class1 var3 = (Class1)this.field17.get(var2);
         if (var3 != null) {
            field2 = new BlockPos(-1, -1, -1);
            field1 = true;
         }
      }
   }

   private void lambda$reloadRooms$5(Class208 var1) {
      if (var1 != null && var1.field3 != null) {
         var1.field3.field1.forEach(this::field0);
      }
   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void b_(Class210.Class1 var1) {
      if (!this.field0.method1()) {
         BlockPos var2 = ThystHiderModule.method3();
         if (field2.equals(var2)) {
            this.field10 = 1;
         } else {
            Class1 var3 = (Class1)this.field17.get(var2);
            if (var3 == null) {
               field1 = false;
               field2 = BlockPos.ORIGIN;
               this.field20.o_();
            } else if (!var3.a_ || !field2.equals(BlockPos.ORIGIN)) {
               if (var3.field1 != null) {
                  Vec3 var4 = var3.field1;
                  Class12 var5 = FragHelperModule.method0(var2.getX(), var2.getZ());
                  Class208 var6 = LividFinderModule.method0(var5.method1(), var5.method0());
                  if (var6 != null) {
                     var4 = var6.method0(var4, false).addVector((double)var5.method1() + 0.5D, 0.0D, (double)var5.method0() + 0.5D);
                  }

                  var1.method0(method0(var4)).method2(true).method5();
               }

               if ((field1 || !var3.field5) && this.field20.a_((long)this.field7.method3()) && this.field10-- != 1) {
                  this.field10 = 1;
                  field1 = false;
                  this.field20.o_();
                  String var7 = var3.field2;
                  if (var7 != null) {
                     this.method0(var7.split(", "));
                  }

                  field2 = var2;
                  if (var3.field1 != null) {
                     int var8 = TrailModule.method0(BrushModule::lambda$onMotionPre$8);
                     if (var8 != -1) {
                        Class361.method0((Class94)(new Class350(var8)));
                     } else {
                        Class514.method0("Unable to find an item with etherwarp!", 5000, Notifications.Class1.field0);
                     }
                  }

               }
            }
         }
      }
   }

   public void method0(BlockPos var1) {
      if (this.method44()) {
         this.field17.remove(var1);
      }
   }

   private static void lambda$null$1(Class208 var0) {
      if (var0 != null) {
         var0.field3 = null;
         var0.field10 = false;
      }
   }

   private static boolean lambda$null$7(String var0) {
      return ChatFormatting.stripFormatting(var0).contains("Ether Transmission");
   }

   public void method5() {
      this.o_();
      Class469.method1().forEach(this::lambda$reloadRooms$5);
   }

   private static void field1(BlockPos var0, Class1 var1) {
      if (var1.field3 != null) {
         field58.theWorld.setBlockState(var0, var1.field3.getStateFromMeta(var1.field0));
      }
   }

   private static boolean lambda$onMotionPre$8(ItemStack var0) {
      return var0.getTooltip(field58.thePlayer, false).stream().anyMatch(BrushModule::lambda$null$7);
   }

   private void lambda$new$2() {
      Class469.method1().forEach(BrushModule::lambda$null$1);
      Class28.field3.clear();
      Class28.field2.clear();
      PestESPModule.n_();
      this.method5();
   }

   private void field0(BlockPos var1, List var2, List var3, BlockPos var4, Class1 var5) {
      if (var4.distanceSq(var1) <= this.field12.method0() * this.field12.method0()) {
         if (var5.field1 != null) {
            if (var5.a_) {
               var2.add((new Vec3(var4)).addVector(0.5D, 0.05D, 0.5D).subtract(field58.getRenderManager().viewerPosX, field58.getRenderManager().viewerPosY, field58.getRenderManager().viewerPosZ));
            } else {
               var3.add((new Vec3(var4)).addVector(0.5D, 0.05D, 0.5D).subtract(field58.getRenderManager().viewerPosX, field58.getRenderManager().viewerPosY, field58.getRenderManager().viewerPosZ));
            }

            Class12 var6 = FragHelperModule.method0(var4.getX(), var4.getZ());
            Class208 var7 = LividFinderModule.method0(var6.method1(), var6.method0());
            Vec3 var8 = var5.field1;
            if (var7 != null) {
               var8 = var7.method0(var8, false).addVector((double)var6.method1() + 0.5D, 0.05D, (double)var6.method0() + 0.5D);
            }

            Class228 var9 = new Class228((double)var4.getX() + 0.5D, (double)var4.getY() + 0.05D, (double)var4.getZ() + 0.5D);
            Class228 var10 = new Class228(var8);
            Class228 var11 = var10.method2(var9).method3(0.0D).method0().method1(0.6D);
            BlockPos var12 = new BlockPos(var8);
            if (this.field17.containsKey(var12)) {
               Class228 var13 = new Class228((double)var12.getX() + 0.5D, (double)var12.getY() + 0.05D, (double)var12.getZ() + 0.5D);
               Class228 var14 = var9.method2(var13).method3(0.0D).method0().method1(0.6D);
               AutoBeamsModule.method0(var9.method4(var11).method6(), var13.method4(var14).method6(), var5.field5 ? this.field18.method17() : this.field13.method17());
            } else {
               AutoBeamsModule.method0(var9.method4(var11).method6(), var10.method6(), var5.field5 ? this.field18.method17() : this.field13.method17());
            }
         }

         if (var5.field2 != null) {
            String[] var15 = var5.field2.split(", ");
            int var16 = var15.length * 13;
            String[] var17 = var5.field2.split(", ");
            int var18 = var17.length;

            for(int var19 = 0; var19 < var18; ++var19) {
               String var20 = var17[var19];
               var16 -= 13;
               AutoCrystalModule.method0(field58.thePlayer, var20, (double)var4.getX() + 0.5D - field58.getRenderManager().viewerPosX, (double)var4.getY() - 0.4D - field58.getRenderManager().viewerPosY + (double)((float)var16 / 50.0F), (double)var4.getZ() + 0.5D - field58.getRenderManager().viewerPosZ, 0);
            }
         }

      }
   }

   public void b_() {
      this.o_();
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START && field58.theWorld != null && field58.thePlayer.ticksExisted >= 20) {
         int var2 = Class198.field0.method5();
         if (field14 != var2) {
            Map var10000 = StringSetting.method0(var2);
            BrushModule var10001 = method7();
            var10000.forEach(var10001::field0);
            field14 = var2;
         }

         this.field17.forEach(BrushModule::field1);
      }
   }

   static {
      field2 = BlockPos.ORIGIN;
      field8 = new VertexBuffer(DefaultVertexFormats.POSITION);
      Tessellator var0 = Tessellator.getInstance();
      WorldRenderer var1 = var0.getWorldRenderer();
      var1.begin(2, DefaultVertexFormats.POSITION);

      for(int var2 = 0; var2 < 90; ++var2) {
         double var3 = Math.sin((double)var2 * 3.141592653589793D / 45.0D) * 0.6D;
         double var5 = Math.cos((double)var2 * 3.141592653589793D / 45.0D) * 0.6D;
         var1.pos(var3, 0.0D, var5).endVertex();
      }

      var1.finishDrawing();
      field8.bufferData(var1.getByteBuffer());
      field15 = var1.getVertexCount();
      var1.reset();
   }

   private void method0(String[] var1) {
      Pattern var2 = Pattern.compile("-yaw:x?(\\+|-?\\d+.?\\d+)");
      Class208 var3 = IceFillHelperModule.method0((int)field58.thePlayer.posX, (int)field58.thePlayer.posZ);
      String[] var4 = var1;
      int var5 = var1.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         String var7 = var4[var6];
         String var8 = var7;
         if (var7.toLowerCase(Locale.ENGLISH).startsWith(AutoRabbitModule.method1() + "use")) {
            String[] var9 = var7.split(" ");

            for(int var10 = 1; var10 < var9.length; ++var10) {
               Matcher var11 = var2.matcher(var9[var10]);
               if (var11.find()) {
                  String var12 = var11.group(1);
                  float var13 = Float.parseFloat(var12);
                  if (var3 != null && var3.field10) {
                     var13 += (float)var3.bB_.getHorizontalIndex() * 90.0F;
                  }

                  var9[var10] = var9[var10].replaceAll(var12, String.valueOf(var13));
               }
            }

            var8 = String.join(" ", var9);
         }

         if (ClientCommandHandler.instance.executeCommand(field58.thePlayer, var8) == 0) {
            field58.thePlayer.sendChatMessage(var8);
         }
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.o_();
   }

   public void o_() {
      this.field17.clear();
      field14 = -1;
   }

   private void lambda$new$3() {
      Class208 var1 = GhostBlocksModule.method5();
      if (var1 != null) {
         var1.field3 = null;
         var1.field10 = false;
         this.method5();
      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!this.field17.isEmpty()) {
         if (Minecraft.isRunningOnMac) {
            this.method11();
         } else {
            BlockPos var2 = ThystHiderModule.method3();
            ArrayList var3 = new ArrayList();
            ArrayList var4 = new ArrayList();
            if (this.field9.method1()) {
               GlStateManager.disableDepth();
            }

            this.field17.forEach(this::field0);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.disableTexture2D();
            GlStateManager.disableCull();
            GL11.glLineWidth(3.0F);
            GlStateManager.color((float)this.field6.method7() / 255.0F, (float)this.field6.method2() / 255.0F, (float)this.field6.method3() / 255.0F);
            Class278.method0(2, field8, field15, var3);
            GlStateManager.color((float)this.field4.method7() / 255.0F, (float)this.field4.method2() / 255.0F, (float)this.field4.method3() / 255.0F);
            Class278.method0(2, field8, field15, var4);
            GL11.glLineWidth(2.0F);
            GlStateManager.enableDepth();
            GlStateManager.enableCull();
            GlStateManager.enableTexture2D();
         }
      }
   }

   private void field0(Class208 var1, BlockPos var2, Class1 var3) {
      Class1 var10000 = (Class1)this.field17.put(var1.method0(var2, false).add(var1.field1, 0, var1.field5), var3);
   }

   public void field0(BlockPos var1, Class1 var2) {
      if (this.method44()) {
         this.field17.put(var1, var2);
      }
   }

   public void method4() {
      this.method5();
   }

   private static Class34 method0(Vec3 var0) {
      double var1 = var0.xCoord - field58.thePlayer.posX;
      double var3 = var0.yCoord - field58.thePlayer.posY - (double)field58.thePlayer.eyeHeight;
      if (!field58.thePlayer.isSneaking()) {
         var3 += 0.08D;
      }

      double var5 = var0.zCoord - field58.thePlayer.posZ;
      double var7 = (double)MathHelper.sqrt_double(var1 * var1 + var5 * var5);
      float var9 = (float)(Math.atan2(var5, var1) * 180.0D / 3.141592653589793D) - 90.0F;
      float var10 = (float)(-(Math.atan2(var3, var7) * 180.0D / 3.141592653589793D));
      return new Class34(var9, var10);
   }

   private void field0(BlockPos var1, BlockPos var2, Class1 var3) {
      if (var2.distanceSq(var1) <= this.field12.method0() * this.field12.method0()) {
         if (var3.field1 != null) {
            Class462.method0((new Vec3(var2)).addVector(0.5D, 0.05D, 0.5D), 0.6D, var3.a_ ? this.field4.method17() : this.field6.method17());
            Class12 var4 = FragHelperModule.method0(var2.getX(), var2.getZ());
            Class208 var5 = LividFinderModule.method0(var4.method1(), var4.method0());
            Vec3 var6 = var3.field1;
            if (var5 != null) {
               var6 = var5.method0(var6, false).addVector((double)var4.method1() + 0.5D, 0.05D, (double)var4.method0() + 0.5D);
            }

            Class228 var7 = new Class228((double)var2.getX() + 0.5D, (double)var2.getY() + 0.05D, (double)var2.getZ() + 0.5D);
            Class228 var8 = new Class228(var6);
            Class228 var9 = var8.method2(var7).method3(0.0D).method0().method1(0.6D);
            BlockPos var10 = new BlockPos(var6);
            if (this.field17.containsKey(var10)) {
               Class228 var11 = new Class228((double)var10.getX() + 0.5D, (double)var10.getY() + 0.05D, (double)var10.getZ() + 0.5D);
               Class228 var12 = var7.method2(var11).method3(0.0D).method0().method1(0.6D);
               AutoBeamsModule.method0(var7.method4(var9).method6(), var11.method4(var12).method6(), var3.field5 ? this.field18.method17() : this.field13.method17());
            } else {
               AutoBeamsModule.method0(var7.method4(var9).method6(), var8.method6(), var3.field5 ? this.field18.method17() : this.field13.method17());
            }
         }

         if (var3.field2 != null) {
            String[] var13 = var3.field2.split(", ");
            int var14 = var13.length * 13;
            String[] var15 = var3.field2.split(", ");
            int var16 = var15.length;

            for(int var17 = 0; var17 < var16; ++var17) {
               String var18 = var15[var17];
               var14 -= 13;
               AutoCrystalModule.method0(field58.thePlayer, var18, (double)var2.getX() + 0.5D - field58.getRenderManager().viewerPosX, (double)var2.getY() - 0.4D - field58.getRenderManager().viewerPosY + (double)((float)var14 / 50.0F), (double)var2.getZ() + 0.5D - field58.getRenderManager().viewerPosZ, 0);
            }
         }

      }
   }

   private void lambda$new$0() {
      StringSetting.method0(0).clear();
      PestESPModule.n_();
      this.method5();
   }

   private void method11() {
      BlockPos var1 = ThystHiderModule.method3();
      if (this.field9.method1()) {
         GlStateManager.disableDepth();
      }

      this.field17.forEach(this::field0);
      GlStateManager.enableDepth();
   }

   public static BrushModule method7() {
      return field5;
   }

   public BrushModule() {
      super("Brush", Category.dungeon, SubCategory.main, "Do .brush to see the commands");
      this.field6 = new ColorSetting("Ring Color", Color.CYAN, true);
      this.field4 = new ColorSetting("Chained Ring Color", Color.CYAN.darker(), true);
      this.field13 = new ColorSetting("Line Color", Color.CYAN, true);
      this.field18 = new ColorSetting("Await Secret Color", Color.WHITE, true);
      this.field12 = new DoubleSetting("Render distance", 64.0D, 16.0D, 256.0D, 16.0D);
      this.field9 = new BooleanSetting("Render through walls", true);
      this.field7 = new DoubleSetting("Delay", 0.0D, 0.0D, 500.0D, 50.0D);
      this.field11 = (BooleanSetting)(new BooleanSetting("Swing to retry", true)).method2("Swing to teleport again. Causes you to teleport when on a chained ring");
      this.field0 = (BooleanSetting)(new BooleanSetting("Disable rings", false)).method2("Enable to prevent rings from working. Useful when setting up routes");
      this.field19 = new ButtonSetting("Clear world brush", this::lambda$new$0);
      this.field3 = new ButtonSetting("Clear brush", this::lambda$new$2);
      this.field16 = new ButtonSetting("Clear current room", this::lambda$new$3);
      this.field20 = new Class447();
      field5 = this;
      this.method1(true);
      this.field59.method0(true);
      this.method0((Setting[])(new Setting[]{this.field6, this.field4, this.field13, this.field18, this.field12, this.field9, this.field7, this.field11, this.field0, this.field16, this.field19, this.field3}));
   }
}
