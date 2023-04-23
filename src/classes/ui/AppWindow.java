package classes.ui;
import javax.swing.*;

import classes.Game;

import java.awt.*;

import java.awt.event.*;

public class AppWindow extends JFrame implements ActionListener
{
    private GameActions gameActions;
    private Color defaultTableColour = new Color(6, 120, 0);
    
    final int WIDTH = 600;
    final int HEIGHT = 500;

	public AppWindow()
    {
        super("Blackjack");
        
        Dimension windowSize = new Dimension(WIDTH, HEIGHT);
        setSize(windowSize);
        setLocationRelativeTo(null);
        
        this.setBackground(defaultTableColour);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu actionMenu = new JMenu("Үйлдэл");

        JMenuItem newGameAction = new JMenuItem("Шинээр эхлэх");
        JMenuItem exitAction = new JMenuItem("Гарах");
        actionMenu.add(newGameAction);
        actionMenu.add(exitAction);
        menuBar.add(actionMenu);
        
        setJMenuBar(menuBar);
        
		newGameAction.addActionListener(this);
        exitAction.addActionListener(this);
    
        gameActions = new GameActions();
        
        gameActions.setBackground(defaultTableColour);
		add(gameActions);

        setVisible(true);
    }

	public void actionPerformed(ActionEvent evt)
    {
        String act = evt.getActionCommand();
        
        // if (act.equals("Шинээр эхлэх"))
        // {
        //     gamePanel.newGame();
        // }
        // else if (act.equals("Гарах"))
        // {
        //     dispose();
        // }
		
		// gamePanel.updateValues();
	}
}