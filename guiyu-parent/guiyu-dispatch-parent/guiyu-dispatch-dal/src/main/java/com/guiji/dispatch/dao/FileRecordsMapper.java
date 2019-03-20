package com.guiji.dispatch.dao;

import com.guiji.dispatch.dao.entity.FileRecords;
import com.guiji.dispatch.dao.entity.FileRecordsExample;

import java.util.Date;
import java.util.List;

import com.guiji.dispatch.sys.ResultPage;
import org.apache.ibatis.annotations.Param;

public interface FileRecordsMapper {
    int countByExample(FileRecordsExample example);

    int deleteByExample(FileRecordsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileRecords record);

    int insertSelective(FileRecords record);

    List<FileRecords> selectByExample(FileRecordsExample example);

    FileRecords selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileRecords record, @Param("example") FileRecordsExample example);

    int updateByExample(@Param("record") FileRecords record, @Param("example") FileRecordsExample example);

    int updateByPrimaryKeySelective(FileRecords record);

    int updateByPrimaryKey(FileRecords record);

    List<FileRecords> queryFileRecordList(//@Param("example") FileRecordsExample example,
                                          @Param("record") FileRecords queryRecord,
                                          @Param("startTime") Date startTime,
                                          @Param("endTime") Date endTime,
                                          @Param("page") ResultPage<FileRecords> page);

    int queryFileRecordCount(@Param("record") FileRecords queryRecord,
                             @Param("startTime") Date startTime,
                             @Param("endTime") Date endTime);


    FileRecords queryFileRecordById(@Param("id") Long id);
}