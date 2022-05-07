/*
 * Represents a record in entity in a scheme called Teacher
 */

package dtos;

import java.util.Objects;

public final class TeacherDTO extends DTOBase{

    private final String academicRank;

    public TeacherDTO(int id, String academicRank) {
        super(id);
        this.academicRank = academicRank;
    }

    public TeacherDTO(int id) {
        this(id, null);
    }

    public String getAcademicRank() {
        return academicRank;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TeacherDTO teacher = (TeacherDTO) obj;
        return getId() == teacher.getId() &&
                Objects.equals(academicRank, teacher.getAcademicRank());
    }
}
