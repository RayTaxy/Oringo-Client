package map;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.event.Class189;
import oringo.event.Class468;
import oringo.module.AimAssistModule;
import oringo.module.AutoVisitorsModule;
import oringo.module.BlockHitModule;
import oringo.module.CreateNewkeybindModule;
import oringo.module.DoorESPModule;

public class Class427 extends Class83 {
   public boolean db_;
   public Class427.Class0 field5;
   public int field6;
   public final Class447 field7;
   public final String[] field8;
   public static final Pattern field9 = Pattern.compile("Visitors: \\((\\d)\\)");
   public final BlockPos field10 = new BlockPos(5, 72, -16);
   public final BlockPos field11 = new BlockPos(5, 73, -18);

   public boolean method4() {
      return false;
   }

   public Class427() {
      super("Visitors");
      this.field5 = Class427.Class0.field3;
      this.field6 = 0;
      this.field7 = new Class447();
      this.field8 = new String[]{"RUNE", "UNIQUE_RUNE", "GREEN_CANDY", "PURPLE_CANDY", "BIOFUEL", "PET_ITEM_FARMING_SKILL_BOOST_UNCOMMON", "JACOBS_TICKET", "JUNGLE_KEY", "PET_CAKE", "ARACHNE_FRAGMENT", "DEAD_BUSH", "MYSTERIOUS_CROP", "IRON_HOE", "VELVET_TOP_HAT", "CASHMERE_JACKET", "SATIN_TROUSERS", "OXFORD_SHOES", "FLOWERING_BOUQUET"};
   }

   public void method0(ClientTickEvent var1) {
      if (this.field3.thePlayer != null && Class496.field6) {
         if (!this.field3.thePlayer.capabilities.allowFlying) {
            this.field5 = Class427.Class0.field0;
         }
      } else {
         this.field1.method23();
      }

   }

   public void o_() {
      super.o_();
      this.db_ = false;
      this.field5 = Class427.Class0.field3;
      this.field6 = 0;
   }

   public int method5() {
      Iterator var1 = this.field3.getNetHandler().getPlayerInfoMap().iterator();

      while(var1.hasNext()) {
         NetworkPlayerInfo var2 = (NetworkPlayerInfo)var1.next();
         if (var2 != null && var2.getDisplayName() != null) {
            String var3 = var2.getDisplayName().getUnformattedText();
            Matcher var4 = field9.matcher(var3);
            if (var4.find()) {
               return Integer.parseInt(var4.group(1));
            }
         }
      }

      return 0;
   }

   public void method0(Class468 var1) {
      // $FF: Couldn't be decompiled
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (this.field5 == Class427.Class0.field2 && var1.message.getUnformattedText().equals("You don't have enough of the required item!")) {
         AutoVisitorsModule.field6 = true;
      }

      if (var1.message.getUnformattedText().equals("You need the Cookie Buff to use this feature!")) {
         this.field1.method23();
         this.field1.method16();
      }

   }

   public int method6() {
      return Class95.method0(this::lambda$getTrashSlot$0);
   }

   public static double method1(double var0, double var2) {
      double var4 = var0 - var2;
      double var6 = Math.sqrt(1.0D - Math.cos(Class84.field0.nextDouble() * 3.141592653589793D / 2.0D));
      return var2 + var4 * var6;
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (var1.field0 instanceof S08PacketPlayerPosLook) {
         this.db_ = true;
      }

   }

   public boolean lambda$getTrashSlot$0(ItemStack var1) {
      String var2 = BlockHitModule.method0(var1);
      if (var2 == null) {
         return false;
      } else if (var1.getDisplayName().contains("Music Rune")) {
         return false;
      } else {
         String[] var3 = this.field8;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            if (var6.equals(var2)) {
               return true;
            }
         }

         if (var2.equals("ENCHANTED_BOOK")) {
            return CreateNewkeybindModule.method0(var1, "Turbo-", true);
         } else {
            return false;
         }
      }
   }

   public boolean method3() {
      if (this.field3.thePlayer != null && this.field3.thePlayer.capabilities.allowFlying && !this.field1.method33() && AimAssistModule.method3()) {
         if (DoorESPModule.method2()) {
            return false;
         } else {
            BlockPos var1 = (new BlockPos(this.field3.thePlayer)).up();

            for(int var2 = (int)this.field3.thePlayer.posY; var2 < 105; ++var2) {
               if (this.field3.theWorld.getBlockState(var1).getBlock() != Blocks.air) {
                  return false;
               }

               var1 = var1.up();
            }

            return this.method5() == 5;
         }
      } else {
         return false;
      }
   }

   public static enum Class0 {
      private static final Class427.Class0[] field4 = new Class427.Class0[]{field3, field2, field1, field0};
      field0,
      field1,
      field2,
      field3;
   }
}
