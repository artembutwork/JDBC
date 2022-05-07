/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.RoomDTO;
import java.util.List;

public interface IRoomRepository extends IRepository<RoomDTO> {

    /**
     * Returns a {@link List<RoomDTO>} object containing objects representing
     * records in the Room entity which capacities within[lowerBound, upperBound]
     *
     * @param lowerBound - minimum permissible room capacity that must be in the records found
     * @param upperBound - maximum permissible room capacity that must be in the records found
     *
     * @return a new {@link List<RoomDTO>}
     */
    List<RoomDTO> findByCapacity(int lowerBound, int upperBound);
}
