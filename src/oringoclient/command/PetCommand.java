package oringo.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import map.Class302;
import map.Class362;
import map.Class409;
import map.Class447;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class270;
import oringo.module.AutoRabbitModule;
import oringo.module.NoRenderModule;

public class PetCommand extends Command {
   public int field0 = -1;
   public boolean cs_;
   public String field2;
   public final Class447 field3 = new Class447();

   public PetCommand() {
      super("pet");
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.cs_ = false;
   }

   public static String[] method2() {
      return (String[])Class409.field5.keySet().toArray(new String[0]);
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (this.cs_ && !this.field3.a_(1500L)) {
         if (var1.field0 instanceof S2DPacketOpenWindow) {
            S2DPacketOpenWindow var2 = (S2DPacketOpenWindow)var1.field0;
            if (var2.getWindowTitle().getUnformattedText().equals("Pets")) {
               this.field0 = var2.getWindowId();
               var1.method9();
            }
         } else if (var1.field0 instanceof S30PacketWindowItems) {
            S30PacketWindowItems var9 = (S30PacketWindowItems)var1.field0;
            if (var9.func_148911_c() != this.field0) {
               return;
            }

            boolean var3 = false;
            int var4 = -1;
            ItemStack[] var5 = var9.getItemStacks();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               ItemStack var8 = var5[var7];
               ++var4;
               if (var8 != null && var8.getItem() == Items.skull && ChatFormatting.stripFormatting(var8.getDisplayName()).toLowerCase().contains(this.field2)) {
                  var3 = true;
                  Class302.method2(new C0EPacketClickWindow(this.field0, var4, 0, 0, var8, (short)1));
                  break;
               }
            }

            if (!var3) {
               method2("Unable to find the pet!");
            }

            Class302.method2(new C0DPacketCloseWindow(this.field0));
            this.cs_ = false;
         }

      }
   }

   public String method1() {
      return "Swaps to the specified pet";
   }

   public static boolean method3() {
      long var0 = System.currentTimeMillis() / 1000L;
      if (var0 != NoRenderModule.field0 && NoRenderModule.field3.method1()) {
         NoRenderModule.field4 = true;
         NoRenderModule.field0 = var0;
      }

      boolean var10000;
      if (Class362.field47.method44()) {
         label24: {
            if (NoRenderModule.field2.method1()) {
               if (NoRenderModule.aK_) {
                  break label24;
               }
            } else if (NoRenderModule.field4) {
               break label24;
            }

            if (NoRenderModule.field6.isEmpty()) {
               var10000 = true;
               return var10000;
            }
         }
      }

      var10000 = false;
      return var10000;
   }

   public void method0(String[] var1) throws Exception {
      if (var1.length == 0) {
         method2("Use " + AutoRabbitModule.method1() + "pet <name>");
      } else {
         this.field2 = String.join(" ", var1);
         field9.thePlayer.sendChatMessage("/pet");
         this.field3.o_();
         this.field0 = -1;
         this.cs_ = true;
      }
   }

   public static Color method0(Color var0, Color var1, float var2) {
      return new Color(var0.getRed() + (int)((float)(var1.getRed() - var0.getRed()) * var2), var0.getBlue() + (int)((float)(var1.getBlue() - var0.getBlue()) * var2), var0.getGreen() + (int)((float)(var1.getGreen() - var0.getGreen()) * var2), var0.getAlpha() + (int)((float)(var1.getAlpha() - var0.getAlpha()) * var2));
   }
}
