package br.com.mytravels.apimytravels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mytravels.apimytravels.data.model.Transportadora;
import br.com.mytravels.apimytravels.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	User findByUsername(@Param("userName") String userName);
	
	@Transactional(readOnly=true)
	List<User> findByTransportadora(Optional<Transportadora> transportadora);

}