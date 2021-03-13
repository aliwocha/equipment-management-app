package pl.javastart.equipy.components.category;

class CategoryMapper {

    static CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    static Category toEntity(CategoryDto category) {
        Category entity = new Category();
        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        return entity;
    }
}
