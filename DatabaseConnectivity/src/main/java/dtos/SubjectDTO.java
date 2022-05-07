/*
 * Represents a record in entity in a scheme called Subject
 */

package dtos;

public final class SubjectDTO extends DTOBase{

    private final String name;
    private final String acronym;

    public SubjectDTO(int id, String name, String acronym) {
        super(id);
        this.name = name;
        this.acronym = acronym;
    }

    public SubjectDTO(int id) {
        this(id, "","");
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SubjectDTO subject = (SubjectDTO) obj;
        return getId() == subject.getId() &&
                name.equals(subject.name) &&
                acronym.equals(subject.acronym);
    }
}
