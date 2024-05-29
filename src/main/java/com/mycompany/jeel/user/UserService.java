package com.mycompany.jeel.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
@Autowired private UserRepository repo;
public List<User> listAll() {
    return (List<User>) repo.findAll();


}


    public void save(User user) {
    repo.save(user);
    }

    public User findById(int id) throws UserNotFoundException {
    Optional<User> result=repo.findById(id);

    if(result.isPresent())
    {
        return result.get();
    }
    throw new UserNotFoundException("Could not find any users with id" +id);
    }

    public User get(Integer id) {
        Optional<User> userOptional = repo.findById(id);
        return userOptional.orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public void delete(Integer id) throws UserNotFoundException {
        Optional<User> userOptional = repo.findById(id);
        if (userOptional.isPresent()) {
            repo.deleteById(Long.valueOf(id));
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

}
