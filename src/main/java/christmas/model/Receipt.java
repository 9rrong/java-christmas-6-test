package christmas.model;

import christmas.model.menu.Badge;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    public static final String WON_FORMAT = "%,d원";
    public static final int CHAMPAGNE_GIFT_TARGET_PRICE = 120_000;

    private static final String NO_CONTENT = "없음";


    public List<String> produceReceipt(Orders orders, LocalDate visitDate) {
        List<String> receipt = new ArrayList<>();
        int paymentValue = orders.getPayment();
        int totalBenefit = Discount.getTotalBenefit(orders, visitDate);
        int totalBenefitWithoutGift = Discount.getTotalBenefitWithoutGift(orders, visitDate);

        receipt.addAll(produceOrderMenus(orders));
        receipt.addAll(producePaymentBeforeDiscount(paymentValue));
        receipt.addAll(produceGiftMenu(paymentValue));
        receipt.addAll(produceBenefitDetails(orders, visitDate, totalBenefit));
        receipt.addAll(produceTotalBenefitAmounts(totalBenefit));
        receipt.addAll(producePaymentAfterDiscount(paymentValue, totalBenefitWithoutGift));
        receipt.addAll(produceDecemberEventBadge(totalBenefit));

        return receipt;
    }

    private List<String> produceDecemberEventBadge(int totalBenefit) {
        List<String> decemberEventBadge = new ArrayList<>();
        decemberEventBadge.add(System.lineSeparator() + "<12월 이벤트 배지>");
        decemberEventBadge.add(calculateEventBadge(totalBenefit));

        return decemberEventBadge;
    }

    private String calculateEventBadge(int totalBenefit) {
        return Badge.from(totalBenefit).getName();
    }

    private List<String> producePaymentAfterDiscount(int paymentValue, int totalBenefitWithoutGift) {
        List<String> paymentAfterDiscount = new ArrayList<>();
        String value = String.format(WON_FORMAT, paymentValue + totalBenefitWithoutGift);

        paymentAfterDiscount.add(System.lineSeparator() + "<할인 후 예상 결제 금액>");
        paymentAfterDiscount.add(value);

        return paymentAfterDiscount;
    }

    private List<String> produceTotalBenefitAmounts(int totalBenefit) {
        List<String> totalBenefitAmount = new ArrayList<>();
        totalBenefitAmount.add(System.lineSeparator() + "<총혜택 금액>");
        totalBenefitAmount.add(String.format(WON_FORMAT, totalBenefit));
        return totalBenefitAmount;
    }

    private List<String> produceOrderMenus(Orders orders) {
        List<String> orderMenus = new ArrayList<>(orders.getOrderMenus());
        orderMenus.add(0, System.lineSeparator() + "<주문 메뉴>");
        return orderMenus;
    }

    private List<String> producePaymentBeforeDiscount(Integer paymentValue) {
        List<String> payment = new ArrayList<>();
        payment.add(System.lineSeparator() + "<할인 전 총주문 금액>");
        payment.add(String.format(WON_FORMAT, paymentValue));

        return payment;
    }

    private List<String> produceGiftMenu(Integer paymentValue) {
        List<String> giftMenu = new ArrayList<>();
        giftMenu.add(System.lineSeparator() + "<증정 메뉴>");
        giftMenu.add(calculateGiftMenu(paymentValue));

        return giftMenu;
    }

    private String calculateGiftMenu(Integer paymentValue) {
        if (paymentValue > CHAMPAGNE_GIFT_TARGET_PRICE) {
            return "샴페인 1개";
        }
        return NO_CONTENT;
    }

    private List<String> produceBenefitDetails(Orders orders, LocalDate visitDate, int totalBenefit) {
        List<String> benefitDetails = new ArrayList<>();
        benefitDetails.add(System.lineSeparator() + "<혜택 내역>");

        if (totalBenefit != 0) {
            benefitDetails.addAll(Discount.getDiscountDetail(orders, visitDate));
        }

        if (totalBenefit == 0) {
            benefitDetails.add(NO_CONTENT);
        }

        return benefitDetails;
    }
}
