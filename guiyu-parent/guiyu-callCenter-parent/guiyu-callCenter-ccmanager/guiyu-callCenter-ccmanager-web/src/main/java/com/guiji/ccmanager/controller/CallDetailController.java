package com.guiji.ccmanager.controller;

import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.ccmanager.constant.Constant;
import com.guiji.ccmanager.service.CallDetailService;
import com.guiji.ccmanager.vo.CallOutPlanVO;
import com.guiji.common.model.Page;
import com.guiji.component.result.Result;
import com.guiji.utils.DateUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 黎阳
 * @Date: 2018/10/29 0029 17:16
 * @Description:  通话记录列表，通话记录详情
 */
@RestController
public class CallDetailController {

    private final Logger log = LoggerFactory.getLogger(CallManagerOutApiController.class);

    @Autowired
    private CallDetailService callDetailService;

    @ApiOperation(value = "获取客户指定时间内的通话记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始时间,yyyy-MM-dd HH:mm:ss格式", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间,yyyy-MM-dd HH:mm:ss格式", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "customerId", value = "客户id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNo", value = "第几页，从1开始", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value="getCallRecord")
    public Result.ReturnData<Page<CallOutPlan>> getCallRecord(String startDate, String endDate, String customerId, String pageSize, String pageNo ){

        log.debug("get request getCallRecord，startDate[{}], endDate[{}],customerId[{}],pageSize[{}],pageNo[{}]", startDate, endDate, customerId, pageSize, pageNo);

        if(StringUtils.isBlank(pageSize) || StringUtils.isBlank(pageNo)){
            return Result.error(Constant.ERROR_PARAM);
        }

        Date end = null;
        Date start = null;
        if(StringUtils.isNotBlank(startDate)){
            start = DateUtil.stringToDate(startDate,"yyyy-MM-dd HH:mm:ss");
        }
        if(StringUtils.isNotBlank(endDate)){
            end = DateUtil.stringToDate(endDate,"yyyy-MM-dd HH:mm:ss");
        }

        int pageSizeInt = Integer.parseInt(pageSize);
        int pageNoInt = Integer.parseInt(pageNo);
        List<CallOutPlan> list = callDetailService.callrecord(start,end,customerId,pageSizeInt,pageNoInt);
        int count = callDetailService.callrecordCount(start,end,customerId);

        Page<CallOutPlan> page = new Page<CallOutPlan>();
        page.setPageNo(pageNoInt);
        page.setPageSize(pageSizeInt);
        page.setTotal(count);
        page.setRecords(list);

        log.debug("response success getCallRecord，startDate[{}], endDate[{}],customerId[{}],pageSize[{}],pageNo[{}]", startDate, endDate, customerId, pageSize, pageNo);

        return Result.ok(page);
    }


    @ApiOperation(value = "查看通话记录详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "callId", value = "callId", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value="getCallDetail")
    public Result.ReturnData<CallOutPlanVO> getCallDetail(String callId){

        log.debug("get request getCallDetail，callId[{}]", callId);

        if(StringUtils.isBlank(callId)){
            return Result.error(Constant.ERROR_PARAM);
        }
        CallOutPlanVO callOutPlanVO = callDetailService.getCallDetail(callId);

        log.debug("reponse success getCallDetail，callId[{}]", callId);
        return Result.ok(callOutPlanVO);
    }

    @ApiOperation(value = "下载通话记录")
    @GetMapping(value="downloadDialogue")
    public void downloadDialogue(String callId,HttpServletResponse resp) throws UnsupportedEncodingException {
        resp.setContentType("application/octet-stream;charset=GBK");
        String fileName = "通话记录.xls";
        resp.setHeader("Content-Disposition", "attachment;filename="+
                new String(fileName.getBytes("utf-8"),"iso-8859-1"));

        //生成文件
        String context = callDetailService.getDialogue(callId);

        OutputStream out=null;
        try {
            out=resp.getOutputStream();
            WritableWorkbook wb = Workbook.createWorkbook(out);

            WritableSheet sheet =  wb.createSheet("sheet1",0);
            WritableCellFormat format = new WritableCellFormat();
            format.setBorder(Border.ALL,BorderLineStyle.THIN);
            format.setWrap(true);

            Label label = new Label(0, 0 , "通话记录",format);
            sheet.addCell(label);
            sheet.setColumnView(0, 100);
            sheet.addCell(new Label(0,1, context,format));

            wb.write();
            wb.close();

        } catch (IOException e) {
            log.error("downloadDialogue IOException :"+e);
        } catch (RowsExceededException e) {
            log.error("downloadDialogue RowsExceededException :"+e);
        } catch (WriteException e) {
            log.error("downloadDialogue WriteException :"+e);
        } finally{
            try {
                out.close();
            } catch (IOException e) {
                log.error("out.close error:"+e);
            }
        }
    }

}
