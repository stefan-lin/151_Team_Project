import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Lambda on 11/16/2015.
 */

/**
 * CurrencyConvertor
 *
 * THIS IS A CLASS THAT PROVIDES FUNCTIONALITY TO CONVERT BIGDECIMAL/DOUBLE
 * OBJECT INTO USD FORMAT (STRING)
 */
public final class CurrencyConvertor {
  private static NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);

  private CurrencyConvertor(){}

  public static String convert_to_USD_fmt(BigDecimal num){
    num.setScale(2, BigDecimal.ROUND_HALF_UP);
    return nf.format(num.doubleValue());
  }

  public static String conver_to_USD_fmt(Double dbl){
    return nf.format(new BigDecimal(dbl).
        setScale(2, BigDecimal.ROUND_HALF_UP).
        doubleValue());
  }
}
