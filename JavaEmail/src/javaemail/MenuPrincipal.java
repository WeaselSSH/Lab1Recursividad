package javaemail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class MenuPrincipal extends JFrame {

    private JPanel panelPrincipal, panelNorte, panelSur, panelCentro;
    private JLabel lblTitulo;
    private JButton btnRegresar, btnMandar;

    private JTable tblInbox;
    private DefaultTableModel inboxModel;
    private JScrollPane spInbox;

    public MenuPrincipal() {
        super("Mensajería");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 620);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        //paneles
        panelPrincipal = crearPanelGradiente(new Color(0x050607), new Color(0x0F1115));
        panelPrincipal.setLayout(new BorderLayout());

        //resto de paneles
        panelNorte = crearPanelTransparente();
        panelNorte.setPreferredSize(new Dimension(0, 55));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        panelSur = crearPanelTransparente();
        panelSur.setPreferredSize(new Dimension(0, 70));
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        panelCentro = crearPanelTransparente();
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de ui
        lblTitulo = crearLabel("Menu Principal", 280, 20, 220, 40);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 22f));
        panelNorte.add(lblTitulo);

        btnRegresar = crearBoton("Regresar", 535, 20, 100, 30);
        panelSur.add(btnRegresar);

        btnMandar = crearBoton("Mandar Correo", 135, 380, 120, 40);
        panelCentro.add(btnMandar);

        String[] columnas = {"Posición", "Emisor", "Asunto", "Fecha", "Hora", "Leído"};
        inboxModel = new DefaultTableModel(columnas, 0) {
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
        tblInbox.setRowHeight(24);
        tblInbox.setForeground(new Color(0xE6EDF7));
        tblInbox.setBackground(new Color(0x111418));
        tblInbox.setGridColor(new Color(0x2B3B63));
        tblInbox.getTableHeader().setForeground(Color.WHITE);
        tblInbox.getTableHeader().setBackground(new Color(0x1A2332));

        spInbox = new JScrollPane(tblInbox);
        spInbox.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        spInbox.setBounds(40, 50, 620, 300);
        panelCentro.add(spInbox);

        // Fila de prueba
        inboxModel.addRow(new Object[]{1, "weaselssh@gmail.com", "Bienvenida", "17/10/2025", "09:12:05 AM", false});

        btnRegresar.addActionListener(e -> {
            new MenuInicial().setVisible(true);
            this.dispose();
        });

        setContentPane(panelPrincipal);
    }

    private JLabel crearLabel(String texto, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(lbl.getFont().deriveFont(Font.PLAIN, 16f));
        lbl.setBounds(x, y, w, h);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private JTextField crearTextField(int x, int y, int w, int h) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, w, h);
        tf.setBackground(Color.decode("#1A2332"));
        tf.setForeground(Color.decode("#E6EDF7"));
        tf.setCaretColor(Color.decode("#E6EDF7"));
        tf.setSelectionColor(Color.decode("#334155"));
        tf.setSelectedTextColor(Color.decode("#E6EDF7"));
        tf.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        return tf;
    }

    private JButton crearBoton(String texto, int x, int y, int w, int h) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, w, h);
        btn.setBackground(Color.decode("#244FC0"));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.decode("#2B3B63")));
        btn.setFocusPainted(false);
        return btn;
    }

    private JPanel crearPanelGradiente(Color start, Color end) {
        return new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setPaint(new GradientPaint(0, 0, start, getWidth(), getHeight(), end));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
    }

    private JPanel crearPanelTransparente() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(null);
        return p;
    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}
