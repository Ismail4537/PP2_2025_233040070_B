import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class test {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Dropdown Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sample data and column names
        Object[][] data = {
                { "Kathy", "Smith", "Snowboarding" },
                { "John", "Doe", "Rowing" },
                { "Sue", "Black", "Knitting" },
                { "Jane", "White", "Textile" },
        };
        String[] columnNames = { "First Name", "Last Name", "Sport" };

        // Create the table with the data model
        JTable table = new JTable(data, columnNames);

        // --- Setup the Dropdown Editor for the "Sport" Column ---

        // Options for the dropdown
        String[] sports = { "Snowboarding", "Rowing", "Knitting", "Textile", "Diving" };

        // Get the "Sport" column
        TableColumn sportColumn = table.getColumnModel().getColumn(2);

        // Create a JComboBox with the options
        JComboBox comboBox = new JComboBox(sports);

        // Set the JComboBox as the cell editor for the column
        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        // Optional: You can also set a custom renderer for the column
        // sportColumn.setCellRenderer(new MyComboBoxRenderer(sports)); // (requires
        // creating MyComboBoxRenderer class)

        // Add the table to a scroll pane and display it
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(400, 150);
        frame.setVisible(true);
    }
}
