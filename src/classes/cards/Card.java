package classes.cards;

import interfaces.card;

public class Card implements card
{
    private Suit suit;
    
    private Face face;
    
    private int code;
    
    public Card(Face face, Suit suit, int code)
    {
        setFace(face);
        setSuit(suit);
        setCode(code);
    }
    
    public void setFace(Face face)
    {
        this.face = face;
    }
    
    public Face getFace()
    {
        return this.face;
    }
    
    public void setSuit(Suit suit)
    {
        this.suit = suit;
    }
    
    public Suit getSuit()
    {
        return this.suit;
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public int getValue()
    {
        return this.getFace().getValue();
    }
    
    public String toString()
    {
        return getFace() + " of " + getSuit();
    }
}