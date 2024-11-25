package prova;

import java.util.List;

public class Calculator {
    public static double calculateInvoice(List<Product> products, Double discount) {

        //Lança um InvalidProductException caso encontre um produto com preço ou qauntidade menor que zero
        if (products.stream().anyMatch(product -> product.getPrice() < 0 || product.getQuantity() < 0)) {
            throw new InvalidProductException("Produto com preço ou quantidade inválidos.");
        }

        //Soma o resultado da multiplicação entre preço e quantidade por produto
        Double total = products.stream().reduce(0.0, (subtotal, product) -> subtotal + (product.getPrice() * product.getQuantity()), Double::sum);

        //Aplica o desconto repebido por parametro
        Double discountedTotal = total * (1 - discount);

        //Caso o total seja maior que R$1.000, será aplicado um desconto de R$100
        if (total > 1000) {
            discountedTotal -= 100;
        }

        return discountedTotal;
    }
}
