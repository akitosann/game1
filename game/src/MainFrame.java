import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.*;

public class MainFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    //アスペクト16:9
    public final int WIDTH = 960;
    public final int HEIGHT = 540;
    private final ImageIcon ICON = new ImageIcon("img/food/fourLeafClover.png");//アイコン取得
    private final CardLayout layout = new CardLayout();

    protected TitlePanel titlePanel;
    protected GamePanle gamePanle;
    protected ScorePanel scorePanel;
    
    private HashMap<String, JPanel> panels = new HashMap<String, JPanel>();

    protected Dimension dimension = new Dimension(WIDTH, HEIGHT);


    MainFrame(){
        this.setTitle(Main.TITLE);//タイトル
        this.setSize(dimension);//フレームサイズ
        this.setPreferredSize(dimension);//レイアウト参照枠サイズ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウインドウ終了時タスク終了
        this.setResizable(true);//画面拡大有無
        this.setLayout(this.layout);//レイアウトをCardLayout()化
        this.setIconImage(ICON.getImage());//アイコン表示
        this.setLocationRelativeTo(null);//Window中央に表示
    }

    public void addPanels(){
        this.panels.put(PanelSelect.TITLE.toString(), this.titlePanel = new TitlePanel(this));
        this.panels.put(PanelSelect.GAME.toString(), this.gamePanle = new GamePanle(this));
        this.panels.put(PanelSelect.SCORE.toString(), this.scorePanel = new ScorePanel(this));

        //frameに加えるpanelとpanel識別名
        for(Map.Entry<String, JPanel> e : this.panels.entrySet()){
            this.add(e.getValue(), e.getKey());
        }

        //this.pack();//画面比率合わせ
    }

    public void setPanle(PanelSelect id){
        String name = id.toString();
        layout.show(this.getContentPane(), name);
        this.panels.get(name).requestFocus();
    }

    //画像の大きさ変更
    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
    
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
    
        return resizedImg;
    }

    public JPanel getPanel(String name){
        return this.panels.get(name);
    }
}