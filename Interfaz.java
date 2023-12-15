package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Interfaz extends JFrame {
    private Memoria memoriaPrincipal;

    private JPanel inicio;
    private JPanel aux;
    private JTable tablaProcesosEnEspera;
    private DefaultTableModel modeloEnEspera;
    private JTable tablaProcesosListos;
    private DefaultTableModel modeloListos;
    private JLabel lbl_titulo;
    public JLabel lbl_memoria;
    public JLabel lbl_infoUntiempo;
    public JLabel lbl_tiempo;
    private JLabel lbl_tituloTabla1;
    private JLabel lbl_tituloTabla2;
    private JLabel lbl_CPU;
    private JLabel lbl_SRT;
    private JLabel lbl_config;
    private JLabel lbl_nuevaMemoria;
    private JLabel lbl_nuevoTiempo;
    private JLabel lbl_generarProceso;
    private JLabel lbl_Aviso;
    private JLabel lbl_infRegeneracion;
    private JTextField txt_nuevaMemoria;
    private JTextField txt_nuevoTiempo;
    private JTextField txt_nuevaGeneracion;
    private JTextArea textArea_CPU;
    private JButton btn_cambiarMemoria;
    private JButton Btn_cambiarTiempo;
    private JButton btn_cambiarGeneracion;
    private Color Azul = new Color(160,210,200);

    public Interfaz (Memoria memoriaPrincipal){
        this.memoriaPrincipal = memoriaPrincipal;
        init();
    }

    private void init() {
        this.setSize(1920, 1080);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        inicio = new JPanel();
        inicio.setBackground(Azul);
        inicio.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        //gbc.fill = GridBagConstraints.HORIZONTAL; //NO
        //gbc.fill = GridBagConstraints.BOTH;

        //FILA 0
        aux = new JPanel();
        aux.setBackground(Azul);
        gbc.gridx = 0;
        gbc.gridy = 0;
        inicio.add(aux, gbc);

        lbl_titulo = new JLabel("SIMULADOR DE ADMINISTRACION DE PROCESOS", SwingConstants.CENTER);
        lbl_titulo.setFont(new Font("Times New Roman", Font.BOLD, 40));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        inicio.add(lbl_titulo, gbc);

        aux = new JPanel();
        aux.setBackground(Azul);
        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        inicio.add(aux,gbc);

        //FILA 1
        lbl_infRegeneracion = new JLabel("Generación de procesos cada " + memoriaPrincipal.getRegeneracion() + " ms.",SwingConstants.CENTER);
        lbl_infRegeneracion.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        inicio.add(lbl_infRegeneracion,gbc);

        lbl_SRT = new JLabel("Algoritmo: Shortest Remaining Time",SwingConstants.CENTER);
        lbl_SRT.setFont(new Font("Times New Roman", Font.BOLD, 35));
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        inicio.add(lbl_SRT,gbc);

        lbl_infoUntiempo = new JLabel("",SwingConstants.CENTER);
        lbl_infoUntiempo.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        gbc.gridx = 8;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        inicio.add(lbl_infoUntiempo,gbc);

        //FILA 2
        lbl_memoria = new JLabel("",SwingConstants.LEFT);
        lbl_memoria.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        inicio.add(lbl_memoria, gbc);

        lbl_tiempo = new JLabel("",SwingConstants.CENTER);
        lbl_tiempo.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        gbc.gridx = 7;
        gbc.gridy = 2;
        inicio.add(lbl_tiempo,gbc);

        //Fila 3
        lbl_tituloTabla1 = new JLabel("Procesos en espera",SwingConstants.CENTER);
        lbl_tituloTabla1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        gbc.gridx = 1;
        gbc.gridy = 3;
        inicio.add(lbl_tituloTabla1,gbc);

        lbl_tituloTabla2 = new JLabel("Procesos listos",SwingConstants.CENTER);
        lbl_tituloTabla2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        gbc.gridx = 4;
        gbc.gridy = 3;
        inicio.add(lbl_tituloTabla2,gbc);

        lbl_CPU = new JLabel("CPU:",SwingConstants.CENTER);
        lbl_CPU.setFont(new Font("Times New Roman", Font.BOLD, 25));
        gbc.gridx = 7;
        gbc.gridy = 3;
        inicio.add(lbl_CPU,gbc);

        //FILA 4
        modeloEnEspera = new DefaultTableModel(new Object[] {"Nombre","Memoria","Tiempos"},0);
        tablaProcesosEnEspera = new JTable(modeloEnEspera);
        tablaProcesosEnEspera.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tablaProcesosEnEspera.setRowHeight(27);
        JScrollPane scrollPaneEnEspera = new JScrollPane(tablaProcesosEnEspera,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 3;
        inicio.add(scrollPaneEnEspera,gbc);

        aux = new JPanel();
        aux.setBackground(Azul);
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        inicio.add(aux,gbc);

        modeloListos = new DefaultTableModel(new Object[] {"Nombre","Memoria","Tiempos"},0);
        tablaProcesosListos = new JTable(modeloListos);
        tablaProcesosListos.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        tablaProcesosListos.setRowHeight(27);
        JScrollPane scrollPaneListos = new JScrollPane(tablaProcesosListos,ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        inicio.add(scrollPaneListos,gbc);

        aux = new JPanel();
        aux.setBackground(Azul);
        gbc.gridx = 6;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        inicio.add(aux,gbc);

        textArea_CPU = new JTextArea();
        textArea_CPU.setFont(new Font("Times New Roman", Font.BOLD, 30));
        textArea_CPU.setEditable(false);
        textArea_CPU.setPreferredSize(new Dimension(300,80));
        gbc.gridx = 7;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inicio.add(textArea_CPU,gbc);

        //Fila 5
        aux = new JPanel();
        aux.setBackground(Azul);
        gbc.gridx = 7;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        inicio.add(aux,gbc);

        //Fila 6
        aux = new JPanel(new BorderLayout()); //Aqui van las configuraciones
        aux.setBackground(new Color(180,180,110));

        lbl_config = new JLabel("CONFIGURACIONES",SwingConstants.CENTER);
        lbl_config.setFont(new Font("Times New Roman", Font.BOLD, 30));
        aux.add(lbl_config,BorderLayout.NORTH);


        JPanel aux2 = new JPanel(new GridLayout(3,1));

        lbl_nuevaMemoria = new JLabel(" Memoria: ",SwingConstants.RIGHT);
        lbl_nuevaMemoria.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        aux2.add(lbl_nuevaMemoria);

        lbl_nuevoTiempo = new JLabel(" Tiempo (ms): ",SwingConstants.RIGHT);
        lbl_nuevoTiempo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        aux2.add(lbl_nuevoTiempo);

        lbl_generarProceso = new JLabel(" Creación (ms): ",SwingConstants.RIGHT);
        lbl_generarProceso.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        aux2.add(lbl_generarProceso);

        aux.add(aux2,BorderLayout.WEST);


        aux2 = new JPanel(new GridLayout(3,1));

        txt_nuevaMemoria = new JTextField();
        txt_nuevaMemoria.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        aux2.add(txt_nuevaMemoria);

        txt_nuevoTiempo = new JTextField();
        txt_nuevoTiempo.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        aux2.add(txt_nuevoTiempo);

        txt_nuevaGeneracion = new JTextField();
        txt_nuevaGeneracion.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        aux2.add(txt_nuevaGeneracion);

        aux.add(aux2,BorderLayout.CENTER);


        aux2 = new JPanel(new GridLayout(3,1));

        btn_cambiarMemoria = new JButton("Cambiar");
        btn_cambiarMemoria.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        btn_cambiarMemoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux = txt_nuevaMemoria.getText();
                int nuevo;
                try {
                    nuevo = Integer.parseInt(aux);
                    if(memoriaPrincipal.setMemoria(nuevo))
                        lbl_memoria.setText("Memoria: principal: " + memoriaPrincipal.toString() + "/" + memoriaPrincipal.getMemoriaTotal() + " mb.");
                    else
                        JOptionPane.showMessageDialog(null, "El nuevo valor es inferior a la memoria actualmente utilizada", "Valor inferior", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "El formato no es admitido", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    txt_nuevaMemoria.setText("");
                }
            }
        });
        aux2.add(btn_cambiarMemoria);

        Btn_cambiarTiempo = new JButton("Cambiar");
        Btn_cambiarTiempo.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        Btn_cambiarTiempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux = txt_nuevoTiempo.getText();
                try {
                    memoriaPrincipal.setUnTiempo(Integer.parseInt(aux));
                    lbl_infoUntiempo.setText("1 tiempo = " + memoriaPrincipal.getUnTiempo() + " ms.");
                } catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "El formato no es admitido", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    txt_nuevoTiempo.setText("");
                }
            }
        });
        aux2.add(Btn_cambiarTiempo);

        btn_cambiarGeneracion = new JButton("Cambiar");
        btn_cambiarGeneracion.setFont(new Font("Times New Roman", Font.PLAIN, 23));
        btn_cambiarGeneracion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aux = txt_nuevaGeneracion.getText();
                int nuevo;
                try {
                    nuevo = Integer.parseInt(aux);
                    memoriaPrincipal.setRegeneracion(nuevo);
                    lbl_infRegeneracion.setText("Generación de procesos cada " + memoriaPrincipal.getRegeneracion() + " ms.");
                } catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "El formato no es admitido", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    txt_nuevaGeneracion.setText("");
                }
            }
        });
        aux2.add(btn_cambiarGeneracion);

        aux.add(aux2,BorderLayout.EAST);

        lbl_Aviso = new JLabel("*Tener cuidado con valores muy pequeños*",SwingConstants.CENTER);
        lbl_Aviso.setFont(new Font("Times New Roman", Font.BOLD, 25));
        aux.add(lbl_Aviso,BorderLayout.SOUTH);

        gbc.gridx = 7;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        inicio.add(aux,gbc);

        //Fila 7
        aux = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        inicio.add(aux,gbc);


        this.add(inicio);
    }

    public synchronized void llenarTabla(List<Proceso> listaProceos, int lista){
        List<Proceso> aux = new ArrayList<>(listaProceos);  //Se realiza una copia de la lista porque otro hilo la altera al mismo tiempo
        Object[] datos = new Object[3];

        if(lista==1){ //Significa que es la lista de procesos en espera
            modeloEnEspera.setRowCount(0);
            for (Proceso pros : aux){
                datos[0] = pros.getNombre();
                datos[1] = pros.getMemoria();
                datos[2] = pros.getTiempos();
                modeloEnEspera.addRow(datos);
            }
            tablaProcesosEnEspera.setModel(modeloEnEspera);
        }
        else { //Significa que es la lista de procesos listos
            modeloListos.setRowCount(0);
            for (Proceso pros : aux){
                datos[0] = pros.getNombre();
                datos[1] = pros.getMemoria();
                datos[2] = pros.getTiempos();
                modeloListos.addRow(datos);
            }
            tablaProcesosListos.setModel(modeloListos);
        }
    }

    public void usarCPU(Proceso proceso){
        String nombre = proceso.getNombre();
        int memoria = proceso.getMemoria();;
        int tiempos = proceso.getTiempos();
        textArea_CPU.setText("\n" + nombre + "\nMemoria: " + memoria + " mb.\n" + "Tiempos restantes: " + tiempos + "\n");
    }
}

