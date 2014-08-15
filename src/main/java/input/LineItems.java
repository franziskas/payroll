package input;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.List;

import util.ValueObject;
import domain.Resource;

public class LineItems extends ValueObject {

    public static final String SEPERATOR = ",";
    private List<String> items;

    public LineItems(String line) {
	items = readItems(line);
    }

    public LineItems(Resource resource) {
	items = resource.getItemsAsList();
    }

    private List<String> readItems(String line) {
	if (line.length() > 0)
	    return asList(line.split(SEPERATOR));
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

    public String getOutput() {
	String itemsAsString = "";
	for (String item : items) {
	    itemsAsString += item;
	    itemsAsString += " | ";
	}
	return itemsAsString.substring(0, itemsAsString.length() - 3);
    }
}
