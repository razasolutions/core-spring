package net.raza.core.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.raza.core.SpringBootWebApplication;
import net.raza.core.enums.RoleEnum;
import net.raza.core.models.User;
import net.raza.core.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootWebApplication.class})
@EnableWebSecurity
public class UserServiceTest {

	@Autowired
    private UserService userService;

    @Test
    public void testUserCRUD(){
    	
        // setup user
        User user = new User();
        user.setUsername("nomedeusuario");
        user.setEmail("email@email.com");
        user.setRole(RoleEnum.USER);
        user.setPassword(BCrypt.hashpw("senha1234", BCrypt.gensalt(12)));

        // save user, verify has ID value after save
        assertNull(user.getId()); //null before save
        userService.save(user);
        assertNotNull(user.getId()); //not null after save
        assertNotNull(user.getUsername());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPassword());

        //fetch from DB
        User fetchedUser = userService.findById(user.getId());

        //should not be null and should be equal
        assertNotNull(fetchedUser);
        assertEquals(user.getId(), fetchedUser.getId());
        assertEquals(user.getUsername(), fetchedUser.getUsername());
        assertEquals(user.getEmail(), fetchedUser.getEmail());
        assertEquals(user.getPassword(), fetchedUser.getPassword());

        // update user and save
        fetchedUser.setUsername("novonomedeusuario");
        fetchedUser.setEmail("novomail@novomail.com");
        fetchedUser.setPassword(BCrypt.hashpw("novasenha1234", BCrypt.gensalt(12)));
        userService.save(fetchedUser);

        //get from DB, should be updated
        User fetchedUpdatedUser = userService.findById(fetchedUser.getId());
        assertEquals(fetchedUpdatedUser.getId(), fetchedUser.getId());
        assertEquals(fetchedUpdatedUser.getUsername(), fetchedUser.getUsername());
        assertEquals(fetchedUpdatedUser.getEmail(), fetchedUser.getEmail());
        assertEquals(fetchedUpdatedUser.getPassword(), fetchedUser.getPassword());

        //verify count of users in DB
        long count = userService.count();
        userService.delete(fetchedUpdatedUser);
        long newCount = userService.count();
        assertEquals(count, newCount+1);
    }
}
