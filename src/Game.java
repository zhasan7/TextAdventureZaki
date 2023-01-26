public class Game {
    private Room currentRoom;
    private Parser parser;
    private Player player;

    public Game() {
        parser = new Parser();
        player = new Player();
    }
        public static void main(String[] args) {
            Game game = new Game();
            game.createRooms();
            game.play();
        }

        private void createRooms(){

                Room secondBuilding = new Room("You wake up up a bishop hallway. You enter the religion classroom and are asked a question by your teacher Mr.Egan. If answered correctly you get a key to help you enter the main office and escape. He asks you, who betrayed Jesus?", "You wake up in a dark hallway. You stand up looking for a light switch." +
                        " You find one and turn it on. The hallway you seem to be in is from your school, Bishop Montgomery, the second building to be exact. You remember your religion teacher is close. You go into your religion classroom and see Mr. Egan waiting at his desk for you." +
                        "He says to leave this school you must get the three keys from your math teacher, religion teacher, and spanish teacher and take them to the main office. He asks you who betrayed Jesus?");
                Room firstBuilding = new Room("You enter first building and find Ms. Fabbri waiting for you. She says to obtain her key you must answer this spanish question, how do you say car in spanish?", "You enter first building. You remember this building was where your spanish classroom was. You wonder if your teacher has a key. You enter" +
                        "the classroom and see Ms. Fabbri waiting for you. She says to obtain her key you must answer this spanish question, how do you say car in spanish? ");
                Room thirdBuilding = new Room("You enter third building and find your math teacher Mr, Nakashima waiting for you.  He says to obtain his key and a flashlight you must answer this math question correctly. He asks you what is 1250 + 1150 / 10?", "You enter third building. Here you find your old math classroom. You enter and find Mr.Nakashima waiting for you. He says to obtain his key you must answer this math" +
                        "question correctly. He asks you what is 1250 + 1150 / 10?");
                Room gym = new Room("You enter the dark gym and find nothing besides a key to the field on the ground.", "You enter the dark gym. You find a light switch and flick it. Inside you find a key to the field.");
                Room mainOffice = new Room("You enter by heading west from first building. You find a spanish secretary asking you for the question the spanish teacher asked you.", "You finally make it to the main office. You see the secretary sitting at the desk. She tells you this isn't the real exit, the exit is at the church. You must answer these  ");
                Room field = new Room("You enter the field and find it is flooded and turned all fo the grass too mud. You struggle to get across the field but drown in the thick mud.", " You enter the field and see a bright light on the other side of the field. You begin to run to the otherside and find that the grass is extremely muddy. You struggle to keep walking until you entire body is consumed by the thick mud. You drown in the mud and die.");
                Room church = new Room("You enter the church and find Jesus Christ waiting for you. He decides to save you from this hell and flies you away from the school.", "You enter the church and see a man waiting for you at the alter. he tells you he is Jesus and he is here to sae you from this god forsaken school. He takes your hand and lifts you away and away.");


                firstBuilding.setExit("west", mainOffice);
                firstBuilding.setExit("east", secondBuilding);
                secondBuilding.setExit("west", firstBuilding);
                secondBuilding.setExit("east", thirdBuilding);
                thirdBuilding.setExit("north", gym);
                thirdBuilding.setExit("west", secondBuilding);
                gym.setExit("north", field);
                gym.setExit("west", thirdBuilding);
                mainOffice.setExit("north", church);
                mainOffice.setExit("east", firstBuilding);

                Item obj = new Item();
                Item obj2 = new Item();

                player.setItem("one", obj);
                secondBuilding.setItem("two", obj2);

                currentRoom = secondBuilding;

        }

        public void play() {
            printWelcome();


            boolean finished = false;
            while(!finished) {
                Command command = parser.getCommand();
                //System.out.println(command);
                finished = processCommand(command);

            }
            System.out.println(("Thanks for playing"));
        }

        private boolean processCommand(Command command){
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord){
            case UNKNOWN:
                System.out.println("I don't know how to do that");
                break;
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                look(command);
                break;
            case DROP:
                drop(command);
                break;
            case GRAB:
                grab(command);
                break;
        }
        return wantToQuit;
    }
    private void printHelp(){
        System.out.println("You are lost. You are so lost.");
        System.out.println("You are in a long dark hallway");
        System.out.println();
        System.out.println("Your command words are: ");
        parser.showCommands();
    }
    private void look(Command command){
        if(command.hasSecondWord()){
            System.out.println("You can't look at " + command.getSecondWord());
            return;
        }

        System.out.println(currentRoom.getLongDescription());
        System.out.println(player.getItemString());
    }

    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()){
            // if there is no second word, we don't know where to go
            System.out.println("Go Where?");
            return;
        }

        String direction = command.getSecondWord();

        //try to leave current room
        Room nextRoom = currentRoom.getExit(direction);

        if(nextRoom == null){
            System.out.println("Can't go this way!");
        }
        else{
            currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());

        }
    }

    private void grab(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go
            System.out.println("Grab what?");
            return;
        }
        String item = command.getSecondWord();

        Item nextItem = currentRoom.getItem(item);

        if(nextItem == null){
            System.out.println("You can't grab " +item);
        }
        else{
            player.setItem(item, nextItem);
            System.out.println("You grabbed " + item);

        }

    }
    private void drop(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go
            System.out.println("Drop what?");
            return;
        }
        String item = command.getSecondWord();

        Item dropItem = player.getItem(item);

        if(dropItem == null){
            System.out.println("You can't drop " +item);
        }
        else{
            currentRoom.setItem(item, dropItem);
            System.out.println("You dropped " + item);

        }

    }


    private boolean quit (Command command) {
        if(command.hasSecondWord()) {
            System.out.println("You can't quit" + command.getSecondWord());
            return false;
        }
        else{
            return true;
        }
    }

        private void printWelcome() {
            System.out.println();
            System.out.println("Welcome to my text adventure game!");
            System.out.println("You find yourself locked in your highschool Bishop Montgomery!");
            System.out.println("Type \\‘help\\\" if you need assistance");
            System.out.println();
            System.out.println("Your commands are go, quit, help, look, drop, grab");
        }

}

