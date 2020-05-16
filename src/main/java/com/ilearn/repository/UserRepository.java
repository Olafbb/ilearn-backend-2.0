package com.ilearn.repository;

import com.ilearn.domain.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    User findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findAllByEmail(String email);

    @Override
    User save(User user);

    @Override
    void deleteById(Long id);

    @Override
    long count();

    User findByStudentId(Long id);
}
