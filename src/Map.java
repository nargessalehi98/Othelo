import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class Map extends GameRules {
    private ArrayList<ArrayList> map;
    private String empty;
    private String white;
    private String black;

    public Map() {
        empty = "|    |";
        white = "| ⚪ |";
        black = "| ⚫ |";
        map = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            ArrayList<String> cul = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                if (i == 3 && j == 3)
                    cul.add("| \u26AA |");
                else if (i == 4 && j == 3)
                    cul.add("| \u26AB |");
                else if (i == 3 && j == 4)
                    cul.add("| \u26AB |");
                else if (i == 4 && j == 4)
                    cul.add("| \u26AA |");
                else
                    cul.add("|    |");

            }
            map.add(cul);
        }
    }

    public boolean fullMap() {
        boolean check = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (checkColor(row, col).equals(empty))
                    check = false;
            }
        }
        return check;
    }

    public int getColNum(String c) {
        if (c.equals("A"))
            return 0;
        if (c.equals("B"))
            return 1;
        if (c.equals("C"))
            return 2;
        if (c.equals("D"))
            return 3;
        if (c.equals("E"))
            return 4;
        if (c.equals("F"))
            return 5;
        if (c.equals("G"))
            return 6;
        if (c.equals("H"))
            return 7;
        else
            return 8;
    }

    public String checkColor(int row, int col) {
        ArrayList<String> temp = map.get(row);
        if (temp.get(col).equals(white))
            return white;
        if (temp.get(col).equals(black))
            return black;
        else
            return empty;
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

    public boolean putDisk(String Color) {
        if (checkPossibleMove(Color)) {
            System.out.println("Enter Number of row and letter of column :");
            Scanner scan = new Scanner(System.in);
            int row = scan.nextInt();
            row = row - 1;
            String colLetter = scan.next();
            int col = getColNum(colLetter);
            if (col != 8) {
                if (checkPutDisk(row, col, Color)) {
                    ArrayList<String> temp = map.get(row);
                    if (temp.get(col).equals(empty)) {
                        temp.remove(col);
                        temp.add(col, Color);
                        map.remove(row);
                        map.add(row, temp);
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
                                                        ArrayList<String> temp = map.get(Row);
                                                        temp.remove(Col);
                                                        temp.add(Col, main);
                                                        map.remove(Row);
                                                        map.add(Row, temp);
                                                    }
                                                }
                                            }
                                        }
                                        if (checkColor(Row, Col).equals(empty)) {
                                           // check = false;
                                            break;
                                        }
//                                        if (Row < 0 || Row > 7 || Col < 0 || Col > 7) {
//                                            check = false;
//                                            break;
//                                        }
                                    }
                                } else
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }


    public void print() {
        System.out.println("    A      B      C      D      E      F       G     H   ");
        System.out.println("  +----+ +----+ +----+ +----+ +----+ +----+ +----+ +----+");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1);
            System.out.println(" " + (map.get(i).toString()).replace("[", "")
                    .replace("]", "").replace(",", ""));
            System.out.println("  +----+ +----+ +----+ +----+ +----+ +----+ +----+ +----+");
        }
    }
}

