package acomcode.enumeration;

public enum RepaymentStatus {

    //还款状态(0未还款 1还款完成 2还款失败)
    未还款(0, "未还款"),
    还款完成(1, "还款完成"),
    还款失败(2, "还款失败");
    
    private int value;
    private String name;

    private RepaymentStatus(int value, String name) {
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
        for (RepaymentStatus forEnum : values()) {
            if (value == forEnum.getValue()) {
                return true;
            }
        }
        return false;
    }
}
