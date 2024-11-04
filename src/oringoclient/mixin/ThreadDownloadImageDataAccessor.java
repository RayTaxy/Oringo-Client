package oringo.mixin;

import net.minecraft.client.renderer.ThreadDownloadImageData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ThreadDownloadImageData.class})
public interface ThreadDownloadImageDataAccessor {
   @Accessor("imageThread")
   void setImageThread(Thread var1);
}
