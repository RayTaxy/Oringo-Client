package map;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.mixin.GuiEditSignAccessor;
import oringo.module.ChestStealerModule;
import oringo.module.ClickGuiModule;
import oringo.module.InventoryManagerModule;
import oringo.module.KillAuraModule;
import oringo.module.TerminalAuraModule;

public class Class237 {
   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.gui instanceof GuiEditSign && this.method0((GuiEditSign)var1.gui)) {
         GuiButton var2 = (GuiButton)var1.buttonList.get(0);
         int var3 = var2.yPosition;
         int var4 = var2.xPosition;
         int var5 = var2.width;
         int var6 = var2.height;
         var1.buttonList.add(new GuiButton(-2134, var4, var3 + var6 + 6, var5, var6, "x71,680"));
      }

      if (ClickGuiModule.field30.method1()) {
         if (var1.gui instanceof GuiContainer) {
            var1.buttonList.add(new GuiButton(-5228, 3, 3, 150, 20, "Disable Kill Aura"));
            var1.buttonList.add(new GuiButton(-5936, 3, 26, 150, 20, "Disable Chest Stealer"));
            var1.buttonList.add(new GuiButton(-1835, 3, 49, 150, 20, "Disable Inventory Manager"));
            var1.buttonList.add(new GuiButton(-1464, 3, 72, 150, 20, "Disable Terminal Aura"));
         }

      }
   }

   public static boolean method0(EntityZombie var0) {
      return true;
   }

   public boolean method0(GuiEditSign var1) {
      IChatComponent[] var2 = ((GuiEditSignAccessor)var1).getTileSign().signText;
      return var2[2].getUnformattedText().equals("Enter amount") && var2[3].getUnformattedText().equals("to order");
   }

   @SubscribeEvent
   public void method0(Pre var1) {
      switch(var1.button.id) {
      case -5936:
         ((ChestStealerModule)Class362.method0(ChestStealerModule.class)).method1(false);
         break;
      case -5228:
         ((KillAuraModule)Class362.method0(KillAuraModule.class)).method1(false);
         break;
      case -2134:
         ((GuiEditSignAccessor)var1.gui).getTileSign().signText[0] = new ChatComponentText("71680");
         var1.gui.onGuiClosed();
         break;
      case -1835:
         ((InventoryManagerModule)Class362.method0(InventoryManagerModule.class)).method1(false);
         break;
      case -1464:
         ((TerminalAuraModule)Class362.method0(TerminalAuraModule.class)).method1(false);
      }

   }

   public static void method0(double var0, double var2, double var4) {
      GL11.glTranslated(var0 - Oringo.field9.getRenderManager().viewerPosX, var2 - Oringo.field9.getRenderManager().viewerPosY, var4 - Oringo.field9.getRenderManager().viewerPosZ);
   }
}
