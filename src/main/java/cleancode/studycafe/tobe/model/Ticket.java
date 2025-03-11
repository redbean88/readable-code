package cleancode.studycafe.tobe.model;

public enum Ticket {
    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석");

    private final String description;

    Ticket(String description) {
        this.description = description;
    }
}
