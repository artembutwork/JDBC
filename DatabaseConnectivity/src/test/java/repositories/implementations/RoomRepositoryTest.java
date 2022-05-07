package repositories.implementations;

import dtos.RoomDTO;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.IRoomRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public final class RoomRepositoryTest extends RepositoryBaseTest<RoomDTO, IRoomRepository, RoomRepository> {

    private RoomDTO testRoom;

    public RoomRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(RoomRepository.class);
    }

    @Test
    public void add(){
        testRoom = new RoomDTO(1, 10, 200);
        super.add(testRoom);
    }

    @Test
    public void update(){
        testRoom = new RoomDTO(1, 10, 300);
        super.update(testRoom);
    }

    @Test
    public void delete(){
        testRoom = new RoomDTO(1, 10, 300);
        super.delete(testRoom);
    }

    @Test
    public void findById(){
        testRoom = new RoomDTO(1, 10, 200);
        super.findById(testRoom, testRoom.getId());
    }

    @Test
    public void findByCapacity(){
        int lowerBound = 130, upperBound = 200;
        List<RoomDTO> allRooms = new ArrayList<>();
        List<RoomDTO> rightAnswersList = new ArrayList<>();
        allRooms.add(new RoomDTO(1, 10, 100));
        allRooms.add(new RoomDTO(2, 11, 150));
        allRooms.add(new RoomDTO(3, 12, 200));
        for (RoomDTO room : allRooms) {
            getRepository().add(room);
            if (room.getCapacity() >= lowerBound && room.getCapacity() <= upperBound)
                rightAnswersList.add(room);
        }
        List<RoomDTO> resultList = getRepository().findByCapacity(lowerBound, upperBound);
        for (RoomDTO room : allRooms)
            getRepository().delete(room);
        Assert.assertEquals(rightAnswersList, resultList);
    }
}