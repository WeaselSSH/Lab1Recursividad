package javaemail;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class EmailAccount {

    private String direccionEmail;
    private String password;
    private String nombreUsuario;
    private Email[] inbox;

    public EmailAccount(String direccionEmail, String password, String nombreUsuario) {
        this.direccionEmail = direccionEmail;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.inbox = new Email[10];

    }
    
    public String getDireccionEmail() {
        return direccionEmail;
    }

    public String getPassword() {
        return password;
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

    public void printInbox() {
                Calendar fecha = Calendar.getInstance();
        System.out.println("Fecha: %tF %tT%n%n" + fecha + fecha);

        int total = 0;
        int sinLeer = 0;

        for (int i = 0; i < inbox.length; i++) {
            Email em = inbox[i];

            if (em != null) {
                total++;
                String estado = em.isLeido() ? "LEÍDO" : "SIN LEER";

                if (em.isLeido()) {
                    sinLeer++;
                }
                System.out.println("%d – %s – %s – [%s]%n" + i + em.getEmisor() + em.getAsunto() + estado);
            }
            System.out.println("Total de correos: %d%n" + total);
            System.out.println("Correos sin leer  %d%n" + sinLeer);
            
            
        }
        
    }   
        
    
    public int contarSinLeer() {
    return RcontarSinLeer(0);
            }

    private int RcontarSinLeer(int i) {
        if (i >= inbox.length) return 0;
            if (inbox[i] != null && !inbox[i].isLeido()) {
                return 1 + RcontarSinLeer(i + 1);
            }
            return RcontarSinLeer(i + 1);
    }
       

    public void buscarPorEmisor(String emisor) {
        RbuscarPorEmisor(emisor, 0);
    }

    private void RbuscarPorEmisor(String emisor, int i) {
        if (i >= inbox.length) return;
            if (inbox[i] != null && inbox[i].getEmisor().equalsIgnoreCase(emisor)) {
                inbox[i].print();
            }
            RbuscarPorEmisor(emisor, i + 1);
    }
        
    
    public void mostrarTodos() {
        RmostrarTodos(0);
    }

    private void RmostrarTodos(int i) {
        if (i >= inbox.length) return;
        if (inbox[i] != null) {
            inbox[i].print();
        }
        RmostrarTodos(i + 1);
    }
    
    
    public void eliminarLeidos() {
        ReliminarLeidos(0);
    }

    private void ReliminarLeidos(int i) {
        if (i >= inbox.length) return;
        if (inbox[i] != null && inbox[i].isLeido()) {
            inbox[i] = null;
        }
        ReliminarLeidos(i + 1);
    }
    
    
    public int contarTotales() {
    return RcontarTotales(0);
    }

    private int RcontarTotales(int i) {
        if (i >= inbox.length) return 0;
        if (inbox[i] != null) {
            return 1 + RcontarTotales(i + 1);
        }
        return RcontarTotales(i + 1);
    }
    
    
    public void leerEmail(int pos) {
        if (inbox[pos] != null) {
                inbox[pos].leer();
            }
    }
    
    

}
