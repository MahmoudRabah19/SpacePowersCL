package Domain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeakEnemy extends Enemy{
	ImageView wEnemy;
	int x;
	int y;
	public WeakEnemy() {
	
		
	}
	public ImageView createWeakEnemy(int x , int y) {
		wEnemy = new ImageView(new Image("file:src/main/resources/Images/enemy.gif"));
		wEnemy.setFitWidth(50);
		wEnemy.setFitHeight(50);
		wEnemy.setLayoutX(x);
		wEnemy.setLayoutY(y);
		return wEnemy;
		
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
