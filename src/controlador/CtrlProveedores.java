package controlador;

import conexion.Conexion;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import modelo.MdlProveedores;

public class CtrlProveedores {

    public void registrar(MdlProveedores pro) {
        Conexion con = new Conexion();

        String sql = "INSERT INTO Proveedores (nit, nombre, direccion , contacto, correo_electronico, telefono) "
                + "VALUES('" + pro.getNit() + "', '" + pro.getNombre() + "', '" + pro.getDireccion() + "',"
                + "'" + pro.getContacto() + "','" + pro.getEmail() + "','" + pro.getTelefono() + "')";

        try {
            if (con.ejecutar(sql)) {
                JOptionPane.showMessageDialog(null, "Datos registrados satisfactoriamente.");
            } else {
                JOptionPane.showMessageDialog(null, "ERROR al registrar datos.");
            }

        } catch (HeadlessException e) {
            System.err.println("Error en registrar: " + e);
        }
    }

    public void modificar(MdlProveedores pro) {

    }

    public void eliminar(MdlProveedores pro) {

    }

    public void buscar(MdlProveedores pro) {

    }
}
