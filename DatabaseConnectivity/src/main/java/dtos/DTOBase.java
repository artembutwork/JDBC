/*
 * DTOBase child classes represent records in entities with an appropriate names
 */

package dtos;

public class DTOBase {

    private final int id;

    public DTOBase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
