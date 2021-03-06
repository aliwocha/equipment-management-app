package pl.javastart.equipy.components.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.components.asset.exceptions.AssetNotFoundException;
import pl.javastart.equipy.components.asset.exceptions.DuplicateSerialNumberException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class AssetService {

    private AssetRepository assetRepository;
    private AssetMapper assetMapper;
    private AssetAssignmentMapper assetAssignmentMapper;

    @Autowired
    public AssetService(AssetRepository assetRepository, AssetMapper assetMapper,
                        AssetAssignmentMapper assetAssignmentMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
        this.assetAssignmentMapper = assetAssignmentMapper;
    }

    AssetDto findById(Long id) {
        return assetRepository.findById(id)
                .map(assetMapper::toDto)
                .orElseThrow(AssetNotFoundException::new);
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

    List<AssetAssignmentDto> getAssetAssignments(Long assetId) {
        return assetRepository.findById(assetId)
                .map(Asset::getAssignments)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .map(assetAssignmentMapper::toDto)
                .collect(Collectors.toList());
    }

    AssetDto save(AssetDto asset) {
        if(asset.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie mo??e mie?? ustawionego id");

        Optional<Asset> assetBySerialNumber = assetRepository.findBySerialNumber(asset.getSerialNumber());
        if(assetBySerialNumber.isPresent()) {
            throw new DuplicateSerialNumberException();
        }

        return mapAndSaveAsset(asset);
    }

    AssetDto update(AssetDto asset, Long id) {
        if(!id.equals(asset.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id aktualizowanego obiektu jest r????ne od id w ??cie??ce URL");
        }

        Optional<Asset> assetBySerialNumber = assetRepository.findBySerialNumber(asset.getSerialNumber());
        assetBySerialNumber.ifPresent(a -> {
            if(!a.getId().equals(asset.getId()))
                throw new DuplicateSerialNumberException();
        });

        return mapAndSaveAsset(asset);
    }

    private AssetDto mapAndSaveAsset(AssetDto asset) {
        Asset assetEntity = assetMapper.toEntity(asset);
        Asset savedAsset = assetRepository.save(assetEntity);
        return assetMapper.toDto(savedAsset);
    }
}
