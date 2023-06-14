import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class WarningPanel extends SubPanel implements ActionListener{
    private JLabel label = new JLabel();
    private JButton yesButton = new JButton();
    private JButton noButton = new JButton();
    private JPanel panel = new JPanel();
    private GamePanle gamePanle;

    WarningPanel(GamePanle gamePanle, String text){
        super();
        this.gamePanle = gamePanle;
        
        this.setPanel(this.panel);

        this.panel.setBackground(Color.LIGHT_GRAY);
        this.panel.setBorder(new LineBorder(Color.PINK, 3));
        //this.panel.setLayout(new FlowLayout());
        this.add(this.panel);

        this.label.setText(text);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.yesButton.setText("YES");
        this.yesButton.setHorizontalTextPosition(JButton.CENTER);
        this.yesButton.setBackground(new Color(0.5f, 1.0f, 0.5f, .7f));
        this.yesButton.addActionListener(this);
        this.panel.add(this.yesButton);

        this.noButton.setText("NO");
        this.noButton.setHorizontalTextPosition(JButton.CENTER);
        this.noButton.setBackground(new Color(1.0f, 0.3f, 0.3f, .7f));
        this.noButton.addActionListener(this);
        this.panel.add(this.noButton);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //中央パネル描画
        this.panel.setSize(this.getWidth() / 2, this.getHeight() / 2);
        this.panel.setLocation(this.getWidth() / 2 - this.panel.getWidth() / 2, this.getHeight() / 2 - this.panel.getHeight() / 2);

        //テキスト描画
        this.label.setSize(this.panel.getWidth(), this.panel.getHeight() / 2);
        this.label.setLocation(this.panel.getWidth() / 2 - this.label.getWidth() / 2, 0);
        this.label.setFont(new Font("MS Gothic", Font.BOLD, this.label.getWidth() / 15));

        //noボタン描画
        this.noButton.setSize(this.panel.getWidth() / 5, this.panel.getHeight() / 5);
        this.noButton.setLocation(this.panel.getWidth() / 3 - this.noButton.getWidth() / 2, this.panel.getHeight() * 2 / 3);
        this.noButton.setFont(new Font("MS Gothic", Font.BOLD, this.noButton.getWidth() / 3));

        //yesボタン描画
        this.yesButton.setSize(this.panel.getWidth() / 5, this.panel.getHeight() / 5);
        this.yesButton.setLocation(this.panel.getWidth() * 2 / 3 - this.yesButton.getWidth() / 2, this.panel.getHeight() * 2 / 3);
        this.yesButton.setFont(new Font("MS Gothic", Font.BOLD, this.noButton.getWidth() / 3));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "YES":
                this.gamePanle.frame.setPanle(PanelSelect.TITLE);
                break;
            
            case "NO":
                this.setPanelDisplay(false);
                break;
            default:
                break;
        }
        
    }
}
