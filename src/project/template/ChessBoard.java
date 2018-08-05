package project.template;


import java.util.*;

import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import project.chess960.implementation.Chess960Handler;
import project.chess960.implementation.PiecePosition;

public class ChessBoard extends Pane {
	// private fields
	private int boardWidth = 8;
	private int boardHeight = 8;
	private int[][] board;

	private Piece[][] pieces;
	private Window[][] windows;

	// pieceName_color_number
	private PieceRook rook_2_1;
	private PieceKnight knight_2_1;
	private PieceBishop bishop_2_1;
	private PieceQueen queen_2;
	private PieceKing king_2;
	private PieceBishop bishop_2_2;
	private PieceKnight knight_2_2;
	private PieceRook rook_2_2;
	private PiecePawn pawn_2_1;
	private PiecePawn pawn_2_2;
	private PiecePawn pawn_2_3;
	private PiecePawn pawn_2_4;
	private PiecePawn pawn_2_5;
	private PiecePawn pawn_2_6;
	private PiecePawn pawn_2_7;
	private PiecePawn pawn_2_8;

	private PieceRook rook_1_1;
	private PieceKnight knight_1_1;
	private PieceBishop bishop_1_1;
	private PieceQueen queen_1;
	private PieceKing king_1;
	private PieceBishop bishop_1_2;
	private PieceKnight knight_1_2;
	private PieceRook rook_1_2;
	private PiecePawn pawn_1_1;
	private PiecePawn pawn_1_2;
	private PiecePawn pawn_1_3;
	private PiecePawn pawn_1_4;
	private PiecePawn pawn_1_5;
	private PiecePawn pawn_1_6;
	private PiecePawn pawn_1_7;
	private PiecePawn pawn_1_8;

	private Piece selectedPiece = null;

	private StatusBar statusBar = null;

	// GameLogic linked variable
	private GameLogic gameLogic = new GameLogic();
	public List<Piece> checkPieces = new ArrayList<Piece>();
	public List<Piece> saviorPieces = new ArrayList<Piece>();
	public int	playerOneRook = 2;
	public int	playerOneBishopLightSquare = 1;
	public int	playerOneBishopDarkSquare = 1;
	public int	playerOneKnight = 2;
	public int	playerOneQueen = 1;
	public int	playerOnePawn = 8;
	public int	playerTwoRook = 2;
	public int	playerTwoBishopLightSquare = 1;
	public int	playerTwoBishopDarkSquare = 1;
	public int	playerTwoKnight = 2;
	public int	playerTwoQueen = 1;
	public int	playerTwoPawn = 8;
	private Alert alert;

	private Rectangle background;
	private double cell_width;
	private double cell_height;
	private int current_player;
	private boolean isBlack = false;
	public boolean checkmate = false;
	public boolean checkState = false;
	public boolean stalemate = false;

	private final int EMPTY = 0;
	private final int PlayerWhite = 1;
	private final int PlayerBlack = 2;

	private Timer timer;

	private String currentGameMode;
	private Chess960Handler handler;


