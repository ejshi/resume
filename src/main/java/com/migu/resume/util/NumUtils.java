package com.migu.resume.util;

import java.math.BigDecimal;

public class NumUtils {
    public NumUtils() {
    }

    public static boolean greatThan(Number num1, Number num2) {
        return num1 != null && num2 != null?(num1 instanceof Long && num2 instanceof Long?num1.longValue() > num2.longValue():(num1 instanceof Long && num2 instanceof Integer?num1.longValue() > (long)num2.intValue():(num1 instanceof Integer && num2 instanceof Long?(long)num1.intValue() > num2.longValue():(num1 instanceof Integer && num2 instanceof Integer?num1.intValue() > num2.intValue():false)))):false;
    }

    public static boolean lessThan(Number num1, Number num2) {
        return num1 != null && num2 != null?(num1 instanceof Long && num2 instanceof Long?num1.longValue() < num2.longValue():(num1 instanceof Long && num2 instanceof Integer?num1.longValue() < (long)num2.intValue():(num1 instanceof Integer && num2 instanceof Long?(long)num1.intValue() < num2.longValue():(num1 instanceof Integer && num2 instanceof Integer?num1.intValue() < num2.intValue():false)))):false;
    }

    public static boolean equals(Number num1, Number num2) {
        return num1 != null && num2 != null?(num1 instanceof Long && num2 instanceof Long?num1.longValue() == num2.longValue():(num1 instanceof Long && num2 instanceof Integer?num1.longValue() == (long)num2.intValue():(num1 instanceof Integer && num2 instanceof Long?(long)num1.intValue() == num2.longValue():(num1 instanceof Integer && num2 instanceof Integer?num1.intValue() == num2.intValue():false)))):false;
    }

    public static boolean notEquals(Number num1, Number num2) {
        return !equals(num1, num2);
    }

    public static boolean greatThanFirstEvery(Number num, Number... nums) {
        boolean result = false;
        if(num != null && nums != null && nums.length > 0) {
            Number[] arr$ = nums;
            int len$ = nums.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Number n = arr$[i$];
                result = greatThan(n, num);
                if(!result) {
                    break;
                }
            }
        }

        return result;
    }

    public static int intValue(Integer num) {
        return num == null?0:num.intValue();
    }

    public static long longValue(Long num) {
        return num == null?0L:num.longValue();
    }

    public static double doubleValue(Double num) {
        return num == null?0.0D:num.doubleValue();
    }

    public static int parseInt(Object num) {
        try {
            return num != null && num instanceof Boolean?(((Boolean)num).booleanValue()?1:0):Integer.parseInt(num.toString());
        } catch (Exception var2) {
            return 0;
        }
    }

    public static long parseLong(Object num) {
        try {
            return Long.parseLong(num.toString());
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static double parseDouble(Double num) {
        try {
            return Double.parseDouble(num.toString());
        } catch (Exception var2) {
            return 0.0D;
        }
    }

    public static float parseFloat(Object num) {
        try {
            return Float.parseFloat(num.toString());
        } catch (Exception var2) {
            return 0.0F;
        }
    }

    public static float divide(long num1, long num2) {
        try {
            float e = (new BigDecimal(num1 * 100L)).divide(new BigDecimal(num2), 2, 4).floatValue();
            if(e > 100.0F) {
                e = 100.0F;
            } else if(e < 0.0F) {
                e = 0.0F;
            }

            return e;
        } catch (Exception var5) {
            return 0.0F;
        }
    }

    public static long divideToLong(long num1, long num2) {
        try {
            long e = (new BigDecimal(num1 * 100L)).divide(new BigDecimal(num2), 2, 4).longValue();
            if(e > 100L) {
                e = 100L;
            } else if(e < 0L) {
                e = 0L;
            }

            return e;
        } catch (Exception var6) {
            return 0L;
        }
    }

    public static String dividePercent(int x, int y) {
        String res = "0";

        try {
            if(x <= 0 || y <= 0) {
                return res;
            }

            BigDecimal e = new BigDecimal(x * 100);
            BigDecimal j = new BigDecimal(y);
            res = e.divide(j, 1, 3).toString();
        } catch (Exception var5) {
            return res;
        }

        return res.replace(".0", "");
    }

    public static long defaultValue(Number value, long defaultValue) {
        return value != null?value.longValue():defaultValue;
    }

    public static int defaultValue(Number value, int defaultValue) {
        return value != null?value.intValue():defaultValue;
    }
}
