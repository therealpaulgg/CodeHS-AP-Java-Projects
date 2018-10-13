public class Player
{
  private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
  // make a list of 5 ships. 1 2, 2 3, 1 4, 1 5.
  Ship destroyer = new Ship(SHIP_LENGTHS[0]);
  Ship cruiser = new Ship(SHIP_LENGTHS[1]);
  Ship submarine = new Ship(SHIP_LENGTHS[2]);
  Ship battleship = new Ship(SHIP_LENGTHS[3]);
  Ship aircraftCarrier = new Ship(SHIP_LENGTHS[4]);
  public Ship[] SHIPS = {destroyer, cruiser, submarine, battleship, aircraftCarrier};
  public String[] SHIPS_STRING = {"destroyer", "cruiser", "submarine", "battleship", "aircraftCarrier"};

  //player and AI need to have two grids each.
  //the first player grid marks locations of their ship
  private Grid playerPlayer = new Grid();
  //the second player grid marks where AI have guessed locations.
  private Grid playerAI = new Grid();
  // the first AI grid marks AI ships.
  private Grid aiAI = new Grid();
  //the second AI grid shows where player has fired.
  private Grid aiPlayer = new Grid();

  //constructor
  public Player() {}
  //prints playerPlayer grid.

  public Ship[] getShips()
  {
      return SHIPS;
  }

  public String[] getShipNames()
  {
      return SHIPS_STRING;
  }

  public void printMyShips()
  {
    playerPlayer.printShips();
  }
  //prints playerAI grid
  public void printMyGuesses()
  {
    aiPlayer.printStatus();
  }
  //prints aiPlayer grid.
  public void printOpponentGuesses()
  {
    playerAI.printStatus();
  }
  public void recordOpponentGuess(int row, int col)
  {
    if(playerPlayer.hasShip(row, col) == true)
    {
      playerAI.markHit(row, col);
    }
    else
    {
      playerAI.markMiss(row, col);
    }
  }
  //admin method that sets a ship location
  public void chooseShipLocation(Ship s, int row, int col, int pos)
  {
    s.setLocation(row, col);
    s.setDirection(pos);
    playerPlayer.addShip(s);
  }

  public void choosePlayerShip(Ship s)
  {
    playerPlayer.addShip(s);
  }

  public void chooseAIShip(Ship s)
  {
      aiAI.addShip(s);
  }

  public void markHitPlayer(int row, int col)
  {
    aiPlayer.markHit(row, col);
  }

  public void markHitAI(int row, int col)
  {
    playerAI.markHit(row, col);
  }

  public void markMissPlayer(int row, int col)
  {
    aiPlayer.markMiss(row, col);
  }

  public void markMissAI(int row, int col)
  {
    playerAI.markMiss(row, col);
  }

  public boolean hasShip(int row, int col)
  {
      return playerPlayer.hasShip(row, col);
  }

  public boolean hasShipAI(int row, int col)
  {
    return aiAI.hasShip(row, col);
  }

  public boolean alreadyGuessed(int row, int col)
  {
    return playerPlayer.alreadyGuessed(row, col);
  }

  public boolean alreadyGuessedAI(int row, int col)
  {
    return aiAI.alreadyGuessed(row, col);
  }
}

/*  s.setLocation(row, col);
  s.setDirection(pos);
}
}

if(s.getDirection() == 0)
{
    for(int i = col; i < (col + s.getLength()); i++)
    {
      s.setShip(row, i, true);
    }
}
else if(s.getDirection() == 1)
{
    for(int i = row; i < (row + s.getLength()); i++)
    {
      s.setShip(i, col, true);
    }
}
*/
