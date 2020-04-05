package simulator.view;

import extra.jtable.EventsTableModel;
import simulator.control.Controller;
import simulator.model.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
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
        JPanel vehiculosView = createViewPanel(new JTable(new VehiclesTableModel(_ctrl)), "Vehicles");
        vehiculosView.setPreferredSize(new Dimension(500, 200));
        tablesPanel.add(vehiculosView);
        JPanel roadsView = createViewPanel(new JTable(new RoadsTableModel(_ctrl)), "Roads");
        roadsView.setPreferredSize(new Dimension(500, 200));
        tablesPanel.add(roadsView);
        JPanel juntionsView = createViewPanel(new JTable(new JunctionsTableModel(_ctrl)), "Juntions");
        juntionsView.setPreferredSize(new Dimension(500, 200));
        tablesPanel.add(juntionsView);

// maps
        JPanel mapView = createViewPanel(new MapComponent(_ctrl), "Map");
        mapView.setPreferredSize(new Dimension(500, 400));
        mapsPanel.add(mapView);
        JPanel mapViewRoads = createViewPanel(new MapByRoadComponent(_ctrl), "Map by Road");
        mapView.setPreferredSize(new Dimension(500, 400));
        mapsPanel.add(mapViewRoads);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    private JPanel createViewPanel(JComponent c, String title) {
        JPanel p = new JPanel( new BorderLayout() );
        p.setBorder(BorderFactory.createTitledBorder(MainWindow.bordePorDefecto, title, TitledBorder.LEFT, TitledBorder.TOP));
        p.add(new JScrollPane(c));
        return p;
    }




}
