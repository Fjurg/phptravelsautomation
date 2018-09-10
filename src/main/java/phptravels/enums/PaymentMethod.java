package phptravels.enums;

public enum PaymentMethod {
    JAZZ_CASH("JazzCash");

    private final String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
