# 参考代码说明

## test.soundmaker

包含了播放MP3和WAVE类型音乐文件的参考代码（不完整，仅用于播放）。

## music.client

包含了用于创建、上传音乐单及音乐文件的参考代码，需要与music.server配合使用。

## music.server

包含了用于构建服务端软件的代码。在music.server.sql目录中包含了创建服务器端数据库中各表的SQL文件（如musicserver_2017-11-17.sql）。
需要进入MySQL命令行终端，创建数据库（create database musicserver;）,进入数据库（use musicserver;）,执行（source musicserver_2017-11-17.sql;）会自动创建表并导入测试数据。

## music.client.test

测试ouc-cs-music-client-1.0.jar，该Jar包提供了client到server进行音乐单操作、音乐文件上传下载等关键接口的本地Java实现。主要接口代码在import ouc.cs.course.java.musicclient.MusicOperationClient类中。

# 注意

认真阅读并参考。