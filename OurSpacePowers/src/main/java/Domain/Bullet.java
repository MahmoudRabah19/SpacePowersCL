package Domain;

import javafx.scene.shape.Circle;

public abstract class Bullet  {
	Circle bullet;
	double x;
	double y;

	Bullet(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public Circle createBullet() {
		bullet.setLayoutX(getX());
		bullet.setLayoutY(getY());
		return bullet;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
