package classes.hands;

import java.util.*;

import classes.cards.Card;
import interfaces.cardHand;

public abstract class CardHand extends Vector<Card> implements cardHand
{    
    public CardHand()
    {
        super();
    }
    
    public void clear()
    {
        super.clear();
    }
    
    public int getTotal()
    {  
        int total = 0;
        
        for (Card eachCard : this)
        {
            total += eachCard.getValue();
        }
        
        return total;
    }
    
    public boolean isBust()
    {
        return (getTotal() > 21) ? true : false;
    }
    
    public boolean hasBlackjack()
    {
        return (getTotal() == 21 && this.size() == 2) ? true : false;
    }
    
    public String toString()
    {
        return super.toString() + " (" + getTotal() + ")"; 
    }
}