package classes;

import classes.players.Dealer;
import classes.players.Player;
import classes.ui.GameTable;
import classes.ui.GameActions;

public class Game
{
    private Dealer dealer;
    private Player player;
    

    public void newGame()
    {
		this.dealer = new Dealer();
        this.player = new Player("Хосбаяр", 32, "Male");
        this.player.setWallet(100.00);

        updateValues();
    }
    
    public void newRound()
    {
        dealer.deal(player);
        updateValues();
    }
    
    public void hit()
    {
        dealer.hit(player);
        updateValues();
    }
    
    public void playDouble()
    {
        dealer.playDouble(player);
        updateValues();
    }
    
    public void stand()
    {
        dealer.stand(player);
        updateValues();
    }
    
    public void increaseBet(int amount)
    {
        dealer.acceptBetFrom(player, amount + player.getBet());
        updateValues();
    }
    
    public void clearBet()
    {
        player.clearBet();
        updateValues();
    }
    
    private void updateValues()
    {
        GameSerializer.isGameOver = dealer.isGameOver();
        GameSerializer.isBetPlaced = player.betPlaced();
        GameSerializer.isBankrupt = player.isBankrupt();
        GameSerializer.isCanDouble = dealer.canPlayerDouble(player);

        GameSerializer.totalBet = player.getBet();
        GameSerializer.totalCard = dealer.cardsLeftInPack();
        GameSerializer.totalWallet = player.getWallet();
        GameSerializer.announcement = dealer.says();
        GameSerializer.dealerName = this.dealer.getName();
        GameSerializer.playerName = this.player.getName();
        GameSerializer.playerCardHand = this.player.getHand();
        GameSerializer.dealerCardHand = this.dealer.getHand();
        GameSerializer.showAllDealerCards = (dealer.areCardsFaceUp()) ? true : false;
    }
}
