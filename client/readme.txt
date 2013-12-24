
#请先安装node.js最新版本
http://nodejs.org/

#本项目使用grunt自动构建,执行以下命令，整个过程大概 20~30分钟
#在命令行进入当前目录

#安装grunt命令行工具grunt-cli

# grunt 必须依赖
npm install -g grunt-cli

# 生成项目脚手架 grunt-init --help
npm install -g grunt-init


#安装Yeoman

npm install -g yo

npm install -g generator-webapp

#安装grunt及插件，安装在项目根目录

npm install grunt --save-dev
npm install grunt-contrib-qunit --save-dev

#安装依赖的模块
npm install

#安装模版编译插件
npm install grunt-ember-templates --save-dev

#运行，自动编译模版
grunt server --force
