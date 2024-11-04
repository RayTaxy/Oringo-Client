package map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.module.IceFillHelperModule;
import oringo.setting.StringSetting;

public class Class28 {
   public static final Gson field0;
   public static final ConcurrentHashMap field1 = new ConcurrentHashMap();
   public static final List field2 = new ArrayList();
   public static final List field3 = new ArrayList();
   public static final String field4;

   public static boolean lambda$null$11(Entry var0) {
      return var0.getValue() == null || ((map.Class1)var0.getValue()).field3 == null && ((map.Class1)var0.getValue()).field2 == null && ((map.Class1)var0.getValue()).field1 == null;
   }

   public static void lambda$load$1(JsonElement var0) {
      JsonObject var1 = var0.getAsJsonObject();
      Class507 var2 = new Class507(var1.getAsJsonPrimitive("core").getAsInt(), var1.getAsJsonPrimitive("offset").getAsInt(), var1.getAsJsonPrimitive("block_count").getAsInt());
      var1.getAsJsonArray("blocks").forEach(Class28::lambda$null$0);
      field3.add(var2);
   }

   public static void lambda$removeEmptyData$12(Class507 var0) {
      var0.field1.entrySet().removeIf(Class28::lambda$null$11);
   }

   public static void lambda$save$9(JsonArray var0, BlockPos var1, map.Class1 var2) {
      JsonObject var3 = new JsonObject();
      JsonObject var4 = new JsonObject();
      var4.addProperty("x", var1.getX());
      var4.addProperty("y", var1.getY());
      var4.addProperty("z", var1.getZ());
      var3.add("pos", var4);
      var3.add("data", field0.toJsonTree(var2));
      var0.add(var3);
   }

   static {
      field4 = Oringo.field5 + "brush/brush.json";
      field0 = (new GsonBuilder()).registerTypeAdapter(map.Class1.class, new Class28.Class1()).registerTypeAdapter(map.Class1.class, new Class28.Class0()).create();
   }

   public static void method0() {
      if (!Class198.field0.method13() && !Class198.field4) {
         HashMap var0 = Maps.newHashMap();
         Iterator var1 = Class198.field2.theWorld.loadedTileEntityList.iterator();

         while(var1.hasNext()) {
            TileEntity var2 = (TileEntity)var1.next();
            if (var2 instanceof TileEntityChest) {
               TileEntityChest var3 = (TileEntityChest)var2;
               if (var3.getChestType() == 1) {
                  Class208 var4 = IceFillHelperModule.method0(var3.getPos().getX(), var3.getPos().getZ());
                  if (var4 != null) {
                     Integer var5 = (Integer)var0.get(var4.method45());
                     if (var5 == null) {
                        var0.put(var4.method45(), 1);
                     } else {
                        var0.replace(var4.method45(), var5 + 1);
                     }

                     if ((Integer)var0.get(var4.method45()) > var4.method4()) {
                        var4.field7 = true;
                        Class198.field4 = true;
                        break;
                     }
                  }
               }
            }
         }

      }
   }

   public static void lambda$null$4(Class507 var0, JsonElement var1) {
      JsonObject var2 = var1.getAsJsonObject();
      BlockPos var3 = new BlockPos(var2.getAsJsonPrimitive("x").getAsInt(), var2.getAsJsonPrimitive("y").getAsInt(), var2.getAsJsonPrimitive("z").getAsInt());
      map.Class1 var4 = new map.Class1();
      if (var2.has("id")) {
         var4.field3 = Block.getBlockById(var2.getAsJsonPrimitive("id").getAsInt());
      }

      if (var2.has("meta")) {
         var4.field0 = var2.getAsJsonPrimitive("meta").getAsInt();
      }

      if (var2.has("targetX")) {
         var4.field1 = new Vec3(var2.getAsJsonPrimitive("targetX").getAsDouble(), var2.getAsJsonPrimitive("targetY").getAsDouble(), var2.getAsJsonPrimitive("targetZ").getAsDouble());
      }

      if (var2.has("awaitSecret")) {
         var4.field5 = var2.getAsJsonPrimitive("awaitSecret").getAsBoolean();
      }

      if (var2.has("command")) {
         var4.field2 = var2.getAsJsonPrimitive("command").getAsString();
      }

      var0.field1.put(var3, var4);
   }

   public static boolean lambda$null$13(Entry var0) {
      return var0.getValue() == null || ((map.Class1)var0.getValue()).field3 == null && ((map.Class1)var0.getValue()).field2 == null && ((map.Class1)var0.getValue()).field1 == null;
   }

   public static void lambda$removeEmptyData$14(ConcurrentHashMap var0) {
      var0.entrySet().removeIf(Class28::lambda$null$13);
   }

   public static void lambda$load$5(JsonElement var0) {
      JsonObject var1 = var0.getAsJsonObject();
      Class507 var2 = new Class507(var1.getAsJsonPrimitive("core").getAsInt(), var1.getAsJsonPrimitive("offset").getAsInt(), var1.has("block_count") ? var1.getAsJsonPrimitive("block_count").getAsInt() : 10);
      var1.getAsJsonArray("blocks").forEach(Class28::lambda$null$4);
      field3.add(var2);
   }

   public static void lambda$load$3(JsonElement var0) {
      JsonArray var1 = var0.getAsJsonArray();
      ConcurrentHashMap var2 = new ConcurrentHashMap();
      field2.add(var2);
      var1.forEach(Class28::lambda$null$2);
   }

