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

import javax.print.attribute.standard.JobImpressions;
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
    private JFileChooser fc;
    private int contadorTiempo;
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
    private boolean _stopped;
    private int ticks;

    public ControlPanel(Controller ctrl) {
        ticks = 1;
        _stopped = false;
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
        botonCargar.setIcon(Images.OPEN.imagen());
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
        botonChangeContamination.setIcon(Images.CO2CLASS.imagen());
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
        botonChangeWeather.setIcon(Images.WEATHER.imagen());
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
        botonPlay.setIcon(Images.RUN.imagen());
        botonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                _stopped = false; //Este valor a false es porque cuando se completa el tick se activa el toolbar y se pone el valor a true.
                enableToolBar(false);
                run_sim(ticks);
            }
        });
        toolBar.add(botonPlay);

        //Boton de Detener
        botonStop = new JButton();
        botonStop.setToolTipText("Detiene la Simulación");
        botonStop.setIcon(Images.STOP.imagen());
        botonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                _stopped = true;
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
        toolBar.add(Box.createGlue());


        //Boton de Salir

        botonApagar = new JButton();
        botonApagar.setToolTipText("Detiene la Simulación");
        botonApagar.setIcon(Images.STOP.imagen());
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
        contadorTiempo = 0;
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

    private void run_sim(int n) {
        if (n > 0 && !_stopped) {
            try {
                _ctrl.run(1);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(this, "No se ha podido ejecutar", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.WARNING_MESSAGE);
                _stopped = true;
                return;
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    run_sim(n - 1);
                }
            });
        } else {
            enableToolBar(true);
            _stopped = true;
        }
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
        _stopped = true;
    }

    private void setTicks(int setticks){
        ticks = setticks;
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
