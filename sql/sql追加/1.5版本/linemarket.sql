#1、新建数据库
CREATE DATABASE IF NOT EXISTS guiyu_linemarket DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
grant all on guiyu_linemarket.* to line@'%' identified by 'line@1234' with grant option; 
grant all privileges on guiyu_linemarket.* to 'line'@'%' identified by 'line@1234' with grant option;

#2、建表
CREATE TABLE sip_line_apply (id int NOT NULL AUTO_INCREMENT, sip_line_id int, agent_line_id int, up_sip_line_id int COMMENT '变更sip线路编号(变更时用)', line_name varchar(30) COMMENT '线路名称', supplier varchar(30) COMMENT '供应商', overt_area varchar(30) COMMENT '外显归属地', call_direc int COMMENT '呼叫方向:1-呼出;2-呼入', max_concurrent_calls int COMMENT '并发数', templates varchar(100) COMMENT '话术模板编号', begin_date varchar(10) COMMENT '开始日期', end_date varchar(10) COMMENT '结束日期', belong_user varchar(50) COMMENT '虚拟线路归属人', org_code varchar(8) COMMENT '虚拟线路归属企业', univalent decimal(8,2) COMMENT '单价(元)', remark varchar(128) COMMENT '备注', apply_user varchar(50) COMMENT '申请人', apply_org_code varchar(8) COMMENT '申请企业', apply_date varchar(10) COMMENT '申请日期', apply_time datetime COMMENT '申请时间', apply_type int COMMENT '申请类型:1-新线路;2-业务数据变更;3-线路变更', apply_status int COMMENT '申请状态:1-申请中,2-审批通过;3-审批拒绝', approve_user varchar(50) COMMENT '审批人', approve_date varchar(10) COMMENT '审批日期', approve_time datetime COMMENT '审批时间', approve_remark varchar(128) COMMENT '审批备注', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', update_time datetime, update_user varchar(50), PRIMARY KEY (id), INDEX sip_line_apply_idx1 (agent_line_id), INDEX sip_line_apply_idx3 (up_sip_line_id), INDEX sip_line_apply_idx4 (apply_user), INDEX sip_line_apply_idx5 (apply_status), INDEX sip_line_apply_idx6 (approve_user)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SIP线路申请';
CREATE TABLE sip_line_base_info (id int NOT NULL AUTO_INCREMENT, line_name varchar(30) COMMENT '线路名称', line_id int COMMENT '线路编号', supplier varchar(30) COMMENT '供应商', line_status int COMMENT '线路状态:0-初始未提交,1-正常;2-到期;3-失效', sip_ip varchar(15) COMMENT 'sip服务器ip', sip_port int COMMENT 'sip服务器端口', sip_domain varchar(20) COMMENT 'sip域', sip_account varchar(20) COMMENT 'sip账号', sip_psd varchar(20) COMMENT 'sip密码', codec varchar(10) COMMENT '编解码', reg_flag tinyint(1) COMMENT '是否注册-fs是否需注册', caller_num varchar(20) COMMENT '主叫号码', destination_prefix varchar(20) COMMENT '被叫前缀', max_concurrent_calls int COMMENT '并发数', use_concurrent_calls int COMMENT '已用并发数', call_direc int COMMENT '呼叫方向:1-呼出;2-呼入', begin_date varchar(10) COMMENT '开始日期', end_date varchar(10) COMMENT '结束日期', time_begin varchar(20) COMMENT '拨打时间开始', time_end varchar(20) COMMENT '拨打时间结束', overt_area varchar(30) COMMENT '外显归属地', industrys varchar(100) COMMENT '行业限制', areas varchar(100) COMMENT '地区限制', except_areas varchar(100) COMMENT '地区盲区', contract_univalent decimal(8,2) COMMENT '合同价', univalent decimal(8,2) COMMENT '单价(元)', line_fee_type int COMMENT '线路计费类型:1-自营线路(扣费),2-客户自备线路(不扣费)', remark varchar(128) COMMENT '备注', sip_share_id int COMMENT '虚拟共享sip线路编号(根据)', org_code varchar(8) COMMENT '归属企业', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', update_time datetime, update_user varchar(50), PRIMARY KEY (id), INDEX sip_line_base_info_idx1 (org_code)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理第三方线路';
CREATE TABLE sip_line_exclusive (id int NOT NULL AUTO_INCREMENT, sip_line_id int, apply_id int COMMENT '申请编号', agent_line_id int, line_id int COMMENT '线路编号', line_name varchar(30) COMMENT '线路名称', line_type int COMMENT '线路类型:1-自营线路;2-代理线路', line_status int COMMENT '线路状态:1-正常;2-到期;3-失效', supplier varchar(30) COMMENT '供应商', call_direc int COMMENT '呼叫方向:1-呼出;2-呼入', max_concurrent_calls int COMMENT '并发数', begin_date varchar(10) COMMENT '开始日期', end_date varchar(10) COMMENT '结束日期', line_fee_type int COMMENT '线路计费类型:1-自营线路(扣费),2-客户自备线路(不扣费)', univalent decimal(8,2) COMMENT '单价(元)', overt_area varchar(30) COMMENT '外显归属地', industrys varchar(100) COMMENT '行业限制', templates varchar(100) COMMENT '模板限制', areas varchar(100) COMMENT '地区限制', belong_user varchar(50) COMMENT '归属人', belong_org_code varchar(8) COMMENT '分配企业', except_areas varchar(100) COMMENT '地区盲区', remark varchar(128) COMMENT '备注', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', update_time datetime, update_user varchar(50), PRIMARY KEY (id), INDEX sip_line_exclusive_idx1 (sip_line_id), INDEX sip_line_exclusive_idx2 (agent_line_id), INDEX sip_line_exclusive_idx3 (line_type), INDEX sip_line_exclusive_idx4 (line_status)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sip线路(独享)';
CREATE TABLE sip_line_share (id int NOT NULL AUTO_INCREMENT, line_status int COMMENT '线路状态:1-正常;2-到期;3-失效', line_name varchar(30) COMMENT '线路名称', supplier varchar(30) COMMENT '供应商', call_direc int COMMENT '呼叫方向:1-呼出;2-呼入', begin_date varchar(10) COMMENT '开始日期', end_date varchar(10) COMMENT '结束日期', univalent decimal(8,2) COMMENT '单价(元)', overt_area varchar(30) COMMENT '外显归属地', industrys varchar(100) COMMENT '行业限制', areas varchar(100) COMMENT '地区限制', except_areas varchar(100) COMMENT '地区盲区', apply_num int COMMENT '申请次数', remark varchar(128) COMMENT '备注', belong_user varchar(50) COMMENT '归属人', org_code varchar(8) COMMENT '归属企业', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', update_time datetime, update_user varchar(50), PRIMARY KEY (id), INDEX sip_line_share_idx1 (call_direc, univalent, overt_area, industrys), INDEX sip_line_share_idx2 (belong_user), INDEX sip_line_share_idx3 (org_code), INDEX sip_line_share_idx4 (line_status)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sip线路(共享)';
CREATE TABLE sip_route_item (id int NOT NULL AUTO_INCREMENT, item_code varchar(10) COMMENT '规则项编号', item_name varchar(20) COMMENT '规则项名称', seq int COMMENT '显示顺序', PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sip线路路由规则项';
CREATE TABLE sip_route_rule (id int NOT NULL AUTO_INCREMENT, user_id varchar(50) COMMENT '用户编号', rule_type int COMMENT '规则类型:1-优先级', rule_content varchar(1024) COMMENT '规则内容', status int COMMENT '规则状态:1-启用,2-失效', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', update_time datetime, update_user varchar(50), PRIMARY KEY (id), INDEX sip_route_rule_idx1 (user_id), INDEX sip_route_rule_idx2 (status)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线路路由规则';
CREATE TABLE voip_gw_info (id int NOT NULL AUTO_INCREMENT, gw_name varchar(20) NOT NULL COMMENT '网关名称(对应网关配置中的描述信息,用来匹配数据使用)', gw_brand varchar(20) COMMENT '网关品牌', gw_version varchar(30) COMMENT '网关型号', company_Id int COMMENT '网关公司号', dev_id int COMMENT '网关设备号', port_num int COMMENT '端口数', sip_ip varchar(15) COMMENT '注册服务ip', sip_port int COMMENT '注册服务端口', start_sip_account int COMMENT '起始sip账号', start_sip_pwd int COMMENT '起始sip密码', sip_account_step int COMMENT 'sip账号步长', sip_pwd_step int COMMENT 'sip密码步长', univalent decimal(8,2) COMMENT '单价(元)', reg_type int COMMENT '语音网关注册类型:1-反向注册(gw-fs);2-正向注册(fs-gw)', gw_reg_status int COMMENT '语音网关注册状态: 0-初始化,1-确认', gw_ip varchar(15) COMMENT '网关外网ip', user_id varchar(50) COMMENT '归属人', org_code varchar(8) COMMENT '归属企业', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', update_time datetime, update_user varchar(50), PRIMARY KEY (id), CONSTRAINT voip_gw_info_idx1 UNIQUE (gw_name), INDEX voip_gw_info_idx2 (org_code), INDEX voip_gw_info_idx3 (company_Id), INDEX voip_gw_info_idx4 (dev_id)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='语音网关配置';
CREATE TABLE voip_gw_port (id int NOT NULL AUTO_INCREMENT, port int, line_id int COMMENT '线路编号', alias varchar(30) COMMENT '别名', company_Id int COMMENT '网关公司号', dev_id int COMMENT '网关设备号', gw_id int, sip_account int, sip_pwd int, univalent decimal(8,2) COMMENT '单价(元)', phone_no varchar(20), user_id varchar(50) COMMENT '归属人', org_code varchar(8) COMMENT '归属企业', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', update_time datetime, update_user varchar(50), PRIMARY KEY (id), INDEX voip_gw_port_idx1 (gw_id), INDEX voip_gw_port_idx2 (port), INDEX voip_gw_port_idx3 (user_id), INDEX voip_gw_port_idx4 (org_code)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='语音网关端口配置';
CREATE TABLE voip_gw_port_his (id int NOT NULL AUTO_INCREMENT, company_Id int COMMENT '网关公司号', dev_id int COMMENT '网关设备号', port int, line_id int COMMENT '线路编号', alias varchar(30) COMMENT '别名', gw_id int, sip_account int, sip_pwd int, univalent decimal(8,2) COMMENT '单价(元)', phone_no varchar(20), user_id varchar(50) COMMENT '归属人', org_code varchar(8) COMMENT '归属企业', crt_user varchar(50) COMMENT '创建人', crt_time datetime COMMENT '创建时间', PRIMARY KEY (id), INDEX voip_gw_port_his_idx1 (gw_id), INDEX voip_gw_port_his_idx2 (port)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='语音网关端口配置变更历史';

#3、基础数据
INSERT INTO sip_route_item (id, item_code, item_name, seq) VALUES (1, '01', '地区', 1);
INSERT INTO sip_route_item (id, item_code, item_name, seq) VALUES (2, '02', '接通率', 2);
INSERT INTO sip_route_item (id, item_code, item_name, seq) VALUES (3, '03', '单价', 3);

#4、老线路数据迁移--切到guiyu_callcenter库
use guiyu_callcenter
select concat('INSERT INTO sip_line_exclusive (line_id, line_name, line_type, line_status, max_concurrent_calls, line_fee_type, belong_user, belong_org_code, remark, crt_user, crt_time, update_time, update_user) VALUES (',line_id,',''',line_name,''',',1,',',1,',',1,',',max_concurrent_calls,',''',customer_id,''',''',coalesce(org_code,"1"),''',''',coalesce(remark,""),''',',1,',''2019-02-03 00:00:00'',','''2019-02-03 00:00:00'',''',1,''');') from line_info where customer_id is not null
#注意：查询出来的数据，企业编号要另外处理下，需要使用用户真实所属企业org_code
#执行后生成的insert语句再到line_market库执行
