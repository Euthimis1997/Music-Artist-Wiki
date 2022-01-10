package myCode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
//CREATION OF THE APP FRAME

class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}



public class AppMain  {


    public static void main(String[] args) throws IOException {
        JFrame f=new JFrame();
        f.setContentPane(new ImagePanel(ImageIO.read(new File("fonto.jpg")).getScaledInstance(1920, 1080, Image.SCALE_SMOOTH)));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(new ArtistUI(), BorderLayout.CENTER);
        f.setSize(800, 600);
        f.setVisible(true);

    }

}


