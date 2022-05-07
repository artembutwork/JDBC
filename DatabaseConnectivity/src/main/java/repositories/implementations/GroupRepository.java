/*
 * Represents an entity in a scheme called Group
 */

package repositories.implementations;

import dtos.GroupDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.IGroupRepository;
import java.sql.*;
import java.util.*;

public final class GroupRepository extends RepositoryBase<GroupDTO> implements IGroupRepository {

    private static final String TABLE_NAME = "\"Group\"";

    private static final String GROUP_ID = "IdGroup";
    private static final String GROUP_NUMBER = "Number";
    private static final String GROUP_TYPE = "Type";

    public GroupRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(GroupDTO group) {
        return Arrays.asList(group.getNumber(), group.getType());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(GROUP_ID, GROUP_NUMBER, GROUP_TYPE);
    }

    @Override
    protected GroupDTO getDTO(ResultSet resultSet) throws SQLException {
        int groupId = resultSet.getInt(1);
        int groupNumber = resultSet.getInt(2);
        char groupType = resultSet.getString(3).charAt(0);
        return new GroupDTO(groupId, groupNumber, groupType);
    }

    @Override
    public List<GroupDTO> findByType(String type) {
        List<GroupDTO> groups = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + GROUP_ID + ", " + GROUP_NUMBER + ", " + GROUP_TYPE +
                    " FROM " + TABLE_NAME +
                    " WHERE " + GROUP_TYPE + " = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            preparedStatement.setString(1, type.substring(0,1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getRow() != 0)
                groups.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return groups;
    }
}
