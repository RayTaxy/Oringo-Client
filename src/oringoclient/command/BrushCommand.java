package oringo.command;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import map.Class1;
import map.Class119;
import map.Class12;
import map.Class157;
import map.Class178;
import map.Class198;
import map.Class208;
import map.Class229;
import map.Class25;
import map.Class28;
import map.Class339;
import map.Class496;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class148;
import oringo.event.Class270;
import oringo.event.Class274;
import oringo.event.Class307;
import oringo.mixin.MinecraftAccessor;
import oringo.module.AntiTentacleModule;
import oringo.module.AutoRabbitModule;
import oringo.module.BrushModule;
import oringo.module.FragHelperModule;
import oringo.module.GhostBlocksModule;
import oringo.module.LividFinderModule;
import oringo.module.ScaffoldModule;
import oringo.module.SneakModule;
import oringo.module.ThystHiderModule;
import oringo.setting.ButtonSetting;
import oringo.setting.ColorSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.KeyBindSetting;

public class BrushCommand extends Command {
   public static int cM_ = -1;
   public static Block field1;
   public static boolean field0;

   @SubscribeEvent
   public void method0(Class270 var1) {
      if (field0) {
         method2("Editing has been auto disabled!");
      }

      field0 = false;
   }

   public String method1() {
      return "Allows you to edit dungeon rooms. Do .brush to see commands";
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (field0 && Class496.field5 && field1 != null && var1.type == ElementType.TEXT) {
         Item var2 = Item.getItemFromBlock(field1);
         if (var2 != null) {
            ItemStack var3 = new ItemStack(var2);
            var3.setItemDamage(cM_);
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            GL11.glScaled(4.0D, 4.0D, 4.0D);
            RenderHelper.enableGUIStandardItemLighting();
            field9.getRenderItem().renderItemIntoGUI(var3, 10, 10);
            RenderHelper.disableStandardItemLighting();
            GL11.glScaled(0.25D, 0.25D, 0.25D);
         }
      }

   }

   public static void method2() {
      if (Class496.field5 && Class198.field2.thePlayer != null && Class198.field2.theWorld != null) {
         if (Class198.field2.thePlayer.ticksExisted % 10 == 0) {
            for(int var0 = 0; var0 < Class198.field1.length; ++var0) {
               for(int var1 = 0; var1 < Class198.field1.length; ++var1) {
                  int var2 = -185 + var0 * 32;
                  int var3 = -185 + var1 * 32;
                  if (Class198.field2.theWorld.isBlockLoaded(new BlockPos(var2, 0, var3), false)) {
                     Class208 var4 = Class25.method0(var0, var1);
                     if (var4 != null) {
                        Class119.method0(var2, var3, var4);
                     } else {
                        var4 = Class178.method0(var2, var3);
                        Class198.field1[var1][var0] = var4;
                     }

                     if (var4 != null) {
                        String var5 = var4.method45();
                        Class208 var6 = Class25.method0(var0 - 1, var1);
                        Class208 var7 = Class25.method0(var0, var1 - 1);
                        Class208 var8 = Class25.method0(var0 - 1, var1 - 1);
                        Class208 var9 = Class25.method0(var0 + 1, var1 - 1);
                        var4.bA_ = (var6 == null || !var5.equals(var6.method45())) && (var7 == null || !var5.equals(var7.method45())) && (var8 == null || !var5.equals(var8.method45())) && (var9 == null || !var5.equals(var9.method45()));
                     }
                  }
               }
            }

            Class28.method0();
         }

         ScaffoldModule.method6();
         if (Class229.field5.a_(1000L)) {
            SneakModule.method2();
         }

      }
   }

   public static void method3() {
      GL11.glTranslated(-Oringo.field9.getRenderManager().viewerPosX, -Oringo.field9.getRenderManager().viewerPosY, -Oringo.field9.getRenderManager().viewerPosZ);
   }

