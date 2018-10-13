public class Grid
{
  public static final int NUM_ROWS = 10;
  public static final int NUM_COLS = 10;

  private Location[][] grid = new Location[NUM_ROWS][NUM_COLS];

  // Create a new Grid. Initialize each Location in the grid
  // to be a new Location object.
  public Grid()
  {
    for(int row = 0; row < NUM_ROWS; row++)
    {
      for(int col = 0; col < NUM_COLS; col++)
      {
        grid[row][col] = new Location();
      }
    }
  }

  // Mark a hit in this location by calling the markHit method
  // on the Location object.
  public void markHit(int row, int col)
  {
    grid[row][col].markHit();
  }
  // Mark a miss on this location.
  public void markMiss(int row, int col)
  {
    grid[row][col].markMiss();
  }

  // Set the status of this location object.
  public void setStatus(int row, int col, int status)
  {
    grid[row][col].setStatus(status);
  }

  // Get the status of this location in the grid
  public int getStatus(int row, int col)
  {
    return grid[row][col].getStatus();
  }
  // Return whether or not this Location has already been guessed.
  public boolean alreadyGuessed(int row, int col)
  {
    return !grid[row][col].isUnguessed();
  }


  // Set whether or not there is a ship at this location to the val
  public void setShip(int row, int col, boolean val)
  {
    grid[row][col].setShip(val);
  }

  // Return whether or not there is a ship here
  public boolean hasShip(int row, int col)
  {
    return grid[row][col].hasShip();
  }


  // Get the Location object at this row and column position
  public Location get(int row, int col)
  {
    return grid[row][col];
  }

  // Return the number of rows in the Grid
  public int numRows()
  {
    return NUM_ROWS;
  }

  // Return the number of columns in the grid
  public int numCols()
  {
    return NUM_COLS;
  }


  // Print the Grid status including a header at the top
  // that shows the columns 1-10 as well as letters across
  // the side for A-J
  // If there is no guess print a -
  // If it was a miss print a O
  // If it was a hit, print an X
  // A sample print out would look something like this:
  //
  //   1 2 3 4 5 6 7 8 9 10
  // A - - - - - - - - - -
  // B - - - - - - - - - -
  // C - - - O - - - - - -
  // D - O - - - - - - - -
  // E - X - - - - - - - -
  // F - X - - - - - - - -
  // G - X - - - - - - - -
  // H - O - - - - - - - -
  // I - - - - - - - - - -
  // J - - - - - - - - - -
  public void printStatus()
  {
    String[] numbers = {" ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10"};
    String[] letters = {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};
    for(int x = 0; x <= NUM_ROWS; x++)
    {
      System.out.print(numbers[x]);
    }
    System.out.println();
    for(int x = 0; x < NUM_ROWS; x++)
    {
      System.out.print(letters[x]);
      for(int y = 0; y < NUM_COLS; y++)
      {
        if(grid[x][y].getStatus() == 2)
        {
          System.out.print("O ");
        }
        else if(grid[x][y].getStatus() == 1)
        {
          System.out.print("X ");
        }
        else
        {
          System.out.print("- ");
        }
      }
      System.out.println();
    }
  }

  // Print the grid and whether there is a ship at each location.
  // If there is no ship, you will print a - and if there is a
  // ship you will print a X. You can find out if there was a ship
  // by calling the hasShip method.
  //
  //   1 2 3 4 5 6 7 8 9 10
  // A - - - - - - - - - -
  // B - X - - - - - - - -
  // C - X - - - - - - - -
  // D - - - - - - - - - -
  // E X X X - - - - - - -
  // F - - - - - - - - - -
  // G - - - - - - - - - -
  // H - - - X X X X - X -
  // I - - - - - - - - X -
  // J - - - - - - - - X -
  public void printShips()
  {
    String[] numbers = {" ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 ", "10"};
    String[] letters = {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};
    for(int x = 0; x <= NUM_ROWS; x++)
    {
      System.out.print(numbers[x]);
    }
    System.out.println();
    for(int x = 0; x < NUM_ROWS; x++)
    {
      System.out.print(letters[x]);
      for(int y = 0; y < NUM_COLS; y++)
      {
        if(grid[x][y].hasShip() == true)
        {
          System.out.print("X ");
        }
        else
        {
          System.out.print("- ");
        }
      }
      System.out.println();
    }
  }

  public void addShip(Ship s)
  {
    if(s.getDirection() == 0)
    {
        for(int i = s.getCol(); i < (s.getCol() + s.getLength()); i++)
        {
          setShip(s.getRow(), i, true);
        }
    }
    else if(s.getDirection() == 1)
    {
        for(int i = s.getRow(); i < (s.getRow() + s.getLength()); i++)
        {
          setShip(i, s.getCol(), true);
        }
    }
  }
}
