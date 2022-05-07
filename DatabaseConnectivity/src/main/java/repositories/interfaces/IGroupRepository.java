/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.GroupDTO;
import java.util.List;

public interface IGroupRepository extends IRepository<GroupDTO> {

    /**
     * Returns a {@link List<GroupDTO>} object containing objects representing
     * records in the Group entity which type is the same as the parameter value
     *
     * @param type - the group type that the records found must be
     *
     * @return a new {@link List<GroupDTO>}
     */
    List<GroupDTO> findByType(String type);
}
