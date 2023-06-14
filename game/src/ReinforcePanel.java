import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

class ReinforcePanle extends SubPanel implements ActionListener{
    private JButton income = new JButton(), dumpRaito = new JButton(), damegeBase = new JButton(), successRateAppear = new JButton(), changePrice = new JButton(), maxHp = new JButton();
    private int incomeCost, dumpRaitoCost, damegeBaseCost, successRateAppearCost, changePriceCost, maxHpCost;
    private GamePanle gamePanle;
    private JPanel panel = new JPanel();

    public void configReset(){
        this.incomeCost = 5000;
        this.dumpRaitoCost = 9000;
        this.damegeBaseCost = 10000;
        this.successRateAppearCost = 100000;
        this.changePriceCost = 10000;
        this.maxHpCost = 500;

        this.income.setEnabled(true);
        this.dumpRaito.setEnabled(true);
        this.damegeBase.setEnabled(true);
        this.successRateAppear.setEnabled(true);
        this.changePrice.setEnabled(true);
        this.maxHp.setEnabled(true);

        this.income.setText("<html><body style = text-align:center>収入増加<br>(費用" + this.incomeCost + "円)</body></html>");
        this.dumpRaito.setText("<html><body style = text-align:center>食べなかった時のダメージ減少<br>(費用" + this.dumpRaitoCost + "円)</body></html>");
        this.damegeBase.setText("<html><body style = text-align:center>失敗時のダメージ減少<br>(費用" + this.damegeBaseCost + "円)</body></html>");
        this.changePrice.setText("<html><body style = text-align:center>食べ物の値段減少<br>(費用" + this.changePriceCost + "円)</body></html>");
        this.maxHp.setText("<html><body style = text-align:center>最大HP増加<br>(費用" + this.maxHpCost + "円)</body></html>");
        this.successRateAppear.setText("<html><body style = text-align:center>成功率が見えるようになる<br>(費用" + this.successRateAppearCost + "円)</body></html>");

        this.updateBtn();
        this.setPanelDisplay(false);
    }

