import java.util.ArrayList;

public class Map {
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

    public ArrayList<String> getRow(int index){
        return map.get(index);
    }

    public void removeRow(int index){
        map.remove(index);

    }

    public void addRow(int index,ArrayList temp){
        map.add(index,temp);
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
