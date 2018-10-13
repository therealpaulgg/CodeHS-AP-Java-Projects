import java.util.*;

public class MyProgram extends ConsoleProgram
{
	
	public Board board = new Board();
	private Location aiLast = new Location();
	private Location[] corners = {Board.getLocation(0, 0), Board.getLocation(0, 2), Board.getLocation(2, 0), Board.getLocation(2, 2)};
	private boolean firstMove = false;
	private boolean hasHit = false;
	private boolean playerWon = false;
	private boolean aiWon = false;
	private boolean tie = false;
	public static String ICON = "";
	
	public void run()
	{
		
		// missing infinite play loop. need to add while so game does not instantly end
		System.out.println("Welcome to Tic Tac Toe!");
		while(true)
		{
			ICON = readLine("Will you be X or O? ");
			ICON = ICON.toUpperCase();
			if(ICON.equals("O") || ICON.equals("X"))
			{
				break;
			} else { System.out.println("Try again."); }
			
		}
		
		Board.printBoard();
		while(true)
		{
			firstMove = false;
			hasHit = false;
			playerWon = false;
			aiWon = false;
			tie = false;
			ICON = "";
			for(int r = 0; r < Board.ROWS; r++)
			{
				for(int c = 0; c < Board.COLS; c++)
				{
					Board.reset(r, c);
				}
			}
			while(playerWon == false && aiWon == false && tie == false)
			{
				String inputX = "";
				String inputY = "";
				int x = 0;
				int y = 0;
				while(playerWon == false)
				{
					while(true)
					{
						inputX = readLine("Please enter your desired row. ");
						if(inputX.equals("1") || inputX.equals("2") || inputX.equals("3"))
						{
							break;
						} else {
							System.out.println("Please try again.");
						}
					}
					// add: if inputX != 0 || 1 || 2, repeat input.
					while(true)
					{
						inputY = readLine("Please enter your desired column. ");
						if(inputY.equals("1") || inputY.equals("2") || inputY.equals("3"))
						{
							break;
						} else {
							System.out.println("Please try again.");
						}
					}
					x = Integer.parseInt(inputX) - 1;
					y = Integer.parseInt(inputY) - 1;
					if(!Board.getLocation(x, y).isMarked())
					{
						break;
					} else {
						System.out.println("That point is already marked. Try again.");
					}
				}
				// add: if inputY != 0 || 1 || 2, repeat input.
				
				Board.setPlayer(x, y);
				System.out.println();
				Board.printBoard();
				checkVictory();
				checkTie();
				if(tie == true || playerWon == true)
				{
					break;
				}
				System.out.println("AI Playing...");
				// Regarding AI: crucial bug must be fixed: currently a mark can be placed on a mark. 
				aiTurn();
				Board.printBoard();
				checkLoss();
			}
			if(playerWon == true)
			{
				System.out.println("You won! Thanks for playing.");
			}
			else if(aiWon == true)
			{
				System.out.println("You lost...thanks for playing.");
			}
			else
			{
				System.out.println("You tied. Thanks for playing!");
			}
			boolean playAgain = readBoolean("Would you like to play again? (true/false) ");
			if(playAgain == true)
			{
				System.out.println("Alright, another!");;
			} else { 
				System.out.println("Bye!");
				break;
			}
		}
		
		
		/* Code for noob AI: 
		 * 
		 * while(true)
		{
			int xx = Randomizer.nextInt(0, 2);
			int yy = Randomizer.nextInt(0, 2);
			if(board.isMarked(xx, yy))
			{
				// just picks a random spot on the board that hasnt been picked. 
				break;
			}
		} */ 
		
		
		
		
	}
	
	private void checkTie() {
		int check = 0;
		for(int a = 0; a < Board.ROWS; a++)
		{
			for(int b = 0; b < Board.COLS; b++)
			{
				if(board.getStatus(a, b) != Location.EMPTY)
				{
					check++;
				}
			}
		}
		if(check == 9)
		{
			tie = true;
		}
	}

