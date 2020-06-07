package simulator.view;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver {
    private List<Road> lista;
    private Controller ctrl;
    private String[] _colNames = { "Id", "Lenght", "Weather", "Max.Speed", "Speed Limit", "Total CO2", "CO2 Limit" };

    public RoadsTableModel(Controller controller){
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
        switch (indiceCol) {
            case 0:
                s = lista.get(indiceFil).getId();
                break;
            case 1:
                s = Integer.toString(lista.get(indiceFil).getLength());
                break;
            case 2:
                s = lista.get(indiceFil).getCondicionAmbiental();
                break;
            case 3:
                s = Integer.toString(lista.get(indiceFil).getVelocidadMaxima());;
                break;
            case 4:
                s = Integer.toString(lista.get(indiceFil).getLimiteVelocidad());
                break;
            case 5:
                s = Integer.toString(lista.get(indiceFil).getContaminacionTotal());
                break;
            case 6:
                s = Integer.toString(lista.get(indiceFil).getAlarmaContaminacion());
                break;
            default: assert (false);
        }
        return s;
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
        this.lista = map.getCarreteras();
        this.fireTableStructureChanged();
    }

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
        this.lista = map.getCarreteras();
        this.fireTableStructureChanged();
    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
        this.lista = map.getCarreteras();
        this.fireTableStructureChanged();
    }

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
        this.lista = map.getCarreteras();
        this.fireTableStructureChanged();
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {
        this.lista = map.getCarreteras();
        this.fireTableStructureChanged();
    }

    @Override
    public void onError(String err) {
        this.fireTableStructureChanged();
    }
}
