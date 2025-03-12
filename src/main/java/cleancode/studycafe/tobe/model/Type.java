package cleancode.studycafe.tobe.model;

public enum Type {
    HOURLY("1","시간 단위 이용권"),
    WEEKLY("2","주 단위 이용권"),
    FIXED("3","1인 고정석");

    private final String number;
    private final String description;

    Type(String number, String description) {
        this.number = number;
        this.description = description;
    }
}
