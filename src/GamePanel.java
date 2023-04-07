import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;

import Players.*;
import Cards.*;

public class GamePanel extends JPanel implements ActionListener
{
    private Dealer dealer;
    private Player player;
    
    private GameTable table;
    
    private JButton newGameButton = new JButton("Бооцоо тавих");
    private JButton hitButton = new JButton("Шалгах");
    private JButton doubleButton = new JButton("Үржүүлэх");
	private JButton standButton = new JButton("Зогсох");
    private JButton add1Chip = new JButton("1");
    private JButton add5Chip = new JButton("5");
    private JButton add10Chip = new JButton("10");
    private JButton add25Chip = new JButton("25");
    private JButton add100Chip = new JButton("100");
    private JButton clearBet =  new JButton("Цэвэрлэх");
    
    private JLabel currentBet = new JLabel("Бооцоогоо тавьна уу");
    private JLabel playerWallet = new JLabel("$100");
    private JLabel cardsLeft = new JLabel("");
    private JLabel dealerSays = new JLabel("");
    
    public GamePanel()
    {
        this.setLayout(new BorderLayout());
        
        table = new GameTable();
        add(table, BorderLayout.CENTER);
        
        JPanel betPanel = new JPanel();
        betPanel.add(currentBet);
        betPanel.add(clearBet);
        betPanel.add(add1Chip);
        betPanel.add(add5Chip);
        betPanel.add(add10Chip);
        betPanel.add(add25Chip);
        betPanel.add(add100Chip);
        betPanel.add(playerWallet);
        
        JPanel dealerPanel = new JPanel();
        dealerPanel.add(dealerSays);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(newGameButton);
        optionsPanel.add(hitButton);
        optionsPanel.add(doubleButton);
        optionsPanel.add(standButton);
        optionsPanel.add(cardsLeft);
        
        JPanel bottomItems = new JPanel();
        bottomItems.setLayout(new GridLayout(0,1));
        bottomItems.add(dealerPanel);
        bottomItems.add(betPanel);
        bottomItems.add(optionsPanel);
        add(bottomItems, BorderLayout.SOUTH);
        
        betPanel.setOpaque(false);
        dealerPanel.setOpaque(false);
        optionsPanel.setOpaque(false);
        bottomItems.setOpaque(false);
        
        // add listeners to buttons
        newGameButton.addActionListener(this);
        hitButton.addActionListener(this);
        doubleButton.addActionListener(this);
		standButton.addActionListener(this);
		clearBet.addActionListener(this);
		add1Chip.addActionListener(this);
		add5Chip.addActionListener(this);
		add10Chip.addActionListener(this);
		add25Chip.addActionListener(this);
		add100Chip.addActionListener(this);
		
		dealer = new Dealer();
        player = new Player("Хосбаяр", 32, "Male");
        player.setWallet(100.00);
		
        updateValues();
    }
    
    public void actionPerformed(ActionEvent evt)
    {
        String act = evt.getActionCommand();
        
        if (act.equals("Бооцоо тавих"))
        {
            newGame();
        }
        else if (act.equals("Шалгах"))
        {
            hit();
        }
        else if (act.equals("Үржүүлэх"))
        {
            playDouble();
        }
        else if (act.equals("Зогсох"))
        {
            stand();
        }
        else if (act.equals("1") || act.equals("5") || act.equals("10") || act.equals("25") || act.equals("100"))
        {
            increaseBet(Integer.parseInt(act));
        }
        else if (act.equals("Цэвэрлэх"))
        {
            clearBet();
        }
        
        updateValues();
    }
    
    public void newGame()
    {
        dealer.deal(player);
    }
    
    public void hit()
    {
        dealer.hit(player);
    }
    
    public void playDouble()
    {
        dealer.playDouble(player);
    }
    
    public void stand()
    {
        dealer.stand(player);
    }
    
    public void increaseBet(int amount)
    {
        dealer.acceptBetFrom(player, amount + player.getBet());
    }
    
    public void clearBet()
    {
        player.clearBet();
    }
    
    public void updateValues()
    {
        dealerSays.setText("<html><p align=\"center\"><font face=\"Serif\" color=\"white\" style=\"font-size: 20pt\">" + dealer.says() + "</font></p></html>");
        
        if (dealer.isGameOver())
        {
            if (player.betPlaced() && !player.isBankrupt())
            {
                newGameButton.setEnabled(true);
            }
            else
            {
                newGameButton.setEnabled(false);
            }
            hitButton.setEnabled(false);
            doubleButton.setEnabled(false);
            standButton.setEnabled(false);
            
            if (player.betPlaced())
            {
                clearBet.setEnabled(true);
            }
            else
            {
                clearBet.setEnabled(false);
            }
            
            if (player.getWallet() >= 1.0)
            {
                add1Chip.setEnabled(true);
            }
            else
            {
                add1Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 5)
            {
                add5Chip.setEnabled(true);
            }
            else
            {
                add5Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 10)
            {
                add10Chip.setEnabled(true);
            }
            else
            {
                add10Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 25)
            {
                add25Chip.setEnabled(true);
            }
            else
            {
                add25Chip.setEnabled(false);
            }
            
            if (player.getWallet() >= 100)
            {
                add100Chip.setEnabled(true);
            }
            else
            {
                add100Chip.setEnabled(false);
            }
        }
        else
        {
            newGameButton.setEnabled(false);
            hitButton.setEnabled(true);
            if (dealer.canPlayerDouble(player))
            {
                doubleButton.setEnabled(true);
            }
            else
            {
                doubleButton.setEnabled(false);
            }
            
            standButton.setEnabled(true);
            
            clearBet.setEnabled(false);
            add1Chip.setEnabled(false);
            add5Chip.setEnabled(false);
            add10Chip.setEnabled(false);
            add25Chip.setEnabled(false);
            add100Chip.setEnabled(false);
        }
        
        table.update(dealer.getHand(), player.getHand(), (dealer.areCardsFaceUp()) ? true : false);
		table.setNames(dealer.getName(), player.getName());
        table.repaint();
        
        cardsLeft.setText("Ширээ: " + dealer.cardsLeftInPack() + "/" + (dealer.CARD_PACKS * Cards.CardPack.CARDS_IN_PACK));
        
        currentBet.setText(Double.toString(player.getBet()));
        playerWallet.setText(Double.toString(player.getWallet()));
    }
}