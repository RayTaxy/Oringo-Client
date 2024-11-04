package oringo.module;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import map.Class228;
import map.Class253;
import map.Class296;
import map.Class313$1;
import map.Class361;
import map.Class395;
import map.Class426;
import map.Class428;
import map.Class94;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockMelon;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.PlayerCommand;
import oringo.event.Class189;
import oringo.event.Class194;
import oringo.event.Class270;
import oringo.event.Class75;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class CropNukerModule extends Module {
   public Block field0 = null;
   public final BooleanSetting aS_ = new BooleanSetting("Check for replenish", true);
   public final BooleanSetting field2 = new BooleanSetting("Only tools", true);
   public final BooleanSetting field3 = new BooleanSetting("Sand.", false);
   public final DoubleSetting field4 = new DoubleSetting("Breaks per tick", 3.0D, 1.0D, 5.0D, 0.5D);
   public final ConcurrentHashMap field5 = new ConcurrentHashMap();
   public final DoubleSetting field6 = new DoubleSetting("Skip Mushrooms", 10.0D, 1.0D, 15.0D, 1.0D);
   public final BooleanSetting field7 = new BooleanSetting("No ghost blocks", true);
   public ArrayList field8 = new ArrayList();
   public final DoubleSetting field9 = (new DoubleSetting("Vertical Range", 6.0D, 0.0D, 6.0D, 0.5D)).method0("m");
   public final Class296 field10 = new Class296();

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S23PacketBlockChange) {
         S23PacketBlockChange var2 = (S23PacketBlockChange)var1.field0;
         if (var2.getBlockState().getBlock() == Blocks.air) {
            this.field5.remove(var2.getBlockPosition());
         }
      }

   }

   public boolean method0(BlockPos var1) {
      return AutoHarpModule.method5().squareDistanceTo(new Vec3((double)var1.getX() + 0.5D, (double)(var1.getY() - 1), (double)var1.getZ() + 0.5D)) < 36.0D;
   }

   public static boolean lambda$onTick$0(Entry var0) {
      if (CropNukerModule.Class0.access$100((CropNukerModule.Class0)var0.getValue()).a_(10L)) {
         field58.theWorld.setBlockState((BlockPos)var0.getKey(), CropNukerModule.Class0.access$000((CropNukerModule.Class0)var0.getValue()));
         return true;
      } else {
         return false;
      }
   }

   @SubscribeEvent
   public void method0(Class194 var1) {
      BlockPos var2 = var1.field0;
      CropNukerModule.Class0 var3 = (CropNukerModule.Class0)this.field5.get(var2);
      if (var3 != null && CropNukerModule.Class0.access$000(var3).getBlock() == Blocks.sand) {
         var1.field1 = new AxisAlignedBB(var2, var2.add(1, 1, 1));
      }

   }

   public CropNukerModule() {
      super("Crop Nuker", Category.skyblock, SubCategory.mining, "Nukes crops near you");
      this.method0((Setting[])(new Setting[]{this.field4, this.field9, this.field6, this.field2, this.aS_, this.field3, this.field7}));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (!this.field8.isEmpty()) {
         GlStateManager.disableAlpha();
         Color var2 = null;
         boolean var3 = true;
         ArrayList var4 = new ArrayList();

         AxisAlignedBB var9;
         for(Iterator var5 = this.field8.iterator(); var5.hasNext(); var4.add(var9)) {
            BlockPos var6 = (BlockPos)var5.next();
            IBlockState var7 = field58.theWorld.getBlockState(var6);
            Block var8 = var7.getBlock();
            var2 = new Color(var8.getMapColor(var7).colorValue);
            if (var8 == Blocks.cocoa) {
               var2 = Color.ORANGE.darker().darker();
            }

            if (var8 == Blocks.wheat) {
               var2 = Color.YELLOW;
            }

            if (var8 == Blocks.brown_mushroom) {
               var2 = Color.YELLOW.darker().darker();
            }

            if (var8 == Blocks.red_mushroom) {
               var2 = Color.RED.darker();
            }

            var9 = FragHelperModule.method0(var6);
            if (var9.maxX - var9.minX != 1.0D || var9.maxZ - var9.minZ != 1.0D) {
               var3 = false;
            }
         }

         AutoBlazeModule.method0(PlayerCommand.method0(var2, 60));
         DungeonMapModule.method0(var3, var4);
         GlStateManager.enableAlpha();
         AutoCloseModule.method5();
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.method1(false);
   }

   public static void method0(HttpsURLConnection var0) {
      TrustManager[] var1 = new TrustManager[]{new Class313$1()};

      SSLContext var2;
      try {
         var2 = SSLContext.getInstance("SSL");
      } catch (NoSuchAlgorithmException var5) {
         var5.printStackTrace();
         return;
      }

      try {
         var2.init((KeyManager[])null, var1, new SecureRandom());
      } catch (KeyManagementException var4) {
         var4.printStackTrace();
      }

      var0.setSSLSocketFactory(var2.getSocketFactory());
   }

   public static double lambda$onTick$1(Class228 var0, BlockPos var1) {
      return (new Class228(var1)).method3(0.0D).method0(var0);
   }

   public static void lambda$onTick$2(List var0) {
      Class426.method10();
      Iterator var1 = var0.iterator();

      while(var1.hasNext()) {
         BlockPos var2 = (BlockPos)var1.next();
         MovingObjectPosition var3 = AutoCloakModule.method0(var2);
         if (var3 == null) {
            return;
         }

         EnumFacing var4 = var3.sideHit;
         field58.thePlayer.swingItem();
         field58.playerController.clickBlock(var2, var4);
      }

   }

   public boolean method0(BlockPos var1, IBlockState var2) {
      Block var3 = var2.getBlock();
      if (var3.isCollidable() && var1.equals(ThystHiderModule.method3())) {
         return false;
      } else if (var3.getPlayerRelativeBlockHardness(field58.thePlayer, field58.theWorld, var1) < 1.0F && !(var3 instanceof BlockCactus)) {
         return false;
      } else {
         boolean var4 = CreateNewkeybindModule.method0(field58.thePlayer.getHeldItem(), "Replenish I", false) || !this.aS_.method1();
         if (var3 instanceof BlockCrops) {
            return (Integer)var2.getValue(BlockCrops.AGE) == 7 && var4;
         } else if (var3 instanceof BlockNetherWart) {
            return (Integer)var2.getValue(BlockNetherWart.AGE) == 3 && var4;
         } else if (var3 instanceof BlockReed) {
            return field58.theWorld.getBlockState(var1.down()).getBlock() == Blocks.reeds && field58.theWorld.getBlockState(var1.up()).getBlock() == Blocks.reeds;
         } else if (var3 instanceof BlockCactus) {
            return field58.theWorld.getBlockState(var1.down()).getBlock() == Blocks.cactus && field58.theWorld.getBlockState(var1.up()).getBlock() == Blocks.cactus;
         } else if (var3 instanceof BlockCocoa) {
            return (Integer)var2.getValue(BlockCocoa.AGE) == 2 && var4;
         } else if (var3 instanceof BlockMushroom) {
            return (var1.getX() + var1.getZ()) % this.field6.method3() != 0;
         } else if (!(var3 instanceof BlockSand) && !(var3 instanceof BlockSoulSand)) {
            return var3 instanceof BlockPumpkin || var3 instanceof BlockMelon;
         } else {
            return this.field3.method1();
         }
      }
   }

   public static boolean lambda$onTick$3() {
      return !field58.thePlayer.isUsingItem();
   }

   public static void method0(File var0) {
      if (!ScreenShotModule.field3.F_()) {
         try {
            Process var1 = (new ProcessBuilder(new String[0])).command(ScreenShotModule.field3.method1().split(" ")).start();

            try {
               OutputStream var2 = var1.getOutputStream();
               Throwable var3 = null;

               try {
                  BufferedReader var4 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
                  Throwable var5 = null;

                  try {
                     var2.write((var0.getAbsolutePath() + "\n").getBytes(StandardCharsets.UTF_8));
                     var2.flush();

                     String var6;
                     while((var6 = var4.readLine()) != null) {
                        if (var6.toLowerCase().startsWith("copy ")) {
                           AutoIceSprayModule.b_(var6.substring(var6.indexOf(" ")));
                        }

                        if (ScreenShotModule.field58.thePlayer != null) {
                           ScreenShotModule.field58.thePlayer.addChatMessage(new Class253.Class0(RenderChunkBoundsModule.method0(var6, EnumChatFormatting.WHITE), ClickGuiModule.field0.method17().getRGB()));
                        }
                     }
                  } catch (Throwable var32) {
                     var5 = var32;
                     throw var32;
                  } finally {
                     if (var4 != null) {
                        if (var5 != null) {
                           try {
                              var4.close();
                           } catch (Throwable var31) {
                              var5.addSuppressed(var31);
                           }
                        } else {
                           var4.close();
                        }
                     }

                  }
               } catch (Throwable var34) {
                  var3 = var34;
                  throw var34;
               } finally {
                  if (var2 != null) {
                     if (var3 != null) {
                        try {
                           var2.close();
                        } catch (Throwable var30) {
                           var3.addSuppressed(var30);
                        }
                     } else {
                        var2.close();
                     }
                  }

               }
            } catch (Exception var36) {
            }
         } catch (IOException var37) {
            var37.printStackTrace();
            ScreenShotModule.method2("An error occurred, when executing the uploader!");
         }

      }
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      this.field5.entrySet().removeIf(CropNukerModule::lambda$onTick$0);
      this.field8.clear();
      if (field58.thePlayer.getHeldItem() != null && (field58.thePlayer.getHeldItem().getItem() instanceof ItemTool || field58.thePlayer.getHeldItem().getItem() instanceof ItemHoe) || !this.field2.method1()) {
         BlockPos var2 = AutoConversationModule.method5();
         int var3 = 0;
         ArrayList var4 = new ArrayList();
         Iterator var5 = Class428.method0(var2).iterator();

         while(true) {
            BlockPos var6;
            IBlockState var7;
            do {
               do {
                  do {
                     do {
                        if (!var5.hasNext()) {
                           Class228 var12 = (new Class228(var2)).method3(0.0D);
                           var4.sort(Comparator.comparingDouble(CropNukerModule::lambda$onTick$1));
                           double var13 = this.field4.method0();
                           this.field8 = new ArrayList(var4);
                           ArrayList var8 = Lists.newArrayList();
                           Iterator var9 = var4.iterator();

                           while(var9.hasNext()) {
                              BlockPos var10 = (BlockPos)var9.next();
                              IBlockState var11 = field58.theWorld.getBlockState(var10);
                              if (this.method0(var10, var11)) {
                                 this.field8.remove(var10);
                                 if (this.field7.method1()) {
                                    this.field5.put(var10, new CropNukerModule.Class0(var11));
                                 }

                                 var8.add(var10);
                                 ++var3;
                                 if ((double)var3 >= var13) {
                                    break;
                                 }
                              }
                           }

                           if (var8.isEmpty()) {
                              return;
                           }

                           Class361.method0((Class94)(new Class395(CropNukerModule::lambda$onTick$2, false, CropNukerModule::lambda$onTick$3)));
                           return;
                        }

                        var6 = (BlockPos)var5.next();
                     } while(!this.method0(var6));
                  } while((double)Math.abs(var6.getY() - var2.getY()) > this.field9.method0());

                  var7 = field58.theWorld.getBlockState(var6);
               } while(!this.method0(var6, var7));

               if (this.field10.a_(50L)) {
                  this.field0 = var7.getBlock();
                  break;
               }
            } while(!var7.getBlock().equals(this.field0));

            var4.add(var6);
            this.field10.o_();
         }
      }
   }

   public static class Class0 {
      private final IBlockState field0;
      private final Class296 field1 = new Class296();

      static IBlockState access$000(CropNukerModule.Class0 var0) {
         return var0.field0;
      }

      static Class296 access$100(CropNukerModule.Class0 var0) {
         return var0.field1;
      }

      public Class0(IBlockState var1) {
         this.field0 = var1;
      }
   }
}
