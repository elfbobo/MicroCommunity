FROM registry.cn-beijing.aliyuncs.com/sxd/ubuntu-java8:1.0
MAINTAINER wuxw <928255095@qq.com>


ADD bin/start_agent.sh /root/


RUN chmod u+x /root/start_agent.sh

CMD ["/root/start_agent.sh","dev"]