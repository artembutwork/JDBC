/*
 * Represents an entity in a scheme called Subject
 */

package repositories.implementations;

import dtos.SubjectDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.ISubjectRepository;
import java.sql.*;
import java.util.*;

public final class SubjectRepository extends RepositoryBase<SubjectDTO> implements ISubjectRepository {

    private static final String TABLE_NAME = "Subject";

    private static final String SUBJECT_ID = "IdSubject";
    private static final String SUBJECT_NAME = "Name";
    private static final String SUBJECT_ACRONYM = "Acronym";

    public SubjectRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(SubjectDTO subject) {
        return Arrays.asList(subject.getName(), subject.getAcronym());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(SUBJECT_ID, SUBJECT_NAME, SUBJECT_ACRONYM);
    }

    @Override
    protected SubjectDTO getDTO(ResultSet resultSet) throws SQLException {
        int subjectId = resultSet.getInt(1);
        String subjectName = resultSet.getString(2);
        String subjectAcronym = resultSet.getString(3);
        return new SubjectDTO(subjectId, subjectName, subjectAcronym);
    }

    @Override
    public List<SubjectDTO> findThreeCharactersAcronym() {
        List<SubjectDTO> subjects = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + SUBJECT_ID + ", " + SUBJECT_NAME + ", " + SUBJECT_ACRONYM +
                    " FROM " + TABLE_NAME +
                    " WHERE " + SUBJECT_ACRONYM + " LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            preparedStatement.setString(1, "___");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getRow() != 0)
                subjects.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return subjects;
    }
}
