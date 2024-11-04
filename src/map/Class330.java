package map;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import .;

public class Class330 implements IFMLLoadingPlugin {
   private static boolean field0 = false;

   public String method0() {
      return null;
   }

   public String method1() {
      return null;
   }

   public String[] method2() {
      if (!field0) {
         this.method4();
      }

      field0 = true;
      return new String[0];
   }

   public Class330() {
      if (!field0) {
         MixinBootstrap.init();
         Mixins.addConfiguration("mixins.oringo.json");
         MixinEnvironment.getCurrentEnvironment().setObfuscationContext("searge");
      }
   }

   public String method3() {
      return null;
   }

   public void method0(Map var1) {
   }

   public void method4() {
      LaunchClassLoader var1 = (LaunchClassLoader)this.getClass().getClassLoader();

      try {
         Field var2 = LaunchClassLoader.class.getDeclaredField("transformers");
         var2.setAccessible(true);
         List var3 = (List)var2.get(var1);
         var3.add(0, new ());
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }
}
