package oringo.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C0EPacketClickWindow.class})
public interface C0EPacketClickWindowAccessor {
   @Accessor("windowId")
   void setWindowID(int var1);

   @Accessor
   void setClickedItem(ItemStack var1);
}
