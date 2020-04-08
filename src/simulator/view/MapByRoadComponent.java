package simulator.view;

import simulator.control.Controller;
import simulator.model.*;
import simulator.model.Event;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Image;


import javax.imageio.ImageIO;

public class MapByRoadComponent extends JComponent implements TrafficSimObserver {

    private static final long serialVersionUID = 1L;

    private static final int _JRADIUS = 10;

    private static final Color _BG_COLOR = Color.WHITE;
    private static final Color _JUNCTION_COLOR = Color.BLUE;
    private static final Color _JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
    private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
    private static final Color _RED_LIGHT_COLOR = Color.RED;
    private static final Color _RoadColor = Color.black;

    private RoadMap _map;

    private Image _car;
    private Image _cloud;
    private Image _sunny;
    private Image _wind;
    private Image _storm;
    private Image _rainy;
    private Image cont_0;
    private Image cont_1;
    private Image cont_2;
    private Image cont_3;
    private Image cont_4;
    private Image cont_5;



    public MapByRoadComponent(Controller ctrl){
        this.setPreferredSize(new Dimension(300, 200));
        initGUI();
        ctrl.addObserver(this);
    }

    private void initGUI() {
       _car = loadImage("car.png");
       _cloud = loadImage("cloud.png");
       _rainy = loadImage("rain.png");
       _storm = loadImage("storm.png");
       _sunny = loadImage("sun.png");
       _wind = loadImage("wind.png");
       cont_0 = loadImage("cont_0.png");
       cont_1 = loadImage("cont_1.png");
       cont_2 = loadImage("cont_2.png");
       cont_3 = loadImage("cont_3.png");
       cont_4 = loadImage("cont_4.png");
       cont_5 = loadImage("cont_5.png");



    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // clear with a background color
        g.setColor(_BG_COLOR);
        g.clearRect(0, 0, getWidth(), getHeight());

        if (_map == null || _map.getJunctions().size() == 0) {
            g.setColor(Color.red);
            g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
        } else {
            updatePrefferedSize();
            drawMap(g);
        }
    }

    private void drawMap(Graphics g) {
        drawRoads(g);
    }

    private void drawRoads(Graphics g) {
        for (int i = 0; i < _map.getCarreteras().size(); i++) {

            int x1 = 50;
            int x2 = getWidth() - 100;
            int y = (i+1) * 50;
            int A = 0;
            int B;
            int C;

            // choose a color for the arrow depending on the traffic light of the road
            Color arrowColor = _RED_LIGHT_COLOR;
            int idx = _map.getCarreteras().get(i).getCruceDestino().getIndiceSemaforo();
            if (idx != -1 && _map.getCarreteras().get(i).equals(_map.getCarreteras().get(i).getCruceDestino().getListaCarreterasEntrantes().get(idx))) {
                arrowColor = _GREEN_LIGHT_COLOR;
            }
            Junction origen = _map.getCarreteras().get(i).getCruceOrigen();
            Junction destino = _map.getCarreteras().get(i).getCruceDestino();
            g.setColor(_RoadColor);
            Road r = _map.getCarreteras().get(i);

            g.drawString(r.getId(),x1-30,y);
            drawLine(g,x1,y,x2,y,15, 5,  _RoadColor);
            // Dibuja los cruces
            drawJunctions(g,x1,y,_JUNCTION_COLOR,origen);
            drawJunctions(g,x2,y,arrowColor,destino);
            for (Vehicle v : _map.getVehiculos()) {
                if(v.getCarretera() == r) {
                    drawVehicles(g,v,x1,x2,y);
                }
                A = v.getContaminacionTotal();
            }
            //------------------------------Dibujo Weather---------------------------------//
            Image weather = drawWeather(r.getCondicionAmbiental());
            g.drawImage(weather,x2+10,y-20,32,32,this);

            //------------------------------Dibujo Contaminacion---------------------------//
            B = r.getAlarmaContaminacion();
            C =  (int) Math.floor(Math.min((double) A/(1.0 + (double) B),1.0) / 0.19);
            Image contClass = drawContClass(C);
            g.drawImage(contClass,x2+50,y-20,32,32,this);
        }
    }

