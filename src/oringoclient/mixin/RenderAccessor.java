package oringo.mixin;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({Render.class})
public interface RenderAccessor {
   @Invoker("getEntityTexture")
   ResourceLocation getEntityTextureInvoker(Entity var1);
}
