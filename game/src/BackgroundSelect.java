import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum BackgroundSelect {
    LIVING1("img/background/LivingDaytimeBackground.jpg"),
    LIVING2("img/background/LivingEveningBackground.jpg"),
    LIVING3("img/background/LivingNightBackground.jpg"),
    LIVING4("img/background/LivingMidnightBackground.jpg");

    private Image image;

    BackgroundSelect(String pass){
        try {
			this.image = ImageIO.read(new File(pass));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public Image get(){
        return this.image;
    }
}
