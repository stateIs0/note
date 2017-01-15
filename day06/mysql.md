mysql:

显示Mysql中与编码有关的环境变量

show variables like '%char%';

| Variable_name            | Value                                        
| character_set_client     | utf8                                          
| character_set_connection | utf8                                          
| character_set_database   | utf8                                         
| character_set_filesystem | binary                                       
| character_set_results    | utf8                                          
| character_set_server     | utf8                                         
| character_set_system     | utf8

解决MySQL中文问题：
                                         
替换文件： /etc/my.cnf

重新启动MySQL：/sbin/service mysqld restart

重建数据库：source cloud_note.sql

