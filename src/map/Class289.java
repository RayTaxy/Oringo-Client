package map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.DataWatcher.WatchableObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Rotations;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ObjectUtils;

public class Class289 extends DataWatcher {
   public WatchableObject[] field0 = new WatchableObject[5];
   public boolean field1;
   public final Entity field2;
   public static final Map field3 = Maps.newHashMap();
   public boolean field4 = true;

   public List getAllWatched() {
      ArrayList var1 = null;
      WatchableObject[] var2 = this.field0;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         WatchableObject var5 = var2[var4];
         if (var5 != null) {
            if (var1 == null) {
               var1 = Lists.newArrayList();
            }

            var1.add(var5);
         }
      }

      return var1;
   }

   public void func_111144_e() {
      this.field1 = false;
   }

   public Class289(Entity var1) {
      super(var1);
      this.field2 = var1;
   }

   public void setObjectWatched(int var1) {
      this.getWatchedObject(var1).setWatched(true);
      this.field1 = true;
   }

   public byte getWatchableObjectByte(int var1) {
      return (Byte)this.getWatchedObject(var1).getObject();
   }

   public void writeTo(PacketBuffer var1) {
      WatchableObject[] var2 = this.field0;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         WatchableObject var5 = var2[var4];
         if (var5 != null) {
            Class496.method0(var1, var5);
         }
      }

      var1.writeByte(127);
   }

   public WatchableObject getWatchedObject(int var1) {
      try {
         WatchableObject var2 = this.field0[var1];
         return var2;
      } catch (Throwable var6) {
         CrashReport var4 = CrashReport.makeCrashReport(var6, "Getting synched entity data");
         CrashReportCategory var5 = var4.makeCategory("Synched entity data");
         var5.addCrashSection("Data ID", var1);
         throw new ReportedException(var4);
      }
   }

   public static Scoreboard method2() {
      return Class82.field4.theWorld == null ? null : Class82.field4.theWorld.getScoreboard();
   }

   public int getWatchableObjectInt(int var1) {
      return (Integer)this.getWatchedObject(var1).getObject();
   }

   public void updateObject(int var1, Object var2) {
      WatchableObject var3 = this.getWatchedObject(var1);
      if (ObjectUtils.notEqual(var2, var3.getObject())) {
         var3.setObject(var2);
         this.field2.onDataWatcherUpdate(var1);
         var3.setWatched(true);
         this.field1 = true;
      }

   }

   public short getWatchableObjectShort(int var1) {
      return (Short)this.getWatchedObject(var1).getObject();
   }

   public List getChanged() {
      ArrayList var1 = null;
      if (this.field1) {
         WatchableObject[] var2 = this.field0;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            WatchableObject var5 = var2[var4];
            if (var5 != null && var5.isWatched()) {
               var5.setWatched(false);
               if (var1 == null) {
                  var1 = Lists.newArrayList();
               }

               var1.add(var5);
            }
         }
      }

      this.field1 = false;
      return var1;
   }

   public void putObject(int var1, WatchableObject var2) {
      if (this.field0.length <= var1) {
         WatchableObject[] var3 = new WatchableObject[var1 + 1];
         System.arraycopy(this.field0, 0, var3, 0, this.field0.length);
         this.field0 = var3;
      }

      this.field0[var1] = var2;
   }

   public boolean hasObjectChanged() {
      return this.field1;
   }

   public boolean getIsBlank() {
      return this.field4;
   }

   @SideOnly(Side.CLIENT)
   public void updateWatchedObjectsFromList(List var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         WatchableObject var3 = (WatchableObject)var2.next();
         WatchableObject var4 = this.field0[var3.getDataValueId()];
         if (var4 != null) {
            var4.setObject(var3.getObject());
            this.field2.onDataWatcherUpdate(var3.getDataValueId());
         }
      }

      this.field1 = true;
   }

   public String getWatchableObjectString(int var1) {
      return (String)this.getWatchedObject(var1).getObject();
   }

   public void addObject(int var1, Object var2) {
      Integer var3 = (Integer)field3.get(var2.getClass());
      if (var3 == null) {
         throw new IllegalArgumentException("Unknown data type: " + var2.getClass());
      } else if (var1 > 31) {
         throw new IllegalArgumentException("Data value id is too big with " + var1 + "! (Max is " + 31 + ")");
      } else if (this.isObjectPresent(var1)) {
         throw new IllegalArgumentException("Duplicate id value for " + var1 + "!");
      } else {
         WatchableObject var4 = new WatchableObject(var3, var1, var2);
         this.putObject(var1, var4);
         this.field4 = false;
      }
   }

   public ItemStack getWatchableObjectItemStack(int var1) {
      return (ItemStack)this.getWatchedObject(var1).getObject();
   }

   public boolean isObjectPresent(int var1) {
      return this.field0.length > var1 && this.field0[var1] != null;
   }

   public Rotations getWatchableObjectRotations(int var1) {
      return (Rotations)this.getWatchedObject(var1).getObject();
   }

   static {
      field3.put(Byte.class, 0);
      field3.put(Short.class, 1);
      field3.put(Integer.class, 2);
      field3.put(Float.class, 3);
      field3.put(String.class, 4);
      field3.put(ItemStack.class, 5);
      field3.put(BlockPos.class, 6);
      field3.put(Rotations.class, 7);
   }

   public float getWatchableObjectFloat(int var1) {
      return (Float)this.getWatchedObject(var1).getObject();
   }

   public void addObjectByDataType(int var1, int var2) {
      WatchableObject var3 = new WatchableObject(var2, var1, (Object)null);
      this.putObject(var1, var3);
      this.field4 = false;
   }
}
