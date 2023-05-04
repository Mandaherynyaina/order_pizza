package sorgente.source;

public enum ingredient {

    NOTHING(0.0),
    POMODORO(0.0),
    WURSTEL(0.5),
    CIPOLLA(0.2),
    FORMAGGIO(0.5),
    MOZZARELLA(0.5),
    BASILICO(0.2),
    FUNGHI(0.2),
    ALICE(0.5),
    COTTO(1.0),
    OLIVA(0.5),
    CARCIOFINI(0.2);

    double price;

    ingredient(double price) {
        this.price = price;
    }

}
