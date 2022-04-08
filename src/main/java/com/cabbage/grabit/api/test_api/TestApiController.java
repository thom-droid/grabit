package com.cabbage.grabit.api.test_api;

import com.cabbage.grabit.exception.test.BadArgumentException;
import com.cabbage.grabit.exception.test.InternalException;
import com.cabbage.grabit.exception.test.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestApiController {

    @GetMapping("/exception/{exception_id}")
    public void getException(@PathVariable("exception_id") String exception) throws Exception {
        if("not_found".equals(exception)){
            throw new ResourceNotFoundException("resource not found");
        }
        else if("bad_arguments".equals(exception)) {
            throw new BadArgumentException("bad arguments");
        }
        else
            throw new InternalException("internal error");
    }


}
