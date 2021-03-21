package pl.javastart.equipy.components.assignment.assets;

import pl.javastart.equipy.components.assignment.common.AssignmentDto;

public class AssetAssignmentDto extends AssignmentDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
