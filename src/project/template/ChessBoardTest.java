package project.template;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;


public class ChessBoardTest extends ApplicationTest {

		private StackPane sp_mainlayout;	//layout which allows items to be positioned on top of each other
	private CustomControl cc_custom;
	@Override
	public void start (Stage stage) throws Exception {

		sp_mainlayout = new StackPane();
		cc_custom = new CustomControl();
		cc_custom.getChessBoard().resetGame();
		sp_mainlayout.getChildren().add(cc_custom);
		stage.setTitle("Chess game");
		stage.setScene(new Scene(sp_mainlayout, 600, 700));
		stage.show();
		stage.toFront();
	}

	@Before
	public void setUp () throws Exception {
		System.out.println("SET UP");
		Thread.sleep(2000);

	}

	@After
	public void tearDown () throws Exception {
//		FxToolkit.hideStage();
//		release(new KeyCode[]{});
//		release(new MouseButton[]{});
		System.out.println("Tear Down Method....\n");
	}


	@Test
	public void testModeOnStart () {
		String gameMode = cc_custom.getChessBoard().getCurrentGameMode();
		System.out.println("\n" + gameMode);
		if(gameMode.equals("Traditional Chess")) {
			assertTrue(true);
		}
	}
	@Test
	public void testGameModeTo960 () {
		cc_custom.getChessBoard().setCurrentGameMode("Chess 960");
		String gameMode = cc_custom.getChessBoard().getCurrentGameMode();
		System.out.println("\n" + gameMode);
		if(gameMode.equals("Chess 960")) {
			assertTrue(gameMode, gameMode.equals("Chess 960"));
		}

	}

	@Test
	public void clickResetButtonWhenInTraditionalMode() {
        cc_custom.getChessBoard().setCurrentGameMode("Traditional Chess");
        try {
            Thread.sleep(5000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }
		clickOn(cc_custom.getChessBoard().getStatusBar().getResetButton());

        try {
			Thread.sleep(3000);  // Wait for 3 seconds before interrupting JavaFX application
		} catch(InterruptedException ex) {
			// We don't care if we wake up early.
		}
	}

	@Test
    public void clickMode() {
        try {
            Thread.sleep(2000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }
        clickOn(cc_custom.getChessBoard().getStatusBar().getGameMode());

    }



    @Test
    public void selectWhitePiecePossibleMoves() {
        try {
            Thread.sleep(2000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }


        for(int i = 0; i < cc_custom.getChessBoard().getPieces().length; i++) {
            for(int j =0; j < cc_custom.getChessBoard().getPieces()[i].length; j++) {
                    System.out.println(i + " " + j);
                    System.out.println(cc_custom.getChessBoard().getPieces()[i][j]);

            }
        }

        clickOn(cc_custom.getChessBoard().getPieces()[6][7].getImage());
        try {
            Thread.sleep(3000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }
        clickOn(cc_custom.getChessBoard().getChildren().get(7));
        clickOn(cc_custom.getChessBoard().getChildren().get(5));

        try {
            Thread.sleep(3000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }

        clickOn(cc_custom.getChessBoard().getChildren().get(2));
        clickOn(cc_custom.getChessBoard().getChildren().get(3));
    }

    @Test
    public void selectWhitePiecePossibleMoves2() throws IllegalStateException {
	    ChoiceBox box = cc_custom.getChessBoard().getStatusBar().getGameMode();
            clickOn(cc_custom.getChessBoard().getStatusBar().getGameMode());
            box.getSelectionModel().selectLast();


	    clickOn(cc_custom.getChessBoard().getStatusBar().getGameMode().getScene());
        clickOn(cc_custom.getChessBoard().getStatusBar().getResetButton());
        try {
            Thread.sleep(2000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }


        for(int i = 0; i < cc_custom.getChessBoard().getPieces().length; i++) {
            for(int j =0; j < cc_custom.getChessBoard().getPieces()[i].length; j++) {
                System.out.println(i + " " + j);
                System.out.println(cc_custom.getChessBoard().getPieces()[i][j]);

            }
        }

        try {
            Thread.sleep(3000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }
        clickOn(cc_custom.getChessBoard().getChildren().get(7));
        clickOn(cc_custom.getChessBoard().getChildren().get(5));

        try {
            Thread.sleep(3000);  // Wait for 3 seconds before interrupting JavaFX application
        } catch(InterruptedException ex) {
            // We don't care if we wake up early.
        }

        clickOn(cc_custom.getChessBoard().getChildren().get(2));
        clickOn(cc_custom.getChessBoard().getChildren().get(3));
    }
}
