package myCode;

import javax.swing.*;
import java.awt.*;
//POP UPS A WINDOW THAT SHOWS THE TABLE OF THE DATABASE WITH OR NOT FILTERS
public class Views {

        public static void show(JTable table){
            JFrame f = new JFrame();
            f.add(table.getTableHeader(), BorderLayout.PAGE_START);
            f.add(table, BorderLayout.CENTER);

            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
}
