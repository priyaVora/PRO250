package project.chess960.implementation;

import project.template.PiecePawn;

import javax.swing.text.Position;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chess960Handler {
    //HashMap for Valid Possible Chess960 Board Setup
    private Map<String, PiecePosition> chess960Map;

    private String[][] whitePostion;
    private String[][] blackPosition;

    public Chess960Handler() {
        chess960Map = new HashMap<String, PiecePosition>();
        whitePostion = new String[2][8];
        whitePostion[0][0] = "rook_1_1";
        whitePostion[0][1] = "knight_1_1";
        whitePostion[0][2] = "bishop_1_1";
        whitePostion[0][3] = "king_1";
        whitePostion[0][4] = "WHITE_QUEEN";
        whitePostion[0][5] = "bishop_1_2";
        whitePostion[0][6] = "knight_1_2";
        whitePostion[0][7] = "rook_1_2";


        whitePostion[1][0] = "pawn_1_1";
        whitePostion[1][1] = "pawn_1_2";
        whitePostion[1][2] = "pawn_1_3";
        whitePostion[1][3] = "pawn_1_4";
        whitePostion[1][4] = "pawn_1_5";
        whitePostion[1][5] ="pawn_1_6";
        whitePostion[1][6] ="pawn_1_7";
        whitePostion[1][7] = "pawn_1_8";


        this.randomize();
    }


    public static void  main(String args[]) {
        Chess960Handler handler = new Chess960Handler();
        handler.printPosition_White();
        System.out.println(" ");
        handler.randomize();
        handler.setWhitePostion(shuffle(handler.getWhitePostion()));
        System.out.println(" ");
        handler.printPosition_White();
    }



    public String[][] owm_shuffle() {
        Random randome = new Random();

        String[][] tempArray = new String[whitePostion.length][whitePostion[0].length];

        tempArray[1][0] = whitePostion[1][0];
        tempArray[1][1] = whitePostion[1][1];
        tempArray[1][2] = whitePostion[1][2];
        tempArray[1][3] = whitePostion[1][3];
        tempArray[1][4] = whitePostion[1][4];
        tempArray[1][5] = whitePostion[1][5];
        tempArray[1][6] = whitePostion[1][6];
        tempArray[1][7] = whitePostion[1][7];



        for (int i = 0; i >= 0; i--) {
            for (int j = 0; j < whitePostion[i].length; j++) {
                int m = 0;
                int n = randome.nextInt(j+1);
                String temp = whitePostion[i][j];
                tempArray[i][j] = whitePostion[m][n];
                tempArray[m][n] = temp;
            }
        }
        return tempArray;
    }

    public static  String[][] shuffle(String[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                String temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
        return a;
    }
    public boolean checkValidLayout(String[][] layout) {
        return false;
    }

    public void checkRook_King_Placement() {

    }

    public void checkBishopPlacement() {

    }

    public void randomize() {
        String[] listOfPieces = new String[7];
        int counter = 0;
        for (int i = 1; i >= 0; i--){
            for (int j = 0; j < whitePostion[i].length; j++) {
                System.out.println(whitePostion[i][j]);
                listOfPieces[counter] = whitePostion[i][j];
            }
        }

        setWhitePostion(shuffle(getWhitePostion()));


        int indexX = 0;
        int indexY = 6;

        System.out.println("Randomize...");
        for (int i = 1; i >= 0; i--){
            for (int j = 0; j < whitePostion[i].length; j++) {

                System.out.print(i + ""+  j + " ");
                PiecePosition position = changeOriginalWhitePosition(i,j);
                //position = correctPositionWhite(position);
                chess960Map.put(whitePostion[i][j], position);
            }
            System.out.println(" ");
        }

        System.out.println("Print");
        for (Map.Entry<String, PiecePosition> entry : chess960Map.entrySet()) {
            String key = entry.getKey();
            PiecePosition value = entry.getValue();
            System.out.println(key);
            System.out.print(value.getX() + " ");
            System.out.print(value.getY() + "\n");
        }

    }

    public PiecePosition changeOriginalWhitePosition(int x, int y) {
        PiecePosition position = new PiecePosition();


        if(x == 1 && y == 0) {
            position.setX(0);
            position.setY(6);
        } else if(x == 1 && y == 1) {
            position.setX(1);
            position.setY(6);
        } else if(x ==1 && y == 2) {
            position.setX(2);
            position.setY(6);
        } else if(x ==1 && y == 3) {
            position.setX(3);
            position.setY(6);
        } else if(x ==1 && y == 4) {
            position.setX(4);
            position.setY(6);
        }else if(x ==1 && y ==5 ) {
            position.setX(5);
            position.setY(6);
        } else if(x ==1 && y ==6 ) {
            position.setX(6);
            position.setY(6);
        } else if(x == 1 && y ==7) {
            position.setX(7);
            position.setY(6);
        } else if(x ==0 && y ==0 ) {
            position.setX(0);
            position.setY(7);
        } else if(x ==0 && y ==1 ) {
            position.setX(1);
            position.setY(7);
        } else if(x ==0 && y ==2) {
            position.setX(2);
            position.setY(7);
        }else if(x == 0 && y ==3) {
            position.setX(3);
            position.setY(7);
        }else if(x ==0 && y ==4 ) {
            position.setX(4);
            position.setY(7);
        }else if(x ==0 && y ==5 ) {
            position.setX(5);
            position.setY(7);
        }else if(x ==0 && y ==6 ) {
            position.setX(6);
            position.setY(7);
        }else if(x ==0 && y ==7 ) {
            position.setX(7);
            position.setY(7);
        }
        return position;
    }

    public void findAllPossiblePaths() {
        
    }

    public void printPosition_White() {
        System.out.println("White position");

        for (int i = 1; i >= 0; i--){
            for (int j = 0; j < whitePostion[i].length; j++) {
                System.out.print(i + "" + j + " ");
                //System.out.print(whitePostion[i][j] + " ");
            }
            System.out.println(" ");
        }

        System.out.println(" -------  ");
        for (int i = 1; i >= 0; i--){
            for (int j = 0; j < whitePostion[i].length; j++) {
                System.out.print(whitePostion[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public void printPosition_Black() {
        System.out.println("Black position");
        for (int i = 1; i >= 0; i--){
            for (int j = 0; j < blackPosition[i].length; j++) {
                System.out.print(blackPosition[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public void setWhitePostion(String[][] whitePostion) {
        this.whitePostion = whitePostion;
    }

    public String[][] getWhitePostion() {
        return whitePostion;
    }

    public void setChess960Map(Map<String, PiecePosition> chess960Map) {
        this.chess960Map = chess960Map;
    }

    public Map<String, PiecePosition> getChess960Map() {
        return chess960Map;
    }
}
