/**
 * Created by Lambda on 11/22/2015.
 */
public enum ItemTypeIndex {
  ITEM_NAME    (0),
  ITEM_ID      (1),
  ITEM_PRICE   (2),
  NUM_OF_ITEMS (3),
  ICON_DIR     (4),
  BK_AUTHOR    (5),
  ISBN         (6),
  ITEM_TYPE    (7),
  CALORIES     (8)
  ;
  private final int INDEX;

  ItemTypeIndex(int idx){
    this.INDEX = idx;
  }

  public int get_index(){
    return this.INDEX;
  }
}
