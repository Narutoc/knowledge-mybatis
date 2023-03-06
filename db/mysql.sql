create table if not exists tbl_menu_info (
	menuid varchar(32) not null comment '菜单ID，唯一标识',
	parentid varchar(32) comment '父菜单ID，可为空',
	status int default 1 comment '状态,1-启用，2-停用，3-删除',
	sortnum int default 1 comment '展示顺序',
	createduser varchar(25) not null comment '创建人',
	createddate Datetime not null comment '创建时间',
	lastmodifieduser varchar(25) comment '最后修改人',
	lastmodifieddate Datetime comment '最后修改时间',
	primary key (menuid)
);
create table if not exists tbl_content_info(
    contentid varchar(32) not null comment '内容ID，唯一标识',
    objectid varchar(32) not null comment '对象ID，关联表的唯一标识',
    lang int default 1 comment '语言类型',
    content1 varchar(50) comment '内容1，一般为名称',
    content2 varchar(1024) comment '内容2，一般为描述',
    content3 varchar(1024) comment '内容3，一般为概要',
    content4 varchar(1024) comment '内容4，一般为简介',
    status int default 1 comment '状态,1-启用，2-停用，3-删除',
    createduser varchar(25) not null comment '创建人',
    createddate Datetime not null comment '创建时间',
    lastmodifieduser varchar(25) comment '最后修改人',
    lastmodifieddate Datetime comment '最后修改时间',
    primary key (contentid)
);
create table if not exists tbl_user_info(
    userid varchar(32) not null comment '用户ID，唯一标识',
    username varchar(25) comment '用户名字',
    password varchar(50) comment '登录密码',
    realName varchar(50) comment '用户真实姓名',
    status int default 1 comment '状态,1-启用，2-停用，3-删除',
    createduser varchar(25) not null comment '创建人',
    createddate Datetime not null comment '创建时间',
    lastmodifieduser varchar(25) comment '最后修改人',
    lastmodifieddate Datetime comment '最后修改时间',
    primary key (userid)
);