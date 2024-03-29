# Linux系统启动过程

### 内核引导过程
当计算机打开电源后，首先是BIOS开机自检，按照设置的启动设备（一般指硬盘）来启动
当操作系统接管硬件猴，首先读取/boot目录下的内核文件

### 运行init
init进程是系统所有进程的起点
init进程首先要读取配置文件/etc/inittab
##### 运行级别
init进程的一大任务就是运行开机启动的程序，这些程序在Windows中叫服务(service)，在Linux中就叫守护进程(daemon)
Linux允许为不同场合分配不同的开机启动程序，称为运行级别(runlevel)。
- 运行级别0：系统停机状态，系统默认运行级别不能设为0，否则不能正常启动
- 运行级别1：单用户工作状态，root权限，用于系统维护，禁止远程登录
- 运行级别2：多用户状态(没有NFS)
- 运行级别3：完全的多用户状态(有NFS)登录后进入控制台命令行模式
- 运行级别4：系统未使用，保留
- 运行级别5：X11控制台，登录后进入图形GUI模式
- 运行级别6：系统正常关闭并重启，默认运行级别不能设为6，否则不能正常启动


# Linux 命令基础格式
command [-options] [parameter]
- command 命令本身
- -options [可选，非必填]命令的一些选项，可以通过选项控制命令的行为细节
- parameter [可选，非必填]命令的参数，多数用于命令的指向目标

### ls命令
格式
```bash
ls [-a -l -h] [Linux路径]
```
- -a -l -h是可选的选项
- Linux路径是可选参数
- -a表示列出全部文件（包括隐藏的文件和文件夹）
- -l表示以列表（竖向排列）形式展示内容，并显示更多信息
- -h表示以易读模式显示
- 可以随机组合使用

### cd切换工作目录
```bash
cd [Linux路径]
```
- cd命令只有参数，表示切换的目录下
- cd命令没有参数的时候默认回到HOME目录下

### pwd查看当前工作目录
```bash
pwd
```
-pwd命令直接输入即可

### 路径符
- . ：表示当前目录
- .. : 表示上级目录
- ../.. ：上二级目录
- ~ ：HOME目录

### mkdir创建新的目录
```bash
mkdir [-p] -Linux目录
```
- 参数必填，表示Linux路径
- -p选项可选，表示自动创建不存在的父目录，适用于创建连续多层级的目录

### touch创建文件
```bash
touch Linux路径
```

### cat查看文件内容
```bash
cat Linux路径
```
直接显示文件全部内容

### more 查看文件内容
```bash
more Linux路径
```
支持翻页，文件内容过多时支持翻页展示
- 查看的过程中通过空格翻页
- 想要结束翻页时使用q推出查看模式

### cp复制文件/文件夹
```bash
cp [-r] 参数1 参数2
```
- -r，可选，用于复制文件夹使用，表示递归
- 参数1，表示被复制的文件/文件夹
- 参数2，表示复制去的地方

### mv移动文件/文件夹
```bash
mv 参数1 参数2
```
- 参数1，被移动的文件/文件夹
- 参数2，要移动去的地方
如果参数2的目标不存在时，则将参数1名字进行修改

### rm删除文件/文件夹
```bash
rm [-r -f] 参数1 参数2 ... 参数N
```
- -r用于删除文件夹
- -f表示强制删除(不会弹出提示)，一般只有root用户删除内容时有提示
- 参数表示要删除的文件/文件夹
- rm命令支持通配符
  - 符号*表示通配符，即匹配任何内容(包括空)
  - test*，匹配以test开头的内容
  - *test，匹配以test结尾的内容
  - *test*，匹配包含test的内容

### which查找命令文件
```bash
which 要查找的命令
```
返回被查找的命令对应的程序文件的路径

### find按文件名查找文件
```bash
find 起始路径 -name "被查找文件名"
```
- 支持通配符

### find按照文件大小查找文件
```bash
find 起始路径 -size +|-n[kMG]
```
- +、-表示大于小于
- n表示大小数字
- kMG表示大小单位，k表示kb，M表示MB，G表示GB
示例：
- 查找小于10kb的文件：find / -size -10k

### grep过滤文件行
```bash
grep [-n] 关键字 文件路径
```
- -n，可选，表示结果中显示匹配行的行号
- 关键字，必填，表示过滤的关键字，一般使用""将关键字包裹
- 文件路径，必填，可作为内容输入端口

### wc数量统计
可以统计文件的行数、单词数量等
```bash
wc [-c -m -l -w] 文件路径
```
- -c，统计bytes数量
- -m，统计字符数量
- -l，统计行数
- -w，统计单词数量
- 参数可以作为内容输入端口

### 管道符
```bash
|
```
将管道符左边命令的结果作为右边命令的输入

### echo输出内容
```bash
echo 输入的内容
```
输出命令行内容输出指定内容
只有一个参数

### 反引号`
在echo中使用反引号`，将包围的内容作为命令执行

### 重定向符
- \>，将左侧命令的结果**覆盖**写入符号右侧的内容中
- \>>，将左侧命令的结果**追加**写入到右侧的内容

### tail命令
查看文件尾部内容，跟踪文件的最新修改
```bash
tail [-f -num] Linux路径
```
- 参数，表示被跟踪文件路径
- -f，可选，表示持续跟踪
- -num，可选，表示查看尾部多少行

### su/exit命令
```bash
su [-] [用户名]
```
- -符号是可选的，表示切换用户后是否加载环境
- 参数，表示要切换的用户，默认切换到root
- 切换用户后可以通过exit命令退出当前用户
- root用户切换到其他用户时不需要密码

### sudo临时权限
```bash
sudo 其他命令
```
- 为当前命令临时赋予root授权
- 需要为普通用户配置sudo认证
  - 切换到root用户执行visudo命令，会自动通过vi编辑器打开:/etc/sudoers
  - 在文件的最后添加
    ```bash
    [用户名] ALL = (ALL) NOPASSWD: ALL
    ```
      - 最后的NOPASSWD:ALL 表示使用sudo命令不需要密码
  - 通过wq保存



# vi/vim编辑器
vi/vim指Linux中最经典的文本编辑器
vim是vi的加强版本，兼容vi的所有指令，不仅能编辑文本，还能编辑shell程序

### 三种工作模式
- 命令模式，所有按键快捷键都理解为命令
- 输入模式，对文件内容进行自由编辑
- 底线命令模式，以开始，通常用于文件的保存、退出
- 进入默认为命令模式
- 命令模式下输入**i**进入输入模式
- 输入模式下按下esc退出输入模式
- 命令模式下输入：进入底线命令模式
- 底线命令模式下输入wq保存文件并退出

### 基础语法
```bash
vi 文件路径
vim 文件路径
```
- 如果文件路径表示的文件不存在，则命令用于编辑新文件
- 如果文件存在，则编辑已有文件

# 用户管理