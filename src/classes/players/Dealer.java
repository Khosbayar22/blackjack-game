package classes.players;

import classes.hands.DealerCardHand;
import classes.hands.Deck;
import classes.hands.PlayerCardHand;
import interfaces.player;

public class Dealer extends BlackjackPlayer implements player
{
    private Deck deck;
    
    public DealerCardHand hand = new DealerCardHand();
    
    private boolean firstDeal = true;
    
    public static final int DEALER_STANDS_ON = 17;
    public static final int CARD_PACKS = 2;
    
    private boolean gameOver = true;
    private boolean cardsFaceUp = false;
    
    private boolean playerCanDouble = true;
    
    private String said = "Бооцоогоо тавьна уу";
    
    public Dealer()
    {
        super("CPU", 45, "male");
        
        deck = new Deck(CARD_PACKS);
    }
    
    public void say(String announcement)
    {
        said = announcement;
    }
    
    public String says()
    {
        return said;
    }
    
    public boolean isGameOver()
    {
        return gameOver;
    }
    
    public boolean areCardsFaceUp()
    {
        return cardsFaceUp;
    }
    
    // Тоглогч бооцоо тавих
    public boolean acceptBetFrom(Player player, double bet)
    {
        boolean betSet = player.setBet(bet);
        
        if (player.betPlaced())
        {
            say("Таны тавьсан бооцоо: $" + player.getBet());
        }
        else
        {
            say("Бооцоо тавьна уу");
        }
        
        return (betSet) ? true : false;
    }
    
    public boolean deal(Player player)
    {
        boolean cardsDealt = false;
        
        if (player.betPlaced() && !player.isBankrupt())
        {   
            gameOver = false;
            cardsFaceUp = false;

            playerCanDouble = true;
            
            say("Dealer");
            player.hand = new PlayerCardHand();
            hand = new DealerCardHand();
            
            player.hand.add(deck.deal());
            this.hand.add(deck.deal());
            
            player.hand.add(deck.deal());
            this.hand.add(deck.deal());
            
            cardsDealt = true;
            firstDeal = false; 
            
            if (player.hand.hasBlackjack())
            {
                say("Blackjack!");
                go(player);
            }
            
        }
        else if (!player.betPlaced())
        {
            say("Бооцоогоо тавьна уу");
            gameOver = true;
        }
        
        return cardsDealt;
    }
    
    public void hit(Player player)
    {
        say(player.getName() + " шалгасан.");
        player.hand.add(deck.deal());
        
        playerCanDouble = false;
        
        if (player.hand.isBust())
        {
            say(player.getName() + " хожигдлоо : $" + player.getBet());
            player.loses();
            gameOver = true;
        }
    }
    
    public void playDouble(Player player)
    {
        if (player.doubleBet() && playerCanDouble)
        {
            player.hand.add(deck.deal());
            say(player.getName() + " 2 үржүүлэх.");
            
            if (player.hand.isBust())
            {
                say(player.getName() + " алдаа. Алдсан: $" + player.getBet());
                player.loses();
                gameOver = true;
            }
            else
            {
                go(player);
            }
        }
        else
        {
            say(player.getName() + ", Хангалттай мөнгө байхгүй байна");
        }
    }
    
    
    public void stand(Player player)
    {
        say(player.getName() + " - " + this.getName() + " ээлж.");
        go(player);
    }
    
    private void go(Player player)
    {
        cardsFaceUp = true;
        
        if (!hand.hasBlackjack())
        {
            while (hand.getTotal() < DEALER_STANDS_ON)
            {
                hand.add(deck.deal());
                say(this.getName() + " дуусгах.");
            }
            
            if (hand.isBust())
            {
                say(this.getName() + " алдлаа.");
            }
            else
            {
                say(this.getName() + " - " + hand.getTotal());
            }            
        }
        else
        {
            say(this.getName() + " BLACKJACK!");
        }
        
        if (hand.hasBlackjack() && player.hand.hasBlackjack())
        {
            player.clearBet();
        }
        else if (player.hand.hasBlackjack())
        {
            double winnings = (player.getBet() * 3) / 2;
            say(player.getName() + " Хожсон $" + winnings);
            player.wins(player.getBet() + winnings);
        }
        else if (hand.hasBlackjack())
        {
            say("Blackjack. " + player.getName() + " алдсан: $" + player.getBet());
            player.loses();
        }
        else if (hand.isBust())
        {
            say("Dealer хожигдлоо . " + player.getName() + " хожсон: $" + player.getBet());
            player.wins(player.getBet() * 2);
        }
        else if (player.hand.getTotal() == hand.getTotal())
        {
            player.clearBet();
        }
        else if (player.hand.getTotal() < hand.getTotal())
        {
            say(player.getName() + " хожигдсон. $" + player.getBet());
            player.loses();
        }
        else if (player.hand.getTotal() > hand.getTotal())
        {
            say(player.getName() + " хожсон. $" + player.getBet());
            player.wins(player.getBet() * 2);
        }
        
        gameOver = true;
    }
    
    public int cardsLeftInPack()
    {
        return deck.size();
    }
    
    public void newDeck()
    {
        deck = new Deck(CARD_PACKS);
    }
    
    public boolean canPlayerDouble(Player player)
    {
        return (playerCanDouble && player.canDouble()) ? true : false;
    }
    
    public DealerCardHand getHand()
    {
        return hand;
    }
}