	public ChessBoard(StatusBar newStatusBar, String gameMode) {
		// initalize the board: background, data structures, inital layout of
		// pieces
		statusBar = newStatusBar;
		if(statusBar != null) {
			statusBar.whitePlayerAlert.setText("White Player turn");
			statusBar.blackPlayerAlert.setText("");
			statusBar.whitePlayerTimer.setText("White timer: 15:00");
			statusBar.blackPlayerTimer.setText("Black timer: 15:00");
		}

		currentGameMode = gameMode;
		System.out.println("Current Game Mode for Chess: "+ currentGameMode);

		if(statusBar != null) {
			background = new Rectangle();
			background.setFill(Color.WHITE);

			getChildren().add(background);
		}

		// initialize board array to the correct size
		board = new int[boardWidth][boardHeight];

		// initialize pieces array to the correct size
		pieces = new Piece[boardWidth][boardHeight];

		// initialize windows array to the correct size
		windows = new Window[boardWidth][boardHeight];

		handler = new Chess960Handler();

		// for loop to populate all arrays to default values and add the windows
		// to the board
		for (int i = 0; i < 8; i++) {
			if(i%2 == 0 || i ==0){
				isBlack =false;
			}
			else 
				isBlack = true;
			for (int j = 0; j < 8; j++) {
				board[i][j] = EMPTY;
				if(isBlack){
					windows[i][j] = new Window(0); //Black 
					isBlack=false;
				}else{
					windows[i][j] = new Window(1); //White
					isBlack = true; 
				}
				getChildren().add(windows[i][j]);
				pieces[i][j] = null;
			}
		}

		// set the current player to white
		current_player = PlayerWhite;
		initPiece();
		timer = new Timer(this);
		timer.timeline.setCycleCount(Timeline.INDEFINITE);
		timer.timeline.play();
		timer.playerTurn = current_player;
	}


		
	public void initPiece()
	{
		// Initialize the pieces and put it on the board
		// BLACK Pieces
		rook_2_1 = new PieceRook(2, 0, 0); 
		knight_2_1 = new PieceKnight(2, 1, 0);
		bishop_2_1 = new PieceBishop(2, 2, 0);
		queen_2 = new PieceQueen(2, 3, 0); 
		king_2 = new PieceKing(2, 4, 0);
		bishop_2_2 = new PieceBishop(2, 5, 0);
		knight_2_2 = new PieceKnight(2, 6, 0);
		rook_2_2 = new PieceRook(2, 7, 0);
		pawn_2_1 = new PiecePawn(2, 0, 1);
		pawn_2_2 = new PiecePawn(2, 1, 1);
		pawn_2_3 = new PiecePawn(2, 2, 1);
		pawn_2_4 = new PiecePawn(2, 3, 1);
		pawn_2_5 = new PiecePawn(2, 4, 1);
		pawn_2_6 = new PiecePawn(2, 5, 1);
		pawn_2_7 = new PiecePawn(2, 6, 1);
		pawn_2_8 = new PiecePawn(2, 7, 1);
		
		//WHITE Pieces
		rook_1_1 = new PieceRook(1, 0, 7); 
		knight_1_1 = new PieceKnight(1, 1, 7);
		bishop_1_1 = new PieceBishop(1, 2, 7);
		queen_1 = new PieceQueen(1, 3, 7);
		king_1 = new PieceKing(1, 4, 7);
		bishop_1_2 = new PieceBishop(1, 5, 7);
		knight_1_2 = new PieceKnight(1, 6, 7);
		rook_1_2 = new PieceRook(1, 7, 7);
		pawn_1_1 = new PiecePawn(1, 0, 6);
		pawn_1_2 = new PiecePawn(1, 1, 6);
		pawn_1_3 = new PiecePawn(1, 2, 6);
		pawn_1_4 = new PiecePawn(1, 3, 6);
		pawn_1_5 = new PiecePawn(1, 4, 6);
		pawn_1_6 = new PiecePawn(1, 5, 6);
		pawn_1_7 = new PiecePawn(1, 6, 6);
		pawn_1_8 = new PiecePawn(1, 7, 6);
		
		pieces[0][0] = rook_2_1;
		pieces[1][0] = knight_2_1;
		pieces[2][0] = bishop_2_1;
		pieces[3][0] = queen_2;
		pieces[4][0] = king_2;
		pieces[5][0] = bishop_2_2;
		pieces[6][0] = knight_2_2;
		pieces[7][0] = rook_2_2;
		
		pieces[0][1] = pawn_2_1;
		pieces[1][1] = pawn_2_2;
		pieces[2][1] = pawn_2_3;
		pieces[3][1] = pawn_2_4;
		pieces[4][1] = pawn_2_5;
		pieces[5][1] = pawn_2_6;
		pieces[6][1] = pawn_2_7;
		pieces[7][1] = pawn_2_8;
		
		for (int y = 2; y < 6; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				pieces[x][y] = null;
			}
		}
		
		pieces[0][6] = pawn_1_1;
		pieces[1][6] = pawn_1_2;
		pieces[2][6] = pawn_1_3;
		pieces[3][6] = pawn_1_4;
		pieces[4][6] = pawn_1_5;
		pieces[5][6] = pawn_1_6;
		pieces[6][6] = pawn_1_7;
		pieces[7][6] = pawn_1_8;

