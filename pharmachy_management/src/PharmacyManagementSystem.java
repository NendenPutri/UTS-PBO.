import java.util.ArrayList;
import java.util.Scanner;

abstract class Product {
    protected String name;
    protected String description;
    protected int stock;
    protected double price;

    public Product(String name, String description, int stock, double price) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public abstract void sell(int quantity);

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Stock: " + stock);
        System.out.println("Price: $" + price);
    }
}

class Medication extends Product {
    public Medication(String name, String description, int stock, double price) {
        super(name, description, stock, price);
    }

    @Override
    public void sell(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
            System.out.println("Sold " + quantity + " units of " + name);
        } else {
            System.out.println("Insufficient stock for " + name);
        }
    }
}

abstract class Transaction {
    protected String productName;
    protected int quantity;
    protected double totalPrice;

    public Transaction(String productName, int quantity, double totalPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public abstract void display();
}

class SellTransaction extends Transaction {
    public SellTransaction(String productName, int quantity, double totalPrice) {
        super(productName, quantity, totalPrice);
    }

    @Override
    public void display() {
        System.out.println("Medication: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Price: $" + totalPrice);
    }
}

public class PharmacyManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        while (true) {
            System.out.println("1. Add Medication");
            System.out.println("2. View Stock");
            System.out.println("3. Sell Medication");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter medication name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter medication description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter medication stock: ");
                    int stock = scanner.nextInt();
                    System.out.print("Enter medication price: ");
                    double price = scanner.nextDouble();
                    products.add(new Medication(name, description, stock, price));
                    break;
                case 2:
                    System.out.println("Medication Stock:");
                    for (Product product : products) {
                        product.display();
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println("Enter medication name to sell: ");
                    String medName = scanner.nextLine();
                    System.out.println("Enter quantity to sell: ");
                    int quantity = scanner.nextInt();
                    for (Product product : products) {
                        if (product.getName().equalsIgnoreCase(medName)) {
                            double totalPrice = quantity * product.getPrice();
                            product.sell(quantity);
                            transactions.add(new SellTransaction(medName, quantity, totalPrice));
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    for (Transaction transaction : transactions) {
                        transaction.display();
                        System.out.println();
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
