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

    @GetMapping("")
    public List<AssetDto> getAll(@RequestParam(required = false) String text) {
        if(text != null) {
            return assetService.findAllByNameOrSerialNumber(text);
        } else {
            return assetService.findAll();
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public AssetDto save(@RequestBody AssetDto asset) {
        return assetService.save(asset);
    }
}