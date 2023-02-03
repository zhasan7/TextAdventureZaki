public class Game {
    private Room currentRoom;
    private Parser parser;
    private Player player;
    private Room secondBuilding;
    private Room firstBuilding;
    private Room thirdBuilding;
    private Room gym;
    private Room mainOffice;
    private Room field;
    private Room church;
    boolean wantToQuit;
    private Item key;




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

                 secondBuilding = new Room("You enter the religion classroom and meet your religion teacher Mr.Egan who says you must get three objects from your math teacher, religion teacher, and spanish teacher that will help you escape. You see the bible, the first object. You can go east or west from here.", "You wake up in a dark hallway. You stand up looking for a light switch." +
                        " You find one and turn it on. The hallway you seem to be in is from your school, Bishop Montgomery, the second building to be exact. You remember your religion teacher is close. You go into your religion classroom and see Mr. Egan waiting at his desk for you." +
                        "He says to leave this school you must get three objects from your math teacher, religion teacher, and spanish teacher that will help you escape. He gives you the bible, the first object. You can go east or west from here.");
                 firstBuilding = new Room("You enter first building and find Ms. Fabbri waiting for you. She says getting to the church is the key. You see a flashlight on the table. You can go east or west from here.", "You enter first building. You remember this building was where your spanish classroom was." +
                        "You enterthe classroom and see Ms. Fabbri waiting for you. She says getting to the church is the key. You see a flashlight on the table. You can go east or west from here. ");
                 thirdBuilding = new Room("You enter third building and find your math teacher Mr. Nakashima waiting for you. He says you must escape using the items you have gathered. He offers you a calculator and a ruler. He says one will help you escape one will kill you if touched. Which do you pick. ", "You enter third building. Here you find your old math classroom. You enter and find Mr.Nakashima waiting for you." +
                         " He says you must escape using the items you have gathered. He offers you a calculator and a ruler. He says one will help you escape one will kill you if touched. Which do you pick. ");
                 gym = new Room("You enter the dark gym and find nothing  it smells like grass however, maybe theirs a field nearby. You can go north or west from here", "You enter the dark gym. You find nothing. You smell some grass however. Maybe their is a field nearby. You can go north or west from here");
                 mainOffice = new Room("You enter the main office. You find a the secratary saying the only way to escape is to craft a key out of the objects you obtained. Once you have found the bible, calculator, and flashlight, try crafting a key to get to the church.", "You finally make it to the main office. You see the secretary sitting at the desk. She tells you to craft a key out of the objects you have obtained. ");
                 field = new Room("You enter the field and find it is flooded and turned all of the grass too mud. You struggle to get across the field but drown in the thick mud.", " You enter the field and see a bright light on the other side of the field. You begin to run to the otherside and find that the grass is extremely muddy. You struggle to keep walking until you entire body is consumed by the thick mud. You drown in the mud and die.");
                 church = new Room("You enter the church and find Jesus Christ waiting for you. He decides to save you from this hell and flies you away from the school.", "You enter the church and see a man waiting for you at the alter. he tells you he is Jesus and he is here to sae you from this god forsaken school. He takes your hand and lifts you away and away.");

                firstBuilding.setExit("west", mainOffice);
                firstBuilding.setExit("east", secondBuilding);
                thirdBuilding.setExit("west", secondBuilding);
                gym.setExit("north", field);
                gym.setExit("west", thirdBuilding);
                mainOffice.setExit("east", firstBuilding);

                Item obj = new Item();
                Item obj2 = new Item();
                Item flashlight = new Item();
                Item calculator = new Item();
                Item ruler = new Item();
                Item bible = new Item();


                firstBuilding.setItem("flashlight", flashlight);
                thirdBuilding.setItem("calculator", calculator);
                thirdBuilding.setItem("ruler", ruler);
                secondBuilding.setItem("bible", bible);

                //player.setItem("one", obj);
               // secondBuilding.setItem("two", obj2);

                currentRoom = secondBuilding;

        }

        public void play() {
            printWelcome();


            boolean finished = false;
            while(!finished) {
                Command command = parser.getCommand();
                //System.out.println("finished" + finished);

                finished = processCommand(command);

            }
            System.out.println(("Thanks for playing"));
        }

        private boolean processCommand(Command command){
        wantToQuit = false;

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
            case USE:
                use(command);
                break;
            case CRAFT:
                craft(command);
                break;
        }
             //System.out.println("wantToQuit" + wantToQuit);
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
        //System.out.println(nextRoom);

        // there is a problem with this structure, we should probably talk about it in depth
        // the issue is that your nextRoom is null, because they haven't "found" the items yet
        // with a null Room, the first if check is fine
        // but in subsequent if checks you are calling methods on a null object, that doesn't work
        // you are checking if(null.equals(field)), and that will break the program because null is nothing
        // java doesn't know what class to use for the equals method.

        if(nextRoom == null){
                 if(nextRoom.equals(gym)){
                        System.out.println("Go find a flashlight to enter the gym");
                     return;
                    }
            System.out.println("You can't go this way");}
        else if(nextRoom.equals(field)) {
            System.out.println(currentRoom.getLongDescription());
            wantToQuit = true;

        }
        else if(nextRoom.equals(church)) {
            System.out.println(currentRoom.getLongDescription());
            wantToQuit = true;
        }
        else{
            currentRoom = nextRoom;
            System.out.println(currentRoom.getShortDescription());
            return;
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
        if(player.getInventory().containsKey("flashlight")){
            thirdBuilding.setExit("east", gym);
        }
        if(player.getInventory().containsKey("bible")){
            secondBuilding.setExit("west", firstBuilding);
            secondBuilding.setExit("east", thirdBuilding);
            }
        if(player.getInventory().containsKey("ruler")){
            System.out.println("You picked the wrong item. The ruler poisoned you the minute you touched it and killed you");
            wantToQuit = true;
        }
        else if (player.getInventory().containsKey("calculator")){
            System.out.println("You picked the correct item this will help you create the key to escape! You can go east or west from here.");
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
    private void use(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Use what?");
            return;
        }
        String item = command.getSecondWord();
        if (item.equals("flashlight") ){
            System.out.println("lights");
        }

    }
    private void craft(Command command){
        if(!command.hasSecondWord()){
            System.out.println();
        }
        String item = command.getSecondWord();
        if(player.getInventory().containsKey("calculator") && player.getInventory().containsKey("flashlight") && player.getInventory().containsKey("bible")) {
            if (item.equals("key")){
                key = new Item();
                System.out.println("You created the key too the church! try heading north");
                mainOffice.setExit("north", church);

            }
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
            System.out.println("Type \\‘help\\\" if you need assistance");
            System.out.println();
            System.out.println("You find yourself locked in your high school Bishop Montgomery! You must find items and craft them into a key to escape! You start in your religion class, find a bible.");
            System.out.println("Your commands are go, quit, help, look, drop, grab. Try looking around.");
        }

}

