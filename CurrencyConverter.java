import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.HashMap;

public class CurrencyConverter extends JFrame {

    private JComboBox<String> fromCurrencyDropdown;
    private JComboBox<String> toCurrencyDropdown;
    private JTextField amountField;
    private JLabel resultLabel;
    private ArrayList<Currency> currencies;
    private BufferedImage backgroundImage;

    // Inner class to represent currencies
    private static class Currency {
        private String name;
        private String shortName;
        private HashMap<String, Double> exchangeValues = new HashMap<>();

        // Constructor
        public Currency(String name, String shortName) {
            this.name = name;
            this.shortName = shortName;
        }

        // Getters
        public String getName() {
            return name;
        }

        public String getShortName() {
            return shortName;
        }

        public HashMap<String, Double> getExchangeValues() {
            return exchangeValues;
        }

        // Set default exchange rates
        public void setDefaultExchangeRates() {
            switch (this.shortName) {
                case "USD":
                    exchangeValues.put("USD", 1.00);
                    exchangeValues.put("EUR", 0.93);
                    exchangeValues.put("GBP", 0.81);
                    exchangeValues.put("INR", 83.15);
                    exchangeValues.put("CAD", 1.34);
                    exchangeValues.put("AUD", 1.53);
                    exchangeValues.put("JPY", 149.56);
                    exchangeValues.put("CNY", 7.30);
                    break;
                case "EUR":
                    exchangeValues.put("USD", 1.07);
                    exchangeValues.put("EUR", 1.00);
                    exchangeValues.put("GBP", 0.86);
                    exchangeValues.put("INR", 89.50);
                    exchangeValues.put("CAD", 1.44);
                    exchangeValues.put("AUD", 1.64);
                    exchangeValues.put("JPY", 160.40);
                    exchangeValues.put("CNY", 7.85);
                    break;
                case "GBP":
                    exchangeValues.put("USD", 1.23);
                    exchangeValues.put("EUR", 1.17);
                    exchangeValues.put("GBP", 1.00);
                    exchangeValues.put("INR", 104.27);
                    exchangeValues.put("CAD", 1.66);
                    exchangeValues.put("AUD", 1.88);
                    exchangeValues.put("JPY", 186.54);
                    exchangeValues.put("CNY", 9.08);
                    break;
                case "INR":
                    exchangeValues.put("USD", 0.012);
                    exchangeValues.put("EUR", 0.011);
                    exchangeValues.put("GBP", 0.0096);
                    exchangeValues.put("INR", 1.00);
                    exchangeValues.put("CAD", 0.015);
                    exchangeValues.put("AUD", 0.017);
                    exchangeValues.put("JPY", 1.83);
                    exchangeValues.put("CNY", 0.088);
                    break;
                case "CAD":
                    exchangeValues.put("USD", 0.75);
                    exchangeValues.put("EUR", 0.69);
                    exchangeValues.put("GBP", 0.60);
                    exchangeValues.put("INR", 66.50);
                    exchangeValues.put("CAD", 1.00);
                    exchangeValues.put("AUD", 1.08);
                    exchangeValues.put("JPY", 110.50);
                    exchangeValues.put("CNY", 5.30);
                    break;
                case "AUD":
                    exchangeValues.put("USD", 0.65);
                    exchangeValues.put("EUR", 0.61);
                    exchangeValues.put("GBP", 0.53);
                    exchangeValues.put("INR", 57.70);
                    exchangeValues.put("CAD", 0.93);
                    exchangeValues.put("AUD", 1.00);
                    exchangeValues.put("JPY", 98.20);
                    exchangeValues.put("CNY", 4.80);
                    break;
                case "JPY":
                    exchangeValues.put("USD", 0.0067);
                    exchangeValues.put("EUR", 0.0062);
                    exchangeValues.put("GBP", 0.0054);
                    exchangeValues.put("INR", 0.54);
                    exchangeValues.put("CAD", 0.0071);
                    exchangeValues.put("AUD", 0.0082);
                    exchangeValues.put("JPY", 1.00);
                    exchangeValues.put("CNY",  0.047);
                    break;
                // Add more currencies with respective rates
                // Add currencies such as SGD, ZAR, HKD, KRW, etc.
            }
        }

        // Find currency by code
        public static Currency findCurrencyByCode(ArrayList<Currency> currencies, String shortName) {
            for (Currency currency : currencies) {
                if (currency.getShortName().equalsIgnoreCase(shortName)) {
                    return currency;
                }
            }
            return null;
        }

