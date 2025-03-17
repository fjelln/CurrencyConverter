package se.lexicon;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void run() {
        displayCurrentRates();
        while (true) {
            printMenu();
            int userChoice = getUserChoice();
            options(userChoice);
        }
    }

    static void printMenu() {
        System.out.println("Currency Converter App");
        System.out.println("1. Convert SEK to USD");
        System.out.println("2. Convert USD to SEK");
        System.out.println("3. Convert SEK to Euro");
        System.out.println("4. Convert Euro to SEK");
        System.out.println("0. Exit");
    }

    static void options(int option) {
        double amount = 0, exchange = 0;
        String firstCurrency = "", secondCurrency = "";
        switch (option) {
            case 1:
                amount = getCurrencyAmount();
                exchange = ExchangeCalculator.exchange(amount, ExchangeRate.rateSEKToUSD);
                firstCurrency = "SEK";
                secondCurrency = "USD";
                break;
            case 2:
                amount = getCurrencyAmount();
                exchange = ExchangeCalculator.exchange(amount, ExchangeRate.rateUSDToSEK);
                firstCurrency = "USD";
                secondCurrency = "SEK";
                break;
            case 3:
                amount = getCurrencyAmount();
                exchange = ExchangeCalculator.exchange(amount, ExchangeRate.rateSEKToEuro);
                firstCurrency = "SEK";
                secondCurrency = "Euro";
                break;
            case 4:
                amount = getCurrencyAmount();
                exchange = ExchangeCalculator.exchange(amount, ExchangeRate.rateEuroToSEK);
                firstCurrency = "Euro";
                secondCurrency = "SEK";
                break;
            case 0:
                System.out.println("Good bye!");
                System.exit(0);
            default:
                System.out.println("Somehow an invalid input. Try again.");
        }
        System.out.printf("\n%f %s = %f %s\n\n", amount, firstCurrency, exchange, secondCurrency);
    }

    static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                userChoice = scanner.nextInt();
                if (userChoice > 4 || userChoice < 0) {
                    System.out.println("Invalid input. Try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                scanner.next();
            }
        }
        return userChoice;
    }

    static double getCurrencyAmount() {
        Scanner scanner = new Scanner(System.in);
        double amount;
        while (true) {
            System.out.print("Amount: ");
            try {
                amount = scanner.nextDouble();
                if (amount < 0.0) {
                    System.out.println("Can't be a negative number. Try again.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                scanner.next();
            }
        }
        return amount;
    }

    static void displayCurrentRates() {
        LocalDate today = LocalDate.now();
        System.out.println("The current rates as of " + today);
        System.out.println("----------------");
        System.out.println("SEK to USD: " + ExchangeRate.rateSEKToUSD);
        System.out.println("USD to SEK: " + ExchangeRate.rateUSDToSEK);
        System.out.println("SEK to Euro: " + ExchangeRate.rateSEKToEuro);
        System.out.println("Euro to SEK: " + ExchangeRate.rateEuroToSEK);
        System.out.println("----------------");
    }
}