	private void checkLoss() {
		for(int i = 0; i < Board.ROWS; i++)
		{
			if((board.getStatus(i, 0) == Location.AI && board.getStatus(i, 1) == Location.AI && board.getStatus(i, 2) == Location.AI))
			{
				aiWon = true;
				break;
			}
			else if(board.getStatus(0, i) == Location.AI && board.getStatus(1, i) == Location.AI && board.getStatus(2, i) == Location.AI)
			{
				aiWon = true;
				break;
			}
		}
		if(board.getStatus(0, 0) == Location.AI && board.getStatus(1, 1) == Location.AI && board.getStatus(2, 2) == Location.AI)
		{
			aiWon = true;
		}
		else if(board.getStatus(0, 2) == Location.AI && board.getStatus(1, 1) == Location.AI && board.getStatus(2, 0) == Location.AI)
		{
			aiWon = true;
		}
	}

	private void checkVictory() {
		for(int i = 0; i < Board.ROWS; i++)
		{
			if((board.getStatus(i, 0) == Location.PLAYER && board.getStatus(i, 1) == Location.PLAYER && board.getStatus(i, 2) == Location.PLAYER))
			{
				playerWon = true;
				break;
			}
			else if(board.getStatus(0, i) == Location.PLAYER && board.getStatus(1, i) == Location.PLAYER && board.getStatus(2, i) == Location.PLAYER)
			{
				playerWon = true;
				break;
			}
		}
		if(board.getStatus(0, 0) == Location.PLAYER && board.getStatus(1, 1) == Location.PLAYER && board.getStatus(2, 2) == Location.PLAYER)
		{
			playerWon = true;
		}
		else if(board.getStatus(0, 2) == Location.PLAYER && board.getStatus(1, 1) == Location.PLAYER && board.getStatus(2, 0) == Location.PLAYER)
		{
			playerWon = true;
		}
	}

