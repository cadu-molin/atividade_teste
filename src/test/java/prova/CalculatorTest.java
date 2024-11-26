package prova;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import prova.Calculator;

class CalculatorTest {

    //preço menor que R$ 1000.00, quantidade maior que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithValidProductsAndDiscount() {
        List<Product> products = List.of(
                new Product("Product 1", BigDecimal.valueOf(100), 2),
                new Product("Product 2", BigDecimal.valueOf(200), 1)
        );

        assertEquals(BigDecimal.valueOf(360).setScale(2, RoundingMode.HALF_UP), Calculator.calculateInvoice(products, BigDecimal.valueOf(0.1)));
    }

    //preço maior que R$ 1000.00, quantidade maior que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithAdditionalDiscountOver() {
        List<Product> products = List.of(
                new Product("Product 1", BigDecimal.valueOf(100), 5),
                new Product("Product 2", BigDecimal.valueOf(200), 3)
        );

        assertEquals(BigDecimal.valueOf(890).setScale(2, RoundingMode.HALF_UP), Calculator.calculateInvoice(products, BigDecimal.valueOf(0.1)));
    }

    //preço menor que R$ 1000.00, quantidade maior que zero e desconto zerado
    @Test
    void testCalculateInvoiceWithZeroDiscount() {
        List<Product> products = List.of(
                new Product("Product 1", BigDecimal.valueOf(200), 1),
                new Product("Product 2", BigDecimal.valueOf(300), 1)
        );

        assertEquals(BigDecimal.valueOf(500).setScale(2, RoundingMode.HALF_UP), Calculator.calculateInvoice(products, BigDecimal.ZERO));
    }

    //preço maior que R$ 1000.00, quantidade maior que zero e desconto zerado
    @Test
    void testCalculateInvoiceWithZeroDiscountAndWithAdditionalDiscountOver() {
        List<Product> products = List.of(
                new Product("Product 1", BigDecimal.valueOf(100), 5),
                new Product("Product 2", BigDecimal.valueOf(200), 3)
        );

        assertEquals(BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_UP), Calculator.calculateInvoice(products, BigDecimal.ZERO));
    }

    //preço menor que R$ 0.00, quantidade maior que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithNegativePriceThrowsException() {
        List<Product> products = List.of(
                new Product("Product 1", BigDecimal.valueOf(-100), 1)
        );

        assertThrows(InvalidProductException.class, () -> Calculator.calculateInvoice(products, BigDecimal.valueOf(0.1)));
    }

    //preço menor que R$ 1000.00, quantidade menor que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithNegativeQuantityThrowsException() {
        List<Product> products = List.of(
                new Product("Product A", BigDecimal.valueOf(100), -1)
        );

        assertThrows(InvalidProductException.class, () -> Calculator.calculateInvoice(products, BigDecimal.valueOf(0.1)));
    }
}