import java.util.Scanner;
import java.lang.Math;
/**
* The Game Class serves as the main controller for the Animal Tracking Game.
* It manages player creation, trail selection, turn execution for each
* player one by one. One player is Human, Another is Computer.
* Players move along a trail, encounter features that may or may not have
* a penalty, spot animals randomly for points, and game continues until one
* player reaches the finish line or human quits.
* Winner is only declared when a player reaches finish line.
*
* @author Rushil
* @version 1.0
*/
public class Game
{

    private Trail natureTrail;
    private Player player1;
    private Player player2;

    private static final Animal[] ANIMALS = {
        new Animal("Koala", 10),
        new Animal("Emu", 7),
        new Animal("Wombat", 5),
        new Animal("Kangaroo", 2),
        new Animal("Rabbit", -5),
    };

    private static final String DIVIDER = "=".repeat(80);
    private static final String PADDING = " ".repeat(20);

    /**
    * Default Constructor - initializes the game with default trail and players
    */
    public Game()
    {
        natureTrail = new Trail();
        this.player1 = new Player();
        this.player2 = new Player("Computer");
    }

    /**
    * Adjust a player's score by specific points.
    *
    * @param player The players whose score is to be adjusted
    * @param points The number of points to add/subtract
    */
    private void adjustPlayerScore(Player player, int points)
    {
        player.updateScore(points);
    }

    /**
    * Check if the player sights an animal at their current position,
    * and adjusts the score accordingly.
    * 
    * @param player The player for whom check is to be performed
    * @param message StringBuilder to append all messages on player's current turn
    */
    private void checkAnimalSighting(Player player, StringBuilder message)
    {
        int position = player.getPosition();
        int end = natureTrail.getLength() - 1;

        if (position == 0 || position == end) return; // no animals can be sighted here, even on coming there with space penalty

        boolean animalSighted = Math.random() < 0.5;

        if (animalSighted)
        {   
            // between 0 to length(exclusive) and then floor with int type cast
            int index = (int)(Math.random() * ANIMALS.length);
            Animal animal = ANIMALS[index];
            String verb = animal.getPoints() > 0 ? "gained " : "lost ";
            message.append("\n" + player.getName()).append(" spotted a ")
            .append(animal.getName()).append(" and ").append(verb +
            Math.abs(animal.getPoints())).append(" points!");
            
            adjustPlayerScore(player, animal.getPoints());
        }

    }

    /**
    * Clears the console screen
    */
    private void clearScreen()
    {
        try
        {
            // https://www.reddit.com/r/java/comments/1uuvqo/clear_terminal_screen_linux/?rdt=42217
            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }
        catch(Exception e)
        {
            // might return error on windows or other platforms
            // platform specific code to clear screen
            System.out.println(e.getMessage());
        }
    }

    /**
    * Determines and announces the winner based on scores.
    *
    * @param exitMessageBuilder StringBuilder to append winner message.
    * Messages are printed along with the game board with the help of
    * shared string builder.
    */
    private void declareWinner(StringBuilder exitMessageBuilder)
    {
        if (player1.getScore() == player2.getScore())
        {
            exitMessageBuilder.append("\nIt's a draw between " + player1.getName() + " and " + player2.getName());
        }
        else if (player1.getScore() > player2.getScore())
        {
            exitMessageBuilder.append("\n🏆" + player1.getName() + " won the game with highest points!");
        } 
        else
        {
            exitMessageBuilder.append("\n🏆" + player2.getName() + " won the game with highest points!");
        }
    }

    /**
    * Displays the complete game board with score board, trails,
    * and messages of last executed turn.
    *
    * @param message Message to display below the board.
    */
    private void displayGameBoard(String message)
    {
        clearScreen();
        displayScoreBoard();
        displayTrail();

        if (message != null && !message.isEmpty())
        {
            System.out.println("\n" + message);
            System.out.println(DIVIDER);
        }
    }

    /**
    * Displays the current score of both players.
    */
    private void displayScoreBoard()
    {
        System.out.println(PADDING + "SCORE BOARD" + PADDING);
        System.out.println(DIVIDER);
        System.out.printf("%-20s: %5d points\n", player1.getName(), player1.getScore());
        System.out.printf("%-20s: %5d points\n", player2.getName(), player2.getScore());
        System.out.println(DIVIDER);
    }

