package org.wora.cyclist;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wora.common.GenericService;
import org.wora.cyclist.dto.CyclistRequestDto;
import org.wora.cyclist.dto.CyclistResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CyclistServiceImpl implements GenericService<CyclistRequestDto, CyclistResponseDto, Long> {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Override
    @Transactional
    public CyclistResponseDto save(CyclistRequestDto cyclistRequestDto) {
        Cyclist cyclist = CyclistMapper.INSTANCE.toEntity(cyclistRequestDto);
        Cyclist savedCyclist = cyclistRepository.save(cyclist);
        return CyclistMapper.INSTANCE.toDto(savedCyclist);
    }

    @Override
    @Transactional
    public List<CyclistResponseDto> findAll() {
        List<Cyclist> cyclists = cyclistRepository.findAll();
        return cyclists.stream()
                .map(CyclistMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CyclistResponseDto update(CyclistRequestDto cyclistRequestDto, Long id) {
        Optional<Cyclist> optionalCyclist = cyclistRepository.findById(id);
        if (optionalCyclist.isPresent()) {
            Cyclist cyclist = CyclistMapper.INSTANCE.toEntity(cyclistRequestDto);
            cyclist.setId(id);
            Cyclist updatedCyclist = cyclistRepository.save(cyclist);
            return CyclistMapper.INSTANCE.toDto(updatedCyclist);
        }
        return null;
    }

    @Override
    @Transactional
    public Optional<CyclistResponseDto> findById(Long id) {
        return cyclistRepository.findById(id)
                .map(CyclistMapper.INSTANCE::toDto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Cyclist> optionalCyclist = cyclistRepository.findById(id);
        if (optionalCyclist.isPresent()) {
            cyclistRepository.delete(optionalCyclist.get());
        } else {
            throw new RuntimeException("Cyclist not found with id: " + id);
        }
    }
}
