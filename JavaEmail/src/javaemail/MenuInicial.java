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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MenuInicial extends JFrame {

    private JPanel panelPrincipal, panelNorte, panelSur, panelCentro;
    private JLabel lblTitulo, lblCorreo, lblContrasenia, lblNombre;
    private JTextField txtCorreo, txtNombre;
    private JPasswordField txtContrasenia;
    private JButton btnLogin, btnCrearCuenta, btnSalir;

    public static EmailAccount[] cuentas = new EmailAccount[10];
    public static EmailAccount accountActual = null;

    public MenuInicial() {
        super("Mensajería");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(570, 380);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        panelPrincipal = crearPanelGradiente(new Color(0x050607), new Color(0x0F1115));
        panelPrincipal.setLayout(new BorderLayout());

        panelNorte = crearPanelTransparente();
        panelNorte.setPreferredSize(new Dimension(0, 55));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        panelSur = crearPanelTransparente();
        panelSur.setPreferredSize(new Dimension(0, 70));
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        panelCentro = crearPanelTransparente();
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        lblTitulo = crearLabel("Menu Inicial", 215, 20, 220, 40);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 22f));
        panelNorte.add(lblTitulo);

        lblCorreo = crearLabel("Correo:", 125, 30, 220, 40);
        panelCentro.add(lblCorreo);

        txtCorreo = crearTextField(195, 40, 220, 20);
        panelCentro.add(txtCorreo);

        lblContrasenia = crearLabel("Contraseña:", 125, 75, 225, 40);
        panelCentro.add(lblContrasenia);

        txtContrasenia = crearPasswordField(225, 85, 190, 20);
        panelCentro.add(txtContrasenia);

        lblNombre = crearLabel("Nombre:", 125, 115, 220, 40);
        panelCentro.add(lblNombre);

        txtNombre = crearTextField(195, 125, 220, 20);
        panelCentro.add(txtNombre);

        btnLogin = crearBoton("Iniciar Sesión", 155, 175, 110, 35);
        panelCentro.add(btnLogin);

        btnCrearCuenta = crearBoton("Crear Cuenta", 285, 175, 110, 35);
        panelCentro.add(btnCrearCuenta);

        btnSalir = crearBoton("Salir", 425, 20, 100, 30);
        panelSur.add(btnSalir);

        btnLogin.addActionListener(e -> {
            String pass = new String(txtContrasenia.getPassword());

            login(txtCorreo.getText().trim(), pass);
        });

        btnSalir.addActionListener(e -> System.exit(0));

        btnCrearCuenta.addActionListener(e -> {
            String pass = new String(txtContrasenia.getPassword());

            crearCuenta(txtCorreo.getText().trim(), txtNombre.getText().trim(), pass);
        });

        setContentPane(panelPrincipal);
    }

    private boolean login(String correo, String contrasenia) {
        if (correo.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa correo y contraseña.");
            return false;
        }

        int i = buscarCuenta(correo, 0);
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Error: correo no existe.");
            return false;
        }

        if (cuentas[i].getPassword().equals(contrasenia)) {
            accountActual = cuentas[i];
            new MenuPrincipal().setVisible(true);
            this.dispose();
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Error: contraseña incorrecta.");
            return false;
        }
    }

    private int buscarCuenta(String correo, int i) {
        if (i >= cuentas.length) {
            return -1;
        }

        EmailAccount acc = cuentas[i];
        if (acc != null && acc.getDireccionEmail().equalsIgnoreCase(correo)) {
            return i;
        }
        return buscarCuenta(correo, i + 1);
    }

    private void crearCuenta(String correo, String nombre, String contrasenia) {
        if (correo.isEmpty() || nombre.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa correo, nombre y contraseña.");
            return;
        }

        int pos = primeraPosicionLibre();
        if (pos == -1) {
            JOptionPane.showMessageDialog(this, "No hay espacio para más cuentas.");
            return;
        }

        if (emailExiste(correo)) {
            JOptionPane.showMessageDialog(this, "Ese correo ya existe.");
            return;
        }

        String msg = validarPassword(contrasenia);
        if (msg != null) {
            JOptionPane.showMessageDialog(this, msg);
            return;
        }

        cuentas[pos] = new EmailAccount(correo, contrasenia, nombre);
        accountActual = cuentas[pos];

        JOptionPane.showMessageDialog(this, "Cuenta creada con éxito.");
        new MenuPrincipal().setVisible(true);
        this.dispose();
    }

    private int primeraPosicionLibre() {
        return primeraPosicionLibre(0);
    }

    private int primeraPosicionLibre(int i) {
        if (i >= cuentas.length) {
            return -1;
        }
        return (cuentas[i] == null) ? i : primeraPosicionLibre(i + 1);
    }

    private boolean emailExiste(String correo) {
        return buscarCuenta(correo, 0) != -1;
    }

    private String validarPassword(String s) {
        if (s.length() < 5) {
            return "La contraseña debe tener al menos 5 caracteres.";
        }
        if (!s.matches(".*[A-Z].*")) {
            return "Debe contener al menos una letra mayúscula.";
        }
        if (!s.matches(".*\\d.*")) {
            return "Debe contener al menos un número.";
        }
        if (!s.matches(".*[^a-zA-Z0-9].*")) {
            return "Debe contener al menos un símbolo.";
        }
        return null;
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

    private JPasswordField crearPasswordField(int x, int y, int w, int h) {
        JPasswordField tf = new JPasswordField();
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
        new MenuInicial().setVisible(true);
    }
}