    /**
    * Displays the game trail with current player positions.
    */
    private void displayTrail()
    {
        System.out.println("\n" + PADDING + "Game Trail" + PADDING);
        System.out.println(DIVIDER);
        System.out.print("TRAIL: ");
        int trailLength = natureTrail.getLength();
        for (int i = 0; i < trailLength; i++)
        {
            String marker;
            boolean isHumanHere = player1.getPosition() == i;
            boolean isComputerHere = player2.getPosition() == i;

            String humanInitial = player1.getName().substring(0,1).toUpperCase();
            if (humanInitial.equals("C"))
            {
                // to handle conflict with computer initial C
                humanInitial = "H";
            }

            if (isHumanHere && isComputerHere)
            {
                marker = humanInitial + "C";
            }
            else if (isHumanHere)
            {
                marker = humanInitial + " ";
            } 
            else if (isComputerHere)
            {
                marker = "C ";
            }
            else if (i == 0)
            {
                marker = "S ";
            }
            else if (i == trailLength - 1)
            {
                marker = "F ";
            }
            else
            {
                marker = "_ ";
                 //marker = natureTrail.getFeatureAt(i).getFeatureType()+" ";
            }
            System.out.printf("%-5s", marker);
        }
        System.out.println();
    }

    /**
    * Displays the game's welcome message with instructions.
    */
    private void displayWelcomeMessage()
    {
        clearScreen();
        System.out.println(DIVIDER);
        System.out.println(PADDING + "Welcome to the Animal Tracking Game" + PADDING);
        System.out.println(DIVIDER);
        System.out.println("Gain points by spotting animals along your trail");
        System.out.println("First one to finish gets 10 bonus points. Highest Score Wins.");
        System.out.println(DIVIDER);
    }

    /**
    * Exits the Game, displays final scores, and declares the winner.
    */
    private void exitGame()
    {
        StringBuilder exitMessageBuilder = new StringBuilder();
        exitMessageBuilder.append("\n" + PADDING + "GAME FINISHED" + PADDING + "\n");
        exitMessageBuilder.append("\n" + DIVIDER + "\n");

        // player to reach finish first, gets 10 bonus points
        if (player1.getPosition() == natureTrail.getLength() - 1)
        {
            exitMessageBuilder.append(player1.getName() + " reached the finish line and gained "
            + "10 bonus points.");
            adjustPlayerScore(player1, 10);
            declareWinner(exitMessageBuilder);
        }
        else if (player2.getPosition() == natureTrail.getLength() - 1)
        {
            exitMessageBuilder.append(player2.getName() + " reached the finish line and gained "
            + "10 bonus points.");
            adjustPlayerScore(player2, 10);
            declareWinner(exitMessageBuilder);
        }
        else
        {
            // no winner is declared if game is ended by human interruption or error
            exitMessageBuilder.append("==========GAME ENDED IN BETWEEN===============");
        }

        displayGameBoard(exitMessageBuilder.toString());
        System.exit(0);
    }

    /**
    * Gets the nature trail of the Game.
    *
    * @return The game's trail.
    */
    public Trail getNatureTrail()
    {
        return natureTrail;
    }

    /**
    * Gets player 1.
    *
    * @return The player 1 from the game (human)
    */
    public Player getPlayer1()
    {
        return player1;
    }

    /**
    * Gets player 2.
    *
    * @return The player 2 from the game (computer)
    */
    public Player getPlayer2()
    {
        return player2;
    }

    /**
    * Handles the effect of landing on nature feature.
    * Checks if the player landed on a feature and moves them,
    * according to the penalty.
    * 
    * @param player player Player who just landed on a position on trail
    * @param message StringBuilder to append messages about this feature's impact.
    * Message is displayed with the GameBoad with other events that may have occured
    * during the turn.
    */
    private void handleNatureFeature(Player player, StringBuilder message)
    {
        int currentPosition = player.getPosition();
        NatureFeature feature = this.natureTrail.getFeatureAt(currentPosition);
        if (feature != NatureFeature.NONE)
        {       
            int spacePenalty = feature.getSpacePenalty();
            String direction = "";
            if (spacePenalty > 0) direction = "ahead by ";
            else direction = "back by ";
            
            message.append("\n" + player.getName()).append(" lands on a ").append(
            feature.getFeatureName()).append(" and moves ").append(direction + Math.abs(spacePenalty) + " spaces.");
            movePlayer(player, spacePenalty);
        }
    }

    /**
    * Displays a messages when a player reaches the end and waits
    * for user acknowledgement before displaying the final result.
    * This is to make sure user can read the last dice roll turn events.
    */
    private void handlePlayerReachedEnd(Player player)
    {
        System.out.println(player.getName() + " reached the end. Press Enter to see final results.");
        new Scanner(System.in).nextLine();
    }

    /**
    * Moves a player by a specific number of spaces.
    *
    * @param player The player to move
    * @param spaces Number of spaces to move (can be negative)
    */
    private void movePlayer(Player player, int spaces)
    {
        int newPosition = player.getPosition() + spaces;
        int maxPossiblePosition = this.natureTrail.getLength() - 1;

        if (newPosition < 0)
        {
            newPosition = 0;
        }
        else if (newPosition > maxPossiblePosition)
        {
            newPosition = maxPossiblePosition;
        }
        player.setPosition(newPosition);
    }

