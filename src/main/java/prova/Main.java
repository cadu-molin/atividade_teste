package prova;

import java.math.BigDecimal;
import java.util.List;

import prova.Calculator;

public class Main {
    public static void main(String[] args) {
        try {
            List<Product> products = List.of(
                    new Product("Produto 1", BigDecimal.valueOf(100), 4),
                    new Product("Produto 2", BigDecimal.valueOf(200), 3),
                    new Product("Produto 3", BigDecimal.valueOf(300), 2),
                    new Product("Produto 4", BigDecimal.valueOf(400), 1)
            );

            BigDecimal invoiceValue = Calculator.calculateInvoice(products, BigDecimal.valueOf(0.1));
            System.out.println("Valor total da fatura: R$ " + invoiceValue);

        } catch (InvalidProductException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}