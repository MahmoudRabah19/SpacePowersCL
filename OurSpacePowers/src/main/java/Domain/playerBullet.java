package Domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class playerBullet extends Bullet {
	Circle pBullet;
	
	public playerBullet(double x, double y) {
		super(x, y);
	}
	public Circle createPlayerBullet() {
		pBullet = new Circle();
		pBullet.setFill(Color.GREENYELLOW);
		pBullet.setLayoutX(x);
		pBullet.setLayoutY(y);
		pBullet.setRadius(2);
		pBullet.setScaleY(6);
		pBullet.setFill(Color.BLUE);
		return pBullet;
	}

}