    /**
    * Executes a player's turn including dice roll, movement, and events.
    *
    * Rolls the dice, moves the player, checks if they sighted an animal,
    * handle space penalty if any based on nature feature they may have landed
    * on, and finally displays the GameBoard with latest score, trail and
    * messages from all the above occured events. for eg. space penalty,
    * dice roll result, animal sighting points, etc.
    *
    * @param player Player whose turn is being executed
    */
    private void playTurn(Player player)
    {   
        StringBuilder message = new StringBuilder();
        message.append("\n").append(player.getName()).append("'s turn...");

        int steps = Dice.roll();
        message.append("\n").append(player.getName()).append(" rolls the dice... and gets: ").append(steps);

        movePlayer(player, steps);
        checkAnimalSighting(player, message);
        handleNatureFeature(player, message);

        displayGameBoard(message.toString());
    }

    /**
    * Sets up a complex trail for the Game.
    *
    * The complex trail is 15 to 30 positions in length, with up to three
    * nature features of each type placed randomly on the trail.
    */
    private void requestComplexTrail()
    {
        char[] layout = Landscape.getComplexTrail();
        this.natureTrail = new Trail(layout);
    }
    
    /**
    * Sets up an easy trail for the Game.
    *
    * The easy trail is 12 to 20 positions in length, with one nature feature
    * of each type placed randomly on the trail.
    */
    private void requestEasyTrail()
    {
        char[] layout = Landscape.getEasyTrail();
        this.natureTrail = new Trail(layout);
    }

    /**
    * Prompts for and validate's the human player's name
    * 
    * Name can be 1-10 chars without any trailing spaces
    */
    private void requestPlayerName()
    {
        Scanner in = new Scanner(System.in);
        String name = "";
        boolean validName = false;

        // keeps asking for name until user enters a valid name
        while (!validName)
        {
            System.out.print("Enter your name (1-10 chars, no"+
            " leading/trailing spaces): ");
            name = in.nextLine().trim(); //discard trailing spaces

            try
            {
                this.player1 = new Player(name);
                validName = true;
            }
            catch (GameError e)
            {
                // Handles the INVALID_NAME Error
                // Prints the error and asks for name again
                System.out.println(e.getMessage());
            }

        }

        this.player2 = new Player("Computer"); //player 2 is always computer
    }

    /**
    * Prompts and validates the user's trail difficulty choice
    * between Easy(E) and Complex(C). Keeps prompting until they choose
    * a valid trail.
    */
    private void requestTrailType()
    {
        Scanner in = new Scanner(System.in);
        String choice;
        boolean validChoice = false;

        do
        {
            System.out.println("Choose trail type (E for easy, C for Complex): ");
            choice = in.nextLine().trim().toUpperCase();

            if (choice.equals("E"))
            {
                requestEasyTrail();
                System.out.println("Easy Trail Selected!");
                validChoice = true;
            }
            else if (choice.equals("C"))
            {
                requestComplexTrail();
                System.out.println("Complex Trail Selected!");
                validChoice = true;
            }
            else
            {
                System.out.println("Invalid Choice. Please enter 'E' or 'C'");
            }
        }
        while (!validChoice);
    }

    /**
    * Main Game Loop that executes the entire game.
    * Game continues until one player reaches the end of trail
    * or human quits.
    * Each player gets one turn a time.
    */
    private void runGame()
    {
        displayWelcomeMessage();
        requestPlayerName();
        requestTrailType();

        int endPositionIndex = this.natureTrail.getLength() - 1;
        System.out.println("Trail Length: " + natureTrail.getLength());

        displayGameBoard("Game Starting! Press Enter to Begin...");

        boolean gameOver = false;
        while (!gameOver)
        {           
            waitForEnter();
            playTurn(player1);
            if (player1.getPosition() == endPositionIndex)
            {
                handlePlayerReachedEnd(player1);
                gameOver = true;
                break;
            }

            waitForEnter();
            playTurn(player2);
            if (player2.getPosition() == endPositionIndex)
            {
                handlePlayerReachedEnd(player2);
                gameOver = true;
            }
        }
        if (gameOver)
        {
            exitGame();
        }
        else
        {
            System.out.println("Rushil is a bad coder. Please restart the Game :(");
        }
    }

    /**
    * Entry point through which a game is started.
    *
    * @param args Command-Line Arguments
    */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.runGame();
    }

    /**
    * Sets the nature trail.
    *
    * @param trail The new trail to use if any
    */
    public void setNatureTrail(Trail trail)
    {
        this.natureTrail = trail;
    }

    /**
    * Sets player one.
    *
    * @param player The new player one
    */
    public void setPlayer1(Player player)
    {
        this.player1 = player;
    }

    /**
    * Sets player two.
    *
    * @param player The new player two
    */
    public void setPlayer2(Player player)
    {
        this.player2 = player;
    }

    /**
    * Pauses the game until user press enter or quits.
    */
    private void waitForEnter()
    {
        System.out.println("\nPress Enter to Play a Turn or Press Q to Quit...");
        String input = new Scanner(System.in).nextLine();
        if (input.equalsIgnoreCase("q"))
        {
            exitGame();
        }
    }

}

