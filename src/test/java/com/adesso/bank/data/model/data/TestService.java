package com.adesso.bank.data.model.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<String> retrieveAll() {
        return testRepository.retrieveAll();
    }

    public String retrieveOne() {
        return testRepository.retrieveOne();
    }

    public String retrieveOneAndThrow() throws RuntimeException {
        return testRepository.retrieveOneAndThrow();
    }

    public String retrieveOneAndWait() {
        return testRepository.retrieveOneAndWait();
    }

}
