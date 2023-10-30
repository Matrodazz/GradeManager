package br.com.fiap.grademanager.checkpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckpointService {

    @Autowired
    CheckpointRepository repository;

    public List<Checkpoint> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var checkpoint =  repository.findById(id);
        if (checkpoint.isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }
    
    public void save(Checkpoint checkpoint) {
        repository.save(checkpoint);
    }
}
