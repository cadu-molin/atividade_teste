package prova;

class InvalidProductException extends RuntimeException {
    public InvalidProductException(String message) {
        super(message);
    }
}