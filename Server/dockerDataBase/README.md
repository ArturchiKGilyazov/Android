# **Как создать docker контейнер с базой данных?**
0. Поставить сам docker и всё необходимое.
1. Отрыть командную строку и перейти в папку с файлом "Dockerfile" 
2. Ввести команду : 
```
docker build --pull --rm -f "Dockerfile" -t dockerdatabase:latest "."
```
3. Ввести команду : 
```
docker run -d -p 5432:5432 dockerdatabase
```
4. **Готово, ты большой молодец**
