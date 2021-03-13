package pl.javastart.equipy.components.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class AssetService {

    private AssetRepository assetRepository;
    private AssetMapper assetMapper;

    @Autowired
    AssetService(AssetRepository assetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }

    List<AssetDto> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    List<AssetDto> findAllByNameOrSerialNumber(String text) {
        return assetRepository.findAllByNameOrSerialNumber(text)
                .stream()
                .map(assetMapper::toDto)
                .collect(Collectors.toList());
    }

    AssetDto save(AssetDto asset) {
        if(asset.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");

        Optional<Asset> assetBySerialNumber = assetRepository.findBySerialNumber(asset.getSerialNumber());
        if(assetBySerialNumber.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Wyposażenie z takim numerem seryjnym już istnieje");
        }

        Asset assetEntity = assetMapper.toEntity(asset);
        Asset savedAsset = assetRepository.save(assetEntity);
        return assetMapper.toDto(savedAsset);
    }
}
