package myCode;

import com.sun.rowset.JdbcRowSetImpl;
import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.Locale;


public class artistController {
    //DATABASE AND EDITOR CONNECTION
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/music_artist_wiki?serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASS = "41219974121997";
    private JdbcRowSet rowSet = null;
    private JdbcRowSet rowSet2 = null;

    //CREATES THE OBJECT THAT CONTROLS THE INFORMATION OF EACH REGISTRATION OF THE DATABASE
    public artistController() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(DB_USER);
            rowSet.setPassword(DB_PASS);
            rowSet.setCommand("SELECT * FROM artists");
            rowSet.execute();

            Class.forName(JDBC_DRIVER);
            rowSet2 = new JdbcRowSetImpl();
            rowSet2.setUrl(DB_URL);
            rowSet2.setUsername(DB_USER);
            rowSet2.setPassword(DB_PASS);
            rowSet2.setCommand("SELECT * FROM ratings");
            rowSet2.execute();


        }catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }


        public artists create(artists p) {

            try {
                rowSet.moveToInsertRow();
                rowSet.updateString("Artist_name", p.getName());
                rowSet.updateString("genre", p.getGenre());
                rowSet.updateString("description", p.getDescription());
                rowSet.updateString("discography", p.getDiscography());
                rowSet.insertRow();
                rowSet.moveToCurrentRow();

                rowSet2.moveToInsertRow();
                rowSet2.updateString("Artist_name", p.getName());
                rowSet2.updateString("Rating", p.getRating());
                rowSet2.insertRow();
                rowSet2.moveToCurrentRow();

            } catch (SQLException ex) {
                try {
                    rowSet.rollback();
                    rowSet2.rollback();
                    p = null;
                } catch (SQLException e) {

                }
                ex.printStackTrace();
            }
            return p;
        }

        public artists update(artists p) {
            try {
                rowSet.updateString("genre", p.getGenre());
                rowSet.updateString("description", p.getDescription());
                rowSet.updateString("discography", p.getDiscography());
                rowSet.updateRow();
                rowSet.moveToCurrentRow();
                if (rowSet2.getString(1).equals(p.getName())) {
                    rowSet2.updateString("Artist_name", p.getName());
                    rowSet2.updateString("Rating", p.getRating());
                    rowSet2.updateRow();
                    rowSet2.moveToCurrentRow();
                }
                else {
                    rowSet2.moveToInsertRow();
                    rowSet2.updateString("Artist_name", p.getName());
                    rowSet2.updateString("Rating", p.getRating());
                    rowSet2.insertRow();
                    rowSet2.moveToCurrentRow();
                }


            } catch (SQLException ex) {
                try {
                    rowSet.rollback();
                    rowSet2.rollback();
                } catch (SQLException e) {

                }
                ex.printStackTrace();
            }
            return p;
        }

        public boolean delete(artists p) {
            try {

                rowSet.first();
                rowSet2.first();

                for (int i=0;i< 500;i++) {
                    if(rowSet.getString(1).equals(p.getName())) {

                        rowSet.deleteRow();
                        rowSet2.deleteRow();
                        break;


                    }
                    else {
                        rowSet.next();
                        rowSet2.next();
                        if (rowSet.isAfterLast()){
                            return false;
                        }
                    }

                }

                //rowSet.moveToCurrentRow();
            } catch (SQLException ex) {
                try {
                    rowSet.rollback();
                    rowSet2.rollback();
                }catch (SQLException e) { }
                ex.printStackTrace();
            }
            return true;

        }

        public artists moveFirst() {
            artists p = new artists();
            try {
                rowSet.first();

                p.setName(rowSet.getString("Artist_name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));
                if (rowSet2.first()==false) {
                    p.setRating("");
                }
                else {
                    p.setRating(rowSet2.getString("Rating"));
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists moveLast() {
            artists p = new artists();
            try {
                rowSet.last();
                rowSet2.last();

                p.setName(rowSet.getString("Artist_name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));
                p.setRating(rowSet2.getString("Rating"));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists moveNext() {
            artists p = new artists();
            try {
                if (rowSet.isLast()){
                    rowSet.first();
                    rowSet2.first();
                }
                else {
                    if (rowSet.next() == false) {
                        rowSet.previous();
                    }
                    if (rowSet2.next() == false) {
                        rowSet2.previous();
                    }

                }
                p.setName(rowSet.getString("Artist_name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));
                p.setRating(rowSet2.getString(2));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists movePrevious() {
            artists p = new artists();
            try {
                if (rowSet.isFirst()){
                    rowSet.last();
                    rowSet2.last();
                }
                else {
                    if (rowSet.previous() == false){
                        rowSet.next();
                    }
                    if (rowSet2.previous() == false){
                        rowSet2.next();
                    }

                }

                p.setName(rowSet.getString("Artist_name"));
                p.setGenre(rowSet.getString("genre"));
                p.setDescription(rowSet.getString("description"));
                p.setDiscography(rowSet.getString("discography"));
                p.setRating(rowSet2.getString("Rating"));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

        public artists getCurrent() {
            artists p = new artists();
            try {
                rowSet.moveToCurrentRow();
                rowSet2.moveToCurrentRow();
                if(!(rowSet.isAfterLast())) {
                    p.setName(rowSet.getString("Artist_name"));
                    p.setGenre(rowSet.getString("genre"));
                    p.setDescription(rowSet.getString("description"));
                    p.setDiscography(rowSet.getString("discography"));
                    p.setRating(rowSet2.getString("Rating"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return p;
        }

    public artists search(artists p) {

        try {
            rowSet.first();
            rowSet2.first();
            for (int i=0;i< 1000;i++) {
                if(rowSet.getString(1).toLowerCase().equals(p.getName().toLowerCase())) {
                    p.setName(rowSet.getString("Artist_name"));
                    p.setGenre(rowSet.getString("genre"));
                    p.setDescription(rowSet.getString("description"));
                    p.setDiscography(rowSet.getString("discography"));
                    p.setRating(rowSet2.getString("Rating"));
                    break;
                }
                else {
                    rowSet.next();
                    rowSet2.next();

                    if (rowSet.isAfterLast()){
                        return null;
                    }
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }


    public JTable show_all() {

        String[] column_names = new String[4];

        column_names[0] = "Artist name";
        column_names[1] = "Genre";
        artists current = getCurrent();

        DefaultTableModel data_table= new DefaultTableModel();
        data_table.addColumn(column_names[0]);
        data_table.addColumn(column_names[1]);



        try{
            rowSet.first();
            while (!(rowSet.isAfterLast())) {

                String name =  rowSet.getString("Artist_name");
                String genre = rowSet.getString("genre");

                data_table.addRow(new Object[]{name, genre});

                rowSet.next();
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        JTable table = new JTable();
        table.setModel(data_table);
        try {
            rowSet.first();
            while (!(rowSet.isAfterLast())) {
                if (current.getName().equals(rowSet.getString(1))) {
                    break;

                } else {
                    rowSet.next();
                }
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (table);
    }

    public JTable show_genre(artists p) {

        String[] column_names = new String[4];

        column_names[0] = "Artist name";
        column_names[1] = "Genre";
        artists current = getCurrent();

        DefaultTableModel data_table= new DefaultTableModel();
        data_table.addColumn(column_names[0]);
        data_table.addColumn(column_names[1]);
        try{
            rowSet.first();
            while (!(rowSet.isAfterLast())) {
                   String str = p.getGenre().replace(' ','-');
                if(rowSet.getString("genre").toLowerCase().contains(str.toLowerCase())) {

                    String name = rowSet.getString("Artist_name");
                    String genre = rowSet.getString("genre");


                    data_table.addRow(new Object[]{name, genre});
                }

                rowSet.next();
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        JTable table = new JTable();
        table.setModel(data_table);
        try {
            rowSet.first();
            while (!(rowSet.isAfterLast())) {
                if (current.getName().equals(rowSet.getString(1))) {
                    break;

                } else {
                    rowSet.next();
                }
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return(table);

    }

    public JTable show_ratings() {

        String[] column_names = new String[4];

        column_names[0] = "Artist name";
        column_names[1] = "Rating";
        artists current = getCurrent();

        DefaultTableModel data_table = new DefaultTableModel();
        data_table.addColumn(column_names[0]);
        data_table.addColumn(column_names[1]);

        try{
            rowSet2.first();
            while (!(rowSet2.isAfterLast())) {

                String name =  rowSet2.getString("Artist_name");
                String genre = rowSet2.getString("Rating");


                data_table.addRow(new Object[]{name, genre});

                rowSet2.next();
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        JTable table = new JTable();
        table.setModel(data_table);
        try {
            rowSet2.first();
            while (!(rowSet2.isAfterLast())) {
                if (current.getName().equals(rowSet2.getString(1))) {
                    break;

                } else {
                    rowSet2.next();
                }
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return(table);

    }


    public JTable show_favourites() {

        String[] column_names = new String[4];

        column_names[0] = "Artist name";
        column_names[1] = "Genre";
        column_names[2] = "Rating";

        artists current = getCurrent();

        DefaultTableModel data_table= new DefaultTableModel();
        data_table.addColumn(column_names[0]);
        data_table.addColumn(column_names[1]);
        data_table.addColumn(column_names[2]);
        try{
            rowSet2.first();
            rowSet.first();

            while (!(rowSet2.isAfterLast())) {
                int rating = rowSet2.getInt(2);
                if (rating > 7) {

                    String name = rowSet2.getString("Artist_name");
                    String Rating = rowSet2.getString("Rating");
                    String genre = rowSet.getString("genre");

                    data_table.addRow(new Object[]{name, genre, Rating});
                }
                rowSet2.next();
                rowSet.next();

            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        JTable table = new JTable();
        table.setModel(data_table);
        try {
            rowSet2.first();
            rowSet.first();
            while (!(rowSet2.isAfterLast())) {
                if (current.getName().equals(rowSet2.getString(1))) {
                    break;

                } else {
                    rowSet2.next();
                    rowSet.next();
                }
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return(table);

    }








}

