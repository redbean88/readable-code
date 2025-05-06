package cleancode.studycafe.tobe.model;

public class StudyCafePass implements DisplayInfo{

    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafePass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafePass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafePass(passType, duration, price, discountRate);
    }

    @Override
    public StudyCafePassType getPassType() {
        return passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public boolean isEqualsPassTypeBy(StudyCafePassType studyCafePassType) {
        return passType == studyCafePassType;
    }

    public int getDiscountPrice() {
        return (int) (discountRate * price);
    }

    public boolean isEqualDuration(StudyCafeLockerPass lockerPass) {
        return lockerPass.isEqualDuration(duration);
    }

    public boolean isEqualsPassTypeBy(StudyCafeLockerPass lockerPass) {
        return lockerPass.isEqualsPassTypeBy(passType);
    }
}
