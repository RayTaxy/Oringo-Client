package map;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.item.EntityArmorStand;

public class Class176 {
   public static boolean method0(EntityArmorStand var0) {
      return var0.getCurrentArmor(3) != null && var0.getCurrentArmor(3).serializeNBT().getCompoundTag("tag").getCompoundTag("SkullOwner").getCompoundTag("Properties").toString().contains("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIwNzU5NGUyZGYyNzM5MjFhNzdjMTAxZDBiZmRmYTExMTVhYmVkNWI5YjIwMjllYjQ5NmNlYmE5YmRiYjRiMyJ9fX0=");
   }

   public static void method0(AtomicInteger var0) {
      var0.set(-1);
      GLAllocation.deleteDisplayLists(var0.get());
      Class170.field0.remove(var0);
   }
}
