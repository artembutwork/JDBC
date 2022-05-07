/*
 * Represents an entity in a scheme called Room
 */

package repositories.implementations;

import dtos.RoomDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.IRoomRepository;
import java.sql.*;
import java.util.*;

public final class RoomRepository extends RepositoryBase<RoomDTO> implements IRoomRepository {

    private static final String TABLE_NAME = "Room";

    private static final String ROOM_ID = "IdRoom";
    private static final String ROOM_NUMBER = "Number";
    private static final String ROOM_CAPACITY = "Capacity";

    public RoomRepository(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(RoomDTO room) {
        return Arrays.asList(room.getNumber(), room.getCapacity());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(ROOM_ID, ROOM_NUMBER, ROOM_CAPACITY);
    }

    @Override
    protected RoomDTO getDTO(ResultSet resultSet) throws SQLException {
        int roomId = resultSet.getInt(1);
        int roomNumber = resultSet.getInt(2);
        int roomCapacity = resultSet.getInt(3);
        return new RoomDTO(roomId, roomNumber, roomCapacity);
    }

    @Override
    public List<RoomDTO> findByCapacity(int lowerBound, int upperBound) {
        if(lowerBound > upperBound)
            return null;
        List<RoomDTO> rooms = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + ROOM_ID + ", " + ROOM_NUMBER + ", " + ROOM_CAPACITY +
                    " FROM " + TABLE_NAME +
                    " WHERE " + ROOM_CAPACITY + " BETWEEN ? AND ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            preparedStatement.setInt(1, lowerBound);
            preparedStatement.setInt(2, upperBound);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getRow() != 0)
                rooms.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return rooms;
    }
}
