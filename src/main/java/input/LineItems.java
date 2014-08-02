package input;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

import java.util.List;

public class LineItems {

    public static final String SEPERATOR = ",";
    private List<String> items;

    public LineItems(String line) {
	items = readItems(line);
    }

    private List<String> readItems(String line) {
	if (line.length() > 0)
	    return asList(line.split(SEPERATOR));
	return emptyList();
    }

    public List<String> getValues() {
	return items;
    }

    @Override
    public int hashCode() {
	return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
	return reflectionEquals(this, obj);
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
