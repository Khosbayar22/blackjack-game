package interfaces;

import classes.cards.Face;
import classes.cards.Suit;

public interface card {
    Face getFace();
    void setFace(Face face);
    Suit getSuit();
    void setSuit(Suit suit);
    int getCode();
    void setCode(int code);
}
