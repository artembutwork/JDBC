package repositories.implementations;

import dtos.*;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.ILessonRepository;
import repositories.interfaces.IRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.*;
import java.util.*;

public final class LessonRepositoryTest extends RepositoryBaseTest<LessonDTO, ILessonRepository, LessonRepository> {

    private final IRepository<PersonDTO> peopleRepository;
    private final IRepository<TeacherDTO> teachersRepository;
    private final IRepository<SubjectDTO> subjectsRepository;
    private final IRepository<RoomDTO> roomsRepository;
    private final IRepository<GroupDTO> groupsRepository;

    private LessonDTO testLesson;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public LessonRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(LessonRepository.class);
        Connection connection = getRepository().getConnection();
        peopleRepository = new PersonRepository(connection);
        teachersRepository = new TeacherRepository(connection);
        subjectsRepository = new SubjectRepository(connection);
        roomsRepository = new RoomRepository(connection);
        groupsRepository = new GroupRepository(connection);
    }

    @Test
    public void add(){
        startTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 12, 0,0);
        endTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 13, 30,0);
        testLesson = new LessonDTO(1, 1,1,1,1, startTime, endTime);
        addDTOsIfAbsent(testLesson);
        super.add(testLesson);
    }

    @Test
    public void update(){
        startTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 10, 15,0);
        endTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 11, 45,0);
        testLesson = new LessonDTO(1, 1,1,1,1, startTime, endTime);
        super.update(testLesson);
    }

    @Test
    public void delete(){
        startTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 10, 15,0);
        endTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 11, 45,0);
        testLesson = new LessonDTO(1, 1,1,1,1, startTime, endTime);
        super.delete(new LessonDTO(1, 1,1,1,1, startTime, endTime));
        deleteAddedDTOs(testLesson);
    }

    @Test
    public void findById(){
        startTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 12, 0,0);
        endTime = LocalDateTime.of(2022, Month.OCTOBER, 15, 13, 30,0);
        testLesson = new LessonDTO(1, 1,1,1,1, startTime, endTime);
        addDTOsIfAbsent(testLesson);
        super.findById(testLesson, testLesson.getId());
        deleteAddedDTOs(testLesson);
    }

    @Test
    public void findByLessonDuration(){
        int minutes = 90;
        List<LessonDTO> lessons = new ArrayList<>();
        List<LessonDTO> rightAnswersList = new ArrayList<>();
        lessons.add(new LessonDTO(1, 1,1,1,1,
                LocalDateTime.of(2022, Month.OCTOBER, 15, 12, 0, 0),
                LocalDateTime.of(2022, Month.OCTOBER, 15, 13, 30, 0)));
        lessons.add(new LessonDTO(2, 2,2,2,2,
                LocalDateTime.of(2022, Month.OCTOBER, 15, 12, 0, 0),
                LocalDateTime.of(2022, Month.OCTOBER, 16, 13, 30, 0)));
        lessons.add(new LessonDTO(3, 3,3,3,3,
                LocalDateTime.of(2022, Month.OCTOBER, 16, 12, 0, 0),
                LocalDateTime.of(2022, Month.OCTOBER, 16, 13, 0, 0)));
        lessons.add(new LessonDTO(4, 4,4,4,4,
                LocalDateTime.of(2022, Month.OCTOBER, 17, 10, 15, 0),
                LocalDateTime.of(2022, Month.OCTOBER, 17, 11, 45, 0)));
        for (LessonDTO lesson : lessons) {
            addDTOsIfAbsent(lesson);
            getRepository().add(lesson);
            Duration duration = Duration.between(lesson.getStartTime(), lesson.getEndTime());
            if (duration.toMinutes() == minutes)
                rightAnswersList.add(lesson);
        }
        List<LessonDTO> resultList = getRepository().findByLessonDuration(minutes);
        for (LessonDTO lesson : lessons) {
            getRepository().delete(lesson);
            deleteAddedDTOs(lesson);
        }
        Assert.assertEquals(rightAnswersList, resultList);
    }

    private void addDTOsIfAbsent(LessonDTO testLesson){
        if(teachersRepository.findById(testLesson.getIdTeacher()) == null){
            if(peopleRepository.findById(testLesson.getIdTeacher()) == null)
                peopleRepository.add(new PersonDTO(testLesson.getIdTeacher(), LocalDate.of(1970, Month.JANUARY,1)));
            teachersRepository.add(new TeacherDTO(testLesson.getIdTeacher()));
        }
        if(subjectsRepository.findById(testLesson.getIdSubject()) == null)
            subjectsRepository.add(new SubjectDTO(testLesson.getIdSubject()));
        if(roomsRepository.findById(testLesson.getIdRoom()) == null)
            roomsRepository.add(new RoomDTO(testLesson.getIdRoom()));
        if(groupsRepository.findById(testLesson.getIdGroup()) == null)
            groupsRepository.add(new GroupDTO(testLesson.getIdGroup()));
    }

    private void deleteAddedDTOs(LessonDTO testLesson){
        teachersRepository.delete(new TeacherDTO(testLesson.getIdTeacher()));
        peopleRepository.delete(new PersonDTO(testLesson.getIdTeacher(), LocalDate.of(1970, Month.JANUARY,1)));
        subjectsRepository.delete(new SubjectDTO(testLesson.getIdSubject()));
        roomsRepository.delete(new RoomDTO(testLesson.getIdRoom()));
        groupsRepository.delete(new GroupDTO(testLesson.getIdGroup()));
    }
}
