/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.StudentDTO;
import java.time.LocalDate;
import java.util.List;

public interface IStudentRepository extends IRepository<StudentDTO>{

    /**
     * Returns a {@link List<StudentDTO>} object containing objects representing records
     * in the Student entity which dateOfEnrollment earlier than the parameter value
     *
     * @param date - the date up to which students must be enrolled in the records found
     *
     * @return a new {@link List<StudentDTO>}
     */
    List<StudentDTO> findEnrolledEarlierThen(LocalDate date);
}
