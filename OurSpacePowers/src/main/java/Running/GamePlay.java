package Running;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Domain.BossEnemy;
import Domain.Bullet;
import Domain.Player;
import Domain.WeakEnemy;
import Domain.enemyBullet;
import Domain.playerBullet;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlay extends Application {
	AnimationTimer timer;
	Timeline timeline;
	Pane root = new Pane();
	List<ImageView> enemies = new ArrayList<ImageView>();

	List<Circle> enemyShoots = new ArrayList<Circle>();
	List<Circle> playerShoots = new ArrayList<Circle>();
	Polyline newPlayer;
	Circle dotR = new Circle();
	boolean toRight = true;
	Text lives;
	Text Score;
	int nScore = 0;
	int numLives = 3;
	Scene playScene, howToPlayScene, firstScene, rulesScene;
	MediaPlayer mPlayer;
	ImageView eBoss;
	boolean bossDied = false;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// life and Score
		lives = new Text("Lives: " + numLives);
		lives.setLayoutX(20);
		lives.setLayoutY(30);
		lives.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		lives.setFill(Color.WHITE);
		Score = new Text("Score: " + nScore);
		Score.setLayoutX(350);
		Score.setLayoutY(30);
		Score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		Score.setFill(Color.WHITE);
		root.getChildren().addAll(lives, Score);

		// dot that regulates moving of enemies
		dotR.setLayoutX(0);

		// creating player
		Player player = new Player();
		newPlayer = player.createPlayer();

		root.getChildren().add(newPlayer);
		// PlayerMove(player);

		// create enemies
		addEnemies();

		// AnimationTimer
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				gameUpdate(primaryStage);
			}
		};

		// timeline for making enemies shoots every few seconds

		timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
			if (!enemies.isEmpty()) {
				enemiesShoot();
			}

		}));
		timeline.setCycleCount(Animation.INDEFINITE);

		// setting up stage

		mainMenu(primaryStage);
		PlayerMove(player);
	}

	private void mainMenu(Stage primaryStage) {
		primaryStage.setTitle("SpaceInvaders");
		Image i = new Image("file:src/main/resources/Images/BackGround.png");
		Button play = new Button("Play");
		playScene = new Scene(root, 500, 700);
		play.setOnMouseEntered(e -> {
			play.setTextFill(Color.RED);
			play.setScaleX(1.2);
			play.setScaleY(1.2);
		});

		play.setOnMouseExited(e -> {
			play.setTextFill(Color.BLACK);
			play.setScaleX(1);
			play.setScaleY(1);
		});

		Button howToPlay = new Button("How To Play");

		howToPlay.setOnMouseEntered(e -> {
			howToPlay.setTextFill(Color.RED);
			howToPlay.setScaleX(1.2);
			howToPlay.setScaleY(1.2);
		});

		howToPlay.setOnMouseExited(e -> {
			howToPlay.setTextFill(Color.BLACK);
			howToPlay.setScaleX(1);
			howToPlay.setScaleY(1);
		});
		Button rules = new Button("Game Rules");

		howToPlay.setOnMouseEntered(e -> {
			howToPlay.setTextFill(Color.RED);
			howToPlay.setScaleX(1.2);
			howToPlay.setScaleY(1.2);
		});

		howToPlay.setOnMouseExited(e -> {
			howToPlay.setTextFill(Color.BLACK);
			howToPlay.setScaleX(1);
			howToPlay.setScaleY(1);
		});

		Button exit = new Button("Exit");

		exit.setOnMouseEntered(e -> {
			exit.setTextFill(Color.RED);
			exit.setScaleX(1.2);
			exit.setScaleY(1.2);
		});

		exit.setOnMouseExited(e -> {
			exit.setTextFill(Color.BLACK);
			exit.setScaleX(1);
			exit.setScaleY(1);
		});

		VBox vBox = new VBox();
		play.setMaxSize(150, 300);
		howToPlay.setMaxSize(150, 300);
		rules.setMaxSize(150, 300);
		exit.setMaxSize(150, 300);
		BackgroundImage bg = new BackgroundImage(i, null, null, null, null);
		vBox.setBackground(new Background(bg));
		vBox.getChildren().addAll(play, howToPlay, rules, exit);
		vBox.setSpacing(5);
		vBox.setPadding(new Insets(230));
		vBox.setAlignment(Pos.BOTTOM_CENTER);
		firstScene = new Scene(vBox, 600, 650);
		primaryStage.setScene(firstScene);
		mPlayer = new MediaPlayer(
				new Media("file:///C:/Users/עידן/workspace/OurSpacePowers/src/main/resources/Sound/GameSound.mp3"));
		mPlayer.play();
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image("file:src/main/resources/Images/icon.png"));
		play.setOnAction(e -> {
			Image image = new Image("file:src/main/resources/Images/bgp.gif");
			BackgroundImage bgp = new BackgroundImage(image, null, null, null, null);
			root.setBackground(new Background(bgp));
			primaryStage.setScene(playScene);
			primaryStage.setTitle("Space Invaders");
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("file:src/main/resources/Images/icon.png"));
			timer.start();
			timeline.play();
		});
		howToPlay.setOnAction(e -> {
			Pane pane = new Pane();
			Button back = new Button("Back To Menu");
			back.setLayoutY(600.0);
			back.setLayoutX(505.0);
			back.setScaleY(1.5);
			Image image = new Image("file:src/main/resources/Images/HowToPlay.PNG");
			BackgroundImage bg1 = new BackgroundImage(image, null, null, null, null);
			pane.setBackground(new Background(bg1));
			pane.getChildren().add(back);
			howToPlayScene = new Scene(pane, 600, 650);
			back.setOnAction(event -> {
				primaryStage.setScene(firstScene);
				primaryStage.show();
				primaryStage.setResizable(false);
				primaryStage.getIcons().add(new Image("file:src/main/resources/Images/icon.png"));
			});
			primaryStage.setScene(howToPlayScene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("file:src/main/resources/Images/icon.png"));
		});
		rules.setOnAction(e -> { // over here
			Pane pane = new Pane();
			Button back = new Button("Back To Menu");
			back.setLayoutY(600.0);
			back.setLayoutX(505.0);
			back.setScaleY(1.5);
			Image image = new Image("file:src/main/resources/Images/Rules.png");
			BackgroundImage bg1 = new BackgroundImage(image, null, null, null, null);
			pane.setBackground(new Background(bg1));
			pane.getChildren().add(back);
			rulesScene = new Scene(pane, 600, 650);
			back.setOnAction(event -> {
				primaryStage.setScene(firstScene);
				primaryStage.show();
				primaryStage.setResizable(false);
				primaryStage.getIcons().add(new Image("file:src/main/resources/Images/icon.png"));
			});
			primaryStage.setScene(rulesScene);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image("file:src/main/resources/Images/icon.png"));
		});
		exit.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Exit");
			alert.setContentText("Are You Sure To Exit ?");
			alert.show();
			alert.setOnHiding(e1 -> {
				primaryStage.close();
			});
		});
	}

	private void PlayerMove(Player player) {
		playScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.RIGHT) {
				player.moveRight();

			}
			if (e.getCode() == KeyCode.LEFT) {
				player.moveLeft();

			}

		});
		playScene.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.SPACE) {
				playerShoot();
			}
		});

	}

	public void gameUpdate(Stage stage) {
		// updating enemies shoots
		enemiesShootUpdate();
		// updating players Shoots
		playerShootUpdate();
		// checking if player is hit
		PlayerDied();
		// checking if enemy is hit
		EnemiesDied();
		// moving enemies
		enemiesMove();
		// Setup boss coming
	//	BossComing();

		// You Win
		Win();
		// You Lose
		GameOver(stage);
	}

	/*private void BossDied() {
		// how much hits

	}*/

	/*private void BossComing() {
		if (enemies.isEmpty()) {
			eBoss = createBoss();
			eBoss.setLayoutX(250);
			eBoss.setLayoutY(50);
			enemies.add(eBoss);
			root.getChildren().addAll(enemies);
			// Boss Death
			BossDied();
			KeyFrame frame = new KeyFrame(Duration.seconds(2), e -> moveBoss());
			Timeline bTimeline = new Timeline(frame);
			bTimeline.setCycleCount(Animation.INDEFINITE);
			timeline.stop();
			bTimeline.play();

		}

	}*/

	/*private ImageView createBoss() {
		BossEnemy bEnemy = new BossEnemy();
		eBoss = bEnemy.createBossEnemy(250, 50);
		return eBoss;
	}*/

	public int rand(int min, int max) {
		return (int) (Math.random() * max + min);
	}

	public void enemiesShoot() {
		int getShootingEnemyIndex = rand(0, enemies.size() - 1);
		enemyBullet eShoot = new enemyBullet((enemies.get(getShootingEnemyIndex).getLayoutX() + 25),
				(enemies.get(getShootingEnemyIndex).getLayoutY() + 25));
		Circle eBullet = eShoot.createEnemyBullet();
		enemyShoots.add(eBullet);
		root.getChildren().add((Node) enemyShoots.get(enemyShoots.size() - 1));
	}

	public void addEnemies() {

		for (int i = 0, w = 40; i < 6; i++, w += 70) {
			WeakEnemy wEnemy = new WeakEnemy();
			ImageView enemy = wEnemy.createWeakEnemy(w, 50);
			enemies.add(enemy);
			root.getChildren().add((Node) enemies.get(i));
		}
		for (int i = 0, w = 40; i < 6; i++, w += 70) {
			WeakEnemy wEnemy = new WeakEnemy();
			ImageView enemy = wEnemy.createWeakEnemy(w, 120);
			enemies.add(enemy);
			root.getChildren().add((Node) enemies.get(i + 6));
		}
		for (int i = 0, w = 40; i < 6; i++, w += 70) {
			WeakEnemy wEnemy = new WeakEnemy();
			ImageView enemy = wEnemy.createWeakEnemy(w, 190);
			enemies.add(enemy);
			root.getChildren().add((Node) enemies.get(i + 12));
		}
	}

	public void enemiesMove() {
		// enemies MOVING
		double speed;
		if (toRight)
			speed = 0.6;
		else
			speed = -0.6;

		if (dotR.getLayoutX() >= 40) {
			toRight = false;
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).setLayoutY(enemies.get(i).getLayoutY() + 8);
			}
		}
		if (dotR.getLayoutX() <= -20) {
			toRight = true;
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).setLayoutY(enemies.get(i).getLayoutY() + 8);
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).setLayoutX(enemies.get(i).getLayoutX() + speed);
		}
		dotR.setLayoutX(dotR.getLayoutX() + speed);
	}

	public void playerShoot() {
		playerBullet pShoot = new playerBullet(newPlayer.getLayoutX(), 630);
		Circle pBullet = pShoot.createPlayerBullet();
		playerShoots.add(pBullet);
		root.getChildren().add(playerShoots.get(playerShoots.size() - 1));
		MediaPlayer shoot = new MediaPlayer(new Media("file:///C://Users//עידן//Downloads//Music//laser.wav"));
		shoot.setVolume(20.0);
		shoot.play();
	}

	private void playerShootUpdate() {

		if (!playerShoots.isEmpty()) {
			for (int i = 0; i < playerShoots.size(); i++) {
				playerShoots.get(i).setLayoutY(playerShoots.get(i).getLayoutY() - 3);
				if (playerShoots.get(i).getLayoutY() <= 0) {
					root.getChildren().remove(playerShoots.get(i));
					playerShoots.remove(i);
				}
			}
		}
	}

	private void enemiesShootUpdate() {
		if (!enemyShoots.isEmpty()) {
			for (int i = 0; i < enemyShoots.size(); i++) {
				enemyShoots.get(i).setLayoutY(enemyShoots.get(i).getLayoutY() + 3);
				if (enemyShoots.get(i).getLayoutY() <= 0) {
					root.getChildren().remove(enemyShoots.get(i));
					enemyShoots.remove(i);
				}
			}
		}
	}

	private void EnemiesDied() {
	//	int disLevel = 10;
		for (int i = 0; i < playerShoots.size(); i++) {
			for (int j = 0; j < enemies.size(); j++) {
				if (((playerShoots.get(i).getLayoutX() > enemies.get(j).getLayoutX())
						&& ((playerShoots.get(i).getLayoutX() < enemies.get(j).getLayoutX() + 50))
						&& ((playerShoots.get(i).getLayoutY() > enemies.get(j).getLayoutY())
								&& ((playerShoots.get(i).getLayoutY() < enemies.get(j).getLayoutY() + 50))))) {
					root.getChildren().remove(enemies.get(j));
					 MediaPlayer exp = new MediaPlayer(
					 new Media("file:///C:/Users/עידן/workspace/OurSpacePowers/src/main/resources/Sound/bomb.wav"));
					exp.play();
					enemies.remove(j);
					root.getChildren().remove(playerShoots.get(i));
					playerShoots.remove(i);
					nScore += 100;
					Score.setText("Score: " + nScore);
					System.out.println("hi");
					
					if(enemies.isEmpty() && bossDied == false) {
						createBoss();
						
					}
					break;
				}
			}
		}
				
			/*	if (playerShoots.get(i).getBoundsInParent()
						.intersects(eBoss.getBoundsInParent())) {
					root.getChildren().remove(playerShoots.get(i));
					playerShoots.remove(i);
					disLevel -= 1;
					System.out.println(disLevel);
				
				}
			}
			
		}
		if (disLevel == 0 && numLives > 0) {
				root.getChildren().remove(enemies.get(enemies.indexOf(eBoss)));
				enemies.remove(eBoss);
				bossDied = true;
			}*/
	}
	private void createBoss() {		
		for (int i = 0, w = 40; i < 6; i++, w += 70) {
			BossEnemy bEnemy = new BossEnemy();
			ImageView enemy = bEnemy.createBossEnemy(w, 50);
			enemies.add(enemy);
			root.getChildren().add((Node) enemies.get(i));
		}
		for (int i = 0, w = 40; i < 6; i++, w += 70) {
			BossEnemy bEnemy = new BossEnemy();
			ImageView enemy = bEnemy.createBossEnemy(w, 120);
			enemies.add(enemy);
			root.getChildren().add((Node) enemies.get(i + 6));
		}
		for (int i = 0, w = 40; i < 6; i++, w += 70) {
			BossEnemy bEnemy = new BossEnemy();
			ImageView enemy = bEnemy.createBossEnemy(w, 190);
			enemies.add(enemy);
			root.getChildren().add((Node) enemies.get(i + 12));
		}
		bossDied =true;

			}
		
		
	

	private void PlayerDied() {

		Label live = new Label("Lives -1");
		live.setFont(new Font("Times New Roman", 15));
		live.setTextFill(Color.WHITE);
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getBoundsInParent().intersects(newPlayer.getBoundsInParent())) {
				root.getChildren().remove(enemies.get(i));
				root.getChildren().add(live);
				enemies.remove(i);
				numLives -= 1;
				lives.setText("Lives" + numLives);
			}

		}
		for (int i = 0; i < enemyShoots.size(); i++) {
			if (enemyShoots.get(i).getBoundsInParent().intersects(newPlayer.getBoundsInParent())) {
				root.getChildren().remove(enemyShoots.get(i));
				enemyShoots.remove(i);
				/*
				 * live.setLayoutX(enemyShoots.get(i).getLayoutX());
				 * live.setLayoutY(enemyShoots.get(i).getLayoutY());
				 * root.getChildren().add(live);
				 */
				// newPlayer.setLayoutX(225);
				numLives -= 1;
				lives.setText("Lives: " + numLives);
			}
		}
	}

	public void Win() {

		if (enemies.isEmpty() && bossDied) {
			Text yScore = new Text();
			Text text = new Text();
			text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
			text.setX(150);
			text.setY(300);
			text.setFill(Color.YELLOW);
			text.setStrokeWidth(3);
			text.setStroke(Color.GOLD);
			text.setText("You WIN");
			yScore.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, FontPosture.REGULAR, 30));
			yScore.setFill(Color.WHITE);
			yScore.setText("Your Score is: " + nScore);
			yScore.setX(150);
			yScore.setY(350);
			MediaPlayer winP = new MediaPlayer(new Media("file:///C:/Users/עידן/workspace/OurSpacePowers/src/main/resources/Sound/EpicWin.mp3"));
			mPlayer.stop();
			winP.play();
			root.getChildren().addAll(text, yScore);
			timer.stop();
		}
	}

	public void GameOver(Stage stage) {
		if (numLives <= 0) {
			Text yScore = new Text();
			Label quit = new Label();
			Text text = new Text();
			text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
			text.setX(120);
			text.setY(310);
			text.setFill(Color.RED);
			text.setStrokeWidth(2);
			text.setStroke(Color.GOLD);
			text.setText("GameOver");
			yScore.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, FontPosture.REGULAR, 30));
			yScore.setFill(Color.WHITE);
			yScore.setText("Your Score is: " + nScore);
			yScore.setX(150);
			yScore.setY(350);
			
			MediaPlayer go = new MediaPlayer(
					new Media("file:///C:/Users/עידן/workspace/OurSpacePowers/src/main/resources/Sound/GameOver.mp3"));
			mPlayer.stop();
			go.play();
			
			quit.setFont(Font.font("TimesNewRoman", FontWeight.BOLD, FontPosture.REGULAR, 30));
			quit.setTextFill(Color.WHITE);
			quit.setText("Press Q To Quit");
			quit.setLayoutX(150);
			quit.setLayoutY(350);
			playScene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.Q) {
					stage.close();
					//Restart(stage);
				}
			});
			root.getChildren().addAll(text,  yScore ,quit);
			timer.stop();

		}
	}

	private void Restart(Stage stage) {
		
	}

	private void moveBoss() {
		double speed;
		if (toRight)
			speed = 2;
		else
			speed = -2;

		if ((dotR.getLayoutX() + 40) >= 600) {
			toRight = false;
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).setLayoutY(enemies.get(i).getLayoutY() + speed);
				enemies.get(i).setLayoutX(enemies.get(i).getLayoutX() + 30);
			}
		}
		if ((dotR.getLayoutX() - 20) <= -490) {
			toRight = true;
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).setLayoutY(enemies.get(i).getLayoutY() + speed);
				enemies.get(i).setLayoutX(enemies.get(i).getLayoutX() - 30);
			}
		}

		dotR.setLayoutX(dotR.getLayoutX() + speed);
	}

}
