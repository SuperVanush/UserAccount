import com.useraccount.dao.impl.UserStorage;
import com.useraccount.model.User;
import com.useraccount.service.impl.BillService;
import com.useraccount.service.impl.UserService;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest extends TestCase {
UserService subj;
UserStorage userStorage;
BillService billService;

@Before
    public void setUp (){
userStorage= mock (UserStorage.class);
billService = mock(BillService.class);
subj = new UserService(userStorage,billService);
}
@Test
public void test_notFindUser(){
when(userStorage.findByLogin("qqq")).thenReturn(null);
User user= subj.findUserByLogin("qqq");
assertNotNull(user);
}
}
