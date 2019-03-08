ALTER TABLE guiyu_base.sys_organization_industry MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '企业code'
ALTER TABLE guiyu_base.sys_user MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '企业code'
ALTER TABLE guiyu_billing.billing_acct_charging_record MODIFY COLUMN oper_user_org_code VARCHAR(30) NOT NULL COMMENT '用户编码'
ALTER TABLE guiyu_billing.billing_acct_charging_record_20190304_49 MODIFY COLUMN oper_user_org_code VARCHAR(30) NOT NULL COMMENT '用户编码'
ALTER TABLE guiyu_billing.billing_user_acct MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '公司编码'
ALTER TABLE guiyu_botstence.bot_available_template MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_botstence.bot_sentence_process MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.agent MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.call_out_plan MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.call_out_plan_0 MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.call_out_plan_1 MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.line_info MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.notice_send_label MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.queue MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.report_call_day MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.report_call_hour MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.report_call_today MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.report_line_code MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.report_line_status MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_callcenter.tier MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_da.robot_call_his MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '机构编号'
ALTER TABLE guiyu_da.robot_call_process_stat MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '机构号'
ALTER TABLE guiyu_dispatch.black_list MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '批次名字'
ALTER TABLE guiyu_dispatch.black_list_records MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_dispatch.dispatch_plan_0 MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织编码'
ALTER TABLE guiyu_dispatch.dispatch_plan_1 MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织编码'
ALTER TABLE guiyu_dispatch.dispatch_plan_2 MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织编码'
ALTER TABLE guiyu_dispatch.dispatch_plan_batch MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织code'
ALTER TABLE guiyu_dispatch.file_records MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT ''
ALTER TABLE guiyu_linemarket.sip_line_apply MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '虚拟线路归属企业'
ALTER TABLE guiyu_linemarket.sip_line_apply MODIFY COLUMN apply_org_code VARCHAR(30) NOT NULL COMMENT '申请企业'
ALTER TABLE guiyu_linemarket.sip_line_base_info MODIFY COLUMN belong_org_code VARCHAR(30) NOT NULL COMMENT '分配企业'
ALTER TABLE guiyu_linemarket.sip_line_base_info MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '归属企业'
ALTER TABLE guiyu_linemarket.sip_line_exclusive MODIFY COLUMN belong_org_code VARCHAR(30) NOT NULL COMMENT '分配企业'
ALTER TABLE guiyu_linemarket.sip_line_share MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '归属企业'
ALTER TABLE guiyu_linemarket.voip_gw_info MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '归属企业'
ALTER TABLE guiyu_linemarket.voip_gw_port MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '归属企业'
ALTER TABLE guiyu_linemarket.voip_gw_port_his MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '归属企业'
ALTER TABLE guiyu_notice.notice_info MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '企业编码'
ALTER TABLE guiyu_notice.notice_setting MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '企业编码'
ALTER TABLE guiyu_robot.user_ai_cfg_base_info MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '机构编号'
ALTER TABLE guiyu_sms.sms_config MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织代码'
ALTER TABLE guiyu_sms.sms_platform MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织代码'
ALTER TABLE guiyu_sms.sms_task MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织代码'
ALTER TABLE guiyu_sms.sms_task_detail MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织代码'
ALTER TABLE guiyu_sms.sms_tunnel MODIFY COLUMN org_code VARCHAR(30) NOT NULL COMMENT '组织代码'