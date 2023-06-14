import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

enum FoodSelect{
    //(イメージpass, 名前, 成功率, ダメージ, 回復量, 金額, スコア)　　スイーツ
    PANCAKE("img/food/Pancake.png", "パンケーキ", 100, 0, 200, 1500, 500),
    CHOCOLATE("img/food/Chocolate.png", "チョコレート", 82, 20, 40, 300, 50),
    COOKIE("img/food/Cookie.png", "クッキー", 77, 10, 10, 300, 20),
    CREAMPUFF("img/food/CreamPuff.png", "シュークリーム", 60, 30, 90, 400, 80),
    DONUTS("img/food/Donuts.png", "ドーナッツ", 92, 30, 50, 240, 70),
    DUMPLING("img/food/Dumpling.png", "団子", 50, 70, 70, 430, 100),
    PUDDING("img/food/Pudding.png", "プリン", 99, 30, 50, 350, 40),
    SHORTCAKE("img/food/Shortcake.png", "ショートケーキ", 100, 0, 100, 1000, 100),
    //(イメージpass, 名前, 成功率, ダメージ, 回復量, 金額, スコア)　　加工品
    RICEBALL("img/food/RiceBall.png", "おにぎり", 92, 40, 10, 100, 40),
    CURRYRICE("img/food/CurryRice.png", "カレーライス", 50, 120, 20, 400, 200),
    FRIEDNOODLES("img/food/FriedNoodles.png", "焼きそば", 70, 90, 20, 500, 90),
    HAMBURGER("img/food/Hamburger.png", "ハンバーガー", 83, 80, 30, 400, 90),
    HAMBURGERSTEAK("img/food/hamburgerSteak.png", "ハンバーグ", 80, 65, 20, 800, 100),
    OMELETTRICE("img/food/OmeletteRice.png", "オムライス", 69, 70, 30, 800, 120),
    OYAKODON("img/food/Oyakodon.png", "親子丼", 66, 80, 20, 600, 160),
    PIZZA("img/food/Pizza.png", "ピザ", 75, 90, 30, 1200, 130),
    PORKCUTLET("img/food/PorkCutletOnRice.png", "豚カツ", 73, 70, 20, 800, 130),
    SANDWICH("img/food/Sandwich.png", "サンドウィッチ", 96, 40, 30, 200, 70),
    SIMMEREDDISH("img/food/SimmeredDish.png", "煮物", 37, 80, 5, 400, 300),
    //(イメージpass, 名前, 成功率, ダメージ, 回復量, 金額, スコア)　　生野菜
    GREENONION("img/food/GreenOnion.png", "ネギ", 30, 50, 45, 99, 100),
    ONION("img/food/Onion.png", "玉ねぎ", 43, 40, 30, 80, 85),
    CARROT("img/food/Carrot.png", "にんじん", 32, 20, 25, 105, 82),
    CUCUMBER("img/food/Cucumber.png", "きゅうり", 13, 5, 5, 45, 30),
    LETTUCE("img/food/Lettuce.png", "レタス", 70, 25, 30, 180, 102),
    CABBAGE("img/food/Cucumber.png", "キャベツ", 50, 30, 30, 210, 120),
    //(イメージpass, 名前, 成功率, ダメージ, 回復量, 金額, スコア)　　フルーツ
    APPLE("img/food/Apple.png", "りんご", 76, 50, 60, 550, 200),
    PEACHE("img/food/Peache.png", "もも", 82, 43, 42, 800, 320),
    PEAR("img\\food\\Pear.png", "なし", 72, 22, 40, 400, 220),
    CHERY("img/food/Cherry.png", "さくらんぼ", 80, 18, 30, 220, 200),
    LEMON("img/food/Lemon.png", "れもん", 42, 120, 20, 280, 320),
    //(イメージpass, 名前, 成功率, ダメージ, 回復量, 金額, スコア)　　その他    
    WOOPERLOOPER("img/food/WooperLooper.png", "ウーパールーパー", 18, 320, 0, 2000, 5000),
    CAT("img/food/Cat.png", "ネコ", 4, 500, 0, 80000, 20000),
    DOG("img/food/Dog.png", "イヌ", 2, 800, 0, 50000, 20000),
    PENGUIN("img/food/Penguin.png", "ペンギン", 12, 400, 0, 700000, 300000),
    TADPOLE("img/food/Tadpole.png", "オタマジャクシ", 28, 80, 5, 5, 180),
    

    FOURLEAFCLOVER("img/food/fourLeafClover.png", "四葉のクローバー", 1, 10, 5, 0, 5000);






    private String name, text;
    private int successRate, cure, damege, price, score;
    private Image foodImage;
    private CharacterSelect character;
    private BackgroundSelect background;

    FoodSelect(String food, String name, int successRate, int damege, int cure, int price, int score){
        CharacterSelect[] characters = CharacterSelect.values();
        //画像取得
        try {
			this.foodImage = ImageIO.read(new File(food));
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.name = name;
        this.successRate = successRate;
        this.cure = cure;
        this.background = BackgroundSelect.LIVING1;
        this.damege = damege;
        this.price = price;
        this.score = score;
        
        int count = successRate / 10;
        if(count > 9){
            count = 9;
        }else if(count < 0){
            count = 0;
        }
        this.character = characters[count];
    }

    public void setText(String text){
        this.text = text;
    }

    public String getName(){
        return this.name;
    }

    public String getText(){
        return this.text;
    }

    public int getSuccessRate(){
        return this.successRate;
    }

    public int getCure(){
        return this.cure;
    }

    public Image getFoodImage(){
        return this.foodImage;
    }

    public Image getCharacterImage(){
        return this.character.get();
    }

    public Image getBackgroundImage(){
        return this.background.get();
    }

    public int getDamege(){
        return this.damege;
    }

    public int getPrice(){
        return this.price;
    }

    public int getScore(){
        return this.score;
    }
}