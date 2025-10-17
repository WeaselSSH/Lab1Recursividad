package javaemail;

import java.util.Calendar;

public class EmailAccount {
    
    private String direccionEmail;
    private String password;
    private String nombreUsuario;
    private Email[] inbox;
    
    public EmailAccount(String direccionEmail, String password, String nombreUsuario){
        this.direccionEmail = direccionEmail;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.inbox = new Email[10];
        
    }
    
    public String getDireccionEmail(){
        return direccionEmail;
    }

    public String getPassword(){
        return password;
    }
    
    public String nombreUsuario(){
        return nombreUsuario;
    }
    
    
    public Boolean recibirEmail(Email em){
        for (int i=0; i<=inbox.length; i++){
            if (inbox[i] == null){
                inbox[i] = em;
                
                return true;
            }
        }
        return false;
    }
    
    public static void printInbox (){
        Calendar fecha = Calendar.getInstance();
        System.out.println("Fecha: %tF %tT%n%n"+ fecha +fecha);
        
        int total = 0;
        int sinLeer =0;
        
        
        for (int i=0; i<inbox.length; i++){
            Email em= inbox[i];
            
            if (em != null){
                total++;
                String estado = em.isLeido() ? "LEÍDO": "SIN LEER";
                
                if (em.isLeido())sinLeer++;
                System.out.println("%d – %s – %s – [%s]%n"+ i +em.getEmisor() +em.getAsunto() +estado);
            }
            System.out.println("Total de correos: %d%n"+ total);
            System.out.println("Correos sin leer  %d%n"+sinLeer);
        }
        
        
    }
    
    public static void leerEmail (int pos){
        
    }
    

    
    
}
