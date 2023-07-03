package swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class hocbong {
    private JPanel rootPanel;
    private JTextField studentCode;
    private JTextField studentName;
    private JButton lưuButton;
    private JTable table1;
    private JTextField loaihocbong;
    private JTextField xeploai;
    private JTextField fromDate;
    private JTextField toDate;
    private JTextField note;
    private JButton xóaButton;

    public hocbong() {
        DefaultTableModel tableModel = new DefaultTableModel();
        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Mã sinh viên");
        columnNames.add("Tên sinh viên");
        columnNames.add("Học bổng");
        columnNames.add("Xếp loại");
        columnNames.add("Từ ngày");
        columnNames.add("Đến ngày");
        columnNames.add("note");

        for(String columnName : columnNames){
            tableModel.addColumn(columnName);
        }
        table1.setModel(tableModel);
        table1.setFillsViewportHeight(true);
        table1.setVisible(true);
        table1.setSize(200,200);

        readFile();

        lưuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleAdd();
                super.mouseClicked(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                handleAdd();
                super.mousePressed(e);
            }
        });
        xóaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String studentCodeValue = studentCode.getText();
                if (studentCodeValue == null) {
                    return;
                }
                handleRemove(studentCodeValue);
                super.mousePressed(e);
            }
        });
    }

    public void handleRemove(String studentCode) {
        File inputFile = new File("filename.txt");
        File tempFile = new File("myTempFile.txt");

        try {

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(trimmedLine.startsWith(studentCode + ";")) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            boolean successful = tempFile.renameTo(inputFile);
        } catch (Exception e) {
            System.out.println(e);
        }
        readFile();
    }

    public void handleAdd() {
        String studentCodeValue = studentCode.getText();
        String studentNameValue = studentName.getText();
        String loaihocbongValue = loaihocbong.getText();
        String xeploaiValue = xeploai.getText();
        String fromDateValue = fromDate.getText();
        String toDateValue = toDate.getText();
        String noteValue = note.getText();

        if (studentCodeValue == null || studentNameValue == null || loaihocbongValue == null
                || xeploaiValue == null || fromDateValue == null || toDateValue == null
        ) {
            return;
        }
        String valueLine = studentCodeValue + ";" + studentNameValue + ";" + loaihocbongValue + ";" + xeploaiValue + ";" +fromDateValue + ";" + toDateValue + ";" +noteValue+"\n";
        writeFile(valueLine);
        studentCode.setText("");
        studentName.setText("");
        loaihocbong.setText("");
        xeploai.setText("");
        fromDate.setText("");
        toDate.setText("");
        note.setText("");
        readFile();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello world");
        frame.setContentPane(new hocbong().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void writeFile(String valueLine) {
        try {
            FileWriter myWriter = new FileWriter("filename.txt", true);
            myWriter.write(valueLine);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            DefaultTableModel model = (DefaultTableModel) table1.getModel();

            int rowCount = model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(";");
                model.addRow(arr);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
