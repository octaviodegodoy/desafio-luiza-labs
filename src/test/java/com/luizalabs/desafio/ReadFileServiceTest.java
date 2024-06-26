package com.luizalabs.desafio;

import com.luizalabs.desafio.model.UserOrder;
import com.luizalabs.desafio.service.ReadFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReadFileServiceTest {

    @InjectMocks
    private ReadFileService readFileService;

    @Test
    public void testReadFolderFiles() {
        List<UserOrder> userOrders = readFileService.readFolderFiles();
        assertNotNull(userOrders);
    }

}