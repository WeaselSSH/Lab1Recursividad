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

public class MenuInicial extends JFrame {

    private JPanel panelPrincipal, panelNorte, panelSur, panelCentro;
    private JLabel lblTitulo, lblCorreo, lblContrasenia;
    private JTextField txtCorreo, txtContrasenia;
    private JButton btnLogin, btnCrearCuenta, btnSalir;

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
        panelSur.setPreferredSize(new Dimension(0, 100));
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

        txtContrasenia = crearTextField(225, 85, 190, 20);
        panelCentro.add(txtContrasenia);

        btnLogin = crearBoton("Iniciar Sesión", 155, 155, 110, 35);
        panelCentro.add(btnLogin);

        btnCrearCuenta = crearBoton("Crear Cuenta", 285, 155, 110, 35);
        panelCentro.add(btnCrearCuenta);

        btnSalir = crearBoton("Salir", 425, 50, 100, 30);
        panelSur.add(btnSalir);

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
        new MenuInicial().setVisible(true);
    }
}
