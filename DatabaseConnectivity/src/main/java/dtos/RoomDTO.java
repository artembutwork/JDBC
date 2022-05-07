/*
 * Represents a record in entity in a scheme called Room
 */

package dtos;

public final class RoomDTO extends DTOBase{

    private final int number;
    private final int capacity;

    public RoomDTO(int id, int number, int capacity) {
        super(id);
        this.number = number;
        this.capacity = capacity;
    }

    public RoomDTO(int id) {
        this(id, 0,0);
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RoomDTO room = (RoomDTO) obj;
        return getId() == room.getId() &&
                number == room.number &&
                capacity == room.capacity;
    }
}
