package repositories.implementations;

import dtos.GroupDTO;
import org.junit.*;
import repositories.RepositoryBaseTest;
import repositories.interfaces.IGroupRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public final class GroupRepositoryTest extends RepositoryBaseTest<GroupDTO, IGroupRepository, GroupRepository> {

    private GroupDTO testGroup;

    public GroupRepositoryTest()
            throws NoSuchMethodException, SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(GroupRepository.class);
    }

    @Test
    public void add(){
        testGroup = new GroupDTO(1, 10, 't');
        super.add(testGroup);
    }

    @Test
    public void update(){
        testGroup = new GroupDTO(1, 15, 'l');
        super.update(testGroup);
    }

    @Test
    public void delete() {
        testGroup = new GroupDTO(1, 15, 'l');
        super.delete(testGroup);
    }

    @Test
    public void findById(){
        testGroup = new GroupDTO(1, 10, 't');
        super.findById(testGroup, testGroup.getId());
    }

    @Test
    public void findByType(){
        char groupType = 't';
        List<GroupDTO> allGroups = new ArrayList<>();
        List<GroupDTO> rightAnswersList = new ArrayList<>();
        allGroups.add(new GroupDTO(1, 10, 't'));
        allGroups.add(new GroupDTO(2, 10, 'l'));
        allGroups.add(new GroupDTO(3, 11, 't'));
        for (GroupDTO group : allGroups) {
            getRepository().add(group);
            if (group.getType() == groupType)
                rightAnswersList.add(group);
        }
        List<GroupDTO> resultList = getRepository().findByType(String.valueOf(groupType));
        for (GroupDTO group : allGroups)
            getRepository().delete(group);
        Assert.assertEquals(rightAnswersList, resultList);
    }
}
