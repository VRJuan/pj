/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Juan
 */
public class MdlCliente extends MdlPersona {

    private int id_cli;
    private String correo;
    private String password;    
    private String estado;

    public MdlCliente(int id_cli, String correo, String password, String estado, int id, String nombre, String apellido, String celular, String telefono, String numero, String fecha_de_nacimiento, String tipo_doc, String direccion) {
        super(id, nombre, apellido, celular, telefono, numero, fecha_de_nacimiento, tipo_doc, direccion);
        this.id_cli = id_cli;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
    }

    public MdlCliente(int id_cli, String correo, String password, String estado) {
        this.id_cli = id_cli;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
    }

    public MdlCliente() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_Cli() {
        return id_cli;
    }

    public void setId_Cli(int id_cli) {
        this.id_cli = id_cli;
    }    
}
