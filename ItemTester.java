/**
 * Created by Lambda on 11/14/2015.
 */
public class ItemTester {
  public static void main(String[] args) {
    Item item1 = new Refreshment("Mountain Dew", "1.99", "1", "160");

    PriceInterface pt = new RecycleTaxableItem(item1);

    System.out.println(item1.get_info());
    System.out.println(pt.get_price());
  }
}
