package Domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class enemyBullet extends Bullet {
	Circle eBullet;
	public enemyBullet(double x, double y) {
		super(x, y);
	}

	public Circle createEnemyBullet() {
		eBullet = new Circle();
		eBullet.setFill(Color.RED);
		eBullet.setStroke(Color.BURLYWOOD);
		eBullet.setLayoutX(x);
		eBullet.setLayoutY(y);
		eBullet.setRadius(3);
		return eBullet;
	}

	
	

}
