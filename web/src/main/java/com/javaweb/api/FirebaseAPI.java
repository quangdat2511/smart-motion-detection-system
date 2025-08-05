package com.javaweb.api;

import com.javaweb.model.dto.FirebaseDTO;
import com.javaweb.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/firebase")
public class FirebaseAPI {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public String create(@RequestBody FirebaseDTO firebaseDTO) throws ExecutionException, InterruptedException {
        return firebaseService.create(firebaseDTO);
    }

    @GetMapping()
    public FirebaseDTO get(@RequestParam String documentId) throws ExecutionException, InterruptedException {
        return firebaseService.get(documentId);
    }

    @PutMapping
    public String update(@RequestBody FirebaseDTO firebaseDTO) throws ExecutionException, InterruptedException {
        return firebaseService.update(firebaseDTO);
    }

    @DeleteMapping()
    public String delete(@RequestParam String documentId) throws ExecutionException, InterruptedException {
        return firebaseService.delete(documentId);
    }
}