   public static void lambda$null$6(Map var0, JsonElement var1) {
      JsonObject var2 = var1.getAsJsonObject();
      BlockPos var3 = new BlockPos(var2.getAsJsonPrimitive("x").getAsInt(), var2.getAsJsonPrimitive("y").getAsInt(), var2.getAsJsonPrimitive("z").getAsInt());
      map.Class1 var4 = new map.Class1();
      if (var2.has("id")) {
         var4.field3 = Block.getBlockById(var2.getAsJsonPrimitive("id").getAsInt());
      }

      if (var2.has("meta")) {
         var4.field0 = var2.getAsJsonPrimitive("meta").getAsInt();
      }

      if (var2.has("targetX")) {
         var4.field1 = new Vec3(var2.getAsJsonPrimitive("targetX").getAsDouble(), var2.getAsJsonPrimitive("targetY").getAsDouble(), var2.getAsJsonPrimitive("targetZ").getAsDouble());
      }

      if (var2.has("awaitSecret")) {
         var4.field5 = var2.getAsJsonPrimitive("awaitSecret").getAsBoolean();
      }

      if (var2.has("command")) {
         var4.field2 = var2.getAsJsonPrimitive("command").getAsString();
      }

      var0.put(var3, var4);
   }

   public static void lambda$load$7(Entry var0) {
      Map var1 = StringSetting.method0(Integer.parseInt((String)var0.getKey()));
      ((JsonElement)var0.getValue()).getAsJsonArray().forEach(Class28::lambda$null$6);
   }

   public static void lambda$load$8(Class507 var0) {
      Class507 var10000 = (Class507)field1.put(var0.field2, var0);
   }

   public static void lambda$save$10(JsonArray var0, BlockPos var1, map.Class1 var2) {
      JsonObject var3 = new JsonObject();
      JsonObject var4 = new JsonObject();
      var4.addProperty("x", var1.getX());
      var4.addProperty("y", var1.getY());
      var4.addProperty("z", var1.getZ());
      var3.add("pos", var4);
      var3.add("data", field0.toJsonTree(var2));
      var0.add(var3);
   }

   public static void method1() {
      GL11.glTranslated(Oringo.field9.getRenderManager().viewerPosX, Oringo.field9.getRenderManager().viewerPosY, Oringo.field9.getRenderManager().viewerPosZ);
   }

   public static void lambda$null$2(ConcurrentHashMap var0, JsonElement var1) {
      JsonObject var2 = var1.getAsJsonObject();
      JsonObject var3 = var2.getAsJsonObject("pos");
      var0.put(new BlockPos(var3.getAsJsonPrimitive("x").getAsInt(), var3.getAsJsonPrimitive("y").getAsInt(), var3.getAsJsonPrimitive("z").getAsInt()), field0.fromJson(var2.get("data"), map.Class1.class));
   }

   public static void lambda$null$0(Class507 var0, JsonElement var1) {
      JsonObject var2 = var1.getAsJsonObject();
      JsonObject var3 = var2.getAsJsonObject("pos");
      var0.field1.put(new BlockPos(var3.getAsJsonPrimitive("x").getAsInt(), var3.getAsJsonPrimitive("y").getAsInt(), var3.getAsJsonPrimitive("z").getAsInt()), field0.fromJson(var2.get("data"), map.Class1.class));
   }

   public static class Class0 implements JsonDeserializer {
      public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
         return this.method1(var1, var2, var3);
      }

      public map.Class1 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
         JsonObject var4 = var1.getAsJsonObject();
         map.Class1 var5 = new map.Class1();
         if (var4.has("command")) {
            var5.field2 = var4.get("command").getAsString();
         }

         if (var4.has("etherwarp_target")) {
            JsonArray var6 = var4.getAsJsonArray("etherwarp_target");
            var5.field1 = new Vec3(var6.get(0).getAsDouble(), var6.get(1).getAsDouble(), var6.get(2).getAsDouble());
         }

         if (var4.has("block")) {
            var5.field3 = Block.getBlockById(var4.get("block").getAsInt());
            var5.field0 = var4.get("meta").getAsInt();
         }

         if (var4.has("await_secret")) {
            var5.field5 = var4.getAsJsonPrimitive("await_secret").getAsBoolean();
         }

         if (var4.has("chain")) {
            var5.a_ = var4.getAsJsonPrimitive("chain").getAsBoolean();
         }

         return var5;
      }
   }

   public static class Class1 implements JsonSerializer {
      public JsonElement method0(Object var1, Type var2, JsonSerializationContext var3) {
         return this.serialize((map.Class1)var1, var2, var3);
      }

      public JsonElement serialize(map.Class1 var1, Type var2, JsonSerializationContext var3) {
         JsonObject var4 = new JsonObject();
         if (var1.field2 != null) {
            var4.addProperty("command", var1.field2);
         }

         if (var1.field1 != null) {
            JsonArray var5 = new JsonArray();
            var5.add(new JsonPrimitive(var1.field1.xCoord));
            var5.add(new JsonPrimitive(var1.field1.yCoord));
            var5.add(new JsonPrimitive(var1.field1.zCoord));
            var4.add("etherwarp_target", var5);
         }

         if (var1.field3 != null) {
            var4.addProperty("block", Block.getIdFromBlock(var1.field3));
            var4.addProperty("meta", var1.field0);
         }

         if (var1.field5) {
            var4.addProperty("await_secret", true);
         }

         if (var1.a_) {
            var4.addProperty("chain", true);
         }

         return var4;
      }
   }
}
