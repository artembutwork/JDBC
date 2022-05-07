/*
 * Represents an entity in a scheme called Grade
 */

package repositories.implementations;

import dtos.GradeDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.IGradeRepository;
import java.sql.*;
import java.util.*;

public final class GradeRepository extends RepositoryBase<GradeDTO> implements IGradeRepository {

    private static final String TABLE_NAME = "Grade";

    private static final String GRADE_ID = "IdGrade";
    private static final String GRADE_VALUE = "Value";
    private static final String GRADE_VALUE_IN_WORDS = "ValueInWords";

    public GradeRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(GradeDTO grade) {
        return Arrays.asList(grade.getValue(), grade.getValueInWords());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(GRADE_ID, GRADE_VALUE, GRADE_VALUE_IN_WORDS);
    }

    @Override
    protected GradeDTO getDTO(ResultSet resultSet) throws SQLException {
        int gradeId = resultSet.getInt(1);
        int gradeValue = resultSet.getInt(2);
        String gradeValueInWords = resultSet.getString(3);
        return new GradeDTO(gradeId, gradeValue, gradeValueInWords);
    }

    @Override
    public List<GradeDTO> findGreaterThen(int gradeValue) {
        List<GradeDTO> grades = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + GRADE_ID + ", " + GRADE_VALUE + ", " + GRADE_VALUE_IN_WORDS +
                    " FROM " + TABLE_NAME +
                    " WHERE " + GRADE_VALUE + " > ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            preparedStatement.setInt(1, gradeValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next() && resultSet.getRow() != 0)
                grades.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return grades;
    }
}