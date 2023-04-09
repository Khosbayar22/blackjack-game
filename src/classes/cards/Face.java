package classes.cards;

public class Face
{
    private String name;
    
    private int value;
    
    private final int HIGH_ACE = 11;
    private final int LOW_ACE = 1;
    
    public Face(int face)
    {
        switch (face)
        {
            case 1:
                setName("Нэг");
                setValue(11);
                break;
            case 2:
                setName("Хоёр");
                setValue(2);
                break;
            case 3:
                setName("Гурав");
                setValue(3);
                break;
            case 4:
                setName("Дөрөв");
                setValue(4);
                break;
            case 5:
                setName("Тав");
                setValue(5);
                break;
            case 6:
                setName("Зургаа");
                setValue(6);
                break;
            case 7:
                setName("Долоо");
                setValue(7);
                break;
            case 8:
                setName("Найм");
                setValue(8);
                break;
            case 9:
                setName("Ес");
                setValue(9);
                break;
            case 10:
                setName("Арав");
                setValue(10);
                break;
            case 11:
                setName("Боол");
                setValue(10);
                break;
            case 12:
                setName("Хатан");
                setValue(10);
                break;
            case 13:
                setName("Ноён");
                setValue(10);
                break;
            default:
                break;
        }
    }
    
    private void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    private void setValue(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return this.value;
    }
    
    public boolean isAce()
    {
        return (name.equals("Ace")) ? true : false;
    }
    
    public boolean isLowAce()
    {
        return (name.equals("Ace") && getValue() == LOW_ACE) ? true : false;
    }
    
    public void switchAce()
    {
        if (isAce())
        {
            if (getValue() == HIGH_ACE)
            {
                setValue(LOW_ACE);
            }
        }
    }
    
    public String toString()
    {
        return getName();
    }
}