public class Main {

    public static void main(String[] args) {
        GameRules game = new GameRules();
        game.print();
        while (true) {
            String white = "| \u26AA |";
            String black = "| \u26AB |";
            System.out.println("Black's turn :");
            boolean checkBlack = game.putDisk(black);
            game.print();
            System.out.println("White's turn :");
            boolean checkWhite = game.putDisk(white);
            if (checkBlack && checkWhite || game.checkFullMap()) {
                System.out.println("game is over !");
                game.findWinner();
                game.print();
                break;
            }
            game.print();
        }
    }
}
