
package modelo;

public class MdlPersona {

   private int id;
   private String nombre;
   private String telefono;
   private String apellido;//    Este se puso nuevo 6
   private String celular; // Este se puso nuevo 6
   private String numeroDoc; // Este se puso nuevo 6
   private String fechaNacimiento; // Este se puso nuevo 6
   private String tipoDocumento; // Este se puso nuevo 6
   private String direccion; // Este se puso nuevo 6

    public MdlPersona() {
    }  

    public MdlPersona(int id, String nombre, String telefono, String apellido, String celular, String numeroDoc, String fechaNacimiento, String tipoDocumento, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.apellido = apellido;
        this.celular = celular;
        this.numeroDoc = numeroDoc;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
