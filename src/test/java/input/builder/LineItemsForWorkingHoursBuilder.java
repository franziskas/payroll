package input.builder;

import static input.LineItems.INPUT_SEPERATOR;

public class LineItemsForWorkingHoursBuilder extends LineItemsBuilder {
    public static final String DATE = "20111101";
    public static final String OTHER_DATE = "20111102";
    public static final int HOURS = 8;
    private static final int HOUR_START = 10;
    private static final int HOUR_END = 10 + HOURS;
    private static final String MINUTES = "00";
    private static final String TIMESTAMP_IN = DATE + HOUR_START + MINUTES;
    private static final String TIMESTAMP_OUT = DATE + HOUR_END + MINUTES;
    private static final String TIMESTAMP_IN_DIFFERENT_DATE = OTHER_DATE
	    + HOUR_START + MINUTES;
    private static final String TIMESTAMP_OUT_DIFFERENT_DATE = OTHER_DATE
	    + HOUR_END + MINUTES;
    public static final String TIMESTAMP_TOO_SHORT = HOUR_START + MINUTES;

    public LineItemsForWorkingHoursBuilder() {
	super(INPUT_SEPERATOR, SERIAL_NUMBER, TIMESTAMP_IN, TIMESTAMP_OUT);
    }

    public LineItemsBuilder withTimestampsOnDifferentDates() {
	setValues(SERIAL_NUMBER, TIMESTAMP_IN, TIMESTAMP_OUT_DIFFERENT_DATE);
	return this;
    }

    public LineItemsBuilder withTimestampTooShort() {
	setValues(SERIAL_NUMBER, TIMESTAMP_IN, TIMESTAMP_TOO_SHORT);
	return this;
    }

    public LineItemsBuilder withOtherValues() {
	setValues(OTHER_SERIAL_NUMBER, TIMESTAMP_IN_DIFFERENT_DATE,
		TIMESTAMP_OUT_DIFFERENT_DATE);
	return this;
    }

    public LineItemsBuilder forDifferentDay() {
	setValues(SERIAL_NUMBER, TIMESTAMP_IN_DIFFERENT_DATE,
		TIMESTAMP_OUT_DIFFERENT_DATE);
	return this;
    }
}
