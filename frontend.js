document.addEventListener('DOMContentLoaded', () => {
    const fromCurrency = document.getElementById('from-currency');
    const toCurrency = document.getElementById('to-currency');
    const amountInput = document.getElementById('amount');
    const convertBtn = document.getElementById('convert-btn');
    const result = document.getElementById('result');
    const goldPriceElement = document.getElementById('gold-price');

    const currencies = ['USD', 'EUR', 'GBP', 'JPY', 'AUD']; // Add more currencies as needed

    const API_KEY = 'YOUR_EXCHANGE_RATE_API_KEY'; // Replace with your ExchangeRate-API key
    const GOLD_API_KEY = 'YOUR_GOLD_API_KEY'; // Replace with your GoldAPI key

    // Populate currency select options
    currencies.forEach(currency => {
        const option1 = document.createElement('option');
        option1.value = currency;
        option1.textContent = currency;
        fromCurrency.appendChild(option1);

        const option2 = document.createElement('option');
        option2.value = currency;
        option2.textContent = currency;
        toCurrency.appendChild(option2);
    });

    async function fetchRates() {
        const baseCurrency = fromCurrency.value;
        const targetCurrency = toCurrency.value;
        const amount = amountInput.value;

        try {
            const response = await fetch(`https://api.exchangerate-api.com/v4/latest/${baseCurrency}`);
            const data = await response.json();
            const rate = data.rates[targetCurrency];
            const convertedAmount = (amount * rate).toFixed(2);

            result.textContent = `${amount} ${baseCurrency} = ${convertedAmount} ${targetCurrency}`;
        } catch (error) {
            result.textContent = 'Error fetching data';
        }
    }

    async function fetchGoldPrice() {
        try {
            const response = await fetch(`https://www.metals-api.com/api/latest?base=USD&symbols=XAU&access_key=${GOLD_API_KEY}`);
            const data = await response.json();
            const goldPrice = data.rates.XAU;

            goldPriceElement.textContent = `1 Gold Ounce = $${goldPrice.toFixed(2)}`;
        } catch (error) {
            goldPriceElement.textContent = 'Error fetching gold price';
        }
    }

    convertBtn.addEventListener('click', fetchRates);
    fetchGoldPrice();
});
