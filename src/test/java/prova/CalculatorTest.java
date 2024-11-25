package prova;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import prova.Calculator;

class CalculatorTest {

    //preço menor que R$ 1000.00, quantidade maior que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithValidProductsAndDiscount() {
        List<Product> products = List.of(
                new Product("Product 1", 100.0, 2),
                new Product("Product 2", 200.0, 1)
        );

        assertEquals(360, Calculator.calculateInvoice(products, 0.1));
    }

    //preço maior que R$ 1000.00, quantidade maior que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithAdditionalDiscountOver() {
        List<Product> products = List.of(
                new Product("Product 1", 100.0, 5),
                new Product("Product 2", 200.0, 3)
        );

        assertEquals(890, Calculator.calculateInvoice(products, 0.1));
    }

    //preço menor que R$ 1000.00, quantidade maior que zero e desconto zerado
    @Test
    void testCalculateInvoiceWithZeroDiscount() {
        List<Product> products = List.of(
                new Product("Product 1", 200.0, 1),
                new Product("Product 2", 300.0, 1)
        );

        assertEquals(500, Calculator.calculateInvoice(products, 0.0));
    }

    //preço maior que R$ 1000.00, quantidade maior que zero e desconto zerado
    @Test
    void testCalculateInvoiceWithZeroDiscountAndWithAdditionalDiscountOver() {
        List<Product> products = List.of(
                new Product("Product 1", 100.0, 5),
                new Product("Product 2", 200.0, 3)
        );

        assertEquals(1000, Calculator.calculateInvoice(products, 0.0));
    }

    //preço menor que R$ 0.00, quantidade maior que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithNegativePriceThrowsException() {
        List<Product> products = List.of(
                new Product("Product 1", -100.0, 1)
        );

        assertThrows(InvalidProductException.class, () -> Calculator.calculateInvoice(products, 0.1));
    }

    //preço menor que R$ 1000.00, quantidade menor que zero e desconto maior que zero
    @Test
    void testCalculateInvoiceWithNegativeQuantityThrowsException() {
        List<Product> products = List.of(
                new Product("Product A", 100.0, -1)
        );

        assertThrows(InvalidProductException.class, () -> Calculator.calculateInvoice(products, 0.1));
    }
}