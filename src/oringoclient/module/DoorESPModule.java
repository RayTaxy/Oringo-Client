package oringo.module;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import map.Class178;
import map.Class208;
import map.Class229;
import map.Class322;
import map.Class376;
import map.Class424;
import map.Class432;
import map.Class447;
import map.Class469;
import map.Class496;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Post;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class DoorESPModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Key ESP", true);
   public final BooleanSetting aC_ = new BooleanSetting("Tracers", true);
   public EntityArmorStand field2 = null;
   public final ArrayList field3 = new ArrayList();
   public final BooleanSetting field4 = new BooleanSetting("Without key", false);
   public boolean field5 = true;

   public static boolean lambda$onUpdate$0(EntityArmorStand var0) {
      return var0.getCurrentArmor(3) != null && var0.getCurrentArmor(3).getItem() == Items.skull;
   }

   public boolean method0(Class208 var1, Class208 var2) {
      if (var1 != null && var2 != null) {
         return var1.field6 == Class432.field2 && var2.field6 != Class432.field2 || var2.field6 == Class432.field2 && var1.field6 != Class432.field2;
      } else {
         return false;
      }
   }

   public DoorESPModule() {
      super("Door ESP", Category.render, SubCategory.world, "Shows you wither and blood door locations");
      this.method0((Setting[])(new Setting[]{this.aC_, this.field4, this.field0}));
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Pre var1) {
      if (this.field2 != null) {
         if (var1.entity.equals(this.field2)) {
            KillAuraModule.method12();
         }

      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (Class496.field5) {
         Iterator var2 = this.field3.iterator();

         while(var2.hasNext()) {
            DoorESPModule.Class0 var3 = (DoorESPModule.Class0)var2.next();
            this.method0(var3);
         }

         if (this.field2 != null && this.field0.method1()) {
            Color var4 = this.field5 ? Color.black : Color.RED;
            if (this.aC_.method1()) {
               Vec3 var5 = this.field2.getPositionEyes(1.0F);
               AutoArrowModule.method0(var5.xCoord, var5.yCoord, var5.zCoord, var4);
            }
         }

      }
   }

   public static boolean method2() {
      Iterator var0 = Class322.method0().iterator();

      String var1;
      do {
         if (!var0.hasNext()) {
            return false;
         }

         var1 = (String)var0.next();
      } while(!var1.startsWith("○ " + Class229.method1().method13().method0().toLowerCase()));

      return true;
   }

   public static void method0(ItemStack var0, Class447 var1, double var2) {
      ThornAimbotModule.method0(var0, (float)((double)var1.method0() / var2));
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (this.field2 != null) {
         if (var1.entity.equals(this.field2)) {
            AutoSalvageModule.method14();
         }

      }
   }

   public static void method0(double var0) {
      GL11.glBegin(6);

      for(int var2 = 0; var2 <= 90; ++var2) {
         double var3 = Math.sin((double)var2 * 3.141592653589793D / 45.0D) * var0;
         double var5 = Math.cos((double)var2 * 3.141592653589793D / 45.0D) * var0;
         GL11.glVertex2d(var3, var5);
      }

      GL11.glEnd();
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (Class496.field5) {
         List var2 = field58.theWorld.getEntities(EntityArmorStand.class, DoorESPModule::lambda$onUpdate$0);
         this.field2 = null;
         Iterator var3 = var2.iterator();

         while(var3.hasNext()) {
            EntityArmorStand var4 = (EntityArmorStand)var3.next();
            ItemStack var5 = var4.getCurrentArmor(3);
            if ("BOSS".equals(var5.getDisplayName())) {
               this.field5 = false;
               this.field2 = var4;
            }

            if ("GOLDEN".equals(var5.getDisplayName())) {
               this.field5 = true;
               this.field2 = var4;
            }
         }

         this.field3.clear();
         if (this.field4.method1() || Class376.method0("■ 1x") || Class376.method0("■ ✓")) {
            ArrayList var9 = Class469.method1();
            int var10 = var9.size();
            int var11 = 0;
            Iterator var6 = var9.iterator();

            while(true) {
               Class208 var7;
               Class208 var8;
               do {
                  do {
                     while(true) {
                        do {
                           if (!var6.hasNext()) {
                              return;
                           }

                           var7 = (Class208)var6.next();
                           ++var11;
                        } while(var7 == null);

                        if (var7.field11 == null || var7.field11.field0 != Class424.field2 && var7.field11.field0 != Class424.field0) {
                           break;
                        }

                        if (var10 > var11 + 5) {
                           var8 = (Class208)var9.get(var11 + 5);
                           if (this.method0(var7, var8)) {
                              this.field3.add(new DoorESPModule.Class0(var7, var8, var7.field11, false));
                              break;
                           }
                        }
                     }
                  } while(var7.field4 == null);
               } while(var7.field4.field0 != Class424.field2 && var7.field4.field0 != Class424.field0);

               if (var10 > var11) {
                  var8 = (Class208)var9.get(var11);
                  if (this.method0(var7, var8)) {
                     this.field3.add(new DoorESPModule.Class0(var7, var8, var7.field4, true));
                  }
               }
            }
         }
      }
   }

   public void method0(DoorESPModule.Class0 var1) {
      if (DoorESPModule.Class0.access$000(var1) != null && DoorESPModule.Class0.access$000(var1).field6 != Class432.field2 || DoorESPModule.Class0.access$100(var1).field6 != Class432.field2) {
         Color var2 = DoorESPModule.Class0.access$200(var1).method0(true);
         GlStateManager.color((float)var2.getRed() / 255.0F, (float)var2.getGreen() / 255.0F, (float)var2.getBlue() / 255.0F, 0.2F);
         GhostBlocksModule.method0((new AxisAlignedBB((double)(DoorESPModule.Class0.access$100(var1).field1 - 1 + (DoorESPModule.Class0.access$300(var1) ? 16 : 0)), 69.0D, (double)(DoorESPModule.Class0.access$100(var1).field5 - 1 + (!DoorESPModule.Class0.access$300(var1) ? 16 : 0)), (double)(DoorESPModule.Class0.access$100(var1).field1 + 2 + (DoorESPModule.Class0.access$300(var1) ? 16 : 0)), 73.0D, (double)(DoorESPModule.Class0.access$100(var1).field5 + 2 + (!DoorESPModule.Class0.access$300(var1) ? 16 : 0)))).offset(-field58.getRenderManager().viewerPosX, -field58.getRenderManager().viewerPosY, -field58.getRenderManager().viewerPosZ), false);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (this.aC_.method1()) {
            AutoArrowModule.method0((double)(DoorESPModule.Class0.access$100(var1).field1 + (DoorESPModule.Class0.access$300(var1) ? 16 : 0)) + 0.5D, 70.5D, (double)(DoorESPModule.Class0.access$100(var1).field5 + (!DoorESPModule.Class0.access$300(var1) ? 16 : 0)) + 0.5D, DoorESPModule.Class0.access$200(var1).method0(true));
         }
      }

   }

   public static class Class0 {
      private final Class208 field0;
      private final Class208 field1;
      private final boolean field2;
      private final Class178 field3;

      static boolean access$300(DoorESPModule.Class0 var0) {
         return var0.field2;
      }

      static Class208 access$000(DoorESPModule.Class0 var0) {
         return var0.field0;
      }

      static Class208 access$100(DoorESPModule.Class0 var0) {
         return var0.field1;
      }

      public Class0(Class208 var1, Class208 var2, Class178 var3, boolean var4) {
         this.field1 = var1;
         this.field0 = var2;
         this.field3 = var3;
         this.field2 = var4;
      }

      static Class178 access$200(DoorESPModule.Class0 var0) {
         return var0.field3;
      }
   }
}
