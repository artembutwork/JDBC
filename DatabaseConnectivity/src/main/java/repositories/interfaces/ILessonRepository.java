/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.LessonDTO;
import java.util.List;

public interface ILessonRepository extends IRepository<LessonDTO> {

    /**
     * Returns a {@link List<LessonDTO>} object containing objects representing records
     * in the Lesson entity which duration(expressed in minutes) equals the parameter value
     *
     * @param minutes - the duration of the lesson that must be in the records found
     *
     * @return a new {@link List<LessonDTO>}
     */
    List<LessonDTO> findByLessonDuration(int minutes);
}
