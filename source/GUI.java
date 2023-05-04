package sorgente.source;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import static sorgente.source.Pizza.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sorgente.source.ingredient.*;

public class GUI extends JFrame {
    double pricePizza, priceAddition;
    JPanel northPanel = new JPanel(), centerPanel = new JPanel(), southPanel = new JPanel();
    JComboBox<Pizza> pizzaJComboBox = new JComboBox<>();
    JComboBox<ingredient> additionJComboBox = new JComboBox<>();
    JButton orderPizzaButton = new JButton("Order pizza");
    JLabel priceLabel = new JLabel("Total order: " + getFormatted(0));

    static String getFormatted(double price) {
        return "EUR " + new DecimalFormat("0.00").format(price);
    }

    GUI() {
        super("Order your pizza ");
        additionJComboBox.addItem(NOTHING);
        for (ingredient ingredient : ingredient.values())
            if (ingredient.price > 0)
                additionJComboBox.addItem(ingredient);
        pizzaJComboBox.addItem(new Pizza("--", List.of()));
        for (Pizza pizza : Pizze)
            pizzaJComboBox.addItem(pizza);
        northPanel.setLayout(new GridLayout(Pizze.size(), 3));
        createPizzaPanels().forEach(northPanel::add);
        northPanel.setBackground(Color.WHITE);
        northPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
        centerPanel.add(new JLabel("Choose your pizza : "));
        centerPanel.add(pizzaJComboBox);
        centerPanel.add(new JLabel("                                   "));
        centerPanel.add(new JLabel("Choose additional ingredient : "));
        centerPanel.add(additionJComboBox);
        southPanel.add(orderPizzaButton);
        southPanel.add(priceLabel);
        this.getContentPane().add(northPanel, BorderLayout.NORTH);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(southPanel, BorderLayout.SOUTH);
        addController();
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    List<JPanel> createPizzaPanels() {
        List<JPanel> pizzaPanels = new ArrayList<>();
        for (Pizza pizza : Pizze) {
            JPanel pizzaPanel = new JPanel();
            JLabel pizzaName = new JLabel("   " + pizza.getName(), SwingConstants.LEFT);
            pizzaName.setPreferredSize(new Dimension(150, 30));
            JLabel pizzaIngredients = new JLabel(pizza.getIngredients()
                    .stream()
                    .map(ingredient::toString)
                    .collect(Collectors.joining(", ")), SwingConstants.LEFT);
            pizzaIngredients.setPreferredSize(new Dimension(500, 30));
            JLabel pizzaPrice = new JLabel(getFormatted(pizza.getPrice()), SwingConstants.RIGHT);
            pizzaPrice.setPreferredSize(new Dimension(80, 30));
            pizzaPanel.add(pizzaName);
            pizzaPanel.add(pizzaIngredients);
            pizzaPanel.add(pizzaPrice);
            pizzaPanel.setBackground(Color.WHITE);
            pizzaPanels.add(pizzaPanel);
        }
        return pizzaPanels;
    }

    void addController() {
        pizzaJComboBox.addActionListener($ -> pricePizza = ((Pizza) pizzaJComboBox.getSelectedItem()).getPrice());
        additionJComboBox
                .addActionListener($ -> priceAddition = ((ingredient) additionJComboBox.getSelectedItem()).price);
        orderPizzaButton.addActionListener(
                $ -> priceLabel.setText("Total order: " + getFormatted(pricePizza + priceAddition)));
        orderPizzaButton.addActionListener($ -> {
            if (pizzaJComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "select a pizza! ");
            }
        });
    }
}
