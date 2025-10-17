package javaemail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Inbox extends JDialog {

    private JTable tblInbox;
    private DefaultTableModel inboxModel;

    public Inbox(Frame principal) {
        super(principal, "Inbox", true);
        setSize(700, 420);
        setLocationRelativeTo(principal);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(12, 12));

        String cols[] = {"Posición", "Emisor", "Asunto", "Fecha", "Hora", "Leído"};
        inboxModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int c) {
                return c == 5 ? Boolean.class : String.class;
            }
        };

        tblInbox = new JTable(inboxModel);
        tblInbox.setRowHeight(22);
        tblInbox.setForeground(new Color(0xE6EDF7));
        tblInbox.setBackground(new Color(0x111418));
        tblInbox.setGridColor(new Color(0x2B3B63));
        tblInbox.getTableHeader().setForeground(Color.WHITE);
        tblInbox.getTableHeader().setBackground(new Color(0x1A2332));

        //por si las dudas
        JScrollPane sp = new JScrollPane(tblInbox);
        sp.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        add(sp, BorderLayout.CENTER);

        addRow(new Object[]{1, "weaselssh@gmail.com", "Bienvenida", "17/10/2025", "09:12:05 AM", false});
    }

    public void addRow(Object[] fila) {
        inboxModel.addRow(fila);
    }
}
