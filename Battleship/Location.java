public class Location
{
  public static final int UNGUESSED = 0;
  public static final int HIT = 1;
  public static final int MISSED = 2;

  private int status = UNGUESSED;
  private boolean val = false;

  public Location() {}

  //checkers
  public boolean checkHit()
  {
    if(getStatus() == HIT)
    {
      return true;
    }
    return false;
  }

  public boolean checkMiss()
  {
    if(getStatus() == MISSED)
    {
      return true;
    }
    return false;
  }

  public boolean isUnguessed()
  {
    if(getStatus() == UNGUESSED)
    {
      return true;
    }
    return false;
  }

  //markers
  public void markHit()
  {
    status = HIT;
  }

  public void markMiss()
  {
    status = MISSED;
  }

  //checker
  public boolean hasShip()
  {
    return val;
  }
  //setters
  public void setShip(boolean val)
  {
    this.val = val;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }
  //getter
  public int getStatus()
  {
    return status;
  }

}
