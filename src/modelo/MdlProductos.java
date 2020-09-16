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
public class MdlProductos {

    private int id_pro;
    private String nombre;
    private String codigoqr;
    private String codigobarras;
    private int stock;
    private String tipo;
    private String color;
    private String tono;
    private String peso;
    private String dimensiones;
    private String estado;

    public MdlProductos(int id_pro, String nombre, String codigoqr, String codigobarras, int stock, String tipo, String color, String tono, String peso, String dimensiones, String estado) {
        this.id_pro = id_pro;
        this.nombre = nombre;
        this.codigoqr = codigoqr;
        this.codigobarras = codigobarras;
        this.stock = stock;
        this.tipo = tipo;
        this.color = color;
        this.tono = tono;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.estado = estado;
    }

    public MdlProductos() {
    }

    
    public int getId_pro() {
        return id_pro;
    }

    public void setId_pro(int id_pro) {
        this.id_pro = id_pro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoqr() {
        return codigoqr;
    }

    public void setCodigoqr(String codigoqr) {
        this.codigoqr = codigoqr;
    }

    public String getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(String codigobarras) {
        this.codigobarras = codigobarras;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTono() {
        return tono;
    }

    public void setTono(String tono) {
        this.tono = tono;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
