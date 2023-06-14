import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class ScorePanel extends JPanel implements ActionListener{
    private JLabel scoreLabel = new JLabel();
    private JButton titleButton = new JButton();
    private int score = 100;
    private MainFrame frame;

    ScorePanel(MainFrame frame){
        this.frame = frame;
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, .0f));
        this.setOpaque(false);

        this.scoreLabel.setForeground(Color.RED);
        this.add(this.scoreLabel);

        this.titleButton.addActionListener(this);
        this.titleButton.setText("タイトルに戻る");
        this.titleButton.setBorder(new LineBorder(Color.BLACK, 3));
        this.titleButton.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));
        this.add(this.titleButton);
    }

    public void setScore(int score, int maxHp, int day){
        this.score = score + (maxHp - GamePanle.MAXHP) * day;
        this.scoreLabel.setText("Score:" + this.score);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //背景描画
        g.drawImage(BackgroundSelect.LIVING4.get(), 0, 0, this.getWidth(), this.getHeight(), null);

        //スコア表示
        this.scoreLabel.setText("Score:" + this.score);
        this.scoreLabel.setFont(new Font("MS Gothic", Font.BOLD, this.getHeight() / 8));
        this.scoreLabel.setLocation(this.getWidth() / 2 - this.scoreLabel.getWidth() / 2, this.getHeight() / 2 - this.scoreLabel.getHeight() / 2);

        //ボタン表示
        this.titleButton.setFont(new Font("MS Gothic", Font.BOLD, this.getHeight() / 8));
        this.titleButton.setLocation(this.getWidth() / 2 - this.titleButton.getWidth() / 2, this.getHeight() * 3 / 5);

        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setPanle(PanelSelect.TITLE);
    }

    
}