    ReinforcePanle(GamePanle gamePanle){
        this.gamePanle = gamePanle;
        this.configReset();

        this.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));

        this.panel.setLayout(new GridLayout(2, 3));
        this.panel.setBackground(Color.LIGHT_GRAY);
        this.panel.setBorder(new LineBorder(Color.GRAY, 2));
        this.setPanel(this.panel);
        this.add(this.panel);

        
        this.income.setBackground(new Color(.0f, 1.0f, 1.0f));
        this.income.addActionListener(this);
        this.income.setActionCommand("income");
        this.panel.add(this.income);

        this.dumpRaito.setBackground(new Color(.0f, 1.0f, 1.0f));
        this.dumpRaito.addActionListener(this);
        this.dumpRaito.setActionCommand("dumpRaito");
        this.panel.add(this.dumpRaito);

        this.damegeBase.setBackground(new Color(.0f, 1.0f, 1.0f));
        this.damegeBase.addActionListener(this);
        this.damegeBase.setActionCommand("damegeBase");
        this.panel.add(this.damegeBase);

        this.changePrice.setBackground(new Color(.0f, 1.0f, 1.0f));
        this.changePrice.addActionListener(this);
        this.changePrice.setActionCommand("changePrice");
        this.panel.add(this.changePrice);

        this.maxHp.setBackground(new Color(.0f, 1.0f, 1.0f));
        this.maxHp.addActionListener(this);
        this.maxHp.setActionCommand("maxHp");
        this.panel.add(this.maxHp);

        this.successRateAppear.setBackground(new Color(.0f, 1.0f, 1.0f));
        this.successRateAppear.addActionListener(this);
        this.successRateAppear.setActionCommand("successRateAppear");
        this.panel.add(this.successRateAppear);
    }

    public void updateBtn(){
        //収入ボタン表示
        if(this.incomeCost <= this.gamePanle.money && this.income.getBackground() != Color.LIGHT_GRAY){
            this.income.setEnabled(true);
        }else{
            this.income.setEnabled(false);
        }
        //捨てた時ダメージ比率表示
        if(this.dumpRaitoCost <= this.gamePanle.money && this.dumpRaito.getBackground() != Color.LIGHT_GRAY){
            this.dumpRaito.setEnabled(true);
        }else{
            this.dumpRaito.setEnabled(false);
        }
        //失敗時ダメージ比率表示
        if(this.damegeBaseCost <= this.gamePanle.money && this.damegeBase.getBackground() != Color.LIGHT_GRAY){
            this.damegeBase.setEnabled(true);
        }else{
            this.damegeBase.setEnabled(false);
        }
        //値段表示
        if(this.changePriceCost <= this.gamePanle.money && this.changePrice.getBackground() != Color.LIGHT_GRAY){
            this.changePrice.setEnabled(true);
        }else{
            this.changePrice.setEnabled(false);
        }
        //HP増加表示
        if(this.maxHpCost <= this.gamePanle.money && this.maxHp.getBackground() != Color.LIGHT_GRAY){
            this.maxHp.setEnabled(true);
        }else{
            this.maxHp.setEnabled(false);
        }
        //成功率表示
        if(this.successRateAppearCost <= this.gamePanle.money && this.successRateAppear.getBackground() != Color.LIGHT_GRAY){
            this.successRateAppear.setEnabled(true);
        }else{
            this.successRateAppear.setEnabled(false);
        }

        
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //強化パネル描画
        this.panel.setPreferredSize(new Dimension(this.getWidth() * 2/ 3, this.getHeight() * 2 / 3));
        this.panel.setLocation(this.getWidth() / 2 - this.panel.getWidth() / 2, this.getHeight() / 2 - this.panel.getHeight() / 2);

        this.income.setFont(new Font("MS Gothic", Font.BOLD, (int)this.income.getWidth() / 15));
        this.dumpRaito.setFont(new Font("MS Gothic", Font.BOLD, (int)this.dumpRaito.getWidth() / 15));
        this.damegeBase.setFont(new Font("MS Gothic", Font.BOLD, (int)this.damegeBase.getWidth() / 15));
        this.changePrice.setFont(new Font("MS Gothic", Font.BOLD, (int)this.changePrice.getWidth() / 15));
        this.maxHp.setFont(new Font("MS Gothic", Font.BOLD, (int)this.maxHp.getWidth() / 15));
        this.successRateAppear.setFont(new Font("MS Gothic", Font.BOLD, (int)this.successRateAppear.getWidth() / 15));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "income":
                if(this.incomeCost <= this.gamePanle.money){
                    this.gamePanle.money -= this.incomeCost;
                    this.gamePanle.income += 5000;
                    this.incomeCost *= 2;
                    this.income.setText("<html><body style = text-align:center>収入増加<br>(費用" + this.incomeCost + "円)</body></html>");
                    if(this.gamePanle.income >= 50000){
                        this.income.setBackground(Color.LIGHT_GRAY);
                        this.income.setText("強化最大");
                    }
                }
                break;
            
            case "dumpRaito":
                if(this.dumpRaitoCost <= this.gamePanle.money){
                    this.gamePanle.money -= this.dumpRaitoCost;
                    this.gamePanle.dumpRatio++;
                    this.dumpRaitoCost *= 2;
                    this.dumpRaito.setText("<html><body style = text-align:center>食べなかった時のダメージ減少<br>(費用" + this.dumpRaitoCost + "円)</body></html>");
                    if(this.gamePanle.dumpRatio >= 7){
                        this.dumpRaito.setBackground(Color.LIGHT_GRAY);
                        this.dumpRaito.setText("強化最大");
                    }
                }
                break;
            
            case "damegeBase":
                if(this.damegeBaseCost <= this.gamePanle.money){
                    this.gamePanle.money -= this.damegeBaseCost;
                    this.gamePanle.damegeBase -= 10;
                    this.damegeBaseCost *= 2;
                    this.damegeBase.setText("<html><body style = text-align:center>失敗時のダメージ減少<br>(費用" + this.damegeBaseCost + "円)</body></html>");
                    if(this.gamePanle.damegeBase <= 10){
                        this.damegeBase.setBackground(Color.LIGHT_GRAY);
                        this.damegeBase.setText("強化最大");
                    }
                }
                break;

            case "changePrice":
                if(this.changePriceCost <= this.gamePanle.money){
                    this.gamePanle.money -= this.changePriceCost;
                    this.gamePanle.changeCost -= 10;
                    this.changePriceCost *= 2;
                    this.changePrice.setText("<html><body style = text-align:center>食べ物の値段減少<br>(費用" + this.changePriceCost + "円)</body></html>");
                    if(this.gamePanle.changeCost <= 10){
                        this.changePrice.setBackground(Color.LIGHT_GRAY);
                        this.changePrice.setText("強化最大");
                    }
                }
                break;

            case "maxHp":
                if(this.maxHpCost <= this.gamePanle.money){
                    this.gamePanle.money -= this.maxHpCost;
                    this.gamePanle.maxHp += 100;
                    this.gamePanle.hp += 100;
                    this.gamePanle.hpBar.setMaxHp(this.gamePanle.maxHp);
                    this.gamePanle.hpBar.setHp(this.gamePanle.hp);
                    this.maxHpCost *= 2;
                    this.maxHp.setText("<html><body style = text-align:center>最大HP増加<br>(費用" + this.maxHpCost + "円)</body></html>");
                }
                break;

            case "successRateAppear":
                if(this.successRateAppearCost <= this.gamePanle.money){
                    this.gamePanle.money -= this.successRateAppearCost;
                    this.gamePanle.successRateAppear = true;
                    this.successRateAppear.setBackground(Color.LIGHT_GRAY);
                    this.successRateAppear.setText("取得済み");

                }
                break;

            default:
                break;
        }
        this.updateBtn();
    }
}