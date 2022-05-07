/*
 * Represents a record in entity in a scheme called Student
 */

package dtos;

import java.time.LocalDate;

public final class StudentDTO extends DTOBase{

    private final String IndexNumber;
    private final LocalDate dateOfEnrollment;

    public StudentDTO(int id, String indexNumber, LocalDate dateOfEnrollment) {
        super(id);
        IndexNumber = indexNumber;
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getIndexNumber() {
        return IndexNumber;
    }

    public LocalDate getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentDTO student = (StudentDTO) obj;
        return getId() == student.getId() &&
                IndexNumber.equals(student.IndexNumber) &&
                dateOfEnrollment.equals(student.dateOfEnrollment);
    }
}