class Principal {
    public static void main(String[] args){
        Memoria memoriaPrincipal = new Memoria(1000,300,300);
        List<Proceso> procesosListos = new ArrayList<>();
        Semaphore mutex = new Semaphore(1);

        Interfaz nuevaVentana = new Interfaz(memoriaPrincipal);
        nuevaVentana.setVisible(true);


        ProcessGenerator Hilo1 = new ProcessGenerator(procesosListos,mutex,memoriaPrincipal,nuevaVentana);
        OperativeSystem Hilo2 = new OperativeSystem(procesosListos,mutex,memoriaPrincipal,nuevaVentana);

        Hilo1.start();
        Hilo2.start();
    }
}

class Proceso {
    private int memoria;
    private int tiempos; //Cantidad de ms que necesita para ejecutarse
    private final String nombre;
    public Proceso(){
        memoria = 0;
        tiempos = 0;
        nombre = "";
    }
    public Proceso(int memoria, int tiempos, String nombre){
        this.memoria = memoria;
        this.tiempos = tiempos;
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }
    public void setTiempos(int tiempos){
        this.tiempos = tiempos;
    }
    public int getMemoria(){
        return memoria;
    }
    public int getTiempos(){
        return tiempos;
    }
}

class Memoria {
    private int regeneracion;  //Cada cuantos ms. genera un proceso
    private String memoriaTotal; //Valor total de la memoria
    private int unTiempo; //ms que dura un tiempo
    private int memoria; //Memoria sin uso
    public Memoria(int memoria, int unTiempo, int regeneracion){
        this.memoria = memoria;
        this.memoriaTotal = String.valueOf(memoria);
        this.unTiempo = unTiempo;
        this.regeneracion = regeneracion;
    }
    public void asignarMemoria(int memoria){
        this.memoria-=memoria;
    }
    public void liberarMemoria(int memoria){
        this.memoria+=memoria;
    }
    public boolean setMemoria(int memoria){
        if((memoria-(Integer.parseInt(memoriaTotal)-this.memoria))>=0){ //Verifica que la nueva memoria no sea menor a la memoria que esta siendo usada
            this.memoria = memoria-(Integer.parseInt(memoriaTotal)-this.memoria); //A la memoria nueva se le quita la que esta siendo usada
            this.memoriaTotal = String.valueOf(memoria);
            return true;
        }
        return false;
    }
    public synchronized int getMemoria(){
        return memoria;
    }
    @Override
    public String toString(){
        return String.valueOf(memoria);
    }
    public String getMemoriaTotal(){
        return memoriaTotal;
    }
    public void setUnTiempo(int unTiempo){
        this.unTiempo = unTiempo;
    }
    public int getUnTiempo(){
        return  unTiempo;
    }
    public void setRegeneracion(int regeneracion){
        this.regeneracion = regeneracion;
    }
    public int getRegeneracion(){
        return regeneracion;
    }
}

