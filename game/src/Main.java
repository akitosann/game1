import javax.swing.SwingUtilities;

public class Main {
    static final String TITLE = "たべつづける";
    static MainFrame frame;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                frame = new MainFrame();
                frame.addPanels();
                frame.setPanle(PanelSelect.TITLE);
                frame.setVisible(true);
            }
        });
        


    }
}
