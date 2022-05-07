/*
 * Represents a record in entity in a scheme called Grade
 */

package dtos;

public final class GradeDTO extends DTOBase{

    private final int value;
    private final String valueInWords;

    public GradeDTO(int id, int value, String valueInWords) {
        super(id);
        this.value = value;
        this.valueInWords = valueInWords;
    }

    public int getValue() {
        return value;
    }

    public String getValueInWords() {
        return valueInWords;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GradeDTO grade = (GradeDTO) obj;
        return getId() == grade.getId() &&
                value == grade.value &&
                valueInWords.equals(grade.valueInWords);
    }
}
