package simulator.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public enum Images {
    CAR("Car") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/car.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    CLOUDY("Cloudy") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/cloud.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    RAINY("Rainy") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/rain.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    STORM("Storm") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/storm.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    SUNNY("Sunny") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/sun.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },


    WINDY("Windy") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/wind.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    CONT_0("Cont_0") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/cont_0.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    CONT_1("Cont_1") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/cont_1.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    CONT_2("Cont_2") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/cont_2.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    CONT_3("Cont_3") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/cont_3.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    CONT_4("Cont_4") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/cont_4.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },


    CONT_5("Cont_5") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/cont_5.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },


    CAR_FRONT("Car_front") {
        @Override
        public ImageIcon imagen() {
            return null;
        }

        @Override
        public Image ima() {
            Image ico = null;
            URL u  = null;
            try {
                u = Images.class.getResource("iconos/car_front.png");
                if(u != null) return ImageIO.read(u);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    },

    OPEN("Open") {
        @Override
        public ImageIcon imagen() {
            ImageIcon ico = null;
            java.net.URL url = null;
            url = Images.class.getResource("iconos/open.png");
            if (url != null) {
                ico = new ImageIcon(url);
            }
            return ico;
        }

        @Override
        public Image ima() {
            return null;
        }
    },

    CO2CLASS("Co2class") {
        @Override
        public ImageIcon imagen() {
            ImageIcon ico = null;
            java.net.URL url = null;
            url = Images.class.getResource("iconos/co2class.png");
            if (url != null) {
                ico = new ImageIcon(url);
            }
            return ico;
        }

        @Override
        public Image ima() {
            return null;
        }
    },

    WEATHER("Weather") {
        @Override
        public ImageIcon imagen() {
            ImageIcon ico = null;
            java.net.URL url = null;
            url = Images.class.getResource("iconos/weather.png");
            if (url != null) {
                ico = new ImageIcon(url);
            }
            return ico;
        }

        @Override
        public Image ima() {
            return null;
        }
    },

    RUN("Run") {
        @Override
        public ImageIcon imagen() {
            ImageIcon ico = null;
            java.net.URL url = null;
            url = Images.class.getResource("iconos/run.png");
            if (url != null) {
                ico = new ImageIcon(url);
            }
            return ico;
        }

        @Override
        public Image ima() {
            return null;
        }
    },

    STOP("Stop") {
        @Override
        public ImageIcon imagen() {
            ImageIcon ico = null;
            java.net.URL url = null;
            url = Images.class.getResource("iconos/stop.png");
            if (url != null) {
                ico = new ImageIcon(url);
            }
            return ico;
        }

        @Override
        public Image ima() {
            return null;
        }
    },

    EXIT("Exit") {
        @Override
        public ImageIcon imagen() {
            ImageIcon ico = null;
            java.net.URL url = null;
            url = Images.class.getResource("iconos/exit.png");
            if (url != null) {
                ico = new ImageIcon(url);
            }
            return ico;
        }

        @Override
        public Image ima() {
            return null;
        }
    };





    private String name;

        private Images(String desc) {
            name = desc;
        };

        public abstract ImageIcon imagen();

        public abstract Image ima();



}
