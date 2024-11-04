package oringo.command;

import map.Class12;
import map.Class475;
import oringo.module.FragHelperModule;

public class GenInfoCommand extends Command {
   public GenInfoCommand() {
      super("geninfo");
   }

   public String method1() {
      return null;
   }

   public void method0(String[] var1) {
      Class12 var2 = FragHelperModule.method0((int)field9.thePlayer.posX, (int)field9.thePlayer.posZ);
      method0(String.valueOf(Class475.method0(var2.method1(), var2.method0())));
   }
}
