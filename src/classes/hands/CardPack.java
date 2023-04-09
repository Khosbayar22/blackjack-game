package classes.hands;

import java.util.*;

import classes.cards.Card;
import classes.cards.Face;
import classes.cards.Suit;

public class CardPack extends Stack<Card>
{
    public static final int CARDS_IN_PACK = 52;
    
    public CardPack()
    {
        super();
        
        final String[] suits = {"Бунд", "Гил", "Цэцэг", "Дөрвөлжин"};
        
        int cardCode = 1;
        
        for (String suit : suits)
        {
            for (int i = 1; i < 14; i++)
            {
                this.push(new Card(new Face(i), new Suit(suit), cardCode));
                cardCode++;
            }
        } 
    }
}