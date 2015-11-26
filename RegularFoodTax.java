import java.math.BigDecimal;

/**
 * Created by Lambda on 11/25/2015.
 */
public class RegularFoodTax extends TaxableItem{
  public RegularFoodTax(Item input_item){
    super(input_item);
  }
  @Override
  public BigDecimal get_price(){
    return super.get_price();
  }
}
