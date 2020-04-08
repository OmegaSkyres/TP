package simulator.view;

import Utils.Utils;
import simulator.control.Controller;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeCO2ClassDialog extends JDialog {
    private static String [] vehiculos={"v1","v2","v3","v4"};//array del primer combo //Seguro que habra que cambiarlo
    private static String [] CO2class={"0","1","2","3"};//array del segundo combo //Seguro que habra que cambiarlo

    private static String message = "<html>Schedule an event to change the CO2 class of a vehicle after given<P><html>number of simulation ticks from now<P>";
    private BorderLayout estilo;
    private JDialog box;
    private JLabel texto;
    private JPanel panel;
    private JPanel central;
    private JPanel inferior;
    private Controller controller;

    public ChangeCO2ClassDialog(Controller ctrl){
        controller = ctrl;
        box = new JDialog();
        panel = new JPanel();
        estilo = new BorderLayout();
        panel.setLayout(estilo);
        central = construyePanelCentral(ctrl);
        inferior = construyePanelInferior(box);
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

    private JPanel construyePanelInferior(JDialog main) {
        inferior = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                ;
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
        JComboBox vehicle = new JComboBox(vehiculos);
        vehicle.setSelectedIndex(0);
        JComboBox cO2Class = new JComboBox(CO2class);
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
