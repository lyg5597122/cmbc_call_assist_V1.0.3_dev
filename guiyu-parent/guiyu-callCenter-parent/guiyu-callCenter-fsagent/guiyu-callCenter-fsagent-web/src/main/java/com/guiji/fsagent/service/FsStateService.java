package com.guiji.fsagent.service;

import com.guiji.fsagent.entity.FsInfoVO;

public interface FsStateService {
    boolean ishealthy();

    FsInfoVO fsinfo();

}
