package com.cabbage.grabit.api.test_api;

import com.cabbage.grabit.exception.test.BadArgumentException;
import com.cabbage.grabit.exception.test.InternalException;
import com.cabbage.grabit.exception.test.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TestApiController.class)
public class TestApiControllerTest {

    @Autowired
    private MockMvc mvc;

    private String url = "/test/exception/";

    @Test
    public void notFoundException() throws Exception {
        String param = "not_found";

        mvc.perform(get(url+param).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    public void badArgumentException() throws Exception{
        String param = "bad_arguments";

        mvc.perform(get(url+param).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentException))
                .andExpect(result -> assertEquals("bad arguments", result.getResolvedException().getMessage()))
                .andDo(print());
    }

    @Test
    public void internalException() throws Exception{
        String param = "udon";

        mvc.perform(get(url+param).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalException))
                .andExpect(result -> assertEquals("internal error", result.getResolvedException().getMessage()))
                .andDo(print());
    }
}