   public BrushCommand() {
      super("brush", "edit");
   }

   public boolean a_() {
      return true;
   }

   @SubscribeEvent
   public void method0(Class307 var1) {
      if (field0) {
         var1.method9();
         if (field9.objectMouseOver != null && field9.objectMouseOver.getBlockPos() != null) {
            BlockPos var2 = field9.objectMouseOver.getBlockPos();
            Class208 var3 = GhostBlocksModule.method5();
            Class1 var4 = Class157.method0(var2, var3 == null ? new Class12((int)field9.thePlayer.posX, (int)field9.thePlayer.posZ) : new Class12(var3.field1, var3.field5), var3);
            KeyBindSetting.method0(var2, var4 != null && var4.field3 != null ? null : Blocks.air, 0);
         }

      }
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
      if (field0) {
         var1.method9();
         if (((MinecraftAccessor)field9).getPlaceDelay() <= 0) {
            if (field1 != null && field9.objectMouseOver.getBlockPos() != null) {
               BlockPos var2 = field9.objectMouseOver.getBlockPos();
               ((MinecraftAccessor)field9).setPlaceDelay(4);
               if (field1 == Blocks.air) {
                  KeyBindSetting.method0(var2, field1, 0);
               } else {
                  Vec3 var3 = field9.objectMouseOver.hitVec;
                  float var4 = (float)(var3.xCoord - (double)var2.getX());
                  float var5 = (float)(var3.yCoord - (double)var2.getY());
                  float var6 = (float)(var3.zCoord - (double)var2.getZ());
                  BlockPos var7 = var2.offset(field9.objectMouseOver.sideHit);
                  IBlockState var8 = field1.onBlockPlaced(field9.theWorld, var7, field9.objectMouseOver.sideHit, var4, var5, var6, 0, field9.thePlayer);
                  if (cM_ != -1 && var8.getProperties().containsKey(BlockColored.COLOR)) {
                     var8 = var8.withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(cM_));
                  }

                  KeyBindSetting.method0(var7, field1, field1.getMetaFromState(var8));
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void method0(Class148 var1) {
      if (field0) {
         var1.method9();
         if (field9.objectMouseOver != null && field9.objectMouseOver.getBlockPos() != null) {
            IBlockState var2 = field9.theWorld.getBlockState(field9.objectMouseOver.getBlockPos());
            if (var2.getBlock() instanceof BlockColored) {
               cM_ = ((EnumDyeColor)var2.getValue(BlockColored.COLOR)).getMetadata();
            }

            field1 = var2.getBlock();
            method2("Set block as " + field1.getLocalizedName() + "!");
         }

      }
   }

   public void method0(String[] var1) {
      if (var1.length == 0) {
         method2((field0 = !field0) ? "Enabled editing!" : "Disabled editing!");
         if (field0) {
            method2("Left click to remove blocks");
            method2("Right click to place blocks");
            method2(String.format("%sbrush blockname - selects blocks", AutoRabbitModule.method1()));
            method2(String.format("%sbrush placecmd <command, command...> - creates a command execution ring", AutoRabbitModule.method1()));
            method2(String.format("%sbrush placewarp - creates an etherwarp ring for the targeted block", AutoRabbitModule.method1()));
            method2(String.format("%sbrush removering - removes rings next to you", AutoRabbitModule.method1()));
            method2(String.format("%sbrush chain - makes close rings not work unless chained", AutoRabbitModule.method1()));
            method2(String.format("%sbrush awaitsecret - make close rings wait for secret aura when chained", AutoRabbitModule.method1()));
         }

      } else {
         BlockPos var2 = ThystHiderModule.method3();
         Class12 var3 = FragHelperModule.method0(var2.getX(), var2.getZ());
         Class208 var4 = LividFinderModule.method0(var3.method1(), var3.method0());
         BrushModule.field2 = ThystHiderModule.method3();
         String var5 = var1[0].toLowerCase(Locale.ENGLISH);
         byte var6 = -1;
         switch(var5.hashCode()) {
         case -2073890938:
            if (var5.equals("awaitsecret")) {
               var6 = 2;
            }
            break;
         case 94623425:
            if (var5.equals("chain")) {
               var6 = 3;
            }
            break;
         case 1099464884:
            if (var5.equals("removering")) {
               var6 = 0;
            }
            break;
         case 1793232911:
            if (var5.equals("placewarp")) {
               var6 = 1;
            }
            break;
         case 1858942675:
            if (var5.equals("placecmd")) {
               var6 = 4;
            }
         }

         int var21;
         int var22;
         int var23;
         switch(var6) {
         case 0:
            for(int var20 = -1; var20 <= 1; ++var20) {
               for(var21 = -1; var21 <= 1; ++var21) {
                  Class339.method0(var2.add(var20, 0, var21), var3, var4);
               }
            }

            return;
         case 1:
            MovingObjectPosition var7 = AntiTentacleModule.method0(field9.thePlayer.rotationYaw, field9.thePlayer.rotationPitch, 61.0F);
            if (var7.typeOfHit == MovingObjectType.BLOCK) {
               EnumSetting.method0(var2, var7.hitVec);
            }

            return;
         case 2:
            for(var21 = -1; var21 <= 1; ++var21) {
               for(var22 = -1; var22 <= 1; ++var22) {
                  var23 = DoubleSetting.method0(var2.add(var21, 0, var22));
                  if (var23 != -1) {
                     method2((var23 == 1 ? "Enabled" : "Disabled") + " await secret");
                  }
               }
            }

            return;
         case 3:
            for(var21 = -1; var21 <= 1; ++var21) {
               for(var22 = -1; var22 <= 1; ++var22) {
                  var23 = ButtonSetting.method0(var2.add(var21, 0, var22));
                  if (var23 != -1) {
                     method2((var23 == 1 ? "Enabled" : "Disabled") + " chaining");
                  }
               }
            }

            return;
         case 4:
            String[] var8 = new String[var1.length - 1];
            System.arraycopy(var1, 1, var8, 0, var8.length);
            String[] var9 = String.join(" ", var8).split(", ");
            Pattern var10 = Pattern.compile("-yaw:x?(\\+|-?\\d+.?\\d+)");

            for(int var11 = 0; var11 < var9.length; ++var11) {
               String var12 = var9[var11];
               if (var12.toLowerCase(Locale.ENGLISH).startsWith(AutoRabbitModule.method1() + "use")) {
                  String[] var13 = var12.split(" ");

                  for(int var14 = 1; var14 < var13.length; ++var14) {
                     Matcher var15 = var10.matcher(var13[var14]);
                     if (var15.find()) {
                        String var16 = var15.group(1);
                        float var17 = Float.parseFloat(var16);
                        if (var4 != null && var4.field10) {
                           var17 -= (float)var4.bB_.getHorizontalIndex() * 90.0F;
                        }

                        var13[var14] = var13[var14].replaceAll(var16, String.valueOf(var17));
                     }
                  }

                  var12 = String.join(" ", var13);
                  var9[var11] = var12;
               }
            }

            ColorSetting.method0(var2, String.join(", ", var9));
            return;
         default:
            String[] var19 = var1[0].toLowerCase().split(":");
            field1 = Block.getBlockFromName(var19[0]);
            if (field1 == null) {
               method2("Unknown block!");
            } else {
               method2("Set block successfully!");
               cM_ = -1;
               if (var19.length == 2) {
                  try {
                     cM_ = Integer.parseInt(var19[1]);
                     method2("Color: " + EnumDyeColor.byMetadata(cM_).name());
                  } catch (NumberFormatException var18) {
                     method2("An error occurred while parsing data");
                  }
               }
            }

         }
      }
   }
}
