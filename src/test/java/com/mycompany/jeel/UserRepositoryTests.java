package com.mycompany.jeel;
import com.mycompany.jeel.user.User;
import com.mycompany.jeel.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Optional;
//import org.springframework.transaction.annotation.Transactional;

//import java.util.Collections;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

@Autowired private UserRepository repo;
@Test
    public void testAddNew() {
    User user = new User();
    user.setEmail("dhenuu@email.com");
    user.setPassword("dhyenu");
    user.setFirstName("dhyana");
    user.setLastName("Patadiya");
    User savedUser = repo.save(user);

    Assertions.assertThat(savedUser).isNotNull();
    Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
}
@Test

    public void testListAll() {

    Iterable<User> users = repo.findAll();
    Assertions.assertThat(users).hasSizeGreaterThan(0);

    for (User user : users) {
        System.out.println(user);
    }
}

@Test

public void testUpdate() {
Integer userId=1;
Optional<User> optionalUser = repo.findById(userId);
User user1 =optionalUser.get();
user1.setPassword("hello");
repo.save(user1);

User updatedUser = repo.findById(userId).get();

Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello");

}
@Test
    public void testGet()
{
    Integer userId=1;
    Optional<User> optionalUser= repo.findById(userId);

    Assertions.assertThat(optionalUser).isPresent();
   System.out.println(optionalUser.get());

}
@Test
    public void testDelete()
{
    Integer userId=5;
    repo.deleteById(Long.valueOf(userId));
    Optional<User> optionalUser= repo.findById(userId);
    Assertions.assertThat(optionalUser).isPresent();
}

}

