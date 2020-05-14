package simulator.view;

import extra.jtable.EventEx;
import javafx.scene.control.ToolBar;
import simulator.control.Controller;
import simulator.exceptions.WrongValuesContamination;
import simulator.exceptions.WrongValuesException;
import simulator.exceptions.WrongValuesWeather;
import simulator.model.Event;
import simulator.model.Observable;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class ControlPanel extends JPanel implements TrafficSimObserver {
    private JSpinner steps;
    private JSpinner delay;
    private JFileChooser fc;
    private Controller controlador;
    private File ficheroActual;
    private JToolBar toolBar;
    private JButton botonCargar;
    private JButton botonChangeContamination;
    private JButton botonChangeWeather;
    private JButton botonPlay;
    private JButton botonStop;
    private JButton botonApagar;
    private Controller _ctrl;
    private int ticks;
    private long delayTime;
    private volatile Thread _thread;

    public ControlPanel(Controller ctrl) {

        ticks = 1;
        _ctrl = ctrl;
        BorderLayout layout = new BorderLayout();
        this.controlador = ctrl;
        this.setLayout(layout);
        // BARRA DE HERRAMIENTAS
        toolBar = new JToolBar();
        fc = new JFileChooser();
        ctrl.addObserver(this);
        //Boton de cargar
        botonCargar = new JButton();
        botonCargar.setToolTipText("Carga un fichero de ventos");
        if(Images.OPEN.imagen() == null){
            botonCargar.setText("Load Events");
        }
        else{
            botonCargar.setIcon(Images.OPEN.imagen());
        }
        botonCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                cargaFichero();
            }
        });
        toolBar.add(botonCargar,LEFT_ALIGNMENT);
        toolBar.addSeparator();
        //Boton de cambiar contaminacion
        botonChangeContamination = new JButton();
        botonChangeContamination.setToolTipText("Cambio de las condiciones atmosféricas de una carretera ");
        if(Images.CO2CLASS.imagen() == null){
            botonChangeContamination.setText("Change C02");
        }
        else{
            botonChangeContamination.setIcon(Images.CO2CLASS.imagen());
        }
        botonChangeContamination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ChangeCO2ClassDialog(ctrl);
            }
        });
        toolBar.add(botonChangeContamination);

        //Boton de cambiar el tiempo
        botonChangeWeather= new JButton();
        botonChangeWeather.setToolTipText("Cambio de el tiempo ");
        if(Images.WEATHER.imagen() == null){
            botonChangeWeather.setText("Change Weather");
        }
        else{
            botonChangeWeather.setIcon(Images.WEATHER.imagen());
        }
        botonChangeWeather.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ChangeRoadWeather(ctrl);
            }
        });
        toolBar.add(botonChangeWeather);

        toolBar.addSeparator();

        //Boton de Play
        botonPlay= new JButton();
        botonPlay.setToolTipText("Comienza la simulacion");
        if(Images.RUN.imagen() == null){
            botonPlay.setText("RUN");
        }
        else{
            botonPlay.setIcon(Images.RUN.imagen());
        }
        botonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                _thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        enableToolBar(false);
                        run_sim(ticks,delayTime);
                    }
                });
                _thread.start();
            }
        });
        toolBar.add(botonPlay);

        //Boton de Detener
        botonStop = new JButton();
        botonStop.setToolTipText("Detiene la Simulación");
        if(Images.STOP.imagen() == null){
            botonStop.setText("STOP");
        }
        else{
            botonStop.setIcon(Images.STOP.imagen());
        }
        botonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(_thread != null){
                    _thread.interrupt();
                }
            }
        });
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
                setTicks(value);
            }
        });
        toolBar.add(steps);
        toolBar.add(new JLabel(" Delay: "));
        this.delay = new JSpinner(new SpinnerNumberModel(5, 0, 1000, 1));
        this.delay.setToolTipText("pasos a ejecutar: 0-1000");
        this.delay.setMaximumSize(new Dimension(70, 70));
        this.delay.setMinimumSize(new Dimension(70, 70));
        this.delay.setValue(0);
        delay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = (int)delay.getValue();
                setDelay(value);
            }
        });
        toolBar.add(delay);
        toolBar.add(Box.createGlue());


        //Boton de Salir

        botonApagar = new JButton();
        botonApagar.setToolTipText("Salir de la Simulación");
        if(Images.EXIT.imagen() == null){
            botonApagar.setText("Exit");
        }
        else{
            botonApagar.setIcon(Images.EXIT.imagen());
        }
        botonApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        toolBar.add(botonApagar);
        this.add(toolBar,BorderLayout.PAGE_START);

    }

    protected void salir() {
        if(JOptionPane.showConfirmDialog(this, "¿Seguro que quiere salir?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)
                == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    public void cargaFichero() {
        int returnVal = this.fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fichero = this.fc.getSelectedFile();
            try {
                String s = leeFichero(fichero);
                this.controlador.reset();
                this.ficheroActual = fichero;
                controlador.setFicheroEntrada(this.ficheroActual);
                controlador.loadEvents(controlador.getFichero());
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (WrongValuesException e) {
                e.printStackTrace();
            } catch (WrongValuesContamination wrongValuesContamination) {
                wrongValuesContamination.printStackTrace();
            } catch (WrongValuesWeather wrongValuesWeather) {
                wrongValuesWeather.printStackTrace();
            } catch (Exception e) {
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

    private void run_sim(int n, long delay) {
        while (n > 0 && !_thread.isInterrupted()) {
            try {
                _ctrl.run(1);
                Thread.sleep(delay);

            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            //n--;
        }

        enableToolBar(true);
    }

    private void enableToolBar(boolean b) {
        if(b == true){
            botonApagar.setEnabled(true);
            botonCargar.setEnabled(true);
            botonChangeContamination.setEnabled(true);
            botonChangeWeather.setEnabled(true);
            botonPlay.setEnabled(true);
        }
        else{
            botonApagar.setEnabled(false);
            botonCargar.setEnabled(false);
            botonChangeContamination.setEnabled(false);
            botonChangeWeather.setEnabled(false);
            botonPlay.setEnabled(false);

        }
    }

    private void stop() {
        _thread.interrupt();
    }

    private void setTicks(int setticks){
        ticks = setticks;
    }

    private void setDelay(int newDelay){
        delayTime = newDelay;
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
