package com.guiji.fsmanager.api;

import com.guiji.common.result.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: 黎阳
 * @Date: 2018/10/26 0026 17:19
 * @Description:
 */
@FeignClient("guiyu-callcenter-fsmanager")
public interface ITempApi {

    @ApiOperation(value = "下载模板录音")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tempId", value = "模板Id", dataType = "String", paramType = "query")
    })
    @GetMapping(value="downloadtempwav")
    public Result.ReturnData downloadtempwav(@RequestParam("tempId") String tempId);

    @ApiOperation(value = "模板是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tempId", value = "模板Id", dataType = "String", paramType = "query")
    })
    @GetMapping(value="istempexist")
    public Result.ReturnData<Boolean> istempexist(@RequestParam("tempId") String tempId);
}
