package classes.hands;

import classes.cards.Card;
import interfaces.cardHand;

public class DealerCardHand extends CardHand implements cardHand
{
    public DealerCardHand()
    {
        super();
    }
    
    public boolean add(Card card)
    {
        boolean cardAdded = false;
        
        if (!isBust() && !hasBlackjack())
        {            
            cardAdded = super.add(card);
        }
        
        return (cardAdded) ? true : false;
    }
}