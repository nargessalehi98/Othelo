public class Main {

    public static void main(String[] args) {
        Map map = new Map();
        map.print();
        while (true) {
            String white = "| \u26AA |";
            String black = "| \u26AB |";
            System.out.println("Black's turn");
            map.putDisk(black);
            map.print();
            System.out.println("White's turn");
            map.putDisk(white);
            map.print();
        }
    }
}
