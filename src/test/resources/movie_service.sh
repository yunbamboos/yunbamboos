#!/bin/bash
export JAVA_HOME=/usr/java/jdk-17.0.2
export JRE_HOME=${JAVA_HOME}/bin
APP_HOME=/app/movie_service
APP_NAME=movie_service
usage() {
    echo "Usage: sh boot [APP_NAME] [start|stop|restart|status]"
    exit 1
}

is_exist(){
        PID=$(ps -ef |grep ${APP_NAME} | grep -v $0 |grep -v grep |awk '{print $2}')
        if [ -z "${PID}" ]; then
                # 如果进程不存在返回1
                return 1
        else
                # 进程存在返回0
                return 0
        fi
}

# 定义启动程序函数
start(){
        is_exist
        # shellcheck disable=SC2181
        if [ $? -eq "0" ]; then
                echo "${APP_NAME} is already running, PID=${PID}"
        else
                nohup ${JRE_HOME}/bin/java -jar ${APP_HOME}/${APP_NAME}.jar >/dev/null 2>&1 &
                PID=$(echo $!)
                echo "${APP_NAME} start success, PID=$!"
        fi
}

# 停止进程函数
stop(){
        is_exist
        if [ $? -eq "0" ]; then
                kill -9 "${PID}"
                echo "${APP_NAME} process stop, PID=${PID}"
        else
                echo "There is not the process of ${APP_NAME}"
        fi
}

# 重启进程函数
restart(){
        stop
        start
}

# 查看进程状态
status(){
        is_exist
        if [ $? -eq "0" ]; then
                echo "${APP_NAME} is running, PID=${PID}"
        else
                echo "There is not the process of ${APP_NAME}"
        fi
}

case $1 in
"start")
        start
        ;;
"stop")
        stop
        ;;
"restart")
        restart
        ;;
"status")
       status
        ;;
	*)
	usage
	;;
esac
exit 0
