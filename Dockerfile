FROM ubuntu:latest
LABEL authors="devdo"

ENTRYPOINT ["top", "-b"]