	void setHorizontal() {
		for(int x = 0; x < 3; x++)
		{
			if(board.getStatus(x, 0) == Location.AI && board.getStatus(x, 1) == Location.AI && !Board.getLocation(x, 2).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(x, 2));
				aiLast = Board.getLocation(x, 2);
				Board.marksSet++;
				hasHit = true;
			}
			else if(board.getStatus(x, 0) == Location.AI && board.getStatus(x, 2) == Location.AI && !Board.getLocation(x, 1).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(x, 1));
				aiLast = Board.getLocation(x, 1);
				Board.marksSet++;
				hasHit = true;
			}
			else if(board.getStatus(x, 1) == Location.AI && board.getStatus(x, 2) == Location.AI && !Board.getLocation(x, 0).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(x, 0));
				aiLast = Board.getLocation(x, 0);
				Board.marksSet++;
				hasHit = true;
			}
		}
	}
	
	void setVertical() {
		for(int x = 0; x < 3; x++)
		{
			if(board.getStatus(0, x) == Location.AI && board.getStatus(1, x) == Location.AI && !Board.getLocation(2, x).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(2, x));
				aiLast = Board.getLocation(2, x);
				Board.marksSet++;
				hasHit = true;
			}
			
			else if(board.getStatus(0, x) == Location.AI && board.getStatus(2, x) == Location.AI && !Board.getLocation(1, x).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(1, x));
				aiLast = Board.getLocation(1, x);
				Board.marksSet++;
				hasHit = true;
			}
				
			else if(board.getStatus(1, x) == Location.AI && board.getStatus(2, x) == Location.AI && !Board.getLocation(0, x).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(0, x));
				aiLast = Board.getLocation(0, x);
				Board.marksSet++;
				hasHit = true;
			}
		}
	}
	void setDiagonal() {
		if(board.getStatus(0, 0) == Location.AI && board.getStatus(1, 1) == Location.AI && !Board.getLocation(2, 2).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(2, 2));
			aiLast = Board.getLocation(2, 2);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(1, 1) == Location.AI && board.getStatus(2, 2) == Location.AI && !Board.getLocation(0, 0).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(0, 0));
			aiLast = Board.getLocation(0, 0);
			Board.marksSet++;
			hasHit = true;
		}
			
		else if(board.getStatus(0, 0) == Location.AI && board.getStatus(2, 2) == Location.AI && !Board.getLocation(1, 1).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(1, 1));
			aiLast = Board.getLocation(1, 1);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(2, 0) == Location.AI && board.getStatus(1, 1) == Location.AI && !Board.getLocation(0, 2).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(0, 2));
			aiLast = Board.getLocation(0, 2);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(2, 0) == Location.AI && board.getStatus(0, 2) == Location.AI && !Board.getLocation(1, 1).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(1, 1));
			aiLast = Board.getLocation(1, 1);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(1, 1) == Location.AI && board.getStatus(0, 2) == Location.AI && !Board.getLocation(2, 0).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(2, 0));
			aiLast = Board.getLocation(2, 0);
			Board.marksSet++;
			hasHit = true;
		}
	}
	
	void checkHorizontal() {
		for(int x = 0; x < 3; x++)
		{
			if(board.getStatus(x, 0) == Location.PLAYER && board.getStatus(x, 1) == Location.PLAYER && !Board.getLocation(x, 2).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(x, 2));
				aiLast = Board.getLocation(x, 2);
				Board.marksSet++;
				hasHit = true;
			}
			else if(board.getStatus(x, 0) == Location.PLAYER && board.getStatus(x, 2) == Location.PLAYER && !Board.getLocation(x, 1).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(x, 1));
				aiLast = Board.getLocation(x, 1);
				Board.marksSet++;
				hasHit = true;
			}
			else if(board.getStatus(x, 1) == Location.PLAYER && board.getStatus(x, 2) == Location.PLAYER && !Board.getLocation(x, 0).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(x, 0));
				aiLast = Board.getLocation(x, 0);
				Board.marksSet++;
				hasHit = true; }
		}
	}
	
	void checkVertical() {
		for(int x = 0; x < 3; x++)
		{
			if(board.getStatus(0, x) == Location.PLAYER && board.getStatus(1, x) == Location.PLAYER && !Board.getLocation(2, x).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(2, x));
				aiLast = Board.getLocation(2, x);
				Board.marksSet++;
				hasHit = true;
			}
			
			else if(board.getStatus(0, x) == Location.PLAYER && board.getStatus(2, x) == Location.PLAYER && !Board.getLocation(1, x).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(1, x));
				aiLast = Board.getLocation(1, x);
				Board.marksSet++;
				hasHit = true;
			}
				
			else if(board.getStatus(1, x) == Location.PLAYER && board.getStatus(2, x) == Location.PLAYER && !Board.getLocation(0, x).isMarked() && hasHit == false)
			{
				Board.setAI(Board.getLocation(0, x));
				aiLast = Board.getLocation(0, x);
				Board.marksSet++;
				hasHit = true;
			}
		}
	}
	void checkDiagonal() {
		if(board.getStatus(0, 0) == Location.PLAYER && board.getStatus(1, 1) == Location.PLAYER && !Board.getLocation(2, 2).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(2, 2));
			aiLast = Board.getLocation(2, 2);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(1, 1) == Location.PLAYER && board.getStatus(2, 2) == Location.PLAYER && !Board.getLocation(0, 0).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(0, 0));
			aiLast = Board.getLocation(0, 0);
			Board.marksSet++;
			hasHit = true;
		}
			
		else if(board.getStatus(0, 0) == Location.PLAYER && board.getStatus(2, 2) == Location.PLAYER && !Board.getLocation(1, 1).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(1, 1));
			aiLast = Board.getLocation(1, 1);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(2, 0) == Location.PLAYER && board.getStatus(1, 1) == Location.PLAYER && !Board.getLocation(0, 2).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(0, 2));
			aiLast = Board.getLocation(0, 2);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(2, 0) == Location.PLAYER && board.getStatus(0, 2) == Location.PLAYER && !Board.getLocation(1, 1).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(1, 1));
			aiLast = Board.getLocation(1, 1);
			Board.marksSet++;
			hasHit = true;
		}
		
		else if(board.getStatus(1, 1) == Location.PLAYER && board.getStatus(0, 2) == Location.PLAYER && !Board.getLocation(2, 0).isMarked() && hasHit == false)
		{
			Board.setAI(Board.getLocation(2, 0));
			aiLast = Board.getLocation(2, 0);
			Board.marksSet++;
			hasHit = true;
		}
	}
	
	void aiTurn()
	{
		hasHit = false;
		if(firstMove == false)
		{
			int index;
			while(true)
			{
				index = Randomizer.nextInt(0, 3);
				if(!corners[index].isMarked())
				{
					break;
				}
			}
			Board.setAI(corners[index]);
			firstMove = true;
			aiLast = corners[index];
			Board.marksSet++;
			hasHit = true;
		} else {
			setHorizontal();
			setVertical();
			setDiagonal();	
			checkHorizontal();
			checkVertical();
			checkDiagonal();
			
			if(aiLast == Board.getLocation(0, 0) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(0, 1), Board.getLocation(1, 0), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(aiLast == Board.getLocation(0, 1) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(0, 2), Board.getLocation(0, 0), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(aiLast == Board.getLocation(0, 2) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(0, 2), Board.getLocation(1, 2), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(aiLast == Board.getLocation(1, 0) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(0, 0), Board.getLocation(2, 0), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(aiLast == Board.getLocation(1, 1) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(0, 2), Board.getLocation(2, 2), Board.getLocation(1, 1),
						Board.getLocation(0, 0), Board.getLocation(0, 1), Board.getLocation(1, 0), Board.getLocation(1, 2), Board.getLocation(2, 1),
						Board.getLocation(2, 0)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
				
			}
			else if(aiLast == Board.getLocation(1, 2) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(0, 2), Board.getLocation(2, 2), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(aiLast == Board.getLocation(2, 0) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(1, 0), Board.getLocation(2, 1), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(aiLast == Board.getLocation(2, 1) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(0, 2), Board.getLocation(2, 0), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(aiLast == Board.getLocation(2, 2) && hasHit == false)
			{
				int checker = 0;
				ArrayList<Location> list = new ArrayList<>(Arrays.asList(Board.getLocation(2, 1), Board.getLocation(1, 2), Board.getLocation(1, 1)));
				Location pick = null;
				while(true)
				{
					int index = Randomizer.nextInt(0, list.size() - 1);
					pick = list.get(index);
					list.remove(index);
					checker++;
					if(!pick.isMarked() || checker == 3)
					{
						break;
					}
				}
				if(checker != 3)
				{
					Board.setAI(pick);
					aiLast = pick;
					Board.marksSet++;
					hasHit = true;
				}
			}
			else if(board.getStatus(1, 0) == Location.EMPTY  && hasHit == false)
			{
				Board.setAI(1, 0);
				/* aiLast = Board.getLocation(1, 0);
				Board.marksSet++;
				hasHit = true; */
			}
			else if(board.getStatus(1, 2) == Location.EMPTY && hasHit == false)
			{
				Board.setAI(1, 2);
				/* aiLast = Board.getLocation(1, 2);
				Board.marksSet++;
				hasHit = true; */
			}
			else if(board.getStatus(0, 1) == Location.EMPTY  && hasHit == false)
			{
				Board.setAI(0, 1);
				/* aiLast = Board.getLocation(1, 0);
				Board.marksSet++;
				hasHit = true; */
			}
			else if(board.getStatus(2, 1) == Location.EMPTY && hasHit == false)
			{
				Board.setAI(2, 1);
				/* aiLast = Board.getLocation(2, 1);
				Board.marksSet++;
				hasHit = true; */
			}
		}
	}
}
