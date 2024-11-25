package prova;

import java.util.List;

import prova.Calculator;

public class Main {
    public static void main(String[] args) {
        try {
            List<Product> products = List.of(
                    new Product("Produto 1", 100.0, 4),
                    new Product("Produto 2", 200.0, 3),
                    new Product("Produto 3", 300.0, 2),
                    new Product("Produto 4", 400.0, 1)
            );

            Double invoiceValue = Calculator.calculateInvoice(products, 0.1);
            System.out.println("Valor total da fatura: R$ " + invoiceValue);

        } catch (InvalidProductException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}