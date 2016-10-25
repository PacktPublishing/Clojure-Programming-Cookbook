import java.util.Iterator;

import clojure_java.records.Item;
import clojure.lang.Keyword;

public class Java1 implements Iterator<Item> {
  public static final Keyword INFO =
                           Keyword.intern(null, "info");

  private long count;

  public Java1(long count) {
    this.count = count;
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }

  public Item next() {
    if (0 < count) {
      count--;
      return new Item(INFO, String.format("%d", count));
    } else 
    return null;
  }

  public boolean hasNext() {
    return 0 < count;
  }
}