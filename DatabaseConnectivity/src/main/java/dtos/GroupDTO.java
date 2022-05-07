/*
 * Represents a record in entity in a scheme called Group
 */

package dtos;

public final class GroupDTO extends DTOBase{

    private final int number;
    private final char type;

    public GroupDTO(int id, int number, char type) {
        super(id);
        this.number = number;
        this.type = type;
    }

    public GroupDTO(int id) {
        this(id, 0, ' ');
    }

    public int getNumber() {
        return number;
    }

    public char getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GroupDTO group = (GroupDTO) obj;
        return getId() == group.getId() &&
                number == group.number &&
                type == group.type;
    }
}
