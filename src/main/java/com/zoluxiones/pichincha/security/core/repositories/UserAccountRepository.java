package com.zoluxiones.pichincha.security.core.repositories;


import com.zoluxiones.pichincha.security.core.entities.UserAccount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserAccountRepository extends ReactiveCrudRepository<UserAccount, Long> {
    Mono<UserAccount> findByUsernameOrEmail(String username, String email);
}
