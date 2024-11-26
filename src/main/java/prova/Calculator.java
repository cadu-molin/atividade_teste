package prova;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Calculator {
    public static BigDecimal calculateInvoice(List<Product> products, BigDecimal discount) {

        // Lança um InvalidProductException caso encontre um produto com preço ou quantidade menor que zero
        if (products.stream().anyMatch(product -> product.getPrice().compareTo(BigDecimal.ZERO) < 0 || product.getQuantity() < 0)) {
            throw new InvalidProductException("Produto com preço ou quantidade inválidos.");
        }

        // Soma o resultado da multiplicação entre preço e quantidade por produto
        BigDecimal total = products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Aplica o desconto recebido por parâmetro
        BigDecimal discountedTotal = total.multiply(BigDecimal.ONE.subtract(discount));

        // Caso o total seja maior que R$1000 será aplicado um desconto adicional de R$100
        if (total.compareTo(BigDecimal.valueOf(1000)) > 0) {
            discountedTotal = discountedTotal.subtract(BigDecimal.valueOf(100));
        }

        // Garante que o valor final tenha apenas duas casas decimais, arredondando corretamente
        return discountedTotal.setScale(2, RoundingMode.HALF_UP);
    }
}
