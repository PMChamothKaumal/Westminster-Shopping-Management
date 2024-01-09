package GUI;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Arrays;

public class ShoppingGui extends JFrame implements ActionListener {
    private JPanel mainPanel, topPanel, topPanel1, topPanel2, middlePanel, bottomPanel;
    private JLabel label, label2, label1, label3, label4;
    private JComboBox<String> combobox;
    private JButton button;
    private JTable table = new JTable(model);
    static DefaultTableModel model = new DefaultTableModel();
    private static CartGui cartGui;
    public boolean i = true;
    public int col = -1;

    public void Load(String one){
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("Westminster_shopping.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] components = line.split(",");
                if (components.length == 7) {
                    String productId = components[1].trim();
                    String productName = components[2].trim();
                    int itemQty = Integer.parseInt(components[3].trim());
                    double price = Double.parseDouble(components[4].trim());
                    String category = components[0].trim();


                    if (category.equals("Electronics")){
                        String brand = components[5].trim();
                        int warrantyPeriod = Integer.parseInt(components[6].trim());
                        Object[] rowData = {productId, productName, category, price, brand+", "+warrantyPeriod+" weeks warranty"};
                        if(one.equals("Electronic") || one.equals("Product")){
                            model.addRow(rowData);
                        }

                    }else {
                        String size = components[5].trim();
                        String colour = components[6].trim();
                        Object[] rowData = {productId, productName, category, price, size+", "+colour};
                        if (one.equals("Clothing")|| one.equals("Product")){
                            model.addRow(rowData);
                        }
                    }

                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ShoppingGui(String user) throws HeadlessException {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileWriter fw = new FileWriter("Cart_Storage.txt", false);
                    PrintWriter pw = new PrintWriter(fw, false);
                    pw.flush();
                    pw.close();
                    fw.close();
                } catch (Exception exception) {
                    System.out.println("Exception have been caught");
                }
            }
        });


        this.setSize(920, 800);
        this.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top Panel
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        topPanel1 = new JPanel();
        topPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        label1 = new JLabel("Welcome To Westminster Shopping.");
        label1.setFont(new Font("MV Boli", Font.BOLD, 24));
        topPanel1.add(label1);

        topPanel.add(topPanel1, BorderLayout.NORTH);

