package com.example.heavytoolsapp.repos;

import com.example.heavytoolsapp.models.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolCodeRepo extends JpaRepository<Tool, Long>{

    Tool findByToolCode(String toolCode);

    boolean existsByToolCode(String toolCode);

}
