package oringo.command;

import java.util.ArrayList;
import java.util.Arrays;
import map.Class253;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import oringo.module.ClickGuiModule;

public abstract class Command {
   protected static final Minecraft field9 = Minecraft.getMinecraft();
   private final String[] field0;

   public boolean a_() {
      return false;
   }

   public String method4() {
      return this.method1();
   }

   public static void method0(String var0) {
      Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(var0));
   }

   public Command(String var1, String... var2) {
      ArrayList var3 = new ArrayList();
      var3.add(var1);
      var3.addAll(Arrays.asList(var2));
      this.field0 = (String[])var3.toArray(new String[0]);
      MinecraftForge.EVENT_BUS.register(this);
   }

   public String[] method5() {
      return this.field0;
   }

   public static void method2(Object var0) {
      if (field9.thePlayer != null) {
         Minecraft.getMinecraft().thePlayer.addChatMessage(new Class253.Class0("OringoClient §7» §f" + var0, ClickGuiModule.field0.method17().getRGB()));
      }

   }

   public abstract void method0(String[] var1) throws Exception;

   public abstract String method1();
}
