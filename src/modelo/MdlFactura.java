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
public class MdlFactura{
    private int id_fact;
    private String num_fac;
    private double total;
    private double sub_total;
    private double iva;
    private double descuento;
    private String fecha_registro;
    private String estado;
    private MdlCliente cliente;    

    public MdlFactura(int id_fact, String num_fac, double total, double sub_total, double iva, double descuento, String fecha_registro, String estado, MdlCliente cliente) {
        this.id_fact = id_fact;
        this.num_fac = num_fac;
        this.total = total;
        this.sub_total = sub_total;
        this.iva = iva;
        this.descuento = descuento;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
        this.cliente = cliente;
    }

    public MdlFactura() {
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public MdlCliente getCliente() {
        return cliente;
    }

    public void setCliente(MdlCliente cliente) {
        this.cliente = cliente;
    }

    public int getId_fact() {
        return id_fact;
    }

    public void setId_fact(int id_fact) {
        this.id_fact = id_fact;
    }

    public String getNum_fac() {
        return num_fac;
    }

    public void setNum_fac(String num_fac) {
        this.num_fac = num_fac;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getDescuesto() {
        return descuento;
    }

    public void setDescuesto(double descuesto) {
        this.descuento = descuesto;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
