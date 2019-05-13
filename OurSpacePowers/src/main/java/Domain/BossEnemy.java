package Domain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossEnemy {
	ImageView bEnemy;
	int x;
	int y;
	public BossEnemy() {	
	}
	public ImageView createBossEnemy(int x , int y) {
		bEnemy = new ImageView(new Image("file:src/main/resources/Images/bossEnemy.gif"));
		bEnemy.setFitWidth(50);
		bEnemy.setFitHeight(50);
		bEnemy.setLayoutX(x);
		bEnemy.setLayoutY(y);
		return bEnemy;
		
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
