package pl.javastart.equipy.components.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{id}")
    public AssetDto getOne(@PathVariable Long id) {
        return assetService.findById(id);
    }

    @GetMapping("")
    public List<AssetDto> getAll(@RequestParam(required = false) String text) {
        if(text != null) {
            return assetService.findAllByNameOrSerialNumber(text);
        } else {
            return assetService.findAll();
        }
    }

    @GetMapping("/{assetId}/assignments")
    public List<AssetAssignmentDto> getAssetAssignments(@PathVariable Long assetId) {
        return assetService.getAssetAssignments(assetId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public AssetDto save(@RequestBody AssetDto asset) {
        return assetService.save(asset);
    }

    @PutMapping("/{id}")
    public AssetDto update(@RequestBody AssetDto asset, @PathVariable Long id) {
        return assetService.update(asset, id);
    }
}