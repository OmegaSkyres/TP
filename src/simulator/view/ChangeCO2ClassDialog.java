package simulator.view;


import simulator.control.Controller;
import simulator.model.Vehicle;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ChangeCO2ClassDialog extends JDialog {
    private List<Vehicle> vehicles; //array del primer combo //Seguro que habra que cambiarlo
    private static String [] CO2class={"0","1","2","3","4","5","6","7","8","9","10"};//array del segundo combo //Seguro que habra que cambiarlo

    private static String message = "<html>Schedule an event to change the CO2 class of a vehicle after given<P><html>number of simulation ticks from now<P>";
    private BorderLayout estilo;
    private JDialog box;
    private JLabel texto;
    private JPanel panel;
    private JPanel central;
    private JPanel inferior;
    private Controller controller;
    private int newCO2 = 0;
    private String newVehicle = "v1";
    private int newTicks = 1;

    public ChangeCO2ClassDialog(Controller ctrl){
        controller = ctrl;
        vehicles = ctrl.getVehiculos();
        box = new JDialog();
        panel = new JPanel();
        estilo = new BorderLayout();
        panel.setLayout(estilo);
        central = construyePanelCentral(ctrl);
        inferior = construyePanelInferior(ctrl,box);
        estilo = new BorderLayout();
        panel.setLayout(estilo);
        texto = new JLabel(message);
        box.setTitle("Chance CO2 Class");
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

    private JPanel construyePanelInferior(Controller ctrl ,JDialog main) {
        inferior = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                try {
                    if(vehicles.size() == 0){
                        JOptionPane.showMessageDialog(main,"No hay vehiculos cargados");
                        main.dispose();
                    }
                    else{
                        ctrl.newEventC02(newVehicle,newCO2,newTicks);
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
        central.add(new JLabel(" Vehicle: "));
        JComboBox vehicle = new JComboBox();
        for(Object objeto : vehicles) {
            vehicle.addItem(objeto.toString());
        }
        if(vehicles.size() == 0){

        }
        else{
            vehicle.setSelectedIndex(0);
        }

        vehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newVehicle = (String) vehicle.getSelectedItem();
            }
        });
        JComboBox cO2Class = new JComboBox(CO2class);
        cO2Class.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newCO2 = Integer.parseInt((String) cO2Class.getSelectedItem());
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
        central.add(vehicle);
        central.add(new JLabel(" CO2 Class: "));
        central.add(cO2Class);
        central.add(new JLabel(" Ticks: "));
        central.add(ticks);
        return central;
    }
}
