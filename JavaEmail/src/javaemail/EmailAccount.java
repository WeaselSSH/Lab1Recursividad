package javaemail;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmailAccount {

    private String direccionEmail;
    private String password;
    private String nombreUsuario;
    private Email[] inbox;

    public EmailAccount(String direccionEmail, String password, String nombreUsuario) {
        this.direccionEmail = direccionEmail;
        this.password = password;
        this.nombreUsuario = nombreUsuario;
        this.inbox = new Email[10];
    }

    public String getDireccionEmail() {
        return direccionEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String nombreUsuario() {
        return nombreUsuario;
    }

    public boolean recibirEmail(Email em) {
        for (int i = 0; i < inbox.length; i++) {
            if (inbox[i] == null) {
                inbox[i] = em;
                return true;
            }
        }
        return false;
    }

    public Object[][] toInboxRows() {
        int count = 0;
        for (Email e : inbox) {
            if (e != null) {
                count++;
            }
        }

        Object[][] rows = new Object[count][6];
        SimpleDateFormat FFECHA = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat FHORA = new SimpleDateFormat("hh:mm:ss a");

        int k = 0;
        for (int i = 0; i < inbox.length; i++) {
            Email e = inbox[i];
            if (e != null) {
                rows[k][0] = i;
                rows[k][1] = e.getEmisor();
                rows[k][2] = e.getAsunto();
                rows[k][3] = FFECHA.format(e.getFechaEnvio().getTime());
                rows[k][4] = FHORA.format(e.getFechaEnvio().getTime());
                rows[k][5] = e.isLeido();
                k++;
            }
        }
        return rows;
    }

    public String printInbox() {
        Calendar ahora = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String s = "";
        s += "Fecha actual: " + sdf.format(ahora.getTime()) + "\n";
        s += "INBOX de " + nombreUsuario + " <" + direccionEmail + ">\n";
        s += "-----------------------------------------------------\n";
        s += "POSICIÓN – EMISOR – ASUNTO – [LEÍDO / SIN LEER]\n";

        int total = 0;
        int sinLeer = 0;

        for (int i = 0; i < inbox.length; i++) {
            Email em = inbox[i];
            if (em != null) {
                total++;
                boolean leido = em.isLeido();
                if (!leido) {
                    sinLeer++;
                }

                s += i + " – " + em.getEmisor() + " – " + em.getAsunto()
                        + " – [" + (leido ? "LEÍDO" : "SIN LEER") + "]\n";
            }
        }

        s += "-----------------------------------------------------\n";
        s += "Total de correos: " + total + "\n";
        s += "Correos sin leer: " + sinLeer + "\n";

        return s;
    }

    public void leerEmail(int pos) {
        if (pos < 0 || pos >= inbox.length) {
            System.out.println("Posición inválida.");
            return;
        }
        Email em = inbox[pos];
        if (em == null) {
            System.out.println("Correo no existe");
            return;
        }
        System.out.println(em.print());
        em.marcarLeido();
    }

    public int contarSinLeer() {
        return RcontarSinLeer(0);
    }

    private int RcontarSinLeer(int i) {
        if (i >= inbox.length) {
            return 0;
        }
        if (inbox[i] != null && !inbox[i].isLeido()) {
            return 1 + RcontarSinLeer(i + 1);
        }
        return RcontarSinLeer(i + 1);
    }

    public void buscarPorEmisor(String emisor) {
        RbuscarPorEmisor(emisor, 0);
    }

    private void RbuscarPorEmisor(String emisor, int i) {
        if (i >= inbox.length) {
            return;
        }
        if (inbox[i] != null && inbox[i].getEmisor().equalsIgnoreCase(emisor)) {
            System.out.println(inbox[i].print());
        }
        RbuscarPorEmisor(emisor, i + 1);
    }

    public void mostrarTodos() {
        RmostrarTodos(0);
    }

    private void RmostrarTodos(int i) {
        if (i >= inbox.length) {
            return;
        }
        if (inbox[i] != null) {
            System.out.println(inbox[i].print());
        }
        RmostrarTodos(i + 1);
    }

    public void eliminarLeidos() {
        ReliminarLeidos(0);
    }

    private void ReliminarLeidos(int i) {
        if (i >= inbox.length) {
            return;
        }
        if (inbox[i] != null && inbox[i].isLeido()) {
            inbox[i] = null;
        }
        ReliminarLeidos(i + 1);
    }

    public int contarTotales() {
        return RcontarTotales(0);
    }

    private int RcontarTotales(int i) {
        if (i >= inbox.length) {
            return 0;
        }
        if (inbox[i] != null) {
            return 1 + RcontarTotales(i + 1);
        }
        return RcontarTotales(i + 1);
    }
}
