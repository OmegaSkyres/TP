package simulator.view;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StatusBar extends JPanel implements TrafficSimObserver {
    private JLabel infoTime;
    private JLabel infoEjecucion;

    public StatusBar(Controller ctrl) {
        JPanel panel = new JPanel();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.infoTime = new JLabel("Time: " + ctrl.getSteps());
        this.add(infoTime);
        this.add(Box.createGlue());
        this.add(new JSeparator());
        this.infoEjecucion = new JLabel("HOLA"); //TODO LUEGO HABRA QUE CAMBIARLO
        this.add(this.infoEjecucion);
        this.setBorder(BorderFactory.createBevelBorder(1));
        ctrl.addObserver(this);

    }

    public void setMensaje(String mensaje) {
        infoEjecucion.setText(mensaje);
    } // la ventana principal se comunica con el panel



    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
        setMensaje("Avanza");
    }

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {

    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
        setMensaje("Evento añadido con Exito");
    }

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
       setMensaje("El simulador se ha reiniciado");
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {

    }

    @Override
    public void onError(String err) {
        setMensaje(err);
    }
}
