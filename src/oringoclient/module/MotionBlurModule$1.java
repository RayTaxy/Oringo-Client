package oringo.module;

import java.util.List;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

class MotionBlurModule$1 extends FallbackResourceManager {
   final MotionBlurModule field0;

   MotionBlurModule$1(MotionBlurModule var1, IMetadataSerializer var2) {
      super(var2);
      this.field0 = var1;
   }

   public IResource getResource(ResourceLocation var1) {
      return new MotionBlurModule.Class0();
   }

   public List getAllResources(ResourceLocation var1) {
      return null;
   }
}
