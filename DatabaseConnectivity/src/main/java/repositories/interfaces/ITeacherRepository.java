/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.TeacherDTO;
import java.util.List;

public interface ITeacherRepository extends IRepository<TeacherDTO> {

    /**
     * Returns a {@link List<TeacherDTO>} object containing objects representing
     * records in the Teacher entity which academicRank is null
     *
     * @return a new {@link List<TeacherDTO>}
     */
    List<TeacherDTO> findWithoutRank();
}
