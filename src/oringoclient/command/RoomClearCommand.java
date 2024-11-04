package oringo.command;

public class RoomClearCommand extends Command {
   public void method0(String[] var1) {
   }

   public String method1() {
      return "Removes all brush blocks from the room you are standing in";
   }

   public RoomClearCommand() {
      super("roomclear", "clearroom");
   }
}
