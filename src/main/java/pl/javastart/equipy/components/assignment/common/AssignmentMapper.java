package pl.javastart.equipy.components.assignment.common;

public interface AssignmentMapper<T> {

    AssignmentDto toDto(Assignment assignment);

    Assignment toEntity(T assignment);
}
