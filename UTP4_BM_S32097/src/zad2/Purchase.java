/**
 *
 *  @author Balcerowicz Maciej S32097
 *
 */

package zad2;


public class Purchase {

    private String customerId;
    private String customerName;
    private String product;
    private double price;
    private double quantity;

    public Purchase(String line) {
        String[] parts = line.split(";");
        customerId = parts[0];
        customerName = parts[1];
        product = parts[2];
        price = Double.parseDouble(parts[3]);
        quantity = Double.parseDouble(parts[4]);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCost(){
        return price * quantity;
    }

    public String toString() {
        return String.format("%s;%s;%s;%.1f;%.1f", customerId, customerName, product, price, quantity);
    }

    public String toStringWithCost() {
        return String.format("%s (koszt: %.1f)", this.toString(), getCost());
    }
}
