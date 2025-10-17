package javaemail;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Email {

    // definir atributos
    private String emisor;
    private String asunto;
    private String contenido;
    private boolean leido;
    private Calendar fechaEnvio;

    // Crear el constructor
    public Email(String emisor, String asunto, String contenido) {
        this.emisor = emisor;
        this.asunto = asunto;
        this.contenido = contenido;

        // Inicializar leido en false
        this.leido = false;

        // Asignar la fecha actual
        this.fechaEnvio = Calendar.getInstance();

    }

    // Crear métodos (getters) para obtener cada atributo
    public String getEmisor() {
        return emisor;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public boolean isLeido() {
        return leido;
    }

    public Calendar getFechaEnvio() {
        return fechaEnvio;
    }

    // Función marcarLeido()
    public void marcarLeido() {
        this.leido = true;
    }

    public boolean leer() {
        this.leido = true;
        return this.leido;
    }

    // Función print()
    public String print() {
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String estado = leido ? "Leido" : "Sin Leer";
        return "De: [" + emisor + "] "
                + "\nAsunto: [" + asunto + "] "
                + "\nContenido: [" + contenido + "] "
                + "\nFecha: [" + fecha.format(fechaEnvio.getTime()) + "] "
                + "\nEstado: [" + estado + "]";
    }
}
