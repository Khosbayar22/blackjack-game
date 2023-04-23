package classes.ui;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

import classes.GameSerializer;
import classes.cards.Card;
import classes.hands.CardPack;

public class GameTable extends JPanel
{
    private final int CARD_INCREMENT = 20;
    private final int CARD_START = 100;
    private final int DEALER_POSITION = 50;
    private final int PLAYER_POSITION = 200;
    
    private final int CARD_IMAGE_WIDTH = 71;
    private final int CARD_IMAGE_HEIGHT = 96;
    
    private final int NAME_SPACE = 10;
    
    private Font handTotalFont;
    private Font playerNameFont;
    
    private Image[] cardImages = new Image[CardPack.CARDS_IN_PACK + 1];
    
    public GameTable()
    {
        super();
        
        this.setBackground(Color.BLUE);
        this.setOpaque(false);
        handTotalFont = new Font("Serif", Font.PLAIN, 96);
        playerNameFont = new Font("Serif", Font.ITALIC, 20);
        
        GameSerializer.showAllDealerCards = true;
        
        for (int i = 0; i < CardPack.CARDS_IN_PACK; i++)
        {
            String cardName = "../../assets/cards/" + (i+1) + ".png";
            
            URL urlImg = getClass().getResource(cardName);
            Image cardImage = Toolkit.getDefaultToolkit().getImage(urlImg);
            cardImages[i] = cardImage;
        }
        
        String backCard = "../../assets/cards/red_back.png";
        
        URL backCardURL = getClass().getResource(backCard);
        Image backCardImage = Toolkit.getDefaultToolkit().getImage(backCardURL);
        
        cardImages[CardPack.CARDS_IN_PACK] = backCardImage;
        
        MediaTracker imageTracker = new MediaTracker(this);
        
        for (int i = 0; i < CardPack.CARDS_IN_PACK + 1; i++)
        {
            imageTracker.addImage(cardImages[i], i + 1); 
        }
        
        try
        {
            imageTracker.waitForAll();
        }
        catch (InterruptedException excep)
        {
        }
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.WHITE);
        
        g.setFont(playerNameFont);
        
        g.drawString(GameSerializer.dealerName, CARD_START, DEALER_POSITION - NAME_SPACE);
        g.drawString(GameSerializer.playerName, CARD_START, PLAYER_POSITION - NAME_SPACE);
        
        g.setFont(handTotalFont);
        
        String cardName;
    
        int i = CARD_START;
    
        if (GameSerializer.showAllDealerCards)
        {
            for (Card aCard : GameSerializer.dealerCardHand)
            {
                g.drawImage(cardImages[aCard.getCode() - 1], i, DEALER_POSITION, this);

                i += CARD_INCREMENT;
            }
        
            g.drawString(Integer.toString(GameSerializer.dealerCardHand.getTotal()), i 
                + CARD_IMAGE_WIDTH + CARD_INCREMENT, DEALER_POSITION 
                + CARD_IMAGE_HEIGHT);
        }
        else
        {
            for (Card aCard : GameSerializer.dealerCardHand)
            {
                g.drawImage(cardImages[CardPack.CARDS_IN_PACK], i, DEALER_POSITION, this);

                i += CARD_INCREMENT;
            }
        
            try
            {
                Card topCard = GameSerializer.dealerCardHand.lastElement();
            
                i -= CARD_INCREMENT;
            
                g.drawImage(cardImages[topCard.getCode() - 1], i, DEALER_POSITION, this);
            }
            catch (Exception e)
            {
            }
            
            g.drawString("?", i + CARD_IMAGE_WIDTH + CARD_INCREMENT, 
                DEALER_POSITION + CARD_IMAGE_HEIGHT);
            
        }
    
        i = CARD_START;

        for (Card aCard : GameSerializer.playerCardHand)
        {
            g.drawImage(cardImages[aCard.getCode() - 1], i, PLAYER_POSITION, this);

            i += CARD_INCREMENT;
        }
    
        g.drawString(Integer.toString(GameSerializer.playerCardHand.getTotal()), i + CARD_IMAGE_WIDTH + CARD_INCREMENT, PLAYER_POSITION + CARD_IMAGE_HEIGHT); 
    }
}