package sorgente.source;

import java.util.List;
import static sorgente.source.ingredient.*;

public class Pizza {

    private String name;
    private List<ingredient> ingredients;

    public Pizza(String name, List<ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<ingredient> getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return 4.0 + ingredients.stream().mapToDouble(ingredients -> ingredients.price).sum();
    }
    @Override
    public String toString() {
        return name;
    }

    static List<Pizza> Pizze = List.of(
            new Pizza("Margherita", List.of(POMODORO, MOZZARELLA, BASILICO)),
            new Pizza("Napoli", List.of(POMODORO, MOZZARELLA, ALICE)),
            new Pizza("Capricciosa", List.of(POMODORO, MOZZARELLA, COTTO, FUNGHI, OLIVA, CARCIOFINI)),
            new Pizza("Favignana", List.of(POMODORO, MOZZARELLA, CIPOLLA)),
            new Pizza("Quattro Formaggio", List.of(MOZZARELLA, FORMAGGIO)),
            new Pizza("Wurstel", List.of(POMODORO, MOZZARELLA, WURSTEL)));

}