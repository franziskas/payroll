package input;

import static input.LineItems.SEPERATOR;

public class LineItemsBuilder {
    private LineItems lineItems;

    public LineItemsBuilder(Object... values) {
	String line = "";
	for (Object value : values) {
	    line += value;
	    line += SEPERATOR;
	}
	lineItems = new LineItems(
		line.substring(0, line.lastIndexOf(SEPERATOR)));
    }

    public LineItems create() {
	return lineItems;
    }

}
