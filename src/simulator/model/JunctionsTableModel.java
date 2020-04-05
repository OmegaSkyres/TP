package simulator.model;

import simulator.control.Controller;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver {
    private List<Junction> lista;
    private Controller ctrl;
    private String[] _colNames = { "Id", "Green", "Queues" };

    public JunctionsTableModel(Controller controller) {
        ctrl = controller;
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
    public Object getValueAt(int indiceFila, int indiceColum) {
        Object o = null;
        switch (indiceColum){
            case 0:
                o = this.lista.get(indiceFila).getId();
                break;
            case 1:
                o = "[" + this.lista.get(indiceFila).muestraSemaforoVerde() + "]";
                break;
            case 2:
                o = "[" + this.lista.get(indiceFila).muestraSemaforoRojo() + "]";
                break;
            default:
                assert (false);
        }
        return o;
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
        this.lista = map.getJunctions();
        this.fireTableStructureChanged();
    }

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
        this.lista = map.getJunctions();
        this.fireTableStructureChanged();
    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
        this.lista = map.getJunctions();
        this.fireTableStructureChanged();
    }

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
        this.lista = map.getJunctions();
        this.fireTableStructureChanged();
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {
        this.lista = map.getJunctions();
        this.fireTableStructureChanged();
    }

    @Override
    public void onError(String err) {
        this.fireTableStructureChanged();
    }
}