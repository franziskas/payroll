package input;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.List;

import util.ValueObject;

public class LineItems extends ValueObject {
    public static final String INPUT_SEPERATOR = ",";
    private List<String> items;

    public LineItems(String line) {
	items = readItems(line);
    }

    private List<String> readItems(String line) {
	if (line.length() > 0)
	    return asList(line.split(INPUT_SEPERATOR));
	return emptyList();
    }

    public List<String> getValues() {
	return items;
    }

    public void validate(int numberOfValuesExpected) {
	if (items.size() < numberOfValuesExpected)
	    throw new IllegalArgumentException("Input does not have "
		    + numberOfValuesExpected + " values: " + items);
    }

    public String getValue(int index) {
	return items.get(index);
    }

}
