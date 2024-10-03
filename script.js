// JavaScript for currency conversion
const exchangeRates = {
    "USD": { "EUR": 0.93, "GBP": 0.81, "AUD": 1.53, "CAD": 1.34, "INR": 83.15, "JPY": 149.56, "CHF": 0.91, "CNY": 7.30, "ZAR": 18.34, "HKD": 7.85, "SGD": 1.36, "NZD": 1.66 },
    "EUR": { "USD": 1.07, "GBP": 0.86, "AUD": 1.64, "CAD": 1.44, "INR": 89.50, "JPY": 160.40, "CHF": 0.98, "CNY": 7.85, "ZAR": 19.83, "HKD": 8.69, "SGD": 1.47, "NZD": 1.78 },
    "GBP": { "USD": 1.23, "EUR": 1.17, "AUD": 1.88, "CAD": 1.66, "INR": 104.27, "JPY": 186.54, "CHF": 1.14, "CNY": 9.08, "ZAR": 22.85, "HKD": 10.56, "SGD": 1.65, "NZD": 2.01 },
    "AUD": { "USD": 0.65, "EUR": 0.61, "GBP": 0.53, "CAD": 0.93, "INR": 57.70, "JPY": 98.20, "CHF": 0.58, "CNY": 4.80, "ZAR": 12.00, "HKD": 5.10, "SGD": 0.88, "NZD": 1.05 },
    "CAD": { "USD": 0.75, "EUR": 0.69, "GBP": 0.60, "AUD": 1.08, "INR": 66.50, "JPY": 110.50, "CHF": 0.68, "CNY": 5.30, "ZAR": 13.00, "HKD": 5.80, "SGD": 0.97, "NZD": 1.19 },
    "INR": { "USD": 0.012, "EUR": 0.011, "GBP": 0.0096, "AUD": 0.017, "CAD": 0.015, "JPY": 1.83, "CHF": 0.011, "CNY": 0.088, "ZAR": 0.21, "HKD": 0.018, "SGD": 0.015, "NZD": 0.020 },
    "JPY": { "USD": 0.0067, "EUR": 0.0062, "GBP": 0.0054, "AUD": 0.0082, "CAD": 0.0071, "INR": 0.54, "CHF": 0.0059, "CNY": 0.047, "ZAR": 0.10, "HKD": 0.042, "SGD": 0.007, "NZD": 0.0085 },
    "CHF": { "USD": 1.10, "EUR": 1.02, "GBP": 0.88, "AUD": 1.68, "CAD": 1.47, "INR": 92.70, "JPY": 168.40, "CNY": 7.95, "ZAR": 20.15, "HKD": 8.60, "SGD": 1.55, "NZD": 1.23 },
    "CNY": { "USD": 0.14, "EUR": 0.13, "GBP": 0.11, "AUD": 0.20, "CAD": 0.18, "INR": 11.30, "JPY": 21.14, "CHF": 0.12, "ZAR": 2.56, "HKD": 0.17, "SGD": 0.15, "NZD": 0.18 },
    "ZAR": { "USD": 0.055, "EUR": 0.051, "GBP": 0.044, "AUD": 0.083, "CAD": 0.077, "INR": 43.40, "JPY": 74.20, "CHF": 0.047, "CNY": 3.60, "HKD": 0.30, "SGD": 0.05, "NZD": 0.07 },
    "HKD": { "USD": 0.13, "EUR": 0.12, "GBP": 0.11, "AUD": 0.20, "CAD": 0.18, "INR": 12.10, "JPY": 21.70, "CHF": 0.11, "CNY": 0.60, "ZAR": 3.33, "SGD": 0.18, "NZD": 0.15 },
    "SGD": { "USD": 0.74, "EUR": 0.68, "GBP": 0.60, "AUD": 1.13, "CAD": 1.03, "INR": 62.70, "JPY": 108.80, "CHF": 0.65, "CNY": 5.00, "ZAR": 12.50, "HKD": 5.83, "NZD": 1.19 },
    "NZD": { "USD": 0.60, "EUR": 0.56, "GBP": 0.50, "AUD": 0.95, "CAD": 0.85, "INR": 54.50, "JPY": 95.80, "CHF": 0.60, "CNY": 4.40, "ZAR": 10.50, "HKD": 4.30, "SGD": 0.84 }
    // Add more currencies as needed
};

// Function to convert currencies
function convertCurrency() {
    const fromCurrency = document.getElementById("fromCurrency").value;
    const toCurrency = document.getElementById("toCurrency").value;
    const amount = parseFloat(document.getElementById("amount").value);
    let result = document.getElementById("result");

    // Check if the conversion is possible
    if (!exchangeRates[fromCurrency] || !exchangeRates[fromCurrency][toCurrency]) {
        result.innerText = "Conversion rate not available.";
        return;
    }

    // Perform the conversion
    const rate = exchangeRates[fromCurrency][toCurrency];
    const convertedAmount = (amount * rate).toFixed(2);
    result.innerText = `${amount} ${fromCurrency} = ${convertedAmount} ${toCurrency}`;
}

// Add event listeners to the button
document.getElementById("convertButton").addEventListener("click", convertCurrency);
