/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexion.Conexion;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.MdlCliente;
import modelo.MdlPersona;

/**
 *
 * @author scorpion
 */
public class CtrlClientes {

    //sw = el indicador si trae datos de persona
    public void guardar(MdlCliente cliente, int sw) {
        Conexion conectar = new Conexion();
        CtrlPersona ctrlpersona = new CtrlPersona();
        String sql;
        int codigo = 0;
        try {
            conectar.conectar();
            if (sw == 0) {
                codigo = ctrlpersona.guardar(cliente);
                if (codigo != 0) {
                    sql = "insert into Clientes(str_correo,str_password,persona,str_estado)"
                            + "Values('" + cliente.getCorreo() + "','" + cliente.getPassword()
                            + "','" + codigo + "','" + cliente.getEstado() + "')";
                    if (conectar.ejecutar(sql)) {
                        JOptionPane.showMessageDialog(null, "Sus datos fueron guardados satisfatoriamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Validar la información con el administrador, no guardo datos clientes");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Validar la información con el administrador, no guardo datos persona");
                }
            } else {
                sql = "insert into Clientes(str_correo,str_password,persona,str_estado)"
                        + "Values('" + cliente.getCorreo() + "','" + cliente.getPassword()
                        + "','" + cliente.getId() + "','" + cliente.getEstado() + "')";
                if (conectar.ejecutar(sql)) {
                    JOptionPane.showMessageDialog(null, "Sus datos fueron guardados satisfatoriamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Validar la información con el administrador, no guardo datos clientes");
                }
            }
//            conectar.cerrar();
        } catch (HeadlessException e) {
            System.out.println("Error en guardar datos cliente: " + e);
        }
    }

    public void modificar(MdlCliente cliente) {
        Conexion conectar = new Conexion();
        CtrlPersona ctrlpersona = new CtrlPersona();
        String sql;
        if (cliente.getEstado().equals("1")) {
            sql = "update Clientes set str_correo ='" + cliente.getCorreo()
                    + "' where int_id_cli=" + cliente.getId_Cli();
        } else {
            sql = "update Clientes set str_correo ='" + cliente.getCorreo()
                    + "', str_estado='0' where int_id_cli=" + cliente.getId_Cli();
        }

        try {
            conectar.conectar();
            if (ctrlpersona.Actualizar(cliente) == 1 && conectar.ejecutar(sql)) {
                JOptionPane.showMessageDialog(null, "Sus datos fueron modificados");
            } else {
                JOptionPane.showMessageDialog(null, "Validar la información con el administrador, no se modifico el registro");
            }
//            conectar.cerrar();
        } catch (HeadlessException e) {
            System.out.println("Error en eliminar datos de cliente: " + e);
        }
    }
    
    public void recuperar(MdlCliente cliente) {
        Conexion conectar = new Conexion();
        String sql;
        sql = "update Clientes set str_estado='1' where int_id_cli=" + cliente.getId_Cli();

        try {
            conectar.conectar();
            conectar.conectar();
            if (conectar.ejecutar(sql)) {
                JOptionPane.showMessageDialog(null, "Su dato fue recuperado satisfatoriamente");
            } else {
                JOptionPane.showMessageDialog(null, "Validar la información con el administrador, no se recupero el registro");
            }
//            conectar.cerrar();
        } catch (HeadlessException e) {
            System.out.println("Error en recuperar datos de cliente: " + e);
        }
    }

    public void eliminar(MdlCliente cliente) {
        Conexion conectar = new Conexion();
        String sql;
        sql = "update Clientes set str_estado='0' where int_id_cli=" + cliente.getId_Cli();
        try {
            conectar.conectar();
            if (conectar.ejecutar(sql)) {
                JOptionPane.showMessageDialog(null, "Sus datos fueron eliminados");
            } else {
                JOptionPane.showMessageDialog(null, "Validar la información con el administrador, no elimino el registro");
            }
//            conectar.cerrar();
        } catch (HeadlessException e) {
            System.out.println("Error en eliminar datos de cliente: " + e);
        }
    }

    public void buscar() {

    }

    public MdlCliente devolverCliente(MdlPersona persona) {
        Conexion conectar = new Conexion();
        MdlCliente cliente = new MdlCliente();
        String sql;
        ResultSet rs;
        sql = "select * from Clientes where persona=" + persona.getId();
        try {
            conectar.conectar();
            rs = conectar.consultar(sql);
            if (rs.next()) {
                cliente.setId_Cli(rs.getInt("int_id_cli"));
                cliente.setCorreo(rs.getString("str_correo"));
                cliente.setPassword(rs.getString("str_password"));
                cliente.setEstado(rs.getString("str_estado"));
            }
            cliente.setApellido(persona.getApellido());
            cliente.setCelular(persona.getCelular());
            cliente.setDireccion(persona.getDireccion());
            cliente.setFechaNacimiento(persona.getFechaNacimiento());
            cliente.setNombre(persona.getNombre());
            cliente.setNumeroDoc(persona.getNumeroDoc());
            cliente.setTelefono(persona.getTelefono());
            cliente.setTipoDocumento(persona.getTipoDocumento());
            cliente.setId(persona.getId());

//            conectar.cerrar();
        } catch (SQLException e) {
            System.out.println("Error en busqueda de datos persona: " + e);
        }
        return cliente;
    }

    public ArrayList<MdlCliente> listaClientes() {
        Conexion conectar = new Conexion();
        ArrayList<MdlCliente> listaclientes = new ArrayList();
        CtrlPersona ctrlpersona = new CtrlPersona();
        String sql;
        ResultSet rs;//abrebiatura de resultset
        sql = "select * from Clientes where str_estado='1'";
        try {
            conectar.conectar();
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlPersona persona = new MdlPersona();
                MdlCliente cliente = new MdlCliente();
                cliente.setId_Cli(rs.getInt("int_id_cli"));
                cliente.setCorreo(rs.getString("str_correo"));
                cliente.setPassword(rs.getString("str_password"));
                cliente.setEstado(rs.getString("str_estado"));
                cliente.setId(rs.getInt("persona"));
                persona = ctrlpersona.buscaPersona(rs.getInt("persona"));
                cliente.setApellido(persona.getApellido());
                cliente.setCelular(persona.getCelular());
                cliente.setDireccion(persona.getDireccion());
                cliente.setFechaNacimiento(persona.getFechaNacimiento());
                cliente.setNombre(persona.getNombre());
                cliente.setNumeroDoc(persona.getNumeroDoc());
                cliente.setTelefono(persona.getTelefono());
                cliente.setTipoDocumento(persona.getTipoDocumento());
                listaclientes.add(cliente);
            }

//            conectar.cerrar();
        } catch (SQLException e) {
            System.out.println("Error en busqueda de datos persona: " + e);
        }
        return listaclientes;
    }
    
