import java.math.BigDecimal;

/**
 * Created by Lambda on 11/15/2015.
 */
public class RecycleTaxableItem extends TaxableItem{
  private final String _RECYCLE_TAX = "1.0";
  public RecycleTaxableItem(Item input_item) {
    super(input_item);
  }

  @Override
  public BigDecimal get_price(){
    return new BigDecimal(_RECYCLE_TAX).add(super.get_price());
  }
}
