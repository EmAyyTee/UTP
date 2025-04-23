/**
 *
 *  @author Balcerowicz Maciej S32097
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomersPurchaseSortFind {

    private List<Purchase> purchases = new ArrayList<>();

    public void readFile(String fname) {
        try {
            Files.lines(Paths.get(fname))
                    .map(Purchase::new)
                    .forEach(purchases::add);
        } catch (IOException e){
            System.out.println("Błąd odczytu pliku: " + e.getMessage());
        }
    }

    public void showSortedBy(String category){
        switch (category){
            case "Nazwiska":
                System.out.println("Nazwiska");
                purchases.stream()
                        .sorted(Comparator
                                .comparing(Purchase::getCustomerId)
                                .thenComparing(Purchase::getCustomerName))
                        .forEach(System.out::println);
                break;

            case "Koszty":
                System.out.println("Koszty");
                purchases.stream()
                        .sorted(Comparator
                                .comparingDouble(Purchase::getCost).reversed()
                                .thenComparing(Purchase::getCustomerId))
                        .forEach(p -> System.out.println(p.toStringWithCost()));
                break;
        }
    }

    public void showPurchaseFor(String customerId){
        System.out.println("Klient " + customerId);
        purchases.stream()
                .filter(p -> p.getCustomerId().equals(customerId))
                .forEach(System.out::println);
    }
}
