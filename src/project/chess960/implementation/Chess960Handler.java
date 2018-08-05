package project.chess960.implementation;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import project.template.Piece;
import project.template.PiecePawn;

import javax.swing.text.Position;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Chess960Handler {
    //HashMap for Valid Possible Chess960 Board Setup
    private Map<String, PiecePosition> chess960Map;
    private Map<Integer, List<String>> validChessLayout;

    private String[][] whitePostion;
    private String[][] blackPosition;

    public Chess960Handler() {
        setOriginalBoard();
        validChessLayout = new HashMap<Integer, List<String>>();
        this.randomize();
    }

    /*
    * Sets the position with traditional chess rules
    * **/
    public void setOriginalBoard() {
        chess960Map = new HashMap<String, PiecePosition>();
        whitePostion = new String[2][8];
        whitePostion[0][0] = "rook_1_1";
        whitePostion[0][1] = "knight_1_1";
        whitePostion[0][2] = "bishop_1_1";
        whitePostion[0][4] = "king_1";
        whitePostion[0][3] = "WHITE_QUEEN";
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


        blackPosition = new String[2][8];
        blackPosition[0][0] = "rook_2_1";
        blackPosition[0][1] = "knight_2_1";
        blackPosition[0][2] = "bishop_2_1";
        blackPosition[0][4] = "king_2";
        blackPosition[0][3] = "BLACK_QUEEN";
        blackPosition[0][5] = "bishop_2_2";
        blackPosition[0][6] = "knight_2_2";
        blackPosition[0][7] = "rook_2_2";

        blackPosition[1][0] = "pawn_2_1";
        blackPosition[1][1] = "pawn_2_2";
        blackPosition[1][2] = "pawn_2_3";
        blackPosition[1][3] = "pawn_2_4";
        blackPosition[1][4] = "pawn_2_5";
        blackPosition[1][5] ="pawn_2_6";
        blackPosition[1][6] ="pawn_2_7";
        blackPosition[1][7] = "pawn_2_8";

    }

    public void mirrorBlackPie7ces(String[] board) {
        System.out.println("--------mirror starts----------");
        String[] black_pieces = new String[board.length];

        System.out.println(" ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " ");
        }
        System.out.println(" ");
       for (int i = 0; i < board.length; i++) {
           System.out.println(board[i]);
           if(board[i].contains("knight")) {
               if(board[i].equals("knight_1_1")) {
                    black_pieces[i] = "knight_2_1";
               } else {
                   black_pieces[i] = "knight_2_2";
               }

           } else if(board[i].contains("rook")) {
               if(board[i].equals("rook_1_1")) {
                   black_pieces[i] = "rook_2_1";
               } else {
                   black_pieces[i] = "rook_2_2";
               }

           } else if(board[i].contains("bishop")) {
               if(board[i].equals("bishop_1_1")) {
                   black_pieces[i] = "bishop_2_1";
               } else {
                   black_pieces[i] = "bishop_2_2";
               }

           } else if(board[i].contains("WHITE_QUEEN")) {
               black_pieces[i] = "BLACK_QUEEN";
           } else if(board[i].contains("king")) {
               black_pieces[i] = "king_2";
           }
       }
        System.out.println(" ");
        for (int i = 0; i < black_pieces.length; i++) {
            System.out.print(black_pieces[i] + " ");
        }



        int positionCounter = 0;
        for (int i = 0; i <= 0; i++) {
            for (int j = 0; j < blackPosition[i].length; j++) {
                blackPosition[i][j] = black_pieces[positionCounter];
                PiecePosition position = changeOriginalBlackPosition(i,j);
                chess960Map.put(black_pieces[positionCounter], position);
                positionCounter++;
            }
        }
        System.out.println("\n--------mirror ends----------");


    }

    public String[] randomPositionGenerator() {
        String[] board = new String[8];


        board = addBishop(board);
        board = addQueen_Knights(board);
        board = addRook_King_Rook(board);

        System.out.println();
        return board;
    }

    public String[] addBishop(String[] board) {
        String previous = "";
        for (int i = 0; i < 2; i++) {
            int randomPosition = (int) (Math.random() * 4) * 2;

            if (i == 1) {
                randomPosition++;
            }

            if(i == 0) {
                String[] bishop = {"bishop_1_2", "bishop_1_1"};
                Random random  = new Random();
                int randomIndex = random.nextInt(bishop.length);
                previous = bishop[randomIndex];
                board[randomPosition] = previous;

                if(previous.equals("bishop_1_2")) {
                    previous = "bishop_1_1";
                } else {
                    previous = "bishop_1_2";
                }

            } else {
                board[randomPosition] = previous;
            }
        }
        return board;
    }

    public String[] addQueen_Knights(String[] board) {
        String[] queenKnights = {"WHITE_QUEEN", "knight_1_1", "knight_1_2"};
        for (int i = 0; i < queenKnights.length; i++) {
            int randomPosition = (int) (Math.random() * (6 - i));

            while (board[randomPosition] != null) {
                randomPosition++;
            }

            board[randomPosition] = queenKnights[i];
        }
        return board;
    }


    public String[] addRook_King_Rook(String[] board) {
        String[] kingRooks = {"rook_1_1", "king_1", "rook_1_2"};
        int randomPosition= 0;
        for (int i = 0; i < kingRooks.length; i++) {
            while (board[randomPosition] != null) {
                randomPosition++;
            }

            board[randomPosition] = kingRooks[i];
            randomPosition++;
        }


        return board;
    }

    public static void  main(String args[]) {
        System.out.println("Chess 960");
        Chess960Handler handler = new Chess960Handler();
        handler.printPosition_White();
      //  handler.randomize();
        System.out.println(" ");
        System.out.println("\nBlack Chess 960 Positions:\n ");
        handler.printPosition_Black();
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
    public PiecePosition changeOriginalBlackPosition(int x, int y) {
        PiecePosition position = new PiecePosition();


        if(x == 1 && y == 0) {
            position.setX(0);
            position.setY(1);
        } else if(x == 1 && y == 1) {
            position.setX(1);
            position.setY(1);
        } else if(x ==1 && y == 2) {
            position.setX(2);
            position.setY(1);
        } else if(x ==1 && y == 3) {
            position.setX(3);
            position.setY(1);
        } else if(x ==1 && y == 4) {
            position.setX(4);
            position.setY(1);
        }else if(x ==1 && y ==5 ) {
            position.setX(5);
            position.setY(1);
        } else if(x ==1 && y ==6 ) {
            position.setX(6);
            position.setY(1);
        } else if(x == 1 && y ==7) {
            position.setX(7);
            position.setY(1);
        } else if(x ==0 && y ==0 ) {
            position.setX(0);
            position.setY(0);
        } else if(x ==0 && y ==1 ) {
            position.setX(1);
            position.setY(0);
        } else if(x ==0 && y ==2) {
            position.setX(2);
            position.setY(0);
        }else if(x == 0 && y ==3) {
            position.setX(3);
            position.setY(0);
        }else if(x ==0 && y ==4 ) {
            position.setX(4);
            position.setY(0);
        }else if(x ==0 && y ==5 ) {
            position.setX(5);
            position.setY(0);
        }else if(x ==0 && y ==6 ) {
            position.setX(6);
            position.setY(0);
        }else if(x ==0 && y ==7 ) {
            position.setX(7);
            position.setY(0);
        }
        return position;
    }

    public void randomize() {
        System.out.println(" ----------------- ");
        Random random = new Random();


        String[][] tempArray = new String[whitePostion.length][whitePostion[0].length];
        String[] board = randomPositionGenerator();
        int positionCounter= 0;
        for (int i = 1; i >= 0; i--) {
            for (int j = 0; j < whitePostion[i].length; j++) {
            if(i == 1) {
                tempArray[i][j] = whitePostion[i][j];
            } else {
                tempArray[i][j] = board[positionCounter];
                positionCounter++;
                }
            }
        }

        whitePostion = tempArray;

        chess960Map = new HashMap<>();
        mirrorBlackPie7ces(board);
        addToChessMap();
    }

    public void addToChessMap() {
        if(chess960Map != null) {
            for (int i = 1; i >= 0; i--) {
                for (int j = 0; j < whitePostion[i].length; j++) {
                    System.out.println(whitePostion[i][j]);
                    PiecePosition position = changeOriginalWhitePosition(i,j);
                    chess960Map.put(whitePostion[i][j], position);
                }
            }


        }
    }


    public void printPosition_White() {
        System.out.println("\nWhite position");
        for (int i = 1; i >= 0; i--) {
            for (int j = 0; j < whitePostion[i].length; j++) {
               System.out.print(i + "" + j + " ");
               //System.out.print(whitePostion[i][j] + " ");

            }
            System.out.println(" ");
        }
    }


    public  void printPosition_Black() {
        System.out.println("\nBlack Position");
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < blackPosition[i].length; j++) {
               //System.out.print(i + "" + j + " ");
                System.out.print(blackPosition[i][j] + " ");

            }
            System.out.println(" ");
        }
    }


    public void setChess960Map(Map<String, PiecePosition> chess960Map) {
        this.chess960Map = chess960Map;
    }

    public Map<String, PiecePosition> getChess960Map() {
        return chess960Map;
    }


}

