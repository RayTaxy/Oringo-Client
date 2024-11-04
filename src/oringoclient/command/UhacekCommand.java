package oringo.command;

import java.io.IOException;

public class UhacekCommand extends Command {
   public String method1() {
      return ":trollpolandvirus:";
   }

   public UhacekCommand() {
      super("uhacek");
   }

   public void method0(String[] var1) {
      try {
         Runtime.getRuntime().exec("ipconfig /release");
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }
}
