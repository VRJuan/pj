/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author dell
 */
public class MdlDetalleFactura extends MdlFactura{
    private int id_dfa;
    private int cantidad;
    private MdlFactura factura;
    private MdlProductos producto;

    public MdlDetalleFactura(int id_dfa, int cantidad, MdlFactura factura, MdlProductos producto, int id_fact, String num_fac, double total, double sub_total, double iva, double descuento, String fecha_registro, String estado, MdlCliente cliente) {
        super(id_fact, num_fac, total, sub_total, iva, descuento, fecha_registro, estado, cliente);
        this.id_dfa = id_dfa;
        this.cantidad = cantidad;
        this.factura = factura;
        this.producto = producto;
    }

    public MdlDetalleFactura(int id_dfa, int cantidad, MdlFactura factura, MdlProductos producto) {
        this.id_dfa = id_dfa;
        this.cantidad = cantidad;
        this.factura = factura;
        this.producto = producto;
    }

    public MdlDetalleFactura() {
    }

    public MdlFactura getFactura() {
        return factura;
    }

    public void setFactura(MdlFactura factura) {
        this.factura = factura;
    }

    public MdlProductos getProducto() {
        return producto;
    }

    public void setProducto(MdlProductos producto) {
        this.producto = producto;
    }
    
   
    public int getId_dfa() {
        return id_dfa;
    }

    public void setId_dfa(int id_dfa) {
        this.id_dfa = id_dfa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
