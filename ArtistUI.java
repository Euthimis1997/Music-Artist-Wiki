package myCode;


import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
//CREATION OF THE APP PANEL


class PanelBackground extends JPanel {
    private Image image;

    public void ImagePanel(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}


public class ArtistUI extends JPanel {

    private JTextField nameField = new JTextField(45);
    private JTextField genreField = new JTextField(45);
    private JTextArea descriptionField = new JTextArea(5,140);
    private JTextArea discographyField = new JTextArea(5,140);
    private JTextField ratingField = new JTextField(45);

    private JButton createButton = new JButton("New...");
    private JButton searchButton = new JButton("Search by name");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
    private JButton firstButton = new JButton("First");
    private JButton prevButton = new JButton("Previous");
    private JButton nextButton = new JButton("Next");
    private JButton lastButton = new JButton("Last");
    private JButton genreButton = new JButton("Show by genre");
    private JButton showButton = new JButton("Show all");
    private JButton show_ratingButton = new JButton("Show ratings");
    private JButton show_favouritesButton = new JButton("Show favourites");
    private JButton quitButton = new JButton("Quit");
    //... updateButton, deleteButton, firstButton, prevButton, nextButton,
    //...lastButton,quitButton
    private artistController bean = new artistController();

    public ArtistUI() throws IOException {
        setBorder(new TitledBorder
                (new EtchedBorder(), "Artist Details"));
        setLayout(new BorderLayout(5, 1));
        add(initFields(), BorderLayout.CENTER);
        add(initButtons(), BorderLayout.SOUTH);
        setFieldData(bean.moveFirst());
    }

