package Domain;

import javafx.scene.image.ImageView;

public abstract class Enemy {
	ImageView iv;
	int x;
	int y;
	

	public ImageView getIv() {
		return iv;
	}

	public void setIv(ImageView iv) {
		this.iv = iv;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
