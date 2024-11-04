package oringo.module;

public class AntiStuckModule extends Module {
   public static final AntiStuckModule field0 = new AntiStuckModule();

   public AntiStuckModule() {
      super("Anti Stuck", Category.movement, SubCategory.movement, "Prevents you from getting stuck in some blocks");
   }

   public static StringBuilder method0(StringBuilder var0, String var1) {
      return var0.append(var1);
   }
}
