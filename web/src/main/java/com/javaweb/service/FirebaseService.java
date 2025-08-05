package com.javaweb.service;

import com.javaweb.model.dto.FirebaseDTO;

import java.util.concurrent.ExecutionException;

public interface FirebaseService {
    String create(FirebaseDTO firebaseDTO) throws ExecutionException, InterruptedException;
    String update(FirebaseDTO firebaseDTO) throws ExecutionException, InterruptedException;
    FirebaseDTO get(String documentId) throws ExecutionException, InterruptedException;
    String delete(String documentId);
}
