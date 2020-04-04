package simulator.view;

import extra.jtable.EventsTableModel;
import simulator.control.Controller;
import simulator.model.Junction;
import simulator.model.Road;
import simulator.model.Vehicle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainWindow extends JFrame {
    private Controller _ctrl;
    private boolean _stopped;
    public static Border bordePorDefecto = BorderFactory.createLineBorder(Color.black, 2);

    //private PanelTabla<Vehicle> panelVehiculos;
    //private PanelTabla<Road> panelCarreteras;
    //private PanelTabla<Junction> panelCruces;


    public MainWindow(Controller ctrl) {
        super("Traffic Simulator");
        _ctrl = ctrl;
        _stopped = false;
        initGUI();
    }
    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);
        mainPanel.add(new ControlPanel(_ctrl), BorderLayout.PAGE_START);
        mainPanel.add(new StatusBar(_ctrl),BorderLayout.PAGE_END);
        JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(viewsPanel, BorderLayout.CENTER);
        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
        viewsPanel.add(tablesPanel);
        JPanel mapsPanel = new JPanel();
        mapsPanel.setLayout(new BoxLayout(mapsPanel, BoxLayout.Y_AXIS));
        viewsPanel.add(mapsPanel);
// tables
        JPanel eventsView = createViewPanel(new JTable(new EventsTableModel(_ctrl)), "Events");
        eventsView.setPreferredSize(new Dimension(500, 200));
        tablesPanel.add(eventsView);
        tablesPanel.setLayout(new BoxLayout(tablesPanel,BoxLayout.Y_AXIS));
        /*
        this.panelVehiculos = new PanelTabla<Vehiculo>("Vehiculos", new ModeloTablaVehiculos(VentanaPrincipal.columnIdVehiculo, this.controlador));
        paneltablas.add(this.panelVehiculos);
        this.panelCarreteras = new PanelTabla<Carretera>("Carretras", new ModeloTablaCarreteras(VentanaPrincipal.columnIdCarretera, this.controlador));
        paneltablas.add(this.panelCarreteras);
        this.panelCruces = new PanelTabla<CruceGenerico<?>>("Cruces", new ModeloTablaCruces(VentanaPrincipal.columnIdCruce,this.controlador));
        paneltablas.add(panelCruces);
        */
// TODO add other tables
// ...
// maps
        JPanel mapView = createViewPanel(new MapComponent(_ctrl), "Map");
        mapView.setPreferredSize(new Dimension(500, 400));
        mapsPanel.add(mapView);
// TODO add a map for MapByRoadComponent
// ...
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    private JPanel createViewPanel(JComponent c, String title) {
        JPanel p = new JPanel( new BorderLayout() );
// TODO add a framed border to p with title
        p.add(new JScrollPane(c));
        return p;
    }

    private void run_sim(int n) {
        if (n > 0 && !_stopped) {
            try {
               // _ctrl.run(1);
            } catch (Exception e) {
// TODO show error message
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

    }

    private void stop() {
        _stopped = true;
    }


}