class ProcessGenerator extends Thread{  //Se encarga de estar generando procesos cada determinado tiempo
    private Random numeroRandom = new Random();
    private Semaphore mutex;
    private Memoria memoriaPrincipal;
    private Interfaz nuevaVentana;
    private final java.util.List<Proceso> procesosListos; //Aquí se van encolando los procesos que se van creando
    public ProcessGenerator(java.util.List<Proceso> procesosListos, Semaphore mutex, Memoria memoriaPrincipal, Interfaz nuevaVentana){
        this.procesosListos = procesosListos;
        this.mutex = mutex;
        this.memoriaPrincipal = memoriaPrincipal;
        this.nuevaVentana = nuevaVentana;
    }

    @Override
    public void run(){
        java.util.List<Proceso> procesosEnEspera = new ArrayList<>();
        int i=1, memoria, tiempos;
        String nombreProceso = "Proceso ";
        Proceso nuevoProceso, aux;

        while (true){
            memoria = numeroRandom.nextInt(100) + 1;
            tiempos = numeroRandom.nextInt(10) + 1;  //Numero de tiempos que necesita
            nombreProceso+= String.valueOf(i);
            nuevoProceso = new Proceso(memoria,tiempos,nombreProceso);

            if (!procesosEnEspera.isEmpty()){
                procesosEnEspera.add(nuevoProceso);
                nuevoProceso = procesosEnEspera.get(0);
                procesosEnEspera.remove(0);
            }

            if (memoriaPrincipal.getMemoria() >= nuevoProceso.getMemoria()){ //Significa que hay memoria suficiente
                nuevaVentana.llenarTabla(procesosEnEspera,1);
                memoriaPrincipal.asignarMemoria(nuevoProceso.getMemoria());  //Asigna memoria al proceso creado
                nuevaVentana.lbl_memoria.setText("Memoria principal: " + memoriaPrincipal.toString() + "/" + memoriaPrincipal.getMemoriaTotal() + " mb.");

                try {
                    mutex.acquire();
                    procesosListos.add(nuevoProceso);
                    mutex.release();
                    nuevaVentana.llenarTabla(procesosListos,2);
                    sleep(memoriaPrincipal.getRegeneracion());
                } catch (InterruptedException e) {
                    System.out.println("No se pudo dormir el proceso o error con el semaforo");
                }
            }
            else {
                procesosEnEspera.add(0, nuevoProceso);  //Por si no hay memoria todavia, el proceso que se sacó de la cola en espera se pone de nuevo al frente
                nuevaVentana.llenarTabla(procesosEnEspera,1);
            }
            nombreProceso = "Proceso ";
            i++;
        }
    }
}

