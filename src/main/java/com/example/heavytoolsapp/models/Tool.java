package com.example.heavytoolsapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TOOL", uniqueConstraints = @UniqueConstraint(columnNames = {"toolType_id", "brand_id"}))
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tool_code", nullable = false, unique = true)
    private String toolCode;

    @JoinColumn(name = "TOOL_TYPE_ID", nullable = false)
    @ManyToOne
    private ToolType toolType;

    @JoinColumn(name = "BRAND_ID", nullable = false)
    @ManyToOne
    private ToolBrand brand;


}