    private JPanel initButtons() {
        //JPanel panel = new JPanel();

        PanelBackground panel = new PanelBackground();
        try {
            panel.ImagePanel(ImageIO.read(new File("fonto.jpg")).getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        panel.setBackground(Color.black);

        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //create button
        panel.add(createButton);
        Color myColor = new Color(0, 172, 172);
        createButton.setBackground(myColor);
        createButton.setForeground(Color.white);
        createButton.addActionListener( new ButtonHandler());
        //search button
        panel.add(searchButton);
        searchButton.setBackground(myColor);
        searchButton.setForeground(Color.white);
        searchButton.addActionListener( new ButtonHandler());
        //update button
        panel.add(updateButton);
        updateButton.setBackground(myColor);
        updateButton.setForeground(Color.white);
        updateButton.addActionListener(new ButtonHandler());
        //delete button
        panel.add(deleteButton);
        deleteButton.setBackground(myColor);
        deleteButton.setForeground(Color.white);
        deleteButton.addActionListener(new ButtonHandler());
        //first button
        panel.add(firstButton);
        firstButton.setBackground(myColor);
        firstButton.setForeground(Color.white);
        firstButton.addActionListener(new ButtonHandler());
        //previous button
        panel.add(prevButton);
        prevButton.setBackground(myColor);
        prevButton.setForeground(Color.white);
        prevButton.addActionListener(new ButtonHandler());
        //next button
        panel.add(nextButton);
        nextButton.setBackground(myColor);
        nextButton.setForeground(Color.white);
        nextButton.addActionListener(new ButtonHandler());
        //last button
        panel.add(lastButton);
        lastButton.setBackground(myColor);
        lastButton.setForeground(Color.white);
        lastButton.addActionListener(new ButtonHandler());
        //show by genre button
        panel.add(genreButton);
        genreButton.setBackground(myColor);
        genreButton.setForeground(Color.white);
        genreButton.addActionListener( new ButtonHandler());
        //show all button
        panel.add(showButton);
        showButton.setBackground(myColor);
        showButton.setForeground(Color.white);
        showButton.addActionListener( new ButtonHandler());
        //show ratings button
        panel.add(show_ratingButton);
        show_ratingButton.setBackground(myColor);
        show_ratingButton.setForeground(Color.white);
        show_ratingButton.addActionListener( new ButtonHandler());
        //show favourites button
        panel.add(show_favouritesButton);
        show_favouritesButton.setBackground(myColor);
        show_favouritesButton.setForeground(Color.white);
        show_favouritesButton.addActionListener( new ButtonHandler());
        //quit button
        panel.add(quitButton);
        quitButton.setBackground(myColor);
        quitButton.setForeground(Color.white);
        quitButton.addActionListener( new ButtonHandler());
        return panel;
    }

    private JPanel initFields() throws IOException{
        PanelBackground panel = new PanelBackground();
        try {
            panel.ImagePanel(ImageIO.read(new File("fonto.jpg")).getScaledInstance(1920, 1080, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        panel.setLayout(new MigLayout());
        panel.setPreferredSize( new Dimension( 640, 480 ) );
        //artist
        JLabel artistLabel = new JLabel("Artist");                      //ftiaxnw to label Artist p exei to onoma kai meta kanw lefka ta grammata
        artistLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        artistLabel.setForeground(Color.white);
        panel.add(artistLabel, "align label");
        Color myColor = new Color(55, 28, 93);
        nameField.setBackground(myColor);                                   //vazw to xrwma mesa sto field kai kanw lefka ta grammata
        nameField.setForeground(Color.white);
        panel.add(nameField, "wrap");                               //kanw add sto panel mou to jlabel pou eftiaksa

        //genre
        JLabel genreLabel = new JLabel("Genre");
        genreLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        genreLabel.setForeground(Color.white);
        panel.add(genreLabel, "align label");
        genreField.setBackground(myColor);
        genreField.setForeground(Color.white);
        panel.add(genreField, "wrap");

        //description
        descriptionField.setLineWrap(true);
        descriptionField.setBorder(new LineBorder((Color.white)));
        JLabel descriptionLabel = new JLabel("Description");
        descriptionLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        descriptionLabel.setForeground(Color.white);
        panel.add(descriptionLabel, "align label");
        descriptionField.setBackground(myColor);
        descriptionField.setForeground(Color.white);
        panel.add(descriptionField, "wrap");

        //discography
        discographyField.setLineWrap(true);
        discographyField.setBorder(new LineBorder((Color.white)));
        JLabel discographyLabel = new JLabel("Discography");
        discographyLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        discographyLabel.setForeground(Color.white);
        panel.add(discographyLabel, "align label");
        discographyField.setBackground(myColor);
        discographyField.setForeground(Color.white);
        panel.add(discographyField, "wrap");

        //rating
        JLabel ratingLabel = new JLabel("Rating (out of 10)");
        ratingLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
        ratingLabel.setForeground(Color.white);
        panel.add(ratingLabel, "align label");
        ratingField.setBackground(myColor);
        ratingField.setForeground(Color.white);
        panel.add(ratingField, "wrap");


        return panel;
    }

    private artists getFieldData() {
        artists p = new artists();
        p.setName(nameField.getText());
        p.setGenre(genreField.getText());
        p.setDescription(descriptionField.getText());
        p.setDiscography(discographyField.getText());
        p.setRating(ratingField.getText());

        return p;
    }

    private void setFieldData(artists p) {
        nameField.setText(String.valueOf(p.getName()));
        genreField.setText(p.getGenre());
        descriptionField.setText(p.getDescription());
        discographyField.setText(p.getDiscography());
        ratingField.setText(p.getRating());
    }

    private boolean isEmptyFieldData() {
        return (nameField.getText().trim().isEmpty()
                && genreField.getText().trim().isEmpty()
                && descriptionField.getText().trim().isEmpty()
                && discographyField.getText().trim().isEmpty()
                && ratingField.getText().trim().isEmpty());
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            artists p = getFieldData();
            switch (e.getActionCommand()) {
                case "Save" -> {
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot create an empty record");
                        return;
                    }
                    else {
                        if (bean.search(p) != null) {
                            JOptionPane.showMessageDialog(null,
                                    "Artist already exists.");
                        } else {
                            if (bean.create(p) != null) {

                                JOptionPane.showMessageDialog(null,
                                        "New artist created successfully.");
                            }
                        }
                    }
                    setFieldData(bean.moveFirst());
                    createButton.setText("New...");
                }
                case "New..." -> {
                    p.setName("");
                    p.setGenre("");
                    p.setDescription("");
                    p.setDiscography("");
                    p.setRating("");
                    setFieldData(p);
                    createButton.setText("Save");
                }
                case "Search by name" -> {
                    p = bean.search(p);
                    if (p!= null){
                        setFieldData(p);
                        JOptionPane.showMessageDialog(null,
                                "Artist was found.");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,
                                "Artist was not found.");
                    }

                    createButton.setText("New...");

                }
                case "Update" -> {
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot update an empty record");
                        return;
                    }
                    if (bean.update(p) != null)
                        JOptionPane.showMessageDialog(
                                null, "The artist  :" + String.valueOf(p.getName()
                                        + " was updated successfully"));
                    createButton.setText("New...");
                }
                case "Delete" -> {
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null,
                                "Cannot delete an empty record");
                        return;
                    }
                    if(bean.delete(p)){
                        setFieldData(bean.moveNext());
                        JOptionPane.showMessageDialog(
                                null, "The artist  :"
                                        + String.valueOf(p.getName()
                                        + " was deleted successfully"));
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                null, "The artist :"
                                        + String.valueOf(p.getName()
                                        + " was not found"));
                    }

                    createButton.setText("New...");
                }
                case "First" -> {
                    setFieldData(bean.moveFirst());
                    createButton.setText("New...");
                }
                case "Previous" -> {
                    setFieldData(bean.movePrevious());
                    createButton.setText("New...");
                }

                case "Next" -> {
                    setFieldData(bean.moveNext());
                    createButton.setText("New...");
                }
                case "Last" -> {
                    setFieldData(bean.moveLast());
                    createButton.setText("New...");
                }
                case "Show by genre" -> {
                    JTable genre_table = bean.show_genre(p);
                    Views.show(genre_table);
                    createButton.setText("New...");
                }
                case "Show all" -> {
                    JTable table = bean.show_all();
                    Views.show(table);
                    createButton.setText("New...");
                }
                case "Show ratings" -> {
                    JTable table = bean.show_ratings();
                    Views.show(table);
                    createButton.setText("New...");
                }
                case "Show favourites" -> {
                    JTable table = bean.show_favourites();
                    Views.show(table);
                    createButton.setText("New...");
                }

                case "Quit" -> System.exit(0);
                default -> JOptionPane.showMessageDialog(null,
                        "invalid command");
            }
        }
    }
}
