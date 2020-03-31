public class Main {

    public static void main(String[] args) {
        Map map = new Map();
        map.print();
        while (true) {
            String white = "| \u26AA |";
            String black = "| \u26AB |";
            System.out.println("Black's turn :");
            boolean checkBlack = map.putDisk(black);
            map.print();
            System.out.println("White's turn :");
            boolean checkWhite = map.putDisk(white);
            if (checkBlack && checkWhite || map.fullMap()) {
                System.out.println("game is over !");
                map.findWinner();
                break;
            }
            map.print();

        }
    }
}
