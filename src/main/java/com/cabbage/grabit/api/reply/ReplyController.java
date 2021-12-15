package com.cabbage.grabit.api.reply;

import com.cabbage.grabit.domain.reply.dto.ReplyPostRequestDto;
import com.cabbage.grabit.exception.ApiResult;
import com.cabbage.grabit.exception.ApiStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyFacade replyFacade;

    @PostMapping
    public ApiResult postReply(@RequestBody ReplyPostRequestDto requestDto){

        return ApiResult.of(ApiStatus.SUCCESS, replyFacade.postReply(requestDto));
    }

}
