/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.GradeDTO;
import java.util.List;

public interface IGradeRepository extends IRepository<GradeDTO>{

    /**
     * Returns a {@link List<GradeDTO>} object containing objects representing
     * records in the Grade entity which value is greater than the parameter value
     *
     * @param gradeValue - the value above which the value in the records found must be
     *
     * @return a new {@link List<GradeDTO>}
     */
    List<GradeDTO> findGreaterThen(int gradeValue);
}
