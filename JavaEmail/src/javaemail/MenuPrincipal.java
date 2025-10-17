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
<<<<<<< HEAD
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
=======
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
>>>>>>> b696da1561c498f7730685e78bdd250e1b7bcf2c

public class MenuPrincipal extends JFrame {

    private JPanel panelPrincipal, panelNorte, panelSur, panelCentro;
    private JLabel lblTitulo;
<<<<<<< HEAD
    private JButton btnSalir, btnMandar;
    
    // Atributos de Sección - Mandar Correo
    private JTextField txtEmisor;
    private JTextField txtPara;
    private JTextField txtAsunto;
    private JTextArea txtContenido;
    private JButton btnEnviar;
=======
    private JButton btnRegresar, btnMandar;

    private JTable tblInbox;
    private DefaultTableModel inboxModel;
    private JScrollPane spInbox;
>>>>>>> b696da1561c498f7730685e78bdd250e1b7bcf2c

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

<<<<<<< HEAD
        btnSalir = crearBoton("Salir", 425, 50, 100, 30);
        panelSur.add(btnSalir);
        
        btnMandar = crearBoton("Mandar Correo", 105, 50, 120, 40);
=======
        btnRegresar = crearBoton("Regresar", 535, 20, 100, 30);
        panelSur.add(btnRegresar);

        btnMandar = crearBoton("Mandar Correo", 135, 380, 120, 40);
>>>>>>> b696da1561c498f7730685e78bdd250e1b7bcf2c
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
        
        // Sección de - Mandar Correo
        JPanel root = crearPanelGradiente(Color.decode("#0B11220"), Color.decode("#0F172A"));
        root.setLayout(null);
        
        JLabel lblTituloDe = etiqueta("Mandar Correo", 24);
        lblTituloDe.setBounds(24, 16, 400, 32);
        root.add(lblTituloDe);
        
        JLabel lblPara = etiqueta("Para: ", 14);
        lblPara.setBounds(24, 72, 160, 24);
        root.add(lblPara);
        
        txtPara = crearTextField(24, 98, 400, 32);
        root.add(txtPara);
        
        JLabel lblAsunto = etiqueta("Asunto: ", 14);
        lblAsunto.setBounds(24, 144, 166, 24);
        root.add(lblAsunto);
        
        txtAsunto = crearTextField(24, 170, 400, 32);
        root.add(txtAsunto);
        
        JLabel lblContenido = etiqueta("Contenido: ", 14);
        lblContenido.setBounds(24, 216, 160, 24);
        root.add(lblContenido);
        
        txtContenido = new JTextArea();
        txtContenido.setLineWrap(true);
        txtContenido.setWrapStyleWord(true);
        txtContenido.setBackground(Color.decode("#111827"));
        txtContenido.setForeground(Color.decode("#E6EDF7"));
        txtContenido.setCaretColor(Color.decode("#E6ED7"));
        txtContenido.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        
        JScrollPane spinner = new JScrollPane(txtContenido);
        spinner.setBounds(24, 242, 580, 150);
        root.add(spinner);
        
        btnEnviar = crearBoton("Enviar", 24, 410, 140, 36);
        root.add(btnEnviar);
        
        setContentPane(root);
        
        btnEnviar.addActionListener(e -> enviar());
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
    
    // Sección de - Mandar Correo
    private JLabel etiqueta(String txt, int size) {
        JLabel label = new JLabel(txt);
        label.setForeground(Color.decode("#E6EDF7"));
        label.setFont(label.getFont().deriveFont(Font.BOLD, size));
        return label;
    }
    
    private void enviar() {
        String emisor = txtEmisor.getText().trim();
        String para = txtPara.getText().trim();
        String asunto = txtAsunto.getText().trim();
        String contenido = txtContenido.getText().trim();
        
        if (para.isEmpty() || asunto.isEmpty() || contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa Para, Asunto y Contenido",
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        /* En caso de que el destinatario no existe
        Usuario dest = RepositorioUsuarios.buscar(para);
        if (dest == null) {
            JOptionPane.showMessageDialog(this, "El destinatario no existe!"+para, 
                    "Error", JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        if (!dest.tieneEspacio()) {
            JOptionPane.showMessageDialog(this, 
                    "El inbox de "+para+ " se encuentra lleno",
                    "No entregado", JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        Email email = new Email(emisor, asunto, contenido);
        dest.recibir(email);
        
        JOptionPane.showMessageDialog(this, 
                "Envio exitoso! \n\n"+ email.print(),
                "Listo", JOptionPane.INFORMATION_MESSAGE);
        
        txtAsunto.setText("");
        txtContenido.setText("");
        */
    }
    
    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}