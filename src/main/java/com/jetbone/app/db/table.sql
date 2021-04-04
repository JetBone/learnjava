CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint not null auto_increment,
    `username` varchar(20) not null ,
    `password` varchar(20) not null ,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工'

