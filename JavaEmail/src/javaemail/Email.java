package javaemail;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Email {

    private final String emisor;
    private final String asunto;
    private final String contenido;
    private final Calendar fechaEnvio;
    private boolean leido;

    public Email(String emisor, String asunto, String contenido) {
        this.emisor = emisor;
        this.asunto = asunto;
        this.contenido = contenido;
        this.fechaEnvio = Calendar.getInstance();
        this.leido = false;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public Calendar getFechaEnvio() {
        return fechaEnvio;
    }

    public boolean isLeido() {
        return leido;
    }

    public void marcarLeido() {
        leido = true;
    }

    public String print() {
        SimpleDateFormat f1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f2 = new SimpleDateFormat("hh : mm : ss - a");
        String s = "";
        s += "De: " + emisor + "\n";
        s += "Asunto: " + asunto + "\n";
        s += "Fecha: " + f1.format(fechaEnvio.getTime()) + "  Hora: " + f2.format(fechaEnvio.getTime()) + "\n";
        s += "Estado: " + (leido ? "LEÍDO" : "SIN LEER") + "\n";
        s += "Contenido:\n";
        s += contenido + "\n";
        return s;
    }
}
