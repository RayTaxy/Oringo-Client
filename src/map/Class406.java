package map;

import org.lwjgl.opengl.GL20;

public class Class406 {
   public final int field0;
   public final int field1;
   public final int field2;
   public final int field3;
   public final String field4;

   public Class406(int var1, int var2) {
      this.field2 = var2;
      this.field1 = var1;
      this.field4 = GL20.glGetActiveUniform(var1, var2, 1024);
      this.field0 = GL20.glGetActiveUniformType(var1, var2);
      this.field3 = GL20.glGetActiveUniformSize(var1, var2);
   }
}
