package com.pwel.allegrotrader.user.infrastructure;

import com.pwel.allegrotrader.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
