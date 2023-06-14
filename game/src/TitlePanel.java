import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class TitlePanel extends JPanel implements ActionListener{
    private static final long serialVersionUID = 1L;
    private MainFrame frame;

    private Image background;
    private Dimension dimension;
    private JButton btnStart, btnExit;
    
    TitlePanel(MainFrame frame){

        this.frame = frame;
        //画像取得
        try {
			this.background = ImageIO.read(new File("img/TitleBackground.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

        //startボタン設定
        this.btnStart = new JButton("START");
        this.btnStart.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));
        this.btnStart.addActionListener(this);
        this.add(btnStart);

        //exitボタン設定
        this.btnExit = new JButton("EXIT");
        this.btnExit.setBackground(new Color(1.0f, 1.0f, 1.0f, .5f));
        this.btnExit.addActionListener(this);
        this.add(btnExit);
    }

        

    @Override
    protected void paintComponent(Graphics g){
        this.dimension = this.getSize();
        super.paintComponent(g);
        //背景描画
		g.drawImage(this.background, 0, 0, dimension.width, dimension.height, null);

        //startボタン描画
        this.btnStart.setLocation(dimension.width / 2 - this.btnStart.getWidth() / 2, dimension.height * 11 / 20);
        this.btnStart.setFont(new Font("MS Gothic", Font.BOLD, this.getWidth() / 18));
        //exitボタン描画
        this.btnExit.setLocation(dimension.width / 2 - this.btnExit.getWidth() / 2, dimension.height * 14 / 20);
        this.btnExit.setFont(new Font("MS Gothic", Font.BOLD, this.getWidth() / 18));
        
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "START":
                this.frame.gamePanle.configReset();
                this.frame.setPanle(PanelSelect.GAME);
                break;
            case "EXIT":
                System.exit(1);
                break;
            default:
                break;
        }
    }
}
