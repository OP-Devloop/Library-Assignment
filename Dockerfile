FROM ubuntu:latest
LABEL authors="oscpe"

ENTRYPOINT ["top", "-b"]