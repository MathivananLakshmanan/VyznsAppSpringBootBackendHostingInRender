package com.example.vyzns.Repository;

import com.example.vyzns.Entity.Items;
import com.example.vyzns.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IteamRepository  extends JpaRepository<Items,Long> {

        List<Items> findByOwner(User owner);
        Optional<Items> findById(Long id);

    List<Items> findByTitleContainingIgnoreCase(String title);
}
