import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum CharacterSelect {
    
    AFRAID("img/character/AfraidCharacter.png"),
    ANGRY("img/character/AngryCharacter.png"),
    CRY("img/character/CryCharacter.png"),
    PANIC("img/character/PanicCharacter.png"),
    SURPRISED("img/character/SurprisedCharacter.png"),
    NOMAL("img/character/NomalCharacter.png"),
    BRAGGING("img/character/BraggingCharacter.png"),
    LAZY("img/character/LazyCharacter.png"),
    SMILE("img/character/SmileCharacter.png"),
    HAPPY("img/character/HappyCharacter.png");

    private Image image;

    CharacterSelect(String pass){
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