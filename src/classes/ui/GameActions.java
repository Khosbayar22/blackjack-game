package classes.ui;
import javax.swing.*;

import classes.Game;
import classes.GameSerializer;

import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameActions extends JPanel implements ActionListener {
    private Game game;
    private GameTable table;
    
    private JButton newRoundButton = new JButton("Бооцоо тавих");
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
    
    public GameActions()
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
        optionsPanel.add(newRoundButton);
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
        
        newRoundButton.addActionListener(this);
        hitButton.addActionListener(this);
        doubleButton.addActionListener(this);
		standButton.addActionListener(this);
		clearBet.addActionListener(this);
		add1Chip.addActionListener(this);
		add5Chip.addActionListener(this);
		add10Chip.addActionListener(this);
		add25Chip.addActionListener(this);
		add100Chip.addActionListener(this);
        
        game = new Game();
        game.newGame();
    }
    
    public void update()
    {
        dealerSays.setText("<html><p align=\"center\"><font face=\"Roboto\" color=\"white\" style=\"font-size: 20pt\">" + GameSerializer.announcement + "</font></p></html>");
        
        if (GameSerializer.isGameOver)
        {
            if (GameSerializer.isBetPlaced && !GameSerializer.isBankrupt)
            {
                newRoundButton.setEnabled(true);
            }
            else
            {
                newRoundButton.setEnabled(false);
            }
            
            hitButton.setEnabled(false);
            doubleButton.setEnabled(false);
            standButton.setEnabled(false);
            
            if (GameSerializer.isBetPlaced)
            {
                clearBet.setEnabled(true);
            }
            else
            {
                clearBet.setEnabled(false);
            }
            
            if (GameSerializer.totalWallet >= 1.0)
            {
                add1Chip.setEnabled(true);
            }
            else
            {
                add1Chip.setEnabled(false);
            }
            
            if (GameSerializer.totalWallet >= 5)
            {
                add5Chip.setEnabled(true);
            }
            else
            {
                add5Chip.setEnabled(false);
            }
            
            if (GameSerializer.totalWallet >= 10)
            {
                add10Chip.setEnabled(true);
            }
            else
            {
                add10Chip.setEnabled(false);
            }
            
            if (GameSerializer.totalWallet >= 25)
            {
                add25Chip.setEnabled(true);
            }
            else
            {
                add25Chip.setEnabled(false);
            }
            
            if (GameSerializer.totalWallet >= 100)
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
            newRoundButton.setEnabled(false);
            hitButton.setEnabled(true);

            if (GameSerializer.isCanDouble)
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
        
        cardsLeft.setText("Үлдсэн хөзрийн тоо: " + GameSerializer.totalCard + "/");
        
        currentBet.setText(Double.toString(GameSerializer.totalBet));
        playerWallet.setText(Double.toString(GameSerializer.totalWallet));
    }
    
    public void actionPerformed(ActionEvent event)
    {
        String command = event.getActionCommand();
        switch (command) {
            case "Бооцоо тавих":
                game.newRound();
                table.repaint();    
                update();
                break;
            case "Шалгах":
                game.hit();
                table.repaint();    
                update();
                break;
            case "Үржүүлэх":
                game.playDouble();
                table.repaint();    
                update();
                break;
            case "Зогсох":
                game.stand();
                table.repaint();    
                update();
                break;
            case "1", "5", "10", "25", "100":
                game.increaseBet(Integer.parseInt(command));
                table.repaint();    
                update();
                break;
            case "Цэвэрлэх":
                game.clearBet();
                table.repaint();    
                update();
                break;
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
        }
    }
}
