package net.raza.core.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.raza.core.SpringBootWebApplication;
import net.raza.core.enums.RoleEnum;
import net.raza.core.models.User;
import net.raza.core.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = {SpringBootWebApplication.class})
@EnableWebSecurity
public class UserServiceTest {

	@Autowired
    private UserService userService;

    @Test
    public void testUserCRUD(){
    	
        // setup user
        User user = new User();
        user.setUsername("username");
        user.setEmail("email@email.com");
        user.setRole(RoleEnum.USER);
        user.setPassword("password1234");
        
        // save null user
        assertNull(userService.save(null));

        // save user, verify has ID value after save
        assertNull(user.getId()); //null before save
        user = userService.save(user);
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

        // update null user
        assertNull(userService.update(null));
        
        // update user and save
        fetchedUser.setUsername("newusername");
        fetchedUser.setEmail("newmail@newmail.com");
        fetchedUser.setPassword("newpassword1234");
        fetchedUser = userService.update(fetchedUser);
        
        // get from DB, should be updated
        User fetchedUpdatedUser = userService.findById(fetchedUser.getId());
        assertEquals(fetchedUpdatedUser.getId(), fetchedUser.getId());
        assertEquals(fetchedUpdatedUser.getUsername(), fetchedUser.getUsername());
        assertEquals(fetchedUpdatedUser.getEmail(), fetchedUser.getEmail());
        assertEquals(fetchedUpdatedUser.getPassword(), fetchedUser.getPassword());
        
        // update without password changing
        fetchedUser.setUsername("mudeionome");
        fetchedUser.setEmail("mudeimail@mail.com");
        fetchedUser.setPassword("");
        userService.update(fetchedUser);
        
        // get from DB, should be updated
        fetchedUpdatedUser = userService.findByUsername(fetchedUser.getUsername());
        assertEquals(BCrypt.checkpw("newpassword1234", fetchedUpdatedUser.getPassword()), true);
        
        // update new user
        assertNull(userService.update(new User()));

        //verify count of users in DB
        long count = userService.count();
        userService.delete(fetchedUpdatedUser);
        long newCount = userService.count();
        assertEquals(count, newCount+1);
    }
    
    @Test
    public void testValidation(){
    	User user = new User();
    	assertEquals(userService.invalidUser(user), true);
    	
    	user.setUsername("username");
        user.setEmail("email@email.com");
        user.setRole(RoleEnum.USER);
        user.setPassword(BCrypt.hashpw("password1234", BCrypt.gensalt(12)));
        assertEquals(userService.invalidUser(user), false);
        
        user.setUsername(null);
        assertEquals(userService.invalidUser(user), true);
        user.setUsername("");
        assertEquals(userService.invalidUser(user), true);
        user.setUsername("s");
        assertEquals(userService.invalidUser(user), true);
        user.setUsername("username");
        assertEquals(userService.invalidUser(user), false);
        
        user.setEmail(null);
        assertEquals(userService.invalidUser(user), true);
        user.setEmail("emailemail.com");
        assertEquals(userService.invalidUser(user), true);
        user.setEmail("");
        assertEquals(userService.invalidUser(user), true);
        user.setEmail("email@emailcom");
        assertEquals(userService.invalidUser(user), true);
        user.setEmail("email@email.c");
        assertEquals(userService.invalidUser(user), true);
        user.setEmail("email@email.com");
        assertEquals(userService.invalidUser(user), false);
        
        user.setRole(null);
        assertEquals(userService.invalidUser(user), true);
        user.setRole(RoleEnum.ADMIN);
        assertEquals(userService.invalidUser(user), false);
        
        user.setPassword(null);
        assertEquals(userService.invalidUser(user), true);
        user.setPassword("");
        assertEquals(userService.invalidUser(user), true);
        user.setPassword("fsafs");
        assertEquals(userService.invalidUser(user), true);
        user.setPassword("fsafssaf");
        assertEquals(userService.invalidUser(user), false);
        
    }
}
