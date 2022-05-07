/*
 * Represents an entity in a scheme called Student
 */

package repositories.implementations;

import dtos.StudentDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.IStudentRepository;
import java.time.LocalDate;
import java.sql.*;
import java.util.*;
import java.sql.Date;

public final class StudentRepository extends RepositoryBase<StudentDTO> implements IStudentRepository {

    private static final String TABLE_NAME = "Student";

    private static final String STUDENT_ID = "IdStudent";
    private static final String STUDENT_INDEX_NUMBER = "IndexNumber";
    private static final String STUDENT_DATE_OF_ENROLLMENT = "DateOfEnrollment";

    public StudentRepository(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(StudentDTO student) {
        return Arrays.asList(student.getIndexNumber(), student.getDateOfEnrollment());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(STUDENT_ID, STUDENT_INDEX_NUMBER, STUDENT_DATE_OF_ENROLLMENT);
    }

    @Override
    protected StudentDTO getDTO(ResultSet resultSet) throws SQLException {
        int studentId = resultSet.getInt(1);
        String studentIndexNumber = resultSet.getString(2);
        LocalDate studentDateOfEnrollment = resultSet.getDate(3).toLocalDate();
        return new StudentDTO(studentId, studentIndexNumber, studentDateOfEnrollment);
    }

    @Override
    public List<StudentDTO> findEnrolledEarlierThen(LocalDate date) {
        List<StudentDTO> students = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + STUDENT_ID +
                    ", " + STUDENT_INDEX_NUMBER + ", " + STUDENT_DATE_OF_ENROLLMENT +
                    " FROM " + TABLE_NAME +
                    " WHERE " + STUDENT_DATE_OF_ENROLLMENT + " < ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getRow() != 0)
                students.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return students;
    }
}
