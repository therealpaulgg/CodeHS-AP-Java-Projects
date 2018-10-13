
public class Location {
	
	public static final int EMPTY = 0;
	public static final int PLAYER = 1;
	public static final int AI = 2;
	
	private boolean marked = false;
	private int status = 0;
	
	public Location() {}
	
	public boolean isMarked()
	{
		if(marked == true)
		{
			return true;
		}
		return false; 
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void setMarked()
	{
		marked = true;
	}
	
	public void setPlayer()
	{
		status = PLAYER;
	}
	
	public void setAI()
	{
		status = AI;
	}
	
	public void reset()
	{
		marked = false;
		status = EMPTY;
	} 
}
