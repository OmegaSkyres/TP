package simulator.view;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver {
    private List<Vehicle> lista;
    private Controller ctrl;
    private String[] _colNames = { "Id", "Location", "Itenerary", "CO2 Class", "Max.Speed", "Speed", "Total CO2", "Distance" };

    public VehiclesTableModel(Controller controller){
        ctrl = controller;
        ctrl.addObserver(this);
    }
    @Override
    public String getColumnName(int column) {
        return _colNames[column];
    }

    @Override
    public int getRowCount() {
        return lista == null ? 0 : lista.size();
    }

    @Override
    public int getColumnCount() {
        return _colNames.length;
    }

    @Override
    public Object getValueAt(int indiceFil, int indiceCol) {
        Object s = null;
        if(indiceFil < getColumnCount() && indiceCol < getColumnCount()) {
            switch (indiceCol) {
                case 0:
                    s = lista.get(indiceFil).getId();
                    break;
                case 1:
                    s = this.lista.get(indiceFil).getCarretera() + ":" + this.lista.get(indiceFil).getKilometraje();
                    break;
                case 2:
                    s = this.lista.get(indiceFil).getItinerario();
                    break;
                case 3:
                    s = Integer.toString(lista.get(indiceFil).getGradoContaminacion());
                    break;
                case 4:
                    s = Integer.toString(lista.get(indiceFil).getVelocidadMaxima());
                    break;
                case 5:
                    s = Integer.toString(lista.get(indiceFil).getVelocidadActual());
                    break;
                case 6:
                    s = Integer.toString(lista.get(indiceFil).getContaminacionTotal());
                    break;
                case 7:
                    s = Integer.toString(lista.get(indiceFil).getKilometraje());
                    break;

                default:
                    assert (false);
            }
        }
        return s;
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
        this.lista = map.getVehiculos();
        this.fireTableDataChanged();
    }

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
        this.lista = map.getVehiculos();
        this.fireTableDataChanged();
    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
        this.lista = map.getVehiculos();
        this.fireTableDataChanged();
    }

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
        this.lista = map.getVehiculos();
        this.fireTableDataChanged();
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {
        this.lista = map.getVehiculos();
        this.fireTableDataChanged();
    }

    @Override
    public void onError(String err) {
        this.fireTableDataChanged();
    }
}
