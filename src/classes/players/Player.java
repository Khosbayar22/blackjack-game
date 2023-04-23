package classes.players;
import classes.hands.PlayerCardHand;

import interfaces.player;

public class Player extends BlackjackPlayer implements player
{
    public transient PlayerCardHand hand = new PlayerCardHand();
    
    private double wallet = 100.00;
    
    private double  bet = 0.0;
    
    public Player()
    {
        super();
    }
    
    public Player(String name, int age, String gender)
    {
        super(name, age, gender);
    }
    
    public void setWallet(double amount)
    {
        this.wallet = amount;
    }
    
    public double getWallet()
    {
        return this.wallet;
    }
    
    public boolean setBet(double bet)
    {
        boolean betMade = false;
        
        if (bet <= (getWallet() + getBet()))
        {
            this.wallet += this.bet;
            this.bet = bet;
            this.wallet -= bet; 
            betMade = true;
        }
        
        return betMade;
    }
    
    public double getBet()
    {
        return this.bet;
    }
    
    public void clearBet()
    {
        this.wallet += this.bet;
        this.bet = 0.0;
    }
    
    public boolean doubleBet()
    {
        boolean betDoubled = false;
        
        if (setBet(getBet() * 2))
        {
            betDoubled = true;
        }
        
        return betDoubled;
    }
    
    
    public void loses()
    {
        this.bet = 0.0;
    }
    
    public void wins(double amount)
    {
        this.wallet += amount;
        this.bet = 0.0;
    }
    
    public boolean betPlaced()
    {
        return (getBet() > 0.0) ? true : false;
    }
    
    public boolean isBankrupt()
    {
        return (getWallet() < 1 && getBet() <= 0) ? true : false;
    }
    
    public boolean canDouble()
    {
        boolean answer = false;
        
        if (getBet() <= getWallet())
        {
            answer = true;
        }
        
        return answer;
    }
    
    public PlayerCardHand getHand()
    {
        return this.hand;
    }
}