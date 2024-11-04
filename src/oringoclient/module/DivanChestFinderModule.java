package oringo.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Pattern;
import map.Class362;
import map.Class376;
import map.Class475;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.command.BloodSkipCommand;
import oringo.command.PlayerCommand;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.mixin.ChunkProviderClientAccessor;

public class DivanChestFinderModule extends Module {
   public BlockPos bw_ = null;
   public BlockPos bx_ = null;
   public boolean field2 = false;
   public final Pattern field3 = Pattern.compile("TREASURE: (.+?)m");
   public final ArrayList field4 = new ArrayList(40);
   public final ArrayList field5 = new ArrayList();

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.bx_ != null) {
         Color var2 = Color.BLACK;
         AutoArrowModule.method0((double)this.bx_.getX() + 0.5D, (double)this.bx_.getY() + 0.5D, (double)this.bx_.getZ() + 0.5D, var2);
         AutoBlazeModule.method0(PlayerCommand.method0(var2, 60));
         Class475.method0(this.bx_);
      }

   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.bw_ = null;
      this.bx_ = null;
      Iterator var2 = this.field5.iterator();

      while(var2.hasNext()) {
         DivanChestFinderModule.Class0 var3 = (DivanChestFinderModule.Class0)var2.next();
         var3.o_();
      }

   }

   public static void method0(int var0, boolean var1) {
      GemstoneESPModule.method0(var0, var1, false);
   }

   public Vec3 method3() {
      int var1 = (int)((double)Class362.field7.field8 / 50.0D * 3.0D);
      var1 = this.field4.size() - var1;
      if (var1 < 0) {
         var1 = 0;
      }

      return this.field4.size() <= var1 ? null : (Vec3)this.field4.get(var1);
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      String var2 = ChatFormatting.stripFormatting(var1.message.getUnformattedText());
      if (var2.contains("TREASURE:")) {
         if (this.field2) {
            this.field2 = false;
            return;
         }

         String var3 = BloodSkipCommand.method0(var2, this.field3, 1);
         if (var3 == null) {
            return;
         }

         double var4 = Double.parseDouble(var3);
         Vec3 var6 = this.method3();
         BlockPos var7 = this.method5();
         if (var7 == null || var6 == null) {
            return;
         }

         Iterator var8 = this.field5.iterator();

         while(var8.hasNext()) {
            DivanChestFinderModule.Class0 var9 = (DivanChestFinderModule.Class0)var8.next();
            var9.method0(var6.subtract(new Vec3(var7)), var4);
         }

         this.field5.sort(Comparator.comparingDouble(DivanChestFinderModule::lambda$onChat$0));
         this.bx_ = (new BlockPos(DivanChestFinderModule.Class0.access$000((DivanChestFinderModule.Class0)this.field5.get(0)))).add(var7);
      }

      if (var2.startsWith("You found")) {
         this.field2 = true;
         this.bx_ = null;
         Iterator var10 = this.field5.iterator();

         while(var10.hasNext()) {
            DivanChestFinderModule.Class0 var11 = (DivanChestFinderModule.Class0)var10.next();
            var11.o_();
         }
      }

   }

   public BlockPos method5() {
      if (this.bw_ != null) {
         return this.bw_;
      } else if (field58.theWorld.getChunkProvider() instanceof ChunkProviderClient) {
         Iterator var1 = ((ChunkProviderClientAccessor)field58.theWorld.getChunkProvider()).getChunkListing().iterator();

         while(true) {
            Chunk var2;
            do {
               do {
                  if (!var1.hasNext()) {
                     return null;
                  }

                  var2 = (Chunk)var1.next();
               } while(var2.isEmpty());
            } while(var2.getBlock(0, 189, 0) != Blocks.bedrock);

            for(int var3 = 0; var3 < 16; ++var3) {
               for(int var4 = 0; var4 < 16; ++var4) {
                  for(int var5 = 80; var5 < 160; ++var5) {
                     if (var2.getBlock(var3, var5, var4) == Blocks.barrier && var2.getBlock(var3, var5 + 1, var4) == Blocks.air) {
                        boolean var6 = true;
                        BlockPos var7 = new BlockPos(var2.xPosition * 16 + var3, var5, var2.zPosition * 16 + var4);

                        for(int var8 = 1; var8 <= 5; ++var8) {
                           var6 = field58.theWorld.getBlockState(var7.add(var8, 0, 0)).getBlock() == Blocks.barrier && field58.theWorld.getBlockState(var7.add(-var8, 0, 0)).getBlock() == Blocks.barrier && field58.theWorld.getBlockState(var7.add(0, 0, var8)).getBlock() == Blocks.barrier && field58.theWorld.getBlockState(var7.add(0, 0, -var8)).getBlock() == Blocks.barrier;
                           if (!var6) {
                              break;
                           }
                        }

                        if (var6) {
                           this.bw_ = new BlockPos(var2.xPosition * 16 + var3, var5 + 1, var2.zPosition * 16 + var4);
                           return this.bw_;
                        }
                     }
                  }
               }
            }
         }
      } else {
         return null;
      }
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (!Class376.method0("Mines of Divan")) {
         this.bx_ = null;
      } else {
         if (this.field4.size() == 40) {
            this.field4.remove(0);
         }

         this.field4.add(var1.getPosition());
      }
   }

   public static double lambda$onChat$0(DivanChestFinderModule.Class0 var0) {
      return DivanChestFinderModule.Class0.access$100(var0);
   }

   public DivanChestFinderModule() {
      super("Divan Chest Finder", Category.skyblock, SubCategory.mining, "Helps you find the divan chest");

      try {
         InputStreamReader var1 = new InputStreamReader((InputStream)Objects.requireNonNull(this.getClass().getResourceAsStream("/assets/oringoclient/divan_chests.json")));
         Throwable var2 = null;

         try {
            JsonArray var3 = (new JsonParser()).parse(var1).getAsJsonArray();
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
               JsonElement var5 = (JsonElement)var4.next();
               JsonArray var6 = var5.getAsJsonArray();
               this.field5.add(new DivanChestFinderModule.Class0(var6.get(0).getAsInt(), var6.get(1).getAsInt(), var6.get(2).getAsInt()));
            }
         } catch (Throwable var15) {
            var2 = var15;
            throw var15;
         } finally {
            if (var1 != null) {
               if (var2 != null) {
                  try {
                     var1.close();
                  } catch (Throwable var14) {
                     var2.addSuppressed(var14);
                  }
               } else {
                  var1.close();
               }
            }

         }
      } catch (NullPointerException | IOException var17) {
         if (Oringo.cV_) {
            var17.printStackTrace();
         }
      }

   }

   public static class Class0 {
      private double field0;
      private final Vec3 field1;

      public void method0(Vec3 var1, double var2) {
         this.field0 += Math.abs(var1.distanceTo(this.field1.addVector(0.0D, 1.0D, 0.0D)) - var2);
      }

      public void o_() {
         this.field0 = 0.0D;
      }

      static double access$100(DivanChestFinderModule.Class0 var0) {
         return var0.field0;
      }

      public Class0(int var1, int var2, int var3) {
         this.field1 = new Vec3((double)var1, (double)var2, (double)var3);
      }

      static Vec3 access$000(DivanChestFinderModule.Class0 var0) {
         return var0.field1;
      }
   }
}
