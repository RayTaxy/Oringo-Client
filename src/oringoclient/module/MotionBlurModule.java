package oringo.module;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import oringo.mixin.SimpleReloadableResourceManagerAccessor;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class MotionBlurModule extends Module {
   public static double field0 = -1.0D;
   public Map aL_ = null;
   public static final DoubleSetting field2 = new DoubleSetting("Blur amount", 0.2D, 0.1D, 0.9D, 0.01D);

   public MotionBlurModule() {
      super("Motion Blur", Category.render, SubCategory.ui);
      this.method0(new Setting[]{field2});
   }

   public static DoubleSetting access$000() {
      return field2;
   }

   @SubscribeEvent
   public void method0(RenderTickEvent var1) {
      if (var1.phase != Phase.END) {
         if (this.aL_ == null) {
            this.aL_ = ((SimpleReloadableResourceManagerAccessor)Minecraft.getMinecraft().getResourceManager()).getDomainResourceManagers();
         }

         if (!this.aL_.containsKey("motionblur")) {
            this.aL_.put("motionblur", new MotionBlurModule$1(this, new IMetadataSerializer()));
         }

         if (!field58.entityRenderer.isShaderActive() || field0 != field2.method0()) {
            field58.entityRenderer.loadShader(new ResourceLocation("motionblur", "motionblur"));
            field0 = field2.method0();
         }

      }
   }

   public void b_() {
      field58.entityRenderer.stopUseShader();
   }

   public static class Class0 implements IResource {
      public String getResourcePackName() {
         return null;
      }

      public ResourceLocation getResourceLocation() {
         return null;
      }

      public boolean hasMetadata() {
         return false;
      }

      public IMetadataSection getMetadata(String var1) {
         return null;
      }

      public InputStream getInputStream() {
         double var1 = 1.0D - MotionBlurModule.access$000().method0();
         return new ByteArrayInputStream("{\n  \"targets\": [\n    \"swap\",\n    \"previous\"\n  ],\n  \"passes\": [\n    {\n      \"name\": \"motionblur\",\n      \"intarget\": \"minecraft:main\",\n      \"outtarget\": \"swap\",\n      \"auxtargets\": [\n        {\n          \"name\": \"PrevSampler\",\n          \"id\": \"previous\"\n        }\n      ],\n      \"uniforms\": [\n        {\n          \"name\": \"Phosphor\",\n          \"values\": [ blur_amount, blur_amount, blur_amount ]\n        }\n      ]\n    },\n    {\n      \"name\": \"blit\",\n      \"intarget\": \"swap\",\n      \"outtarget\": \"previous\"\n    },\n    {\n      \"name\": \"blit\",\n      \"intarget\": \"swap\",\n      \"outtarget\": \"minecraft:main\"\n    }\n  ]\n}\n".replaceAll("blur_amount", String.valueOf(var1 == 1.0D ? 0.99D : var1)).getBytes(StandardCharsets.UTF_8));
      }
   }
}
