package classes;

import classes.hands.DealerCardHand;
import classes.hands.PlayerCardHand;

public class GameSerializer {
    public static boolean isGameOver;
    public static boolean isBetPlaced;
    public static boolean isBankrupt;
    public static boolean isCanDouble;

    public static int totalCard;
    public static double totalBet;
    public static double totalWallet;
    public static String dealerName;
    public static String playerName;
    public static String announcement;
    public static boolean showAllDealerCards;
    public static PlayerCardHand playerCardHand;
    public static DealerCardHand dealerCardHand;
}
