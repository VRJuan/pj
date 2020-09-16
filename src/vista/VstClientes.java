/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.CtrlClientes;
import controlador.CtrlPersona;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.MdlCliente;
import modelo.MdlPersona;

/**
 *
 * @author scorpion
 */
public class VstClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form VstClientes
     */
    //bandera para determinar si hay datos de persona, 
    //0=no hay datos persona
    //1=existe persona y cliente no
    //2=existe cliente
    int sw = 0;

    public VstClientes() {
        initComponents();
        ocultar();
        llenarTabla();
    }

    public void limpiar() {
        txtnombres.setText("");
        txtapellidos.setText("");
        txtcelular.setText("");
        txtcorreo.setText("");
        txtdireccion.setText("");
        txtnatalicio.setText("");
        txtnatalicio.setText("");
        txtnumerodocume.setText("");
        txtpassword.setText("");
        txtpasswordveri.setText("");
        txttelefono.setText("");
        txttipodocument.setText("");
        lblidpersona.setText("");
        lblidcliente.setText("");
        bgrestado.clearSelection();
        ocultar();
    }

    public void ocultar() {
        txtcelular.setVisible(false);
        txtcorreo.setVisible(false);
        txtdireccion.setVisible(false);
        txtnatalicio.setVisible(false);
        txtnatalicio.setVisible(false);
        txtpassword.setVisible(false);
        txtpasswordveri.setVisible(false);
        txttelefono.setVisible(false);
        txttipodocument.setVisible(false);
        lblidpersona.setVisible(false);
        lblidcliente.setVisible(false);
        lblcelular.setVisible(false);
        lblcorreo.setVisible(false);
        lbldireccion.setVisible(false);
        lblestado.setVisible(false);
        lblnatalicio.setVisible(false);
        lblpassword.setVisible(false);
        lblpasswordveri.setVisible(false);
        lbltelefono.setVisible(false);
        lbltipodocumento.setVisible(false);
        rbtactivo.setVisible(false);
        rbtinactivo.setVisible(false);
    }

    public void visualizar() {
        txtcelular.setVisible(true);
        txtcorreo.setVisible(true);
        txtdireccion.setVisible(true);
        txtnatalicio.setVisible(true);
        txtnatalicio.setVisible(true);
        txtpassword.setVisible(true);
        txtpasswordveri.setVisible(true);
        txttelefono.setVisible(true);
        txttipodocument.setVisible(true);
        lblcelular.setVisible(true);
        lblcorreo.setVisible(true);
        lbldireccion.setVisible(true);
        lblnatalicio.setVisible(true);
        lblpassword.setVisible(true);
        lblpasswordveri.setVisible(true);
        lbltelefono.setVisible(true);
        lbltipodocumento.setVisible(true);
    }

    public void visualizaEstado() {
        lblestado.setVisible(true);
        rbtactivo.setVisible(true);
        rbtinactivo.setVisible(true);
    }

    public void guardar() {
        if (sw == 2) {
            JOptionPane.showMessageDialog(null, "Los datos ya existen");
        } else {
            MdlCliente cliente = new MdlCliente();
            CtrlClientes ctrlcliente = new CtrlClientes();
            cliente.setNombre(txtnombres.getText());
            cliente.setCorreo(txtcorreo.getText());
            cliente.setApellido(txtapellidos.getText());
            cliente.setCelular(txtcelular.getText());
            cliente.setDireccion(txtdireccion.getText());
            cliente.setEstado("1");
            cliente.setFechaNacimiento(txtnatalicio.getText());
            cliente.setNumeroDoc(txtnumerodocume.getText());
            cliente.setTelefono(txttelefono.getText());
            cliente.setTipoDocumento(txttipodocument.getText());
            if (txtpassword.getText().equals(txtpasswordveri.getText())) {
                cliente.setPassword(String.valueOf(txtpassword.getPassword()));
                ctrlcliente.guardar(cliente, sw);
            } else {
                JOptionPane.showMessageDialog(null, "La clave no coincide con la confirmación");
                txtpassword.setText("");
                txtpasswordveri.setText("");
            }
        }
        limpiar();
        llenarTabla();
    }

    public void buscar() {
        CtrlPersona ctrlpersona = new CtrlPersona();
        CtrlClientes ctrlcliente = new CtrlClientes();
        if (ctrlpersona.buscaPersona(txtnumerodocume.getText()) == null) {
            visualizar();
            sw = 0;
        } else {
            MdlCliente cliente = new MdlCliente();
            cliente = ctrlcliente.devolverCliente(ctrlpersona.buscaPersona(txtnumerodocume.getText()));
            visualizar();
            llenarDatos(cliente);
        }
    }

    public void llenarDatos(MdlCliente cliente) {
        visualizaEstado();
        txtnombres.setText(cliente.getNombre());
        txtapellidos.setText(cliente.getApellido());
        txtcelular.setText(cliente.getCelular());
        txtdireccion.setText(cliente.getDireccion());
        txtnatalicio.setText(cliente.getFechaNacimiento());
        txtnumerodocume.setText(cliente.getNumeroDoc());
        txttelefono.setText(cliente.getTelefono());
        txttipodocument.setText(cliente.getTipoDocumento());
        lblidpersona.setText(String.valueOf(cliente.getId()));

        txtpassword.setText("");
        txtpasswordveri.setText("");
        if (!cliente.getCorreo().isEmpty()) {
            txtcorreo.setText(cliente.getCorreo());
            lblidcliente.setText(String.valueOf(cliente.getId_Cli()));
            if (cliente.getEstado().equalsIgnoreCase("1")) {
                rbtactivo.setSelected(true);
            } else {
                rbtinactivo.setSelected(true);
            }
            sw = 2;
        } else {
            sw = 1;
        }
    }

    public void llenarTabla() {
        limpiarTabla();
        CtrlClientes ctrlcliente = new CtrlClientes();
        ArrayList<MdlCliente> listaclientes = new ArrayList();
        listaclientes = ctrlcliente.listaClientes();
        for (int posicion = 0; posicion < listaclientes.size(); posicion++) {
            tblclientes.setValueAt(posicion + 1, posicion, 0);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getNombre() + " " + ((MdlCliente) listaclientes.get(posicion)).getApellido(), posicion, 1);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getNumeroDoc(), posicion, 2);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getTipoDocumento(), posicion, 3);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getFechaNacimiento(), posicion, 4);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getDireccion(), posicion, 5);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getTelefono(), posicion, 6);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getCelular(), posicion, 7);
            tblclientes.setValueAt(((MdlCliente) listaclientes.get(posicion)).getCorreo(), posicion, 8);
            tblclientes.setValueAt("Activo", posicion, 9);
        }
    }

    public void limpiarTabla() {
        for (int posicion = 0; posicion < 100; posicion++) {
            tblclientes.setValueAt("", posicion, 0);
            tblclientes.setValueAt("", posicion, 1);
            tblclientes.setValueAt("", posicion, 2);
            tblclientes.setValueAt("", posicion, 3);
            tblclientes.setValueAt("", posicion, 4);
            tblclientes.setValueAt("", posicion, 5);
            tblclientes.setValueAt("", posicion, 6);
            tblclientes.setValueAt("", posicion, 7);
            tblclientes.setValueAt("", posicion, 8);
            tblclientes.setValueAt("", posicion, 9);
        }
    }

    public void eliminar() {
        MdlCliente cliente = new MdlCliente();
        CtrlClientes ctrlcliente = new CtrlClientes();
        cliente.setNombre(txtnombres.getText());
        cliente.setCorreo(txtcorreo.getText());
        cliente.setApellido(txtapellidos.getText());
        cliente.setCelular(txtcelular.getText());
        cliente.setDireccion(txtdireccion.getText());
        cliente.setEstado("1");
        cliente.setFechaNacimiento(txtnatalicio.getText());
        cliente.setNumeroDoc(txtnumerodocume.getText());
        cliente.setTelefono(txttelefono.getText());
        cliente.setTipoDocumento(txttipodocument.getText());
        cliente.setId_Cli(Integer.parseInt(lblidcliente.getText()));
        ctrlcliente.eliminar(cliente);
        limpiar();
        llenarTabla();
    }

    public void actualizar() {
        MdlCliente cliente = new MdlCliente();
        CtrlClientes ctrlcliente = new CtrlClientes();
        cliente.setNombre(txtnombres.getText());
        cliente.setCorreo(txtcorreo.getText());
        cliente.setApellido(txtapellidos.getText());
        cliente.setCelular(txtcelular.getText());
        cliente.setDireccion(txtdireccion.getText());
        if (rbtinactivo.isSelected()) {
            cliente.setEstado("0");
        } else {
            cliente.setEstado("1");
        }
        cliente.setFechaNacimiento(txtnatalicio.getText());
        cliente.setNumeroDoc(txtnumerodocume.getText());
        cliente.setTelefono(txttelefono.getText());
        cliente.setTipoDocumento(txttipodocument.getText());
        cliente.setId(Integer.parseInt(lblidpersona.getText()));
        cliente.setId_Cli(Integer.parseInt(lblidcliente.getText()));
        ctrlcliente.modificar(cliente);
        limpiar();
        llenarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrestado = new javax.swing.ButtonGroup();
        pnlprincipal = new javax.swing.JPanel();
        pnldatos = new javax.swing.JPanel();
        txtnombres = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        lblnombres = new javax.swing.JLabel();
        lblcorreo = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        lblapellidos = new javax.swing.JLabel();
        txtapellidos = new javax.swing.JTextField();
        lblcelular = new javax.swing.JLabel();
        txtcelular = new javax.swing.JTextField();
        txtnatalicio = new javax.swing.JTextField();
        lblnatalicio = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        lbldireccion = new javax.swing.JLabel();
        lbltipodocumento = new javax.swing.JLabel();
        txttipodocument = new javax.swing.JTextField();
        lblpassword = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        lblpasswordveri = new javax.swing.JLabel();
        txtpasswordveri = new javax.swing.JPasswordField();
        lblestado = new javax.swing.JLabel();
        rbtactivo = new javax.swing.JRadioButton();
        rbtinactivo = new javax.swing.JRadioButton();
        lblnumerodocumento = new javax.swing.JLabel();
        txtnumerodocume = new javax.swing.JTextField();
        pnlbotones = new javax.swing.JPanel();
        btnagregar = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        lblidpersona = new javax.swing.JLabel();
        lblidcliente = new javax.swing.JLabel();
        pnldetalle = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblclientes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestión de Clientes");

        pnlprincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        pnldatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        txtnombres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtcorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txttelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblnombres.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblnombres.setText("Nombres:");

        lblcorreo.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblcorreo.setText("Correo:");

        lbltelefono.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbltelefono.setText("Télefono:");

        lblapellidos.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblapellidos.setText("Apellidos:");

        txtapellidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblcelular.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblcelular.setText("Celular:");

        txtcelular.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtnatalicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblnatalicio.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblnatalicio.setText("Fecha Nacimiento:");

        txtdireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbldireccion.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbldireccion.setText("Dirección:");

        lbltipodocumento.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbltipodocumento.setText("Tipo Documento:");

        txttipodocument.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblpassword.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblpassword.setText("Password:");

        lblpasswordveri.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblpasswordveri.setText("Confirmar Password:");

        lblestado.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblestado.setText("Estado:");

        bgrestado.add(rbtactivo);
        rbtactivo.setText("Activo");

        bgrestado.add(rbtinactivo);
        rbtinactivo.setText("Inactivo");

        lblnumerodocumento.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblnumerodocumento.setText("Número Documento:");

        txtnumerodocume.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnldatosLayout = new javax.swing.GroupLayout(pnldatos);
        pnldatos.setLayout(pnldatosLayout);
        pnldatosLayout.setHorizontalGroup(
            pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldatosLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblnumerodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtnumerodocume, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lbltipodocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txttipodocument, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lbltelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblcelular, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblnatalicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtnatalicio, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lbldireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblpasswordveri, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtpasswordveri, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addComponent(lblestado)
                        .addGap(116, 116, 116)
                        .addComponent(rbtactivo)
                        .addGap(22, 22, 22)
                        .addComponent(rbtinactivo))))
        );
        pnldatosLayout.setVerticalGroup(
            pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldatosLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnumerodocumento)
                    .addComponent(txtnumerodocume, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnombres)
                    .addComponent(txtnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblapellidos)
                    .addComponent(txtapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltipodocumento)
                    .addComponent(txttipodocument, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltelefono)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblcelular)
                    .addComponent(txtcelular, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnatalicio)
                    .addComponent(txtnatalicio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbldireccion)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblcorreo))
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblpassword)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblpasswordveri)
                    .addComponent(txtpasswordveri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnldatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblestado)
                    .addComponent(rbtactivo)
                    .addComponent(rbtinactivo)))
        );

        pnlbotones.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));

        btnagregar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        btndelete.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btndelete.setText("Eliminar");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnactualizar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btnlimpiar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        btnbuscar.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlbotonesLayout = new javax.swing.GroupLayout(pnlbotones);
        pnlbotones.setLayout(pnlbotonesLayout);
        pnlbotonesLayout.setHorizontalGroup(
            pnlbotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlbotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnagregar, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(btnlimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlbotonesLayout.setVerticalGroup(
            pnlbotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbotonesLayout.createSequentialGroup()
                .addComponent(btnbuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnagregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnactualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnlimpiar)
                .addGap(90, 90, 90))
        );

        javax.swing.GroupLayout pnlprincipalLayout = new javax.swing.GroupLayout(pnlprincipal);
        pnlprincipal.setLayout(pnlprincipalLayout);
        pnlprincipalLayout.setHorizontalGroup(
            pnlprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlprincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnldatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(pnlprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblidpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(356, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlprincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlbotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlprincipalLayout.setVerticalGroup(
            pnlprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlprincipalLayout.createSequentialGroup()
                .addGroup(pnlprincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlprincipalLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(pnlbotones, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(lblidpersona, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlprincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnldatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnldetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        tblclientes = new javax.swing.JTable(){
            public boolean isCellEditable(int fila,int col){
                return false;
            }
        };
        tblclientes.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tblclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Nombre", "Numero Documento", "Tipo Documento", "Fecha Nacimiento", "Dirección", "Teléfono", "Celular", "Correo", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblclientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblclientes.setFocusable(false);
        tblclientes.setRowMargin(4);
        tblclientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblclientes);
        if (tblclientes.getColumnModel().getColumnCount() > 0) {
            tblclientes.getColumnModel().getColumn(0).setMinWidth(30);
            tblclientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblclientes.getColumnModel().getColumn(0).setMaxWidth(50);
            tblclientes.getColumnModel().getColumn(1).setMinWidth(100);
            tblclientes.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblclientes.getColumnModel().getColumn(1).setMaxWidth(300);
            tblclientes.getColumnModel().getColumn(2).setMinWidth(70);
            tblclientes.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblclientes.getColumnModel().getColumn(2).setMaxWidth(150);
            tblclientes.getColumnModel().getColumn(3).setMinWidth(70);
            tblclientes.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblclientes.getColumnModel().getColumn(3).setMaxWidth(150);
            tblclientes.getColumnModel().getColumn(4).setMinWidth(70);
            tblclientes.getColumnModel().getColumn(4).setPreferredWidth(150);
            tblclientes.getColumnModel().getColumn(4).setMaxWidth(150);
            tblclientes.getColumnModel().getColumn(5).setMinWidth(100);
            tblclientes.getColumnModel().getColumn(5).setPreferredWidth(200);
            tblclientes.getColumnModel().getColumn(5).setMaxWidth(200);
            tblclientes.getColumnModel().getColumn(6).setMinWidth(50);
            tblclientes.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblclientes.getColumnModel().getColumn(6).setMaxWidth(100);
            tblclientes.getColumnModel().getColumn(7).setMinWidth(50);
            tblclientes.getColumnModel().getColumn(7).setPreferredWidth(100);
            tblclientes.getColumnModel().getColumn(7).setMaxWidth(100);
            tblclientes.getColumnModel().getColumn(8).setMinWidth(100);
            tblclientes.getColumnModel().getColumn(8).setPreferredWidth(250);
            tblclientes.getColumnModel().getColumn(8).setMaxWidth(250);
            tblclientes.getColumnModel().getColumn(9).setMinWidth(50);
            tblclientes.getColumnModel().getColumn(9).setPreferredWidth(100);
            tblclientes.getColumnModel().getColumn(9).setMaxWidth(100);
        }

        javax.swing.GroupLayout pnldetalleLayout = new javax.swing.GroupLayout(pnldetalle);
        pnldetalle.setLayout(pnldetalleLayout);
        pnldetalleLayout.setHorizontalGroup(
            pnldetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        pnldetalleLayout.setVerticalGroup(
            pnldetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldetalleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlprincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnldetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlprincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnldetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        // TODO add your handling code here:
        actualizar();
    }//GEN-LAST:event_btnactualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrestado;
    public javax.swing.JButton btnactualizar;
    public javax.swing.JButton btnagregar;
    public javax.swing.JButton btnbuscar;
    public javax.swing.JButton btndelete;
    public javax.swing.JButton btnlimpiar;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblapellidos;
    private javax.swing.JLabel lblcelular;
    private javax.swing.JLabel lblcorreo;
    private javax.swing.JLabel lbldireccion;
    private javax.swing.JLabel lblestado;
    private javax.swing.JLabel lblidcliente;
    private javax.swing.JLabel lblidpersona;
    private javax.swing.JLabel lblnatalicio;
    private javax.swing.JLabel lblnombres;
    private javax.swing.JLabel lblnumerodocumento;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblpasswordveri;
    private javax.swing.JLabel lbltelefono;
    private javax.swing.JLabel lbltipodocumento;
    private javax.swing.JPanel pnlbotones;
    public javax.swing.JPanel pnldatos;
    private javax.swing.JPanel pnldetalle;
    private javax.swing.JPanel pnlprincipal;
    private javax.swing.JRadioButton rbtactivo;
    private javax.swing.JRadioButton rbtinactivo;
    public javax.swing.JTable tblclientes;
    public javax.swing.JTextField txtapellidos;
    public javax.swing.JTextField txtcelular;
    public javax.swing.JTextField txtcorreo;
    public javax.swing.JTextField txtdireccion;
    public javax.swing.JTextField txtnatalicio;
    public javax.swing.JTextField txtnombres;
    public javax.swing.JTextField txtnumerodocume;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JPasswordField txtpasswordveri;
    public javax.swing.JTextField txttelefono;
    public javax.swing.JTextField txttipodocument;
    // End of variables declaration//GEN-END:variables
}
