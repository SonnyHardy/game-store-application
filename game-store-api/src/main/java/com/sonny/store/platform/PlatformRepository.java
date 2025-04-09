package com.sonny.store.platform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatformRepository extends JpaRepository<String, Platform> {

    @Query("""
        SELECT p FROM Platform p
        WHERE p.console IN :consoles
    """)
    List<Platform> findAllByConsoleIn(@Param("consoles") List<Console> selectedConsoles);
}
