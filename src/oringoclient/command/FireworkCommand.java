package oringo.command;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import oringo.module.ShortbowTriggerbotModule;

public class FireworkCommand extends Command {
   public FireworkCommand() {
      super("firework");
   }

   public void method0(String[] var1) {
      if (var1.length == 2) {
         ItemStack var2 = new ItemStack(Items.fireworks);
         var2.stackSize = 64;
         var2.setStackDisplayName("crash");
         new NBTTagList();
         NBTTagCompound var4 = var2.serializeNBT();
         NBTTagCompound var5 = var4.getCompoundTag("tag").getCompoundTag("Fireworks");
         NBTTagList var6 = new NBTTagList();
         NBTTagCompound var7 = new NBTTagCompound();
         var7.setTag("Type", new NBTTagByte((byte)1));
         var7.setTag("Flicker", new NBTTagByte((byte)1));
         var7.setTag("Trail", new NBTTagByte((byte)3));
         int[] var8 = new int[Integer.parseInt(var1[1])];

         int var9;
         for(var9 = 0; var9 < Integer.parseInt(var1[1]); ++var9) {
            var8[var9] = 261799 + var9;
         }

         var7.setIntArray("Colors", var8);
         var8 = new int[100];

         for(var9 = 0; var9 < 100; ++var9) {
            var8[var9] = 11250603 + var9;
         }

         var7.setIntArray("FadeColors", var8);

         for(var9 = 0; var9 < Integer.parseInt(var1[0]); ++var9) {
            var6.appendTag(var7);
         }

         var5.setTag("Explosions", var6);
         var4.getCompoundTag("tag").setTag("Fireworks", var5);
         ShortbowTriggerbotModule.method0("Oringo Client", "NBT Size: " + var4.toString().length(), 2000);
         var2.deserializeNBT(var4);
         field9.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, var2));
      } else {
         ShortbowTriggerbotModule.method0("Oringo Client", "/firework explosions colors", 1000);
      }

   }

   public String method1() {
      return "Gives you a crash firework. You need to have creative";
   }
}
