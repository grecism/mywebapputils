package util;

import java.math.BigDecimal;

public class CalculationUtil {
    
    public final static BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    
    public final static BigDecimal TEN_THOUSAND = BigDecimal.valueOf(10000);
    
    public static int multiply100(int initInput) {
        return BigDecimal.valueOf(initInput).multiply(ONE_HUNDRED).intValue();
    }
    
    public static int multiply100(double initInput) {
        return BigDecimal.valueOf(initInput).multiply(ONE_HUNDRED).intValue();
    }
    
    public static double devide100ForCalc(int ininInput) {
        return BigDecimal.valueOf(ininInput).divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UNNECESSARY).doubleValue();
    }
    
    public static int formatElement(int ininInput) {
        return BigDecimal.valueOf(ininInput).divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UNNECESSARY).intValue();
    }
    
    public static double devide100ForCalc(double ininInput) {
        return BigDecimal.valueOf(ininInput).divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UNNECESSARY).doubleValue();
    }
    
    public static String devide100(int ininInput) {
        return String.valueOf(BigDecimal.valueOf(ininInput).divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UNNECESSARY));
    }
    
    public static String devide100(double ininInput) {
        return String.valueOf(BigDecimal.valueOf(ininInput).divide(ONE_HUNDRED, 2, BigDecimal.ROUND_UNNECESSARY));
    }
    
}
