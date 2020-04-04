package simulator.view;

import simulator.control.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeRoadWeather extends JDialog{
    private static String [] road={"r1","r2","r3","r4"};//array del primer combo //Seguro que habra que cambiarlo
    private static String [] weather={"SUNNY","STORM","CLOUDY", "RAINY", "WINDY", "STORM"};//array del segundo combo //Seguro que habra que cambiarlo

    private static String message = "<html>Schedule an event to change the weather of a road after given number<P><html>number of simulation ticks from now<P>";
    private BorderLayout estilo;
    private JDialog box;
    private JLabel texto;
    private JPanel panel;
    private JPanel central;
    private JPanel inferior;

    public ChangeRoadWeather(Controller ctrl){
        box = new JDialog();
        panel = new JPanel();
        estilo = new BorderLayout();
        panel.setLayout(estilo);
        central = construyePanelCentral(ctrl);
        inferior = construyePanelInferior();
        estilo = new BorderLayout();
        panel.setLayout(estilo);
        texto = new JLabel(message);
        box.setTitle("Chance Road Weather");
        //tamaño de la ventana
        box.setSize(600,200);
        //pone la ventana en el Centro de la pantalla
        box.setLocationRelativeTo(null);
        panel.add(texto,BorderLayout.NORTH);
        panel.add(central,BorderLayout.CENTER);
        panel.add(inferior,BorderLayout.SOUTH);
        box.add(panel);
        box.setVisible(true);
    }

    private JPanel construyePanelInferior() {
        inferior = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                //cargaFichero();
            }
        });
        JButton cancelar = new JButton("Cancel");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                //cargaFichero();
            }
        });
        inferior.add(ok);
        inferior.add(cancelar);
        return inferior;

    }

    private JPanel construyePanelCentral(Controller ctrl) {
        central = new JPanel();
        central.add(new JLabel(" Road: "));
        JComboBox vehicle = new JComboBox(road);
        vehicle.setSelectedIndex(0);
        JComboBox cO2Class = new JComboBox(weather);
        JSpinner ticks = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
        ticks.setToolTipText("pasos a ejecutar: 1-1000");
        ticks.setMaximumSize(new Dimension(70, 70));
        ticks.setMinimumSize(new Dimension(70, 70));
        ticks.setValue(1);
        ticks.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //int value = (int) ctrl..getValue();
                //mainWindow.setSteps(value);
            }
        });
        central.add(vehicle);
        central.add(new JLabel(" CO2 Class: "));
        central.add(cO2Class);
        central.add(new JLabel(" Ticks: "));
        central.add(ticks);
        return central;
    }

}
