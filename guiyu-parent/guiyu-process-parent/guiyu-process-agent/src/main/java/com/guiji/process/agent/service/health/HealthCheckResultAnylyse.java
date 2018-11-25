package com.guiji.process.agent.service.health;

import com.guiji.common.model.process.ProcessStatusEnum;
import com.guiji.common.model.process.ProcessTypeEnum;
import com.guiji.process.agent.model.CommandResult;
import com.guiji.process.agent.service.health.impl.GpuHealthCheckResultAnalyse;
import com.guiji.process.agent.service.health.impl.SellbotHealthCheckResultAnalyse;

public class HealthCheckResultAnylyse {


    public static ProcessStatusEnum check(CommandResult cmdResult, ProcessTypeEnum processType)
    {
        IHealthCheckResultAnalyse analyse = null;
        switch (processType)
        {
            case SELLBOT:
                analyse = new SellbotHealthCheckResultAnalyse();
                break;

            case TTS:
                analyse = new GpuHealthCheckResultAnalyse();
                break;
        }

        ProcessStatusEnum result = null;
        if(analyse != null)
        {
            result = analyse.check(cmdResult);
        }

        return result;
    }
}