        topPanel2 = new JPanel();
        topPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 40));
        label = new JLabel("Select Product Category:");
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        combobox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        combobox.addActionListener(this);

        combobox.setPreferredSize(new Dimension(130, 20));

        // Set font size for JComboBox
        combobox.setFont(new Font("MV Boli", Font.PLAIN, 12));


        JPanel buttonPanel = new JPanel();
        button = new JButton("Shopping Cart");
        button.setSize(120, 10);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GRAY);
        button.setFocusable(false);
        button.addActionListener(this);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ( cartGui== null) {
                    cartGui = new CartGui(ShoppingGui.this,user);
                } else {
                    cartGui.Loads();
                    cartGui.toFront();
                }
                dispose();

                cartGui.setVisible(true);
            }
        });

        button.setPreferredSize(new Dimension(160, 35));
        // Set font size for JButton
        button.setFont(new Font("MV Boli", Font.BOLD, 14));
        buttonPanel.add(button);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        topPanel.add(topPanel2, BorderLayout.CENTER);

        topPanel2.add(label);
        topPanel2.add(combobox);

        if (i){
            // Middle Panel
            middlePanel = new JPanel();

            model.addColumn("Product ID");
            model.addColumn("Name");
            model.addColumn("Category");
            model.addColumn("Price(£)");
            model.addColumn("Info");

            Load("Product");

            int columnIndexProductID = 0;
            int columnIndexName = 1;
            int columnIndexCategory = 2;
            int columnIndexPrice = 3;
            int columnIndexInfo = 4;

            int width = 150;
            int widthInfo = 300;

            TableColumn columnProductID = table.getColumnModel().getColumn(columnIndexProductID);
            TableColumn columnName = table.getColumnModel().getColumn(columnIndexName);
            TableColumn columnCategory = table.getColumnModel().getColumn(columnIndexCategory);
            TableColumn columnPrice = table.getColumnModel().getColumn(columnIndexPrice);
            TableColumn columnInfo = table.getColumnModel().getColumn(columnIndexInfo);

            columnProductID.setPreferredWidth(width);
            columnName.setPreferredWidth(width);
            columnCategory.setPreferredWidth(width);
            columnPrice.setPreferredWidth(width);
            columnInfo.setPreferredWidth(widthInfo);

            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(720, 200));
            table.setRowHeight(10);

            JTableHeader header = table.getTableHeader();
            header.setPreferredSize(new Dimension(header.getWidth(), 40));

            middlePanel.setLayout(new BorderLayout());
            middlePanel.add(scrollPane, BorderLayout.CENTER);


            // Adding a horizontal line after the table
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setPreferredSize(new Dimension(middlePanel.getWidth(), 1));
            middlePanel.add(separator, BorderLayout.SOUTH);
            middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 35, 0, 35));
        }


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        JButton button2 = new JButton("Add To Shopping Cart");


        final String[] productId = new String[1];
        final String[] productName = new String[1];
        final String[] info = new String[1];
        final String[] category = new String[1];
        final double[] price = new double[1];
        final int[] qty = new int[1];
        // Assuming you have a JTable named 'table'

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {

                        JPanel productDetailsPanel = new JPanel();
                        // Get the data from the selected row
                        productId[0] = (String) table.getValueAt(selectedRow, 0);
                        productName[0] = (String) table.getValueAt(selectedRow, 1);
                        category[0] = (String) table.getValueAt(selectedRow, 2);
                        price[0] = (double) table.getValueAt(selectedRow, 3);
                        info[0] = (String) table.getValueAt(selectedRow, 4);

                        String[] parts = info[0].split(",");
                        String infoPart1 = parts[0].trim();
                        String infoPart2 = parts[1].trim();

                        // Product Details Panel
                        bottomPanel.removeAll();

                        productDetailsPanel.setLayout(new GridLayout(6, 1));
                        Font labelFont = new Font("MV Boli", Font.PLAIN, 16);

                        JLabel label1 = new JLabel("Product Id: "+ productId[0]);
                        label1.setFont(labelFont);
                        label1.setPreferredSize(new Dimension(label1.getPreferredSize().width, 16));
                        productDetailsPanel.add(label1);

                        JLabel label2 = new JLabel("Category: "+ category[0]);
                        label2.setFont(labelFont);
                        label2.setPreferredSize(new Dimension(label2.getPreferredSize().width, 16));
                        productDetailsPanel.add(label2);

                        JLabel label3 = new JLabel("Name: "+ productName[0]);
                        label3.setFont(labelFont);
                        label3.setPreferredSize(new Dimension(label3.getPreferredSize().width, 16));
                        productDetailsPanel.add(label3);

                        if(category[0].equals("Electronics")){

                            JLabel label4 = new JLabel("Brand: "+infoPart1);
                            label4.setFont(labelFont);
                            label4.setPreferredSize(new Dimension(label4.getPreferredSize().width, 16));
                            productDetailsPanel.add(label4);

                            JLabel label5 = new JLabel("Warranty : "+infoPart2);
                            label5.setFont(labelFont);
                            label5.setPreferredSize(new Dimension(label5.getPreferredSize().width, 16));
                            productDetailsPanel.add(label5);

                        }else {
                            JLabel label4 = new JLabel("Size: "+infoPart1);
                            label4.setFont(labelFont);
                            label4.setPreferredSize(new Dimension(label4.getPreferredSize().width, 16));
                            productDetailsPanel.add(label4);

                            JLabel label5 = new JLabel("Colour: "+infoPart2);
                            label5.setFont(labelFont);
                            label5.setPreferredSize(new Dimension(label5.getPreferredSize().width, 16));
                            productDetailsPanel.add(label5);
                        }


                        try {
                            BufferedReader reader = new BufferedReader(new FileReader("Westminster_shopping.txt"));
                            String line = reader.readLine();
                            while (line != null) {
                                String[] components = line.split(",");
                                if (components[1].equals(productId[0])){
                                    qty[0] = Integer.parseInt(components[3]);
                                }
                                line = reader.readLine();
                            }
                            if (qty[0]==0){
                                button2.setEnabled(false);
                            }else {
                                button2.setEnabled(true);
                            }
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        JLabel label6 = new JLabel("Items Available: "+ qty[0]);
                        label6.setFont(labelFont);
                        label6.setPreferredSize(new Dimension(label6.getPreferredSize().width, 16));
                        productDetailsPanel.add(label6);

                        bottomPanel.add(productDetailsPanel, BorderLayout.CENTER);


                        label2 = new JLabel("Selected Product - Details");
                        label2.setFont(new Font("MV Boli", Font.BOLD, 20));
                        label2.setVerticalAlignment(JLabel.CENTER);
                        bottomPanel.add(label2, BorderLayout.NORTH);
                        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 0, 0));
                        bottomPanel.revalidate();
                        bottomPanel.repaint();

                        JPanel centeredButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

                        button2.setForeground(Color.WHITE);
                        button2.setBackground(Color.GRAY);
                        button2.setFocusable(false);
                        centeredButtonPanel.add(button2);

                        button2.setPreferredSize(new Dimension(190, 35));
                        // Set font size for JButton
                        button2.setFont(new Font("MV Boli", Font.BOLD, 14));

                        centeredButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
                        bottomPanel.add(centeredButtonPanel, BorderLayout.SOUTH);
                        bottomPanel.setPreferredSize(new Dimension(bottomPanel.getPreferredSize().width, 300));

                    }
                }
            }

        }
        );

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean addedSuccessfully = addToShoppingCart(productId,productName,price,info,category);
                System.out.println(qty[0]);
                if (addedSuccessfully) {
                    displaySuccessMessage();
                    clearProductDetailsPanel();
                }
            }
            public boolean addToShoppingCart(String[] productId, String[] productName, double[] price, String[] info, String[] category) {
                try {
                    // Read the existing content of the file
                    BufferedReader reader = new BufferedReader(new FileReader("Westminster_shopping.txt"));
                    StringBuilder fileContent = new StringBuilder();
                    String line = reader.readLine();

                    while (line != null) {
                        String[] components = line.split(",");
                        if (components.length > 1 && components[1].equals(productId[0])) {
                            // Increment the quantity by 1
                            int qty = Integer.parseInt(components[3]) -1;
                            components[3] = String.valueOf(qty);
                        }
                        fileContent.append(String.join(",", components)).append(System.lineSeparator());
                        line = reader.readLine();
                    }
                    reader.close();

                    FileWriter myWriter = new FileWriter("Westminster_shopping.txt");
                    myWriter.write(fileContent.toString());
                    myWriter.close();

                    myWriter = new FileWriter("Cart_Storage.txt", true);
                    myWriter.write(productId[0] + "," + productName[0] + "," + price[0] + "," + info[0] + "," + category[0] + "\n");
                    myWriter.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }


            private void clearProductDetailsPanel() {
                bottomPanel.removeAll();
                bottomPanel.revalidate();
                bottomPanel.repaint();
            }

            private void displaySuccessMessage() {
                JOptionPane optionPane = new JOptionPane("Added successfully to the shopping cart", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = optionPane.createDialog("Success");

                // Set the timer to close the dialog after 2000 milliseconds (2 seconds)
                Timer timer = new Timer(2000, e -> dialog.dispose());
                timer.setRepeats(false); // Do not repeat the timer
                timer.start();

                dialog.setVisible(true);
            }
        });

        combobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) combobox.getSelectedItem();
                switch (selectedItem) {
                    case "All":
                        model.setRowCount(0);
                        Load("Product");
                        break;
                    case "Electronics":
                        model.setRowCount(0);
                        Load("Electronic");
                        break;
                    case "Clothing":
                        model.setRowCount(0);
                        Load("Clothing");
                        break;

                }
            }
        });

        // Centered Button Panel
        JPanel centeredButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //JButton button2 = new JButton("Add To Shopping Cart");
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.GRAY);
        button2.setFocusable(false);
        centeredButtonPanel.add(button2);

        button2.setPreferredSize(new Dimension(190, 35));
        // Set font size for JButton
        button2.setFont(new Font("MV Boli", Font.BOLD, 14));

        centeredButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        bottomPanel.add(centeredButtonPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(bottomPanel.getPreferredSize().width, 300));



        // Add panels to the main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Adjust the table row height
        table.setRowHeight(24);
        // Add the main panel to the frame
        this.add(mainPanel);
        this.setTitle("Westminster Shopping");

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}


