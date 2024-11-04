package oringo.module;

import java.util.HashSet;
import java.util.Iterator;
import map.Class376;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class270;
import oringo.setting.ButtonSetting;
import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class CustomHubMap extends Module {
   public static final StringSetting field0;
   public static CustomHubMap.Class0[] aD_;
   public static int field2;
   public static final HashSet field3 = new HashSet();
   public Entity field4 = null;
   public static CustomHubMap.Class0 field5;
   public static long field6;
   public static final ButtonSetting field7;

   public static Minecraft access$100() {
      return field58;
   }

   @SubscribeEvent
   public void field0(Class210.Class0 var1) {
      if (Class376.method0("Village")) {
         if (this.field4 != null) {
            this.field4.ignoreFrustumCheck = true;
         } else {
            Iterator var2 = field58.theWorld.loadedEntityList.iterator();

            while(var2.hasNext()) {
               Entity var3 = (Entity)var2.next();
               if (var3 instanceof EntityItemFrame) {
                  var3.ignoreFrustumCheck = false;
                  ItemStack var4 = ((EntityItemFrame)var3).getDisplayedItem();
                  if (var4 != null && var4.getItem() instanceof ItemMap) {
                     MapData var5 = Items.filled_map.getMapData(var4, field58.theWorld);
                     if (var5 != null && var5.mapName.equals("map_31545")) {
                        this.field4 = var3;
                     }
                  }
               }
            }

         }
      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.field4 = null;
   }

   public CustomHubMap() {
      super("Custom Hub Map", Category.render, SubCategory.world, "Changes the texture of the big map in the hub");
      this.method0(new Setting[]{field0, field7});
      this.method1(true);
   }

   public static Minecraft access$200() {
      return field58;
   }

   static {
      for(int var0 = 0; var0 < 49; ++var0) {
         field3.add("map_" + (31521 + var0));
      }

      field5 = null;
      aD_ = new CustomHubMap.Class0[0];
      field0 = new StringSetting("File path", "");
      field7 = new ButtonSetting("Reload", AnimationsModule::method5);
      field6 = System.currentTimeMillis();
   }

   public static int method0(Entity var0, boolean var1) {
      int var2 = Oringo.field9.thePlayer.inventory.currentItem;
      float var3 = AutoPhobiaModule.method0(var0, Oringo.field9.thePlayer.inventory.getStackInSlot(var2), var1);

      for(int var4 = 0; var4 < 9; ++var4) {
         float var5 = AutoPhobiaModule.method0(var0, Oringo.field9.thePlayer.inventory.getStackInSlot(var4), var1);
         if (var5 > var3) {
            var3 = var5;
            var2 = var4;
         }
      }

      return var2;
   }

   private static class Class0 {
      private final ResourceLocation field0;
      private final int field1;

      public void method0() {
         CustomHubMap.access$100().getTextureManager().deleteTexture(this.field0);
      }

      static int access$000(CustomHubMap.Class0 var0) {
         return var0.field1;
      }

      public void method1() {
         CustomHubMap.access$200().getTextureManager().bindTexture(this.field0);
      }

      public Class0(ResourceLocation var1, int var2) {
         this.field0 = var1;
         this.field1 = var2;
      }
   }
}
