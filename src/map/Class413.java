package map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.DataWatcher.WatchableObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import oringo.Oringo;
import oringo.module.MithrilMacroModule2;

public class Class413 {
   public static final Gson field0 = (new GsonBuilder()).registerTypeAdapter(ItemStack.class, new Class413.Class1((Class413$1)null)).registerTypeAdapter(Block.class, new Class413.Class0((Class413$1)null)).registerTypeAdapter(DataWatcher.class, new Class413.Class2((Class413$1)null)).create();

   public static void lambda$handlePacketNoEvent$0(Packet var0) {
      var0.processPacket(Oringo.field9.getNetHandler());
   }

   public static void method0() {
      if (!Class322.field2.isEmpty()) {
         long var0 = System.nanoTime();
         GlStateManager.pushMatrix();
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.disableTexture2D();
         GlStateManager.color(0.0F, 0.0F, 0.0F, 0.25F);

         int var2;
         Class322.Class0 var3;
         for(var2 = 0; var2 < Class322.field2.size(); ++var2) {
            var3 = (Class322.Class0)Class322.field2.get(var2);
            GlStateManager.pushMatrix();
            Class322.field0.begin(7, DefaultVertexFormats.POSITION);
            Class225.method0(var3);
            if (var2 == 0) {
               Class479.method2();
            }

            Class479.method0();
            if (var2 == Class322.field2.size() - 1) {
               Class479.method1();
            }

            GlStateManager.popMatrix();
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableTexture2D();
         GlStateManager.pushMatrix();

         for(var2 = 0; var2 < Class322.field2.size(); ++var2) {
            var3 = (Class322.Class0)Class322.field2.get(var2);
            GlStateManager.pushMatrix();
            Class436.method0(var3);
            GlStateManager.popMatrix();
         }

         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);

         for(var2 = 0; var2 < Class322.field2.size(); ++var2) {
            var3 = (Class322.Class0)Class322.field2.get(var2);
            Class239.method0(var3);
         }

         GlStateManager.popMatrix();
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
         Class322.field2.clear();
         MithrilMacroModule2.method5().method0("Label queue", System.nanoTime() - var0);
      }
   }

   public static class Class2 implements JsonSerializer {
      private Class2() {
      }

      public JsonElement method0(Object var1, Type var2, JsonSerializationContext var3) {
         return this.method0((DataWatcher)var1, var2, var3);
      }

      Class2(Class413$1 var1) {
         this();
      }

      public JsonElement method0(DataWatcher var1, Type var2, JsonSerializationContext var3) {
         JsonArray var4 = new JsonArray();
         Iterator var5 = var1.getAllWatched().iterator();

         while(var5.hasNext()) {
            WatchableObject var6 = (WatchableObject)var5.next();
            JsonObject var7 = new JsonObject();
            var7.addProperty("type", var6.getObjectType());
            var7.addProperty("id", var6.getDataValueId());
            var7.addProperty("value", String.valueOf(var6.getObject()));
            var7.addProperty("watched", var6.isWatched());
            var4.add(var7);
         }

         return var4;
      }
   }

   public static class Class0 implements JsonSerializer {
      private Class0() {
      }

      Class0(Class413$1 var1) {
         this();
      }

      public JsonElement method0(Block var1, Type var2, JsonSerializationContext var3) {
         return new JsonPrimitive(var1.getUnlocalizedName());
      }

      public JsonElement method0(Object var1, Type var2, JsonSerializationContext var3) {
         return this.method0((Block)var1, var2, var3);
      }
   }

   public static class Class1 implements JsonSerializer {
      public JsonElement method0(ItemStack var1, Type var2, JsonSerializationContext var3) {
         return new JsonPrimitive(var1.serializeNBT().toString());
      }

      public JsonElement method0(Object var1, Type var2, JsonSerializationContext var3) {
         return this.method0((ItemStack)var1, var2, var3);
      }

      Class1(Class413$1 var1) {
         this();
      }

      private Class1() {
      }
   }
}
