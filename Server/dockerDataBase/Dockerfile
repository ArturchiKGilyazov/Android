FROM postgres:13.2
COPY *.sql /docker-entrypoint-initdb.d/
ENV POSTGRES_PASSWORD=snowball
ENV POSTGRES_USER=snowball