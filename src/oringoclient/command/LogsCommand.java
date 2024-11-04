package oringo.command;

import java.awt.Desktop;
import java.io.File;
import oringo.module.NamesOnlyModule;

public class LogsCommand extends Command {
   public static NamesOnlyModule method2() {
      return NamesOnlyModule.field3;
   }

   public String method1() {
      return "Open latest.txt";
   }

   public LogsCommand() {
      super("logs");
   }

   public void method0(String[] var1) throws Exception {
      Desktop.getDesktop().open(new File("logs/latest.log"));
   }
}
