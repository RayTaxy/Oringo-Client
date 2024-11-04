package map;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Class285 {
   public String field0;

   public static List method0() {
      return Class430.field1;
   }

   public static List method0(String var0) {
      ArrayList var1 = new ArrayList();
      Matcher var2 = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(var0);

      while(var2.find()) {
         String var3 = var2.group(1).replace("\"", "");
         var1.add(var3);
      }

      return var1.subList(0, var1.size() - 1);
   }
}
