import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class AppWindow extends JFrame 
    implements ActionListener, ComponentListener
{
    private GamePanel gamePanel;
    private Color defaultTableColour = new Color(6, 120, 0);
    
    final int WIDTH = 600;
    final int HEIGHT = 500;

	public AppWindow()
    {
        super("Blackjack");
        
        addComponentListener(this);
        
        Dimension windowSize = new Dimension(WIDTH, HEIGHT);
        setSize(windowSize);
        setLocationRelativeTo(null);
        
        this.setBackground(defaultTableColour);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu actionMenu = new JMenu("Үйлдэл");
        JMenuItem dealAction = new JMenuItem("Бооцоо тавих");
        JMenuItem hitAction = new JMenuItem("Шалгах");
        JMenuItem doubleAction = new JMenuItem("Үржүүлэх");
        JMenuItem standAction = new JMenuItem("Зогсох");
        actionMenu.add(dealAction);
        actionMenu.add(hitAction);
        actionMenu.add(doubleAction);
        actionMenu.add(standAction);
        menuBar.add(actionMenu);
        
        JMenu betMenu = new JMenu("Бооцоо");
        JMenuItem oneChip = new JMenuItem("$1");
        JMenuItem fiveChip = new JMenuItem("$5");
        JMenuItem tenChip = new JMenuItem("$10");
        JMenuItem twentyFiveChip = new JMenuItem("$25");
        JMenuItem hundredChip = new JMenuItem("$100");
        betMenu.add(oneChip);
        betMenu.add(fiveChip);
        betMenu.add(tenChip);
        betMenu.add(twentyFiveChip);
        betMenu.add(hundredChip);
        menuBar.add(betMenu);
        
        setJMenuBar(menuBar);
        
		// action listeners
		dealAction.addActionListener(this);
        hitAction.addActionListener(this);
        doubleAction.addActionListener(this);
        standAction.addActionListener(this);

		oneChip.addActionListener(this);
        fiveChip.addActionListener(this);
        tenChip.addActionListener(this);
        twentyFiveChip.addActionListener(this);
        hundredChip.addActionListener(this);
        		
        gamePanel = new GamePanel();
        gamePanel.setBackground(defaultTableColour);
		add(gamePanel);
        
        setVisible(true);
    }

	public void actionPerformed(ActionEvent evt)
    {
        String act = evt.getActionCommand();
        
        if (act.equals("$1"))
        {
            gamePanel.increaseBet(1);
        }
        else if (act.equals("$5"))
        {
            gamePanel.increaseBet(5);
        }
        else if (act.equals("$10"))
        {
            gamePanel.increaseBet(10);
        }
        else if (act.equals("$25"))
        {
            gamePanel.increaseBet(25);
        }
        else if (act.equals("$100"))
        {
            gamePanel.increaseBet(100);
        }
        else if (act.equals("Бооцоо тавих"))
        {
            gamePanel.newGame();
        }
        else if (act.equals("Шалгах"))
        {
            gamePanel.hit();
        }
        else if (act.equals("Үржүүлэх"))
        {
            gamePanel.playDouble();
        }
        else if (act.equals("Зогсох"))
        {
            gamePanel.stand();
        }
		
		gamePanel.updateValues();
	}
	
	public void componentResized(ComponentEvent e)
	{
	    int currentWidth = getWidth();
	    int currentHeight = getHeight();
	    
	    boolean resize = false;
	    
	    if (currentWidth < WIDTH)
	    {
	        resize = true;
	        currentWidth = WIDTH;
	    }
	    
	    if (currentHeight < HEIGHT)
	    {
	        resize = true;
	        currentHeight = HEIGHT;
	    }
	    
	    if (resize)
	    {
	        setSize(currentWidth, currentHeight);
	    }
	}
	
	public void componentMoved(ComponentEvent e) { }
	public void componentShown(ComponentEvent e) { }
	public void componentHidden(ComponentEvent e) { }
}