        // Conversion logic
        public static double convertAmount(double amount, double exchangeRate) {
            return Math.round(amount * exchangeRate * 100.0) / 100.0;
        }

        // Initialize the list of currencies
        public static ArrayList<Currency> initializeCurrencies() {
            ArrayList<Currency> currencies = new ArrayList<>();
            currencies.add(new Currency("US Dollar", "USD"));
            currencies.add(new Currency("Euro", "EUR"));
            currencies.add(new Currency("British Pound", "GBP"));
            currencies.add(new Currency("Indian Rupee", "INR"));
            currencies.add(new Currency("Canadian Dollar", "CAD"));
            currencies.add(new Currency("Australian Dollar", "AUD"));
            currencies.add(new Currency("Japanese Yen", "JPY"));
            // Add more currencies such as SGD, ZAR, HKD, KRW, etc.
            // Add a total of 20 currencies...
            for (Currency currency : currencies) {
                currency.setDefaultExchangeRates();
            }
            return currencies;
        }
    }

    public CurrencyConverter() {
        // Initialize currencies
        currencies = Currency.initializeCurrencies();

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("background.jpg"));
        } catch (IOException e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }

        // Set up the JFrame with an enhanced color scheme
        setTitle("Currency Converter");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel with background styling
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        panel.setBackground(new Color(45, 52, 54)); // Dark background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Set font and foreground colors
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font resultFont = new Font("Arial", Font.BOLD, 20);
        Color labelColor = Color.WHITE;
        Color fieldColor = Color.LIGHT_GRAY;
        Color resultColor = new Color(39, 174, 96); // Green for results

        // Amount input label and field
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(labelFont);
        amountLabel.setForeground(labelColor);
        panel.add(amountLabel, gbc);
        gbc.gridx = 1;
        amountField = new JTextField(12);
        amountField.setBackground(fieldColor);
        panel.add(amountField, gbc);

        // From currency dropdown
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setFont(labelFont);
        fromLabel.setForeground(labelColor);
        panel.add(fromLabel, gbc);
        gbc.gridx = 1;
        fromCurrencyDropdown = new JComboBox<>();
        populateCurrencyDropdown(fromCurrencyDropdown);
        fromCurrencyDropdown.setBackground(fieldColor);
        panel.add(fromCurrencyDropdown, gbc);

        // To currency dropdown
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setFont(labelFont);
        toLabel.setForeground(labelColor);
        panel.add(toLabel, gbc);
        gbc.gridx = 1;
        toCurrencyDropdown = new JComboBox<>();
        populateCurrencyDropdown(toCurrencyDropdown);
        toCurrencyDropdown.setBackground(fieldColor);
        panel.add(toCurrencyDropdown, gbc);

        // Convert button
        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(0, 123, 255)); // Blue button
        convertButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(convertButton, gbc);

        // Result label
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        resultLabel = new JLabel("Converted amount will appear here.");
        resultLabel.setFont(resultFont);
        resultLabel.setForeground(resultColor);
        panel.add(resultLabel, gbc);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        // Add the panel to the JFrame
        add(panel);
    }

    // Populate the currency dropdowns
    private void populateCurrencyDropdown(JComboBox<String> dropdown) {
        for (Currency currency : currencies) {
            dropdown.addItem(currency.getShortName());
        }
    }

    // Perform the currency conversion
    private void convertCurrency() {
        String fromCurrencyCode = (String) fromCurrencyDropdown.getSelectedItem();
        String toCurrencyCode = (String) toCurrencyDropdown.getSelectedItem();
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number for the amount.");
            return;
        }

        Currency fromCurrency = Currency.findCurrencyByCode(currencies, fromCurrencyCode);
        Currency toCurrency = Currency.findCurrencyByCode(currencies, toCurrencyCode);

        if (fromCurrency == null || toCurrency == null) {
            resultLabel.setText("Invalid currency code.");
        } else {
            Double exchangeRate = fromCurrency.getExchangeValues().get(toCurrencyCode);
            if (exchangeRate != null) {
                double convertedAmount = Currency.convertAmount(amount, exchangeRate);
                resultLabel.setText("Converted amount: " + convertedAmount + " " + toCurrencyCode);
            } else {
                resultLabel.setText("No exchange rate found between the selected currencies.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter().setVisible(true);
            }
        });
    }
}