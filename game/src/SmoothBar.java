import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class SmoothBar extends JProgressBar implements ActionListener{
    private int hp, maxHp, curentHp;
    private Timer timer;

    SmoothBar(){
        this.setMinimum(0);
        this.setStringPainted(true);
        this.setBorder(new LineBorder(Color.BLACK, 2));

        this.setForeground(Color.GREEN);
        this.setBackground(Color.GRAY);
        this.setUI(new BasicProgressBarUI() {
            @Override protected Color getSelectionForeground() {
                return Color.WHITE.brighter();
            }

            @Override protected Color getSelectionBackground() {
                return Color.WHITE;
            }
        });

        this.timer = new Timer(1 , this);
    }

    public void setHp(int hp){
        this.hp = hp;
        timer.start();
    }

    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
        this.setMaximum(this.maxHp);
    }

    public void setCurentHp(int curentHp){
        this.curentHp = hp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.curentHp > this.hp){
            this.curentHp -= 5;
            if(this.curentHp < this.hp)this.curentHp = this.hp;
        }else if(this.curentHp < this.hp){
            this.curentHp += 5;
            if(this.curentHp > this.hp)this.curentHp = this.hp;
        }else{
            timer.stop();
        }
        this.setString(this.curentHp + "/" + this.maxHp);
        this.setValue(this.curentHp);

        if(this.curentHp > maxHp / 2){
            this.setForeground(Color.GREEN);
            this.setBackground(Color.GRAY);
            this.setUI(new BasicProgressBarUI() {
                @Override protected Color getSelectionForeground() {
                    return Color.WHITE.brighter();
                }

                @Override protected Color getSelectionBackground() {
                    return Color.WHITE;
                }
            });
        }else if(this.curentHp < maxHp / 10){
            this.setForeground(Color.RED);
            this.setBackground(Color.GRAY);
            this.setUI(new BasicProgressBarUI() {
                @Override protected Color getSelectionForeground() {
                    return Color.WHITE.brighter();
                }

                @Override protected Color getSelectionBackground() {
                    return Color.WHITE;
                }
            });
        }else{
            this.setForeground(Color.ORANGE);
            this.setBackground(Color.GRAY);
            this.setUI(new BasicProgressBarUI() {
                @Override protected Color getSelectionForeground() {
                    return Color.WHITE.brighter();
                }

                @Override protected Color getSelectionBackground() {
                    return Color.WHITE;
                }
            });
        }
    }
    
}