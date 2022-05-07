/*
 * Represents a record in entity in a scheme called Lesson
 */

package dtos;

import java.time.LocalDateTime;

public final class LessonDTO extends DTOBase{

    private final int idTeacher;
    private final int idSubject;
    private final int idRoom;
    private final int idGroup;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public LessonDTO(int id, int idTeacher, int idSubject, int idRoom, int idGroup, LocalDateTime startTime, LocalDateTime endTime) {
        super(id);
        this.idTeacher = idTeacher;
        this.idSubject = idSubject;
        this.idRoom = idRoom;
        this.idGroup = idGroup;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LessonDTO lesson = (LessonDTO) obj;
        return getId() == lesson.getId() &&
                idTeacher == lesson.idTeacher &&
                idSubject == lesson.idSubject &&
                idRoom == lesson.idRoom &&
                idGroup == lesson.idGroup &&
                startTime.equals(lesson.startTime) &&
                endTime.equals(lesson.endTime);
    }
}
