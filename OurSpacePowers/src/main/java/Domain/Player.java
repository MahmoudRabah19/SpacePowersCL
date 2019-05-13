package Domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class Player {

	Polyline player;

	public Polyline createPlayer() {
		player = new Polyline();
		player.getPoints().addAll(new Double[] { 0.0, -25.0, -25.0, 25.0, 0.0, 15.0, 25.0, 25.0, 0.0, -25.0 });
		player.setFill(Color.RED);
		player.setLayoutX(225);
		player.setLayoutY(650);
		return player;
	}

	public void moveLeft() {
		player.setLayoutX(player.getLayoutX() - 10);
		if((player.getLayoutX()-25) <= 0) {
			player.setLayoutX(25);
		}

	}

	public void moveRight() {
		player.setLayoutX(player.getLayoutX() + 10);
		if((player.getLayoutX()+25) >= 500) {
			player.setLayoutX(475);
		}
	}

	

}
