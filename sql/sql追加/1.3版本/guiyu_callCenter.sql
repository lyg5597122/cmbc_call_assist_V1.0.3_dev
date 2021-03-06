ALTER TABLE  `call_out_plan` ADD COLUMN talk_num INT(4) DEFAULT 0 COMMENT '对话轮数';
ALTER TABLE  `call_out_plan` ADD COLUMN is_cancel INT(1) DEFAULT 0 COMMENT '是否超时';
ALTER TABLE  `call_out_plan` ADD COLUMN is_answer INT(1) DEFAULT 0 COMMENT '是否接听';

ALTER TABLE  `call_out_plan_0` ADD COLUMN talk_num INT(4) DEFAULT 0 COMMENT '对话轮数';
ALTER TABLE  `call_out_plan_0` ADD COLUMN is_cancel INT(1) DEFAULT 0 COMMENT '是否超时';
ALTER TABLE  `call_out_plan_0` ADD COLUMN is_answer INT(1) DEFAULT 0 COMMENT '是否接听';

ALTER TABLE  `call_out_plan_1` ADD COLUMN talk_num INT(4) DEFAULT 0 COMMENT '对话轮数';
ALTER TABLE  `call_out_plan_1` ADD COLUMN is_cancel INT(1)  DEFAULT 0 COMMENT '是否超时';
ALTER TABLE  `call_out_plan_1` ADD COLUMN is_answer INT(1) DEFAULT 0 COMMENT '是否接听';


DROP TABLE IF EXISTS `report_line_code`;

CREATE TABLE `report_line_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `line_id` int(11) DEFAULT NULL,
  `hangup_code` varchar(10) DEFAULT NULL,
  `is_cancel` int(1) DEFAULT NULL,
  `total_calls` int(11) DEFAULT NULL,
  `answer_calls` int(11) DEFAULT NULL,
  `phone_num` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `report_line_status`;

CREATE TABLE `report_line_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `line_id` int(11) DEFAULT NULL,
  `answer_num` int(11) DEFAULT NULL,
  `total_num` int(11) DEFAULT NULL,
  `rate` float DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


ALTER TABLE  `call_out_detail` ADD COLUMN isupdate INT(1) DEFAULT 0 COMMENT '是否修改过';
ALTER TABLE  `call_out_detail_0` ADD COLUMN isupdate INT(1) DEFAULT 0 COMMENT '是否修改过';
ALTER TABLE  `call_out_detail_1` ADD COLUMN isupdate INT(1) DEFAULT 0 COMMENT '是否修改过';

DROP TABLE IF EXISTS `call_out_detail_log`;

CREATE TABLE `call_out_detail_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `call_detail_id` bigint(20) DEFAULT NULL,
  `customer_say_text_new` varchar(255) DEFAULT NULL,
  `customer_say_text` varchar(255) DEFAULT NULL,
  `update_by` int(8) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


