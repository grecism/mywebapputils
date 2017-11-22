package acomcode.enumeration;

public enum LoanStatus {

    审核中(0, "审核中"),
    审核成功_放款待确认(1, "审核成功(放款待确认)"),
    审核失败(2, "审核失败"),
    确认失败(3, "确认失败"),
    放款中(4, "放款中"),
    放款失败(5, "放款失败"),
    放款成功(6, "放款成功"),
	已流标(101,"流标成功");

    private int value;
    private String name;

    private LoanStatus(int value, String name) {
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
        for (LoanStatus forEnum : values()) {
            if (value == forEnum.getValue()) {
                return true;
            }
        }
        return false;
    }
    
    public static String getName(int value) {
        for (LoanStatus forEnum : values()) {
            if (value == forEnum.getValue()) {
                return forEnum.name;
            }
        }
        return null;
    }
}
