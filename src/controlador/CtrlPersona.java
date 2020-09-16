package controlador;

import conexion.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.MdlCliente;
import modelo.MdlPersona;

public class CtrlPersona {

    public int guardar(MdlCliente cliente) {
        Conexion conectar = new Conexion();
        int codigo = 0;
        String sql;
        sql = "insert into Personas(str_apellido,str_nombre,str_celular_no,"
            + "str_telefono,str_numero_doc,str_fecha_nacimiento,str_tipo_doc,"
            + "str_direccion)"
            + "values('"+cliente.getApellido()+"','"+cliente.getNombre()+"','"
            + cliente.getCelular()+"','"+cliente.getTelefono()+"','"
            + cliente.getNumeroDoc()+"','"+cliente.getFechaNacimiento()+"','"
            + cliente.getTipoDocumento()+"','"+cliente.getDireccion()+"')";
        try {                     
            if (conectar.ejecutar(sql)) {
                codigo = devuelveCodigo(cliente.getNumeroDoc());
            } 
        } catch (Exception e) {
            System.out.println("Error al insertar datos persona: "+e);
        }
        return codigo;
    }

    public int Actualizar(MdlCliente per) {
        Conexion conectar = new Conexion();
        int r = 0;
        String sql;
        sql="update Personas set str_nombre='"+per.getNombre()+"',str_telefono='"
           +per.getTelefono()+"',str_apellido='"+per.getApellido()
           +"',str_celular_no='"+per.getCelular()+"',str_fecha_nacimiento='"
           +per.getFechaNacimiento()+"',str_tipo_doc='"+per.getTipoDocumento()
           +"',str_direccion='"+per.getDireccion()+"' where int_id_per="
           +per.getId();
        try {                     
            if (conectar.ejecutar(sql)) {
                r = 1;
            } 
        } catch (Exception e) {
            System.out.println("Error al actualizar datos persona: "+e);
        }
        return r;
    }
    
    public MdlPersona buscaPersona(String identificacion){
        Conexion conectar = new Conexion();   
        MdlPersona persona = new MdlPersona();
        String sql;
        ResultSet rs;
        sql = "select * from Personas where str_numero_doc='"+identificacion+"'";
        try {
            conectar.conectar();
            rs = conectar.consultar(sql);
            if (rs.next()) {
                persona.setId(rs.getInt("int_id_per"));
                persona.setNombre(rs.getString("str_nombre"));
                persona.setTelefono(rs.getString("str_telefono"));
                persona.setApellido(rs.getString("str_apellido"));
                persona.setCelular(rs.getString("str_celular_no"));
                persona.setNumeroDoc(rs.getString("str_numero_doc"));
                persona.setFechaNacimiento(rs.getString("str_fecha_nacimiento"));
                persona.setTipoDocumento(rs.getString("str_tipo_doc"));
                persona.setDireccion(rs.getString("str_direccion"));
            }else{
                persona=null;
            }
//            conectar.cerrar();
        } catch (SQLException e) {
            System.out.println("Error en busqueda de datos persona por identificación : "+e);
        }
        return persona;
    }
    
    public MdlPersona buscaPersona(int codigo){
        Conexion conectar = new Conexion();   
        MdlPersona persona = new MdlPersona();
        String sql;
        ResultSet rs;
        sql = "select * from Personas where int_id_per='"+codigo+"'";
        try {
            conectar.conectar();
            rs = conectar.consultar(sql);
            if (rs.next()) {
                persona.setId(rs.getInt("int_id_per"));
                persona.setNombre(rs.getString("str_nombre"));
                persona.setTelefono(rs.getString("str_telefono"));
                persona.setApellido(rs.getString("str_apellido"));
                persona.setCelular(rs.getString("str_celular_no"));
                persona.setNumeroDoc(rs.getString("str_numero_doc"));
                persona.setFechaNacimiento(rs.getString("str_fecha_nacimiento"));
                persona.setTipoDocumento(rs.getString("str_tipo_doc"));
                persona.setDireccion(rs.getString("str_direccion"));
            }else{
                persona=null;
            }
//            conectar.cerrar();
        } catch (SQLException e) {
            System.out.println("Error en busqueda de datos persona por codigo: "+e);
        }
        return persona;
    }
    
    public int devuelveCodigo(String identificacion){
        Conexion conectar = new Conexion();   
        MdlPersona persona = new MdlPersona();
        String sql;
        int codigo=0;
        ResultSet rs;
        sql = "select int_id_per from Personas where str_numero_doc='"+identificacion+"'";
        try {
            conectar.conectar();
            rs = conectar.consultar(sql);
            if (rs.next()) {
                codigo = rs.getInt("int_id_per");
            }
//            conectar.cerrar();
        } catch (SQLException e) {
            System.out.println("Error en busqueda de datos persona que devuelve codigo: "+e);
        }
        return codigo;
    }
}
