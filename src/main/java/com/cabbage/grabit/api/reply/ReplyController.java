package com.cabbage.grabit.api.reply;

import com.cabbage.grabit.config.auth.LoginUser;
import com.cabbage.grabit.config.auth.SessionUser;
import com.cabbage.grabit.domain.reply.dto.request.ReplyPostRequestDto;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyFacade replyFacade;

    @PostMapping
    public ApiResult postReply(@RequestBody ReplyPostRequestDto requestDto
                               //@LoginUser SessionUser sessionUser
                               ){

        return ApiResult.of(ApiStatus.SUCCESS, replyFacade.postReply(requestDto));
    }

}
