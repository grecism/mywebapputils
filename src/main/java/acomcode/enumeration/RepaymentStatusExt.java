package acomcode.enumeration;

public enum RepaymentStatusExt {
    
    //待还款：   已放款但未到期订单。
    //正常还款：按时还款订单，包含提前还款。
    //逾期未还款：逾期订单。
    //逾期已还款：产生逾期但已还款订单。
    待还款(0, "待还款"),
    正常还款(1, "正常还款"),
    逾期未还款(2, "逾期未还款"),
    逾期已还款(3, "逾期已还款");

    private int value;
    private String name;

    private RepaymentStatusExt(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean LoanStatus(int value) {
        for (RepaymentStatusExt forEnum : values()) {
            if (value == forEnum.getValue()) {
                return true;
            }
        }
        return false;
    }
}
