package javaemail;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Email extends JavaEmail {

    // definir atributos
    public String emisor;
    public String asunto;
    public String contenido;
    public boolean leido;
    public Calendar fechaEnvio;
    
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
    
    public boolean getLeido() {
        return leido;
    }
    
    public Calendar getFechaEnvio() {
        return fechaEnvio;
    }
    
    // Función leido()
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
        return "De: ["+emisor+"] "
             + "\nAsunto: ["+asunto+"] "
             + "\nContenido: ["+contenido+"] "
             + "\nFecha: ["+fecha.format(fechaEnvio.getTime())+"] "
             + "\nEstado: ["+estado+"]";
    }
}
