package classes.hands;

import classes.cards.Card;

public class PlayerCardHand extends CardHand
{
    public PlayerCardHand()
    {
        super();
    }
    
    public boolean add(Card card)
    {
        boolean cardAdded = false;
        
        if (!isBust() && !hasBlackjack())
        {
            cardAdded = super.add(card);
                        
            if (isBust())
            {
                for (Card eachCard : this)
                {
                    eachCard.getFace().switchAce();
                    
                    if (!isBust()) 
                    {
                        break;
                    }
                }
            }
        }
        
        return (cardAdded) ? true : false;
    }
}