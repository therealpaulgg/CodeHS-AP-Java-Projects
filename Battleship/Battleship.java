public class Battleship extends ConsoleProgram
{
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    private int row;
    private int col;
    private int pos;
    private Player user = new Player();
    private Player computer = new Player();
    private boolean playAgain;
    private boolean playerWins = false;

    public void run()
    {
      startGame();
      while(true)
      {
          while(true)
          {
            playerTurn();
            computerTurn();
          }
          /* if(playerWins == true)
          {
              System.out.println("Congratulations!!! You won!!!");
          }
          else
          {
              System.out.println("You lost. :(");
          }
          while(true)
          {
              String playagain = readLine("Play again? (yes/no) ");
              if(playagain.equals("yes"))
                {
                    System.out.println("Playing again...");
                    playAgain = true;
                    break;
                }
              else if(playagain.equals("no"))
              {
                System.out.println("Thanks for playing!");
                playAgain = false;
                break;
              }
              else
              {
              System.out.println("You didn't put in a right answer.");
              }
          }
          if(playAgain == false)
          {
              break;
          } */
      }
    }

  public void playerTurn()
  {
      int x;
      int y;

    //mark position as guessed, and return as hit or not.
    while(true)
    {
      x = readInt("Enter x coordinate to guess.");
      y = readInt("Enter y coordinate to guess.");
      if(user.alreadyGuessed(x, y) == true)
      {
        System.out.println("Stupid, you already guessed there. Guess again.");
      }
      else
      {
        break;
      }
    }
    if(computer.hasShipAI(x, y) == true)
    {
      user.markHitPlayer(x, y);
      System.out.println("You hit an enemy ship!");
      user.printMyGuesses();
    }
    else
    {
      user.markMissPlayer(x, y);
      System.out.println("You missed!");
      user.printMyGuesses();
    }
    //if( == 17)

        // check to see if the position has been guessed or not, if it has, then tell to redo.
    // check to see if enemy ships have been all destroyed or not. set playerWins to true.
  }

  public void computerTurn()
  {
    int x = Randomizer.nextInt(0, 9);
    int y = Randomizer.nextInt(0, 9);
    if(user.hasShip(x, y) == true)
    {
      computer.markHitAI(x, y);
      System.out.println("The enemy missed you.");
      computer.printOpponentGuesses();
    }
    else
    {
      computer.markMissAI(x, y);
      System.out.println("The enemy missed you.");
      computer.printOpponentGuesses();
    }
    //mark position as guessed, and return as hit or not.
        // check to see if the position has been guessed or not, if it has, then tell to redo.
    //check to see if player ships have all been destroyed or not. set playerWins to false.

  }

  public void startGame()
  {
      System.out.println("Welcome to Battleship!");
      System.out.println("Let's set up your ships. Ship sizes are in order: 2, 3, 3, 4, 5!");
      user.printMyShips();
      for(int i = 0; i < user.getShips().length; i++)
      {
      //INITIALIZING X VALUE!!!!!
        while(true)
        {
        while(true)
      {
          row = (readInt("Enter x coordinate for ship: " + user.getShipNames()[i] + ": ")) - 1;
          if(row == 0 || row == 1 || row == 2 || row == 3 || row == 4 || row == 5 || row == 6 || row == 7 || row == 8 || row == 9)
          {
              break;
          }
          else
          {
              System.out.println("That didn't work. Try again.");
          }
      }
          while(true)
      {
          col = (readInt("Enter y coordinate for ship: " + user.getShipNames()[i] + ": ")) - 1;
          if(col == 0 || col == 1 || col == 2 || col == 3 || col == 4 || col == 5 || col == 6 || col == 7 || col == 8 || col == 9)
          {
              break;
          }
          else
          {
              System.out.println("That didn't work. Try again.");
          }
      }
          while(true)
      {
        pos = readInt("Enter position for ship: " + user.getShipNames()[i] + "(HORIZONTAL/VERTICAL): ");
        if(pos == HORIZONTAL || pos == VERTICAL)
        {
          break;
        }
        else
        {
          System.out.println("That didn't work. Try again.");
        }
      }
          if(pos == HORIZONTAL)
          {
            if((SHIP_LENGTHS[i] + col) - 1 < 10)
            {
                boolean toBreak = true;
                for(int j = col; j < (SHIP_LENGTHS[i] + col); j++)
                {
                  if(user.hasShip(row, j) == true)
                  {
                    toBreak = false;
                  }
                }
                if(toBreak == true)
                {
                    break;
                }
                else
                {
                    System.out.println("There's a ship here already! Try again.");
                }
            }
            else
            {
                System.out.println("You can't put a ship there - it goes off the x axis.");
            }
          }
          else
          {
              if((SHIP_LENGTHS[i] + row) - 1 < 10)
              {
                  boolean toBreak = true;
                  for(int j = row; j < (SHIP_LENGTHS[i] + row); j++)
                  {
                    if(user.hasShip(j, col) == true)
                    {
                        toBreak = false;
                    }
                  }
                  if(toBreak == true)
                {
                    break;
                }
                else
                {
                    System.out.println("There's a ship here already! Try again.");
                }
              }
              else
              {
                  System.out.println("You can't put a ship there - it goes off the y axis.");
              }
          }
        }
      user.getShips()[i].setLocation(row, col);
      user.getShips()[i].setDirection(pos);
      user.choosePlayerShip(user.getShips()[i]);
      user.printMyShips();
      //chooseShipLocation(SHIPS[i], row, col, pos);
    }
    System.out.println("Setting up computer ships...");

      //initializeComputer();
      for(int i = 0; i < user.getShips().length; i++)
    {
      row = Randomizer.nextInt(1, 9);
      col = Randomizer.nextInt(1, 9);
      pos = Randomizer.nextInt(0, 1);
      computer.getShips()[i].setLocation(row, col);
      computer.getShips()[i].setDirection(pos);
      computer.chooseAIShip(user.getShips()[i]);
    }

      System.out.println("Done. Prepare to battle!");
  }
}
