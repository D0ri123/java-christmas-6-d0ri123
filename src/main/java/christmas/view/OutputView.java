package christmas.view;

public class OutputView {
    private static final String previewEventBenefits = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void previewEventBenefits(int date) {
        System.out.printf(previewEventBenefits, date);
    }

}
