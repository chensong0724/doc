一、安装[docker](https://so.csdn.net/so/search?q=docker&spm=1001.2101.3001.7020)
1、Docker 要求 CentOS 系统的内核版本高于 3.10 ，查看本页面的前提条件来验证你的CentOS 版本是否支持 Docker 。

通过 uname -r 命令查看你当前的内核版本

$ uname -r
2、使用 root 权限登录 Centos。确保 [yum](https://so.csdn.net/so/search?q=yum&spm=1001.2101.3001.7020) 包更新到最新。

$ sudo yum update
3、卸载旧版本(如果安装过旧版本的话)

$ sudo yum remove docker docker-common docker-selinux docker-engine
4、安装需要的软件包， yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的

$ sudo yum install -y yum-utils device-mapper-persistent-data lvm2
5、设置yum源

//国外镜像
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
//阿里镜像
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
![在这里插入图片描述](https://img-blog.csdnimg.cn/cd891260c35b4d9fb67426d6da7729a6.png)
6、可以查看所有仓库中所有docker版本，并选择特定版本安装
$ yum list docker-ce --showduplicates | sort -r
![在这里插入图片描述](https://img-blog.csdnimg.cn/0bebd1ad27c24d7ca920c0db00602e89.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5pe25YWJ5a6J6Z2Z5L6_5aW9,size_15,color_FFFFFF,t_70,g_se,x_16)
7、安装docker
$ sudo yum install docker-ce #由于repo中默认只开启stable仓库，故这里安装的是最新稳定版17.12.0
$ sudo yum install # 例如：sudo yum install docker-ce-17.12.0.ce
![在这里插入图片描述](https://img-blog.csdnimg.cn/f3b0e2a0283b41d8b836bd26e2e90a5f.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5pe25YWJ5a6J6Z2Z5L6_5aW9,size_19,color_FFFFFF,t_70,g_se,x_16)
8、启动并加入开机启动
$ sudo systemctl start docker
$ sudo systemctl enable docker
![在这里插入图片描述](https://img-blog.csdnimg.cn/404c32b03f724144904362cd0051ae77.png)
9、验证安装是否成功(有client和service两部分表示docker安装启动都成功了)

$ docker version
![在这里插入图片描述](https://img-blog.csdnimg.cn/6bb965232da34dbebfc7e15c9cb439c4.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5pe25YWJ5a6J6Z2Z5L6_5aW9,size_14,color_FFFFFF,t_70,g_se,x_16)
二、问题
1、因为之前已经安装过旧版本的docker，在安装的时候报错如下：

```javascript
Transaction check error:
  file /usr/bin/docker from install of docker-ce-17.12.0.ce-1.el7.centos.x86_64 conflicts with file from package docker-common-2:1.12.6-68.gitec8512b.el7.centos.x86_64
  file /usr/bin/docker-containerd from install of docker-ce-17.12.0.ce-1.el7.centos.x86_64 conflicts with file from package docker-common-2:1.12.6-68.gitec8512b.el7.centos.x86_64
  file /usr/bin/docker-containerd-shim from install of docker-ce-17.12.0.ce-1.el7.centos.x86_64 conflicts with file from package docker-common-2:1.12.6-68.gitec8512b.el7.centos.x86_64
  file /usr/bin/dockerd from install of docker-ce-17.12.0.ce-1.el7.centos.x86_64 conflicts with file from package docker-common-2:1.12.6-68.gitec8512b.el7.centos.x86_64
12345
```

2、卸载旧版本的包

```javascript
$ sudo yum erase docker-common-2:1.12.6-68.gitec8512b.el7.centos.x86_64
1
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/01c21cf0a24b4ce7a0e2a38e4e21287b.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5pe25YWJ5a6J6Z2Z5L6_5aW9,size_20,color_FFFFFF,t_70,g_se,x_16)
3、再次安装docker

$ sudo yum install docker-ce

```docker
docker logs  -f -t --tail 1000 b7dbee6acbe4 查看docker启动时日志
```