    public ArrayList<MdlCliente> listaClienteIna() {
        Conexion conectar = new Conexion();
        ArrayList<MdlCliente> listaclientes = new ArrayList();
        CtrlPersona ctrlpersona = new CtrlPersona();
        String sql;
        ResultSet rs;//abrebiatura de resultset
        sql = "select * from Clientes where str_estado='0'";
        try {
            conectar.conectar();
            rs = conectar.consultar(sql);
            while (rs.next()) {
                MdlPersona persona = new MdlPersona();
                MdlCliente cliente = new MdlCliente();
                cliente.setId_Cli(rs.getInt("int_id_cli"));
                cliente.setCorreo(rs.getString("str_correo"));
                cliente.setPassword(rs.getString("str_password"));
                cliente.setEstado(rs.getString("str_estado"));
                cliente.setId(rs.getInt("persona"));
                persona = ctrlpersona.buscaPersona(rs.getInt("persona"));
                cliente.setApellido(persona.getApellido());
                cliente.setCelular(persona.getCelular());
                cliente.setDireccion(persona.getDireccion());
                cliente.setFechaNacimiento(persona.getFechaNacimiento());
                cliente.setNombre(persona.getNombre());
                cliente.setNumeroDoc(persona.getNumeroDoc());
                cliente.setTelefono(persona.getTelefono());
                cliente.setTipoDocumento(persona.getTipoDocumento());
                listaclientes.add(cliente);
            }

//            conectar.cerrar();
        } catch (SQLException e) {
            System.out.println("Error en busqueda de datos persona: " + e);
        }
        return listaclientes;
    }
}
