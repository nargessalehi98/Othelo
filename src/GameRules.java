import java.util.ArrayList;
import java.util.Scanner;

public class GameRules {
    private Map map;
    private String empty;
    private String white;
    private String black;

    public GameRules() {
        empty = "|    |";
        white = "| ⚪ |";
        black = "| ⚫ |";
        map = new Map();

    }

    public String checkColor(int row, int col) {
        ArrayList<String> temp = map.getRow(row);
        if (temp.get(col).equals(white))
            return white;
        if (temp.get(col).equals(black))
            return black;
        else
            return empty;
    }

    public boolean checkFullMap() {
        boolean check = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (checkColor(row, col).equals(empty))
                    check = false;
            }
        }
        return check;
    }

    public boolean checkPutDisk(int row, int col, String Color) {
        int counter = 0;
        for (int plusRow = -1; plusRow < 2; plusRow++) {
            for (int plusCol = -1; plusCol < 2; plusCol++) {
                int Row = row + plusRow;
                int Col = col + plusCol;
                if (!(plusCol == 0 && plusRow == 0)) {
                    if (Row >= 0 && Row < 8 && Col >= 0 && Col < 8) {
                        if (!checkColor(Row, Col).equals(Color) && !checkColor(Row, Col).equals(empty)) {
                            while (true) {
                                Row += plusRow;
                                Col += plusCol;
                                if (Row >= 0 && Row < 8 && Col >= 0 && Col < 8) {
                                    if (checkColor(Row, Col).equals(Color)) {
                                        counter++;
                                        break;
                                    }
                                } else
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return counter != 0;
    }

    public boolean checkPossibleMove(String Color) {
        int counter = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (checkColor(row, col).equals(empty)) {
                    if (checkPutDisk(row, col, Color)) {
                        counter++;
                    }
                }
            }
        }
        return counter != 0;
    }

    public void findWinner() {
        int counterBlack = 0;
        int counterWhite = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (checkColor(row, col).equals(black))
                    counterBlack++;
                if (checkColor(row, col).equals(white))
                    counterWhite++;
            }
        }
        if (counterBlack > counterWhite)
            System.out.println("Black won ! Number of disks: " + counterBlack);
        if (counterWhite > counterBlack)
            System.out.println("White won ! Number of disks: " + counterWhite);
        if (counterBlack == counterWhite)
            System.out.println("Equal !");
    }

    public void checkNeighbor(int row, int col) {
        for (int plusRow = -1; plusRow < 2; plusRow++) {
            for (int plusCol = -1; plusCol < 2; plusCol++) {
                String main = checkColor(row, col);
                int Row = row + plusRow;
                int Col = col + plusCol;
                if (!(plusCol == 0 && plusRow == 0)) {
                    if (Row >= 0 && Row < 8 && Col >= 0 && Col < 8) {
                        if (!checkColor(Row, Col).equals(main) && !checkColor(Row, Col).equals(empty)) {
                            boolean check = true;
                            while (true) {
                                if (check) {
                                    Row += plusRow;
                                    Col += plusCol;
                                    if (Row >= 0 && Row < 8 && Col >= 0 && Col < 8) {
                                        if (checkColor(Row, Col).equals(main)) {
                                            while (true) {
                                                Row -= plusRow;
                                                Col -= plusCol;
                                                if (Row >= 0 && Row < 8 && Col >= 0 && Col < 8) {
                                                    if (checkColor(Row, Col).equals(main)) {
                                                        check = false;
                                                        break;
                                                    } else {
                                                        ArrayList<String> temp = map.getRow(Row);
                                                        temp.remove(Col);
                                                        temp.add(Col, main);
                                                        map.removeRow(Row);
                                                        map.addRow(Row, temp);
                                                    }
                                                }
                                            }
                                        }
                                        if (checkColor(Row, Col).equals(empty))
                                            break;
                                    } else
                                        break;
                                } else
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean putDisk(String Color) {
        if (checkPossibleMove(Color)) {
            System.out.println("Enter Number of row and letter of column :");
            Scanner scan = new Scanner(System.in);
            int row = scan.nextInt();
            row = row - 1;
            String colLetter = scan.next();
            int col = map.getColNum(colLetter);
            if (col != 8) {
                if (checkPutDisk(row, col, Color)) {
                    ArrayList<String> temp = map.getRow(row);
                    if (temp.get(col).equals(empty)) {
                        temp.remove(col);
                        temp.add(col, Color);
                        map.removeRow(row);
                        map.addRow(row, temp);
                    }
                    checkNeighbor(row, col);
                } else {
                    System.out.println("choose right position !");
                    putDisk(Color);
                }
            } else {
                System.out.println("wrong input !");
                putDisk(Color);
            }
            return false;
        } else {
            System.out.println("pass !");
            return true;
        }
    }


    public void print() {
        map.print();
    }
}
