import java.awt.Color;
import java.awt.event.*;

import javax.swing.JPanel;

public class SubPanel extends JPanel implements MouseListener{
    private JPanel panel;

    SubPanel(){
        this.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));
        this.addMouseListener(this);
    }

    public void setPanel(JPanel panel){
        this.panel = panel;
    }

    public void setPanelDisplay(Boolean tmp){
        this.setVisible(tmp);
        this.setEnabled(tmp);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.panel.getLocation().getX() > e.getX() || this.panel.getLocation().getX() + this.panel.getWidth() < e.getX() ||
        this.panel.getLocation().getY() > e.getY() || this.panel.getLocation().getY() + this.panel.getHeight() < e.getY()){
            this.setPanelDisplay(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
}
