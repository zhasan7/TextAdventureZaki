public class Game {
    private Room currentRoom;
    public Game() {
    }
        public static void main(String[] args) {
            Game game = new Game();
            game.createRooms();
            game.play();
        }

        private void createRooms(){

                Room secondBuilding = new Room("You wake up up a bishop hallway. You enter the religion classroom and are asked a question If answered correctly you can head either east or west");
                Room firstBuilding = new Room("You enter first building and find the spanish teacher asking a basic spanish question. You must answer the question correctly to continue west.");
                Room thirdBuilding = new Room("You enter third building and find your math teacher  wanting to know a basic math question. If you answer correct you can advance east.");
                Room gym = new Room("You enter the dark gym and find nothing besides a key to the field on the ground. If you head north from here you will each the field.");
                Room mainOffice = new Room("You enter by heading west from first building. You find a spanish secretary asking you for the question the spanish teacher asked you.");
                Room field = new Room("You enter the field and find it is flooded and turned all fo the grass too mud. You struggle tog et across the field but drown in the thick mud.");
                Room church = new Room("You enter the church and find Jesus Christ waiting for you. He decides to save you from this hell and flies you away from the school.");

        }

        public void play() {
            printWelcome();


            boolean finished = false;
            while(!finished) {

            }
            System.out.println(("Thanks for playing"));
        }

        private void printWelcome() {
            System.out.println();
            System.out.println("Welcome to my text adventure game!");
            System.out.println("You will find yourself in a garden maze, desperate to escape!");
            System.out.println("Type \\‘help\\\" if you need assistance");
            System.out.println();
            System.out.println("we will print a long room description here");
        }

    }

