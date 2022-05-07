/*
 * Describes the additional function implemented by the repository with the appropriate name
 */

package repositories.interfaces;

import dtos.PersonDTO;
import java.util.List;

public interface IPersonRepository extends IRepository<PersonDTO>{

    /**
     * Returns a {@link List<PersonDTO>} object containing objects representing
     * records in the Person entity which names start with the parameter
     *
     * @param startsWith - the prefix of the person's name that must be in the records found
     *
     * @return a new {@link List<PersonDTO>}
     */
    List<PersonDTO> findByNameBeginning(String startsWith);
}