    private Image drawWeather(Weather condicionAmbiental) {
        Image i;
        switch (condicionAmbiental) {
            case SUNNY:
                i = _sunny;
                break;
            case STORM:
                i = _storm;
                break;
            case CLOUDY:
                i = _cloud;
                break;
            case RAINY:
                i = _rainy;
                break;
            case WINDY:
                i = _wind;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + condicionAmbiental);
        }
        return i;
    }

    private Image drawContClass(int c) {
        Image i;
        switch (c) {
            case 0:
                i = cont_0;
                break;
            case 1:
                i = cont_1;
                break;
            case 2:
                i = cont_2;
                break;
            case 3:
                i = cont_3;
                break;
            case 4:
                i = cont_4;
                break;
            case 5:
                i = cont_5;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
        return i;
    }

    private void drawLine(Graphics g, int x1, int y, int x2, int y1, int w, int h, Color roadColor) {
        int dx = x2 - x1, dy = y;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - w, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = { x2, (int) xm, (int) xn };
        int[] ypoints = { y, (int) ym, (int) yn };

        g.setColor(roadColor);
        g.drawLine(x1, y1, x2, y);
    }

    private void drawVehicles(Graphics g, Vehicle v, int x1, int x2,int y) {
        if (v.getEstado() != VehicleStatus.ARRIVED) {

            // The calculation below compute the coordinate (vX,vY) of the vehicle on the
            // corresponding road. It is calculated relativly to the length of the road, and
            // the location on the vehicle.
            Road r = v.getCarretera();

            int A = v.getLocalizacion();
            int B = r.getLongitud();
            int vX =  x1 + (int) ((x2 - x1) * ((double) A / (double) B));

            // Choose a color for the vehcile's label and background, depending on its
            // contamination class
            int vLabelColor = (int) (25.0 * (10.0 - (double) v.getGradoContaminacion()));
            g.setColor(new Color(0, vLabelColor, 0));

            // draw an image of a car (with circle as background) and it identifier
            g.drawImage(_car, vX, y-10, 16, 16, this);
            g.drawString(v.getId(), vX, y - 6);
        }
    }

    private void drawJunctions(Graphics g, int x, int y, Color color, Junction j) {

            // (x,y) are the coordinates of the junction

            // draw a circle with center at (x,y) with radius _JRADIUS
            g.setColor(color);
            g.fillOval(x - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);

            // draw the junction's identifier at (x,y)
            g.setColor(_JUNCTION_LABEL_COLOR);
            g.drawString(j.getId(), x, y);
    }

    // this method is used to update the preffered and actual size of the component,
    // so when we draw outside the visible area the scrollbars show up
    private void updatePrefferedSize() {
        int maxW = 200;
        int maxH = 200;
        for (Junction j : _map.getJunctions()) {
            maxW = Math.max(maxW, j.getCoordenadaX());
            maxH = Math.max(maxH, j.getCoordenadaY());
        }
        maxW += 20;
        maxH += 20;
        if (maxW > getWidth() || maxH > getHeight()) {
            setPreferredSize(new Dimension(maxW, maxH));
            setSize(new Dimension(maxW, maxH));
        }
        else{
            setPreferredSize(new Dimension(maxW, maxH));
            setSize(new Dimension(maxW, maxH));
        }

    }

    private Image loadImage(String img) {
        Image i = null;
        try {
            return ImageIO.read(new File("resources/icons/" + img));
        } catch (IOException e) {
        }
        return i;
    }

    private void update(RoadMap map) {
        _map = map;
        repaint();
    }

    @Override
    public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
        update(map);
    }

    @Override
    public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
        update(map);
    }

    @Override
    public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
        update(map);
    }

    @Override
    public void onReset(RoadMap map, List<Event> events, int time) {
        update(map);
    }

    @Override
    public void onRegister(RoadMap map, List<Event> events, int time) {
        update(map);
    }

    @Override
    public void onError(String err) {

    }
}
