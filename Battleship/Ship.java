public class Ship
{
  public static final int UNSET = -1;
  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;

  private int row = UNSET;
  private int col = UNSET;
  private int length;
  private int direction = UNSET;

  public Ship(int length)
  {
    this.length = length;
  }

  public boolean isLocationSet()
  {
    return row != UNSET && col != UNSET;
  }

  public boolean isDirectionSet()
  {
    return direction != UNSET;
  }

  public void setLocation(int row, int col)
  {
    this.row = row;
    this.col = col;
  }

  public void setDirection(int direction)
  {
    this.direction = direction;
  }

  public int getRow()
  {
    return row;
  }

  public int getCol()
  {
    return col;
  }

  public int getDirection()
  {
    return direction;
  }
  public int getLength()
  {
    return length;
  }

  private String directionToString()
  {
    if(direction == HORIZONTAL && isDirectionSet() == true)
    {
      return "horizontal";
    }
    else if(direction == VERTICAL && isDirectionSet() == true)
    {
      return "verical";
    }
    return "unset direction";
  }

  private String locationToString()
  {
    String location = "";
    location += ("(" + row) + (", " + col) + ")";
    if(isLocationSet() == true)
    {
      return location;
    }
    return "(unset location)";
  }

  public String toString()
  {
    return directionToString() + " ship of length " + getLength() + " at " + locationToString();
  }
}