class OperativeSystem extends Thread{  //Decide quien toma el CPU de acuerdo al algoritmo: Shortest Remaining Time
    private Memoria memoriaPrincipal;
    private Semaphore mutex;
    private java.util.List<Proceso> procesosListos;  //Procesos que ya tienen memoria y sus tiempos
    private Interfaz nuevaVentana;

    public OperativeSystem(List<Proceso> procesosListos, Semaphore mutex, Memoria memoriaPrincipal, Interfaz nuevaVentana){
        this.mutex = mutex;
        this.procesosListos = procesosListos;
        this.memoriaPrincipal = memoriaPrincipal;
        this.nuevaVentana = nuevaVentana;
    }

    @Override
    public void run(){
        int contador = 0;
        boolean CPU, finalizado=true;
        Proceso procesoCorriendo=null;
        Proceso aux;

        nuevaVentana.lbl_infoUntiempo.setText("1 tiempo = " + memoriaPrincipal.getUnTiempo() + " ms.");

        while (true){  //Cada iteración de este ciclo simula un tiempo
            try {
                mutex.acquire();
                Collections.sort(procesosListos, Comparator.comparingInt(Proceso::getTiempos));  //Ordena los procesos listos ascendentemente de acuerdo a sus tiempos

                if (finalizado && !procesosListos.isEmpty()){ //Significa que no hay un proceso en la CPU pero si procesos listos
                    procesoCorriendo = procesosListos.get(0);
                    procesosListos.remove(0);
                    CPU = true; //Se le asigna la CPU
                    finalizado = false;  //Indica que aun le faltan tiempos por trabajar
                    nuevaVentana.llenarTabla(procesosListos,2);
                }
                else if (!finalizado){
                    if(!procesosListos.isEmpty()){ //Significa que hay un proceso en la CPU pero hay procesos listos que pueden ser menores
                        aux = procesosListos.get(0);
                        if(aux.getTiempos() < procesoCorriendo.getTiempos()){
                            procesosListos.remove(0);
                            procesosListos.add(0,procesoCorriendo);
                            procesoCorriendo = aux;
                            nuevaVentana.llenarTabla(procesosListos,2);
                        }
                    }
                    CPU = true;
                }
                else //No hay un proceso en la CPU ni procesos listos
                    CPU=false;
                mutex.release();
            } catch (InterruptedException e){
                System.out.println("Error con el semaforo");
                CPU=false;
            }

            if(CPU){
                try{
                    nuevaVentana.lbl_tiempo.setText("Tiempo: " + String.valueOf(contador));
                    nuevaVentana.usarCPU(procesoCorriendo);
                    sleep(memoriaPrincipal.getUnTiempo()); // Simula que el proceso trabaja un tiempo
                    procesoCorriendo.setTiempos(procesoCorriendo.getTiempos()-1);  //Al proceso se le resta el tiempo que trabajó
                } catch (InterruptedException e2) {
                    System.out.println("No se puede dormir el proceso");
                }

                if (procesoCorriendo.getTiempos()==0) { //Es hora de que el proceso muera
                    memoriaPrincipal.liberarMemoria(procesoCorriendo.getMemoria()); //Se regresa el recurso de meoria
                    nuevaVentana.lbl_memoria.setText("Memoria principal: " + memoriaPrincipal.toString() + " / " + memoriaPrincipal.getMemoriaTotal() + " mb.");
                    finalizado = true;
                }
            }
            else {  //Significa que no hay procesos listos ni en la CPU
                try{
                    sleep(memoriaPrincipal.getUnTiempo()); // Simula que pasa un tiempo en la CPU
                } catch (InterruptedException e2) {
                    System.out.println("No se puede dormir el proceso");
                }
            }
            contador++;
        }
    }
}
