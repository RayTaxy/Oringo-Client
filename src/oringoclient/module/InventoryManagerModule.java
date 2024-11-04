package oringo.module;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import map.Class362;
import map.Class447;
import map.Class475;
import map.Class6;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.mixin.ItemToolAccessor;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class InventoryManagerModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Shovel slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public final List aj_ = Arrays.asList("Training Weight", "Healing Potion", "Beating Heart", "Premium Flesh", "Mimic Fragment", "Enchanted Rotten Flesh", "Machine Gun Bow", "Enchanted Bone", "Defuse Kit", "Enchanted Ice", "Diamond Atom", "Silent Death", "Cutlass", "Soulstealer Bow", "Sniper Bow", "Optical Lens", "Tripwire Hook", "Button", "Carpet", "Lever", "Journal Entry", "Sign", "Zombie Commander", "Zombie Lord", "Skeleton Master, Skeleton Grunt, Skeleton Lord, Zombie Soldier", "Zombie Knight", "Heavy", "Super Heavy", "Undead", "Bouncy", "Skeletor", "Trap", "Inflatable Jerry");
   public boolean field2;
   public final List field3 = Arrays.asList("Egg", "Snowball", "Poison", "Lava", "Steak", "Enchanting", "Poison");
   public final EnumSetting field4 = new EnumSetting("Mode", "Inv open", new String[]{"Inv open", "Always"});
   public final BooleanSetting field5 = new BooleanSetting("Middle click to drop", false);
   public final DoubleSetting field6 = new DoubleSetting("Axe slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public final DoubleSetting field7 = new DoubleSetting("Pickaxe slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public final DoubleSetting field8 = new DoubleSetting("Gapple slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public final DoubleSetting field9 = new DoubleSetting("Block slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public final DoubleSetting field10 = new DoubleSetting("Delay", 30.0D, 0.0D, 300.0D, 1.0D);
   public static final String[] field11 = new String[]{"(Right Click)"};
   public final Class447 field12 = new Class447();
   public final BooleanSetting field13 = new BooleanSetting("Auto Armor", false);
   public final BooleanSetting field14 = new BooleanSetting("Drop trash", true);
   public final DoubleSetting field15 = new DoubleSetting("Sword slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public final DoubleSetting field16 = new DoubleSetting("Bow slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public final DoubleSetting field17 = new DoubleSetting("Rod slot", 0.0D, 0.0D, 9.0D, 1.0D);
   public static final List field18 = new ArrayList();
   public final EnumSetting field19 = new EnumSetting("Trash items", "Skyblock", new String[]{"Skyblock", "Skywars", "Custom"});

   public void method2() {
      for(int var1 = 9; var1 < 45; ++var1) {
         if (field58.thePlayer.inventoryContainer.getSlot(var1).getHasStack() && this.method10()) {
            ItemStack var2 = field58.thePlayer.inventoryContainer.getSlot(var1).getStack();
            if (var2.getItem() instanceof ItemTool && this.method0(var2) != 0 && !DungeonESPModule.method0(var2)) {
               if (this.method0(var2, var1)) {
                  if (this.method2(this.method0(var2)) != var1) {
                     this.method0(var1, this.method0(var2) - 1);
                  }
               } else {
                  this.method0(var1);
               }
            }
         }
      }

   }

   public void method3() {
      for(int var1 = 9; var1 < 45; ++var1) {
         if (field58.thePlayer.inventoryContainer.getSlot(var1).getHasStack() && this.method10()) {
            ItemStack var2 = field58.thePlayer.inventoryContainer.getSlot(var1).getStack();
            if (var2.getItem() instanceof ItemBow && !DungeonESPModule.method0(var2)) {
               if (this.method1(var2, var1)) {
                  if (this.method2((int)this.field16.method0()) != var1) {
                     this.method0(var1, (int)this.field16.method0() - 1);
                  }
               } else {
                  this.method0(var1);
               }
            }
         }
      }

   }

   public void b_(int var1) {
      this.field12.o_();
      field58.playerController.windowClick(field58.thePlayer.inventoryContainer.windowId, var1, 0, 1, field58.thePlayer);
   }

   public void method5() {
      for(int var1 = 9; var1 < 45; ++var1) {
         if (field58.thePlayer.inventoryContainer.getSlot(var1).getHasStack() && this.method10()) {
            ItemStack var2 = field58.thePlayer.inventoryContainer.getSlot(var1).getStack();
            if (var2.getItem() instanceof ItemBlock && ((ItemBlock)var2.getItem()).block.isFullBlock() && !DungeonESPModule.method0(var2) && this.method2(var2, var1)) {
               if (this.method2((int)this.field9.method0()) != var1) {
                  this.method0(var1, (int)this.field9.method0() - 1);
               }

               return;
            }
         }
      }

   }

   public InventoryManagerModule() {
      super("Inventory Manager", 0, Category.player, SubCategory.player, "Manages your inventory");
      this.method0((Setting[])(new Setting[]{this.field4, this.field10, this.field14, this.field19, this.field5, this.field13, this.field15, this.field17, this.field16, this.field7, this.field6, this.field0, this.field9}));
   }

   public void method0(int var1) {
      this.field12.o_();
      field58.playerController.windowClick(field58.thePlayer.inventoryContainer.windowId, var1, 1, 4, field58.thePlayer);
   }

   public void method6() {
      for(int var1 = 9; var1 < 45; ++var1) {
         if (field58.thePlayer.inventoryContainer.getSlot(var1).getHasStack() && this.method10()) {
            ItemStack var2 = field58.thePlayer.inventoryContainer.getSlot(var1).getStack();
            if (var2.getItem() instanceof ItemFishingRod && !DungeonESPModule.method0(var2)) {
               if (this.method4(var2, var1)) {
                  if (this.method2((int)this.field17.method0()) != var1) {
                     this.method0(var1, (int)this.field17.method0() - 1);
                  }
               } else {
                  this.method0(var1);
               }
            }
         }
      }

   }

   public void method7() {
      int var1;
      ItemStack var2;
      for(var1 = 5; var1 < 9; ++var1) {
         if (field58.thePlayer.inventoryContainer.getSlot(var1).getHasStack() && this.method10()) {
            var2 = field58.thePlayer.inventoryContainer.getSlot(var1).getStack();
            if (!AutoCrystalModule.method0(var2, var1)) {
               this.method0(var1);
            }
         }
      }

      for(var1 = 9; var1 < 45; ++var1) {
         if (field58.thePlayer.inventoryContainer.getSlot(var1).getHasStack() && this.method10()) {
            var2 = field58.thePlayer.inventoryContainer.getSlot(var1).getStack();
            if (var2.getItem() instanceof ItemArmor && !DungeonESPModule.method0(var2)) {
               if (AutoCrystalModule.method0(var2, var1)) {
                  this.b_(var1);
               } else {
                  this.method0(var1);
               }
            }
         }
      }

   }

   public void method8() {
      for(int var1 = 9; var1 < 45; ++var1) {
         if (field58.thePlayer.inventoryContainer.getSlot(var1).getHasStack() && this.method10()) {
            ItemStack var2 = field58.thePlayer.inventoryContainer.getSlot(var1).getStack();
            if (var2.getItem() instanceof ItemSword && !DungeonESPModule.method0(var2)) {
               if (this.method3(var2, var1)) {
                  if (this.method2((int)this.field15.method0()) != var1) {
                     this.method0(var1, (int)this.field15.method0() - 1);
                  }
               } else {
                  this.method0(var1);
               }
            }
         }
      }

   }

   @SubscribeEvent
   public void method0(ItemTooltipEvent var1) {
      if (Mouse.isButtonDown(2) && field58.currentScreen instanceof GuiInventory && this.field5.method1()) {
         if (!this.field2) {
            this.field2 = true;
            String var2 = ChatFormatting.stripFormatting(var1.itemStack.getDisplayName());
            if (field18.contains(var2)) {
               field18.remove(var2);
               ShortbowTriggerbotModule.method0("Oringo Client", "Removed " + var2 + " from custom drop list", 2000);
            } else {
               field18.add(var2);
               ShortbowTriggerbotModule.method0("Oringo Client", "Added " + ChatFormatting.AQUA + var2 + ChatFormatting.RESET + " to custom drop list", 2000);
            }

            IceFillHelperModule.n_();
         }
      } else {
         this.field2 = false;
      }

   }

   public static boolean lambda$getClosestPlayer$0(double var0, EntityPlayer var2) {
      return var2 != field58.thePlayer && !Class6.method0(var2) && !AutoMiddleModule.method0((Entity)var2) && (double)field58.thePlayer.getDistanceToEntity(var2) < var0;
   }

   public void method0(int var1, int var2) {
      this.field12.o_();
      field58.playerController.windowClick(field58.thePlayer.inventoryContainer.windowId, var1, var2, 2, field58.thePlayer);
   }

   public static Vec3 method0(float var0) {
      return new Vec3(AutoEnchantingModule.method0(Oringo.field9.thePlayer.prevPosX, Oringo.field9.thePlayer.posX, var0), AutoEnchantingModule.method0(Oringo.field9.thePlayer.prevPosY, Oringo.field9.thePlayer.posY, var0) + 0.1D, AutoEnchantingModule.method0(Oringo.field9.thePlayer.prevPosZ, Oringo.field9.thePlayer.posZ, var0));
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C02PacketUseEntity || var1.field0 instanceof C08PacketPlayerBlockPlacement) {
         this.field12.o_();
      }

   }

   public boolean method0(ItemStack var1, int var2) {
      if (!(var1.getItem() instanceof ItemTool)) {
         return false;
      } else {
         for(int var3 = 9; var3 < 45; ++var3) {
            if (field58.thePlayer.inventoryContainer.getSlot(var3).getHasStack()) {
               ItemStack var4 = field58.thePlayer.inventoryContainer.getSlot(var3).getStack();
               if (this.method0(var4) != 0) {
                  if (var1.getItem() instanceof ItemAxe && var4.getItem() instanceof ItemAxe) {
                     if (GhostBlocksModule.method0(var4) > GhostBlocksModule.method0(var1) && var2 == this.method2((int)this.field6.method0()) || var2 != this.method2((int)this.field6.method0()) && FragHelperModule.method0(var4) >= FragHelperModule.method0(var1) && var2 != var3) {
                        return false;
                     }
                  } else if (var1.getItem() instanceof ItemPickaxe && var4.getItem() instanceof ItemPickaxe) {
                     if (GhostBlocksModule.method0(var4) > GhostBlocksModule.method0(var1) && var2 == this.method2((int)this.field7.method0()) || var2 != this.method2((int)this.field7.method0()) && FragHelperModule.method0(var4) >= FragHelperModule.method0(var1) && var2 != var3) {
                        return false;
                     }
                  } else if (var1.getItem() instanceof ItemSpade && var4.getItem() instanceof ItemSpade && (GhostBlocksModule.method0(var4) > GhostBlocksModule.method0(var1) && var2 == this.method2((int)this.field0.method0()) || var2 != this.method2((int)this.field7.method0()) && FragHelperModule.method0(var4) >= FragHelperModule.method0(var1) && var2 != var3)) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public static boolean lambda$dropTrash$2(Slot var0, String var1) {
      return var1.contains(ChatFormatting.stripFormatting(var0.getStack().getDisplayName()));
   }

   public static void method0(JsonObject var0) {
      JsonElement var1 = var0.remove("Dillo");
      if (var1 != null && DilloFinderModule.cB_ == null && Class362.field49.method44()) {
         BlockPos var2 = BlockPos.fromLong(var1.getAsLong());
         DilloFinderModule.cB_ = var2;
         if (Class362.field49.field3.method1()) {
            PopupAnimationModule.method2(String.format("Found dillo structure at §a%s, %s, %s§f!", var2.getX(), var2.getY(), var2.getZ()));
            SecretHitboxesModule.method0("Dillo found!", 2500);
         }
      }

      if (Class362.field3.method44()) {
         Iterator var5 = NucleusHelperModule.field0.iterator();

         while(true) {
            NucleusHelperModule.Class0 var3;
            do {
               do {
                  if (!var5.hasNext()) {
                     return;
                  }

                  var3 = (NucleusHelperModule.Class0)var5.next();
               } while(!var0.has(var3.field1));
            } while(!var3.field3 && !var3.field7.isEmpty());

            BlockPos var4 = BlockPos.fromLong(var0.get(var3.field1).getAsLong());
            if (!var3.field7.contains(var4)) {
               var3.method0(var4);
            }
         }
      }
   }

   public void method9() {
      Iterator var1 = field58.thePlayer.inventoryContainer.inventorySlots.iterator();

      while(true) {
         while(true) {
            Slot var2;
            do {
               do {
                  if (!var1.hasNext()) {
                     return;
                  }

                  var2 = (Slot)var1.next();
               } while(!var2.getHasStack());
            } while(!this.method10());

            if (this.field19.method4().equals("Custom")) {
               if (field18.contains(ChatFormatting.stripFormatting(var2.getStack().getDisplayName()))) {
                  this.method0(var2.slotNumber);
               }
            } else if (this.field19.method4().equals("Skyblock") && this.aj_.stream().anyMatch(InventoryManagerModule::lambda$dropTrash$1)) {
               this.method0(var2.slotNumber);
            } else if (this.field19.method4().equals("Skywars") && this.field3.stream().anyMatch(InventoryManagerModule::lambda$dropTrash$2)) {
               this.method0(var2.slotNumber);
            }
         }
      }
   }

   public boolean method10() {
      return this.field12.a_((long)this.field10.method0());
   }

   public boolean method1(ItemStack var1, int var2) {
      for(int var3 = 9; var3 < 45; ++var3) {
         if (field58.thePlayer.inventoryContainer.getSlot(var3).getHasStack()) {
            ItemStack var4 = field58.thePlayer.inventoryContainer.getSlot(var3).getStack();
            if (var4.getItem() instanceof ItemBow && (Class475.method2(var4) > Class475.method2(var1) && var2 == this.method2((int)this.field16.method0()) || Class475.method2(var4) >= Class475.method2(var1) && var2 != this.method2((int)this.field16.method0())) && var3 != var2) {
               return false;
            }
         }
      }

      return true;
   }

   public EntityPlayer method0(double var1) {
      Stream var10000 = field58.theWorld.playerEntities.stream().filter(InventoryManagerModule::lambda$getClosestPlayer$0);
      EntityPlayerSP var10001 = field58.thePlayer;
      var10001.getClass();
      List var3 = (List)var10000.sorted(Comparator.comparingDouble(var10001::func_70032_d)).collect(Collectors.toList());
      return !var3.isEmpty() ? (EntityPlayer)var3.get(0) : null;
   }

   public boolean method2(ItemStack var1, int var2) {
      for(int var3 = 9; var3 < 45; ++var3) {
         if (var2 != var3 && field58.thePlayer.inventoryContainer.getSlot(var3).getHasStack()) {
            ItemStack var4 = field58.thePlayer.inventoryContainer.getSlot(var3).getStack();
            if (var4.getItem() instanceof ItemBlock && ((ItemBlock)var4.getItem()).block.isFullBlock() && (var4.stackSize > var1.stackSize || var4.stackSize == var1.stackSize && var3 == this.method2((int)this.field9.method0()))) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean method3(ItemStack var1, int var2) {
      if (!(var1.getItem() instanceof ItemSword)) {
         return false;
      } else {
         for(int var3 = 9; var3 < 45; ++var3) {
            if (field58.thePlayer.inventoryContainer.getSlot(var3).getHasStack()) {
               ItemStack var4 = field58.thePlayer.inventoryContainer.getSlot(var3).getStack();
               if (var4.getItem() instanceof ItemSword && (FragHelperModule.method0(var4) > FragHelperModule.method0(var1) && var2 == this.method2((int)this.field15.method0()) || var2 != this.method2((int)this.field15.method0()) && FragHelperModule.method0(var4) >= FragHelperModule.method0(var1) && var2 != var3)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public int method0(ItemStack var1) {
      if (var1 != null && var1.getItem() instanceof ItemTool) {
         String var2 = ((ItemToolAccessor)var1.getItem()).getToolClass();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -903145309:
            if (var2.equals("shovel")) {
               var3 = 2;
            }
            break;
         case -578028723:
            if (var2.equals("pickaxe")) {
               var3 = 0;
            }
            break;
         case 97038:
            if (var2.equals("axe")) {
               var3 = 1;
            }
         }

         switch(var3) {
         case 0:
            return (int)this.field7.method0();
         case 1:
            return (int)this.field6.method0();
         case 2:
            return (int)this.field0.method0();
         default:
            return 0;
         }
      } else {
         return 0;
      }
   }

   public int method2(int var1) {
      return var1 + 35;
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (!this.method44() || !RenderChunkBoundsModule.method5() || !(field58.currentScreen instanceof GuiInventory) && !this.field4.method0(1)) {
         this.field12.o_();
      } else {
         if (this.field13.method1()) {
            this.method7();
         }

         if (this.field14.method1()) {
            this.method9();
         }

         if (this.field15.method0() != 0.0D) {
            this.method8();
         }

         this.method2();
         if (this.field16.method0() != 0.0D) {
            this.method3();
         }

         if (this.field17.method0() != 0.0D) {
            this.method6();
         }

         if (this.field9.method0() != 0.0D) {
            this.method5();
         }
      }

   }

   public static boolean lambda$dropTrash$1(Slot var0, String var1) {
      return var1.contains(ChatFormatting.stripFormatting(var0.getStack().getDisplayName()));
   }

   public boolean method4(ItemStack var1, int var2) {
      for(int var3 = 9; var3 < 45; ++var3) {
         if (field58.thePlayer.inventoryContainer.getSlot(var3).getHasStack()) {
            ItemStack var4 = field58.thePlayer.inventoryContainer.getSlot(var3).getStack();
            if (var4.getItem() instanceof ItemFishingRod && (var4.getItemDamage() < var1.getItemDamage() && var2 == this.method2((int)this.field17.method0()) || var4.getItemDamage() <= Class475.method2(var1) && var2 != this.method2((int)this.field17.method0())) && var3 != var2) {
               return false;
            }
         }
      }

      return true;
   }
}
