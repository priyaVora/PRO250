package project.chess960.implementation;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class Chess960Handler {
    //HashMap for Valid Possible Chess960 Board Setup
    private HashMap<String, String[][]> chess960Map;

    private String[][] whitePostion;
    private String[][] blackPosition;

    public Chess960Handler() {
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

    }


    public static void  main(String args[]) {
        Chess960Handler handler = new Chess960Handler();
        handler.printPosition_White();
    }

    public void randomize() {

    }

    public void printPosition_White() {
        System.out.println("White position");
        for (int i = 1; i >= 0; i--){
            for (int j = 0; j < whitePostion[i].length; j++) {
               System.out.print(whitePostion[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public void printPosition_Black() {

    }

    public void setChess960Map(HashMap<String, String[][]> chess960Map) {
        this.chess960Map = chess960Map;
    }

    public HashMap<String, String[][]> getChess960Map() {
        return chess960Map;
    }
}
