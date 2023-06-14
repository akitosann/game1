import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanle extends JPanel implements ActionListener{
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private JLabel imgLabel, percentLabel, characterLabel, hpString, moneyLabel, dayLabel;
    private JButton eatButton, dumpButton, changeButton, reinforceButton, titleButton;
    private Dimension dimension;
    private FoodSelect[] foods = FoodSelect.values();
    private FoodSelect food;
    private Random rand = new Random();
    protected SmoothBar hpBar;
    private ReinforcePanle reinforcePanle;
    protected MainFrame frame;
    private WarningPanel warningPanel;

    private double score;
    protected int hp, money, day;
    protected int maxHp;//HPの上限値
    protected int dumpRatio;//捨てた時のダメージ割合(連続で捨てた時何日で死ぬか)
    protected int damegeBase;//失敗時のダメージ比率(0~100％)
    protected int income;//収入
    protected int changeCost;//値段(0~100％)
    protected boolean successRateAppear; //成功率出現判定

    protected static final int MAXHP = 500;
    
    public void configReset(){
        this.food = foodGet();
        this.score = 0;

        this.maxHp = MAXHP;
        this.hp = MAXHP;
        this.money = 30000;
        this.day = 1;
        this.income = 10000;
        this.dumpRatio = 3;
        this.damegeBase = 100;
        this.changeCost = 100;
        this.successRateAppear = false;

        this.hpBar.setMaxHp(this.maxHp);
        this.hpBar.setHp(this.hp);
        this.hpBar.setCurentHp(this.hp);

        this.setEnabled(true);
        this.reinforcePanle.setPanelDisplay(false);
        this.warningPanel.setPanelDisplay(false);

        this.reinforcePanle.configReset();
    }

    GamePanle(MainFrame frame){
        this.frame = frame;
        this.setLayout(new FlowLayout());

        //強化パネル
        this.reinforcePanle = new ReinforcePanle(this);
        this.add(this.reinforcePanle);

        //タイトルパネル
        this.warningPanel = new WarningPanel(this, "タイトルに戻りますか？");
        this.add(warningPanel);

        //食べ物決定
        this.food = foodGet();

        //HPバー
        this.hpBar = new SmoothBar();
        this.hpBar.setHp(this.hp);
        this.add(hpBar);
        this.hpString = new JLabel("HP");
        this.add(this.hpString);

        //食べ物
        this.imgLabel = new JLabel();
        this.imgLabel.setHorizontalAlignment(JLabel.CENTER);
        this.imgLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.imgLabel.setVerticalTextPosition(JLabel.BOTTOM);
        this.imgLabel.setForeground(Color.BLACK);
        this.imgLabel.setBackground(new Color(1.0f, 1.0f, 1.0f, .7f));
        this.imgLabel.setBorder(new LineBorder(Color.BLACK, 1));
        this.imgLabel.setOpaque(true);
        this.add(this.imgLabel);

        //成功率
        this.percentLabel = new JLabel();
        this.percentLabel.setBorder(new LineBorder(Color.RED.brighter(), 2));
        this.percentLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.percentLabel);

        //お金
        this.moneyLabel = new JLabel();
        this.add(this.moneyLabel);

        //キャラ
        this.characterLabel = new JLabel();
        this.add(this.characterLabel);

        //日付
        this.dayLabel = new JLabel();
        //this.dayLabel.setBorder(new LineBorder(Color.BLACK, 3));
        this.add(this.dayLabel);

        //食べるボタン
        this.eatButton = new JButton("食べる");
        this.eatButton.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));
        this.eatButton.addActionListener(this);
        this.eatButton.setToolTipText("食べ物を食べ、確率でHPが回復か減少し1日進む");
        this.add(this.eatButton);

        //別の物ボタン
        this.changeButton = new JButton("別の物");
        this.changeButton.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));
        this.changeButton.addActionListener(this);
        this.changeButton.setToolTipText("食べ物変更できるが、次の食べ物の値段が1.1倍になる");
        this.add(changeButton);

        //食べないボタン
        this.dumpButton = new JButton("食べない");
        this.dumpButton.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));
        this.dumpButton.addActionListener(this);
        this.dumpButton.setToolTipText("HPが減少するが1日進む");
        this.add(this.dumpButton);

        //タイトルボタン
        this.titleButton = new JButton("タイトルに戻る");
        this.titleButton.setBackground(new Color(.9f, .9f, .9f, 0.8f));
        this.titleButton.addActionListener(this);
        this.add(this.titleButton);

        //強化ボタン
        this.reinforceButton = new JButton("強化する");
        this.reinforceButton.setBackground(new Color(.9f, .9f, .9f, 0.8f));
        this.reinforceButton.addActionListener(this);
        this.add(this.reinforceButton);

        

    }

    private FoodSelect foodGet(){
        return foods[this.rand.nextInt(this.foods.length)];
    }


    @Override
    protected void paintComponent(Graphics g){
        this.dimension = this.getSize();
        super.paintComponent(g);

        //背景描画
		g.drawImage(this.food.getBackgroundImage(), 0, 0, dimension.width, dimension.height, null);

        //キャラ描画
        g.drawImage(this.food.getCharacterImage(), (int)this.dimension.width * 5 / 8, (int)this.dimension.height / 13, (int)this.dimension.width * 6 / 10, (int)(this.dimension.height * 1.3), null);

        //HPbar描画
        this.hpBar.setLocation((int)this.dimension.width * 2 / 5, (int)this.dimension.height / 40);
        this.hpBar.setSize(this.dimension.width / 2, this.dimension.height / 15);
        this.hpBar.setFont(new Font("MS Gothic", Font.BOLD, this.hpBar.getHeight() / 2));
        this.hpString.setFont(new Font("MS Gothic", Font.BOLD, this.hpBar.getHeight()));
        this.hpString.setLocation((int)this.dimension.width * 2 / 5 - this.hpString.getWidth(), (int)this.dimension.height / 40);

        //お金描画
        this.moneyLabel.setText("所持金:" + this.money + "円");
        this.moneyLabel.setFont(new Font("MS Gothic", Font.BOLD, (int)(this.hpBar.getHeight() * 0.7)));
        this.moneyLabel.setLocation((int)this.dimension.width * 1 / 5 - this.hpString.getWidth(), (int)this.dimension.height / 40);

        //アイテム描画
        this.imgLabel.setSize((int)this.dimension.width * 2 / 5, dimension.height * 1 / 2);
        this.imgLabel.setLocation((int)(this.dimension.getWidth() / 2 - this.imgLabel.getSize().width / 2), (int)(this.dimension.height / 8));
        this.imgLabel.setIcon(new ImageIcon(MainFrame.getScaledImage(this.food.getFoodImage(), (int)this.imgLabel.getSize().width * 2 / 3, (int)this.imgLabel.getSize().height * 3 / 4)));
        this.imgLabel.setFont(new Font("MS Gothic", Font.BOLD, (int)this.imgLabel.getSize().width / 15));
        this.imgLabel.setText(this.food.getName() + "(値段:" + this.food.getPrice() + "円)");

        //成功率描画
        this.percentLabel.setVisible(this.successRateAppear);
        this.percentLabel.setSize(this.dimension.width / 4, this.dimension.height / 7);
        this.percentLabel.setLocation(this.dimension.width / 2 - this.percentLabel.getWidth() / 2, dimension.height * 13 / 20);
        this.percentLabel.setFont(new Font("MS Gothic", Font.BOLD, (int)this.percentLabel.getSize().width / 8));
        this.percentLabel.setText("成功率 " + this.food.getSuccessRate() + "%");
        if(this.food.getSuccessRate() >= 50){
            this.percentLabel.setForeground(Color.BLACK.brighter());
        }else if(this.food.getSuccessRate() <= 20){
            this.percentLabel.setForeground(Color.RED.brighter());
        }else{
            this.percentLabel.setForeground(Color.ORANGE.brighter());
        }
        
        //日付描画
        this.dayLabel.setLocation(this.dimension.width / 100, this.dimension.height / 100);
        this.dayLabel.setText("DAY:" + this.day);
        this.dayLabel.setFont(new Font("MS Gothic", Font.BOLD, (int)this.dimension.height / 15));

        //食べないボタン描画
        this.dumpButton.setSize(this.dimension.width / 7, (int)this.dimension.height / 8);
        this.dumpButton.setLocation(this.dimension.width * 7 / 10 - this.dumpButton.getWidth() / 2, dimension.height * 16 / 20);
        this.dumpButton.setFont(new Font("MS Gothic", Font.BOLD, this.dimension.width / 40));

        //食べるボタン描画
        this.eatButton.setSize(this.dimension.width / 7, (int)this.dimension.height / 8);
        this.eatButton.setLocation(this.dimension.width * 3 / 10 - this.eatButton.getWidth() / 2, this.dimension.height * 16 / 20);
        this.eatButton.setFont(new Font("MS Gothic", Font.BOLD, this.dimension.width / 40));

        //別の物ボタン描画
        this.changeButton.setSize(this.dimension.width / 7, (int)this.dimension.height / 8);
        this.changeButton.setLocation(this.dimension.width / 2 - this.changeButton.getWidth() / 2, dimension.height * 16 / 20);
        this.changeButton.setFont(new Font("MS Gothic", Font.BOLD, this.dimension.width / 40));

        //タイトルボタン描画
        this.titleButton.setSize(this.dimension.width / 7, (int)this.dimension.height / 10);
        this.titleButton.setLocation(this.dimension.width / 100, this.dimension.height * 1 / 8);
        this.titleButton.setFont(new Font("MS Gothic", Font.BOLD, this.titleButton.getWidth() / 10));

        //強化ボタン描画
        this.reinforceButton.setSize(this.dimension.width / 7, (int)this.dimension.height / 10);
        this.reinforceButton.setLocation(this.dimension.width / 100, this.dimension.height * 2 / 8);
        this.reinforceButton.setFont(new Font("MS Gothic", Font.BOLD, (int)this.reinforceButton.getWidth() / 6));

        //強化パネル描画
        this.reinforcePanle.setBounds(0, 0, this.dimension.width, this.dimension.height);

        //タイトルback警告
        this.warningPanel.setBounds(0, 0, this.dimension.width, this.dimension.height);

        //再描画
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "食べる":

                this.score += this.food.getScore() * (this.day / 10 + 1);

                int rate = this.rand.nextInt(100) + 1;
                if(rate <= this.food.getSuccessRate()){
                    if(this.hp == this.maxHp){
                        this.maxHp += this.food.getCure() / 10;
                        this.hp = this.maxHp;
                        this.hpBar.setMaxHp(this.maxHp);
                    }else{
                        this.hp += this.food.getCure();
                        if(this.hp > this.maxHp)this.hp = this.maxHp;
                    }
                }else{
                    this.hp -= (rate - this.food.getSuccessRate() + this.damegeBase) * this.food.getDamege() / 100;
                }
                this.money -= this.food.getPrice() * this.changeCost / 100;
                this.changeCost = 100;
                this.day++;
                this.hpBar.setHp(this.hp);
                this.food = foodGet();
                if(this.day % 30 == 0)this.money += income;
                if(this.hp <= 0 || this.money < 0){
                    this.frame.scorePanel.setScore((int)this.score, this.maxHp, this.day);
                    this.frame.setPanle(PanelSelect.SCORE);
                }

                break;

            case "別の物":
                this.changeCost+= 10;
                this.food = foodGet();
                break;
            
            case "食べない":
                this.hp -= this.maxHp / this.dumpRatio;
                this.day++;
                this.hpBar.setHp(this.hp);
                this.food = foodGet();
                if(this.day % 30 == 0)this.money += income;
                if(this.hp <= 0 || this.money < 0){
                    this.frame.scorePanel.setScore((int)this.score, this.maxHp, this.day);
                    this.frame.setPanle(PanelSelect.SCORE);
                }

                break;

            case "強化する":
                this.reinforcePanle.setVisible(true);
                break;

            case "タイトルに戻る":
                this.warningPanel.setPanelDisplay(true);
                break;

            default:
                break;

        }
        

    }

    
}