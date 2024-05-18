package com.example.heavytoolsapp.repos;

import com.example.heavytoolsapp.models.ToolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolTypeRepo extends JpaRepository<ToolType, Long> {

    ToolType findToolTypeByName(String toolCode);
}