		pieces[0][7] = rook_1_1;
		pieces[1][7] = knight_1_1;
		pieces[2][7] = bishop_1_1;
		pieces[3][7] = queen_1;
		pieces[4][7] = king_1;
		pieces[5][7] = bishop_1_2;
		pieces[6][7] = knight_1_2;
		pieces[7][7] = rook_1_2;
		
		for (int y = 0; y < boardHeight; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				if (y == 0 || y == 1)
					board[x][y] = 2;
				else if (y == 6 || y == 7)
					board[x][y] = 1;
				else
					board[x][y] = 0;
			}
		}



		for(int i = 0; i < 8; i++){
			getChildren().addAll(pieces[i][0].getImage(), pieces[i][1].getImage(), pieces[i][6].getImage(), pieces[i][7].getImage());
		}
	}


	/*
	* initPieceChess960: Initializes the Board corresponding to Chess 960 Rules
	*/
	public void initPieceChess960()
	{
		handler.randomize();
		PiecePosition rook1Position = handler.getChess960Map().get("rook_1_1");

		PiecePosition knight1Position = handler.getChess960Map().get("knight_1_1");

		PiecePosition bishop1Position = handler.getChess960Map().get("bishop_1_1");

		PiecePosition queen1Position = handler.getChess960Map().get("WHITE_QUEEN");

		PiecePosition king1Position = handler.getChess960Map().get("king_1");

		PiecePosition bishop2Position = handler.getChess960Map().get("bishop_1_2");

		PiecePosition knight2Position = handler.getChess960Map().get("knight_1_2");

		PiecePosition rook2Position = handler.getChess960Map().get("rook_1_2");


		PiecePosition rook2Position1 = handler.getChess960Map().get("rook_2_1");

		PiecePosition knight2Position1 = handler.getChess960Map().get("knight_2_1");

		PiecePosition bishop2Position1 = handler.getChess960Map().get("bishop_2_1");

		PiecePosition queen2Position = handler.getChess960Map().get("BLACK_QUEEN");

		PiecePosition king2Position = handler.getChess960Map().get("king_2");

		PiecePosition bishop2Position2 = handler.getChess960Map().get("bishop_2_2");

		PiecePosition knight2Position2 = handler.getChess960Map().get("knight_2_2");

		PiecePosition rook2Position2 = handler.getChess960Map().get("rook_2_2");

		// Initialize the pieces and put it on the board
		// BLACK Pieces
		rook_2_1 = new PieceRook(2, rook2Position1.getX(), rook2Position.getY());
		knight_2_1 = new PieceKnight(2, knight2Position1.getX(), knight2Position1.getY());
		bishop_2_1 = new PieceBishop(2, bishop2Position1.getX(), bishop2Position1.getY());
		queen_2 = new PieceQueen(2, queen2Position.getX(), queen2Position.getY());
		king_2 = new PieceKing(2, king2Position.getX(), king2Position.getY());
		bishop_2_2 = new PieceBishop(2, bishop2Position2.getX(), bishop2Position2.getY());
		knight_2_2 = new PieceKnight(2, knight2Position2.getX(), knight2Position2.getY());
		rook_2_2 = new PieceRook(2, rook2Position2.getX(), rook2Position2.getY());
		pawn_2_1 = new PiecePawn(2, 0, 1);
		pawn_2_2 = new PiecePawn(2, 1, 1);
		pawn_2_3 = new PiecePawn(2, 2, 1);
		pawn_2_4 = new PiecePawn(2, 3, 1);
		pawn_2_5 = new PiecePawn(2, 4, 1);
		pawn_2_6 = new PiecePawn(2, 5, 1);
		pawn_2_7 = new PiecePawn(2, 6, 1);
		pawn_2_8 = new PiecePawn(2, 7, 1);

		//WHITE Pieces
		rook_1_1 = new PieceRook(1, rook1Position.getX(), rook1Position.getY());
		knight_1_1 = new PieceKnight(1, knight1Position.getX(), knight1Position.getY());
		bishop_1_1 = new PieceBishop(1, bishop1Position.getX(), bishop1Position.getY());
		queen_1 = new PieceQueen(1, queen1Position.getX(), queen1Position.getY());
		king_1 = new PieceKing(1, king1Position.getX(), king1Position.getY());
		bishop_1_2 = new PieceBishop(1, bishop2Position.getX(), bishop2Position.getY());
		knight_1_2 = new PieceKnight(1, knight2Position.getX(), knight2Position.getY());
		rook_1_2 = new PieceRook(1, rook2Position.getX(), rook2Position.getY());


		pawn_1_1 = new PiecePawn(1, 0, 6);
		pawn_1_2 = new PiecePawn(1, 1, 6);
		pawn_1_3 = new PiecePawn(1, 2, 6);
		pawn_1_4 = new PiecePawn(1, 3, 6);
		pawn_1_5 = new PiecePawn(1, 4, 6);
		pawn_1_6 = new PiecePawn(1, 5, 6);
		pawn_1_7 = new PiecePawn(1, 6, 6);
		pawn_1_8 = new PiecePawn(1, 7, 6);

		pieces[rook2Position1.getX()][rook2Position1.getY()] = rook_2_1;
		pieces[knight2Position1.getX()][knight2Position1.getY()] = knight_2_1;
		pieces[bishop2Position1.getX()][bishop2Position1.getY()] = bishop_2_1;
		pieces[queen2Position.getX()][queen2Position.getY()] = queen_2;
		pieces[king2Position.getX()][king2Position.getY()] = king_2;
		pieces[bishop2Position2.getX()][bishop2Position2.getY()] = bishop_2_2;
		pieces[knight2Position2.getX()][knight2Position2.getY()] = knight_2_2;
		pieces[rook2Position2.getX()][rook2Position2.getY()] = rook_2_2;

		pieces[0][1] = pawn_2_1;
		pieces[1][1] = pawn_2_2;
		pieces[2][1] = pawn_2_3;
		pieces[3][1] = pawn_2_4;
		pieces[4][1] = pawn_2_5;
		pieces[5][1] = pawn_2_6;
		pieces[6][1] = pawn_2_7;
		pieces[7][1] = pawn_2_8;

		for (int y = 2; y < 6; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				pieces[x][y] = null;
			}
		}

		pieces[0][6] = pawn_1_1;
		pieces[1][6] = pawn_1_2;
		pieces[2][6] = pawn_1_3;
		pieces[3][6] = pawn_1_4;
		pieces[4][6] = pawn_1_5;
		pieces[5][6] = pawn_1_6;
		pieces[6][6] = pawn_1_7;
		pieces[7][6] = pawn_1_8;

		pieces[rook1Position.getX()][rook1Position.getY()] = rook_1_1;
		pieces[knight1Position.getX()][knight2Position.getY()] = knight_1_1;
		pieces[queen1Position.getX()][queen1Position.getY()] = queen_1;
		pieces[bishop1Position.getX()][bishop1Position.getY()] = bishop_1_1;
		pieces[king1Position.getX()][king1Position.getY()] = king_1;
		pieces[bishop2Position.getX()][bishop2Position.getY()] = bishop_1_2;
		pieces[knight2Position.getX()][knight2Position.getY()] = knight_1_2;
		pieces[rook2Position.getX()][rook2Position.getY()] = rook_1_2;

		for (int y = 0; y < boardHeight; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				if (y == 0 || y == 1)
					board[x][y] = 2;
				else if (y == 6 || y == 7)
					board[x][y] = 1;
				else
					board[x][y] = 0;
			}
		}

		for(int i = 0; i < 8; i++){
			getChildren().addAll(pieces[i][0].getImage(), pieces[i][1].getImage(), pieces[i][6].getImage(), pieces[i][7].getImage());
		}
	}


	// resize method
	@Override
	public void resize(double width, double height) {
		// call the superclass resize method
		super.resize(width, height);

		// resize the rectangle to take the full size of the widget
		background.setWidth(width);
		background.setHeight(height);

		// calculate the width and height of a cell in which a windows and a
		// piece will sit
		cell_width = width / 8.0;
		cell_height = height / 8.0;

		// nested for loop to reset the sizes and positions of all pieces that
		// were already placed
		// and update the position of the windows in the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != 0) {
					pieces[i][j].relocate(i * cell_width, j * cell_height);
					pieces[i][j].resize(cell_width, cell_height);
				}
				windows[i][j].relocate(i * cell_width, j * cell_height);
				windows[i][j].resize(cell_width, cell_height);
			}
		}
	}

	// reset game method
	public void resetGame() {
		if(currentGameMode.equals("Traditional")) {
			timer.playerTurn = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					board[i][j] = 0;
					if (pieces[i][j] != null)
						getChildren().remove(pieces[i][j].getImage());
					getChildren().remove(pieces[i][j]);
					pieces[i][j] = null;
				}
			}
			current_player = PlayerWhite;
			initPiece();
			for(int i = 0; i < 8; i++){
				pieces[i][0].resetPiece();
				pieces[i][1].resetPiece();
				pieces[i][6].resetPiece();
				pieces[i][7].resetPiece();
			}
			unhighlightWindow();
			statusBar.whitePlayerAlert.setText("White Player turn");
			statusBar.blackPlayerAlert.setText("");
			statusBar.whitePlayerTimer.setText("White timer: 15:00");
			statusBar.blackPlayerTimer.setText("Black timer: 15:00");
			statusBar.winner.setText("");
			checkmate = false;
			checkState = false;
			stalemate = false;
			selectedPiece = null;
			playerOneRook = 2;
			playerOneBishopLightSquare = 1;
			playerOneBishopDarkSquare = 1;
			playerOneKnight = 2;
			playerOneQueen = 1;
			playerOnePawn = 8;
			playerTwoRook = 2;
			playerTwoBishopLightSquare = 1;
			playerTwoBishopDarkSquare = 1;
			playerTwoKnight = 2;
			playerTwoQueen = 1;
			playerTwoPawn = 8;
			checkPieces.clear();
			saviorPieces.clear();
			timer.timeIsOver = false;
			timer.whiteTimer = 900;
			timer.blackTimer = 900;
			timer.playerTurn = current_player;
			timer.timeline.play();
		} else if(currentGameMode.equals("Chess 960")){
			timer.playerTurn = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					board[i][j] = 0;
					if (pieces[i][j] != null)
						getChildren().remove(pieces[i][j].getImage());
					getChildren().remove(pieces[i][j]);
					pieces[i][j] = null;
				}
			}
			current_player = PlayerWhite;
			initPieceChess960();
			for(int i = 0; i < 8; i++){
				pieces[i][0].resetPiece();
				pieces[i][1].resetPiece();
				pieces[i][6].resetPiece();
				pieces[i][7].resetPiece();
			}
			unhighlightWindow();
			statusBar.whitePlayerAlert.setText("White Player turn");
			statusBar.blackPlayerAlert.setText("");
			statusBar.whitePlayerTimer.setText("White timer: 15:00");
			statusBar.blackPlayerTimer.setText("Black timer: 15:00");
			statusBar.winner.setText("");
			checkmate = false;
			checkState = false;
			stalemate = false;
			selectedPiece = null;
			playerOneRook = 2;
			playerOneBishopLightSquare = 1;
			playerOneBishopDarkSquare = 1;
			playerOneKnight = 2;
			playerOneQueen = 1;
			playerOnePawn = 8;
			playerTwoRook = 2;
			playerTwoBishopLightSquare = 1;
			playerTwoBishopDarkSquare = 1;
			playerTwoKnight = 2;
			playerTwoQueen = 1;
			playerTwoPawn = 8;
			checkPieces.clear();
			saviorPieces.clear();
			timer.timeIsOver = false;
			timer.whiteTimer = 900;
			timer.blackTimer = 900;
			timer.playerTurn = current_player;
			timer.timeline.play();
		}

	}
	
	// select piece method
	public void selectPiece(final double x, final double y){
		System.out.println("Select Method Called");
		int indexX = (int) (x/ cell_width);
		int indexY = (int) (y/ cell_height);

		System.out.println(indexX);
		System.out.println(indexY);
		
		if (!checkmate && !stalemate && !timer.timeIsOver)
		{
			System.out.print("Select no checkmate, select no stalemate, "+ "Select no timer");
			if (windows[indexX][indexY].isHighlighted())
			{
				System.out.print("Select window is highlighted");
				System.out.println(windows[indexX][indexY]);
				System.out.println(windows[indexX][indexY].isHighlighted());
				movePiece(x, y);
				unhighlightWindow();
				selectedPiece = null;
			}
			else
			{
				System.out.println("Window is not highlighted");
				//add condition to know if the player is in check
				if(board[indexX][indexY] == current_player){
					unhighlightWindow();
					pieces[indexX][indexY].SelectPiece(this);
					selectedPiece = pieces[indexX][indexY];
				}
			}
		}
	}
	
	// move piece method
	public void movePiece(final double x, final double y){
		int indexX = (int) (x/ cell_width);
		int indexY = (int) (y/ cell_height);
		System.out.println("Moved Method Called");

		// add a condition to know if the player can put his piece there
		selectedPiece.MovePiece(this, indexX, indexY);
		// change isFirstTime (pas beau, il faudrait le faire qu'une fois)
		selectedPiece.setFirstTime(false);
		// don't forget to change the player
		if (current_player == PlayerWhite)
		{
			current_player = PlayerBlack;
			statusBar.whitePlayerAlert.setText("");
			checkState = false;
			for(Iterator<Piece> piece = saviorPieces.iterator(); piece.hasNext(); ) {
			    Piece item = piece.next();
			    item.isASavior = false;
			}
			if (gameLogic.isCheck(this, king_2.xPos, king_2.yPos, current_player, true))
			{

				checkPieces.clear();
				saviorPieces.clear();
				checkState = true;
				gameLogic.findAllCheckPieces(this, king_2.xPos, king_2.yPos, current_player);
				if (gameLogic.isCheckmate(this, king_2.xPos, king_2.yPos, current_player))
				{
					checkmate = true;
					statusBar.blackPlayerAlert.setText("Black player is in checkmate");
					statusBar.winner.setText("White player won !");
				}
				else
					statusBar.blackPlayerAlert.setText("Black player is in check");
			}
			else if (gameLogic.isStalemate(this, king_2, current_player))
				statusBar.winner.setText("Stalemate !");
			else
				statusBar.blackPlayerAlert.setText("Black Player turn");
		}
		else
		{
			current_player = PlayerWhite;
			statusBar.blackPlayerAlert.setText("");
			checkState = false;
			for(Iterator<Piece> piece = saviorPieces.iterator(); piece.hasNext(); ) {
			    Piece item = piece.next();
			    item.isASavior = false;
			}
			if (gameLogic.isCheck(this, king_1.xPos, king_1.yPos, current_player, true))
			{
				checkPieces.clear();
				saviorPieces.clear();
				checkState = true;
				gameLogic.findAllCheckPieces(this, king_1.xPos, king_1.yPos, current_player);
				if (gameLogic.isCheckmate(this, king_1.xPos, king_1.yPos, current_player))
				{
					checkmate = true;
					statusBar.whitePlayerAlert.setText("White player is in checkmate");
					statusBar.winner.setText("Black player won !");
				}
				else
					statusBar.whitePlayerAlert.setText("White player is in check");
			}
			else if (gameLogic.isStalemate(this, king_1, current_player))
				statusBar.winner.setText("Stalemate !");
			else
				statusBar.whitePlayerAlert.setText("White Player turn");
		}
		timer.playerTurn = current_player;
	}
	
	public void createPromotePiece(Piece piece)
	{
		Piece promotedPiece;
		        
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Promote a piece");
		alert.setHeaderText("You can promote your pawn into another piece");
		alert.setContentText("Choose one of the following piece");

		ButtonType buttonRook = new ButtonType("Rook");
		ButtonType buttonKnight = new ButtonType("Knight");
		ButtonType buttonBishop = new ButtonType("Bishop");
		ButtonType buttonQueen = new ButtonType("Queen");

		alert.getButtonTypes().setAll(buttonRook, buttonKnight, buttonBishop, buttonQueen);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonRook){
			promotedPiece = new PieceRook(piece.type, piece.xPos, piece.yPos);
			getChildren().remove(piece.getImage());
			getChildren().add(promotedPiece.getImage());
			pieces[piece.xPos][piece.yPos] = promotedPiece;
			if (piece.type == 1)
				playerOneRook++;
			else
				playerTwoRook++;
		}
		else if (result.get() == buttonKnight) {
			promotedPiece = new PieceKnight(piece.type, piece.xPos, piece.yPos);
			getChildren().remove(piece.getImage());
			getChildren().add(promotedPiece.getImage());
			pieces[piece.xPos][piece.yPos] = promotedPiece;
			if (piece.type == 1)
				playerOneKnight++;
			else
				playerTwoKnight++;
		}
		else if (result.get() == buttonBishop) {
			promotedPiece = new PieceBishop(piece.type, piece.xPos, piece.yPos);
			getChildren().remove(piece.getImage());
			getChildren().add(promotedPiece.getImage());
			pieces[piece.xPos][piece.yPos] = promotedPiece;
			if (piece.type == 1)
			{
				if ((piece.xPos + piece.yPos) % 2 != 0)
					playerOneBishopDarkSquare++;
				else
					playerOneBishopLightSquare++;
			}
			else
			{
				if ((piece.xPos + piece.yPos) % 2 == 0)
					playerTwoBishopLightSquare++;
				else
					playerTwoBishopDarkSquare++;
			}
		}
		else if (result.get() == buttonQueen) {
			promotedPiece = new PieceQueen(piece.type, piece.xPos, piece.yPos);
			getChildren().remove(piece.getImage());
			getChildren().add(promotedPiece.getImage());
			pieces[piece.xPos][piece.yPos] = promotedPiece;
			if (piece.type == 1)
				playerOneQueen++;
			else
				playerTwoQueen++;
		}
	}
	
	public void colorSquare(int x, int y, boolean selectedPiece)
	{

		System.out.println("Color Change Method Called");
		if (selectedPiece) {
			System.out.println("X: " + x);
			System.out.println("Y: " + y);
			windows[x][y].highlightWindow(Color.ORANGE);
		} else {
			System.out.println("X: " + x);
			System.out.println("Y: " + y);
			windows[x][y].highlightWindow(Color.GREEN);
		}
	}
	
	public void unhighlightWindow()
	{
		for (int y = 0; y < boardHeight; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				if (windows[x][y].getRectangle().getStroke() != null)
					windows[x][y].unhighlight();
			}
		}
	}
	
	public void timerOver(int playerOutOfTime)
	{
		timer.timeline.stop();
		if (playerOutOfTime == 1)
		{
			statusBar.whitePlayerAlert.setText("White player run out of time");
			statusBar.winner.setText("Black player won !");
		}
		else if (playerOutOfTime == 2)
		{
			statusBar.blackPlayerAlert.setText("Black player run out of time");
			statusBar.winner.setText("White player won !");
		}
	}

	public void setCurrentGameMode(String newMode) {
		currentGameMode = newMode;
	}

	public String getCurrentGameMode() {
		return currentGameMode;
	}

	
	// Getter and setter method
	
	public Piece getKing(int type)
	{
		if (type == 1)
			return (king_1);
		return (king_2);
	}


	public void setPieces(Piece[][] pieces) {
		this.pieces = pieces;
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public int getBoardHeight()
	{
		return (this.boardHeight);
	}
	
	public int getBoardWidth()
	{
		return (this.boardWidth);
	}
	
	public int getBoardPosition(int x, int y)
	{
		return (this.board[x][y]);
	}
	
	public void setBoard(int x, int y, int type)
	{
		this.board[x][y] = type;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

	public Piece getPiece(int x, int y)
	{
		return (pieces[x][y]);
	}
	
	public void setPiece(int x, int y, Piece piece)
	{
		this.pieces[x][y] = piece;
	}
	
	public StatusBar getStatusBar() {
		return (statusBar);
	}
}