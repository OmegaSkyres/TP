package simulator.view;

import simulator.control.Controller;
import simulator.model.Road;
import simulator.model.Vehicle;
import simulator.model.Weather;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChangeRoadWeather extends JDialog{
    private List<Road> roads;//array del primer combo //Seguro que habra que cambiarlo
    private static String [] weather={"SUNNY","STORM","CLOUDY", "RAINY", "WINDY", "STORM"};//array del segundo combo //Seguro que habra que cambiarlo

    private static String message = "<html>Schedule an event to change the weather of a road after given number<P><html>number of simulation ticks from now<P>";
    private BorderLayout estilo;
    private JDialog box;
    private JLabel texto;
    private JPanel panel;
    private JPanel central;
    private JPanel inferior;
    private Weather newWeather = Weather.SUNNY;
    private String newRoad = "r1";
    private int newTicks = 1;

    public ChangeRoadWeather(Controller ctrl){
        box = new JDialog();
        panel = new JPanel();
        estilo = new BorderLayout();
        panel.setLayout(estilo);
        roads = ctrl.getRoads();
        central = construyePanelCentral(ctrl);
        inferior = construyePanelInferior(ctrl,box);
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

    private JPanel construyePanelInferior(Controller ctrl, JDialog main) {
        inferior = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                try {
                    if(roads.size() == 0){
                        JOptionPane.showMessageDialog(main,"No hay Carreteras cargados");
                        main.dispose();
                    }
                    else{
                        ctrl.newEventRoadWeather(newRoad,newWeather,newTicks);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JButton cancelar = new JButton("Cancel");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                main.dispose();
            }
        });
        inferior.add(ok);
        inferior.add(cancelar);
        return inferior;

    }

    private JPanel construyePanelCentral(Controller ctrl) {
        central = new JPanel();
        central.add(new JLabel(" Road: "));
        JComboBox r = new JComboBox();
        for(Object objeto : roads) {
            r.addItem(objeto.toString());
        }
        if(roads.size() == 0){

        }
        else{
            r.setSelectedIndex(0);
        }
        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newRoad = (String) r.getSelectedItem();
            }
        });
        JComboBox weath = new JComboBox(weather);
        weath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newWeather = Weather.valueOf((String) weath.getSelectedItem());

            }
        });
        JSpinner ticks = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
        ticks.setToolTipText("pasos a ejecutar: 1-1000");
        ticks.setMaximumSize(new Dimension(70, 70));
        ticks.setMinimumSize(new Dimension(70, 70));
        ticks.setValue(1);
        ticks.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                newTicks = (int) ticks.getValue();
            }
        });
        central.add(r);
        central.add(new JLabel(" Weather: "));
        central.add(weath);
        central.add(new JLabel(" Ticks: "));
        central.add(ticks);
        return central;
    }

}
