package oringo.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import map.Class447;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.input.Mouse;
import oringo.Oringo;
import oringo.command.BindCommand;
import oringo.event.Class274;
import oringo.event.Class307;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class CommandBindingModule extends Module {
   public boolean field0 = false;
   public final BooleanSetting field1 = new BooleanSetting("Show binds in the lore", true);
   public final ArrayList field2 = new ArrayList();
   public boolean field3 = false;

   public static boolean method5() {
      return Oringo.field9.thePlayer.motionX != 0.0D && Oringo.field9.thePlayer.motionZ != 0.0D && Oringo.field9.thePlayer.motionY != 0.0D;
   }

   @SubscribeEvent
   public void method0(Class274 var1) {
      UUID var2 = HitboxesModule.method0(field58.thePlayer.getHeldItem());
      if (var2 != null) {
         Iterator var3 = BindCommand.field1.iterator();

         while(true) {
            BindCommand.Class0 var4;
            do {
               do {
                  if (!var3.hasNext()) {
                     this.field3 = Mouse.isButtonDown(1);
                     return;
                  }

                  var4 = (BindCommand.Class0)var3.next();
               } while(!var4.field0.equals(var2));
            } while(var4.field1 < 2);

            long var5 = 0L;
            if (!this.field3) {
               String[] var7 = var4.field2.split(", ");
               int var8 = var7.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  String var10 = var7[var9];
                  if (var10.startsWith(".delay ")) {
                     var5 += Long.parseLong(var10.split(" ")[1]);
                  } else {
                     Class447 var11 = new Class447();
                     var11.method0(-var5);
                     this.field2.add(new SimpleEntry(var10, var11));
                  }
               }
            }

            if (var4.field1 == 3) {
               var1.method9();
            }
         }
      }
   }

   public static boolean lambda$onWorldRender$0(Entry var0) {
      if (((Class447)var0.getValue()).a_(0L)) {
         if (ClientCommandHandler.instance.executeCommand(field58.thePlayer, (String)var0.getKey()) != 0) {
            return true;
         } else {
            field58.thePlayer.sendChatMessage((String)var0.getKey());
            return true;
         }
      } else {
         return false;
      }
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.END) {
         this.field3 = Mouse.isButtonDown(1);
         this.field0 = Mouse.isButtonDown(0);
      }
   }

   public static boolean method0(ItemStack var0) {
      return var0.getItem() instanceof ItemSword && CreateNewkeybindModule.method0(var0, "Ability: Wither Impact", true);
   }

   @SubscribeEvent
   public void method0(Class307 var1) {
      if (!this.field0) {
         UUID var2 = HitboxesModule.method0(field58.thePlayer.getHeldItem());
         if (var2 != null) {
            Iterator var3 = BindCommand.field1.iterator();

            while(true) {
               BindCommand.Class0 var4;
               do {
                  do {
                     if (!var3.hasNext()) {
                        this.field0 = Mouse.isButtonDown(0);
                        return;
                     }

                     var4 = (BindCommand.Class0)var3.next();
                  } while(!var4.field0.equals(var2));
               } while(var4.field1 > 1);

               long var5 = 0L;
               if (!this.field0) {
                  String[] var7 = var4.field2.split(", ");
                  int var8 = var7.length;

                  for(int var9 = 0; var9 < var8; ++var9) {
                     String var10 = var7[var9];
                     if (var10.startsWith(".delay ")) {
                        var5 += Long.parseLong(var10.split(" ")[1]);
                     } else {
                        Class447 var11 = new Class447();
                        var11.method0(-var5);
                        this.field2.add(new SimpleEntry(var10, var11));
                     }
                  }
               }

               if (var4.field1 == 1) {
                  var1.method9();
               }
            }
         }
      }
   }

   public CommandBindingModule() {
      super("Command Binding", Category.other, SubCategory.other, "Enables command binding from .bind");
      this.method0((Setting[])(new Setting[]{this.field1}));
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      this.field2.removeIf(CommandBindingModule::lambda$onWorldRender$0);
   }

   public static int method0(C0EPacketClickWindow var0) {
      return var0.getMode();
   }

   @SubscribeEvent
   public void method0(ItemTooltipEvent var1) {
      if (this.field1.method1()) {
         UUID var2 = HitboxesModule.method0(var1.itemStack);
         if (var2 != null) {
            Iterator var3 = BindCommand.field1.iterator();

            while(var3.hasNext()) {
               BindCommand.Class0 var4 = (BindCommand.Class0)var3.next();
               if (var4.field0.equals(var2)) {
                  String var5 = "§e§lLEFT";
                  switch(var4.field1) {
                  case 1:
                     var5 = var5 + " (OVERRIDE)";
                     break;
                  case 2:
                     var5 = "§e§lRIGHT";
                     break;
                  case 3:
                     var5 = "§e§lRIGHT (OVERRIDE)";
                  }

                  var1.toolTip.add(var5 + " §f" + var4.field2);
               }
            }

         }
      }
   }
}
