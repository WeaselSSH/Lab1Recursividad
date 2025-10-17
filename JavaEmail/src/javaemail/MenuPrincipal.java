package javaemail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MenuPrincipal extends JFrame {

    private JPanel panelPrincipal, panelNorte, panelSur, panelCentro;
    private JLabel lblTitulo, lblUsuario, lblFecha, lblHora;
    private JButton btnSalir, btnMandar, btnLeer, btnLimpiar, btnBuscar, btnVerInbox;

    private JTable tblInbox;
    private DefaultTableModel inboxModel;
    private JScrollPane spInbox;

    public MenuPrincipal() {
        super("Mensajería");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(760, 760);
        setLocationRelativeTo(null);
        initComponents();
        cargarInboxDe(MenuInicial.accountActual);
        setVisible(true);
    }

    private void initComponents() {
        panelPrincipal = crearPanelGradiente(new Color(0x050607), new Color(0x0F1115));
        panelPrincipal.setLayout(new BorderLayout());

        panelNorte = crearPanelTransparente();
        panelNorte.setPreferredSize(new Dimension(0, 70));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        panelSur = crearPanelTransparente();
        panelSur.setPreferredSize(new Dimension(0, 85));
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        panelCentro = crearPanelTransparente();
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        lblTitulo = crearLabel("Menú Principal", 20, 10, 220, 28);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 22f));
        panelNorte.add(lblTitulo);

        lblUsuario = crearLabel("", 250, 10, 460, 24);
        panelNorte.add(lblUsuario);

        lblFecha = crearLabel("", 250, 36, 180, 24);
        panelNorte.add(lblFecha);

        lblHora = crearLabel("", 440, 36, 220, 24);
        panelNorte.add(lblHora);

        panelSur.setLayout(null);

        btnSalir = crearBoton("Salir", 620, 25, 100, 34);
        panelSur.add(btnSalir);

        panelCentro.setLayout(null);

        btnMandar = crearBoton("Mandar Correo", 80, 470, 150, 34);
        panelCentro.add(btnMandar);

        btnLeer = crearBoton("Leer Correo", 240, 470, 140, 34);
        panelCentro.add(btnLeer);

        btnLimpiar = crearBoton("Limpiar Inbox", 390, 470, 150, 34);
        panelCentro.add(btnLimpiar);

        btnBuscar = crearBoton("Buscar Correo", 550, 470, 150, 34);
        panelCentro.add(btnBuscar);

        btnVerInbox = crearBoton("Ver Inbox", 320, 515, 120, 32);
        panelCentro.add(btnVerInbox);

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
        tblInbox.setAutoCreateRowSorter(true);
        tblInbox.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblInbox.setForeground(new Color(0xE6EDF7));
        tblInbox.setBackground(new Color(0x111418));
        tblInbox.setGridColor(new Color(0x2B3B63));
        tblInbox.getTableHeader().setForeground(Color.WHITE);
        tblInbox.getTableHeader().setBackground(new Color(0x1A2332));

        spInbox = new JScrollPane(tblInbox);
        spInbox.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        spInbox.setBounds(40, 70, 660, 380);
        panelCentro.add(spInbox);

        btnSalir.addActionListener(e -> {
            new MenuInicial().setVisible(true);
            dispose();
        });
        btnMandar.addActionListener(e -> abrirDialogEnviar());
        btnLeer.addActionListener(e -> leerSeleccionado());
        btnLimpiar.addActionListener(e -> limpiarLeidos());
        btnBuscar.addActionListener(e -> buscarPorEmisor());
        btnVerInbox.addActionListener(e -> verInboxPlano());

        setContentPane(panelPrincipal);
        refrescarDatosDeUsuario();
        iniciarReloj();
    }

    private void refrescarDatosDeUsuario() {
        EmailAccount acc = MenuInicial.accountActual;
        if (acc == null) {
            lblUsuario.setText("Usuario: (no iniciado)");
        } else {
            lblUsuario.setText("Usuario: " + acc.getNombreUsuario() + "  <" + acc.getDireccionEmail() + ">");
        }
    }

    private void iniciarReloj() {
        Timer t = new Timer(1000, e -> {
            Calendar ahora = Calendar.getInstance();
            SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat f2 = new SimpleDateFormat("hh : mm : ss - a");
            lblFecha.setText("Fecha: " + f1.format(ahora.getTime()));
            lblHora.setText("Hora: " + f2.format(ahora.getTime()));
        });
        t.setRepeats(true);
        t.start();
    }

    public void cargarInboxDe(EmailAccount cuenta) {
        if (cuenta == null) {
            setInboxData(null);
            return;
        }
        setInboxData(cuenta.toInboxRows());
    }

    public void setInboxData(Object[][] filas) {
        inboxModel.setRowCount(0);
        if (filas != null) {
            for (Object[] f : filas) {
                inboxModel.addRow(f);
            }
        }
    }

    private void leerSeleccionado() {
        EmailAccount acc = MenuInicial.accountActual;
        if (acc == null) {
            return;
        }
        int row = tblInbox.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un correo en la tabla.");
            return;
        }
        row = tblInbox.convertRowIndexToModel(row);
        int pos = (int) inboxModel.getValueAt(row, 0);
        acc.leerEmail(pos);
        cargarInboxDe(acc);
    }

    private void limpiarLeidos() {
        EmailAccount acc = MenuInicial.accountActual;
        if (acc == null) {
            return;
        }
        acc.eliminarLeidos();
        cargarInboxDe(acc);
        int tot = acc.contarTotales();
        int sin = acc.contarSinLeer();
        JOptionPane.showMessageDialog(this, "Total: " + tot + " | Sin leer: " + sin);
    }

    private void buscarPorEmisor() {
        EmailAccount acc = MenuInicial.accountActual;
        if (acc == null) {
            return;
        }
        String[] opciones = {"Por emisor", "Ver todos"};
        int op = JOptionPane.showOptionDialog(this, "Búsqueda:", "Buscar correo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (op == 1 || op == JOptionPane.CLOSED_OPTION) {
            cargarInboxDe(acc);
            return;
        }
        String q = JOptionPane.showInputDialog(this, "Emisor a buscar:");
        if (q == null || q.trim().isEmpty()) {
            return;
        }
        q = q.trim();
        Object[][] all = acc.toInboxRows();
        java.util.List<Object[]> filtradas = new java.util.ArrayList<>();
        for (Object[] r : all) {
            String emisor = String.valueOf(r[1]);
            if (emisor.toLowerCase().contains(q.toLowerCase())) {
                filtradas.add(r);
            }
        }
        setInboxData(filtradas.toArray(new Object[0][]));
        JOptionPane.showMessageDialog(this, "Coincidencias: " + filtradas.size() + "\nTotal: " + acc.contarTotales() + "\nSin leer: " + acc.contarSinLeer());
    }

    private void verInboxPlano() {
        EmailAccount acc = MenuInicial.accountActual;
        if (acc == null) {
            return;
        }
        JTextArea ta = new JTextArea(acc.printInbox());
        ta.setEditable(false);
        ta.setBackground(Color.decode("#0F1115"));
        ta.setForeground(Color.decode("#E6EDF7"));
        ta.setCaretColor(Color.decode("#E6EDF7"));
        JScrollPane sp = new JScrollPane(ta);
        sp.setPreferredSize(new Dimension(620, 420));
        JOptionPane.showMessageDialog(this, sp, "Inbox", JOptionPane.PLAIN_MESSAGE);
    }

    private void abrirDialogEnviar() {
        JPanel p = crearPanelTransparente();
        p.setLayout(null);

        JLabel l1 = etiqueta("Para:", 14);
        l1.setBounds(10, 10, 80, 24);
        p.add(l1);
        JTextField txtPara = crearTextField(10, 34, 360, 28);
        p.add(txtPara);

        JLabel l2 = etiqueta("Asunto:", 14);
        l2.setBounds(10, 68, 80, 24);
        p.add(l2);
        JTextField txtAsunto = crearTextField(10, 92, 360, 28);
        p.add(txtAsunto);

        JLabel l3 = etiqueta("Contenido:", 14);
        l3.setBounds(10, 126, 100, 24);
        p.add(l3);
        javax.swing.JTextArea txtContenido = new javax.swing.JTextArea();
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true);
        txtContenido.setBackground(Color.decode("#111827"));
        txtContenido.setForeground(Color.decode("#E6EDF7"));
        txtContenido.setCaretColor(Color.decode("#E6EDF7"));
        txtContenido.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        JScrollPane sp = new JScrollPane(txtContenido);
        sp.setBounds(10, 150, 360, 140);
        p.add(sp);

        int res = JOptionPane.showConfirmDialog(this, p, "Mandar Correo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION) {
            return;
        }

        String para = txtPara.getText().trim();
        String asunto = txtAsunto.getText().trim();
        String contenido = txtContenido.getText().trim();

        if (para.isEmpty() || asunto.isEmpty() || contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa Para, Asunto y Contenido");
            return;
        }

        EmailAccount dest = buscarCuenta(para, 0);
        if (dest == null) {
            JOptionPane.showMessageDialog(this, "Error: destinatario no existe.");
            return;
        }

        String emisor = (MenuInicial.accountActual != null) ? MenuInicial.accountActual.getDireccionEmail() : "sistema@localhost";
        Email email = new Email(emisor, asunto, contenido);
        boolean ok = dest.recibirEmail(email);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "No entregado: inbox del destinatario está lleno.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Envío exitoso a " + para);

        if (MenuInicial.accountActual != null && MenuInicial.accountActual.getDireccionEmail().equalsIgnoreCase(para)) {
            cargarInboxDe(MenuInicial.accountActual);
        }
    }

    private EmailAccount buscarCuenta(String correo, int i) {
        if (MenuInicial.cuentas == null || i >= MenuInicial.cuentas.length) {
            return null;
        }
        EmailAccount acc = MenuInicial.cuentas[i];
        if (acc != null && acc.getDireccionEmail().equalsIgnoreCase(correo)) {
            return acc;
        }
        return buscarCuenta(correo, i + 1);
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

    private JLabel etiqueta(String txt, int size) {
        JLabel label = new JLabel(txt);
        label.setForeground(Color.decode("#E6EDF7"));
        label.setFont(label.getFont().deriveFont(Font.BOLD, size));
        return label;
    }
}
