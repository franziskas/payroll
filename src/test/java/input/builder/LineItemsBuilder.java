package input.builder;

import input.LineItems;

public abstract class LineItemsBuilder {
    public static final long SERIAL_NUMBER = 123L;
    public static final long OTHER_SERIAL_NUMBER = 1234L;

    protected String seperator;
    protected Object[] values;
    public static final int REGULAR_HOURS = 7;
    public static final String OTHER_FIRST_NAME = "first2";
    public static final String OTHER_LAST_NAME = "last2";
    public static final String LAST_NAME = "last";
    public static final String FIRST_NAME = "first";

    protected LineItemsBuilder(String seperator, Object... values) {
	this.seperator = seperator;
	this.values = values;
    }

    protected LineItemsBuilder withSeperator(String seperator) {
	this.seperator = seperator;
	return this;
    }

    public LineItems create() {
	String line = "";

	for (Object value : values) {
	    line += value;
	    line += seperator;
	}

	return new LineItems(line.substring(0, line.lastIndexOf(seperator)));
    }

    protected void setValues(Object... values) {
	this.values = values;
    }

}