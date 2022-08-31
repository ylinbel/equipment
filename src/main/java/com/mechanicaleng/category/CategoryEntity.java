package com.mechanicaleng.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String layer1;

    @Enumerated(value = EnumType.STRING)
    private ParentLayerEnum parentLayerEnum;

    public static CategoryEntity fromDto(CategoryDto categoryDto) {
        return CategoryEntity.builder().name(categoryDto.getName()).layer1(categoryDto.getLayer1()).parentLayerEnum(categoryDto.getParentLayerEnum()).build();
    }

    public CategoryDto toDto() {
        return CategoryDto.builder().id(this.getId()).name(this.getName()).layer1(this.getLayer1()).parentLayerEnum(this.getParentLayerEnum()).build();
    }

    public void updateFromDto(CategoryDto categoryDto) {
        this.setName(categoryDto.getName());
        this.setLayer1(categoryDto.getLayer1());
        this.setParentLayerEnum(categoryDto.getParentLayerEnum());
    }

}
