/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.SubjectDTO;
import java.util.List;

public interface ISubjectRepository extends IRepository<SubjectDTO> {

    /**
     * Returns a {@link List<SubjectDTO>} object containing objects representing
     * records in the Subject entity which acronym length equals 3
     *
     * @return a new {@link List<SubjectDTO>}
     */
    List<SubjectDTO> findThreeCharactersAcronym();
}
