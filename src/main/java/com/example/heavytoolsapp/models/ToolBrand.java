package com.example.heavytoolsapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "tool_brand")
public class ToolBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public ToolBrand setName(String name) {
        this.name = name;
        return this;
    }

    public ToolBrand(String name) {
        this.name = name;
    }
}
