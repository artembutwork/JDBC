/*
 * Represents an entity in a scheme called Lesson
 */

package repositories.implementations;

import dtos.LessonDTO;
import exception.CustomException;
import repositories.RepositoryBase;
import repositories.interfaces.ILessonRepository;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.*;

public final class LessonRepository extends RepositoryBase<LessonDTO> implements ILessonRepository {

    private static final String TABLE_NAME = "Lesson";

    private static final String LESSON_ID = "IdLesson";
    private static final String LESSON_TEACHER_ID = "IdTeacher";
    private static final String LESSON_SUBJECT_ID = "IdSubject";
    private static final String LESSON_ROOM_ID = "IdRoom";
    private static final String LESSON_GROUP_ID = "IdGroup";
    private static final String LESSON_START_TIME = "StartTime";
    private static final String LESSON_END_TIME = "EndTime";

    public LessonRepository(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected List<Object> getParameters(LessonDTO lessonDTO) {
        return Arrays.asList(lessonDTO.getIdTeacher(), lessonDTO.getIdSubject(), lessonDTO.getIdRoom(),
                lessonDTO.getIdGroup(), lessonDTO.getStartTime(), lessonDTO.getEndTime());
    }

    @Override
    protected List<String> getColumnsName() {
        return Arrays.asList(LESSON_ID, LESSON_TEACHER_ID, LESSON_SUBJECT_ID,
                LESSON_ROOM_ID, LESSON_GROUP_ID, LESSON_START_TIME, LESSON_END_TIME);
    }

    @Override
    protected LessonDTO getDTO(ResultSet resultSet) throws SQLException {
        int lessonId = resultSet.getInt(1);
        int lessonTeacherId = resultSet.getInt(2);
        int lessonSubjectId = resultSet.getInt(3);
        int lessonRoomId = resultSet.getInt(4);
        int lessonGroupId = resultSet.getInt(5);
        LocalDateTime lessonStartTime = resultSet.getTimestamp(6).toLocalDateTime();
        LocalDateTime lessonEndTime = resultSet.getTimestamp(7).toLocalDateTime();
        return new LessonDTO(lessonId, lessonTeacherId, lessonSubjectId,
                                lessonRoomId, lessonGroupId, lessonStartTime, lessonEndTime);
    }

    @Override
    public List<LessonDTO> findByLessonDuration(int minutes) {
        List<LessonDTO> lessons = new ArrayList<>();
        try {
            Connection connection = getConnection();
            final String findCommand = "SELECT " + LESSON_ID + ", " + LESSON_TEACHER_ID + ", " + LESSON_SUBJECT_ID +
                    ", " + LESSON_ROOM_ID + ", " + LESSON_GROUP_ID + ", " + LESSON_START_TIME + ", " + LESSON_END_TIME +
                    " FROM " + TABLE_NAME +
                    " WHERE DATEDIFF(MINUTE, " + LESSON_START_TIME + ", " + LESSON_END_TIME + ")" + " = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(findCommand);
            preparedStatement.setInt(1, minutes);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getRow() != 0)
                lessons.add(getDTO(resultSet));
        } catch (Throwable exception){
            String message = exception.getMessage();
            throw new CustomException(message, exception);
        }
        return lessons;
    }
}