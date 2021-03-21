package pl.javastart.equipy.components.assignment.users;

import pl.javastart.equipy.components.assignment.common.AssignmentDto;

public class UserAssignmentDto extends AssignmentDto {

    private Long assetId;
    private String assetName;
    private String assetSerialNumber;

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetSerialNumber() {
        return assetSerialNumber;
    }

    public void setAssetSerialNumber(String assetSerialNumber) {
        this.assetSerialNumber = assetSerialNumber;
    }
}
