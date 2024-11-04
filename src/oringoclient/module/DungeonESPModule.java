package oringo.module;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import map.Class122;
import map.Class374;
import map.Class496;
import map.Class528;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.event.Class125;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.ColorSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class DungeonESPModule extends Module {
   public final ColorSetting field0 = new ColorSetting("Team color", new Color(0, 226, 0), false, this::lambda$new$5);
   public final BooleanSetting aU_ = new BooleanSetting("Starred ESP", true);
   public final ColorSetting field2 = new ColorSetting("SA color", new Color(75, 0, 19), false, this::lambda$new$2);
   public final ColorSetting field3 = new ColorSetting("AA color", new Color(97, 226, 255), false, this::lambda$new$4);
   public final ColorSetting field4 = new ColorSetting("LA color", new Color(34, 139, 34), false, this::lambda$new$3);
   public final ColorSetting field5 = new ColorSetting("Bat color", new Color(140, 69, 19), false, this::lambda$new$1);
   public final BooleanSetting field6 = new BooleanSetting("Teammate ESP", false);
   public final BooleanSetting field7 = new BooleanSetting("Bat ESP", true);
   public final EnumSetting field8 = new EnumSetting("Mode", "2D", new String[]{"Outline", "Glow", "2D", "Chams", "Box", "Tracers"});
   public final ColorSetting field9 = new ColorSetting("Starred color", new Color(245, 81, 66), false, this::lambda$new$0);
   public static final HashMap field10 = new HashMap();
   public boolean field11;
   public final BooleanSetting field12 = new BooleanSetting("Bow warning", false);
   public final BooleanSetting field13 = new BooleanSetting("Miniboss ESP", true);

   public Boolean lambda$new$1() {
      return !this.field7.method1();
   }

   public static boolean method0(ItemStack var0) {
      String[] var1 = InventoryManagerModule.field11;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String var4 = var1[var3];
         if (var0.getDisplayName().contains(var4)) {
            return true;
         }
      }

      return false;
   }

   public static void lambda$onRenderWorld$10(Entity var0, Color var1) {
      if (var0 instanceof EntityLivingBase) {
         ScaffoldModule.method0((EntityLivingBase)var0, var1);
      }
   }

   public Boolean lambda$new$2() {
      return !this.field13.method1();
   }

   public void b_() {
      field10.clear();
   }

   public static void lambda$onRenderWorld$9(RenderWorldLastEvent var0, Entity var1, Color var2) {
      Class528.method0(var1, var0.partialTicks, 1.5F, var2);
   }

   public Boolean lambda$new$3() {
      return !this.field13.method1();
   }

   @SubscribeEvent
   public void method0(Class125 var1) {
      if (Class496.field5 && this.field8.method0(0)) {
         if (field10.containsKey(var1.field4)) {
            if (!Class210.method0((Entity)var1.field4)) {
               return;
            }

            MinigameAimbotModule.method0(var1, (Color)field10.get(var1.field4));
         }

      }
   }

   @SubscribeEvent
   public void method0(Pre var1) {
      if (this.field11) {
         this.field11 = false;
         AutoSalvageModule.method14();
      }

   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (field58.theWorld != null && var1.phase != Phase.END) {
         if (Class496.field5) {
            field10.clear();
            Color var2 = this.field9.method17();
            Color var3 = this.field5.method17();
            Color var4 = this.field2.method17();
            Color var5 = this.field4.method17();
            Color var6 = this.field0.method17();
            Color var7 = this.field3.method17();
            Iterator var8 = field58.theWorld.getEntities(EntityLivingBase.class, DungeonESPModule::lambda$onUpdate$6).iterator();

            while(true) {
               while(true) {
                  EntityLivingBase var9;
                  do {
                     if (!var8.hasNext()) {
                        return;
                     }

                     var9 = (EntityLivingBase)var8.next();
                  } while(field10.containsKey(var9));

                  if (var9 instanceof EntityBat) {
                     if (!var9.isInvisible() && this.field7.method1()) {
                        field10.put(var9, var3);
                     }
                  } else if (var9 instanceof EntityEnderman) {
                     if (var9.getName().equals("Dinnerbone")) {
                        var9.setInvisible(false);
                     }
                  } else if (var9 instanceof EntityOtherPlayerMP) {
                     if (this.field6.method1() && !var9.isInvisible() && var9.riddenByEntity == null && !Class122.method0((EntityOtherPlayerMP)var9)) {
                        field10.put(var9, var6);
                     }

                     if (this.field13.method1() && AutoIceFillModule.method0((Entity)var9)) {
                        switch(var9.getName().charAt(0)) {
                        case 'D':
                           field10.put(var9, var7);
                           break;
                        case 'L':
                           field10.put(var9, var5);
                           break;
                        case 'S':
                           var9.setInvisible(false);
                           field10.put(var9, var4);
                        }
                     }

                     if (this.field12.method1() && var9.getHeldItem() != null && var9.getHeldItem().getItem() instanceof ItemBow) {
                        field58.ingameGUI.displayTitle((String)null, ChatFormatting.DARK_RED + "Bow", 0, 2, 0);
                     }
                  } else if (var9 instanceof EntityArmorStand && this.aU_.method1() && var9.getName().contains("✯")) {
                     for(int var10 = 1; var10 < 5; ++var10) {
                        Entity var11 = field58.theWorld.getEntityByID(var9.getEntityId() - var10);
                        if (var11 != null && !(var11 instanceof EntityArmorStand) && !AutoIceFillModule.method0(var11)) {
                           field10.put(var11, var2);
                           break;
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public DungeonESPModule() {
      super("Dungeon ESP", 0, Category.render, SubCategory.main);
      this.method0((Setting[])(new Setting[]{this.field8, this.field6, this.field0, this.aU_, this.field9, this.field7, this.field5, this.field13, this.field2, this.field4, this.field3}));
   }

   public static boolean method0(int var0, int var1) {
      if (DungeonMapModule.method0(var0, var1)) {
         return false;
      } else {
         boolean var2 = DungeonMapModule.method0(var0 + 4, var1);
         boolean var3 = DungeonMapModule.method0(var0 - 4, var1);
         boolean var4 = DungeonMapModule.method0(var0, var1 + 4);
         boolean var5 = DungeonMapModule.method0(var0, var1 - 4);
         return var2 && var3 && !var4 && !var5 || !var2 && !var3 && var4 && var5;
      }
   }

   public static void lambda$onRenderWorld$8(RenderWorldLastEvent var0, Entity var1, Color var2) {
      if (Class210.method0(var1)) {
         AutoIceFillModule.method0(var1, var0.partialTicks, var2);
      }
   }

   public Boolean lambda$new$0() {
      return !this.aU_.method1();
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(net.minecraftforge.client.event.RenderLivingEvent.Pre var1) {
      if (Class496.field5 && this.field8.method0(3)) {
         if (field10.containsKey(var1.entity)) {
            KillAuraModule.method12();
            this.field11 = true;
         }

      }
   }

   public static void lambda$onRenderWorld$7(RenderWorldLastEvent var0, Entity var1, Color var2) {
      if (Class210.method0(var1)) {
         Class374.method0(var1, var0.partialTicks, 1.0F, var2);
      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method44() && Class496.field5) {
         String var2 = this.field8.method4();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case 1618:
            if (var2.equals("2D")) {
               var3 = 0;
            }
            break;
         case 66987:
            if (var2.equals("Box")) {
               var3 = 1;
            }
            break;
         case 2222509:
            if (var2.equals("Glow")) {
               var3 = 3;
            }
            break;
         case 597252646:
            if (var2.equals("Tracers")) {
               var3 = 2;
            }
         }

         switch(var3) {
         case 0:
            field10.forEach(DungeonESPModule::lambda$onRenderWorld$7);
            break;
         case 1:
            field10.forEach(DungeonESPModule::lambda$onRenderWorld$8);
            break;
         case 2:
            field10.forEach(DungeonESPModule::lambda$onRenderWorld$9);
            break;
         case 3:
            field10.forEach(DungeonESPModule::lambda$onRenderWorld$10);
         }

      }
   }

   public static Vec3 method0(Entity var0, float var1) {
      return new Vec3(var0.lastTickPosX + (var0.posX - var0.lastTickPosX) * (double)var1, var0.lastTickPosY + (var0.posY - var0.lastTickPosY) * (double)var1, var0.lastTickPosZ + (var0.posZ - var0.lastTickPosZ) * (double)var1);
   }

   public Boolean lambda$new$4() {
      return !this.field13.method1();
   }

   public Boolean lambda$new$5() {
      return !this.field6.method1();
   }

   public static boolean lambda$onUpdate$6(EntityLivingBase var0) {
      return var0 instanceof EntityBat || var0 instanceof EntityArmorStand || var0 instanceof EntityEnderman || var0 instanceof EntityOtherPlayerMP;
   }
}
