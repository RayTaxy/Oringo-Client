package oringo.module;

import map.Class442;

public enum SubCategory {
   visual("Visual", Class442.field1),
   mining("Mining", Class442.field1),
   slayer("Slayer", Class442.field1),
   world("World", Class442.field2),
   qol("QOL", Class442.field1),
   ui("UI", Class442.field2),
   floor7("Floor 7", Class442.field4),
   puzzle("Puzzle", Class442.field4);

   private static final SubCategory[] field16 = new SubCategory[]{skills, mining, slayer, rift, qol, visual, main, floor7, puzzle, ui, world, combat, movement, player, other, keybinds};
   main("Main", Class442.field4),
   movement("Movement", Class442.field0);

   private final String name;
   private final Class442 field18;
   rift("Rift", Class442.field1),
   skills("Skills", Class442.field1),
   keybinds("Keybinds", Class442.field3),
   combat("Combat", Class442.field0),
   player("Player", Class442.field0),
   other("Other", Class442.field3);

   public Class442 u_() {
      return this.field18;
   }

   private SubCategory(String var3, Class442 var4) {
      this.name = var3;
      this.field18 = var4;
   }

   public String method0() {
      return this.name;
   }
}
