package oringo.module;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.image.BufferedImage;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import map.Class447;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class ChestStealerModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Delay", 100.0D, 30.0D, 200.0D, 1.0D);
   public final Class447 field1 = new Class447();
   public final BooleanSetting field2 = new BooleanSetting("Auto close", true);
   public final BooleanSetting field3 = new BooleanSetting("Steal trash", false);
   public final BooleanSetting field4 = new BooleanSetting("Name check", true);

   public static void method0(BufferedImage var0) {
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new ScreenShotModule.Class0(var0), (ClipboardOwner)null);
   }

   public static int method5() {
      try {
         HttpsURLConnection var0 = (HttpsURLConnection)(new URL("https://api.plancke.io/hypixel/v1/punishmentStats")).openConnection();
         CropNukerModule.method0(var0);
         var0.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
         var0.setRequestMethod("GET");
         JsonObject var1 = (new JsonParser()).parse(new InputStreamReader(var0.getInputStream())).getAsJsonObject();
         return var1.get("record").getAsJsonObject().get("staff_total").getAsInt();
      } catch (Exception var2) {
         var2.printStackTrace();
         return -1;
      }
   }

   @SubscribeEvent
   public void method0(BackgroundDrawnEvent var1) {
      if (var1.gui instanceof GuiChest && this.method44()) {
         Container var2 = ((GuiChest)var1.gui).inventorySlots;
         if (var2 instanceof ContainerChest && (!this.field4.method1() || ((ContainerChest)var2).getLowerChestInventory().getDisplayName().getUnformattedText().equals("Chest") || ((ContainerChest)var2).getLowerChestInventory().getDisplayName().getUnformattedText().equals("LOW"))) {
            int var3;
            Item var4;
            for(var3 = 0; var3 < ((ContainerChest)var2).getLowerChestInventory().getSizeInventory(); ++var3) {
               if (var2.getSlot(var3).getHasStack() && this.field1.a_((long)this.field0.method0())) {
                  var4 = var2.getSlot(var3).getStack().getItem();
                  if (this.field3.method1() || var4 instanceof ItemEnderPearl || var4 instanceof ItemTool || var4 instanceof ItemArmor || var4 instanceof ItemBow || var4 instanceof ItemPotion || var4 == Items.arrow || var4 instanceof ItemAppleGold || var4 instanceof ItemSword || var4 instanceof ItemBlock) {
                     field58.playerController.windowClick(var2.windowId, var3, 0, 1, field58.thePlayer);
                     this.field1.o_();
                     return;
                  }
               }
            }

            var3 = 0;

            while(true) {
               if (var3 >= ((ContainerChest)var2).getLowerChestInventory().getSizeInventory()) {
                  if (this.field2.method1()) {
                     field58.thePlayer.closeScreen();
                  }
                  break;
               }

               if (var2.getSlot(var3).getHasStack()) {
                  var4 = var2.getSlot(var3).getStack().getItem();
                  if (this.field3.method1() || var4 instanceof ItemEnderPearl || var4 instanceof ItemTool || var4 instanceof ItemArmor || var4 instanceof ItemBow || var4 instanceof ItemPotion || var4 == Items.arrow || var4 instanceof ItemAppleGold || var4 instanceof ItemSword || var4 instanceof ItemBlock) {
                     return;
                  }
               }

               ++var3;
            }
         }
      }

   }

   public ChestStealerModule() {
      super("Chest Stealer", 0, Category.player, SubCategory.player, "Takes items from chests");
      this.method0((Setting[])(new Setting[]{this.field0, this.field4, this.field3, this.field2}));
   }
}
