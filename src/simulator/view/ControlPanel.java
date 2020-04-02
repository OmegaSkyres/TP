package simulator.view;

import Utils.Utils;
import javafx.scene.control.ToolBar;
import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ControlPanel extends JPanel implements TrafficSimObserver {
    private JSpinner steps;
    private JFileChooser fc;
    private int contadorTiempo;
    private Controller controlador;
    private File ficheroActual;
    private JToolBar toolbar;

    public ControlPanel(Controller ctrl) {
        // BARRA DE HERRAMIENTAS
        toolbar = createBotonera(ctrl);
        this.add(toolbar,LEFT_ALIGNMENT);

    }

    private JToolBar createBotonera(Controller ctrl) {
        JToolBar toolBar = new JToolBar();
        fc = new JFileChooser();
        ctrl.addObserver(this);


        //Boton de cargar
        JButton botonCargar = new JButton();
        botonCargar.setToolTipText("Carga un fichero de ventos");
        botonCargar.setIcon(new ImageIcon(Utils.loadImage("resources/icons/open.png")));
        botonCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                cargaFichero();
            }
        });
        toolBar.add(botonCargar,LEFT_ALIGNMENT);
        toolBar.addSeparator();
        //Boton de cambiar contaminacion
        JButton botonChangeContamination = new JButton();
        botonChangeContamination.setToolTipText("Cambio de las condiciones atmosféricas de una carretera ");
        botonChangeContamination.setIcon(new ImageIcon(Utils.loadImage("resources/icons/co2class.png")));
        toolBar.add(botonChangeContamination);

        //Boton de cambiar el tiempo
        JButton botonChangeWeather= new JButton();
        botonChangeWeather.setToolTipText("Cambio de el tiempo ");
        botonChangeWeather.setIcon(new ImageIcon(Utils.loadImage("resources/icons/weather.png")));
        toolBar.add(botonChangeWeather);

        toolBar.addSeparator();

        //Boton de cambiar contaminacion
        JButton botonPlay= new JButton();
        botonPlay.setToolTipText("Comienza la simulacion");
        botonPlay.setIcon(new ImageIcon(Utils.loadImage("resources/icons/run.png")));
        toolBar.add(botonPlay);

        //Boton de cambiar contaminacion
        JButton botonStop= new JButton();
        botonStop.setToolTipText("Detiene la Simulación");
        botonStop.setIcon(new ImageIcon(Utils.loadImage("resources/icons/stop.png")));
        toolBar.add(botonStop);

        toolBar.add(new JLabel(" Ticks: "));
        this.steps = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
        this.steps.setToolTipText("pasos a ejecutar: 1-1000");
        this.steps.setMaximumSize(new Dimension(70, 70));
        this.steps.setMinimumSize(new Dimension(70, 70));
        this.steps.setValue(1);
        steps.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int)steps.getValue();
                //mainWindow.setSteps(value);
            }
        });
        toolBar.add(steps);

        toolBar.addSeparator();

        //Boton de Detener

        JButton botonApagar = new JButton();
        botonApagar.setToolTipText("Detiene la Simulación");
        botonApagar.setIcon(new ImageIcon(Utils.loadImage("resources/icons/exit.png")));
        botonApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               salir();
            }
        });
        toolBar.add(botonApagar);

        return toolBar;

    }

    private void salir() {
        if(JOptionPane.showConfirmDialog(this, "¿Seguro que quiere salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)
                == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    public void cargaFichero() {
        contadorTiempo = 0;
        int returnVal = this.fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fichero = this.fc.getSelectedFile();
            try {
                String s = leeFichero(fichero);
                this.controlador.reset();
                this.ficheroActual = fichero;
                controlador.setFicheroEntrada(this.ficheroActual); //TODO REVISAR
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String leeFichero(File fichero) throws IOException {
        String res = "";

        FileReader archivo = new FileReader(fichero);
        BufferedReader b = new BufferedReader(archivo);

        String line;
        while((line = b.readLine()) != null) {
            res += line +"\n";
        }

        b.close();


        return res;
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {

    }

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {

    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {

    }

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {

    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {

    }

    @Override
    public void onError(String err) {

    }

}
