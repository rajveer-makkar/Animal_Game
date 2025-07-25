import java.util.Scanner;

public class Game{

    private Trail trail;
    private Player player;
    private Player computer;
    private Dice dice;
    private String animalName;
    static boolean easyFlag;
    static boolean complexFlag;

    public Game(){
        dice = new Dice();
    }
     public void gameSetup(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name(1-10 characters): ");
        String name = scanner.nextLine();
        player = new Player(name);
        computer = new Player("Computer");

        System.out.println("Choose your trail (easy/complex): ");
        String trailChoice = scanner.nextLine().toLowerCase().trim();

         boolean validChoice=false;

         while (!validChoice) {
             System.out.println("Choose your trail (easy/complex): ");

             if (trailChoice.contains("easy")) {
                 trail = new Trail(Landscape.getEasyTrail());
                 easyFlag = true;
                 validChoice = true;
             } else if (trailChoice.contains("complex")) {
                 trail = new Trail(Landscape.getComplexTrail());
                 complexFlag = true;
                 validChoice = true;
             } else {
                 System.out.println("Invalid choice. Please enter 'easy' or 'complex'.");
                 trailChoice = scanner.nextLine().toLowerCase().trim();
             }
         }
    }

    public void startGame() {
       boolean gameOver = false;
       while (!gameOver){
           nextTurn(player);
           if (player.getPosition() >= trail.length()-1){
               gameOver=true;
               player.calculateScore(10);
               break;
           }
           nextTurn(computer);
           if (computer.getPosition() >= trail.length()-1) {
               gameOver = true;
               computer.calculateScore(10);
               break;
           }
           trail.display(player.getPosition(), computer.getPosition());
       }
       displayResults();
    }

    public void nextTurn(Player player){
        System.out.println(player.getName()+"'s turn.");
        int diceRoll =dice.roll();
        System.out.println(player.getName()+" rolled: "+ diceRoll);

        int newPos = player.getPosition()+diceRoll;
        if (newPos >= trail.length()){
            newPos = trail.length()-1;
        }

         NatureFeature feature = trail.getFeatureAt(newPos);
        if (feature!= null) {
            System.out.println("Landed on: "+ feature.getName()+ "\nand got a space penalty of: "+feature.getPenalty());
            newPos=newPos+ feature.getPenalty();
        }

        if (newPos < 0) newPos=0;
        if (newPos >= trail.length()) newPos = trail.length() - 1;
        player.setPosition(newPos);

        if (checkAnimalSighting()){
            int points = animalSightedPoints();
            System.out.println("Animal sighted! "+animalName +"\t"+ points +"points");
            player.calculateScore(points);
        }
    }
    public int animalSightedPoints(){
        int animalRoll = dice.whichAnimal();
        // 1 for koala, 2 for emu, 3 for wombat, 4 for kangaroo, 5 for rabbit
        int points=0;
        switch (animalRoll){
            case 1:
                animalName = "Koala";
                points = 10;
                break;
            case 2:
                animalName = "Emu";
                points =7;
                break;
            case 3:
                animalName="Wombat";
                points =5;
                break;
            case 4:
                animalName="Kangaroo";
                points = 2;
                break;
            case 5:
                animalName="Rabbit";
                points=-2;
                break;
        }
        return points;
    }
    public boolean checkAnimalSighting(){
        return dice.generateAnimal();
    }
    public void displayResults(){
        System.out.println("Game Over!");
        System.out.println(player.getName()+": "+ player.getScore());
        System.out.println(computer.getName()+": "+ computer.getScore());

        if (player.getScore() > computer.getScore()) {
            System.out.println(player.getName() + " wins! with a score of "+player.getScore());
        } else if (computer.getScore() > player.getScore()) {
            System.out.println("Computer wins! with a score of "+computer.getScore());
        } else {
            System.out.println("It's a tie!");
        }
    }
    public static void main(String[] args){
        Game game = new Game();
        game.gameSetup();
        game.startGame();
    }



}



























