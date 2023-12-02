FROM gradle:alpine as BASE
WORKDIR /app
COPY ./ .
RUN gradle build

FROM openjdk:17-alpine
COPY --from=BASE /app/build/libs/aoc23_day02-1.0.0.jar /aoc23_day02.jar
COPY input.txt .
CMD java -jar /aoc23_day02.jar
