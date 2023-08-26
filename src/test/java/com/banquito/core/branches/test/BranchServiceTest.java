package com.banquito.core.branches.test;

import com.banquito.core.branches.exception.CRUDException;
import com.banquito.core.branches.model.Branch;
import com.banquito.core.branches.repository.BranchRepository;
import com.banquito.core.branches.service.BranchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {
    private Branch branch;
    private List<Branch> branches;
    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;

    @BeforeEach
    void setUp(){
        this.branchService = new BranchService(this.branchRepository);
        this.branch = new Branch();
        this.branch.setId("64e84eb9f5783b17e73000x0");
        this.branch.setCode("e0ce35d5-0984-4408-9e6f-e6995d279a7e");
        this.branch.setName("Branch Example 1");
        this.branches = new ArrayList<>();
        branches.add(this.branch);
    }

    @Test
    void testLookById() {
        when(this.branchRepository.findById("64e84eb9f5783b17e73000x0")).thenReturn(Optional.ofNullable(this.branch));
        assertDoesNotThrow(()->{
            this.branchService.lookById("64e84eb9f5783b17e73000x0");
        });
        assertThrows(CRUDException.class, () -> {
            this.branchService.lookById("4ed4ebcf57833b17e73f2031");
        });
    }

    @Test
    void testLookByCode() {
        when(this.branchRepository.findByCode("e0ce35d5-0984-4408-9e6f-e6995d279a7e")).thenReturn(this.branch);
        assertDoesNotThrow(()->{
            //this.branchService.lookById("64e84eb9f5783b17e73000x0");
            this.branchService.lookByCode("e0ce35d5-0984-4408-9e6f-e6995d279a7e");
        });
    }

    @Test
    void testGetAll() {
        when(this.branchRepository.findAll()).thenReturn(this.branches);
        assertDoesNotThrow(()->{
            //this.branchService.lookById("64e84eb9f5783b17e73000x0");
            this.branchService.getAll();
        });
    }

    @Test
    void testCreate() {
        when(this.branchRepository.save(this.branch)).thenReturn(this.branch);
        assertDoesNotThrow(()->{
            //this.branchService.lookById("64e84eb9f5783b17e73000x0");
            this.branchService.create(this.branch);
        });
//        assertThrows(CRUDException.class, ()->{
//            //this.branchService.lookById("64e84eb9f5783b17e73000x0");
//            this.branchService.create();
//        });
    }

    @Test
    void testUpdate() {
        when(this.branchRepository.findByCode("e0ce35d5-0984-4408-9e6f-e6995d279a7e")).thenReturn(this.branch);
        assertDoesNotThrow(()->{
            //this.branchService.lookById("64e84eb9f5783b17e73000x0");
            this.branchService.update("e0ce35d5-0984-4408-9e6f-e6995d279a7e",this.branch);
        });
        assertThrows(CRUDException.class, ()->{
            //this.branchService.lookById("64e84eb9f5783b17e73000x0");
            this.branchService.update("23ee0a36-5b9c-4e8f-9725-b1de280756ef", this.branch);
        });
    }
}