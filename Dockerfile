FROM gradle:8.1.1-jdk17

WORKDIR /home/gradle
ADD src /home/gradle

CMD ["gradle", "appRun"]

EXPOSE 3000
