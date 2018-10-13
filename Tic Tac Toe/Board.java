public class Board {

	public static final int ROWS = 3;
	public static final int COLS = 3;
	public static int marksSet = 0;
	
	private static Location[][] boards = new Location[ROWS][COLS];
	
	public Board() {
		for(int r = 0; r < ROWS; r++)
		{
			for(int c = 0; c < COLS; c++)
			{
				boards[r][c] = new Location(); // has [0][0], [0][1[, etc
			}
		}
	}
	
	public static void setPlayer(int r, int c)
	{
		boards[r][c].setPlayer();
		boards[r][c].setMarked();
		marksSet++;
	}
	
	public static void setAI(Location loc)
	{
		loc.setAI();
		loc.setMarked();
		marksSet++;
	}
	
	public static void setAI(int r, int c)
	{
		boards[r][c].setAI();
		boards[r][c].setMarked();
		marksSet++;
	}
	
	public static void printBoard()
	{
		/* Looks like this:
		 * |_|_|_|
		 * |_|_|_|
		 * |_|_|_|
		 */
		for(int r = 0; r < ROWS; r++)
		{
			for(int c = 0; c < COLS; c++)
			{
				if(boards[r][c].getStatus() == Location.EMPTY)
				{
					System.out.print("|_");
				}
				else if(boards[r][c].getStatus() == Location.PLAYER)
				{
					if(MyProgram.ICON.equals("O"))
					{
						System.out.print("|O");	
					} else { System.out.print("|X");}
					
				}
				else
				{
					if(MyProgram.ICON.equals("O"))
					{
						System.out.print("|X");
					} else { System.out.print("|O"); }
					
				}
				System.out.print("|");
			}
			System.out.println();
		}
	}
	
	public static void reset(int r, int c)
	{
		boards[r][c].reset();
	} 
	
	public static Location getLocation(int r, int c)
	{
		return boards[r][c];
	}
	
	public boolean isMarked(int r, int c)
	{
		return boards[r][c].isMarked();
	}
	
	public boolean isMarked(Location loc)
	{
		return loc.isMarked();
	}
	
	public int getStatus(int r, int c)
	{
		return boards[r][c].getStatus();
	}
	
	public boolean isBlank()
	{
		boolean blank = true;
		for(int r = 0; r < ROWS; r++)
		{
			for(int c = 0; c < COLS; c++)
			{
				if(boards[r][c].isMarked())
				{
					blank = false;
				}
			}
		}
		return blank;
	}
}
