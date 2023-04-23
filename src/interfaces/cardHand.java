package interfaces;

import classes.cards.Card;

public interface cardHand {
    boolean add(Card card);
    void clear();
    int getTotal();
    boolean isBust();
    boolean hasBlackjack();
}
