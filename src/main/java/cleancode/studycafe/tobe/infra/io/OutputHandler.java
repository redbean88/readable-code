package cleancode.studycafe.tobe.infra.io;

import cleancode.studycafe.tobe.model.LockerTicket;
import cleancode.studycafe.tobe.model.Ticket;
import cleancode.studycafe.tobe.model.Tickets;
import cleancode.studycafe.tobe.model.Type;
import cleancode.studycafe.tobe.vo.Money;
import cleancode.studycafe.tobe.vo.Result;
import java.util.List;

public class OutputHandler {

    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showTicketsForSelection(Tickets tickets) {
        System.out.println();
        System.out.println("이용권 목록");
        List<Ticket> list = tickets.getList();
        list.forEach(ticket -> {
            Type type = ticket.getType();
            Money money = ticket.getPrice();
            int duration = ticket.getDuration();
            switch (type) {
                case HOURLY -> System.out.printf("%s시간권 - %s원%n", duration, money);
                case WEEKLY, FIXED -> System.out.printf("%s주권 - %s원%n", duration, money);
            }
        });
        for (int index = 0; index < list.size(); index++) {
            Ticket ticket = list.get(index);
            Type type = ticket.getType();
            Money money = ticket.getPrice();
            int duration = ticket.getDuration();

            System.out.printf("%s. %s%n", index + 1, switch (type) {
                case HOURLY -> String.format("%s시간권 - %s원", duration, money);
                case WEEKLY, FIXED -> String.format("%s주권 - %s원", duration, money);
            });
        }
    }

    public void askLockerTicket(LockerTicket lockerTicket) {
        System.out.println();
        Type type = lockerTicket.getType();
        Money money = lockerTicket.getMoney();
        int duration = lockerTicket.getDuration();
        String askMessage = String.format("사물함을 이용하시겠습니까? (%s)", switch (type) {
            case HOURLY -> String.format("%s시간권 - %s원", duration, money);
            case WEEKLY, FIXED -> String.format("%s주권 - %s원", duration, money);
        });

        System.out.println(askMessage);
        System.out.println("1. 예 | 그 외. 아니오");
    }

    public void showTicketOrderSummary(Result result) {
        System.out.println();
        System.out.println("이용 내역");
        showTicketTypes(result);

        if (result.hasLockerTicket()) {
            showLockerTicketType(result);
        }

        System.out.println("이벤트 할인 금액: " + result.calculateDiscountPrice() + "원");

        Money totalPrice = result.calculateTotalPrice();
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    private void showLockerTicketType(Result result) {
        LockerTicket lockerTicket = result.getLockerTicket();
        Type type = lockerTicket.getType();
        Money money = lockerTicket.getMoney();
        int duration = lockerTicket.getDuration();
        System.out.printf("사물함: {}%n", switch (type) {
            case HOURLY -> String.format("%s시간권 - %s원", duration, money);
            case WEEKLY, FIXED -> String.format("%s주권 - %s원", duration, money);
        });
    }

    private static void showTicketTypes(Result result) {
        Ticket ticket = result.getTicket();
        Type type = ticket.getType();
        Money money = ticket.getPrice();
        int duration = ticket.getDuration();
        System.out.printf("이용권: %s", switch (type) {
            case HOURLY -> String.format("%s시간권 - %s원", duration, money);
            case WEEKLY, FIXED -> String.format("%s주권 - %s원", duration, money);
        });
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

}
