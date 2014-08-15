package input.builder;

import input.LineItems;

public abstract class LineItemsBuilder {

    public static final long SERIAL_NUMBER = 123L;
    protected String seperator;
    protected Object[] values;
    protected static final long OTHER_SERIAL_NUMBER = 1234L;

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