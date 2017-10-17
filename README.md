# DaDouMiAppServer
## 大豆米APP的rest风格服务器
## 使用Spring+SpringMVC+MyBatis+Mysql开发

# 部署服务

- 1.将sql语句执行,创建数据库

- 2.部署服务到tomcat

- 3.部署图片上传服务 ImgUploadServer.tar.gz

	解压到/usr/local目录下,配置图片存储路径名

	var storage = multer.diskStorage({ //multers disk storage settings
        destination: function (req, file, cb) {
            cb(null, '/静态资源存储路径/');
        },
        filename: function (req, file, cb) {
            var datetimestamp = Date.now();
            filenameBak = file.fieldname + '-' + datetimestamp + '.' + file.originalname.split('.')[file.originalname.split('.').length -1];
            cb(null, file.fieldname + '-' + datetimestamp + '.' + file.originalname.split('.')[file.originalname.split('.').length -1]);
        }
    });

- 4.安装服务器pm2
	
	pm2 start app.js    

	