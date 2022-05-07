/*
 * Represents an entity in a scheme called Teacher
 */

package repositories.implementations;

import dtos.TeacherDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.ITeacherRepository;
import java.sql.*;
import java.util.*;

public final class TeacherRepository extends RepositoryBase<TeacherDTO> implements ITeacherRepository {

    private static final String TABLE_NAME = "Teacher";

    private static final String TEACHER_ID = "IdTeacher";
    private static final String TEACHER_DEGREE = "AcademicRank";

    public TeacherRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(TeacherDTO teacher) {
        return Collections.singletonList(teacher.getAcademicRank());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(TEACHER_ID, TEACHER_DEGREE);
    }

    @Override
    protected TeacherDTO getDTO(ResultSet resultSet) throws SQLException {
        int teacherId = resultSet.getInt(1);
        String teacherAcademicRank = resultSet.getString(2);
        return new TeacherDTO(teacherId, teacherAcademicRank);
    }

    @Override
    public List<TeacherDTO> findWithoutRank() {
        List<TeacherDTO> teachers = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + TEACHER_ID + ", " + TEACHER_DEGREE +
                    " FROM " + TABLE_NAME +
                    " WHERE " + TEACHER_DEGREE + " IS NULL;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getRow() != 0)
                teachers.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return teachers;
    }
}
